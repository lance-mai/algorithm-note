package hot100;

import java.util.Stack;

/**
 * 验证二叉搜索树的后序遍历序列
 * 我的思路：后序遍历顺序：左子树 -》 右子树 -》 根节点
 * 所以逆向后序遍历就是 根节点 -》 右子树 -》 左子树
 * 可以使用单调栈来解决。逆向遍历后序数组，维护递增的右子树节点，用root记录当前子树根节点
 * 如果遇到比root打的节点，违反BST规则，返回false
 * 解法1：看答案的。
 */
public class VerifyTreeOrder_152 {
    public boolean verifyTreeOrder(int[] postorder) {
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MAX_VALUE; // 这是一个初始化的根节点，原来的所有树节点都小于它的，都在他左边
        for (int i = postorder.length - 1; i >= 0; i--) {
            if (postorder[i] >= root) return false; // 左子树，不可能大于等于根节点
            while (!stack.isEmpty() && stack.peek() > postorder[i]) {// 右子树，弹出
                root = stack.pop(); // 作为新的左子树上界
            }
            // 将当前节点入栈：作为后续节点的“右子树候选”
            stack.push(postorder[i]);
        }
        // 遍历完成，所有节点符合BST规则
        return true;
    }
}
