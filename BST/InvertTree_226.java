package BST;

/**
 * 翻转二叉树
 * 解法1：分解子问题。定义一个函数，有返回值。函数定义为：翻转一个二叉树，返回该二叉树的根节点
 * 解法2：递归遍历。前序遍历，遍历到该节点的时候，就翻转左右子树
 * 前中后序遍历都可以
 */
public class InvertTree_226 {
    // 解法2
    public TreeNode invertTree(TreeNode root) {
        traverse(root);
        return root;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        // 前序遍历
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        traverse(root.left);
        traverse(root.right);
    }

    // 解法1
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree1(root.left);
        TreeNode right = invertTree1(root.right);
        // 翻转
        root.left = right;
        root.right = left;
        return root;
    }
}
