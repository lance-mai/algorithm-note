package labuladong.stackandqueue;

import java.util.LinkedList;

/**
 * 简化路径。做了半个小时，没做出来。不知道怎么使用栈解决问题
 * 关键点：需要先用 / split出多个String，基于这些string做压栈出栈操作
 * 注意if的嵌套逻辑 if ("..".equals(segment)) {
 * if (!stack.isEmpty()) {}   不等价于 if ("..".equals(segment) && !stack.isEmpty())
 */
public class SimplifyPath {
    // private static final char SLASH = '/';
    // private final char DOT = '.';

    public String simplifyPath(String path) {
        // ---------------labuladong的解法----------------
        String[] segments = path.split("/");
        LinkedList<String> stack = new LinkedList<>();
        for (String segment : segments) {
            if (segment.isEmpty() || ".".equals(segment)) {
                continue;
            }
            if ("..".equals(segment)) {
                if (!stack.isEmpty()) {
                    stack.removeFirst();
                }
                continue;
            }
            stack.addFirst(segment);
        }
        StringBuilder str = new StringBuilder();
        while (!stack.isEmpty()) {
            str.insert(0, "/" + stack.removeFirst());
        }
        return str.length() == 0 ? "/" : str.toString();

        // ---------------我的解法，没做出来----------------
        // LinkedList<Character> stack = new LinkedList<>();
        // HashMap<Character, Integer> charToCnt = new HashMap<>();
        // charToCnt.put(SLASH, 0);
        // charToCnt.put(DOT, 0);
        // char[] chars = path.toCharArray();
        // for (int i = 0; i < path.toCharArray().length; i++) {
        //     char c = chars[i];
        //     Integer slashCnt = charToCnt.get(SLASH);
        //     Integer dotCnt = charToCnt.get(DOT);
        //     if (c == SLASH) {
        //         // 清空 dot
        //         if (dotCnt == 1) {
        //             // do nothing
        //         }
        //         if (dotCnt == 2)
        //             charToCnt.put(SLASH, slashCnt + 1);
        //     } else if (c == DOT) {
        //         // 清空 slash
        //         charToCnt.put(DOT, dotCnt + 1);
        //     } else {
        //
        //     }
        // }
    }
}
