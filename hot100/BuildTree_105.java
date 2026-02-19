package hot100;

import java.util.HashMap;

/**
 * 从前序遍历和中序遍历构造二叉树
 * 解法1：构造二叉树，需要中序遍历 + 前序或后序
 * 参考labuladong：https://labuladong.online/zh/algo/data-structure/binary-tree-part2/
 * 先画出前序后序的根节点、左右子树的位置，再进行公式运算
 * 先通过preorder确定根节点值，再去查找其在inorder中的位置。由于题目中要求元素不重复，因此可以使用hash来代替for循环，加快速度
 * inorder中很好确定左右子树的范围以及节点数。根据节点数，再去preorder中确定左右子树的范围
 *
 */
public class BuildTree_105 {
    HashMap<Integer, Integer> numToIdx;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 初始化hash，为后面搜索index提供快捷索引
        numToIdx = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            numToIdx.put(inorder[i], i);
        }

        return build(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1); // [left, right] 左闭右闭
    }

    private TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        // base case
        if (preStart > preEnd) {
            return null;
        }
        // 确定根节点
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        // 需要确定根节点在中序遍历中的位置
        int index = numToIdx.get(rootVal);
//        int index = 0;
//        for (int i = inStart; i <= inEnd; i++) {
//            if (inorder[i] == rootVal) {
//                index = i;
//                break;
//            }
//        }

        // 左右子树的节点数量
//        int leftCnt = index - inStart; // index - 1 - inStart + 1
//        int rightCnt = inEnd - index;  // inEnd  - (index + 1) + 1
        // 确定左右子树
        // x - (prestart + 1) + 1 = leftCnt = index - instart => x = index - instart + prestart
        TreeNode left = build(preorder, preStart + 1, index - inStart + preStart,
                inorder, inStart, index - 1);
        //
        TreeNode right = build(preorder, index - inStart + preStart + 1, preEnd,
                inorder, index + 1, inEnd);
        root.left = left;
        root.right = right;
        return root;
    }
}
