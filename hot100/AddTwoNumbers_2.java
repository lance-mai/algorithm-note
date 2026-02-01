package hot100;

/**
 * 两数相加
 * 解法1：（行不通）先遍历每个链表组成数，通过加法得到结果后，再还原成链表
 * 这个解法不行，因为链表的节点数最大为100，没有这么大的位数，还是要以链表来做
 * 解法2：（迭代法）新增一个链表来承载结果。关键是在链表有长有短时，短链表的后面可以当做是0值的链表节点
 * 第一次解题时不对。问题是：取余和取模搞反了。取加和结果是 取余%，取进位是除法 x/10
 * 第二次解题时不对，因为没有考虑到最后一位怎么处理。需要考虑到如果还有carry，那还需要多加一个链表
 * 第三次解题时不对，因为没有考虑到当其中一个链表为空时，其值需要重置为0，而不是继承上一次结果
 */
public class AddTwoNumbers_2 {
    // 解法2：链表遍历
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        int carry = 0; // 进位
        while (cur1 != null || cur2 != null) {
            int num1 = 0;
            int num2 = 0;
            if (cur1 != null) {
                num1 = cur1.val;
                cur1 = cur1.next;
            }
            if (cur2 != null) {
                num2 = cur2.val;
                cur2 = cur2.next;
            }
            int sum = num1 + num2 + carry;
            carry = sum / 10;  // 取模
            sum = sum % 10;    // 取余
            cur.next = new ListNode(sum);
            cur = cur.next;
        }
        if (carry != 0) {
            cur.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        CommonUtils.printList(new AddTwoNumbers_2().addTwoNumbers(
                CommonUtils.generateList(new int[]{9, 9, 9, 9, 9, 9, 9}),
                CommonUtils.generateList(new int[]{9, 9, 9, 9})));
    }

    // 解法1：不行
    // public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    //     int num1 = 0;
    //     ListNode cur = l1;
    //     int base = 1;
    //     while (cur != null) {
    //         num1 += base * cur.val;
    //         base *= 10;
    //         cur = cur.next;
    //     }
    // }
}
