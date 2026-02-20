package hot100;

/**
 * 寻找重复数
 * 解法1：和之前的多个成双的数中找其中一个落单的数的情况不同，不能使用异或。
 * 参考豆包解决方案，弗洛伊德龟兔赛跑算法，将数组想象成链表，有重复数字，说明形成环。就可以使用环检测算法来做了
 * 但是数组怎么变成和链表那样的处理方式呢？将数组的值作为下一个值的索引。
 * 为什么能这么做呢？因为题目规定：给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n）
 * 双指针，环检测+检测环入口（重复的数）
 * 接下来就是自己独立做。
 * 这里有个问题，因为一开始slow和fast都是先前进再判断，因此如果开头就是重复数字的话，就会遗漏
 * 问题根源：重置 slow 后，你先移动指针再判断相等，跳过了「初始重置后 slow 和 fast 是否相等」的判断
 *
 */
public class FindDuplicate_287 {
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];
        while (true) {
            slow = nums[slow]; // 相当于slow = slow.next
            fast = nums[nums[fast]]; // 相当于 fast = fase.next.next
            if (slow == fast) {
                break;
            }
        }
        // 此时fast和slow即为重复数。
        // slow从头开始，slow和fast匀速前进，直到再次相遇
        slow = nums[0];
        while (true) {
            if (slow == fast) {
                return slow;
            }
            slow = nums[slow];
            fast = nums[fast];
        }
        // 问题根源：重置 slow 后，你先移动指针再判断相等，跳过了「初始重置后 slow 和 fast 是否相等」的判断
//        while (true) {
//            slow = nums[slow];
//            fast = nums[fast];
//            if (slow == fast) {
//                return slow;
//            }
//        }
    }
}
