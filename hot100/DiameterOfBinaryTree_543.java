package hot100;

/**
 * 二叉树的直径
 * 解法1：分解问题。递归函数定义：二叉树的。。。好像分解不了
 * 解法2：（没解出来）根据labuladong的提示，解决这道题关键，每条二叉树的直径长度，就是一个节点的左右子树的最大深度之和
 * 那分解问题就好办了。--》 没写出来。因为没体现出最大深度的解决方案
 * 解法3：labuladong O(n^2)。解决问题关键：每条二叉树的直径长度，就是一个节点长度的左右子树的最大深度之和
 * 由于不一定要经过根节点，因此将所有的直接都求出来，然后取一个最大值即可
 * 这个解法OK，但是运行时间很长，因为traverse遍历每个节点时，还会调用递归函数maxDepth，而这个递归函数是要遍历子树的所有节点的，
 * 因此时间复杂度为O(n^2)。有没有更好的办法呢？
 * 解法4：labuladong O(n)。提升性能
 * 因为前序位置无法获取子树的信息，所以只能让每个节点调用maxDepth函数取计算子树的深度
 * 改为后序？使得，我们应该把计算直径的逻辑放在后序位置，准确说应该是放在maxDepth的后序位置，
 * 因为maxDepth的后序位置是知道左右子树的最大深度的。
 */
public class DiameterOfBinaryTree_543 {
    // 记录最大直径的长度
    int maxDiameter = 0;

    // 解法4：后序遍历位置
    public int diameterOfBinaryTree(TreeNode root) {
        int depth = maxDepth(root);
        return maxDiameter;
    }

    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMax = maxDepth(root.left);
        int rightMax = maxDepth(root.right);
        // 因为是后序遍历，因此这时候可以知道左子树和右子树的最大深度。那就可以计算最大直径了
        maxDiameter = Math.max(maxDiameter, leftMax + rightMax);
        return Math.max(leftMax, rightMax) + 1;
    }


    // 解法3：
    // 注意不一定要经过root节点
    public int diameterOfBinaryTree3(TreeNode root) {
        // 对每一个节点都计算直径，计算最大直径
        traverse(root);
        return maxDiameter;
    }

    // 遍历二叉树
    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        // 对每个以子节点作为root的树的最大深度
        int leftMax = maxDepth3(root.left);
        int rightMax = maxDepth3(root.right);
        int curDiameter = leftMax + rightMax;
        // 更新全局最大直径
        maxDiameter = Math.max(maxDiameter, curDiameter);

        // 遍历左子树、右子树。疑问：为什么是前序遍历呢？ 因为刚进入该节点时就要计算该节点的左右子树的最大深度
        traverse(root.left);
        traverse(root.right);
    }

    // 计算最大深度。分解子问题的方式
    private int maxDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepthMax = maxDepth3(root.left);
        int rightDepthMax = maxDepth3(root.right);
        // 后序遍历，因为需要得到子树的结果
        return Math.max(leftDepthMax, rightDepthMax) + 1;
    }

    // 求以root为根节点的二叉数的最大深度
    // int maxDepth = 0;
    // int depth = 0;
    //
    // private void traverse(TreeNode root) {
    //     if (root == null) {
    //         return;
    //     }
    //     depth++;
    //     int leftMax = traverse(root.left);
    //     int rightMax = traverse(root.right);
    //     maxDepth = Math.max(depth, maxDepth);
    //     depth--;
    // }


    // 解法2：返回左右子树的最大深度之和
    public int diameterOfBinaryTree2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = diameterOfBinaryTree(root.left);
        int right = diameterOfBinaryTree(root.right);
        return left + right;
    }
}
