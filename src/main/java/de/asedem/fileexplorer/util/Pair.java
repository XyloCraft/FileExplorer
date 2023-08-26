package de.asedem.fileexplorer.util;

import org.jetbrains.annotations.NotNull;

public class Pair<K, V> {

    private K key;
    private V value;

    public Pair(@NotNull K key, @NotNull V value) {
        this.key = key;
        this.value = value;
    }

    @NotNull
    public static <K, V> Pair<K, V> of(@NotNull K key, @NotNull V value) {
        return new Pair<>(key, value);
    }

    @NotNull
    public K getKey() {
        return key;
    }

    public void setKey(@NotNull K key) {
        this.key = key;
    }

    @NotNull
    public V getValue() {
        return value;
    }

    public void setValue(@NotNull V value) {
        this.value = value;
    }
}
