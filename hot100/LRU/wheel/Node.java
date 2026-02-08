package hot100.LRU.wheel;

public class Node {
    public int key, val;
    public Node pre, next;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
