package hot100.LRU.wheel_DIY;

/**
 * 链表节点
 * 链表节点中为什么要有key？只有val不行么？
 * 因为在LRU中，需要map来通过key快速找到node节点。所以node中要有key
 */
public class Node {
    int key; // map的索引
    int val;
    Node next;
    Node pre;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
