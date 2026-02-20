package hot100;

/**
 * 回文字串
 * 解法1：遍历每一个元素，从该元素辐射到两端，看看有多少回文子串，记录下来 时间复杂度 O(n^2)
 * 结果部分出错，为什么：我忽略了一个事实，从两个字符扩散出去也可以是回文串。
 * 第二个问题出现了：当没有回文串的时候，我还是往外扩散了，正确的做法应该是停止基于该节点的扩展（因为后面不再可能是回文串），转移到下一个节点
 * 以上两个问题解决了，还是有问题，对于输入"leetcode"，输出10，预期结果是9
 * 问一下豆包：原来第二个问题解决时，只对双节点扩散解决了，但是单节点，扩散没有解决。OK，搞定
 * 所以，代码复用是多么重要，利于维护，不担心改漏
 */
public class CountSubStrings_647 {
    public int countSubstrings(String s) {
        int res = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int left = i, right = i; // 从当前单节点向左右扩散
            while (left >= 0 && right < n) {
//                if (s.charAt(left) == s.charAt(right)) {
//                    res++;
//                }
//                left--;
//                right++;
                if (s.charAt(left) == s.charAt(right)) {
                    res++;
                    left--;
                    right++;
                } else {
                    break; // 左右不匹配，不再可能是回文串，需要遍历下一个基点i
                }
//                left--;
//                right++;
            }
        }
        for (int i = 0; i < n; i++) {
            int left = i, right = i + 1; // 从当前双节点向左右扩散
            while (left >= 0 && right < n) {
                if (s.charAt(left) == s.charAt(right)) {
                    res++;
                    left--;
                    right++;
                } else {
                    break; // 左右不匹配，不再可能是回文串，需要遍历下一个基点i
                }
//                left--;
//                right++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        String s = "aaa";
//        String s = "fdsklf";
        String s = "leetcode";
        System.out.println(new CountSubStrings_647().countSubstrings(s));
    }
}
