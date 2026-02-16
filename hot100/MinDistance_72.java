package hot100;

import java.sql.Array;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 编辑距离
 * 解法1：没有想法
 * 解法2：labuladong，动态规划。
 * 解决两个字符串的动态规划问题，一般是用两个指针i、j分别指向两个字符串的头部或尾部，然后尝试写“状态转移方程”
 * 这个解法超时了。因为有很多重复的计算。
 * 解法3：labuladong，基于解法2做优化，需要做备忘录，避免大量重复计算。将i、j状态缓存起来，使用map，"i-j" -> value
 * 做缓存以后，还是有不正确的用例。但似乎性能问题已经解决了
 * 我的解法有点问题，输入为 word1 ="horse",word2 ="ros"时，预期输出为3，我的输出却为4。
 * 询问过豆包后发现：
 * 你的代码逻辑整体思路是对的（递归 + 备忘录），但备忘录的存储逻辑出现了关键错误：
 * 你在计算res1/res2/res3时，把dp(...) + 1的结果直接存入了备忘录
 * 但备忘录应该存储的是dp(i,j)的原始结果（不加操作数 1），而不是加 1 后的结果
 * 这导致后续从备忘录读取的值都偏大，最终计算结果错误
 * 将 map.put(getKey(i, j - 1), res1); 修改成 map.put(getKey(i, j - 1), res1 - 1); 就OK了
 * 解法4：labuladong，解法3的优化，备忘录可以使用二维数组，更简单一些
 *
 *
 */
public class MinDistance_72 {
    // 解法4：使用二维数组。先检查当前状态的备忘录→计算→存储当前状态的结果
    int[][] memo; // 备忘录

    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        // 重要：初始化备忘录
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dp(word1, m - 1, word2, n - 1); // 从尾部开始
    }

    private int dp(String s1, int i, String s2, int j) {
        // base case
        if (i < 0) return j + 1;
        if (j < 0) return i + 1;
        // 重要：查备忘录，避免重复子问题
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (s1.charAt(i) == s2.charAt(j)) { // skip，不增加操作数
            memo[i][j] = dp(s1, i - 1, s2, j - 1);
        } else { //  取以下三种操作最优解（最小操作树），操作树需要+1
            int res1 = dp(s1, i, s2, j - 1) + 1;// s1插入，i不动，s2的j继续往左移动
            int res2 = dp(s1, i - 1, s2, j) + 1; // s1删除，i往左移动，j不动
            int res3 = dp(s1, i - 1, s2, j - 1) + 1; // s1替换，i、j均往左移动
            memo[i][j] = min(res1, res2, res3);
        }
        return memo[i][j];
    }

    private String getKey(int i, int j) {
        return i + "-" + j;
    }

    private int min(int res1, int res2, int res3) {
        int min = res1;
        min = Math.min(min, res2);
        min = Math.min(min, res3);
        return min;
    }

    HashMap<String, Integer> map = new HashMap<>();

    // 解法3：将i、j状态缓存起来，使用map，"i-j" -> value
    public int minDistance3(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        return dp3(word1, m - 1, word2, n - 1); // 从尾部开始
    }

    // 函数定于：返回 s1[0,...,i] 转换成 s2[0,...,j]的最短距离（最小操作数）
    private int dp3(String s1, int i, String s2, int j) {
        // base case
        if (i < 0) {
            int res = j + 1; // 如果i=0，相当于s1为空，需要做j+1个插入操作
            map.put(getKey(i, j), res); // 备忘录
            return res;
        }
        if (j < 0) {  // 如果j=0，相当于s2为空，需要做i+1个删除操作
            int res = i + 1;
            map.put(getKey(i, j), res); // 备忘录
            return res;
        }

        if (s1.charAt(i) == s2.charAt(j)) { // skip，不增加操作数
            if (map.containsKey(getKey(i - 1, j - 1))) {
                return map.get(getKey(i - 1, j - 1));
            }
            int res = dp3(s1, i - 1, s2, j - 1); //备忘录应该存储的是dp(i,j)的原始结果（不加操作数 1），而不是加 1 后的结果
            map.put(getKey(i - 1, j - 1), res);
            return res;
        } else { //  取以下三种操作最优解（最小操作树），操作树需要+1
            int res1;
            if (map.containsKey(getKey(i, j - 1))) {
                res1 = map.get(getKey(i, j - 1)) + 1; // s1插入，i不动，s2的j继续往左移动
            } else {
                res1 = dp3(s1, i, s2, j - 1) + 1;
                map.put(getKey(i, j - 1), res1 - 1); //备忘录应该存储的是dp(i,j)的原始结果（不加操作数 1），而不是加 1 后的结果
            }

            int res2;
            if (map.containsKey(getKey(i - 1, j))) {
                res2 = map.get(getKey(i - 1, j)) + 1; // s1删除，i往左移动，j不动
            } else {
                res2 = dp3(s1, i - 1, s2, j) + 1;
                map.put(getKey(i - 1, j), res2 - 1); //备忘录应该存储的是dp(i,j)的原始结果（不加操作数 1），而不是加 1 后的结果
            }

            int res3;
            if (map.containsKey(getKey(i - 1, j - 1))) {
                res3 = map.get(getKey(i - 1, j - 1)) + 1;
            } else {
                res3 = dp3(s1, i - 1, s2, j - 1) + 1; // s1替换，i、j均往左移动
                map.put(getKey(i - 1, j - 1), res3 - 1); //备忘录应该存储的是dp(i,j)的原始结果（不加操作数 1），而不是加 1 后的结果
            }
            return min(res1, res2, res3);
        }
    }

    // 解法2:
    public int minDistance2(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        return dp2(word1, m - 1, word2, n - 1); // 从尾部开始
    }

    // 函数定于：返回 s1[0,...,i] 转换成 s2[0,...,j]的最短距离（最小操作数）
    private int dp2(String s1, int i, String s2, int j) {
        // base case
        if (i < 0) return j + 1; // 如果i=0，相当于s1为空，需要做j+1个插入操作
        if (j < 0) return i + 1; // 如果j=0，相当于s2为空，需要做i+1个删除操作

        if (s1.charAt(i) == s2.charAt(j)) { // skip，不增加操作数
            return dp2(s1, i - 1, s2, j - 1);
        } else { //  取以下三种操作最优解（最小操作树），操作树需要+1
            int res1 = dp2(s1, i, s2, j - 1) + 1;// s1插入，i不动，s2的j继续往左移动
            int res2 = dp2(s1, i - 1, s2, j) + 1; // s1删除，i往左移动，j不动
            int res3 = dp2(s1, i - 1, s2, j - 1) + 1; // s1替换，i、j均往左移动
            return min(res1, res2, res3);
        }
    }
}
