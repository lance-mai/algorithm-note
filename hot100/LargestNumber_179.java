package hot100;

import java.util.Arrays;
import java.util.List;

/**
 * 最大数
 * 我的思路：自定义排序
 * 解法1：自定义排序。
 * 当3和30比较时，3排在前面
 * 当3和34比较时，34排在前面
 * 解法2：直接看答案吧。
 * 将数组转化成字符串后直接进行排序比较
 * 使用自定义比较器，comparator：(b+a).compareTo(a+b)，如果大于0，则会交换a、b
 * 注意：
 * // 特例：[0, 0]，返回的是0
 * // 补充，不止[0,0]，如果数组都是0的话，也是只返回一个0
 * 补充：Comparator<String>接口中，compare(a,b)方法的返回值规则是：
 * 1、返回负数：表示a排在b前面a,b（a<b）
 * 2、返回正数：表示b排在a前面b,a
 * 3、返回0，a、b相等，与顺序无关
 *
 *
 */
public class LargestNumber_179 {
    // 解法2
    public String largestNumber(int[] nums) {

        if (nums.length == 2 && nums[0] == 0 && nums[1] == 0) {
            return "0";
        }
        List<String> list = Arrays.stream(nums).mapToObj(String::valueOf).sorted((a, b) -> {
            // 如果 b+a大于a+b，则交换a、b
            // 例如a="3"、b="30"，则303.compareTo(330)，返回的是-1。返回-1时，外面的comprarator接口就会交换a、b参数位置
            return (b + a).compareTo(a + b);
        }).toList();

        // 特例：[0, 0]，返回的是0
        // 补充，不止[0,0]，如果数组都是0的话，也是只返回一个0
        // 清除所有的高位0
        // int i = 0;
        // while (!res.isEmpty() && res.charAt(i) == '0') {
        //     res.deleteCharAt(0);
        // }
        // return res.isEmpty() ? "0" : res.toString();

        // 不用清除高位0，直接判断高位是否为0，如果为0，那就意味着排在第一位的就是0，后面的肯定也是0
        if (list.get(0).equals("0")) {
            return "0";
        }

        StringBuilder res = new StringBuilder();
        for (String str : list) {
            res.append(str);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(new LargestNumber_179().largestNumber(new int[]{0, 0}));
    }

    // 解法1
    // public String largestNumber(int[] nums) {
    //     Arrays.stream(nums).boxed().sorted(new Comparator<Integer>() {
    //         @Override
    //         public int compare(Integer o1, Integer o2) {
    //             // 拆分成数组
    //             if (o1 < 10) {
    //
    //             }
    //         }
    //     })
    // }
}
