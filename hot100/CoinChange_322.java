package hot100;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 零钱兑换
 * 解法1：第一反应是贪心算法。但好像又不是，因为要求恰好能凑成目标数字
 * 解法2：可以尝试使用回溯，全排列。感觉不太行
 * 解法3：尝试用动态规划
 * dp[i]定义：
 * dp[n]定义：输入一个目标金额n，返回凑出目标金额n所需最少硬币数
 * 超时了。需要使用备忘录
 * 哈哈哈，备忘录是我自己做出来的，渐渐有点感觉了哈哈哈哈哈，好开心
 */
public class CoinChange_322 {
    // 解法3：动态规划
    HashMap<Integer, Integer> memo = new HashMap<>(); // 备忘录，amount->dp

    public int coinChange(int[] coins, int amount) {
        return dp(coins, amount);
    }

    // 定义：要凑出金额amount，至少需要dp[coins, amount]个硬币
    private int dp(int[] coins, int amount) {
        // base case
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        int res = Integer.MAX_VALUE; // 需要取最小值
        for (int coin : coins) { // 遍历每一个选择
            // 查询备忘录
            int subProblem = memo.containsKey(amount - coin)
                    ? memo.get(amount - coin)
                    : dp(coins, amount - coin);
            // 注意，我没写出来的点：子问题无解时跳过
            if (subProblem == -1) continue;
//            res = Math.min(res, subProblem);
            res = Math.min(res, subProblem + 1); // 这里需要+1，因为包含这一层的一个硬币数
        }
        // 存备忘录
        res = res == Integer.MAX_VALUE ? -1 : res;
        memo.put(amount, res);
        return res;
    }
}
