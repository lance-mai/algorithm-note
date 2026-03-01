package bytedance;

public class MaxArea_11 {
    public int maxArea(int[] height) {
        int n = height.length;
        int left = 0, right = n - 1;
        int maxArea = 0;
        while (left < right) {
            int leftHeight = height[left];
            int rightHeight = height[right];
            int area = Math.min(leftHeight, rightHeight) * (right - left);
            maxArea = Math.max(maxArea, area);
            if (leftHeight < rightHeight) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}
