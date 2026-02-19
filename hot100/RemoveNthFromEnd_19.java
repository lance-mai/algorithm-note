package hot100;

/**
 * 删除链表的倒数第N个节点
 * 解法1：快慢指针。注意遍历到待删除节点的前置节点。使用dummyHead
 */
public class RemoveNthFromEnd_19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode fast = dummyHead;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        ListNode slow = dummyHead;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // 循环结束后，slow是要删除节点的前置节点，fast指向最后一个节点
        ListNode toDelete = slow.next;
        slow.next = toDelete.next;
        toDelete = null;
        return dummyHead.next;
    }
}
