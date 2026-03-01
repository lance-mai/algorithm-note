package bytedance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TwoSum_1 {
    public int[] twoSum(int[] nums, int target) {
        ArrayList<List<Integer>> numTuples = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            ArrayList<Integer> list = new ArrayList<>(List.of(new Integer[]{i, nums[i]}));
            numTuples.add(list);
        }
        List<List<Integer>> sortedList = numTuples.stream().sorted(Comparator.comparingInt(list -> list.get(1))).toList();
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int leftVal = sortedList.get(left).get(1);
            int rightVal = sortedList.get(right).get(1);
            int computeRes = leftVal + rightVal;
            if (computeRes > target) {
                while (left < right && sortedList.get(right).get(1) == rightVal) right--;
            } else if (computeRes < target) {
                while (left < right && sortedList.get(left).get(1) == leftVal) left++;
            } else {
                return new int[]{sortedList.get(left).get(0), sortedList.get(right).get(0)};
            }
        }
        return null;
    }

    // public int[] twoSum(int[] nums, int target) {
    //     // 先存值和索引
    //     HashMap<Integer, Integer> numToIndex = new HashMap<>();
    //     for (int i = 0; i < nums.length; i++) {
    //         numToIndex.put()
    //     }
    //     Arrays.sort(nums); // 升序
    //     int left = 0, right = nums.length - 1;
    //     while (left < right) {
    //         int leftVal = nums[left];
    //         int rightVal = nums[right];
    //         int computeRes = leftVal + rightVal;
    //         if (computeRes > target) {
    //             while (left < right && nums[right] == rightVal) right--;
    //         } else if (computeRes < target) {
    //             while (left < right && nums[left] == leftVal) left++;
    //         } else {
    //             return new int[]{left, right};
    //         }
    //     }
    //     return null;
    // }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new TwoSum_1().twoSum(new int[]{3, 2, 4}, 6)));
    }
}
