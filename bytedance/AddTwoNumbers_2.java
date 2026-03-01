package bytedance;

public class AddTwoNumbers_2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        int carry = 0;
        while (l1 != null && l2 != null) {
            int val1 = l1.val;
            int val2 = l2.val;
            int sum = carry + val1 + val2;
            carry = sum / 10;
            int lowVal = sum % 10;
            cur.next = new ListNode(lowVal);
            cur = cur.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int val1 = l1.val;
            int sum = carry + val1;
            carry = sum / 10;
            int lowVal = sum % 10;
            cur.next = new ListNode(lowVal);
            cur = cur.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            int val2 = l2.val;
            int sum = carry + val2;
            carry = sum / 10;
            int lowVal = sum % 10;
            cur.next = new ListNode(lowVal);
            cur = cur.next;
            l2 = l2.next;
        }
        if (carry != 0) {
            cur.next = new ListNode(carry);
        }
        return dummy.next;
    }
}
