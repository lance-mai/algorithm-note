package bytedance.LRU;

public class DoubleList {
    int size;
    Node head, tail;

    public DoubleList() {
        size = 0;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.pre = head;
    }

    // add last
    public void addLast(Node x) {
        Node tailPre = tail.pre;
        tailPre.next = x;
        x.pre = tailPre;
        x.next = tail;
        tail.pre = x;
        size++;
    }

    // remove
    public void remove(Node x) {
        if (size == 0) {
            return;
        }
        Node xPre = x.pre;
        Node xNext = x.next;
        xPre.next = xNext;
        xNext.pre = xPre;
        x = null;
        size--;
    }

    // remove first
    public int removeFirst() {
        if (size == 0) {
            return -1;
        }
        Node toDelete = head.next;
        Node headNextNext = head.next.next;
        head.next = headNextNext;
        headNextNext.pre = head;
        size--;
        return toDelete.key;
    }
}
