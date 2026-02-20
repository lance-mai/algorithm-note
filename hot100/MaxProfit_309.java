package hot100;

/**
 * 买卖股票的最佳时机含冷冻期
 * 解法1：第一次读题都没读懂！QAQ
 * 动态规划。dp[i]定义：第i天获得的利润为dp[i]
 * 冷冻期不知道怎么处理。
 * <p>
 * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
 * dp[i][0]表示第i天属于不持股状态的最大利润
 * dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i])
 * dp[i][1]表示第i天属于持股状态的最大利润
 * - 为什么不是计算差价？
 * - 因为差价已经在两个操作中分别体现了：
 * - 当你 buy 时，你已经用 -prices[i] 记录了成本
 * - 当你 sell 时，你用 +prices[i] 记录了收入
 * ok，搞出来了，yes！！！
 */
public class MaxProfit_309 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // base case
        int[][] dp = new int[n + 2][2]; // 因为状态转移方程中有dp[i-2]，因此将dp的长度+2; dp[..][0/1] 0和1分别代表不持股和持股
        dp[0][0] = 0; // 股票卖出状态，第-2天，利润为0
        dp[0][1] = Integer.MIN_VALUE; // 股票持有状态，第-2天，不可能有利润，但是后面要求最大利润，因此赋初值为最小值
        dp[1][0] = 0; // 同理
        dp[1][1] = Integer.MIN_VALUE; // 同理
        // 状态转移方程
        for (int i = 2; i < n + 2; i++) { // 为了照顾dp[i-2]，因此循环从i=2开始
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i - 2]); // 前一天不持有（今天继续不持有），或者前一天持有，今天卖出（不持有）
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i - 2]); // 前一天持有（今天继续持有），或者 前2天卖出（一天冷冻期），今天买入
        }
        return Math.max(dp[n + 1][0], dp[n + 1][1]);
    }

//    public int maxProfit(int[] prices) {
//        int n = prices.length;
//        // base case
//        int[] dp = new int[n];
//        dp[0] = 0; // 第一天，只能是买入、不买，不可能卖出，因此利润为0
//        for (int i = 1; i < n; i++) {
//            dp[i] = Math.max(dp[i - 1], dp[i - 1] + prices[i] - prices[i - 1]);
//        }
//        return dp[n - 1];
//    }
}
