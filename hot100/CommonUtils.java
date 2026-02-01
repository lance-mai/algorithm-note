package hot100;

public class CommonUtils {
    public static ListNode generateList(int[] ints) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        for (int i : ints) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        return dummyHead.next;
    }

    public static void printList(ListNode head) {
        ListNode cur = head;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (cur != null) {
            sb.append(cur.val).append(" -> ");
            cur = cur.next;
        }
        sb.append("null]");
        System.out.println(sb);
    }
}
