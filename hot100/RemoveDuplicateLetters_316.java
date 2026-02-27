package hot100;

import java.util.ArrayDeque;

/**
 * 去除重复字母
 * 解法1：直接看答案吧。
 * 单调栈+贪心
 * 思路：用单调栈增栈维护字典序最小的序列，贪心弹出栈顶更大的字符（前提是该字符后面还有），压入更小的字符
 * 保证结果最优
 * 1、charCount：判断字符后续是否还有，如果没有的话就不能出栈，避免丢失
 * 2、inStack：避免字符重复入栈
 * 3、stack：维护最终的字符序列
 *
 *
 */
public class RemoveDuplicateLetters_316 {
    public String removeDuplicateLetters(String s) {
        // 由于存储的是大小写字母，因为可以使用数组，不使用map
        // 数组大小可以设定为256，兼容扩展ascii码（0~255）
        // java中char本质是16位的无符号整数，但是英文字符，其ascii范围是0~127
        int[] charCnt = new int[256];
        // 初始化。后面遍历以后，也要维护，-1。
        int m = s.length();
        for (int i = 0; i < m; i++) {
            charCnt[s.charAt(i)]++;
        }
        boolean[] inStack = new boolean[256];
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char cur : s.toCharArray()) {
            // 遍历过就-1
            charCnt[cur]--;
            if (inStack[cur]) {
                continue;
            }
            while (!stack.isEmpty() && stack.peek() > cur) {
                if (charCnt[stack.peek()] <= 0) { // 栈顶的元素已经没有多余的元素了，不能再弹出，否则就丢失了
                    break;
                }
                inStack[stack.pop()] = false;
            }
            if (inStack[cur]) {
                continue;
            }
            stack.push(cur);
            inStack[cur] = true;
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }
        return res.reverse().toString();
    }

    public String removeDuplicateLetters1(String s) {
        // 由于存储的是大小写字母，因为可以使用数组，不使用map
        // 数组大小可以设定为256，兼容扩展ascii码（0~255）
        // java中char本质是16位的无符号整数，但是英文字符，其ascii范围是0~127
        int[] charCnt = new int[256];
        // 初始化。后面遍历以后，也要维护，-1。遍历到时不如栈，或者弹出栈，都要-1
        int m = s.length();
        for (int i = 0; i < m; i++) {
            charCnt[s.charAt(i)]++;
        }
        boolean[] inStack = new boolean[256];
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            char cur = s.charAt(i);
            while (!stack.isEmpty() && stack.peek() > cur) {
                if (charCnt[stack.peek()] > 1) {
                    // 维护charCnt
                    charCnt[stack.peek()]--;
                    inStack[stack.pop()] = false;
                } else {
                    break;
                }
            }
            if (inStack[cur]) { // 避免重复入栈
                // 维护charCnt
                charCnt[cur]--;
                continue;
            }
            stack.push(cur);
            inStack[cur] = true;
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        // System.out.println(new RemoveDuplicateLetters_316().removeDuplicateLetters("cdadabcc"));
        System.out.println(new RemoveDuplicateLetters_316().removeDuplicateLetters("abacb"));
    }
}
