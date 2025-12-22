package labuladong.circulararray;

/**
 * 设计循环双端队列
 * 1、使用循环数组。15分钟搞定。
 * 有点小问题：计算 getRear()时，计算的是 arr[end - 1]，结果导致 IndexOutOfBounds
 * 实际上应该是：return isEmpty() ? -1 : arr[(end - 1 + size) % size]
 */
public class MyCircularDeque {
    int[] arr;
    int size;
    int cnt;
    int start, end; // [left, right)

    public MyCircularDeque(int k) {
        this.arr = new int[k];
        this.size = k;
        this.cnt = 0;
        start = 0;
        end = 0;
    }

    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        start = (start - 1 + size) % size;
        arr[start] = value;
        cnt++;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        arr[end] = value;
        end = (end + 1) % size;
        cnt++;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        start = (start + 1) % size;
        cnt--;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        end = (end - 1 + size) % size;
        cnt--;
        return true;
    }

    public int getFront() {
        return isEmpty() ? -1 : arr[start];
    }

    public int getRear() {
        return isEmpty() ? -1 : arr[(end - 1 + size) % size];
    }

    public boolean isEmpty() {
        return cnt == 0;
    }

    public boolean isFull() {
        return cnt == size;
    }
}
