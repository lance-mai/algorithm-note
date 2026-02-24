package hot100;

/**
 * 数字1的个数
 * 我的思路：不会，感觉是技巧性的东西，直接看答案吧。
 * 解法1：直接看答案。答案也看不懂（豆包）
 */
public class DigitOneIntNumber_162 {
    public int digitOneInNumber(int num) {
        return f(num);
    }

    // 返回：<=num的数中1的总数
    private int f(int num) {
        if (num <= 0) {
            return 0;
        }
        String s = String.valueOf(num);
        int high = s.charAt(0) - '0'; // 高位
        int pow = (int) Math.pow(10, s.length() - 1); // 高位的位权，如124，高位为1，位权为100
        int extra = num - high * pow; // 除了高位以外，其他的数值
        if (high == 1) { // 高位为1
            return f(pow - 1) + extra + 1 + f(extra);
        } else { // 高位不为1（当然也不可能为0）
            return pow + high * f(pow - 1) + f(extra);
        }
    }
}
