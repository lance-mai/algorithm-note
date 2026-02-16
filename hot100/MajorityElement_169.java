package hot100;

import java.util.HashMap;
import java.util.Map;

/**
 * 多数元素
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 * 解法1：使用map来统计，然后遍历map，判断是否该元素个数>n/2
 * 时间复杂度是 O(N)，空间复杂度是 O(N)
 * 解法2：不知道怎么使用O(1)的空间复杂度
 */
public class MajorityElement_169 {
    // 解法1
    public int majorityElement1(int[] nums) {
        HashMap<Integer, Integer> eleMapTimes = new HashMap<>();
        for (int num : nums) {
            eleMapTimes.put(num, eleMapTimes.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : eleMapTimes.entrySet()) {
            Integer num = entry.getKey();
            Integer times = entry.getValue();
            if (times > nums.length / 2) {
                return num;
            }
        }
        return -1;
    }
}
