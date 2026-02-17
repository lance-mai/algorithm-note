package hot100;

import java.util.Arrays;

/**
 * 最长有效括号（困难）挺难理解的，先不做
 * 解法1：我不会，但是我知道有效括号的两个特点
 * 1）有效括号：左括号数量等于右括号数量
 * 2）对于有效括号p，则对于p[0..i]（0<=i<p.len），有左括号数量大于或等于右括号
 * 我猜是使用动态规划
 * 我试着定义以下: dp[i]表示以s[i]为结尾的有效括号长度
 * 解法2：豆包。栈+动态规划
 * 栈，用来记录未匹配的左括号的“索引”
 * dp数组，dp[i] 定义为 以字符串第i-1位字符结尾的最长有效括号字串长度（这样定义是为了避免数组越界）
 *
 */
public class LongestValidParentheses_32 {

    // 解法1：不对。
    public int longestValidParentheses1(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            // 什么时候是有效括号呢？
            dp[i] = Math.max(dp[i], dp[i - 1] + 1);
        }
        int res = Integer.MIN_VALUE;
        for (int i : dp) {
            res = Math.max(res, i);
        }
        return res;
    }
}
