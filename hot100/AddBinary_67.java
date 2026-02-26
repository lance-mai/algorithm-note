package hot100;

/**
 * 二进制求和
 * 解法1：双指针，倒着相加
 * 别忘了 int i = a.charAt(p1) - '0'; 要减去'0'，才能得到真正的0
 *
 */
public class AddBinary_67 {
    public String addBinary(String a, String b) {
        int p1 = a.length() - 1;
        int p2 = b.length() - 1;
        int carry = 0; // 进位
        StringBuilder res = new StringBuilder();
        while (p1 >= 0 && p2 >= 0) {
            int i = a.charAt(p1) - '0';
            int j = b.charAt(p2) - '0';
            int cur = (i + j + carry) % 2;
            res.insert(0, cur);
            carry = (i + j + carry) / 2;
            p1--;
            p2--;
        }
        while (p1 >= 0) {
            int i = a.charAt(p1) - '0';
            int cur = (i + carry) % 2;
            res.insert(0, cur);
            carry = (i + carry) / 2;
            p1--;
        }

        while (p2 >= 0) {
            int j = b.charAt(p2) - '0';
            int cur = (j + carry) % 2;
            res.insert(0, cur);
            carry = (j + carry) / 2;
            p2--;
        }
        if (carry == 1) {
            res.insert(0, "1");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(new AddBinary_67().addBinary("11", "1"));
    }
}
