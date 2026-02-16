package hot100;

/**
 * 岛屿数量
 * 解法1：我能想到的是，连在一起的陆地组成一个岛屿，其他的都是水。找到一个陆地，看其上下左右各一个点是不是陆地
 * 但是不能再进一步了
 * 解法2：labuladong，岛屿系列题目的核心考点是用dfs/bfs算法遍历二维数组
 * 二维矩阵中如何使用DFS搜索呢？如果把二维矩阵的每一个位置当做一个节点，这个节点的上下左右四个位置就是相邻节点
 * 那么整个矩阵可以抽象成一个网状的图结构
 * 对于这道题，重点是如何寻找并标记“岛屿”：遍历每一个节点，当节点为陆地（1）时，标记找到一个岛屿，并通过dfs
 * 将和这个陆地连在一起的陆地淹没（0），这样就不用在下次遍历另一个节点时重复访问了
 * 如果把陆地淹没，就不用visited数组标记了
 *
 */
public class NumIslands_200 {
    public int numIslands(char[][] grid) {
        int m = grid.length; // 行
        int n = grid[0].length; // 列
        int islands = 0;
        // boolean[][] visited = new boolean[m][n]; // 如果把陆地淹没，就不用visited数组标记了
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char cur = grid[i][j];
                if (cur == '1') { // 如果该节点是陆地，则岛屿+1
                    islands++;
                    // 将该陆地及与之连在一起的陆地全部淹没（置0）
                    dfs(grid, i, j);
                }
            }
        }
        return islands;
    }

    private void dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        // 如果节点越界则返回
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return;
        }
        // 如果节点被访问过，也直接返回
        // if (visited[i][j]) {  // 如果把陆地淹没，就不用visited数组标记了
        //     return;
        // }
        // visited[i][j] = true; // 表示这个节点已经被访问过了

        // 如果节点不是陆地，直接返回
        if (grid[i][j] == '0') {
            return;
        }
        // 如果节点是陆地，则淹没，并访问其上下左右四个节点，并淹没陆地节点
        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }

    public static void main(String[] args) {
        // char[][] grid = {
        //         {'1', '1', '1', '1', '0'},
        //         {'1', '1', '0', '1', '0'},
        //         {'1', '1', '0', '0', '0'},
        //         {'0', '0', '0', '0', '0'}
        // };
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println(new NumIslands_200().numIslands(grid));
    }
}
