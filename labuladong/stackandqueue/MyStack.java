package labuladong.stackandqueue;

import java.util.LinkedList;

/**
 * 用队列实现栈。耗时26min
 * 几个注意的点：
 * 1、peek复用pop方法，注意size的变化。
 * 2、使用栈的size做遍历时，注意先创建一个临时变量，在使用临时变量去遍历，
 * 而不是使用queue.size()，因为remove过程中size会变化
 */
public class MyStack {
    LinkedList<Integer> queue1, queue2;
    int size;

    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
        size = 0;
    }

    public void push(int x) {
        if (!queue1.isEmpty()) {
            queue1.addLast(x);
        } else {
            queue2.addLast(x);
        }
        size++;
    }

    // 将该队列的元素迁移到另一个队列中，剩下最后一个元素
    private int moveQueue(LinkedList<Integer> source, LinkedList<Integer> target) {
        int sourceLen = source.size();
        for (int i = 0; i < sourceLen - 1; i++) {
            target.addLast(source.removeFirst());
        }
        return source.removeFirst();
    }

    public int pop() {
        int lastEle;
        if (!queue1.isEmpty()) {
            lastEle = moveQueue(queue1, queue2);
        } else {
            lastEle = moveQueue(queue2, queue1);
        }
        size--;
        return lastEle;
    }

    public int top() {
        int peekEle = pop();
        size++; // 抵消 pop的size--
        if (!queue1.isEmpty()) {
            queue1.addLast(peekEle);
        } else {
            queue2.addLast(peekEle);
        }
        return peekEle;
    }

    public boolean empty() {
        return size == 0;
    }
}
