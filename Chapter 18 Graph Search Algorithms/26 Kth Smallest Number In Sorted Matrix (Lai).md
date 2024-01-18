# 26 Kth Smallest Number In Sorted Matrix (Lai)

Given a matrix of size N x M. For each row the elements are sorted in ascending order, and for each column the elements are also sorted in ascending order. Find the Kth smallest number in it.

**Assumptions**

- the matrix is not null, N > 0 and M > 0
- K > 0 and K <= N * M

**Examples**

{ {1,  3,  5,  7},

 {2,  4,  8,  9},

 {3,  5, 11, 15},

 {6,  8, 13, 18} }

- the 5th smallest number is 4
- the 8th smallest number is 6

**Solution:**

```java
public class Solution {

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
    // Write your solution here
    int rows = matrix.length;
    int cols = matrix[0].length;

    boolean[][] visited = new boolean[rows][cols];

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

    minHeap.offer(new Cell(0,0, matrix[0][0]));
    visited[0][0] = true;

    for (int i = 1; i < k; i++){
      Cell cur = minHeap.poll();

      if (cur.row + 1 < rows && !visited[cur.row+1][cur.col]){
        minHeap.offer(new Cell(cur.row + 1, cur.col, matrix[cur.row+1][cur.col]));
        visited[cur.row+1][cur.col] = true;
      }

      if (cur.col + 1 < cols && !visited[cur.row][cur.col+1]){
        minHeap.offer(new Cell(cur.row, cur.col+1, matrix[cur.row][cur.col+1]));
        visited[cur.row][cur.col+1] = true;
      }
    }

    return minHeap.peek().value;
  }
}
// [  3 3 4
// [  3

// TC: O(nlogn)
// SC: O(n)
```

