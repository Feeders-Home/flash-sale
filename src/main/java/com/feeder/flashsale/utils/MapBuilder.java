package com.feeder.flashsale.utils;

import java.util.HashMap;
import java.util.Map;

public final class MapBuilder<K, V> {

    private MapBuilder() {}

    /**
     * @deprecated use {@link MapBuilder#newBuilder()}, since the bad name
     */
    public static <K, V> MapBuilder<K, V> newBuild() {
        return newBuilder();
    }

    public static <K, V> MapBuilder<K, V> newBuilder() {
        return new MapBuilder<>();
    }

    public MapBuilder<K, V> put(K k, V v) {
        m.put(k, v);
        return this;
    }

    public Map<K, V> build() {
        return m;
    }

    private final Map<K, V> m = new HashMap<>();
}
