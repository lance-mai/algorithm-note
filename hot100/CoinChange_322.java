package hot100;

import java.util.HashMap;

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

    /*
     我自己尝试第二遍做。20260224
     没做对，为什么：
     1、子问题中比较最小值时忘了+1：res = Math.min(res, subProblem + 1);
     2、存备忘录和返回值的顺序没正确
     3、忘了这个：if (subProblem == -1) continue;
        这个不是剪枝，而是影响到结果正确性。当子问题为-1时，说明找不到
        当子问题为-1时，说明amount-coin无法凑出，所以不要和res比较最小值了
     */
    HashMap<Integer, Integer> coinsToAmount;

    public int coinChange(int[] coins, int amount) {
        coinsToAmount = new HashMap<>();
        return dp(coins, amount);
    }

    private int dp(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subProblem = coinsToAmount.containsKey(amount - coin)
                    ? coinsToAmount.get(amount - coin)
                    : dp(coins, amount - coin);
            // 当子问题为-1时，说明amount-coin无法凑出，所以不要和res比较最小值了
            if (subProblem == -1) continue;
            res = Math.min(res, subProblem + 1); // 这里需要+1，因为子问题加上本层递归
        }

        // coinsToAmount.put(amount, res);
        // return res == Integer.MAX_VALUE ? -1 : res;
        res = res == Integer.MAX_VALUE ? -1 : res;
        coinsToAmount.put(amount, res);
        return res;
    }


    // 解法3：动态规划
    HashMap<Integer, Integer> memo = new HashMap<>(); // 备忘录，amount->dp

    public int coinChange1(int[] coins, int amount) {
        return dp1(coins, amount);
    }

    // 定义：要凑出金额amount，至少需要dp[coins, amount]个硬币
    private int dp1(int[] coins, int amount) {
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
