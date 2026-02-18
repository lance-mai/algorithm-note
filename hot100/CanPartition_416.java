package hot100;

import java.util.Arrays;

/**
 * 分割等和子集(有点难，好好理解)
 * <p>
 * 解法1：回溯+剪枝
 * 思路：nums的和为奇数，不行。nums的元素为1个，也不行。pass
 * 只需要求元素和为sum的一半的子集即可
 * 超时了。存在重复计算
 * 问一下豆包吧：
 * 1、重复路径计算，比如[2,2,3]，先选第一个2后选第二个2 和 先选第二个2后选第一个2，重复计算
 * 解决：这个最关键。不解决这个问题，还是会超时。
 * 2、无排序优化，导致无法提前终止遍历（即当前元素已超过剩余目标） -》 我通过target=sum/2不可以达到这个目的
 * 解决：升序排序，让结果提前到来
 * 3、起始索引从0开始，每次递归都要从0开始遍历，虽然有used数组，但还是避免不了大量无效遍历
 * 解决：加一个起始索引start，不用每次都从0开始遍历；排序后，如果最大元素都超过target，那就没有必要继续了，直接淘汰
 * ---> 各种超时，各种剪枝还是不行啊！！！
 * <p>
 * 解法2：直接看答案吧。
 * labuladong，使用动态规划。 -> 经典子集背包问题
 * 本题核心目标是，判断一个整数数组能否被分割成两个子集，使得两个子集的元素和完全相等（因此和为奇数则不行）
 * 转化为0-1背包问题：
 * 1）数组总和的一半sum/2 => 背包容量
 * 2）数组中的每一个元素 => 物品
 * 3）元素值 => 物品重量/价值
 * 4）问题目标：能够选出若干物品，使得总重量 = 背包容量
 * 还是答错了，原因：sum要取一半
 * 需要记住dp[i][j]定义
 * 状态方程好好理解
 *
 *
 */
public class CanPartition_416 {
    // 解法2：动态规划（0-1背包问题）
    public boolean canPartition(int[] nums) {
        // 求和
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0) return false; // 奇数

        sum /= 2; //sum要取一半来用，转化为背包问题

        int n = nums.length;
        // 定义：dp[i][j]表示 前i个元素，是否能够选出若干物品，组成j，true为可以，false为不可以
        boolean[][] dp = new boolean[n + 1][sum + 1]; // 包含0个物品、0重量，因此要+1
        // base case：当j为0时，都是true，因为目标重量为0时，i等于多少都可以（不选物品就能达到重量0）
        for (int i = 0; i < dp.length; i++) dp[i][0] = true;
        // 状态转移方程，开始遍历任意i和任意j
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                // 如果容量不足以接纳下一个元素时，则可以判断为不行，其状态就可以上一个dp一样
                if (nums[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 选或者不选的结果取或，因为只要有一个达到目的就可以
//                    dp[i][j] = dp[i - 1][j] || dp[i][j - nums[i - 1]];
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][sum];
    }

    // 解法1：优化版
    boolean found = false;

    public boolean canPartition1(int[] nums) {
        int n = nums.length;
        if (n == 1) { // 单个元素,pass
            return false;
        }
        // 求和
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) { // 奇数，pass
            return false;
        }

        // 升序排序
        nums = Arrays.stream(nums).sorted().boxed().mapToInt(Integer::intValue).toArray();


        // 回溯
        int target = sum / 2;
        boolean[] used = new boolean[n];
        backtrack(nums, target, 0, 0, used);
        return found;
    }

    // start是优化剪枝，让回溯分支知道从哪里开始遍历，而不是每次都从0开始
    private void backtrack(int[] nums, int target, int start, int curSum, boolean[] used) {
        // base case
        if (found) {
            return;
        }
        if (curSum == target) {
            found = true;
            return;
        }
        if (curSum > target) {
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (used[i]) { // 使用过，就不再使用
                continue;
            }
            // 关键！跳过重复元素，避免重复递归路径。不解决这个问题，一直会超时
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            // 选择
            curSum += nums[i];
            used[i] = true;
            start++;
            // 回溯
            backtrack(nums, target, start, curSum, used);
            // 撤销选择
            used[i] = false;
            curSum -= nums[i];
            start--;
        }
    }
}
