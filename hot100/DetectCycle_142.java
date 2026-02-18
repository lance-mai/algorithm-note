package hot100;

/**
 * 环形链表II
 * 解法1：快慢指针，第一次相遇后，将其中一个节点指向头节点，两个指针以相同速度前进，再次相遇的节点即为环入口节点
 * 超时了。为什么？记得退出while循环
 */
public class DetectCycle_142 {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                // 这里要退出循环
                break;
            }
        }
        if (!hasCycle) {
            return null;
        }
        // 将慢指针指向头节点，两者匀速向前
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow; // or fast
    }
}
