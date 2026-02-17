package hot100;

import java.util.Arrays;

/**
 * 最长递增子序列
 * 解法1：不会。动态规划。labuladong O(N^2)
 * 定义：× dp[i]是nums[0..i]的最长严格递增子序列
 * 定义：√ dp[i]是以nums[i]结尾的最长严格递增子序列
 * 解法2；二分查找。labuladong原话，hhh：这个解法的时间复杂度为
 * O(NlogN)，但是说实话，正常人基本想不到这种解法（也许玩过某些纸牌游戏的人可以想出来）。
 * 所以大家了解一下就好，正常情况下能够给出动态规划解法就已经很不错了。
 */
public class LengthOfLIS_300 {
    // 解法2：二分查找
    public int lengthOfLIS(int[] nums) {
        return 1;
    }

    public int lengthOfLIS1(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1); // dp[0]为1，后面都先初始化为1，因为一个数组最长严格递增子序列至少有一个元素
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // 状态转移方程，有点难度
//                dp[i] = Math.max(dp[i], dp[j] + 1);
                // 需要做判断，如果后面一个元素已经不递增了，那就要算dp[i]
                // 为什么不是<=，不是严格递增么。
                // 解答：No，因为序列是以nums[i]结尾的。核心判断：nums[j] < nums[i] 是严格递增的关键
                if (nums[j] < nums[i]) {
                    // 更新dp[i]，如果nums[j]能接在nums[i]前面，那么dp[i]= dp[j]+1（与之前的dp[i]取最大值）
                    // 为什么要取最大值？因为一个dp[i]可能会被多个j尝试更新，需保留最优解
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = Integer.MIN_VALUE;
        for (int j : dp) {
            res = Math.max(res, j);
        }
        return res;
    }
}
