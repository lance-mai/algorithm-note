package labuladong.prefixsum;

import java.util.HashMap;

/**
 * 和为K的子数组
 * 1、我的思路是通过左右指针来确定子数组的和，花了50分钟还是解决不了。不能用滑动窗口：当我们想扩大窗口时，是想让窗口内元素和变大，但问题是
 * 这些数组元素中有负数，所以不能单独递增/递减，无法使用滑动窗口
 * 2、转念一想：能不能使用 前缀和 + 哈希表 来解决：又花了半个小时，解决了 85/93的用例
 * 3、看下labuladong的解法吧：和2的思路是一致的。但是为什么我做不对呢？问题在于：标准答案是先查找是否存在满足条件，计数后再更新。
 * 而我的做法是先更新map然后再去查找，就可能会重复计数。注意，前缀和数组中是不允许 pre[i] - pre[i] 即自己减自己本身的
 */
public class SubarraySum_560 {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        if (n == 1) {
            return nums[0] == k ? 1 : 0;
        }
        int[] prefix = new int[n + 1];
        int cnt = 0;
        // 记录前缀和重复出现次数
        HashMap<Integer, Integer> prefixMapCnt = new HashMap<>();
        prefixMapCnt.put(prefix[0], 1);
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
            // labudadong代码
            int subtract = prefix[i] - k;
            if (prefixMapCnt.containsKey(subtract)) {
                cnt += prefixMapCnt.get(subtract);
            }
            if (!prefixMapCnt.containsKey(prefix[i])) {
                prefixMapCnt.put(prefix[i], 1);
            } else {
                prefixMapCnt.put(prefix[i], prefixMapCnt.get(prefix[i]) + 1);
            }


            // // update map
            // Integer cntVal = prefixMapCnt.get(prefix[i]);
            // if (cntVal == null) {
            //     prefixMapCnt.put(prefix[i], 1);
            // } else {
            //     prefixMapCnt.put(prefix[i], cntVal + 1);
            // }
            // // count
            // int subtract = prefix[i] - k;
            // cnt += prefixMapCnt.getOrDefault(subtract, 0);
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] nums = {1};
        int k = 0;
        int i = new SubarraySum_560().subarraySum(nums, k);
        System.out.println(i);
    }
}
