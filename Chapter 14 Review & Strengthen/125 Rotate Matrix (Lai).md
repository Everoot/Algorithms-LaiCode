# 125 Rotate Matrix (Lai)

Rotate an N * N matrix clockwise 90 degrees.

**Assumptions**

- The matrix is not null and N >= 0

**Examples**

{ {1,  2,  3}

 {8,  9,  4},

 {7,  6,  5} }

after rotation is

{ {7,  8,  1}

 {6,  9,  2},

 {5,  4,  3} }



**Solution:**

```java
public class Solution {
  public void rotate(int[][] matrix) {
    // Write your solution here

    int n = matrix.length; 
    // base case
    if (n <= 1){
      return;
    }

    for (int i = 0; i < n; i++){
      for (int j = i; j < n; j++){
        int tmp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = tmp;
      }
    }

    for (int i = 0; i < n; i++){
      for (int j = 0; j < n/2; j++){
        int tmp = matrix[i][j];
        matrix[i][j] = matrix[i][n-1-j];
        matrix[i][n-1-j] = tmp;
      }
    }

    
  }
}

// TC: O(n^2)
// SC: O(1)

/*
   1 2 3
   8 9 4
   7 6 5

   1 8 7
   2 9 6
   3 4 5


   -======-

   middle line
   7 8 1
   6 9 2
   5 4 3


*/

```



