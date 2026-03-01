package bytedance.LRU.linkedhashmap;

import java.util.LinkedHashMap;

public class LRUCache {
    int size;
    int capacity;
    LinkedHashMap<Integer, Integer> cache;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true);
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        Integer value = cache.get(key);
        cache.remove(key);
        cache.put(key, value);
        return value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            cache.put(key, value);
            return;
        }
        if (size == capacity) {
            Integer leastUsedKey = cache.entrySet().iterator().next().getKey();
            cache.remove(leastUsedKey);
            size--;
        }
        cache.put(key, value);
        size++;
    }
}
