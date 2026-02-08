package hot100.priorityqueue;

import hot100.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并K个升序链表
 * 解法1：小顶堆，自然排序就是小顶堆
 * 报错：链表形成循环。需要在拼接的时候断链
 * 做法是，在pq弹完元素后，最后一个元素 cur.next = null就OK了
 * 因为最后一个元素可能指向前面的哪个元素
 * 时间复杂度分析：假设多个链表元素个数为 a b c ... n
 * 插入到小顶堆时为 log(a)+log(b)+...+log(n)
 * 弹出也是一样
 * 计算最坏结果（假设N个数最大）：O(NlogN)
 */
public class MergeKLists_23 {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode list : lists) {
            ListNode cur = list;
            while (cur != null) {
                pq.offer(cur);
                cur = cur.next;
            }
        }

        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        while (!pq.isEmpty()) {
            cur.next = pq.poll();
            cur = cur.next;
        }
        cur.next = null;
        return dummyHead.next;
    }
}
