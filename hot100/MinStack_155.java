package hot100;

import java.util.ArrayDeque;

/**
 * 最小栈。
 * 思路：如何在常数时间内检索到最小元素呢。
 * 使用小顶堆不太行，因为小顶堆取元素是O(logn)
 * 在常数时间内取到最小元素，那只能用数组或者hash了。最小元素，那说明要排序啊
 * 直接看答案把。
 * 解法1：labuladong。想提高时间效率，那就得使用空间换时间。这个问题其实就是个“动态集合中维护最值的问题”
 * 关键：每个元素入栈时，需要额外记下当前栈中的最小值。可以使用另一个栈同步入栈和出栈
 * 解法2：基于解法1进行优化。包装成一个内部类。每次都要包装成类，效率会更低
 * 解法3：同一个最小值不重复入栈和出栈。效率更高
 */
public class MinStack_155 {
    ArrayDeque<Integer> stack;
    ArrayDeque<Integer> minStack;

    public MinStack_155() {
        this.stack = new ArrayDeque<>();
        this.minStack = new ArrayDeque<>();
    }

    public void push(int val) {
        if (minStack.isEmpty()) {
            stack.push(val);
            minStack.push(val);
            return;
        }
        Integer curMin = minStack.peek();
        if (val <= curMin) { // 解法3优化，当得到新的最小值，才插入
            minStack.push(val);
        }
        stack.push(val);
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        int pop = stack.pop();
        if (!minStack.isEmpty() && pop == minStack.peek()) { // 解法3优化，当要弹出的值为最小值时，才弹出。
            minStack.pop();
        }
    }

    public int top() {
        if (stack.isEmpty()) {
            return -1;
        }
        return stack.peek();
    }

    public int getMin() {
        if (minStack.isEmpty()) {
            return -1;
        }
        return minStack.peek();
    }
}
