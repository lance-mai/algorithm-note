package hot100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 单词拆分(这道题有点难，要好好理解)
 * 思路：感觉有点像是全排列，但是到底要排列多少次去比较呢
 * 不会。直接看答案
 * 解法1；我尝试做的。回溯
 * 超时了。需要剪枝。如何剪枝：
 * 备忘录，存储不能切分的子串（子树），从而避免重复计算：HashSet<String> memo = new HashSet<>();
 *
 */
public class WordBreak_139 {
    // 解法1：回溯
    List<String> wordDict;
    boolean found = false;
    ArrayList<String> track = new ArrayList<>(); // 记录回溯算法的路径
    // （剪枝）备忘录，存储不能切分的子串（子树），从而避免重复计算
    HashSet<String> memo = new HashSet<>();

    public boolean wordBreak(String s, List<String> wordDict) {
        this.wordDict = wordDict;
        backtrack(s, 0);
        return found;
    }

    // start表示字符串拼接的尾巴
    private void backtrack(String s, int start) {
        // base case
        if (found) { // 找到答案就不再递归搜索
            return;
        }
        if (start == s.length()) { // 整个s都被拼接完了，可以返回
            found = true;
            return;
        }
        // -----剪枝逻辑，查询字串是否被计算过------------------------
        String suffix = s.substring(start); // 后缀
        if (memo.contains(suffix)) {
            // 当前子串（子树）不能被切分，就不用继续递归了
            return;
        }
        // -----------------------------
        // 回溯算法框架
        // 注意：这里的做选择和撤回选择好像没有什么用
        for (String word : wordDict) {
            int len = word.length();
            // 符合匹配条件
            if ((start + len) <= s.length() && s.substring(start, start + len).equals(word)) {
                // 走下一步
                // 做选择
                track.add(word);
                // 回溯
                backtrack(s, start + len);
                // 撤销选择
                track.removeLast();
            }
        }

        // -----剪枝逻辑，将不能切分的字串记录到备忘录------------------------
        if (!found) {
            memo.add(suffix);
        }
        // -----------------------------
    }
}
