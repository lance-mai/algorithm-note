package hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 * 解法1：暴力方法。三层for循环 O(n^3)
 * 解法2：hash，不会
 * 解法3：双指针。只要数组是有序的，就要想到双指针技巧
 * 其实就是 两数之和的解法，在上面套一层for循环穷举 -》超出时间限制了。为什么呢？
 * 是因为死循环了么？本地执行发现，确实是死循环了
 * 问题出在这里：while (left < right && nums[right] == nums[right - 1]) right--;
 * 这条语句原本是为了把重复的数据跳过，但问题就是如果不满足这个重复条件，原本该--的动作也不做了，导致死循环
 * 上面语句改以后，发现还是死循环，原来是因为：
 * int leftN = nums[left]; int rightN = nums[right]; 这两条语句放在while循环外了，正确做法是放在while循环内部
 * 这回OK了
 */
public class ThreeSum_15 {
    public List<List<Integer>> threeSum(int[] nums) {
        // 先对nums进行排序，再结合双指针技巧
        Arrays.sort(nums); // 升序
        // 降序写法
        // int[] sortedNums = Arrays.stream(nums).boxed().sorted((o1, o2) -> o2 - o1).mapToInt(Integer::intValue).toArray();
        ArrayList<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // 对于当前数字cur，寻找另外两个不重复索引的数x、y，使得 x + y + cur = 0(target)
            // 开头应该把左边的数都排除，因为前面的循环已经遍历过了，不需要再次遍历
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int leftN = nums[left];
                int rightN = nums[right];
                int compute = nums[i] + leftN + rightN;
                if (compute > 0) {
                    while (left < right && rightN == nums[right]) right--;
                } else if (compute < 0) {
                    while (left < right && leftN == nums[left]) left++;
                } else {
                    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(leftN, rightN, nums[i]));
                    result.add(list);
                    // 当得到其中一个答案以后，应该怎么操作 left、right呢？ -》 两个指针都要移动
                    while (left < right && leftN == nums[left]) left++;
                    while (left < right && rightN == nums[right]) right--;
                }
            }
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) i++;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new ThreeSum_15().threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}
