package hot100;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 字符串解码
 * 解法1：× 这道题看起来没有什么难度，直接开撸。
 * 注意解析括号
 * 解法2：× 注意到也要解析括号，那就通过split得到数组就完事儿了
 * String.split("\\[|\\]") => 通过 "[" 或者 "]"分割
 * 解法3：我发现还有内嵌的，没那么简单啊。"3[a2[c]]"
 * 直接看答案把。嵌套的情况使得难度加大。
 * 使用栈结构
 * 1、遍历字符串，遇到数字、字母、左括号[是入栈
 * 2、遇到右括号]时，开始出栈处理
 * 2.1 先出栈指导遇到左括号，拼接出需要重复的子字符串(注意点，先按出栈顺序拼接，再统一通过stringbuilder.reverse来反转)
 * 2.2 再出栈数字字符，拼接出完整的重复次数 k（因为数字可能有不止一位数字）
 * 2.3 将子字符串重复k次后，重新压栈(压栈的时候，压的是字符，不是字符串)
 * 3、遍历结束后，将栈中的所有字符拼接得到最终解码结果
 */
public class DecodeString_394 {
    // 解法3：压栈
    public String decodeString(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c != ']') {
                stack.push(c);
            } else { // 遇到右括号后，需要处理栈中的字符
                // 压出字符串
                StringBuilder sb = new StringBuilder();
                while (!stack.isEmpty() && stack.peek() != '[') {
                    sb.append(stack.pop());
                }
                // 由于出栈时逆序的，因此要反转一下
                String subStr = sb.reverse().toString();
                // 接下来栈顶为 '['
                stack.pop();
                // 开始拼接数字
                sb = new StringBuilder();
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    sb.append(stack.pop());
                }
                int time = Integer.parseInt(sb.reverse().toString());
                // 将time次字符压回去
                for (int i = 0; i < time; i++) {
                    for (char c1 : subStr.toCharArray()) {
                        stack.push(c1);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    // 解法2：
    public String decodeString2(String s) {
        String[] split = s.split("\\[|\\]");
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < split.length; i += 2) {
            int time = Integer.parseInt(split[i]);
            String str = split[i + 1];
            for (int j = 0; j < time; j++) {
                res.append(str);
            }
        }
        return res.toString();
    }

//    解法1：
//    public String decodeString(String s) {
//        ArrayList<String[]> list = new ArrayList<>(); // 每个数组String[]有两个元素，String[0]为出现次数，1为字符串
//        int p = 0; // 指针
//        String[] subRes = new String[2];
//        StringBuilder sb = new StringBuilder();
//        while (p < s.length()) {
//            char c = s.charAt(p);
//            if (Character.isDigit(c)) {
//                subRes[1] = sb.toString();
//                sb = new StringBuilder();
//                list.add(new String[]{subRes[0], subRes[1]});
//                subRes = new String[2];
//                subRes[0] = String.valueOf(c);
//            } else {
//                sb.append(c);
//            }
//            p++;
//        }
//
//        // 开始处理list
//        StringBuilder res = new StringBuilder();
//        for (String[] strs : list) {
//            int time = Integer.parseInt(strs[0]); // 倍数
//            String str = strs[1];
//            for (int i = 0; i < time; i++) {
//                res.append(str);
//            }
//        }
//        return res.toString();
//    }

    public static void main(String[] args) {
//        System.out.println(new DecodeString_394().decodeString("3[a]2[bc]"));
//        System.out.println(Arrays.toString("3[a]2[bc]".split("\\[|\\]")));
    }
}
