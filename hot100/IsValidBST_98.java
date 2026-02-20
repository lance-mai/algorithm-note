package hot100;

import com.sun.source.tree.IfTree;

/**
 * 验证二叉搜索树
 * 解法1：前序遍历
 * 部分解答错误，为什么？因为不是只比较该节点与左右子节点的大小，而是比较该节点与左右子树的大小
 * 解决这个问题以后，还是有部分错误。因为校验BST的核心逻辑完全缺失。
 * 核心：递归的时候传入范围[min, max]
 * 要考虑到值溢出场景。比如只有一个节点，恰好等于Integer.MAX_VALUE，因此使用long型。还是不行。
 * 刚开始传入null吧。在递归的时候判断是否为null
 *
 */
public class IsValidBST_98 {
//    boolean isValid;

    public boolean isValidBST(TreeNode root) {
//        isValid = true;
//        traverse(root);
        // 这里的min、max是要求当前节点符合的范围(min, max)，不符合则不是BST
//        int min = Integer.MIN_VALUE;
//        int max = Integer.MAX_VALUE;
        // 验证 BST 的优雅写法 —— 用null代替初始的极值，避开数值溢出的坑
        return dfs(root, null, null);
//        return isValid;
    }

    // 返回以root为根节点的树是否是BST (min, max)，左开右开区间
    private boolean dfs(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
//        boolean isValid = true;
        // 补充：还是缺乏核心逻辑，只校验当前节点和左右子节点，没有比较左右子树
//        if (root.left != null) {
//            isValid = isValid && (root.val > root.left.val);
//        }
//        if (root.right != null) {
//            isValid = isValid && (root.val < root.right.val);
//        }
        // （修正）如果当前节点不为空，校验BST的核心逻辑不能缺失
        if (min != null && root.val <= min) {
            return false;
        }
        if (max != null && root.val >= max) {
            return false;
        }
//        return isValid && dfs(root.left) && dfs(root.right);
        //
        return dfs(root.left, min, root.val) && dfs(root.right, root.val, max);
    }


//    private void traverse(TreeNode root) {
//        if (root == null) {
//            return;
//        }
//        if (root.left != null) {
//            if (root.left.val >= root.val) {
//                isValid = false;
//                return;
//            }
//            traverse(root.left);
//        }
//        if (root.right != null) {
//            if (root.val >= root.right.val) {
//                isValid = false;
//            }
//        }
//    }
}
