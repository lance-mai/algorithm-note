package hot100;

import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * 有效的括号
 * 解法1：结合hash表和栈
 * 注意key value的取法
 * if (stack.isEmpty() || !leftMapRight.get(stack.peek()).equals(c)) {
 */
public class IsValid_20 {
    public boolean isValid(String s) {
        HashMap<Character, Character> leftMapRight = new HashMap<>();
        leftMapRight.put('(', ')');
        leftMapRight.put('[', ']');
        leftMapRight.put('{', '}');
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (leftMapRight.containsKey(c)) {
                stack.push(c);
                continue;
            }
            if (stack.isEmpty() || !leftMapRight.get(stack.peek()).equals(c)) {
                return false;
            }
            stack.pop();
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new IsValid_20().isValid("()"));
    }
}
