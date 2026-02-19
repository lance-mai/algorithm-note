package hot100;

import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 前K个高频元素
 * 解法1：先统计每个整数出现次数，用map来记录。然后再使用大顶堆 O(nlogn)
 * 解法2：优化。可以搞到 O(nlogk) k = n时会恶化到O(nlogn)
 * 使用小顶堆，只装k个元素，每次都挤出最小的。后面剩下就是k个最大的
 * 注意，先进一个，再出一个，这样才能保证最后一个元素的操作是出队，才能保证最终所有元素是k个最大的元素
 */
public class TopKFrequent_347 {
    // 解法2：小顶堆
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> numToCnt = new HashMap<>();
        for (int num : nums) {
            numToCnt.put(num, numToCnt.getOrDefault(num, 0) + 1);
        }
        // 小顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> numToCnt.get(a) - numToCnt.get(b));
        List<Integer> list = numToCnt.keySet().stream().toList();
        // 先压入k个
        for (int i = 0; i < k; i++) {
            queue.offer(list.get(i));
        }
        for (int i = k; i < list.size(); i++) {
//            queue.poll();
//            queue.offer(list.get(i));
            // 注意，先进一个，再出一个，这样才能保证最后一个元素的操作是出队，才能保证最终所有元素是k个最大的元素
            queue.offer(list.get(i));
            queue.poll();
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll();
        }
        return res;
    }

    // 解法1：大顶堆
    public int[] topKFrequent1(int[] nums, int k) {
        HashMap<Integer, Integer> numToCnt = new HashMap<>();
        for (int num : nums) {
            numToCnt.put(num, numToCnt.getOrDefault(num, 0) + 1);
        }
        // 大顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> numToCnt.get(b) - numToCnt.get(a));

        for (Integer num : numToCnt.keySet()) {
            pq.offer(num);
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll();
        }
        return res;
    }
}
