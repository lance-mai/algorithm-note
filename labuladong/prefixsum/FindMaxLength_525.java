package labuladong.prefixsum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 连续数组
 * 1、前缀和。看了20分钟，还是把问题想复杂了。。。
 * 2、参考labuladong的思路，将0替换为-1，花了1h，捉虫（key和value搞反了，key应该是prefix，value应该是indexList）
 * 但是内存超出限制了，该怎么怎么办呢？ 检查了一下，发现是我将数组的capacity设置为n/2，因此超出限制。将该阈值设置为默认(16)后问题解决。
 * 全部通过。但是该解法用时较长，为什么呢？我分析问题出在List的排序上。排序最快也是nlogn。
 * 如何快速从一个数组中得到其最大元素和最小元素的差值。
 * 我分析发现，其实map中存的value即list，天然就是一个有序数组，不用在排序啦。不排序后提速了，但还是不够。
 * 最终解决方案：凭借着indexList的天然升序的特点，我在遍历数组nums组装prefix过程中，就会进行maxLen的比较。最终性能打败了90%用户
 */
public class FindMaxLength_525 {
    public int findMaxLength(int[] nums) {
        // 为了方便计算，将0替换为-1
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }
        // 使用map记录相同前缀和的下标
        HashMap<Integer, List<Integer>> prefixMapIndex = new HashMap<>();
        int[] prefix = new int[n + 1];
        prefixMapIndex.put(prefix[0], new ArrayList<>(List.of(0)));
        int maxLen = 0;
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
            List<Integer> indexList = prefixMapIndex.get(prefix[i]);
            if (indexList == null) {
                indexList = new ArrayList<>();
                indexList.add(i);
                prefixMapIndex.put(prefix[i], indexList);
            } else {
                maxLen = Math.max(maxLen, i - indexList.getFirst());
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1};
        int maxLength = new FindMaxLength_525().findMaxLength(nums);
        System.out.println(maxLength);
    }
}
