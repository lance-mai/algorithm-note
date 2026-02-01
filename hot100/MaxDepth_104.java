package hot100;

/**
 * 二叉树的最大深度
 * 解法1：（没解出来）通过子树的最大深度得到二叉树的最大深度，属于问题分解。
 * 为什么我没做出来，是因为最后返回 Math.max(leftDepth, rightDepth)时，没有+1，没有考虑加上该层的深度。
 * 解法2：labuladong遍历算法，遍历二叉树计算答案，一般函数命名为 void traverse()
 * 为什么要在前序位置depth++，后序位置depth--呢？因为前序位置是进入一个节点的时候，后序位置是离开一个节点的时候
 * depth是记录了当前递归到的节点深度，可以把traverse理解成二叉树上游走的一个指针
 * 解法3：labuladong分解问题算法，二叉树的最大深度可以通过子树的最大深度推导出来。这就是分解问题计算答案的思路
 * 和我的解法1类似
 *
 */
public class MaxDepth_104 {
    // 解法3，分解问题：输入一个节点，返回以该节点为根的二叉树的最大深度
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 利用定义，计算左右子树的最大深度
        int leftMax = maxDepth(root.left);
        int rightMax = maxDepth(root.right);
        // 根据左右子树的最大深度推出原二叉树的最大深度
        // 整棵树的最大深度等于左右子树的最大深度取最大值 + 根节点深度（1）
        return Math.max(leftMax, rightMax) + 1;
    }


    // 记录遍历到的节点的深度
    int depth = 0;
    // 记录最大深度
    int maxDepth = 0;

    // 解法2
    public int maxDepth1(TreeNode root) {
        traverse(root);
        return maxDepth;
    }

    // 遍历二叉树
    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        // 前序遍历位置（进入节点时）增加深度
        depth++;
        // 遍历到叶子结点(即左右子节点为空)时记录最大深度
        if (root.left == null && root.right == null) {
            maxDepth = Math.max(depth, maxDepth);
        }
        traverse(root.left);
        traverse(root.right);
        // 后序遍历位置（离开节点）减少深度
        depth--;
    }

    // 解法1：我的解法，问题分解
    // public int maxDepth(TreeNode root) {
    //     if (root == null) {
    //         return 0;
    //     }
    //     int leftDepth = maxDepth(root.left);
    //     int rightDepth = maxDepth(root.right);
    //     return Math.max(leftDepth, rightDepth) + 1;
    // }
}
