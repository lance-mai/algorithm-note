package hot100;

/**
 * 除自身以外数组的乘积
 * 花了25分钟，没有想到解决方法
 * 看了题解思路后，才发现没有好好审题，题目中给出提示，前缀乘积 和 后缀乘积
 * 最终花了45分钟，搞定了这道题
 * 挑战：O(1)的额外空间复杂度 => 不会
 */
public class ProductExceptSelf_238 {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        // 获取 前缀积 和 后缀积
        int[] prefixProduct = new int[len + 1];
        prefixProduct[0] = 1;
        int[] suffixProduct = new int[len + 1];
        suffixProduct[suffixProduct.length - 1] = 1;
        for (int i = 0; i < len; i++) {
            prefixProduct[i + 1] = prefixProduct[i] * nums[i];
        }
        for (int i = len - 1; i >= 0; i--) {
            suffixProduct[i] = suffixProduct[i + 1] * nums[i];
        }
        int[] answer = new int[len];
        for (int i = 0; i < len; i++) {
            answer[i] = prefixProduct[i] * suffixProduct[i + 1];
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
        int[] result = new ProductExceptSelf_238().productExceptSelf(nums);
        System.out.println(result);
    }
}
