package BST;

/**
 * 二叉树展开为链表（类似于hashmap中的红黑树转链表）
 * 解法1（不会）：使用递归。递归函数定义：返回一个链表，链表头为root
 * 解法2：labuladong。遍历思维。行不通，因为题目要求原地把二叉树拉平成链表
 * 解法3：labuladong。分解问题。函数定义：输入节点root，然后root为根的二叉树就会被拉平成一条链表
 * 1）先利用flatten(x.left)和flatten(x.right)将x的左右子树拉平
 * ~~~ 2）将x的右子树接到左子树下方，然后将整个左子树作为右子树 ~~~ 关键思路，我之前没掌握
 * 报错了：原因1是最后一部cur = right; 只是修改的临时变量，但是没有对真正的指针进行修改
 * 原因2是没有判断左子树是否为空，如果左子树为空，那么root.right 直接拼接原来的右子树就行了
 */
public class Flatten_114 {
    // 解法3：
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        // 利用定义，把左右子树拉平
        flatten(root.left);
        flatten(root.right);
        TreeNode left = root.left;
        TreeNode right = root.right;
        // 将root的右子树设置为x.left，左子树置空
        root.right = root.left;
        root.left = null;
        // 将x.left的最后一个节点接到x.right的头结点
        TreeNode cur = left;
        if (cur != null) { // 仅当左子树非空时，才需要找末尾并拼接
            while (cur.right != null) { // 遍历到当前节点
                cur = cur.right;
            }
            cur.right = right;
        } else { // 左子树为空时，直接将root的右子树设置成原右子树，相当于没操作，可忽略
            root.right = right;
        }
    }


    // 解法1。没做出来
    public void flatten1(TreeNode root) {
        root = getList(root);
    }

    private TreeNode getList(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 前序遍历

        TreeNode leftList = getList(root.left);
        TreeNode rightList = getList(root.right);
        return root;
    }
}
