package hot100;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 目标和
 * 【解法1】：我知道是用动态规划写的。但是状态转移方程我写不出来。我先使用回溯算法写一下，可能时间复杂度上不通过，但是不要紧，总比写不出来强
 * 回溯。
 * 解答错误。为什么？
 * 错误1、多余的for循环。我选择+或-时加上了for (int i = start + 1; i < nums.length; i++)，这是误用
 * 目标和问题的的回溯逻辑是，逐个处理每个元素，对每个元素做+/-选择，不需要循环遍历后续元素
 * for循环会导致 跳过中间元素、重复元素、遗漏大量组合
 * 错误2、终止条件和结果判断逻辑割裂：我把结果判断放在start==length-1上，但是终止条件却写在start==length上
 * 会导致：非最后一个元素的终止条件无判断，最后一个元素的判断逻辑也仅处理了部分情况
 * 错误3、回溯的【选择和撤销】逻辑错误：在for循环内的pathSum+=cur/pathSum-=cur会导致多次累加/减同一个cur，还有撤销操作无法恢复正确状态
 * 【解法1改进】：根据解法1的改进。注意res变量是全局的。OK了。leetcode勉强通过
 * <p>
 * 【解法2】：动态规划。回溯算法中存在重复计算，动态规划可以使用备忘录消除重叠子问题。
 * 我尝试做dp定义。dp[i]为nums[0...i]的子数组组合成target的方法有多少个。。。我不知道怎么定义。看答案吧
 * 思考：dp定义可能是数组、也可能是一个子函数。可能从头算起，可能从中间算起，可能从尾部往回算
 * labuladong。定义dp函数，dp(start,remain)表示：利用nums[start..]这些元素能够组成remain的方法数量int dp(int[] nums, int start, int remain)
 * 解法3：动态规划优化，转化为 0-1背包问题
 *
 */
public class FindTargetSumWays_494 {
    // 解法3：动态规划优化，转化为 0-1背包问题
    public int findTargetSumWays(int[] nums, int target) {
        // 先不看，这道题花了我太多时间了。
        return -1;
    }

    // 解法2：动态规划
    // 备忘录
    HashMap<String, Integer> dpToResult;

    public int findTargetSumWays2(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }
        dpToResult = new HashMap<>();
        int start = 0;
        return dp(nums, start, target);
    }

    // nums[start..]这些元素能够组成remain的组合值为 dp(nums, start, remain)
    private int dp(int[] nums, int start, int remain) {
        // base case
        if (start == nums.length) {
//            return 0;
            return remain == 0 ? 1 : 0;
        }
        String key = start + "-" + remain;
        if (dpToResult.containsKey(key)) { // 备忘录查询
            return dpToResult.get(key);
        }
        int cur = nums[start];
        int result = dp(nums, start + 1, remain - cur) + dp(nums, start + 1, remain + cur);
        // 备忘录维护
        dpToResult.put(key, result);
        return result;
    }

    // 解法1改进
    int res;

    public int findTargetSumWays1_update(int[] nums, int target) {
//        int res = 0; // 局部变量覆盖了全局变量
        res = 0;
        int start = 0;
        backtrack(nums, target, start);
        return res;
    }

    private void backtrack(int[] nums, int target, int start) {
        // base case
        if (start == nums.length) {
            res += (target == 0 ? 1 : 0); // 当target被削减成0时，刚好是一个组合
            return;
        }
        // 对当前元素，有两个选择，分别是+和-
        int cur = nums[start];
        // 选择+，则目标值要-
//        target -= cur;
        backtrack(nums, target - cur, start + 1);
//        target += cur;
        // 选择-，则目标值要+
//        target += cur;
        backtrack(nums, target + cur, start + 1);
//        target -= cur;
    }


 /*
    // 解法1，回溯
    int[] nums;
    int target;
    int res;

    public int findTargetSumWays(int[] nums, int target) {
        this.nums = nums;
        this.target = target;
        res = 0; // 结果
        int start = 0;
        int pathSum = 0;
//        int pathSum2 = 0; // pathSum1和pathSum2代表加减两种选择
        backtrack(start, pathSum);
        return res;
    }

    private void backtrack(int start, int pathSum) {
        // base case
        if (start == nums.length) {
            // 判断
            return;
        }
        int cur = nums[start];
        if (start == nums.length - 1) {
            // 选择+
            res += (pathSum + cur == target ? 1 : 0);
            // 选择-
            res += (pathSum - cur == target ? 1 : 0);
            return;
        }
        // 选择+
        for (int i = start + 1; i < nums.length; i++) {
            pathSum += cur;
            backtrack(i, pathSum);
            pathSum -= cur;
        }
        // 选择-
        for (int i = start + 1; i < nums.length; i++) {
            pathSum -= cur;
            backtrack(i, pathSum);
            pathSum += cur;
        }
    }
    */

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println(new FindTargetSumWays_494().findTargetSumWays(nums, target));
    }
}
