package hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 合并区间
 * 解法1：先升序排序，再进行合并
 * 报错，越界了：因为如果合并以后，有些数组就不add到list里面，那么下标就不能每次访问时+1，而是访问最后一个元素 length-1
 */
public class Merge_56 {
    // 解法1
    public int[][] merge(int[][] intervals) {
        int[][] lists = Arrays.stream(intervals).sorted(Comparator.comparingInt(o -> o[0])).toArray(int[][]::new);
        ArrayList<int[]> result = new ArrayList<>();
        result.add(lists[0]);
        for (int i = 1; i < lists.length; i++) {
            int[] curList = lists[i];
            // int[] preList = result.get(i - 1);
            int[] preList = result.get(result.size() - 1);
            if (preList[1] < curList[0]) { // 分离
                result.add(curList);
                continue;
            }
            preList[1] = Math.max(preList[1], curList[1]);
        }
        return result.toArray(int[][]::new);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {8, 10}, {2, 6}, {15, 18}};
        System.out.println(new Merge_56().merge(intervals));
    }
}
