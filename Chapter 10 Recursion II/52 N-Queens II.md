# 52 N-Queens II

The **n-queens** puzzle is the problem of placing `n` queens on an `n x n` chessboard such that no two queens attack each other.

Given an integer `n`, return *the number of distinct solutions to the **n-queens puzzle***.

 

**Example 1:**

![img](./52 N-Queens II.assets/queens.jpg)

```
Input: n = 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
```

**Example 2:**

```
Input: n = 1
Output: 1
```



```java
class Solution {
    public int totalNQueens(int n) {
        int size = n;
        Set<Integer> cols = new HashSet<Integer>();
        Set<Integer> diga = new HashSet<Integer>();
        Set<Integer> anti = new HashSet<Integer>();
        return helper(0, size, cols, diga, anti);
    }

    public static int helper(int row, int size, Set<Integer> cols, Set<Integer> diga, Set<Integer> anti){
        // base case
        if (row == size){
            return 1;
        }

        int solutions = 0;
        for (int col = 0; col < size; col++){
            int curdiga = row - col;
            int curanti = row + col;
            // check
            if (cols.contains(col) || diga.contains(curdiga) || anti.contains(curanti)){
                continue;
            }

            cols.add(col);
            diga.add(curdiga);
            anti.add(curanti);
            solutions = solutions + helper(row+1, size, cols, diga, anti);
            

            cols.remove(col);
            diga.remove(curdiga);
            anti.remove(curanti);
        }

        return solutions;
    }
}


/*
DFS
 
level                col
row 0            0    1    2    3
              / | \ \
1            0  1 2 3
                  /
2

3

1. cols 
2. diga
3. anti

row == size 


// TC: 4 3 2 1  = > O(n!)
// SC: O(n)
*/
```

```java
class Solution {
    public int totalNQueens(int n) {
        int size = n;
        List<List<String>> solutions = new ArrayList<List<String>>();
        // [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
        //  0  1  2 3
        //0 [] [] [] [] 
        //1 [] [] [] [] 
        //2 [] [] [] [] 
        //3 [] [] [] []

        // . . . . 
        // . . . .
        // . . . .
        // . . . .
        char[][] emptyBoard = new char[size][size];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                emptyBoard[i][j] = '.';
            }
        }
        Set<Integer> cols = new HashSet<Integer>();
        Set<Integer> diagnoals = new HashSet<Integer>();
        Set<Integer> antiDiagnoals = new HashSet<Integer>();
        helper(0, cols, diagnoals, antiDiagnoals, emptyBoard, size, solutions);
        return solutions.size();
    }

    private static void helper(int row, Set<Integer> cols, Set<Integer> diagnoals, Set<Integer> antiDiagnoals, char[][] board, int size, List<List<String>> solutions){
        // base case
        if (row == size){
            solutions.add(transfer(board, size));
        }


        // recursion rule
        for (int col = 0; col < size; col++){
            int currDiagnoal = row - col;
            int currAntiDiagnoal = row + col;
            if (cols.contains(col) || diagnoals.contains(currDiagnoal) || antiDiagnoals.contains(currAntiDiagnoal)){
                continue;
            }

            // eat
            cols.add(col);
            diagnoals.add(currDiagnoal);
            antiDiagnoals.add(currAntiDiagnoal);
            board[row][col] = 'Q';
            helper(row + 1, cols, diagnoals, antiDiagnoals, board, size, solutions);

            // spit
            cols.remove(col);
            diagnoals.remove(currDiagnoal);
            antiDiagnoals.remove(currAntiDiagnoal);
            board[row][col] = '.';
        }
    }



    private static List<String> transfer(char[][] board, int size){
        List<String> subSolutions = new ArrayList<String>();
        for (int i = 0; i < size; i++){
            String row = new String(board[i]); 
            subSolutions.add(row);
        }
        return subSolutions;
    }
}
```

