package hot100;

import java.util.HashMap;

/**
 * 无重复字符的最长子串
 * 解法1：遍历字符，以遍历到的字符作为开头，再遍历一次数组，时间复杂度O(n^2)
 * 解法2（我的解法，没做出来）：也是用hash解法，只需要遍历一次数组，O(n)
 * 注意：
 * 1、需要考虑到遇到重复字符后重试set，并且加入该字符时，lenCnt=1，而不是0
 * 2、注意到如果循环到最后，还有最后处理一下lenCnt，和现有的maxSubLength比较后再返回结果
 * 3、判断到重复出现的字符后，以该字符作为起点，重置set，会丢失前面一部分信息，导致结果错误。应该使用hashmap，记录字符出现次数
 * 解法3：滑动窗口
 * 什么时候扩大窗口、什么时候缩小窗口、什么时候更新结果
 * 有点问题：我不知道什么时候应该更新返回的答案
 * 回答：应该在缩小窗口while循环结果后，更新答案。为什么？因为缩小结束后，是满足条件的。
 * 那为什么不在一开始扩大窗口的时候更新答案呢？那时候也是满足条件所以才会扩大窗口啊。既然满足条件，为什么不记录答案
 * 回答：
 * repeatSet是冗余设计，应该去掉。因为每次判断是否缩小窗口前，实际上加入的in字符才有可能是重复字符，
 * 其他字符在上一次缩小窗口的while循环中已经被去掉了
 */
public class LengthOfLongestSubstring_3 {
    // 滑动窗口解法
    public int lengthOfLongestSubstring(String s) {
        // 什么时候扩大窗口：当不含有重复字符时，扩大，尝试找到更长的子串
        //  更正：只要right没遍历完字符串，就持续往右扩大窗口。因为我们的目标是找最长的无重复子串，因此要尽可能往右边套索，尝试覆盖更多字符
        //  补充：扩大窗口时不先判断是否重复，哪怕加入后窗口会无效，也要先扩，因为"先扩后修"是滑动窗口的标准逻辑
        // 什么时候缩小窗口：当含有重复字符时，缩小，尝试去掉重复字符
        //  更正：只有当扩大窗口后，窗口无效（出现重复字符），才要缩小窗口。缩小的目标是将无效窗口变成有效窗口。
        //  补充：这也就意味着，当缩小窗口的过程结束后，窗口是有效的，这时候可以更新答案
        // 什么时候更新结果：满足不含重复字符的条件时，记录结果
        //  更正：必须在窗口有效时（不含重复字符时）更新。扩大窗口时窗口可能无效，缩小窗口后窗口必然有效，因此缩小后更新是最稳妥的模板

        int left = 0; // [left, right)
        int right = 0;
        HashMap<Character, Integer> window = new HashMap<>(); // 记录字符出现次数
        // HashSet<Character> repeatSet = new HashSet<>(); // 记录重复字符的set
        int maxLen = 0;
        while (right < s.length()) {
            // 将in移入到窗口
            char in = s.charAt(right);
            // 扩大窗口
            right++;
            // 对窗口内数据进行更新
            window.put(in, window.getOrDefault(in, 0) + 1);
            // if (window.get(in) > 1) {
            //     repeatSet.add(in);
            // }

            // 判断是否缩小窗口。当repeatSet不为空，即存在重复字符时，可尝试缩小，去掉重复字符
            // while (!repeatSet.isEmpty()) {
            while (window.get(in) > 1) {
                // 将out移出窗口
                char out = s.charAt(left);
                // 缩小窗口
                left++;
                // 对窗口内数据进行更新
                window.put(out, window.getOrDefault(out, 0) - 1);
                // if (window.get(out) <= 1) {
                //     repeatSet.remove(out);
                // }
            }
            // 缩小窗口结束，说明此时不存在重复字符，且字符串尽可能长，因此可以更新结果
            maxLen = Math.max(maxLen, right - left);
        }
        return maxLen;
    }

    // 我的解法：hash，没解出来
    // public int lengthOfLongestSubstring(String s) {
    //     HashMap<Character, Integer> charMapOccur = new HashMap();
    //     int maxSubLength = 0;
    //     int lenCnt = 0;
    //     for (char c : s.toCharArray()) {
    //         if (charMapOccur.getOrDefault(c, 0) != 0) {
    //             maxSubLength = Math.max(maxSubLength, lenCnt);
    //             charMapOccur = new HashMap<>();
    //             charMapOccur.(c);
    //             lenCnt = 1;
    //             continue;
    //         }
    //         charMapOccur.add(c);
    //         lenCnt++;
    //     }
    //     return Math.max(maxSubLength, lenCnt);
    // }

    public static void main(String[] args) {
        String s = "dvdf";
        System.out.println(new LengthOfLongestSubstring_3().lengthOfLongestSubstring(s));
    }
}
