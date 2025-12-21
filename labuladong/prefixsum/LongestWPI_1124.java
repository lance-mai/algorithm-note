package labuladong.prefixsum;

import java.util.HashMap;

/**
 * 表现良好的最长时间段
 * 1、使用前缀和+滑动窗口。搞了20分钟，还是不行。不能用滑动窗口，
 * 因为前缀和数组中有正数负数，不能保证扩大窗口时元素和是增大的
 * 2、看了labuladong的解法，依然是 前缀和+hashmap。但是如何将实际问题转化为
 * 前缀和 + hashmap，还是比较有难度的，考验技巧的
 * 20251221：不会做，看答案。我之前推导出 prefix[i] > prefix[j]，但这是不等式，需要转换为等式才能求解
 * <p>
 * 网友分享（看完以后才想明白！）：当preSum[i]小于0时，为了让preSum[i] - preSum[j] > 0 (j < i),
 * preSum[j]必须比preSum[i]更小 。在index i之前的preSum数组中，如果有值比preSum[i]小，
 * 那么preSum[j] = preSum[i] - 1必然出现，而且出现得最早。
 * 简单举个例子：假设有个preSum[k] = preSum[i] - 2，那么preSum[k]是怎么来的呢？
 * preSum数组以0值开始，每次变化的绝对值等于1，那么在preSum[0]=0和preSum[k] = preSum[i] - 2
 * 之间必然有个preSum的值为preSum[i] - 1
 */
public class LongestWPI_1124 {
    public int longestWPI(int[] hours) {
        // 将数组归一化
        int n = hours.length;
        for (int i = 0; i < n; i++) {
            hours[i] = hours[i] > 8 ? 1 : -1;
        }
        // prefix
        int maxLen = 0;
        HashMap<Integer, Integer> prefixToIndex = new HashMap<>();
        int[] prefix = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + hours[i - 1];
            // 如果这个前缀和还没有对应的索引，说明这个前缀和第一次出现，需要记录下来
            if (!prefixToIndex.containsKey(prefix[i])) {
                prefixToIndex.put(prefix[i], i);
            } else {
                // 因为题目想找长度最大的子数组，因此prefixToIndex中的索引应尽可能小
                // 因此什么都不做
            }
            // 现在我们想找 hours中元素和大于0的子数组
            // 这里要根据 prefix[i]的正负，分情况讨论
            if (prefix[i] > 0) {
                // prefix[i]为正，说明 hours[0...i-1]都是表现良好的时间段
                maxLen = Math.max(maxLen, i);
            } else {
                // prefix[i]为负，需要寻找一个j，使得prefix[i] - prefix[j] > 0
                // 考虑到 prefix数组每两个相邻元素的差的绝对值都是1且j应该尽可能小（已经归一化了）
                // ???那么只要找到 prefix[j] == prefix[i] - 1, hours[j+1...i]就是一个表现良好的时间段
                if (prefixToIndex.containsKey(prefix[i] - 1)) {
                    int j = prefixToIndex.get(prefix[i] - 1);
                    maxLen = Math.max(maxLen, i - j);
                }
            }
        }
        return maxLen;
    }
}
