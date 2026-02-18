package hot100;

/**
 * 搜索二维矩阵II
 * 解法1：从左上角开始。先搜行，再搜列。时间复杂度 O(m+n) 不太会
 * 解法2：直接看答案。
 * 从右上角开始。规定只能向下向左移动。向下是增大，向左是减小
 *
 */
public class SearchMatrix_240 {
    // 解法2
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        // 双指针，右上角开始
        int x = 0, y = n - 1;
        while (x < m && y >= 0) {
            if (matrix[x][y] == target) {
                return true;
            }
            if (matrix[x][y] < target) { // 需要往下，找更大的值
                x++;
            } else { // 需要往左，找更小的值
                y--;
            }
        }
        return false; // 最后都没找到，那就false
    }

    public boolean searchMatrix1(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 双指针
        int x = 0, y = 0;
        while (x < m && y < n && matrix[x][y] < target) {
            x++;
        }
        if (x > 1 && matrix[x - 1][y] == target) {
            return true;
        }
        while (x < m && y < n && matrix[x][y] < target) {
            y++;
        }
        if (y > 1 && matrix[x][y - 1] == target) {
            return true;
        }
        return false;
    }
}
