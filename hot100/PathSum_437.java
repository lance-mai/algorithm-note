package hot100;

import java.util.HashMap;

/**
 * 路径总和III（不好理解，记住）
 * 解法1：回溯。不从父节点开始，我不知道怎么办。
 * 解法2：labuladong。前缀和+回溯（后序遍历）。居然是前缀和！！！学到了学到了
 * 部分错误，为什么？
 * 1）错误顺序：先更新presumcnt，再统计res。统计res时，需要找“当前节点之前的前缀和（不包括当前节点）”，而我把当前节点的前缀和加入了map
 * 会导致当cursum-targetsum=cursum(即targetsum=0时)，会错误统计当前节点这条路径（重复计数）
 * 2）后序回溯的操作顺序颠倒（导致状态恢复出错）如果选择操作是a->b，那么撤销选择时b->a
 *
 */
public class PathSum_437 {
    int targetSum;
    int res;
    HashMap<Long, Integer> preSumToCnt;

    public int pathSum(TreeNode root, int targetSum) {
        this.targetSum = targetSum;
        res = 0;
        preSumToCnt = new HashMap<>();
        // 初始化
        preSumToCnt.put(0L, 1); // 初始化。。。。。
        traverse(root, 0L);
        return res;
    }

    private void traverse(TreeNode root, Long curSum) {
        if (root == null) {
            return;
        }
        // 前序位置
        curSum += root.val;
        // 看看有多少条路径能凑出 presum + targetsum = curSum，条数叠加到结果中
        res += preSumToCnt.getOrDefault(curSum - targetSum, 0);
        // ！！！不把当前节点统计到res中
        preSumToCnt.put(curSum, preSumToCnt.getOrDefault(curSum, 0) + 1);
        // 回溯
        traverse(root.left, curSum);
        traverse(root.right, curSum);
        // 后序位置（撤销选择时，要和选择时动作反着来）
//        curSum -= root.val;
//        preSumToCnt.put(curSum, preSumToCnt.get(curSum) - 1);
        preSumToCnt.put(curSum, preSumToCnt.get(curSum) - 1);
        curSum -= root.val;
    }
}
