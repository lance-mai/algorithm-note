package hot100;

import java.util.Arrays;

/**
 * 打家劫舍
 * 解法1：看不懂。直接看答案。
 * 重点是要定义好dp，解法1是自顶向下的动态规划解法
 * 超时了，需要备忘录。
 *
 */
public class Rob_198 {
    int[] memo;

    public int rob(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return dp(nums, 0);
    }

    // 定义：返回nums[start...]能抢到的最大值
    private int dp(int[] nums, int start) {
        if (start >= nums.length) {
            return 0; // 抢到头了，没房屋可抢
        }
        // 如果备忘录中已经有了，那就直接返回
        if (memo[start] != -1) {
            return memo[start];
        }
        // 抢，那么隔壁下一家就不能抢
        int res1 = dp(nums, start + 2) + nums[start];
        // 不抢，隔壁下一家可以继续做选择（抢与不抢）
        int res2 = dp(nums, start + 1);
//        return Math.min(res1, res2);  不是最小值，是最大值
        memo[start] = Math.max(res1, res2);
        return memo[start];
    }
}
