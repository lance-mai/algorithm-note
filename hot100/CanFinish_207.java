package hot100;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 课程表
 * 解法1：DFS版本。出现循环依赖的时候，这段依赖环不能算进去。直接看答案吧。
 * labuladong：有向图的环检测算法，用来检测是否有循环依赖。有DFS和BFS两种思路
 * 关于题目，什么时候不能完成所有课程学习？当出现循环依赖的时候（这个思路我是独立想到的）
 * 看到依赖问题，首先想到的是将问题转化为有向图这种数据结构，只要图中存在环，那就说明有循环依赖
 * 思路：把课程当作有向图中的节点，编号为 0,...,numCourses-1，把课程之间的依赖关系看作是节点之间的有向边
 * 两个易错点：1、初始化hasCycle时默认为false无环 2、创建List<Integer>[]时，还要对每个数组元素(list)进行初始化
 * 易错点3：遍历图时，循环遍历了。不应该传入list，传入list的话就会固定不变。应该传入graph，这样每个节点的临接点才会被访问到
 * 超时。需要备忘录。OK了。哈哈哈
 * <p>
 * 解法2：BFS版本。
 * 结合图中的入度（有向图，指向某节点的边数，即为该节点的入度，该节点辐射出去的边数，为出度）
 * 解法错误，为什么：条件搞错了。应该是==，而不是 >
 * if (indegree[node] > 0) { // 注意条件，入度为0，才可进入队列
 * queue.add(node);
 * }
 *
 * <p>
 * https://labuladong.online/zh/algo/data-structure/cycle-detection/
 */
public class CanFinish_207 {
    // 解法2：BFS版本
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        // 构建入度表
        int[] indegree = new int[numCourses];
        for (int[] tuple : prerequisites) {
            int to = tuple[1];
            indegree[to]++;
        }
        // 入度为0可以作为遍历的起点（没有依赖），加入队列中
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int node = 0; node < indegree.length; node++) {
            if (indegree[node] == 0) { // 注意条件，入度为0，才可进入队列
                queue.add(node);
            }
        }
        // 记录已访问节点，遍历队列
        int visited = 0;
        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            visited++;
            // 减少每个to节点的入度（即减少依赖）
            for (Integer to : graph[curNode]) {
                indegree[to]--;
                if (indegree[to] == 0) { // 如果to节点的入度变成0，则可以作为遍历的开始节点
                    queue.offer(to);
                }
            }
        }
        return visited == numCourses; // 如果全部节点被访问到，那就表明无环
    }

    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        // 注意List<Integer>[]的创建方式
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        // 易错点。这时候graph数组里面每一个元素list实际上还没有创建，必须初始化
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] tuple : prerequisites) {
            int from = tuple[0];
            int to = tuple[1];
            graph[from].add(to);
        }
        return graph;
    }


    // 解法1：DBS版本
    boolean hasCycle;
    boolean[] onPath;

    boolean[] used; // 备忘录，用来记录已经访问过的节点

    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        // 初始化状态
        hasCycle = false; // 初始化是默认无环
        onPath = new boolean[numCourses]; // 表示该节点是否正在访问链路上。如果被重复访问，那就是有环
        used = new boolean[numCourses];
        // graph的索引表示课程节点编号。如果课程不是int，可以使用hashmap映射成int编号
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        // 得到图（邻接表）以后，分别遍历邻接表的每一个节点，记录是否有环
        for (int curNode = 0; curNode < graph.length; curNode++) {
            traverse(curNode, graph);
        }
        return !hasCycle;
    }

    private void traverse(int curNode, /*List<Integer> list*/ List<Integer>[] graph) {
        if (hasCycle) {
            return;
        }
        if (onPath[curNode]) {
            hasCycle = true;
            return;
        }
        // 备忘录判断
        if (used[curNode]) {
            return;
        }

        // 将当前节点加入到路径中
        onPath[curNode] = true;
        // 回溯各节点
//        for (Integer node : list) {
//            traverse(node, list); // 这里每次传入的都是当前一个list，会有循环遍历
//        }
        for (Integer node : graph[curNode]) {
            traverse(node, graph);
        }

        // 将当前节点从路径中撤销
        onPath[curNode] = false;

        // 维护备忘录
        used[curNode] = true;
    }

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {
                {1, 0}
        };
        System.out.println(new CanFinish_207().canFinish(numCourses, prerequisites));
    }
}
