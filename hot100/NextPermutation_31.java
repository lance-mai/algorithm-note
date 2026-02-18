package hot100;

/**
 * 下一个排列
 * 解法1：从后向前比较，前面为m，后面为n，如果m<n，则可以交换，满足条件。如果都不满足条件，那就将整个数组倒排（重新回到起点）
 * 算法不对（没有考虑完全），直接看答案
 * 解法2：
 * 1、从后往前遍历，找到第一个满足nums[i]<nums[i+1]的i，这一步是为了找到可以“变大的最小位置”
 * 2、这时候nums[i+1...]序列是降序的。因此如果直接交换nums[i]和nums[j]的话，可能不会是原数组增量最小的，
 * 需要找到大于nums[i]且最接近nums[i]的，如nums[j]（j>i），这样增量才最小
 * 3、交换nums[i]和nums[j]，交换以后，i后面的元素（从i+1开始）依然是降序
 * 4、因为这个时候，需要将i后面的元素从降序变成升序，才能够使得增量最小
 * <p>
 * 注意：不一定能找到交换点，如果找不到交换点，那就是整个数组都是降序排序，那就需要整个数组倒序
 */
public class NextPermutation_31 {
    // 解法2
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int cur = n - 1;
        int swapPoint = n;
        // 找到交换点
        while (cur > 0) {
            if (nums[cur] > nums[cur - 1]) {
                swapPoint = cur - 1;
                break;
            } else {
                cur--;
            }
        }
        // 易错点：判断是否找到交换点
        if (swapPoint == n) {
            reverse(nums, 0, n);
            return;
        }

        // 找到最小交换点
        int rightSwapPoint = n - 1;
        for (int i = n - 1; i > swapPoint; i--) {
            if (nums[i] > nums[swapPoint]) {
                rightSwapPoint = i;
                break;
            }
        }
        // 交换
        swap(nums, swapPoint, rightSwapPoint);
        // 将i之后的升序变成降序
        reverse(nums, swapPoint + 1, n);
    }

    private static void reverse(int[] nums, int start, int end) {
        int left = start, right = end - 1;
        while (left <= right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    private static void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

    public void nextPermutation1(int[] nums) {
        int n = nums.length;
        int cur = n - 1;
        while (cur > 0) {
            if (nums[cur] > nums[cur - 1]) {
                swap(nums, cur, cur - 1);
                return;
            } else {
                cur--;
            }
        }
        // 如果都不满足条件，那就从头再来（新的循环起点），数组倒转
        reverse(nums);
    }

    private void reverse(int[] nums) {
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}
