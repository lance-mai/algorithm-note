package hot100;

/**
 * 买卖股票的最佳时机
 * 解法1：暴力解法，O(n^2)
 * 解法2：大概是使用栈。先看答案吧。
 * 每天最大的获利 = 当天 - 今天之前的最小值
 * 最大获利 = max(每天最大获利) O(N)
 */
public class MaxProfit_121 {
    public int maxProfit(int[] prices) {
        // 算出第i天之前的元素的最小值
        int[] minArr = new int[prices.length];
        // int min = Integer.MAX_VALUE;
        // for (int i = 0; i < prices.length; i++) {
        //     min = Math.min(min, prices[i]);
        //     minArr[i] = min;
        // }
        // int maxProfit = 0;
        // for (int i = 0; i < prices.length; i++) {
        //     maxProfit = Math.max(maxProfit, prices[i] - minArr[i]);
        // }

        // 合并起来写
        int min = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            minArr[i] = min;
            maxProfit = Math.max(maxProfit, prices[i] - minArr[i]);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(new MaxProfit_121().maxProfit(prices));
    }
}
