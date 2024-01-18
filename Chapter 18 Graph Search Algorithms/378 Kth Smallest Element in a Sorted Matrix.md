#  378 Kth Smallest Element in a Sorted Matrix

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

 

**Solution**: 

```java
class Solution {
    static class Cell{
        int row;
        int col;
        int value;
        Cell(int row, int col, int value){
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }
    public int kthSmallest(int[][] matrix, int k) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        boolean[][] visited = new boolean[rows][columns];

        PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(k, new Comparator<Cell>(){
            @Override
            public int compare(Cell c1, Cell c2){
                if (c1.value == c2.value){
                    return 0;
                }else if (c1.value < c2.value){
                    return -1;
                }else{
                    return 1;
                }
            }
        });

        // initial 
        minHeap.offer(new Cell(0, 0, matrix[0][0]));
        visited[0][0] = true;

        for (int i = 0; i < k -1; i++){ // K
            Cell cur = minHeap.poll();

            // -> 
            if (cur.col + 1 < columns && !visited[cur.row][cur.col + 1]){
                minHeap.offer(new Cell(cur.row, cur.col +1, matrix[cur.row][cur.col +1]));
                visited[cur.row][cur.col+1] = true;
            }


            // |
            // v

            if (cur.row + 1 < rows && !visited[cur.row + 1][cur.col]){
                minHeap.offer(new Cell(cur.row+1, cur.col, matrix[cur.row+1][cur.col]));
                visited[cur.row+1][cur.col] = true;
            }
        }

        return minHeap.peek().value;
        
    }
}

// TC: O(k+klogk)               2^levels =  k    level  -> logk

// SC: O(n)

/*
                T
               / \ 
               T   
              /\
              T  
                    ->
     matrix =   1, 5, 9
            |   10,11,13
            v   12,13,15


    visited boolean [][] 
               T T T 
               T T T
               T T F

    Cell: row, col, value =  0,0,1
    min Heap:     (2,1,13) (2,2,15)
    poll:   (1, 2, 13) ->
            |
            v
    for , k -1 = 7times  0

    result: minHeap.peek(); -> (2,1,13) -> 13


*/

// TC: O(nlogn)
// SC: O(n)
```

