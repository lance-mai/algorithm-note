package interview;

import java.util.ArrayList;
import java.util.PriorityQueue;

// 上海大裂谷公司
public class Merge {
    // 两个有序数组 合并成一个 有序数组
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(3);
        list1.add(5);
        list1.add(7);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(4);
        list2.add(6);
        list2.add(8);
        priorityQueue(list1, list2);
        // twoPoint(list1, list2);
    }

    // 双指针
    private static void twoPoint(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        int left = 0;
        int right = 0;
        ArrayList<Integer> result = new ArrayList<>();
        while (left < list1.size() || right < list2.size()) {
            int val1 = left < list1.size() ? list1.get(left) : Integer.MAX_VALUE;
            int val2 = right < list2.size() ? list2.get(right) : Integer.MAX_VALUE;
            if (val1 < val2) {
                result.add(val1);
                left++;
            } else {
                result.add(val2);
                right++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Integer i : result) {
            sb.append(i).append(",");
        }
        System.out.println(sb);
    }

    private static void priorityQueue(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 小顶堆
        for (Integer i : list1) {
            pq.offer(i);
        }
        for (Integer j : list2) {
            pq.offer(j);
        }
        list1 = new ArrayList<>(8);
        while (!pq.isEmpty()) {
            list1.add(pq.poll());
        }
        StringBuilder sb = new StringBuilder();
        for (Integer i : list1) {
            sb.append(i).append(",");
        }
        System.out.println(sb);
    }
}
