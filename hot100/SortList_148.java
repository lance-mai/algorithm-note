package hot100;

import java.beans.beancontext.BeanContext;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 排序链表
 * 解法1：使用优先级队列，小顶堆，时间复杂度O(nlogn)，空间复杂度为O(N)，题目进阶要求常数级空间复杂度
 * 通过，但是效率比较低。
 * 解法2：递归版归并排序（易理解。空间O(logn)-》递归调用栈的深度，时间O(n log n)）
 * 归并排序
 * 1、分：用快慢指针找到链表中点，把链表分成左右两个子链表
 * 快指针走两步，慢指针走一步。当fast走到终点时，slow恰好走到链表中点
 * 2、治：递归对左右两个子链表分别排序
 * 递归终止条件，当链表为空或者只有一个节点时，链表本身就是有序的，直接返回
 * 3、合：把排好序的子链表合并成一个有序链表
 * 用虚拟头节点简化两个链表的拼接
 * 漂亮，一气呵成，直接写出自顶向下 递归版归并排序。我信心大增，很开心 ^_^
 *
 * <p>
 * 解法3；需要归并排序，才能做到空间复杂度为O(1)。自底向上的归并排序是链表排序的最优解 （最优解，空间O(1)，时间O(n log n)）
 * 直接看答案吧
 * 先掌握到递归法的归并排序吧。
 *
 *
 */
public class SortList_148 {
    // 解法2：自底向上归并排序
    public ListNode sortList(ListNode head) {
        // base case
        if (head == null || head.next == null) {
            return head;
        }
        // 使用快慢指针找出中间节点
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode slow = dummyHead;
        ListNode fast = dummyHead;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 得到中点为slow以后，拆分左右链表，记得断开
        ListNode mid = slow;
        ListNode midNext = slow.next;
        mid.next = null; // 断开左右两个子链表
        // 分别取排序两个子链表
        ListNode list1 = sortList(head);
        ListNode list2 = sortList(midNext);
        // （后序遍历）将排序好的两个子链表进行合并，也就是经典的有序链表合并操作
        return merge(list1, list2);
    }

    // 合并两个有序链表。升序
    private ListNode merge(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        ListNode left = list1;
        ListNode right = list2;
        while (left != null && right != null) {
            if (left.val < right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        if (left != null) {
            cur.next = left;
        }
        if (right != null) {
            cur.next = right;
        }
        return dummyHead.next;
    }

    // 解法1：优先级队列
    public ListNode sortList1(ListNode head) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>((l1, l2) -> l1.val - l2.val); // 默认小顶堆
        ListNode cur = head;
        while (cur != null) {
            queue.offer(cur);
            cur = cur.next;
        }
        ListNode dummyHead = new ListNode(-1);
        cur = dummyHead;
        while (!queue.isEmpty()) {
            cur.next = queue.poll();
            cur = cur.next;
        }
        cur.next = null;
        return dummyHead.next;
    }
}
