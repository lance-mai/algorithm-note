package labuladong.prefixsum;

/**
 * 矩阵区域和 1314
 * 错题笔记：
 * 1、int row2 = Math.min(m - 1, i + k); int col2 = Math.min(n - 1, j + k); 其中的 j+k错写成了i+k
 * 2、answer矩阵的大小判断：题目中可以得出，answer[i][j]是以原矩阵中位置(i,j)为中心，上下左右扩展k步区域内所有元素的和
 * 相当于一个固定的边长为2k+1的正方形窗口在原矩阵mat上移动，因此得出answer矩阵和mat的大小一样
 */
public class MatrixBlockSum_1314 {
    int[][] answer;

    private int[][] computePreSum(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] preSum = new int[m + 1][n + 1];
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] + mat[i - 1][j - 1] - preSum[i - 1][j - 1];
            }
        }
        return preSum;
    }

    public int[][] matrixBlockSum(int[][] mat, int k) {
        // 0 <= r-k <= i <= r+k
        // 0 <= c-k <= j <= c+k
        int[][] preSum = computePreSum(mat);
        int m = mat.length;
        int n = mat[0].length;
        this.answer = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int row1 = Math.max(0, i - k);
                int col1 = Math.max(0, j - k);
                int row2 = Math.min(m - 1, i + k);
                int col2 = Math.min(n - 1, j + k);
                answer[i][j] = preSum[row2 + 1][col2 + 1] + preSum[row1][col1] - preSum[row2 + 1][col1] - preSum[row1][col2 + 1];
            }
        }
        return answer;
    }

}
