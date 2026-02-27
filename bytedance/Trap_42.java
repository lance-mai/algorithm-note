package bytedance;

import java.util.ArrayDeque;

/**
 * 接雨水
 * 解法1：滑动窗口。很久不做了，忘了
 * 解法2：直接看答案。单调栈
 * 解法3：备忘录
 */
public class Trap_42 {
    // 解法3
    public int trap(int[] height) {
        int n = height.length;
        int[] l_max = new int[n];
        int[] r_max = new int[n];
        l_max[0] = height[0];
        r_max[n - 1] = height[n - 1];
        for (int i = 1; i < n; i++) {
            l_max[i] = Math.max(height[i], l_max[i - 1]);
        }
        for (int i = n - 2; i >= 0; i--) {
            r_max[i] = Math.max(height[i], r_max[i + 1]);
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += (Math.min(l_max[i], r_max[i]) - height[i]);
        }
        return sum;
    }

    // 解法2
    public int trap2(int[] height) {
        int n = height.length;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                Integer cur = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int right = i;
                int width = right - left - 1;
                int hi = Math.min(height[left], height[right]) - height[cur];
                sum += width * hi;
            }
            stack.push(i);
        }
        return sum;
    }

    // 解法1
    // public int trap1(int[] height) {
    //     int n = height.length;
    //     int left = 0, right = 0;
    //     LinkedList<Integer> window = new LinkedList<>();
    //     int res = 0; // 记录雨水
    //     while (right < n) {
    //         window.addLast(height[right]);
    //         right++;
    //         // 什么时候缩小窗口：当满足左边<=右边时，开始计算雨水
    //         if (window.size() > 1 && height[left] <= window.getLast()) {
    //             // 开始计算雨水
    //             for (int i = left; i < right; i++) {
    //
    //             }
    //         }
    //     }
    // }
}
