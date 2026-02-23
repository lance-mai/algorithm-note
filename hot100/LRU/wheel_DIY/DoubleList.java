package hot100.LRU.wheel_DIY;

/**
 * 双向链表
 * 不用size。size在LRU维护 ==> 纠正，应该由list维护，解耦开来。
 * 双向链表有问题。
 * 约定：addLast就是排在尾部，addFirst就是排在head指向的第一个节点
 * 方向为从左向右插入，先入先出。所以 尾部是排在前头的
 */
public class DoubleList {
    private int size;
    Node head, tail; // 虚拟头、尾节点

    public DoubleList() {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.pre = head;
        size = 0;
    }

    // add last。接近tail的一侧
    public void addLast(Node x) {
        Node xPre = tail.pre;
        tail.pre = x;
        x.next = tail;
        xPre.next = x;
        x.pre = xPre;
        size++;
    }

    // 如何判断链表中是否存在x。我认为是要依靠LRU中的map来判断
    public void remove(Node x) {
        Node xPre = x.pre;
        Node xNext = x.next;
        xPre.next = x.next;
        xNext.pre = xPre;
        size--;
    }


    // remove first
    public Node removeFirst() {
        if (size == 0) {
            return null;
        }
        Node toDeletoNode = head.next;
        head.next = head.next.next;
        toDeletoNode.next.pre = head;
        size--;
        return toDeletoNode;
    }

    public int size() {
        return size;
    }

    // // add first。接近head的一侧
    // public void addFirst(Node x) {
    //     Node xNext = head.next;
    //     head.next = x;
    //     x.pre = head;
    //     x.next = xNext;
    //     xNext.pre = x;
    //     size++;
    // }

    // add last。接近tail的一侧
    // public void addLast(Node x) {
    //     Node xPre = tail.pre;
    //     tail.pre = x;
    //     x.next = tail;
    //     xPre.next = x;
    //     x.pre = xPre;
    //     size++;
    // }

    // add ==> 好像没有这个需求。
    // public void add(Node x, int index) {
    // }

    // // get first
    // public Node getFirst() {
    //     // if (isEmpty()) {
    //     //     throw new IllegalArgumentException("The list is empty.");
    //     // }
    //     return head.next;
    // }

    // get last
    // public Node getLast() {
    //     // if (isEmpty()) {
    //     //     throw new IllegalArgumentException("The list is empty.");
    //     // }
    //     return tail.pre;
    // }

    // remove last
    // public Node removeLast() {
    //     // if (isEmpty()) {
    //     //     throw new IllegalArgumentException("The list is empty.");
    //     // }
    //     Node toDeleteNode = tail.pre;
    //     tail.pre = tail.pre.pre;
    //     toDeleteNode.next = tail;
    //     size--;
    //     return toDeleteNode;
    // }

    public static void main(String[] args) {
        DoubleList list = new DoubleList();

    }
}
