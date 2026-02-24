package hot100.LRU.wheel_DIY;

import java.util.HashMap;

/**
 * LRU缓存
 * 要求函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。这样的话，就不能去遍历双向链表
 * 回答：（看参考答案后得出的）因为是双向链表，因此任意删除一个node对象，时间复杂度是O(1)的
 * 那如何快速让元素 makeRecently呢？
 * 我没有头绪，看一下答案吧。
 * <p>
 * 总结经验：
 * 1、node需要next、pre两个指针
 * 2、node需要key，为LRU提供map的索引，快速找到node
 * 3、doublelist需要自行维护size，封装起来，LRU不care
 * 4、doublelist只需要3个方法
 * 1）Node remove(Node x)，这是为了将节点提升为队头前，先将节点清除
 * 2）void addLast(Node x)，将新节点排在队尾，作为最近最常用的节点
 * 3）void removeFirst()，当缓存满时，需要清掉最近最不常用的节点，约定为first
 * 5、LRUCache需要维护一个map（key -> Node）
 * 6、LRUCache需要makeRecently方法，将元素提升为最近最常使用
 * 7、LRUCache需要removeLeastUsed方法，将最近最不常使用的节点清除
 */
public class LRUCache_146_DIY {
    int capacity;
    // int size;
    DoubleList cache;
    HashMap<Integer, Node> keyToNode;

    public LRUCache_146_DIY(int capacity) {
        this.capacity = capacity;
        // this.size = 0;
        cache = new DoubleList();
        keyToNode = new HashMap<>(capacity);
    }

    public int get(int key) {
        if (!keyToNode.containsKey(key)) {
            return -1;
        }
        makeRecently(key); // 提升该key到最前面
        return keyToNode.get(key).val;
    }

    public void put(int key, int value) {
        // 如果已存在key，那就更新值，不更新key
        if (keyToNode.containsKey(key)) {
            makeRecently(key); // 提升地位
            keyToNode.get(key).val = value; // 更新值
            return;
        }
        // 判断缓存是否占满，如果已满则，则先清掉最不常使用的
        Node x = new Node(key, value);
        if (cache.size() == capacity) {
            removeLeastUsed();
        }
        // 这时候有空位
        keyToNode.put(key, x);
        cache.addLast(x);
    }

    // 删除最近不常使用的节点
    private void removeLeastUsed() {
        Node toRemove = cache.removeFirst();
        keyToNode.remove(toRemove.key);
    }

    // 将key提升到最近使用，即排到前面
    public void makeRecently(int key) {
        Node node = keyToNode.get(key);
        // 删除再添加
        cache.remove(node);
        cache.addLast(node);
    }

    public static void main(String[] args) {
        LRUCache_146_DIY LRU = new LRUCache_146_DIY(3);
        LRU.put(1, 1);
        LRU.put(2, 2);
        LRU.put(3, 3);
        LRU.put(4, 4);
        System.out.println(LRU.get(4));
        System.out.println(LRU.get(3));
        System.out.println(LRU.get(2));
        System.out.println(LRU.get(1));
        LRU.put(5, 5);
        System.out.println(LRU.get(1));
        System.out.println(LRU.get(2));
        System.out.println(LRU.get(3));
        System.out.println(LRU.get(4));
        System.out.println(LRU.get(5));
    }
}
