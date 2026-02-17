package hot100;

/**
 * 旋转图像
 * 解法1：如果不是原地旋转，新创建一个矩阵，是ok的。O(N^2)
 * 解法2：要求原地旋转，那就不能重新创建一个矩阵。如果不是n*n方阵，是无法做到原地旋转的。因为需要用到矩阵转置
 * 怎么做呢？
 * 先转置，再反转每行
 *
 */
public class Rotate_48 {
    // 解法2：原地旋转
    public void rotate(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        // 1、先转置(交换行和列，方针可以原地转置)
        for (int i = 0; i < rows; i++) {
            for (int j = i; j < cols; j++) { // j从i开始，避免重复计算
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        // 2、再反转每一行
        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < (cols - 1) / 2; j++) { // 这里的条件有问题。why? 因为 j<xx，不是j<=xxx
            for (int j = 0; j < cols / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][cols - j - 1];
                matrix[i][cols - j - 1] = tmp;
            }
        }
    }

    // 解法1：新创建一个矩阵
    public void rotate1(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] newMatrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) { // 遍历原矩阵每一行，填充到新矩阵每一列
            for (int j = 0; j < cols; j++) {
                newMatrix[j][rows - i - 1] = matrix[i][j];
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = newMatrix[i][j];
            }
        }
    }
}
