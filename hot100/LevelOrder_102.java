package hot100;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层序遍历
 * 解法1：尝试自己来。使用队列
 * 如何标记每一层，使用size，我忘了，导致无限循环。（后来发现不是这样）
 * 有两个问题：
 * 1、压入子节点的时候，应该使用cur，但是我误用了root
 * 2、在最前面没有对root判空，导致NPE
 */
public class LevelOrder_102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root == null) {
            return res;
        }
        // 压入一个节点
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size(); // 定义size，确定每一层
            ArrayList<Integer> levelList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                levelList.add(cur.val);
//                if (root.left != null) queue.offer(root.left);
//                if (root.right != null) queue.offer(root.right);
                // 搞错了，不是root，是cur
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(levelList);
        }
        return res;
    }
}
