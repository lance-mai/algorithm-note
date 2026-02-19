package hot100;

import java.util.Arrays;

/**
 * 在排序数组中查找元素的第一个和最后一个位置（数组为非严格升序）
 * 解法1：首先看是否有目标值，没有就直接返回。要求O(logn)，那就要使用二分法
 * 按照一般二分法，只能找到其中一个目标值，但是要找首尾两个目标值，可能需要技巧。
 * 直接看答案吧。根据labuladong，需要分别寻找目标值的左边界和有边界。可以通过“匹配目标值时，往左或者往右收缩”来得到左/右边界
 * 在边界处理上有点问题。我不知道怎么处理 target不存在的情况
 * 1、找左边界时，因为一直往左收缩，所以判断left是否越界。右边界同理
 * 2、找左边界时，判断左边界的值是否等于target，不等于则不存在
 */
public class SearchRange_34 {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        Arrays.fill(res, -1);
        res[0] = leftBound(nums, target);
        res[1] = rightBound(nums, target);
        return res;
    }

    private int leftBound(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                // 找到目标值，但要找左边界，所以继续向左收缩右边界
                // 例如：[1,2,2,2,3]，找到mid=2（值为2），需要确认左边是否还有2
                right = mid - 1;
            }
        }
        /*
        循环结束后left的含义：循环结束后一定是 left > right，此时left时第一个“大于等于”target的位置
        如果这时候target存在，那就是左边界，对应left的位置。如果不存在，那就是应该插入的位置
         */

//        if (left >= n || right < 0) {
        if (left >= n || left < 0) {
            return -1;
        }
//        if (nums[right] != target) {
        if (nums[left] != target) {
            return -1;
        }
        return left;
    }

    private int rightBound(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                // 由于要寻找右边界，因此收缩左边界
                left = mid + 1;
            }
        }
//        if (left >= n || right < 0) {
        if (right >= n || right < 0) {
            return -1;
        }
//        if (nums[left] != target) {
        if (nums[right] != target) {
            return -1;
        }
        return right;
    }
}
