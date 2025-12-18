package labuladong.prefixsum;

/**
 * 表现良好的最长时间段
 * 1、使用前缀和+滑动窗口。搞了20分钟，还是不行。不能用滑动窗口，因为前缀和数组中有正数负数，不能保证扩大窗口时元素和是增大的
 * 2、看了labuladong的解法，依然是 前缀和+hashmap。但是如何将实际问题转化为 前缀和 + hashmap，还是比较有难度的，考验技巧的
 */
public class LongestWPI {
    public int longestWPI(int[] hours) {
        // 将数组归一化
        int n = hours.length;
        for (int i = 0; i < n; i++) {
            hours[i] = hours[i] > 8 ? 1 : -1;
        }
        // prefix
        int[] prefix = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + hours[i - 1];
        }


        return maxLen;
    }
}
