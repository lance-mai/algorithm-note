package bytedance;

import java.util.HashMap;

public class LengthOfLongestSubstring_3 {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        HashMap<Character, Integer> charToCnt = new HashMap<>();
        int maxLen = 0;
        int left = 0, right = 0;
        while (right < n) {
            char in = s.charAt(right);
            charToCnt.put(in, charToCnt.getOrDefault(in, 0) + 1);
            right++;
            while (charToCnt.get(in) > 1) {
                // 移出
                char out = s.charAt(left);
                charToCnt.put(out, charToCnt.get(out) - 1);
                left++;
            }
            maxLen = Math.max(maxLen, right - left);
        }
        return maxLen;
    }
}
