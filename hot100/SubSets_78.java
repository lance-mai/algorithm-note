package hot100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 子集
 * 解法1：回溯。没有做对。为什么?
 * 豆包分析问题原因：
 * 1、终止条件错误。子集问题没有严格的终止条件。子集可以是空集、任意长度子集
 * 应该怎么做：每次进入backtrack都加入到res中。index越界时返回。
 * 2、回溯选择的起点错误了。我每次都从0开始。实际上应该从index开始
 * 3、滥用used数组。完全没必要，只要我每次遍历都从index开始，就不会重复选择元素
 * <p>
 * 还是有问题：递归调用时索引参数传递错误，导致子集重复且无限递归。
 * × backtrack(nums, track, i);
 * √ backtrack(nums, track, i+ 1);
 * 如果传i，那下一个backtrack依然从i开始遍历，导致无限循环。OK了
 * <p>
 * 总结一下：
 * 1、什么时候需要使用used[]标记已使用元素：used主要作用是标记已经被选过的元素，避免同一轮递归中重复选择同一个元素
 * 是否需要用，取决于 【元素是否可重复选】+【子集/排列是否要求无序】
 * 如果求排列，那就必须要用，因为排列有序，不可重复选
 * 如果求子集/组合(无重复元素的子集和组合)，则不需要使用。可以通过从index开始遍历，无需标记
 * 如果求子集/组合(有重复元素，需去重)，必须用，
 * 2、什么时候循环内部/外部做 【选择】+【撤销选择】
 * 1）循环内：适用于大部分场景。排列、组合、子集、分割等问题，每一步都有多个可选元素，需遍历所有可选元素，逐个尝试 选->递归->撤销
 * 2）循环外：少数特殊场景，如二叉树路径，固定选择顺序问题，每一个只有一个固定选择，先选这个元素，再遍历剩余可选元素
 *
 */
public class SubSets_78 {
    List<List<Integer>> res;
//    boolean[] used;

    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
//        used = new boolean[nums.length];
        ArrayList<Integer> track = new ArrayList<>();
        int index = 0;
        backtrack(nums, track, index);
        return res;
    }

    private void backtrack(int[] nums, ArrayList<Integer> track, int index) {
        res.add(new ArrayList<>(track));
        if (index >= nums.length) {
            return;
        }
//        track.add(nums[index]);
        // 遍历的时候再加index到track中
        for (int i = index; i < nums.length; i++) {
//            if (used[i]) {
//                continue;
//            }
//            used[i] = true;
            int cur = nums[i];
            track.add(cur); // 当前index对应的元素已经传入了，需要回溯下一个，因此是index+1位置
            backtrack(nums, track, i + 1);
            track.removeLast();
//            used[i] = false;
        }
        // 这里需要撤销选择
//        track.removeLast();
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(new SubSets_78().subsets(nums));
    }
}
