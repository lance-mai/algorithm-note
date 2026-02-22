package additional;

import java.util.Arrays;

/**
 * 最长公共前缀
 * 我的思路：遍历每一个字符，都是一样的时候，加入到前缀中。 时间复杂度 O(n^2)
 * 解法1：遍历字符，判断
 * 解法2：参考leetcode。先将strs进行字典序排序。对第一个str和最后一个字符比较，就能得到最大公共前缀
 * 为什么：因为字典序排序后，第一个str和最后一个str的差异是最大的，他们的公共前缀就是整个strs数组的最长公共前缀
 */
public class LongestCommonPrefix_14 {
    // 解法2：排序后对比首尾str
    public String longestCommonPrefix(String[] strs) {
        int n = strs.length;
        if (n == 1) {
            return strs[0];
        }
        Arrays.sort(strs);
        String first = strs[0];
        String last = strs[n - 1];
        int steps = Math.min(first.length(), last.length());
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < steps; i++) {
            if (first.charAt(i) != last.charAt(i)) {
                return res.toString();
            }
            res.append(first.charAt(i));
        }
        return res.toString();
    }


    // 解法1：暴力解法
    public String longestCommonPrefix1(String[] strs) {
        if (strs.length == 1) {
            return strs[0];
        }
        // 以第一个str作为基准进行遍历
        StringBuilder res = new StringBuilder();
        String std = strs[0];
        for (int i = 0; i < std.length(); i++) { // 遍历std每个字符
            char cur = std.charAt(i);
            for (int j = 1; j < strs.length; j++) { // 遍历其余str的相同位置的字符
                String compareStr = strs[j];
                if (i >= compareStr.length()) { // 该字符不够长，循环结束
                    // break;
                    return res.toString();
                }
                if (compareStr.charAt(i) != cur) { // 不匹配，循环结束（）直接return。
                    // break;
                    return res.toString();
                }
            }
            res.append(cur);
        }
        return res.toString();
    }
}
