package hot100;

/**
 * 最大子数组和
 * 解法1：滑动窗口，需要有序才行（纠正，不一定要有序，但是要知道扩大和缩小窗口的时机）。
 * 有点难理解，算了
 * <p>
 * 解法2：√ 前缀和。labuladong. 我也想到了。输入为{1}时，预期输出是1，但是实际输出为0。why？是前缀和算的有问题
 * 输入为{-1}时，预期输出为-1，结果输出为1了。why？边界值处理有问题
 * 所以，单独将单个元素拎出来处理，简单一点。解决上面的问题了，但还是有问题
 * 什么问题呢？感觉就是取该前缀和最大值和最小值时没处理好。记录最大值和最小值的变量，初始化没做好。
 * 初始化以后还是出问题。反正就是修修补补，问题没完没了
 * 豆包指出问题：核心是最大前缀和必须要在最小前缀和之后
 * labuladong补充：
 * 解法3：labuladong，动态规划。求最值问题，可以考虑用动态规划
 * 如果我们定义：dp[i]是nums[0,...,i]中的“最大的子数组和”，按照数学归纳法，当知道dp[i-1]时，如何推导出dp[i]？
 * 不行，因为子数组一定是连续的，按照dp定义，nums[0...i]中的最大子数组与num[i+1]相邻，所以没法从dp[i-1]推导出dp[i]
 * 重新定义dp：dp[i]是以nums[i]为结尾的最大子数组和，这样，nums[0..i]与nums[i+1]是相邻的
 * 在这种定义下，想得到整个nums数组的最大子数组和，需要遍历整个dp，而不是直接返回dp[nums.length-1]
 * 得知dp[i-1]后，如何推导出dp[i]呢？只有两个选择。选择1：将nums[i]与前面的dp[i-1]合并 选择2：舍弃dp[i-1]，nums[i]作为新的dp[i]
 * 错误了。原因是：
 */
public class MaxSubArray_53 {
    // 解法1：滑动窗口


    // 解法3：动态规划(递推)
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        // base case
        // 第一个元素前面没有子数组
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int i = 1; i < dp.length; i++) {
//            dp[i] = Math.max(dp[i - 1] + nums[i - 1], nums[i - 1]);
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }
        // 遍历dp，求最大值
        int res = Integer.MIN_VALUE;
        for (int j : dp) {
            res = Math.max(res, j);
        }
        return res;
    }

    // 解法2 ：前缀和
    public int maxSubArray2(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        // pres[i] 表示 nums[0,...,i-1]的和
        // pres[i+1] 表示 nums[0,...,i]的和
        int[] pres = getPreSumsOf(nums); // 前缀和，第一个元素pres[0]不算
        // 最大前缀和要在最小前缀和之后
        int res = Integer.MIN_VALUE; // 初始化结果为最小整数（确保能覆盖全负数的情况）
        int minVal = Integer.MAX_VALUE; // 初始化minVal为最大整数（用于记录preSum[0..i]中的最小值）
        // 遍历数组，寻找pres[0,...,i]最小值
        for (int i = 0; i < nums.length; i++) {
            minVal = Math.min(minVal, pres[i]);
            res = Math.max(res, pres[i + 1] - minVal); // pres[i+1]表示nums[0,...,i]的和
        }
        return res;
    }

    private int[] getPreSumsOf(int[] nums) {
        int n = nums.length;
        int[] pres = new int[n + 1];
        pres[0] = 0;
        for (int i = 1; i < pres.length; i++) {
            pres[i] = pres[i - 1] + nums[i - 1];
        }
        return pres;
    }

    public static void main(String[] args) {
//        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums = {-1};
        System.out.println(new MaxSubArray_53().maxSubArray(nums));
    }
}
