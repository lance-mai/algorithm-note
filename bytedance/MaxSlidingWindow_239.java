package bytedance;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class MaxSlidingWindow_239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 大顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        HashMap<Integer, Integer> numToCnt = new HashMap<>(); // 表示数在窗口里面
        for (int i = 0; i < k; i++) {
            int cur = nums[i];
            queue.offer(cur);
            numToCnt.put(cur, numToCnt.getOrDefault(cur, 0) + 1);
        }
        int n = nums.length;
        int[] res = new int[n - k + 1];
        for (int i = k - 1; i < n; i++) {
            // 表示窗口中不存在该数
            while (!queue.isEmpty() && numToCnt.getOrDefault(queue.peek(), 0) == 0) {
                queue.poll();
            }
            res[i - k + 1] = queue.peek();
            int next = (i + 1) < n ? nums[i + 1] : 0;
            // 加数
            queue.offer(next);
            numToCnt.put(next, numToCnt.getOrDefault(next, 0) + 1);
            int windowLeft = nums[i - k + 1];
            numToCnt.put(windowLeft, numToCnt.get(windowLeft) - 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(Arrays.toString(new MaxSlidingWindow_239().maxSlidingWindow(nums, k)));
    }
}
