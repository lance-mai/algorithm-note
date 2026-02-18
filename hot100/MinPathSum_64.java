package hot100;

import java.util.Arrays;

/**
 * 最小路径和
 * 解法1：感觉像是回溯算法。或者动态规划。
 * 尝试着用动态规划写一下
 * dp[i]定义：
 * <p>
 * 解法2：直接看答案。
 * 一般来说，在二维矩阵中求最优化问题（最大值或最小值），肯定要用递归+备忘录，也就是动态规划技巧
 * dp函数定义：从左上角位置(0,0)走到位置(i,j)的最小路径和为dp(grid, i, j)
 * <p>
 * labuladong: https://labuladong.online/zh/algo/dynamic-programming/minimum-path-sum/
 * 超时了，需要设置备忘录。取缓存的时候，需要注意取当前i、j对应的缓存，而不是取子状态的缓存
 *
 */
public class MinPathSum_64 {
    // 备忘录
    int[][] memo;

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        memo = new int[m][n];
        for (int[] ints : memo) {
            Arrays.fill(ints, -1);
        }

        return dp(grid, m - 1, n - 1);
    }

    // dp函数定义：从左上角位置(0,0)走到位置(i,j)的最小路径和为dp(grid, i, j)
    private int dp(int[][] grid, int i, int j) {
        // base case
        if (i == 0 && j == 0) {
            return grid[0][0];
        }

        // 如果i、j越界，就返回一个最大值，避免取min的时候取到
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }

        // 关键！！！取缓存
        if (memo[i][j] != -1) return memo[i][j];

//        return Math.min(
//                dp(grid, i - 1, j),
//                dp(grid, i, j - 1)
//        ) + dp(grid, i, j);
        // 易错点，不是+dp(grid, i, j)，而是+grid[i][j]

        // 这里取缓存点有问题。取缓存点应该前置，取当前i、j的缓存
//        int right = memo[i - 1][j] == -1 ? dp(grid, i - 1, j) : memo[i - 1][j];
//        int down = memo[i][j - 1] == -1 ? dp(grid, i, j - 1) : memo[i][j - 1];
        memo[i][j] = Math.min(
                dp(grid, i - 1, j),
                dp(grid, i, j - 1)
        ) + grid[i][j];
        return memo[i][j];
    }
}
