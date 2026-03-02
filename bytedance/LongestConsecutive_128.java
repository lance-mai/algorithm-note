package bytedance;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class LongestConsecutive_128 {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int maxLen = 0;
        for (Integer num : set) {
            if (set.contains(num - 1)) {
                continue;
            }
            int len = getConsecutiveLength(num, set);
            maxLen = Math.max(len, maxLen);
        }
        return maxLen;
    }

    private int getConsecutiveLength(int num, Set<Integer> set) {
        int len = 0;
        int cur = num;
        while (set.contains(cur)) {
            len++;
            cur++;
        }
        return len;
    }
}
