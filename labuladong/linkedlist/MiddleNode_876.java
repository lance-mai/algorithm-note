package labuladong.linkedlist;

/**
 * 链表的中间节点。10分钟解决。双指针
 */
public class MiddleNode_876 {
    public ListNode middleNode(ListNode head) {
        // 首先需要知道有多少节点
        ListNode cur = head;
        int size = 0;
        while (cur != null) {
            cur = cur.next;
            size++;
        }
        int k = (size + 1) / 2;
        ListNode cur1 = head;
        ListNode cur2 = head;
        for (int i = 0; i < k - 1; i++) {
            cur1 = cur1.next;
        }
        while (cur1.next != null) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur2;
    }
}
