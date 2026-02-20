package hot100;

/**
 * 颜色分类
 * 解法1：思路：三指针？NO。双指针，先排0，再排1，剩下的就是2
 * 哈哈哈，这是我独立做出来的，yes！！！
 */
public class SortColors_75 {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int left = 0, right = 0;
        // 先排0
        while (right < n) {
            if (nums[right] == 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
        // 循环结束后，left是非0的新的开始
        // 再循环一次
        right = left;
        while (right < n) {
            if (nums[right] == 1) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    private void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
}
