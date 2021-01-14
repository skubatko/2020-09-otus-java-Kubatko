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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();
    private final Map<Class<?>, String> beanNameByClass = new HashMap<>();

    private static final Logger log = LoggerFactory.getLogger(AppComponentsContainerImpl.class);

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) {
        checkConfigClass(configClass);


        PriorityQueue<AppBean> pq = new PriorityQueue<>();
        for (Method method : configClass.getDeclaredMethods()) {
            if (!(method.isAnnotationPresent(AppComponent.class))) {
                continue;
            }

            AppComponent annotation = method.getDeclaredAnnotation(AppComponent.class);
            pq.add(new AppBean(annotation.order(), annotation.name(), method));
            beanNameByClass.put(method.getReturnType(), annotation.name());
        }

        Object configInstance = getConfigClassInstance(configClass);
        while (!(pq.isEmpty())) {
            AppBean bean = pq.poll();
            Object beanInstance = getBeanInstance(bean, configInstance);
            appComponents.add(beanInstance);
            appComponentsByName.put(bean.name, beanInstance);
        }
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
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

    private Object getBeanInstance(AppBean bean, Object configInstance) {
        try {
            Object[] beanArgs = getBeanArgs(bean);
            return bean.method.invoke(configInstance, beanArgs);
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
        String beanName = beanNameByClass.get(componentClass);
        if (beanName == null) {
            Optional<Class<?>> componentInterface =
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

    private static class AppBean implements Comparable<AppBean> {
        private final Integer order;
        private final String name;
        private final Method method;

        private AppBean(Integer order, String name, Method method) {
            this.order = order;
            this.name = name;
            this.method = method;
        }

        @Override
        public int compareTo(AppBean o) {
            return this.order - o.order;
        }
    }
}
