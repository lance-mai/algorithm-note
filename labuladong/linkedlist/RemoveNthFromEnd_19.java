package labuladong.linkedlist;

/**
 * 删除链表的倒数第N个节点
 * 1、指针先跑n步，双指针。用时22分钟。注意需要使用到目标指针的前置指针，才能操作链表节点remove
 * 其实，直接找前置节点就行了，不用找
 */
public class RemoveNthFromEnd_19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode cur1 = head;
        ListNode preCur1 = dummyHead; // cur1的前置节点
        for (int i = 0; i < n; i++) {
            cur1 = cur1.next;
        }
        // ListNode cur2 = head;
        while (cur1 != null) {
            cur1 = cur1.next;
            // cur2 = cur2.next;
            preCur1 = preCur1.next;
        }
        // preCur1.next = cur2.next;
        preCur1.next = preCur1.next.next;
        // cur2 = null; // 手动断开cur2，垃圾回收
        return dummyHead.next;
    }

    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4, 5};
        ListNode dummyHead = new ListNode();
        ListNode cur = dummyHead;
        for (int i : ints) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        ListNode head = dummyHead.next;

        new RemoveNthFromEnd_19().removeNthFromEnd(head, 2);
    }
}
