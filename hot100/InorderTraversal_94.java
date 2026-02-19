package hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的中序遍历
 */
public class InorderTraversal_94 {
    ArrayList<Integer> res = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return res;
        }
        inorderTraversal(root.left);
        res.add(root.val);
        inorderTraversal(root.right);
        return res;
    }
}
