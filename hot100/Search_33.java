package hot100;

/**
 * 搜索旋转排序数组
 * 思路：如果是升序，完全可以使用二分法去求解。 O(logn)
 * 但是现在发生了旋转，旋转的点不知道，因此无法使用二分法。如果去找发生旋转的节点，那将是O(n)复杂度
 * 题目要求：你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 * 怎么办嗯？我没有思路，看答案。
 * 解法1：二分搜索法。关键点是 发生旋转的那一截 均小于 不发生旋转的那一截（因为原数组是升序，且独一无二，即严格升序）
 * https://labuladong.online/zh/algo/problem-set/binary-search/#slug_search-in-rotated-sorted-array
 * 即旋转后，仍可以使用二分查找。因为只要有一半儿有序就可以做排除判断，不在这边就肯定在另一边
 * 鼓励一下自己：这是我看来思路之后，自己手撸出来的。哈哈哈哈，我很棒
 */
public class Search_33 {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1; // [left, right]
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 如果mid刚好是target，那就不用判断了，直接饭hi
            if (nums[mid] == target) {
                return mid;
            }
            // 如果mid在断崖左边，那就是左边有序，基于左边做判断
            if (nums[mid] >= nums[left]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { // 如果在断崖右边，那就是右边有序，基于右边做判断
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1; // 没找到，返回-1
    }
}
