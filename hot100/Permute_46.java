package hot100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 全排列
 * 解法1：回溯算法。不会，没算对，我也在做路径选择，回溯、路径撤销，为什么还不对呢：
 * （注：我犯了很多次相同错误，谨记！）
 * res.add(track); // 这里有问题，添加的是track对象的引用，会导致后续被不断修改。应该固化下来结果
 * 应该使用：res.add(new ArrayList<>(track));
 * <p>
 * 解法2：labuladong。和解法1一样，不过是使用数组来代替map
 */
public class Permute_46 {
    // 解法2：
    public List<List<Integer>> permute(int[] nums) {
        backtrack(new HashSet<>(), nums, new ArrayList<>());
        return res;
    }


    ArrayList<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute1(int[] nums) {
        backtrack(new HashSet<>(), nums, new ArrayList<>());
        return res;
    }

    private void backtrack(HashSet<Integer> used, int[] nums, ArrayList<Integer> track) {
        if (track.size() == nums.length) {
//            res.add(track); // 这里有问题，添加的是track对象的引用，会导致后续被不断修改。应该固化下来结果
            res.add(new ArrayList<>(track));

            return;
        }
        for (int num : nums) {
            if (used.contains(num)) {
                continue;
            }
            used.add(num);
            track.add(num);
            backtrack(used, nums, track);
            used.remove(num);
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(new Permute_46().permute(nums));
    }
}
