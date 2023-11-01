# 105 Largest X Of 1s (Lai)

Given a matrix that contains only 1s and 0s, find the largest X shape which contains only 1s, with the same arm lengths and the four arms joining at the central point.

Return the arm length of the largest X shape.

**Assumptions**

- The given matrix is not null, has size of N * M, N >= 0 and M >= 0.

**Examples**

{ {0, 0, 0, 0},

 {**1**, 1, **1**, 1},

 {0, **1**, 1, 1},

 {**1**, 0, **1**, 1} }

the largest X of 1s has arm length 2



```java
public class Solution {
  public int largest(int[][] matrix) {
    // Write your solution here
    if (matrix.length == 0 && matrix[0].length == 0){
      return 0;
    }
    
    int n = matrix.length;
    int m = matrix[0].length;

    int[][] leftUp = leftUp(matrix, n, m);
    int[][] rightDown = rightDown(matrix, n, m);

    return merge(leftUp, rightDown, n, m);
  }

  private static int merge(int[][] leftUp, int[][] rightDown, int n, int m){
    int max = 0;
    for (int i = 0; i < n; i++){
      for (int j = 0; j < m; j++){
        leftUp[i][j] = Math.min(leftUp[i][j], rightDown[i][j]);
        max = Math.max(leftUp[i][j], max);
      }
    }
    return max;
  }

  private static int[][] leftUp(int[][] matrix, int n, int m){
    int[][] left = new int[n][m];
    int[][] up = new int[n][m];
    for (int i = 0; i < n; i++){
      for (int j = 0; j < m; j++){
        if (matrix[i][j] == 1){
          left[i][j] = getNumber(left, i-1, j-1, n, m) + 1;
          up[i][j] = getNumber(up, i-1, j+1, n, m)+1;
        }
      }
    }
    merge(left, up, n, m);
    return left;
  }

  private static int[][] rightDown(int[][] matrix, int n, int m){
    int[][] right = new int[n][m];
    int[][] down = new int[n][m];
    for (int i = n-1; i >= 0; i--){
      for (int j = m-1; j >= 0; j--){
        if (matrix[i][j] == 1){
          right[i][j] = getNumber(right, i+1, j+1, n, m)+1;
          down[i][j] = getNumber(down, i+1, j-1, n, m)+1;
        }
      }
    }
    merge(right, down, n, m);
    return right;
  }

  private static int getNumber(int[][] matrix, int x, int y, int n, int m){
    if (x < 0 || x >= n || y < 0 || y>=m){
      return 0;
    }
    return matrix[x][y];
  }
}
// TC: O(n*m)
// SC: O(n*m)
```



```java
public class Solution {
  public int largest(int[][] matrix) {
    // Write your solution here
    if (matrix.length == 0 && matrix[0].length == 0){
      return 0;
    }
    
    int n = matrix.length;
    int m = matrix[0].length;

    int[][] leftUp = leftUp(matrix, n, m);
    int[][] rightDown = rightDown(matrix, n, m);

    return merge(leftUp, rightDown, n, m);
  }

  private static int merge(int[][] leftUp, int[][] rightDown, int n, int m){
    int max = 0;
    for (int i = 0; i < n; i++){
      for (int j = 0; j < m; j++){
        leftUp[i][j] = Math.min(leftUp[i][j], rightDown[i][j]);
        max = Math.max(leftUp[i][j], max);
      }
    }
    return max;
  }

  private static int[][] leftUp(int[][] matrix, int n, int m){
    int[][] left = new int[n][m];
    int[][] up = new int[n][m];
    for (int i = 0; i < n; i++){
      for (int j = 0; j < m; j++){
        if (matrix[i][j] == 1){
          if (i-1 < 0 || j-1 <0 ||j+1 >= m  ){
            left[i][j] = 1;
            up[i][j] = 1;
          }else{
            left[i][j] = left[i-1][j-1] + 1;
            up[i][j] = up[i-1][j+1] + 1;
          }
        }
      }
    }
    merge(left, up, n, m);
    return left;
  }

  private static int[][] rightDown(int[][] matrix, int n, int m){
    int[][] right = new int[n][m];
    int[][] down = new int[n][m];
    for (int i = n-1; i >= 0; i--){
      for (int j = m-1; j >= 0; j--){
        if (matrix[i][j] == 1){
          if (i+1 >= n || j+1 >= m || j-1 < 0 ){
            right[i][j] = 1;
            down[i][j] = 1;
          }else{
            right[i][j] = right[i+1][j+1] + 1;
            down[i][j] = down[i+1][j-1]+1;
          }

        }
      }
    }
    merge(right, down, n, m);
    return right;
  }

  // private static int getNumber(int[][] matrix, int x, int y, int n, int m){
  //   if (x < 0 || x >= n || y < 0 || y>=m){
  //     return 0;
  //   }
  //   return matrix[x][y];
  // }
}
```

