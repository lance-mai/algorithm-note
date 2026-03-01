package bytedance;

public class LongestPalindrome_5 {
    String res = "";

    public String longestPalindrome(String s) {
        int n = s.length();
        for (int i = 0; i < n; i++) {
            // 以两个字符为起点、以一个字符为起点
            find(i, i, s);
            find(i, i + 1, s);
        }
        return res;
    }

    private void find(int left, int right, String s) {
        StringBuilder sb = new StringBuilder();

        while (left >= 0 && right < s.length()) {

            char leftChar = s.charAt(left);
            char rightChar = s.charAt(right);
            if (leftChar == rightChar && left == right) {
                sb.append(rightChar);
            } else if (leftChar == rightChar) {
                sb.insert(0, leftChar);
                sb.append(rightChar);
            } else {
                break;
            }
            left--;
            right++;
        }
        res = res.length() < sb.length() ? sb.toString() : res;
    }

    public static void main(String[] args) {
        System.out.println(new LongestPalindrome_5().longestPalindrome("a"));
    }
}
