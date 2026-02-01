package BST;

import java.util.LinkedList;

/**
 * 填充每个节点的下一个右侧节点指针
 * 解法1：层序遍历。层序遍历忘了怎么做了。。。
 * 无限循环了。因为队列永远不为空。我错把root的左右子树插入了queue.offer(root.left);。正确做法是把cur左右子树插入
 * 上面问题解决后，还是报错。原因：层序遍历没有按层处理。同层节点需要单独处理，需要加一个size，记住当前层一共有多少个节点
 * 解法2：能不能用递归遍历呢？如果遍历的时候，将父节点下面的左右子树用指针连接起来，是不够的，因为还要求连接跨父节点的同层子节点
 * 怎么办呢？根据labuladong的解法，将二叉树抽象成三叉树！
 * 抽象过程：关键不是遍历节点，而是遍历节点之间的间隙。入参是两个相邻节点（一个间隙），做的是这个间隙的事儿（把两个节点连接起来），
 * 然后递归遍历这个间隙衍生出的三个子间隙，这三个子间隙，就是三叉树的“三叉”的来源
 */
public class Connect_116 {
    // 解法2：三叉树遍历
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        traverse(root.left, root.right);
        return root;
    }

    // 遍历的是由left、right中间组成的间隙
    private void traverse(Node left, Node right) {
        // 如果left、right中间没有间隙，只要其中一个为null，就没有间隙
        if (left == null || right == null) {
            return;
        }
        // 连接间隙
        left.next = right;
        // 三叉树遍历
        traverse(left.left, left.right);
        traverse(left.right, right.left);
        traverse(right.left, right.right);
    }

    // 解法1：层序遍历
    public Node connect1(Node root) {
        if (root == null) {
            return null;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root); // 插入队列
        while (!queue.isEmpty()) {
            // 记录当前层的节点数，保证只处理当前层
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                // 此处判断逻辑错误
                // if (queue.isEmpty()) {
                //     cur.next = null;
                // } else {
                //     cur.next = queue.peekFirst();
                // }
                if (i == size - 1) { // 该层最后一个节点
                    cur.next = null;
                } else {
                    cur.next = queue.peek();
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return root;
    }
}
