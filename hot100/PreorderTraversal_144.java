package hot100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的前序遍历
 * 解法1：遍历所有节点
 * 解法2：能不能通过分解问题来做呢。原二叉树的前序遍历节点都是左右子树前序遍历节点集合 + root本身的值
 * 本来我以为不行的，但是我看到labuladong说可以，因此我继续尝试一下。
 * 因为是前序遍历，所以先加root本身的节点，再去组合
 * 解法2不太常见，因为其复杂度取决于语言特性，java中的List.addAll复杂度是O(N)，所以总体最坏时间复杂度
 * 达到 O(n^2)。自己倒是可以实现O(1)的List.addAll算法，使用链表就OK，因为多条链表使用简单的指针就能连接
 * <p>
 * 总结：遇到一个二叉树的题目时通用思考过程如下
 * 1、是否可以通过遍历一遍二叉树得到答案？如果可以，就是用一个 void traverse()函数配合外部变量来实现
 * 2、是否可以定义一个递归函数，通过子问题（子树）的答案推导出原问题的答案？如果可以，写出这个递归函数的定义，
 * 并充分利用这个函数的返回值
 *
 */
public class PreorderTraversal_144 {
    // 原二叉树的前序遍历节点 = root本身节点 + 左子树前序遍历节点 + 右子树前序遍历节点
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        LinkedList<Integer> result = new LinkedList<>();
        // 这个顺序很重要
        result.add(root.val);
        result.addAll(preorderTraversal(root.left));
        result.addAll(preorderTraversal(root.right));
        return result;
    }

    ArrayList<Integer> result = new ArrayList<>();

    public List<Integer> preorderTraversal1(TreeNode root) {
        traverse(root);
        return result;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        traverse(root.left);
        traverse(root.right);
    }
}
