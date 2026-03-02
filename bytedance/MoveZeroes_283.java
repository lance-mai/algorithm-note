package bytedance;

public class MoveZeroes_283 {
    public void moveZeroes(int[] nums) {
        int slow = 0, fast = 0;
        int n = nums.length;
        while (fast < n) {
            int fastVal = nums[fast];
            if (fastVal != 0) {
                nums[slow] = fastVal;
                slow++;
            }
            fast++;
        }
        while (slow < n) {
            nums[slow] = 0;
            slow++;
        }
    }
}
