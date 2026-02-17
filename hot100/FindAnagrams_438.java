package hot100;

import java.util.*;

/**
 * 找到字符串中所有字母异位词
 * 解法1：假设s长度为m，p字符串的长度为n，在s中固定窗口长度为n，每次窗口都判断一下是否为异位词
 * 有重复计算。整体时间复杂度为 O(m*n)
 * 超时了
 * 解法2：基于解法1，把重复计算去掉。
 * 优化：
 * 1、由于题目中都是小写字母，因此可以使用长度为26的数组来代替hash表
 * 2、滑动窗口优化：不再每次都重新统计窗口内的字符，而是通过“移出左边界字符+移入右边界字符”来更新窗口内的字符数，时间复杂度为 O(m) m是s的长度
 * 3、匹配判断优化：。。。
 * 解法3：滑动窗口解法。labuladong。
 * 通过了。需要注意：1、初始化charMapCnt
 * 时间复杂度分析：假设s、p的长度分别为m,n。 O(m*n)
 * 解法4：优化滑动窗口。
 * 1）使用数组代替hashmap，可以加快计算速度，以及占用空间更小（暂时不优化）
 * 2）在每次窗口滑动时计算匹配数，而不是重新遍历一次。比如，p有15类字母，每类字母的个数为若干个，可以使用map记录
 * 初始化时，计算s、p的前n个字符，看看match是多少。
 * 当match为15时，则为异位词。
 */
public class FindAnagrams_438 {
    // 解法4：滑动窗口解法，优化版
    int match = 0;

    public List<Integer> findAnagrams(String s, String p) {
        HashMap<Character, Integer> need = new HashMap<>();
        ArrayList<Integer> res = new ArrayList<>();
        // 初始化need
        for (char c : p.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        HashMap<Character, Integer> windows = new HashMap<>();
        int left = 0, right = 0;
        while (right < s.length()) {
            char rightCur = s.charAt(right);
            windows.put(rightCur, windows.getOrDefault(rightCur, 0) + 1);
            updateMatchRight(rightCur, windows, need);
            right++;
            while (right - left > p.length()) { // 窗口大于p时，缩小窗口
                char leftCur = s.charAt(left);
                windows.put(leftCur, windows.get(leftCur) - 1);
                updateMatchLeft(leftCur, windows, need);
                left++;
            }
            if (match == need.size()) {
                res.add(left);
            }
        }
        return res;
    }

    // 增加元素时维护match
    private void updateMatchRight(char cur, HashMap<Character, Integer> windows, HashMap<Character, Integer> need) {
        int winCnt = windows.getOrDefault(cur, 0);
        int needCnt = need.getOrDefault(cur, 0);
        if (winCnt == needCnt) {
            match++;
        } else if (winCnt - 1 == needCnt) { // 说明增加元素之前是匹配的，因此需要match--
            match--;
        }
    }

    // 减少元素时维护match
    private void updateMatchLeft(char cur, HashMap<Character, Integer> windows, HashMap<Character, Integer> need) {
        int winCnt = windows.getOrDefault(cur, 0);
        int needCnt = need.getOrDefault(cur, 0);
        if (winCnt == needCnt) {
            match++;
        } else if (winCnt + 1 == needCnt) { // 说明减少元素之前是匹配的，因此需要match--
            match--;
        }
    }

    // 解法3：滑动窗口解法，labuladong
    public List<Integer> findAnagrams3(String s, String p) {
        HashMap<Character, Integer> charMapCnt = new HashMap<>();
        // 初始化charMapCnt
        for (char c : p.toCharArray()) { // O(n)
            charMapCnt.put(c, charMapCnt.getOrDefault(c, 0) + 1);
        }

        HashMap<Character, Integer> windows = new HashMap<>();
        int left = 0, right = 0;
        ArrayList<Integer> res = new ArrayList<>();
        while (right < s.length()) { // O(m*n)
            char cur = s.charAt(right);
            windows.put(cur, windows.getOrDefault(cur, 0) + 1);
            right++;
            // 什么时候缩小窗口：窗口长度长于p时
            while (right - left > p.length()) {
                char leftCur = s.charAt(left);
                Integer leftCnt = windows.get(leftCur);
                if (leftCnt <= 1) {
                    windows.remove(leftCur);
                } else {
                    windows.put(leftCur, leftCnt - 1);
                }
                left++;
            }
            // 判断是否异位词。charMapCnt 和 windows比较。这里比较耗时
            if (charMapCnt.size() == windows.size() && isAnagram(charMapCnt, windows)) {
                res.add(left);
            }
        }
        return res;
    }

    // O(n)
    private boolean isAnagram(HashMap<Character, Integer> charMapCnt, HashMap<Character, Integer> windows) {
        for (Map.Entry<Character, Integer> entry : windows.entrySet()) {
            Character winKey = entry.getKey();
            Integer winVal = entry.getValue();
            if (!charMapCnt.containsKey(winKey) || !charMapCnt.get(winKey).equals(winVal)) {
                return false;
            }
        }
        return true;
    }

    // 解法1：超时了
    public List<Integer> findAnagrams1(String s, String p) {
        int m = s.length();
        int n = p.length();
        ArrayList<Integer> res = new ArrayList<>();
        if (m < n) {
            return res;
        }
        HashMap<Character, Integer> charMapTimes = getMapFromString(p);
        for (int i = 0; i <= m - n; i++) {
            int start = i; // [start, end]
            int end = i + n - 1;
            if (isSame(start, end, s, charMapTimes)) {
                res.add(start);
            }
        }
        return res;
    }

    private HashMap<Character, Integer> getMapFromString(String str) {
        HashMap<Character, Integer> charMapTimes = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            Character cur = str.charAt(i);
            charMapTimes.put(cur, charMapTimes.getOrDefault(cur, 0) + 1);
        }
        return charMapTimes;
    }
    // 异位词的判断，不是单纯地按位比较。需要使用hash

    private boolean isSame(int start, int end, String s, HashMap<Character, Integer> charMapTimes) {
        Map<Character, Integer> tempMap = new HashMap<>(Map.copyOf(charMapTimes));
        for (int i = start; i <= end; i++) {
            Character cur = s.charAt(i);
            if (!tempMap.containsKey(cur) || tempMap.get(cur) <= 0) {
                return false;
            }
            if (tempMap.get(cur) == 1) {
                tempMap.remove(cur);
            } else {
                tempMap.put(cur, tempMap.get(cur) - 1);
            }
        }
        return tempMap.isEmpty();
    }

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        System.out.println(new FindAnagrams_438().findAnagrams(s, p));
    }
}
