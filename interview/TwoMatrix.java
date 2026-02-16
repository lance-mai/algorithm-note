package interview;

// 上海大裂谷公司
public class TwoMatrix {

    // 两个矩阵相乘 2*3 x 3*2 => 2*2
    public static void main(String[] args) {
        int[][] matrixA = {
                {1, 2, 3},
                {4, 5, 6}
        };
        int[][] matrixB = {
                {7, 8},
                {9, 10},
                {11, 12}
        };
        int[][] result = multiplyMatrices(matrixA, matrixB);

        // print
        if (result != null) {
            for (int[] row : result) {
                for (int val : row) {
                    System.out.println(val + " ");
                }
                System.out.println();
            }
        }
    }

    private static int[][] multiplyMatrices(int[][] matrixA, int[][] matrixB) {
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int rowsB = matrixB.length;
        int colsB = matrixB[0].length;
        if (colsA != rowsB) {
            System.out.println("error，无法相乘。");
            return null;
        }
        int[][] matrixC = new int[rowsA][colsB];
        // 三层循环
        // 第一层：遍历A的每一行
        for (int i = 0; i < rowsA; i++) {
            // 第二层：遍历B的每一列
            for (int j = 0; j < colsB; j++) {
                // 第三层：计算点积并求和
                for (int k = 0; k < colsA; k++) {
                    matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return matrixC;
    }
}
