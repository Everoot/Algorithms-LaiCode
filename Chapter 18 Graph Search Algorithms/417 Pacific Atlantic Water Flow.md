# 417 Pacific Atlantic Water Flow

There is an `m x n` rectangular island that borders both the **Pacific Ocean** and **Atlantic Ocean**. The **Pacific Ocean** touches the island's left and top edges, and the **Atlantic Ocean** touches the island's right and bottom edges.

The island is partitioned into a grid of square cells. You are given an `m x n` integer matrix `heights` where `heights[r][c]` represents the **height above sea level** of the cell at coordinate `(r, c)`.

The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is **less than or equal to** the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.

Return *a **2D list** of grid coordinates* `result` *where* `result[i] = [ri, ci]` *denotes that rain water can flow from cell* `(ri, ci)` *to **both** the Pacific and Atlantic oceans*.

 

**Example 1:**

![img](./417 Pacific Atlantic Water Flow/waterflow-grid.jpg)

```
Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
[0,4]: [0,4] -> Pacific Ocean 
       [0,4] -> Atlantic Ocean
[1,3]: [1,3] -> [0,3] -> Pacific Ocean 
       [1,3] -> [1,4] -> Atlantic Ocean
[1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean 
       [1,4] -> Atlantic Ocean
[2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean 
       [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
[3,0]: [3,0] -> Pacific Ocean 
       [3,0] -> [4,0] -> Atlantic Ocean
[3,1]: [3,1] -> [3,0] -> Pacific Ocean 
       [3,1] -> [4,1] -> Atlantic Ocean
[4,0]: [4,0] -> Pacific Ocean 
       [4,0] -> Atlantic Ocean
Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
```

**Example 2:**

```
Input: heights = [[1]]
Output: [[0,0]]
Explanation: The water can flow from the only cell to the Pacific and Atlantic oceans.
```



**Solution:**

```java
class Solution {
    private static final int[][] DIRECTIONS = new int[][]{{0, 1}, {1,0}, {-1,0}, {0, -1}};
    private int numRows;
    private int numCols;
    private int[][] landHeights;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        // Check if input is empty
        if (heights.length == 0 || heights[0].length == 0){
            return result;
        }

        // Save initial values to parameters
        numRows = heights.length;
        numCols = heights[0].length;
        landHeights = heights;

        // Setup each queue with cells adjacnet to their respective ocean
        Queue<int[]> pacificQueue = new LinkedList<>();
        Queue<int[]> atlanticQueue = new LinkedList<>();

        for (int i = 0; i < numRows; i++){
            pacificQueue.offer(new int[]{i, 0});
            atlanticQueue.offer(new int[]{i, numCols - 1});
        }

        for (int j = 0; j < numCols; j++){
            pacificQueue.offer(new int[]{0, j});
            atlanticQueue.offer(new int[]{numRows - 1, j});
        }

        // Perform a BFS for each ocean to find all cells accessible 
        // by each ocean
        boolean[][] pacificReachable = bfs(pacificQueue);
        boolean[][] atlanticReachable = bfs(atlanticQueue);

        // Find all cells that can reach both oceans
        for (int i = 0; i < numRows; i++){
            for (int j = 0; j < numCols; j++){
                if (pacificReachable[i][j] == true && atlanticReachable[i][j] == true){
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }

    private boolean[][] bfs(Queue<int[]> queue){
        boolean[][] reachable = new boolean[numRows][numCols];

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            //  This cur is reachable, so mark it
            reachable[cur[0]][cur[1]] = true;
            for (int[] dir : DIRECTIONS){ // Check all 4 directions
                int newRow = cur[0] + dir[0];
                int newCol = cur[1] + dir[1];


                // Check if new cur is within bounds
                if (newRow < 0 || newRow >= numRows || newCol < 0 || newCol >= numCols){
                    continue;
                }

                // Check that the new cur hasn't already been visited
                if (reachable[newRow][newCol]){
                    continue;
                }

                // Check that the new cur has a higher or equal height
                // So that water can flow from the new cur to the old cur
                if (landHeights[newRow][newCol] < landHeights[cur[0]][cur[1]]){
                    continue;
                }
                // if we've gotten this far, that means the new cur is reachable
                queue.offer(new int[]{newRow, newCol});
            }
        }

        return reachable;

    }
}
// Time complexity: O(M⋅N), where M is the number of rows and N is the number of columns.
// Space complexity: O(M⋅N), where M is the number of rows and N is the number of columns.
```



```java
class Solution {
    private static final int[][] DIRECTIONS = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private int numRows;
    private int numCols;
    private int[][] landHeights;
    
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        // Check if input is empty
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }
        
        // Save initial values to parameters
        numRows = matrix.length;
        numCols = matrix[0].length;
        landHeights = matrix;
        boolean[][] pacificReachable = new boolean[numRows][numCols];
        boolean[][] atlanticReachable = new boolean[numRows][numCols];
        
        // Loop through each cell adjacent to the oceans and start a DFS
        for (int i = 0; i < numRows; i++) {
            dfs(i, 0, pacificReachable);
            dfs(i, numCols - 1, atlanticReachable);
        }
        for (int i = 0; i < numCols; i++) {
            dfs(0, i, pacificReachable);
            dfs(numRows - 1, i, atlanticReachable);
        }
        
        // Find all cells that can reach both oceans
        List<List<Integer>> commonCells = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (pacificReachable[i][j] && atlanticReachable[i][j]) {
                    commonCells.add(Arrays.asList(i, j));
                }
            }
        }
        return commonCells;
    }
    
    private void dfs(int row, int col, boolean[][] reachable) {
        // This cell is reachable, so mark it
        reachable[row][col] = true;
        for (int[] dir : DIRECTIONS) { // Check all 4 directions
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            // Check if new cell is within bounds
            if (newRow < 0 || newRow >= numRows || newCol < 0 || newCol >= numCols) {
                continue;
            }
            // Check that the new cell hasn't already been visited
            if (reachable[newRow][newCol]) {
                continue;
            }
            // Check that the new cell has a higher or equal height,
            // So that water can flow from the new cell to the old cell
            if (landHeights[newRow][newCol] < landHeights[row][col]) {
                continue;
            }
            // If we've gotten this far, that means the new cell is reachable
            dfs(newRow, newCol, reachable);
        }
    }
}
// Time complexity: O(M⋅N), where M is the number of rows and N is the number of columns.
// Space complexity: O(M⋅N), where M is the number of rows and N is the number of columns.
```

