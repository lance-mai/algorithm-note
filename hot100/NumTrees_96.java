package hot100;

import java.util.HashMap;

/**
 * 不同的二叉搜索树
 * 解法1：看蒙了。不会
 * labuladong。定义count(left,right)：闭区间[left,right]能够组成的BST树数量。遍历这个范围，逐个作为根节点，再结合BST特性
 * 其实也是动态规划（递归栈）
 * 参考：https://labuladong.online/zh/algo/data-structure/bst-part3/
 * 解答错误，为什么：
 * 答案是左右子树结果的乘积（结果组合嘛），但是我错搞成了相加：res += (leftCnt + rightCnt);
 * 应该是 res += (leftCnt * rightCnt);
 * left*right是因为左子树的每种结构能和右子树每种结构组合成一个新的BST。外侧的res+= 是为了累加不同根节点对应的组合数
 * 解决完错误后，超时了。原因是：重叠子问题。搞一个备忘录。通过了，OK
 * 解法2：动态规划（递归数组）
 * dp[i]表示用i个不同节点(1~i)能组成的BST数量
 * 状态转移方程想不出来呀：dp[i] += (dp[j - 1] * dp[i - j]); j是根节点，其BST数=左子树(j-1)BST * 右子树(i-j)BST
 */
public class NumTrees_96 {
    // 解法2：动态规划，递归数组
    public int numTrees(int n) {
        // 定义: dp[i]表示从1到i可以组成dp[i]种BST
        int[] dp = new int[n + 1];
        // base case
        dp[0] = 1; // 0个节点，空树，也算BST。dp[0]=1 必须初始化：这是递推的边界条件，没有它整个DP数组都会算错；
//        dp[1] = 1; // 只有一个根节点，组成1个BST。dp[1]=1可以初始化，但没必要
        // 状态转移方程（我想不出来）
        // 遍历所有节点数i: 1~n
        for (int i = 1; i <= n; i++) {
            // 遍历在节点数为i时的所有可能的根节点
            for (int j = 1; j <= i; j++) {
                dp[i] += (dp[j - 1] * dp[i - j]); // j是根节点，其BST数=左子树(j-1)BST * 右子树(i-j)BST
            }
        }
        return dp[n];
    }

    // 备忘录
    HashMap<String, Integer> scopeToCnt;

    // 解法1：动态规划，递归栈
    public int numTrees1(int n) {
        scopeToCnt = new HashMap<>();
        int left = 1;
        int right = n;
        return count(left, right);
    }

    // [left, right] 闭区间
    private int count(int left, int right) {
        // base case
        if (left > right) {
            return 1; // 这是空节点。空节点也算是其中一种BST情况，要算进来
        }
        int res = 0;
        // 查询备忘录
        String key = left + "-" + right;
        if (scopeToCnt.containsKey(key)) {
            return scopeToCnt.get(key);
        }

        for (int root = left; root <= right; root++) {
            // 以root为根节点
            int leftCnt = count(left, root - 1);// 左子树小于root
            int rightCnt = count(root + 1, right); // 右子树大于root
//            res += (leftCnt + rightCnt);
            // left*right是因为左子树的每种结构能和右子树每种结构组合成一个新的BST。外侧的res+= 是为了累加不同根节点对应的组合数
            res += (leftCnt * rightCnt);
        }
        // 维护备忘录
        scopeToCnt.put(key, res);

        return res;
    }
}
