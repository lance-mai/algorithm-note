package hot100;

/**
 * 移除元素
 * 解法1：排序，时间复杂度 O(n^2)
 * 解法2：快慢指针。slow要从 -1开始， slow++;nums[slow] = nums[fast];
 * 或者  slow 从0开始，nums[slow] = nums[fast]; slow++;
 *
 */
public class RemoveElement_27 {
    public int removeElement(int[] nums, int val) {
        int slow = -1;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }
}
