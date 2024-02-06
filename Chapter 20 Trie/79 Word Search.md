---
tags:
    - Array
    - Backtracking
    - Matrix
---



# 79 Word Search

Given an `m x n` grid of characters `board` and a string `word`, return `true` *if* `word` *exists in the grid*.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

 

**Example 1:**

![img](./79 Word Search/word2.jpg)

```
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true
```

**Example 2:**

![img](./79 Word Search/word-1.jpg)

```
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true
```

**Example 3:**

![img](./79 Word Search/word3.jpg)

```
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false
```



**Solution:**

```java
class Solution {
    public boolean exist(char[][] board, String word) {
        // base case 
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0){
            return false;
        }

        int row = board.length;
        int col = board[0].length; 

        boolean[][] visited = new boolean[row][col];
        char[] array = word.toCharArray();

        for (int i = 0; i < row; i++){ /// where to start
            for (int j = 0; j < col; j++){
                int index = 0;
                if (dfs(board, index, array, i, j, visited)){
                    return true;
                }
            }
        }
        return false;
        
    }

    private boolean dfs(char[][] board, int index, char[] array, int x, int y, boolean[][] visited){
        int row = board.length;
        int col = board[0].length;

        if (index == array.length){
            return true;
        }

        // base case check
        if (x >= row || x < 0 || y >= col || y < 0 || visited[x][y] || array[index] != board[x][y]){
            return false;
        }

        visited[x][y] = true;

        // go int next level four directions
        if (dfs(board, index + 1, array, x + 1, y, visited) ||
        dfs(board, index+1, array, x -1 , y , visited) || 
        dfs(board, index+1, array, x, y - 1, visited) || 
        dfs(board, index + 1, array, x, y + 1, visited)){
            return true;
        }
        visited[x][y] = false;
        return false;
    }
}

// TC: O(row * col * 4 ^ length)
// SC: O(row * col)
```

