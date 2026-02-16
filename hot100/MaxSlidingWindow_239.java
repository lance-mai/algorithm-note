package hot100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 滑动窗口最大值
 * 解法1：使用大顶堆。出现很多错误。1、第一个窗口不参与循环，应该在循环前，就维护好 2、先将不在窗口的堆顶元素弹出，然后再求窗口最值
 * 时间复杂度：O(NlogN)
 * 解法2：单调队列 O(n)。直接看答案。
 * https://labuladong.online/zh/algo/data-structure/monotonic-queue/#%E4%BA%8C%E5%AE%9E%E7%8E%B0%E5%8D%95%E8%B0%83%E9%98%9F%E5%88%97%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84
 */
public class MaxSlidingWindow_239 {
    // 解法2：单调队列
    public int[] maxSlidingWindow(int[] nums, int k) {
        return null;
    }

    // 解法1
    public int[] maxSlidingWindow1(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        // 创建一个map，记录窗口中出现的元素
        HashMap<Integer, Integer> numMapTimes = new HashMap<>();
        for (int i = 0; i < k; i++) { // O(klogk)
            recordTimes(numMapTimes, nums[i]);
            pq.offer(nums[i]); // 堆插入 O(logk)，k是堆大小
        }
        int n = nums.length;
        int[] res = new int[n - k + 1];
        // 第一个窗口的最大值应该先维护
        res[0] = pq.peek();
        for (int i = k; i < n; i++) { // O(k)
            int left = nums[i - k];
            removeRecord(numMapTimes, left); // 窗口左边界外最近元素，需要维护记录
            // 什么时候弹出元素：窗口向右滑动时，左边剔出的元素如果是堆顶元素，则弹出。
            while (!pq.isEmpty()) {
                boolean isInRecord = isInRecord(numMapTimes, pq.peek());
                if (!isInRecord) {
                    pq.poll(); // O(logk)
                } else {
                    break;
                }
            }
            pq.offer(nums[i]); // 添加新元素到窗口中 O(log m)
            // 记录新元素
            recordTimes(numMapTimes, nums[i]);
            res[i - k + 1] = pq.peek(); // 窗口内元素最大值，大顶堆
        }
        return res;
    }

    private boolean isInRecord(HashMap<Integer, Integer> numMapTimes, int num) {
        return numMapTimes.getOrDefault(num, 0) > 0;
    }

    private void removeRecord(HashMap<Integer, Integer> numMapTimes, int num) {
        numMapTimes.put(num, numMapTimes.get(num) - 1);
    }

    private void recordTimes(HashMap<Integer, Integer> numMapTimes, int num) {
        numMapTimes.put(num, numMapTimes.getOrDefault(num, 0) + 1);
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(Arrays.toString(new MaxSlidingWindow_239().maxSlidingWindow(nums, k)));
    }
}
