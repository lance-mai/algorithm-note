package hot100;

/**
 * 最长回文子串
 * 解法1：滑动窗口（解决不了问题）
 * 灵魂三问：
 * 1、什么时候扩大窗口：尽可能扩大窗口，先扩大后修正
 * 2、什么时候缩小窗口：当不是回文子串时（即窗口无效），则缩小窗口，直到窗口有效
 * 3、什么时候更新结果：当缩小完窗口后，窗口有效，可以记录结果
 * 问题：每次窗口都要判断是否为回文串么？时间复杂度会不会很高？
 * 解题过程：不能使用滑动窗口，因为缩小窗口也不能保证是回文，本来不是回文，扩大窗口也可能变成回文
 * <p>
 * 解法2：双指针（labuladong）
 * 双指针技巧主要是两类：左右指针和快慢指针
 * 左右指针：两个指针相向而行或相背而行；快慢指针：两个指针相向而行，一快一慢
 * 此题适合快慢指针（错误）
 * 此题适合双指针，相向而行（正确）
 * 针对指针 i、i+1，每次都分别寻找以i为中心（奇数子串）和以i、i+1为中心（偶数子串）的最长回文串，取长度较长的
 * 求解最长回文串时，中间向两边扩散去求解。重点在于求解回文串
 *
 */
public class LongestPalindrome_5 {
    // labuladong解法。双指针
    public String longestPalindrome(String s) {
        String result = "";
        // 遍历 String
        for (int i = 0; i < s.length(); i++) {
            // 求解以i为中心的最长回文串
            String subStr1 = getPalindrome(s, i, i);
            // 求解以i、i+1为中心的最长回文串
            String subStr2 = getPalindrome(s, i, i + 1);
            result = result.length() > subStr1.length() ? result : subStr1;
            result = result.length() > subStr2.length() ? result : subStr2;
        }
        return result;
    }

    // 求字符串最长回文子串
    // 中心向两边扩散
    private String getPalindrome(String s, int left, int right) {
        String result = "";
        // 注意边界条件的判断
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            result = s.substring(left, right + 1);
            left--;
            right++;
        }
        return result;
    }

    // 我的解法1：滑动窗口，解决不了问题。灵魂三问回答不了
    // public String longestPalindrome(String s) {
    //     // int left = 0;
    //     int right = 0;
    //     LinkedList<Character> longest = new LinkedList<>();
    //     // 不要使用arrayDeque，不然后面不好维护
    //     // ArrayDeque<Character> window = new ArrayDeque<>();
    //     LinkedList<Character> window = new LinkedList<>();
    //     // 当right = s.length时，为外边界，这时候循环停止
    //     while (right < s.length()) {
    //         char in = s.charAt(right);
    //         right++;
    //         // 更新窗口信息
    //         window.addLast(in);
    //         // 判断是否满足条件，即是否为回文串，如果不是回文串，则缩小窗口
    //         while (!window.isEmpty() && !isPalindrome(window)) {
    //             // char out = s.charAt(left);
    //             // left++;
    //             // 更新窗口信息
    //             window.removeFirst();
    //         }
    //         // 更新结果
    //         longest = longest.size() > window.size() ? longest : window;
    //     }
    //     return longest.stream().map(Object::toString).collect(Collectors.joining());
    // }
    //
    // // 判断是否为回文字符串
    // private boolean isPalindrome(LinkedList<Character> window) {
    //     if (window.size() <= 1) {
    //         return true;
    //     }
    //     // 这里对window元素有影响了，有问题。不应该改变window的元素
    //     // Character first = window.removeFirst();
    //     // Character last = window.removeLast();
    //     // if (first != last) {
    //     //     return false;
    //     // }
    //     int first = 0;
    //     int last = window.size() - 1;
    //     while (first < last) {
    //         if (!window.get(first).equals(window.get(last))) {
    //             return false;
    //         }
    //         first++;
    //         last--;
    //     }
    //     return true;
    // }

    public static void main(String[] args) {
        System.out.println(new LongestPalindrome_5().longestPalindrome("babad"));
    }
}
