package ru.skubatko.dev.otus.java.hw21.cachehw;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;

/**
 * @author sergey
 *         created on 14.12.18.
 */
public class MyCache<K, V> implements HwCache<K, V> {

    private final Map<K, V> cache = new WeakHashMap<>();
    private final List<WeakReference<HwListener<K, V>>> listeners = new ArrayList<>();

//Надо реализовать эти методы

    @Override
    public void put(K key, V value) {
        cache.put(key, value);
        listeners.forEach(reference -> Optional.ofNullable(reference.get())
                                               .ifPresent(listener -> listener.notify(key, value, "put")));
    }

    @Override
    public void remove(K key) {
        V removed = cache.remove(key);
        listeners.forEach(reference -> Optional.ofNullable(reference.get())
                                               .ifPresent(listener -> listener.notify(key, removed, "remove")));
    }

    @Override
    public V get(K key) {
        V value = cache.get(key);
        listeners.forEach(reference -> Optional.ofNullable(reference.get())
                                               .ifPresent(listener -> listener.notify(key, value, "get")));
        return value;
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        listeners.add(new WeakReference<>(listener));
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        listeners.remove(new WeakReference<>(listener));
    }
}