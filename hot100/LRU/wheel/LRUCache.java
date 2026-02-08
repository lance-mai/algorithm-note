package hot100.LRU.wheel;

import java.util.HashMap;

public class LRUCache {
    // key -> Node(key, val)
    private HashMap<Integer, Node> map;
    // Node(k1, v1) <-> Node(k2, v2)
    private DoubleList cache;
    // max capacity
    private int cap;

    public LRUCache(int capacity) {
        this.cap = capacity;
        this.map = new HashMap<>();
        cache = new DoubleList();
    }

    // promote some key to a recently used key
    private void makeRecently(int key) {
        Node x = map.get(key);
        // remove from list
        cache.remove(x);
        // put to tail, tail means recently used
        cache.addLast(x);
    }

    // add recently used element
    private void addRecently(int key, int val) {
        Node x = new Node(key, val);
        // add to tail of list
        cache.addLast(x);
        // add to map
        map.put(key, x);
    }

    // delete a key
    private void deleteKey(int key) {
        Node x = map.get(key);
        // delete from list
        cache.remove(x);
        // delete from map
        map.remove(key);
    }

    // delete least recently used element
    private void removeLeastRecently() {
        // the first element is the target to delete
        Node x = cache.removeFirst();
        // remove from map
        map.remove(x.key);
    }

    // get some key
    private int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        // promote the element to be used recently
        makeRecently(key);
        return map.get(key).val;
    }

    // put some key
    private void put(int key, int val) {
        if (map.containsKey(key)) {
            // delete old key
            deleteKey(key);
            // add to recently use
            addRecently(key, val);
            return;
        }
        if (cache.size() == cap) {
            // delete least recently used element
            removeLeastRecently();
        }
        // add to recently used element
        addRecently(key, val);
    }
}
