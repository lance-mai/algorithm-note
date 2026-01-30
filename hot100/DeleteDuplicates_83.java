package hot100;

/**
 * 删除排序链表中的重复元素
 * 解法1：快慢指针
 */
public class DeleteDuplicates_83 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null) {
            if (fast.val != slow.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        // 要处理尾部重复元素
        slow.next = null;
        return head;
    }
}
