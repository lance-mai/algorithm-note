package additional;

/**
 * 字符串相乘
 * 我的思路：分解。一个字符串分解成个十百千万的数组
 * 解法1：不做
 * 解法2：直接看答案。labuladong。https://labuladong.online/zh/algo/practice-in-action/multiply-strings/
 * 将乘法分解成一个数中的每个数字和另一个数的每个数字分别相乘，使用char数组承载
 * 使用i、j分别去这两个数游走。模仿计算过程，倒着遍历，从个位数开始
 * 有个规律：num1[i]和num2[j]的乘积对应的就是 result[i+j]【低位】和result[i+j+1]【高位】这两个位置
 * 我解答的时候有错误，需要修正。
 * 修正1：初始化char[]时，要显性化赋值为'0'，不然会默认\u0000，即ASCII码的0
 * 初始化成int[]数组，就没那么多事了。hahaha
 * 修正2：结果为空时需要处理。
 * 修正3：高位需要叠加。正确：res[high] += sum / 10;  错误：res[high] = sum / 10;
 * 为什么低位不用叠加。因为低位计算sum的时候就加上了。int sum = product + res[low];
 * 补充：初始化成int[]数组，就没那么多事了。
 */
public class Multiply_43 {
    public String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        // 初始化成int[]数组，就没那么多事了。
        // char[] res = new char[n + m]; // 根据数学计算，m位和n位数字相乘，结果不超过m+n位
        int[] res = new int[n + m]; // 根据数学计算，m位和n位数字相乘，结果不超过m+n位
        // 初始化char[]
        // Arrays.fill(res, '0');

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // 计算乘积
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                // 承载结果位置
                int low = i + j + 1; // 低位
                int high = i + j; // 高位
                // 原来低位的值为 res[low]，需要叠加乘积结果
                int sum = product + res[low];
                res[low] = sum % 10;
                // res[high] = sum / 10;
                res[high] += sum / 10;
                // res[low] = (char) (sum % 10 + '0');
                // res[high] = (char) (sum / 10 + '0');
                // 高位不是直接加，而是
            }
        }
        // 去掉高位多余的0
        int start = 0; // 表示不为0的起点
        while (start < res.length && res[start] == 0) {
            start++;
        }
        // 此时start是不为0的起点
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < res.length; i++) {
            sb.append(res[i]);
        }
        // return sb.toString();
        // 结果为空时需要处理
        return sb.isEmpty() ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Multiply_43().multiply("2", "3"));
    }
}
