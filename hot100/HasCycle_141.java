package hot100;

import java.util.HashSet;

/**
 * 环形链表
 * 解法1：使用hashset，如果一个节点遇到两次，则代表有环。时间复杂度 O(N)，空间O(N)
 * 解法2：使用快慢指针，如果俩指针能遇到，说明是环形链表
 * 注意：创建虚拟头节点时我忘了dummyhead.next =head。导致虚拟头节点是孤立的，无法得到结果
 * 为了能够得知是在哪里开始有环的，快慢指针初始化时都指向head节点，而不是头节点
 *
 */
public class HasCycle_141 {
    // 解法2
    public boolean hasCycle(ListNode head) {
//        ListNode dummyHead = new ListNode(-1);
//        // 注意点
//        dummyHead.next = head;
//        ListNode slow = dummyHead;
//        ListNode fast = dummyHead;

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    // 解法1
    public boolean hasCycle1(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode cur = head;
        while (cur != null) {
            if (set.contains(cur)) {
                return true;
            }
            set.add(cur);
            cur = cur.next;
        }
        return false;
    }
}
