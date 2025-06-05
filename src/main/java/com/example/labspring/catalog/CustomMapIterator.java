package com.example.labspring.catalog;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class CustomMapIterator<K, V> implements Iterator<V> {
    private K[] keys;
    private int index = 0;
    private Map<K, V> productMap;

    public CustomMapIterator(Map<K, V> productMap) {
        this.productMap = productMap;
        Set<K> keySet = productMap.keySet();
        this.keys = (K[]) keySet.toArray();
    }

    @Override
    public boolean hasNext() {
        return index < keys.length;
    }

    @Override
    public V next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        K key = keys[index++];
        return productMap.get(key);
    }
}