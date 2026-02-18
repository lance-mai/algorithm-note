package hot100;

/**
 * 乘积最大子数组
 * 解法1：动态规划
 * dp[i]定义：不会
 * 解法2：labuladong。
 * 要同时维护「以 nums[i] 结尾的最大子数组」和「以 nums[i] 结尾的最小子数组」，以便适配 nums[i] 可能为负的情况。
 * 定义：
 * 1、以 nums[i] 结尾的子数组，乘积最小为 dp1[i]
 * 2、以 nums[i] 结尾的子数组，乘积最大为 dp2[i]
 * 解答错误，为什么：
 * 下标选择有问题。
 * 错误的选择：
 * dp1[i] = min(dp1[i] * nums[i - 1], dp2[i] * nums[i - 1], nums[i - 1]);
 * dp2[i] = max(dp1[i] * nums[i - 1], dp2[i] * nums[i - 1], nums[i - 1]);
 * 正确的选择：
 * dp1[i] = min(dp1[i - 1] * nums[i], dp2[i - 1] * nums[i], nums[i]);
 * dp2[i] = max(dp1[i - 1] * nums[i], dp2[i - 1] * nums[i], nums[i]);
 */
public class MaxProduct_152 {
    // 解法2：
    public int maxProduct(int[] nums) {
        int n = nums.length;
        // 定义：以 nums[i] 为结尾的子数组，乘积最小为 dp1[i]
        int[] dp1 = new int[n];
        // 定义：以 nums[i] 为结尾的子数组，乘积最大为 dp2[i]
        int[] dp2 = new int[n];
        // base case
        dp1[0] = nums[0];
        dp2[0] = nums[0];
        // 状态转移方程
        for (int i = 1; i < n; i++) {
//            dp1[i] = min(dp1[i] * nums[i - 1], dp2[i] * nums[i - 1], nums[i - 1]);
//            dp2[i] = max(dp1[i] * nums[i - 1], dp2[i] * nums[i - 1], nums[i - 1]);
            // 注意nums[i]和dpx[i]下标选择
            dp1[i] = min(dp1[i - 1] * nums[i], dp2[i - 1] * nums[i], nums[i]);
            dp2[i] = max(dp1[i - 1] * nums[i], dp2[i - 1] * nums[i], nums[i]);
        }
        int res = Integer.MIN_VALUE;
        for (int i : dp2) {
            res = Math.max(res, i);
        }
        return res;
    }

    private int min(int i, int j, int k) {
        return Math.min(i, Math.min(j, k));
    }

    private int max(int i, int j, int k) {
        return Math.max(i, Math.max(j, k));
    }
}
