package hot100;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 * 解法1：我猜测是使用栈。直接看答案
 * 实际上是回溯算法。labualdong。
 * 关于括号问题，有如下性质
 * 1）一个合法括号组合的左括号数量一定等于右括号数量
 * 2）对于一个合法的括号字符串组合p，必然对于任何0<=i<len(p)，都有：字串p[0..i]中左括号数量都大于或等于右括号数量
 * 注意：第一个判断是if (left > right)，不是 if (left < right)
 */
public class GenerateParenthesis_22 {
    public List<String> generateParenthesis(int n) {
        ArrayList<String> res = new ArrayList<>();
        StringBuilder track = new StringBuilder();
        backtrack(n, n, track, res);
        return res;
    }

    // left、right分别表示左右括号的可用数量，track是记录括号组合情况，res用来承载答案
    private void backtrack(int left, int right, StringBuilder track, ArrayList<String> res) {
        if (left > right) { // left > right，表示右括号消耗更多 => 左括号少于右括号，不合法
            return;
        }
        if (left < 0 || right < 0) { // 左/右括号数量消耗到负数，不合法
            return;
        }
        if (left == 0 && right == 0) { // 左右括号刚好用完
            res.add(track.toString());
        }
        // 回溯
        // 拼接左括号
        backtrack(left - 1, right, track.append("("), res);
        // 撤销选择
        track.deleteCharAt(track.length() - 1);

        backtrack(left, right - 1, track.append(")"), res);
        track.deleteCharAt(track.length() - 1);
    }
}
