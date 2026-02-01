package hot100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 最长连续序列
 * 解法1：暴力解法，O(n^n)
 * 解法2：先排序，复杂度nlogn，不符合题目要求的O(n)
 * 解法3：hash法。number : index，先找到最小值和最大值，从最小的开始，到最大的结束
 * key就算有重复值，index被覆盖也没关系
 * 第一次报错：没有考虑到最后一次返回前 maxLen 和 lenCnt取最大值
 * 第二次报错：数组为空是 int max = nums[0] 报数组越界访问，应该先排除空数组的情况
 * 第三次报错：输入[1, 100]时，输出为2，正确应该为1。原因是，当出现不连续时，忘了continue，导致lenCnt还在++。
 * 第四次报错：超时。因为输入是[0,1,2,4,8,5,6,7,9,3,55,88,77,99,999999999]，导致中间很多无用的数我都遍历了。能不能跳过呢？
 * 跳过的方法是：关键是如果判断一个数是【连续序列的起点】=》一个数x能成为连续序列的起点 <==> x-1不在哈希表中
 * 解法3优化版：还是超时。为什么？因为从起点开始算最长序列时，没有更新num（num++)
 * 解决上面这个问题后，还是超时了。输入非常大，有很多个重复值。所有需要去重。不遍历原数组，而是遍历set
 * OK，通过
 *
 */
public class LongestConsecutive_128 {
    // 解法3（优化版）
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int maxLen = 0;
        for (int num : set) { // 去重
            // 判断该数是否能够成为连续序列的起点，则需要判断比该数x小1的值x-1是否在数据中（通过hash快速判断）
            if (set.contains(num - 1)) {
                // 不是序列起点，则跳过
                continue;
            }
            // 如果是起点
            int len = getSubLongest(num, set);
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }

    private int getSubLongest(int num, Set<Integer> set) {
        int lenCnt = 1;
        while (set.contains(num + 1)) {
            lenCnt++;
            num++;
        }
        return lenCnt;
    }

    // 解法3（hashmap），没做出来，超时，因为中间有太多无用的数被遍历
    public int longestConsecutive1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // 组装hash表，同时找到最大值和最小值
        HashMap<Integer, Integer> numMapIdx = new HashMap<>();
        int max = nums[0];
        int min = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            max = Math.max(cur, max);
            min = Math.min(cur, min);
            numMapIdx.put(cur, i);
        }
        // 遍历区间 [min, max]，这是值，不是索引
        int maxLen = 0;
        int lenCnt = 0;
        for (int i = min; i <= max; i++) { // 这里的时间复杂度不止 O(n)，而是 O(max-min)
            // 当遇到不连续时，记录当前最长连续长度，并重置lenCnt
            if (!numMapIdx.containsKey(i)) {
                maxLen = Math.max(maxLen, lenCnt);
                lenCnt = 0;
                continue;
            }
            lenCnt++;
        }
        return Math.max(maxLen, lenCnt);
    }

    public static void main(String[] args) {
        System.out.println(new LongestConsecutive_128().longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
    }
}
