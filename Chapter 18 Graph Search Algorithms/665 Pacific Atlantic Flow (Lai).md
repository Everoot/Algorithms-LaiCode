# 665 Pacific Atlantic Flow (Lai)

Given an **m \* n** matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean. The order of returned grid coordinates does not matter.


**Example:**

Given the following 4 * 4 matrix:

Pacific  ~  ~  ~  ~

   ~   1  2  2  (3)  *

   ~   3  2  3  (4)  *

   ~   2  4  (5)  3  *

   ~   (6) (7)  1  4  *

​       \*  *  *  * Atlantic

Output: [0,3],[1,3],[2,2],[3,0],[3,1]



**Solution:**

```java
public class Solution {
  public static final int[][] DIR = {{-1, 0}, {1,0}, {0,1}, {0,-1}};
  public int rows;
  public int cols;
  public int[][] landHeights;
  public List<List<Integer>> pacificAtlantic(int[][] matrix) {
    // Write your solution here
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    rows = matrix.length;
    cols = matrix[0].length;
    if (rows == 0 || cols == 0){
      return result;
    }

    landHeights = matrix;
    

    Queue<int[]> pQueue = new LinkedList<int[]>();
    Queue<int[]> aQueue = new LinkedList<int[]>();

    for (int i = 0; i < rows; i++){
      pQueue.offer(new int[]{i, 0});
      aQueue.offer(new int[]{i, cols -1});
    }

    for (int j = 0; j < cols; j++){
      pQueue.offer(new int[]{0, j});
      aQueue.offer(new int[]{rows -1, j});
    }

    boolean[][] p = bfs(pQueue);
    boolean[][] a = bfs(aQueue);

    for (int i = 0; i < rows; i++){
      for (int j = 0; j < cols; j++){
        if (p[i][j] == true && a[i][j] == true){
          result.add(Arrays.asList(i,j));
        }
      }
    }    

    return result;

  }

  private boolean[][] bfs(Queue<int[]> q){
    boolean[][] result = new boolean[rows][cols];

    while(!q.isEmpty()){
      int[] cur = q.poll();
      result[cur[0]][cur[1]] = true;
      
      for (int[] dir : DIR){
        int newRow = cur[0] + dir[0];
        int newCol = cur[1] + dir[1];

        if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols){
          continue;
        }

        if (result[newRow][newCol] == true){
          continue;
        }

        if (landHeights[newRow][newCol] < landHeights[cur[0]][cur[1]]){
          continue;
        }

        q.offer(new int[]{newRow, newCol});
      }
    }

    return result;
  }
}


// TC: O(n*m)
// SC: O(n*m)
```

