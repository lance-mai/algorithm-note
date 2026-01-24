package hot100;

import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * 下一个更大元素I
 */
public class NextGreaterElement_496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> numMapNext = new HashMap<>();
        // 先求出num2的下一个大元素
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            int cur = nums2[i];
            while (!stack.isEmpty() && stack.peek() <= cur) {
                stack.pop();
            }
            int next = stack.isEmpty() ? -1 : stack.peek();
            numMapNext.put(cur, next);
            stack.push(cur);
        }
        // 映射
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = numMapNext.get(nums1[i]);
        }
        return ans;
    }
}
