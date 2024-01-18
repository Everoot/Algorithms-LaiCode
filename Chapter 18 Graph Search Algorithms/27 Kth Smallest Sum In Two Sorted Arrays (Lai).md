# 27 Kth Smallest Sum In Two Sorted Arrays (Lai)

Given two sorted arrays A and B, of sizes m and n respectively. Define s = a + b, where a is one element from A and b is one element from B. Find the Kth smallest s out of all possible s'.

**Assumptions**

- A is not null and A is not of zero length, so as B
- K > 0 and K <= m * n

**Examples**

A = {1, 3, 5}, B = {4, 8}

- 1st smallest s is 1 + 4 = 5
- 2nd smallest s is 3 + 4 = 7
- 3rd, 4th smallest s are 9 (1 + 8, 4 + 5) 
- 5th smallest s is 3 + 8 = 11



**Solution:**

```java
public class Solution {
  static class Cell {
    int x;
    int y;
    int value;

    Cell(int x, int y, int value){
      this.x = x;
      this.y = y;
      this.value = value;
    }
  }
  public int kthSum(int[] A, int[] B, int k) {
    // Write your solution here
    int a = A.length;
    int b = B.length;

    boolean[][] visited = new boolean[a][b];

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

    minHeap.offer(new Cell(0, 0, A[0]+ B[0]));
    visited[0][0] = true;

    for (int i = 1; i < k; i++){
      Cell cur = minHeap.poll();

      if (cur.x + 1 < a && !visited[cur.x+1][cur.y]){
        minHeap.offer(new Cell(cur.x +1, cur.y, A[cur.x+1]+B[cur.y]));
        visited[cur.x +1][cur.y] = true;
      }

      if (cur.y + 1 < b && !visited[cur.x][cur.y+1]){
        minHeap.offer(new Cell(cur.x, cur.y+1, A[cur.x]+B[cur.y+1]));
        visited[cur.x][cur.y+1] = true;
      }
    }

    return minHeap.peek().value;
  }
}

// TC: O(klogk)
// SC: O(k)
```

