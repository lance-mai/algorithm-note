package hot100.LRU;

import java.util.LinkedHashMap;

/**
 * 自己尝试手撸，发现错误了。
 * 1、原来是忘了维护size
 * 2、capacity也要放到map入参中设定容量
 * <p>
 * 如果linkedhashmap不指定accessOrder,默认就是false，那么put和remove就是队列FIFO，remove队头，put队尾
 * 当设定accessOrder=true时，不需要另外维护 makeRecently和removeLeastuse，map会帮忙维护
 * <p>
 * 使用LinkedHashMap的时候，size在LRU类维护。如果是手撸LRU，那么size是在doubleList里面维护
 *
 */
public class LRUCache_146_2 {
    LinkedHashMap<Integer, Integer> cache;
    int capacity;
    int size;

    public LRUCache_146_2(int capacity) {
        cache = new LinkedHashMap<>(capacity, 0.75f, true);
        this.capacity = capacity;
        size = 0;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        // makeRecently(key);
        return cache.get(key);
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            cache.put(key, value);
            // makeRecently(key);
            return;
        }
        if (capacity == size) {
            removeLeastUse();
        }
        cache.put(key, value); // put到队尾
        size++;
        // makeRecently(key);
    }

    private void removeLeastUse() {
        Integer key = cache.entrySet().iterator().next().getKey();
        cache.remove(key); // remove队头
        size--;
    }

    // private void makeRecently(int key) {
    //     Integer value = cache.remove(key);
    //     // cache.putLast(key, value);
    //     cache.put(key, value);
    // }
    public static void main(String[] args) {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        map.put(0, 0);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        System.out.println(map);
        map.get(1);
        System.out.println(map);
        Integer next = map.keySet().iterator().next();
        map.remove(next);
        System.out.println(map);
        map.put(5, 5);
        System.out.println(map);
    }
}
