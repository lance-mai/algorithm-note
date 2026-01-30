package hot100;

/**
 * 删除有序数组中的重复项
 * 解法1：快慢指针
 * 原地修改数组的值，fast探路，slow赋值
 */
public class RemoveDuplicates_26 {
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }
}
