package hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合总和
 * 解法1：这道题不能用动态规划，因为需要列出所有组合，而不只是求组合数。
 * 使用回溯。
 * 问题1，返回顺序不一样但数字组成一样的多个数组了。应该去重。怎么去重呢？
 * 手段1：得到结果后，先进行去重，然后返回，如何？在结果中去重也是个麻烦事事儿
 * 手段2：组合的时候按照从小到达（不严格升序）来选，其他的丢弃掉，这样就能在组合的时候结果唯一
 * 实践证明，这是ok的。
 * 我独立做出来啦！！！！！！哈哈哈哈，纪念一下 2026/02/20 在杉杉老家，准备跳槽中。
 */
public class CombinationSum_39 {
    List<List<Integer>> res;
    int target;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.res = new ArrayList<>();
        this.target = target;
        ArrayList<Integer> track = new ArrayList<>();
        int sum = 0;
        backtrack(candidates, track, sum);
        // 去重
//        deduplicate();
        return res;
    }

    private void backtrack(int[] candidates, ArrayList<Integer> track, int sum) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            res.add(new ArrayList<>(track));
            return;
        }
        // if sum < target
        for (int can : candidates) {
            // 升序，去重
            if (!track.isEmpty() && can < track.get(track.size() - 1)) {
                continue;
            }

            track.add(can);
            sum += can;
            backtrack(candidates, track, sum);
            sum -= can;
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] cans = {2, 3, 6, 7};
        int target = 7;
//        int[] cans = {2, 3, 5};
//        int target = 8;
        System.out.println(new CombinationSum_39().combinationSum(cans, target));
    }
}
