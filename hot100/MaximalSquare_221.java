package hot100;

/**
 * 最大正方形
 * 解法1：
 * 我的思路：假设矩形为m*n，则遍历窗口长度分别为1到n或m的小正方形，然后逐一去判断是否为全1，最后返回最大的全1正方形
 * 这个思路是暴力解法，而且重复计算太多了。
 * 如何优化呢？
 * 直接看答案吧。
 * labuladong，动态规划。
 * 思路总结：
 * 1、首先是想出dp的定义。dp[i][j]表示以matrix[i][j]为右下角（也可以是左上角等等，为了方便计算，取右下角）的全1正方形的边长
 * 2、处理边界值，由于遍历的是右下角，因此第一行、第一列的元素，其dp值就是其本身，'1'则为1，'0'则为0
 * 3、最后遍历所有dp[i][j]取最大值
 * 解法部分错误，为什么：因为我返回的是边长，但是题目要求为返回面积。修改完以后就ok了
 * 注意审题！！！
 */
public class MaximalSquare_221 {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        // base case
        // 初始化第一行的dp
        for (int j = 0; j < n; j++) {
            dp[0][j] = matrix[0][j] - '0'; // 如果是'0'，则为0，如果是'1'，则为1
        }
        // 初始化第一列
        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0] - '0';
        }
        // 状态转移方程，从1开始
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                    continue;
                }
                dp[i][j] = Math.min( // 水桶效应，取ij左边、上边、左上的dp进行取值
                        dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])
                ) + 1; // 加上本身
            }
        }

        // 最后所有dp[i][j]取最大值
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dp[i][j]);
            }
        }
        return res * res;
    }
}
