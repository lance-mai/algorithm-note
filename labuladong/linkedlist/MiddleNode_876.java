package labuladong.linkedlist;

/**
 * 链表的中间节点。10分钟解决。双指针。我的解法缺陷：需要先遍历一遍拿到size，再进行第二次遍历
 * labuladong解法：快慢指针。两个指针都指向头结点，slow前进一步，fast前进两步
 * 只需要遍历一遍
 */
public class MiddleNode_876 {
    // labuladong解法：快慢指针
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // public ListNode middleNode(ListNode head) {
    //     // 首先需要知道有多少节点
    //     ListNode cur = head;
    //     int size = 0;
    //     while (cur != null) {
    //         cur = cur.next;
    //         size++;
    //     }
    //     int k = (size + 1) / 2;
    //     ListNode cur1 = head;
    //     ListNode cur2 = head;
    //     for (int i = 0; i < k - 1; i++) {
    //         cur1 = cur1.next;
    //     }
    //     while (cur1.next != null) {
    //         cur1 = cur1.next;
    //         cur2 = cur2.next;
    //     }
    //     return cur2;
    // }
}
