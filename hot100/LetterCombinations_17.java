package hot100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 电话号码的字母组合
 * 解法1：回溯，结合hashmap。yes！一次成功
 *
 */
public class LetterCombinations_17 {
    // 解法1
    HashMap<Character, String> numToChars;
    List<String> res;

    public List<String> letterCombinations(String digits) {
        res = new ArrayList<>();
        numToChars = new HashMap<>();
        initMap();
        int start = 0;
        backtrack(digits, start, new StringBuilder());
        return res;
    }

    private void backtrack(String digits, int start, StringBuilder track) {
        if (start == digits.length()) {
            res.add(track.toString());
            return;
        }
        char cur = digits.charAt(start);
        char[] charArray = numToChars.get(cur).toCharArray();
        for (char c : charArray) {
            track.append(c);
            start++;
            backtrack(digits, start, track);
            start--;
            track.deleteCharAt(track.length() - 1);
        }
    }

    private void initMap() {
        numToChars.put('2', "abc");
        numToChars.put('3', "def");
        numToChars.put('4', "ghi");
        numToChars.put('5', "jkl");
        numToChars.put('6', "mno");
        numToChars.put('7', "pqrs");
        numToChars.put('8', "tuv");
        numToChars.put('9', "wxyz");
    }
}
