package bytedance.LRU;

import java.util.HashMap;

public class LRUCache {
    int capacity;
    DoubleList cache;
    HashMap<Integer, Node> keyToNode;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new DoubleList();
        keyToNode = new HashMap<>();
    }

    public int get(int key) {
        if (!keyToNode.containsKey(key)) {
            return -1;
        }
        Node node = keyToNode.get(key);
        makeRecentlyUsed(key);
        return node.val;
    }

    private void makeRecentlyUsed(int key) {
        Node node = keyToNode.get(key);
        cache.remove(node);
        cache.addLast(node);
    }

    public void put(int key, int value) {
        if (keyToNode.containsKey(key)) {
            keyToNode.get(key).val = value;
            makeRecentlyUsed(key);
            return;
        }

        if (cache.size == capacity) {
            removeLeastUsed();
        }
        Node node = new Node(key, value);
        cache.addLast(node);
        keyToNode.put(key, node);
    }

    private void removeLeastUsed() {
        int deleteKey = cache.removeFirst();
        keyToNode.remove(deleteKey);
    }

    public static void main(String[] args) {
        LRUCache lru = new LRUCache(2);
        lru.put(1, 1);
        lru.put(2, 2);
        lru.get(1);
        lru.put(3, 3);
        lru.get(2);
        lru.put(4, 4);
        lru.get(1);
        lru.get(3);
        lru.get(4);
    }
}
