package hot100.priorityqueue;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 数组中的第K个最大元素
 * 解法1：先将数组排序，然后再取第k个值。但是时间复杂度是 O(nlogN)，不符合题目要求O(n)
 * <p>
 * 解法2：不能再排序完后再取，需要在遍历一次后，就知道哪个值排第几位
 * 我没有思路。看人家怎么做的。
 * 大顶堆，这样弹出来的才是最大的，第k个最大元素
 * 时间复杂度还是 O(NlogN)，不满足要求啊
 * <p>
 * 解法3：优先级队列的优化思路，但时间复杂度 O(N * logK) 还是不满足要求
 * 思路：我们只要第k大的元素，不需要把所有元素都建成堆，浪费性能。只维护一个大小为k的堆
 * 维护小顶堆还是大顶堆呢？维护小顶堆
 * 1）先插入前k个元素到小顶堆
 * 2）再插入第k+1个元素a，如果a>堆顶，则替换堆顶元素并调整堆（自动完成），后续元素依次类推
 * 3）遍历结束后，小顶堆内就是k个最大元素，且顶堆是第k个最大元素
 * 由于每次都是基于k个元素进行swim和sink，因此时间复杂度为O(N * logK)
 * <p>
 * 解法4：labuladong，快排算法（快速选择），只需要O(N)的时间复杂度
 * 快速排序和快速选择的区别：
 * 快速排序是递归排序pivot（基准值）的左右两侧 O(nlogn)
 * 快速选择时只递归处理包含目标的一侧，平均O(n)，最坏O(n^2)
 * 即快速排序是“两边都要”，快速选择是“只找一边”，所以时间复杂度有所下降
 * 快速选择解决此第K大元素问题的核心原理：
 * 1）分区：选一个基准值pivot，把数组分成 大于pivot的左区域、pivot、小于pivot的右区域
 * 2）如果pivot的最终索引是index，则
 * - if index == k-1（索引从0开始）：pivot就是第k大元素，直接返回
 * - if index > k-1，第k大元素在左区域，递归处理左区域
 * - if index < k-1，。。。在右区域，递归处理右区域
 * 每次分区操作是O(n)，之后只递归一半的元素，总代价为 T(n)=n+n/2+n/4+...+1 ≈ 2n => O(N)
 * 注意：以上快速选择解法如果遇到在数组中包含大量元素时，划分后的index往往是子数组第一个元素的下标，退化成O(n^2)
 * 解决办法：。。。
 * 先不做第四个方法，
 * 参考链接 @link[https://leetcode.cn/problems/kth-largest-element-in-an-array/solutions/3799769/on-kuai-su-xuan-ze-suan-fa-pythonjavaccg-lh7c/?envType=problem-list-v2&envId=2cktkvj]
 */
public class FindKthLargest_215 {
    // 解法3
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k); // 默认小顶堆
        // 初始化一个大小为k的小顶堆
        for (int i = 0; i < k; i++) {
            pq.offer(nums[i]);
        }
        // 后续遍历中进行元素替换，将小的去掉，剩下的就是大的了，而且最顶那个是大元素中的最小那个
        // 所以大小为k的小顶堆中，最后顶堆就是第k个最大值了
        for (int i = k; i < nums.length; i++) {
            int cur = nums[i];
            if (cur < pq.peek()) {
                continue;
            }
            // 替换，这里是 O(2*logK) => O(logK)
            pq.poll();
            pq.offer(cur);
        }
        return pq.peek();
    }

    // 解法2
    public int findKthLargest2(int[] nums, int k) {
        // 大顶堆（降序），自然排序（升序）才是小顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        // O(NlogN)
        for (int num : nums) {
            pq.offer(num); // 这里涉及到int的自动装箱
        }
        int res = 0;
        for (int i = 0; i < k; i++) {
            res = pq.poll();
        }
        return res;
    }


    // 解法1：能通过，但是不符合题目要求
    public int findKthLargest1(int[] nums, int k) {
        // Arrays.sort(nums);
        // return nums[nums.length - k];

        // nums = Arrays.stream(nums)
        //         .boxed()
        //         .sorted(Collections.reverseOrder())
        //         .mapToInt(Integer::intValue)
        //         .toArray();
        // return nums[k - 1];

        Integer[] array = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(array, Collections.reverseOrder());
        return array[k - 1];
    }
}
