package hot100;

/**
 * 盛最多水的容器
 * 解法1：单调栈解法--感觉不行
 * 解法2：暴力解法，O(n^2)
 * 解法3：我没有思路。
 * 解法4：通过labuladong的关于接雨水的双指针的解法启示，我也尝试使用双指针来解决这个最大面积问题
 * 还是想不出来
 * 看答案吧。诶
 * 解法5：双指针
 * 接雨水问题给出的是类似直方图，每个横坐标都有宽度，而这道题的每个横坐标是一条竖线，没有宽度
 * 这意味着什么呢？只需要考虑两端的边界最小高度，不用考虑中间的柱子。意味着比接雨水更简单
 * 解法为，用left和right两个指针从两端到中心收缩，一遍收缩一遍计算[left, right]之间的矩形面积，取最大的面积即为答案
 * 但是，什么时候移动左指针，什么时候移动右指针呢？
 * labuladong给出的答案是 移动较低的一边。但是会不会产生局部最优，而错过了最佳答案呢？
 * 解答：矩形的高度是由较低的一边决定的。为了能够找到更大的矩形，只能改变更短的那一边
 * 从豆包得到的解释：这个双指针其实不是在选择最优答案，而是在一直淘汰最差答案，当差劲的答案都被排除以后，就剩下最优答案了
 * 每一步都没有做“选择”，只做了“排除”，直到指针相遇
 */
public class MaxArea_11 {
    public int maxArea(int[] height) {
        // int l_max = 0;
        // int r_max = 0;
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            // l_max = Math.max(l_max, height[left]);
            // r_max = Math.max(r_max, height[right]);
            int area = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(area, maxArea);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}
