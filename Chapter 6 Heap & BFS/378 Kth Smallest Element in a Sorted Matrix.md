# 378 Kth Smallest Element in a Sorted Matrix

Given an `n x n` `matrix` where each of the rows and columns is sorted in ascending order, return *the* `kth` *smallest element in the matrix*.

Note that it is the `kth` smallest element **in the sorted order**, not the `kth` **distinct** element.

You must find a solution with a memory complexity better than `O(n2)`.

 

**Example 1:**

```
Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
Output: 13
Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
```

**Example 2:**

```
Input: matrix = [[-5]], k = 1
Output: -5
```



```java
class Solution {
    static class Cell{
        int row;
        int column;
        int value;

        Cell(int row, int column, int value){
            this.row = row;
            this.column = column;
            this.value = value;
        }
    }

    public static int kthSmallest(int[][] matrix, int k) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        // Best First Search, need a minheap on the value of each cells.
        PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(k, new Comparator<Cell>(){
            @Override
            public int compare(Cell c1, Cell c2){
                if (c1.value == c2.value){
                    return 0;
                }else if (c1.value < c2. value){
                    return -1;
                }else{
                    return 1;
                }
            }
        });

        // all the generated cells will be marked true;
        // to avoid being generated more than once.
        boolean[][] visited = new boolean[rows][columns];
        minHeap.offer(new Cell(0,0, matrix[0][0]));
        visited[0][0] = true;

        // iterate k-1 rounds, and Best First Search the smallest k-1 cells.
        for (int i = 0; i < k -1; i++){
            Cell cur = minHeap.poll();
            // the neighbor cell will be inserted back to the minheap only if 
            // 1. it is not out of boundary.
            // 2. it has not been generated before.
            // Because for each cell it could be generated twice.
            if (cur.row + 1 < rows && !visited[cur.row + 1][cur.column]){
                minHeap.offer(new Cell(cur.row + 1, cur.column, matrix[cur.row + 1][cur.column]));
                visited[cur.row + 1][cur.column] = true;
            }

            if (cur.column + 1 < columns && !visited[cur.row][cur.column + 1]){
                minHeap.offer(new Cell(cur.row, cur.column + 1, matrix[cur.row][cur.column + 1]));
                visited[cur.row][cur.column + 1] = true;
            }
        }

        return minHeap.peek().value;

    }
        
}


/*
    [1,   5,   9]
    [10,  11, 13]
    [12,  13, 15]


    visited:    T  T  F 
                T  F  F
                F  F  F


    Heap:  (0, 1, 5)    (1,0,10)

    cur = (0,1,5)



*/
```

#### Complexity Analysis

- Time Complexity: let X=min(K,N);X+Klog⁡(X); X+Klog(X)
  - Well the heap construction takes O(X) time.
  - After that, we perform Kiterations and each iteration has two operations. We extract the minimum element from a heap containing X elements. Then we add a new element to this heap. Both the operations will take O(log⁡(X)) time.
  - Thus, the total time complexity for this algorithm comes down to be O(X+Klog⁡(X)) where X is min(K,N).
- Space Complexity: O(X)which is occupied by the heap.







## BFS-2 Best First Search

经典算法: Dijkstra's Algorithm (runtime efficiency improvement: A * algorithm <https://en.wikipedia.org/wiki/A*_search_algorith> unnecessary to read)

1. Usages: Find the shortest path cost from a single node (source node) to any other nodes in that graph (点到面(==所有点)的最短距离算法)

2. Example problem: 从北京到中国其他所有主要城市的最短距离是多少

3. Data structure: priority_queue(MIN_HEAP)

4. 解题思路

   4.1 Initial state (start node)

   4.2 Node expansion/Generation rule:

   4.3 Termination condition: 所有点都计算完毕才停止, 也就是p_queue变空

   4.4 Dedup: each node is expanded only once

5. Dijkstra's algorithm 的重要性质

   5.1 one node can be expanded once and only once

   5.2 one node can be generated more than once. (cost can be reduced over time)

   ==**5.3 all the cost of the nodes that are expanded are monotonically non-decreasing(所有从priority queue里面pop出来的元素的值是单调非递减--> 单调递增)**==

   ==**5.4 when a node is poped out for expansion, its value is fixed which is equal to the shortest distance from the start node.**==

   5.5 time complexity, for a graph with n nodes and the connectivity of the node is constant(e.g., 4-connected grid) http://en.wikipedia.org/wiki/Dijkstra%27s_algorithm O(nlogn), for a general graph, O(E log V).

   <img src="./378 Kth Smallest Element in a Sorted Matrix.assets/Screen Shot 2022-05-31 at 17.15.10.png" alt="Screen Shot 2022-05-31 at 17.15.10" style="zoom:50%;" />

initial state

pq<Node, cost>= {{N4, 0}}

set = {}



round1

expand N4 

generate N5, N3, N6

pq<Node, cost> = {(N5, 10), (N3, 1), (N6, 1)}   // 所以找的是最便宜的 N3, N6无所谓

set = {N4}



round2 

expand the node whose cost is smallest: N3 or N6 doesn't matter, e.g (N3, 1)

generate N2 (N2.cost = N3.cost + c(N3 -> n2) = 2)

pq<Node, cost> = {(N5, 10),(N6,1),**(N2,2)**}

set = {N4, N3}

cost 永远在递增



round3

expand the node whose cost is smallest (N6, 1)

generate nothing

pq<Node, cost> = {(N5, 10), (N2, 2)}

set = {N4, N3, N6}



round4 

expande the node where cost is smallest (N2, 2)

generate N1 (N1.cost = N2.cost + c(N2->N1) = 3)

​				N5 (N5.cost = N2.cost + c(N2-> N5) = 3) -- possible to regenerate if cost is smaller

pq<Node, cost> = {(N1, 3), (N5, 3)}

set = {N4, N3, N6, N2}

......



什么时候停止?

只要我pop到goal了, 就说明这个路径被我找到了

pop是不是都考虑过了? 不是, 是说到我的最便宜的路径都考虑过了, 比我更贵的已经被deduplicated.



如何update pq的数据结构很复杂. 但一般不会让你去写

Data structure:

**Priority queue(min heap):**

Expanded nodes: <4, 0> <6, 1> <3, 1> <2, 2> <5, 3> <1, 3>



Algorithm:

Initialize: enqueue <the source code, 0>

For each step:

Expand: 

​			 dequeue the node (x) with the smallest cost from the source - O(logn)

​			**dedup: if x has been expanded before, don't generate anything** 

Generate: 

​			enqueue all neighbors <the neighbor, dist(x) + weight(x -> y)>

​								O(# of outgoing edges * logn)

​			if (dist(x) + weight(x->y) >= dist(y)), ignore

​			if (dist(x) + weight(x->y) < dist(y)), enqueue

Terminate

​			the queue is empty

Dedup 

​			each node is expanded only once.



###  经典考题: (运用Dijkstra's Algorithm 的性质)

这类题非常常见会考

**Dijkstra 性质3: all the cost of the nodes that are expanded are monotonically non-decreasing** 

**(所有从priority queue里面pop出来的元素的值是单调非递减 --> 单调递增)**

**Dijkstra 性质4: when a node is popped out for expansion, its value is fixed which is equal to the shortest distance from the start node.**



==**Given a matrix of size NxN, and for each row the elements are sorted in an ascending order and for each column the elements are also sorted in an ascending order.**==

How to find the k-th smallest element in it? (or How to find K smallest elements)



k = 7, return 4

k = 10, return 5



1	2	3	5	6

2	3	4	6	8

3	4	5	7	9

4	5	6	8	10

5	6	9	10	11



构图:

​		Node: each cell in the matrix

​		Edge: 往左and往下

​		Cost: value



BFS2的操作过程 & How to describe a BFS2's action during an interview?

* Data Structure: Maintain a Priority queue
* Initial state
* Expand: poll from queue
* Generate neighbor nodes: insert into queue
* Termination condition: stop at k loops
* Optionally deduplicate visited nodes (typically for graph not for free)
  * e.g. each node is expanded only once
  * e.g. each node is generated only once



<img src="./378 Kth Smallest Element in a Sorted Matrix.assets/Screenshot 2023-05-31 at 00.33.38.png" alt="Screenshot 2023-05-31 at 00.33.38" style="zoom:33%;" />











initial state

pq = {(1, 1)}

popped =

visited\[][] =

T F F F F

F F F F F

F F F F F

F F F F F

F F F F F 

or set{cell(x,y)} -- need to implement hashcode/equals will be covered later in practice class



round1

expand(1, 1)

generation(2a, 2), (2b, 2)

pq = {(2a, 2), (2b, 2)}

popped = (1, 1)

visited =

T T F F F

T F F F F

F F F F F

F F F F F

F F F F F



round2

expand(2a, 2)

generation(3a, 3), (3b, 3)

pq = {(2b, 2), (3a, 3), (3b, 3)}

popped = (1,1), (2a, 2)

visited

T T T F F 

T T F F F

F F F F F

F F F F F

F F F F F



round3

expand(2b, 2)

generation ~~(3b, 3)~~???, (3c, 3)

pq = {(3a, 3), (3b, 3)}

popped = (1, 1), (2a, 2), (2b, 2)



round k

return pq.peek()



#### Solution:

1. Initial State: input\[0][0]

2. expansion/generation ---> expand input\[0][0] --> generate\[0][1] and generate \[1][0]

   In general --> expand \[i][j]

   - generate \[i][j+1]
   - generate \[i + 1][j]

3. Termination condition

   i. When k-th element is about to be expanded

4. Deduplication is important for this question

   a. 3b 3b can cause problem



How to get the time complexity of this problem?

1. There are k iterations that we need to run.

2. For each iteration

   a. we need to pop one element out of the min-heap, there are at MOST 2k element in the min-heap -- O(logk)

   b. we can generate AT MOST 2 neighbors, and inserted 2 neighbors into the min-heap -- O(2logk)

   Total time for each iteration = O(3logk) = O(logk)

Thus, total time = k x logk => O(klogk)



### Discussion

General algorithm for BFS{1, 2}:

1. Initial state: e.g. root node, start node, minimum number...

2. Expansion/Generation rule:

   a. BFS1: expand node, generate all neighbors -> FIFO queue

   b. BFS2: expand node, generate all neighbors -> Priority-queue (a.k.a heap)

3. Termination condition:

   a. Queue is empty

   b. when conflict is found

   c. when the target node is expanded

   d. when the k-th element is expanded

   e. ...

4. (Optional) deduplication

   a. One node can be expanded only once, but can be generated more than once?

   b. No need to re-generate?



**When should we consider using BFS-1?**

related to levels.

**When should we consider using BFS-2?**

shortest path

k-th smallest/largest

k-th 开头的题 跟priority queue 相关.



**==If all edge costs in the graph are uniform (e.g., all 1s) then in this special case, we use BFS1 to find the shortest path. Otherwise, BFS1 is not the correct algorithm.==**

