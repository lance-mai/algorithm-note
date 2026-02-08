package hot100.LRU;

import java.util.LinkedHashMap;

/**
 * LRU缓存。least recently used，最近最少使用
 * 解法1：先自己造轮子实现一遍LRU算法
 * 解法2：使用hash链表，LinkedHashMap，是java内置的
 * 疑问：linkedHashMap 如何remove first element？
 * 回答：哦，是通过迭代， cache.keySet().iterator().next()，找到第一个元素。在linkedHashMap中，keySet不是乱序的
 */
public class LRUCache_146 {
    // key -> value
    private final LinkedHashMap<Integer, Integer> cache;
    private final int cap;

    public LRUCache_146(int capacity) {
        this.cache = new LinkedHashMap<>(capacity);
        this.cap = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        int value = cache.get(key);
        // jdk21
        // cache.putLast(key, value);
        // jdk8:
        makeRecently(key, value);
        return value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            // jdk21
            // cache.putLast(key, value);
            // jdk8:
            makeRecently(key, value);
            return;
        }
        if (cache.size() == cap) {
            // how to find the first one?
            Integer first = cache.keySet().iterator().next();
            cache.remove(first);
        }
        // jdk21
        cache.putLast(key, value);
    }

    private void makeRecently(int key, int value) {
        // remove old
        if (!cache.containsKey(key)) {
            return;
        }
        cache.remove(key);
        // put new
        cache.put(key, value);
    }

    public static void main(String[] args) {
        LRUCache_146 cache = new LRUCache_146(10);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        cache.put(5, 5);
        cache.put(6, 6);
        cache.put(7, 7);
        cache.put(8, 8);
        cache.put(9, 9);
        cache.put(10, 10);
        cache.put(11, 11);
    }
}
