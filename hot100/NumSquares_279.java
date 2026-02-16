package hot100;

import java.util.Arrays;

/**
 * 完全平方数
 * 解法1：没有思路，直接看答案。
 * 动态规划。labuladong。
 * 解析：我们要解决的问题是，给定整数n，求和为n的完全平方数最小数量
 * 定义dp[i]:组成数字i所需的完全平方数的数量。
 * 对于任意一个数字i，要求出dp[i]，最后一步一定是要加上某个完全平方数j^2（j是整数，且j^2<=i）
 * 也就是说，i = (i - j^2) + j^2
 * 错误了，为什么：
 * 1、要求dp最小值，那么赋初始值时，都要赋最大值
 * 2、j不能从0开始，要从1开始
 *
 *
 */
public class NumSquares_279 {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        // 要求dp最小值，那么赋初始值时，都要赋最大值
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
//            for (int j = 0; j * j <= i; j++) {  j不能从0开始
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}
