package ru.skubatko.dev.otus.java.hw24.appcontainer;

import ru.skubatko.dev.otus.java.hw24.appcontainer.api.AppComponent;
import ru.skubatko.dev.otus.java.hw24.appcontainer.api.AppComponentsContainer;
import ru.skubatko.dev.otus.java.hw24.appcontainer.api.AppComponentsContainerConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();
    private final Map<Class<?>, String> beanNameByClass = new HashMap<>();
    private final Map<Class<?>, Object> configClassInstanceByClass = new HashMap<>();

    private static final Logger log = LoggerFactory.getLogger(AppComponentsContainerImpl.class);

    public AppComponentsContainerImpl(Class<?>... configClasses) {
        processConfig(configClasses);
    }

    private void processConfig(Class<?>... configClasses) {
        checkConfigClasses(configClasses);

        buildConfigClassInstances(configClasses);

        buildAppComponentsContainer(configClasses);
    }

    private void checkConfigClasses(Class<?>... configClasses) {
        for (var configClass : configClasses) {
            checkConfigClass(configClass);
        }
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    private void buildConfigClassInstances(Class<?>... configClasses) {
        for (var configClass : configClasses) {
            configClassInstanceByClass.put(configClass, getConfigClassInstance(configClass));
        }
    }

    private Object getConfigClassInstance(Class<?> configClass) {
        try {
            return configClass.getConstructor().newInstance();
        } catch (Exception e) {
            log.error("cannot instantiate config class");
            throw new RuntimeException(e);
        }
    }

    private void buildAppComponentsContainer(Class<?>... configClasses) {
        var orderedBeans = getOrderedAppBeans(configClasses);
        while (!(orderedBeans.isEmpty())) {
            AppBean bean = orderedBeans.poll();
            Object beanInstance = getBeanInstance(bean);
            appComponents.add(beanInstance);
            appComponentsByName.put(bean.name, beanInstance);
        }
    }

    private PriorityQueue<AppBean> getOrderedAppBeans(Class<?>... configClasses) {
        PriorityQueue<AppBean> orderedBeans = new PriorityQueue<>(Comparator.comparingInt(o -> o.order));
        for (var configClass : configClasses) {
            for (var method : configClass.getDeclaredMethods()) {
                if (!(method.isAnnotationPresent(AppComponent.class))) {
                    continue;
                }

                var annotation = method.getDeclaredAnnotation(AppComponent.class);
                orderedBeans.add(new AppBean(annotation.order(), annotation.name(), method));
                beanNameByClass.put(method.getReturnType(), annotation.name());
            }
        }
        return orderedBeans;
    }

    private Object getBeanInstance(AppBean bean) {
        try {
            var method = bean.method;
            return method.invoke(configClassInstanceByClass.get(method.getDeclaringClass()), getBeanArgs(bean));
        } catch (Exception e) {
            log.error("cannot instantiate bean = {}", bean.name);
            throw new RuntimeException(e);
        }
    }

    private Object[] getBeanArgs(AppBean bean) {
        return Arrays.stream(bean.method.getParameters())
                       .map(Parameter::getType)
                       .map(beanNameByClass::get)
                       .map(appComponentsByName::get)
                       .toArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <C> C getAppComponent(Class<C> componentClass) {
        var beanName = beanNameByClass.get(componentClass);
        if (beanName == null) {
            var componentInterface =
                    Arrays.stream(componentClass.getInterfaces())
                            .filter(beanNameByClass::containsKey)
                            .findAny();
            if (componentInterface.isEmpty()) {
                return null;
            }

            beanName = beanNameByClass.get(componentInterface.get());
        }

        return (C) appComponentsByName.get(beanName);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <C> C getAppComponent(String componentName) {
        return (C) appComponentsByName.get(componentName);
    }

    private static class AppBean {
        private final Integer order;
        private final String name;
        private final Method method;

        private AppBean(Integer order, String name, Method method) {
            this.order = order;
            this.name = name;
            this.method = method;
        }
    }
}
