package hot100;

import java.util.HashMap;

/**
 * 两数之和
 * 使用hashmap，得到数组元素和下标的映射，可以提供O(1)的查询速度
 * 注意，查询下标的时候，需要排除当前元素的下标，因为题目规定不能同时使用两次相同的元素，
 * 比如 target=6, nums=[3,2,4]，则下标不是[0,0]，而是[1,2]
 */
public class TwoSum_1 {
    public int[] twoSum(int[] nums, int target) {
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
