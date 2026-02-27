package bytedance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 * 解法1：排序，双指针
 */
public class ThreeSum_15 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); // 升序
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int cur = nums[i];
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int leftNum = nums[left];
                int rightNum = nums[right];
                int compute = cur + leftNum + rightNum;
                if (compute < 0) {
                    while (left < right && leftNum == nums[left]) {
                        left++;
                    }
                } else if (compute > 0) {
                    while (left < right && rightNum == nums[right]) {
                        right--;
                    }
                } else {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(leftNum);
                    list.add(rightNum);
                    res.add(list);
                    // 得到答案以后，需要跳过相同的数
                    while (left < right && leftNum == nums[left]) {
                        left++;
                    }
                    while (left < right && rightNum == nums[right]) {
                        right--;
                    }
                }
                while (i < n - 1 && nums[i] == nums[i + 1]) {
                    i++;
                }
            }

        }
        return res;
    }
}
