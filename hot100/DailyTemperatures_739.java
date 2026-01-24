package hot100;

import java.util.ArrayDeque;

/**
 * 每日温度
 * 解法1 ❌️：（暴力解法双层for循环当然会，但没意义）不会做，估计是前缀和
 * 解法2 ❌️：比较复杂，没写出来。hash表，因为温度是有限的，因此创建一个长度为温度最大值的数组，其下标代表温度，
 * 其元素值代表第一次出现该温度的temperature下标。
 * 解法3 ✅️：单调栈，简单有套路
 */
public class DailyTemperatures_739 {
    // 单调栈解法
    public int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = temperatures.length - 1; i >= 0; i--) {
            int cur = temperatures[i];
            while (!stack.isEmpty() && temperatures[stack.peek()] <= cur) {
                stack.pop();
            }
            answer[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return answer;
    }

    // hash暴力解法
    // public int[] dailyTemperatures(int[] temperatures) {
    //     int[] tempHash = new int[101];
    //     Arrays.fill(tempHash, -1);
    //     for (int i = 0; i < temperatures.length; i++) {
    //         int temp = temperatures[i];
    //         int tempIndex = tempHash[temp];
    //         // 该温度值还没出现过，则记录到哈希表中
    //         if (tempIndex < 0) {
    //             tempHash[temp] = i;
    //         }
    //     }
    //     // 遍历温度表
    //     int[] answer = new int[temperatures.length];
    //     for (int i = 0; i < temperatures.length; i++) {
    //         int temp = temperatures[i];
    //         int nextIndex = Integer.MAX_VALUE;
    //         // 倒序访问hash表
    //         for (int j = tempHash.length - 1; j >= 1; j--) {
    //             if (j <= temp) {
    //                 break;
    //             }
    //             int tempIndex = tempHash[j];
    //             if (tempIndex < 0 || tempIndex <= i) {
    //                 continue;
    //             }
    //             nextIndex = Math.min(nextIndex, tempIndex);
    //         }
    //         answer[i] = nextIndex == Integer.MAX_VALUE ? 0 : nextIndex - i;
    //     }
    //     return answer;
    // }

    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        // System.out.println(Arrays.toString(new DailyTemperatures_739().dailyTemperatures(temperatures)));
    }
}
