package hot100;

import java.util.ArrayDeque;

/**
 * 柱状图中最大的矩形
 * 解法1：暴力算法
 * 首先如果是暴力算法，那么遍历到i时，需要找出左右边界。左边界为小于height[i]的位置，有边界为小于height[i]的位置
 * 解法2：单调栈
 * 和接雨水有类似解法。接雨水是找到左右两边更高的边界。最大矩形是找到左右两边更低的边界
 *
 */
public class LargestRectangleArea_84 {
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        ArrayDeque<Integer> idxStack = new ArrayDeque<>();
        // 注意，原本我的错误代码中，只在遍历数组时触发，但是对于递增数组如[4,2]，遍历过程中不会进入while循环，
        // 就不会触发计算，栈内的元素永远不会被处理。因此，需要在数组末尾补一个高度为0的虚拟右边界，这样才能触发stack元素处理
        int[] newHeights = new int[heights.length + 1];
        System.arraycopy(heights, 0, newHeights, 0, heights.length);
        newHeights[newHeights.length - 1] = 0;
        for (int i = 0; i < newHeights.length; i++) {
            // 当后面矩形低于当前底层矩形时，开始计算矩形面积
            while (!idxStack.isEmpty() && newHeights[i] < newHeights[idxStack.peek()]) {
                // 当前底层矩形
                Integer curIdx = idxStack.pop();
                // 如果stack为空，则curIdx为左边界
                int left;
                if (idxStack.isEmpty()) {
                    left = -1; // 栈空时，左边界应该是-1（虚拟左边界），而非curIdx
                } else {
                    left = idxStack.peek();
                }
                int width = i - left - 1; // i为右边界（不包含），同时注意，左边界也不包含
                // int height = Math.min(newHeights[left], newHeights[curIdx]);
                // 矩形高度计算时，应该取当前弹出柱子的高度
                int height = newHeights[curIdx];
                maxArea = Math.max(maxArea, width * height);
            }
            idxStack.push(i);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        new LargestRectangleArea_84().largestRectangleArea(new int[]{2, 4});
    }
}
