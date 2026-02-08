package hot100;

/**
 * 爬楼梯
 * 解法1：分两棵树，分别递归遍历。
 * 报错了，为什么？ 因为处理add的时候顺序不对
 * 超时了，输入为44时超时，因为遍历太多太深了。怎么办？
 * 问一下豆包：解法1为暴力回溯算法，时间复杂度为 O(2^n)，当n=44时，需要计算2^44遍，那肯定有问题啊
 * 同时，递归过程中反复计算同一个子问题，比如计算climbStairs(44)时，climbStairs(42)会被climbStairs(43)和climbStairs(44)重复计算
 * 这些无意义地重复计算进一步加剧了性能损耗
 * 如何解决：
 * （解法2）1、动态规划，消除重复子问题，将时间复杂度降至O(n)：
 * 爬楼梯本质符合斐波那契数列，使用DP，dynamic planning，
 * 核心思路：
 * 状态定义：dp[i]表示爬到第i阶楼梯的不同方法数
 * 状态转移：dp[i] = dp[i-1] + dp[i-2]，最后一步可从i-1爬一步，或从i-2阶爬两步
 * 初始条件：dp[i]=1,1阶只有1种方法，dp[2] = 2,2阶有1+1或2两种方法
 * （解法3）2、通过状态压缩，进一步将空间复杂度优化到O(1)：
 * 使用数组中两个元素作为变量，不使用整个数组
 */
public class ClimbStairs_70 {
    // 解法3：动态规划（空间压缩版）
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int prePre = 1;
        int pre = 2;
        int cur = 0; // 表示到达第i阶的不同方法数
        for (int i = 3; i <= n; i++) {
            cur = prePre + pre;
            // 注意顺序
            prePre = pre;
            pre = cur;
        }
        return cur;
    }

    // 解法2：动态规划
    public int climbStairs2(int n) {
        // n = 1时，只需要1步；n=2时，可能是1+1，可能是2，两种可能
        if (n <= 2) {
            return n;
        }
        // dp[i]表示第i阶的不同方法数
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        // 从第3阶开始，递推到n
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n]; // 再回顾一下dp[i]定义：表示到达第i阶的不同方法数，因此到达第n阶的不同方法数为 dp[n]
    }

    // 解法1，暴力回溯，O(n^2)，超时
    int total = 0;

    public int climbStairs1(int n) {
        traverse1(1, 0, n);
        traverse1(2, 0, n);
        return total;
    }

    private void traverse1(int add, int sum, int target) {
        sum += add;
        if (sum > target) {
            return;
        }
        if (sum == target) {
            total++;
            return;
        }
        traverse1(1, sum, target);
        traverse1(2, sum, target);
    }

    public static void main(String[] args) {
        System.out.println(new ClimbStairs_70().climbStairs(2));
    }
}
