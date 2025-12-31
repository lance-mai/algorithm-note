package labuladong.stackandqueue;

import java.util.ArrayDeque;

/**
 * 重排链表。
 * 1、根据原链表A创建一条方向相反的链表B
 * 2、A0 -> B0 -> A1 -> B1 ....
 * 花了50分钟，太慢了，只击败了3%。内存消耗也大。需要优化
 * 出了两个问题：1）正反两个数组，都装了一个null  2）在组装链表时，最后一个节点忘了断开，导致循环链表出现
 * 优化1：链表改为数组 => 性能没啥变化
 * 优化2：去掉HashSet，性能还是没有多大变化
 * 看labuladong解法：这道题难点在于链表无法从尾部循环，那么可以利用栈的特点，从尾部开始循环
 * 优化：压栈的时候不用虚拟头结点，不然会多一个null
 * 好好画图，就会了
 */
public class ReorderList_143 {
    public void reorderList(ListNode head) {
        ArrayDeque<ListNode> stack = new ArrayDeque<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            // 链表尾部节点
            ListNode backNode = stack.pop();
            ListNode next = cur.next;
            // 结束条件，不管链表size是奇数还是偶数
            if (backNode == next || backNode == cur) {
                backNode.next = null;
                break;
            }
            cur.next = backNode;
            backNode.next = next;
            cur = next;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        new ReorderList_143().reorderList(node1);
    }
}


