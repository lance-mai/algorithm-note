package labuladong;

/**
 * 前缀和数组
 * 作用：前缀和适用于快速、频繁地计算一个索引区间内的元素之和（空间换时间）
 * 局限性：
 * 1、只能用于原数组不变的情况，如果原数组变化，那么前缀和结果就会改变，相当于重新计算，那么前缀和技巧就不占优势了
 * 2、前缀和只适合逆运算场景。即可以通过 x + 2 = 5推导出x = 3，如果是 max(x,8) = 8，解不出x的值，前缀和也就不适用了
 * 以上这两个局限性，可以使用“线段树”来解决
 */
class NumArray {
    int[] preSum;

    public NumArray(int[] nums) {
        preSum = new int[nums.length + 1];
        preSum[0] = 0;
        for (int i = 1; i < nums.length + 1; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int left, int right) {
        return preSum[right + 1] - preSum[left];
    }
}
