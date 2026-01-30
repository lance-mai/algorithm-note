package hot100;

import java.util.ArrayDeque;

/**
 * 接雨水
 * 解法1：单调栈
 * 注意：当后面的柱子低于前面的柱子时，是无法接住雨水的
 * 按层来计算，有左边界、右边界的时候才计算雨水
 * 与查看后面更大数的从后往前遍历不同，由于接雨水要从左边开始收集信息，因此需要从左往右遍历
 * （后面更大数为啥要从后往前：因为需要知道右边的数哪些是更大的数，如果从左边开始遍历，那么右边不知道最大的数是哪个，从而会增加计算复杂度）
 * stack需要记录索引而不是高度，因为要计算每一层符合条件（收集雨水）的宽度
 * 解法2：数组双指针技巧（labuladong）
 *
 *
 */
public class Trap_42 {
    // 单调栈解法（借鉴leetcode题解）
    public int trap(int[] height) {
        int sum = 0; // 记录雨水
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < height.length; i++) {
            // 当后面柱子高于底层柱子时，可以考虑收集雨水
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                Integer cur = stack.pop();
                // 如果弹出当前柱子最左边一个矮一点的柱子后，发现stack已经空了，这意味着不存在左边界，收集不了雨水
                // 比如 height[1,2]，遍历到2时，cur为1，cur弹出后stack为空，不存在左边界，不能收集雨水
                if (stack.isEmpty()) {
                    break;
                }
                // 如果存在左边界，则记录下来
                Integer left = stack.peek();
                // 右边界自然是当前遍历到的符合while循环条件的i
                Integer right = i;
                // 计算左右边界之间的能够盛水的宽度
                Integer width = right - left - 1;
                // 计算左边边界之间能够盛水的层数，木桶原理，以最低的为准
                Integer curHeight = Math.min(height[left], height[right]) - height[cur];
                // 计算雨水
                sum += (width * curHeight);
            }
            stack.push(i);
        }
        return sum;
    }

    // 我的解法，按列计算，没算出来
    public int trap1(int[] height) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        // 记录下一个>=其值的位置坐标
        int[] next = new int[height.length];
        for (int i = height.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                stack.pop();
            }
            next[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        // 针对next计算雨水
        int index = 0;
        int water = 0;
        while (index < next.length) {
            if (next[index] == -1) { // 说明后面没有高过他的，装不下水
                index++;
                continue;
            }
            for (int i = index; i <= next[index]; i++) {
                water += (Math.max(height[index] - height[i], 0));
            }
            index = next[index];
        }
        return water;
    }

    public static void main(String[] args) {
        new Trap_42().trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
    }
}
