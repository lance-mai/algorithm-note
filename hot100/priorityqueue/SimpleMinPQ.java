package hot100.priorityqueue;

/**
 * 简化版优先级队列，最小堆
 *
 */
public class SimpleMinPQ {
    // 底层使用数组实现二叉堆
    private final int[] heap;
    private int size;

    public SimpleMinPQ(int capacity) {
        heap = new int[capacity];
        size = 0;
    }

    public int size() {
        return size;
    }

    // 父节点索引
    private int parent(int node) {
        return (node - 1) / 2;
    }

    // 左子节点索引
    private int left(int node) {
        return node * 2 + 1;
    }

    // 右子节点索引
    private int right(int node) {
        return node * 2 + 2;
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // 查，返回堆顶元素，O(1)
    public int peek() {
        return heap[0];
    }

    // 增，向堆顶插入一个元素，O(logN)
    public void push(int x) {
        heap[size] = x;
        swim(size); // 将size位置的元素x上浮
        size++;
    }

    // 上浮。小顶堆，因此越小越能往上浮
    private void swim(int node) { // 这里node是元素在数组中的索引
        while (node > 0 && heap[node] < heap[parent(node)]) {
            swap(node, parent(node));
            node = parent(node);
        }
    }


    // 删，删除堆顶元素，O(logN)
    public int pop() {
        int result = heap[0];
        heap[0] = heap[size - 1];
        size--;
        sink(0); // 将栈顶元素下沉
        return result;
    }

    // 下沉。小顶堆，越大越能往下沉
    private void sink(int node) {
        // 往下沉和上浮不同，上浮只有一个方向，那就是父节点，下沉则可能是左右子节点。
        // 应该选择哪个子节点呢？小顶堆，选择更小的子节点。
        // 为什么。小顶堆嘛，小的上浮，选择更小的子节点，这样更小的子节点就往上浮
        // 如果左右子节点都大于当前节点，那就终止下沉
        while (left(node) < size || right(node) < size) {
            int min = node;
            if (left(node) < size && heap[left(node)] < heap[min]) {
                min = left(node);
            }
            if (right(node) < size && heap[right(node)] < heap[min]) {
                min = right(node);
            }
            if (min == node) {
                break; // 如果左右子节点都大于当前节点，那就终止下沉
            }
            // 如果如果左右子节点右比自己小的，则交换
            swap(node, min);
            node = min;
        }
    }
}
