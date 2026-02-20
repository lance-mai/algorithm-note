package hot100;

/**
 * 不同路径
 * 解法1：使用回溯。独立做出来了。但是超时。哪里需要剪枝呢？是我忘了维护 visited[][]了，只查询没有维护
 * 应该怎么办呢？我好像做什么剪枝操作。visited不是剪枝操作。
 * 来自豆包：「不同路径」问题中根本不需要 visited 数组，因为路径只能向右 / 向下走，永远不会回头（不会重复访问同一个位置），
 * 你添加的 visited 逻辑完全是多余的
 * 时间复杂度为 O(2^(m+n))
 * 回溯我没大问题。
 * 动态规划 后面多练练，搞定他
 * 解法2：最优解。动态规划！
 * 思路：要解决超时问题，必须放弃回溯法，改用时间为O(m*n)的动态规划
 * 定义（我自己尝试的）：dp[i][j]表示从(0,0)走到(i,j)时有的不同路径数
 * 出错了，感觉遍历的方式不太对。因为只能往右走或者往下走
 * 问一下豆包，找出问题所在。
 * 1、初始化不对，我原来只初始化dp[0][0] = 1。正确的做法是初始化第一行、第一列都为1
 * 2、状态转移方程有问题。我原来的状态转移方程是dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + 1，不应该是取最值，而是要叠加
 * 第二次调整为dp[i - 1][j] + dp[i][j - 1] + 1。不应该+1，为什么？因为从上一个节点到这个节点，并不是新的一条路径。
 * 用豆包的说法就是：统计的是路径数，不是步数
 * 最后调整为正确的方程：dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
 * 整道题就OK了。
 * 2026/02/20 春节初四在杉杉老家，这是我动态规划题目主动做题最接近正确答案的一次。继续加油！！！
 */
public class UniquePaths_62 {
    // 解法2：动态规划
    public int uniquePaths(int m, int n) {
        // base case
        int[][] dp = new int[m][n];
        // 初始化（原来不对）
//        dp[0][0] = 1; // 不对
        // 由于只能往下走或者往右走，因为第一行、第一列都是只有1条路
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        // 状态转移方程
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 我之前错误了： dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + 1;
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(new UniquePaths_62().uniquePaths(3, 7));
    }

    // 解法1：
//    boolean[][] visited;
    int paths;

    public int uniquePaths1(int m, int n) {
//        visited = new boolean[m][n];
        paths = 0;
        int i = 0, j = 0;
        backtrack(m, n, i, j);
        return paths;
    }

    private void backtrack(int m, int n, int i, int j) {
        if (i == m || j == n) {
            return;
        }
//        if (visited[i][j]) {
//            return;
//        }
        if (i == m - 1 && j == n - 1) {
            paths++;
            return;
        }
//        visited[i][j] = true; // 维护备忘录
        backtrack(m, n, i + 1, j);
        backtrack(m, n, i, j + 1);
//        visited[i][j] = false; // 维护备忘录
    }
}
