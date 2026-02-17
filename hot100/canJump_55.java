package hot100;

/**
 * 跳跃游戏
 * 解法1：× 好像没啥算法。直接硬怼 O(N)
 * 注意：元素的值代表的是最大长度，不是特定长度
 * 感觉要使用动态规划
 * 解法2：√ labuladong。贪心算法。
 * 贪心算法和动态规划、回溯区别：动态规划的备忘录优化了为了避免重复计算，回溯的减枝是提前排除不可能答案。这两者均需要穷举
 * 而贪心算法不需要完整地穷举，就可以推导出最优解。
 * 谁走得远，就选择谁
 * 贪心的本质是 “每一步都选最优解，最终得到全局最优”。这里的 “最优” 就是：遍历数组时，始终记录当前能跳到的最远距离。
 * 只要遍历过程中，这个 “最远距离” 能覆盖到最后一个位置，就返回 true；
 * 如果遍历中发现 “最远距离” 连当前位置都跳不过（比如碰到 0 卡住），就返回 false。
 * 解法3：动态规划。leetcode题解
 * dp[i]定义：能够达到数组的第i个位置，true表示可以，false表示不可以
 */
public class canJump_55 {
    // 解法3：动态规划。效率低
    public boolean canJump(int[] nums) {
        int n = nums.length;
        // dp[i]表示能否到到第i个节点，true代表可以，false代表不可以
        boolean[] dp = new boolean[n];
        // 初始化，刚开始就在第一个节点，当然能够到达
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            if (dp[i]) { // 仅当能够进入该节点，才处理该节点
                int steps = nums[i];
                for (int j = 1; j <= steps; j++) {
                    int next = j + i;
                    if (next >= n - 1) { // 这里能够提前到达重点，因此直接返回
                        return true;
                    } else {
                        dp[next] = true; // 标记为可达
                    }
                }
            }
        }
        return dp[n - 1];
    }

    // 解法2：贪心算法，效率高
    public boolean canJump2(int[] nums) {
        int fastest = 0;
        int n = nums.length;
        // 遍历到倒数第二个，只要该位置至少能向前一步就ok。倒数第一个没有必要，因为那时候问题已经解决了
        for (int i = 0; i < n - 1; i++) {
            fastest = Math.max(fastest, i + nums[i]);
            if (fastest <= i) { // 不能向前进，代表卡死
                return false;
            }
        }
        return fastest >= n - 1; // 能覆盖到最后一个下标，代表成功
    }


    public static void main(String[] args) {
        int[] nums = {2, 0};
        System.out.println(new canJump_55().canJump(nums));
    }
}
