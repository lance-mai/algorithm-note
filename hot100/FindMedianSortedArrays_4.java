package hot100;

/**
 * 寻找两个正序数组的中位数
 * 解法1：将两个有序数组合并成一个有序数组，双指针。然后再判断元素个数是奇数还是偶数，再求中位数
 * 这个解法是我自己想出来的，ok
 */
public class FindMedianSortedArrays_4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int left = 0;
        int right = 0; // [left, right)
        int m = nums1.length;
        int n = nums2.length;
        int[] merge = new int[m + n];
        int mergeIdx = 0;
        int cur1;
        int cur2;
        while (left < m || right < n) {
            // if (left < m) {
            //     cur1 = nums1[left];
            // } else {
            //     cur1 = Integer.MAX_VALUE;
            // }
            cur1 = left < m ? nums1[left] : Integer.MAX_VALUE;
            // if (right < n) {
            //     cur2 = nums2[right];
            // } else {
            //     cur2 = Integer.MAX_VALUE;
            // }
            cur2 = right < n ? nums2[right] : Integer.MAX_VALUE;
            if (cur1 < cur2) {
                merge[mergeIdx] = cur1;
                left++;
            } else {
                merge[mergeIdx] = cur2;
                right++;
            }
            mergeIdx++;
        }

        // 判断奇偶数
        if ((m + n) % 2 == 0) { // 偶数
            return (merge[(m + n) / 2] + merge[(m + n) / 2 - 1]) / 2.0;
        } else {
            return merge[(m + n) / 2];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        System.out.println(new FindMedianSortedArrays_4().findMedianSortedArrays(nums1, nums2));
    }
}
