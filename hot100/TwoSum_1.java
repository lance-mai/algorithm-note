package hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 两数之和
 * 解法1：使用hashmap，得到数组元素和下标的映射，可以提供O(1)的查询速度
 * 注意，查询下标的时候，需要排除当前元素的下标，因为题目规定不能同时使用两次相同的元素，
 * 比如 target=6, nums=[3,2,4]，则下标不是[0,0]，而是[1,2]
 * 解法2：双指针数组。难点：排序后会破坏原来的索引。使用 list<list<integer> 同时记录元素及其索引，并整体排序
 */
public class TwoSum_1 {

    // 解法2：双指针
    public int[] twoSum(int[] nums, int target) {
        // 记录原本数组元素的索引
        ArrayList<ArrayList<Integer>> numAndIdxList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(nums[i]); // 值
            list.add(i); // 索引
            numAndIdxList.add(list);
        }
        // 排序，升序
        numAndIdxList.sort(Comparator.comparingInt(ArrayList::getFirst));
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int compute = numAndIdxList.get(left).getFirst() + numAndIdxList.get(right).getFirst();
            if (compute > target) {
                right--;
            } else if (compute < target) {
                left++;
            } else {
                return new int[]{numAndIdxList.get(left).getLast(), numAndIdxList.get(right).getLast()};
            }
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new TwoSum_1().twoSum(new int[]{3, 2, 4}, 6)));
    }

    // 解法1：hashmap
    public int[] twoSum1(int[] nums, int target) {
        // 需要数组和下标的映射
        HashMap<Integer, Integer> numMapIdx = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            numMapIdx.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int Num = nums[i];
            int anotherNum = target - Num;
            if (numMapIdx.containsKey(anotherNum) && numMapIdx.get(anotherNum) != i) {
                return new int[]{i, numMapIdx.get(anotherNum)};
            }
        }
        return new int[]{-1, -1};
    }
}
