package hot100.priorityqueue;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * 完善版优先级队列，小顶堆
 * 运行时，没有按照最小堆来。排序不对，为什么？
 */
public class CompletePriorityQueue<T> {
    private T[] heap;
    private int size;
    private final Comparator<? super T> comparator;

    @SuppressWarnings("unchecked")
    public CompletePriorityQueue(int capacity, Comparator<? super T> comparator) {
        this.heap = (T[]) new Object[capacity];
        size = 0;
        this.comparator = comparator;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int parent(int node) {
        return (node - 1) / 2;
    }

    public int left(int node) {
        return node * 2 + 1;
    }

    public int right(int node) {
        return node * 2 + 2;
    }

    public void swap(int i, int j) {
        T tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        return heap[0];
    }

    public void push(T x) {
        if (size == heap.length) {
            resize(2 * heap.length);
        }
        heap[size] = x;
        swim(size);
        size++;
    }

    private void swim(int x) {
        while (x > 0 && comparator.compare(heap[x], heap[parent(x)]) < 0) {
            swap(x, parent(x));
            x = parent(x);
        }
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        T res = heap[0];
        heap[0] = heap[size - 1]; // 或 swap(0, size-1)
        // 避免对象游离，帮助GC快速回收
        heap[size - 1] = null;
        size--;
        sink(0);
        // 缩容
        if (size > 0 && size == heap.length / 4) {
            resize(heap.length / 2);
        }
        return res;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCap) {
        if (newCap <= size) {
            throw new IllegalArgumentException("the new capacity is not more than original size");
        }
        // 创建一个新数组
        T[] tmp = (T[]) new Object[newCap];
        for (int i = 0; i < size; i++) {
            tmp[i] = heap[i];
        }
        heap = tmp;
    }

    private void sink(int x) {
        while (left(x) < size) {
            int min = left(x);
            // 如果右子节点存在且比左子节点更小
            if (right(x) < size && comparator.compare(heap[left(x)], heap[right(x)]) > 0) {
                min = right(x);
            }
            // 如果当前节点已经是最小的，则停止下沉
            if (comparator.compare(heap[x], heap[min]) <= 0) {
                break;
            }
            swap(x, min);
            x = min;
        }
    }

    public static void main(String[] args) {
        CompletePriorityQueue<Integer> pq = new CompletePriorityQueue<>(3, Comparator.naturalOrder());
        pq.push(3);
        pq.push(1);
        pq.push(4);
        pq.push(1);
        pq.push(5);
        pq.push(9);
        // 1 1 3 4 5 9
        while (!pq.isEmpty()) {
            System.out.println(pq.pop());
        }
    }
}
