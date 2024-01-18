# 638 Largest Square Of Matches (Lai)

Determine the largest square surrounded by a bunch of matches (each match is either horizontal or vertical), return the length of the largest square.

The input is a matrix of points. Each point has one of the following values:

0 - there is no match to its right or bottom.

1 - there is a match to its right.

2 - there is a match to its bottom.

3 - there is a match to its right, and a match to its bottom.

**Assumptions**

The given matrix is guaranteed to be of size M * N, where M, N >= 0

**Examples**

{{3, 1, 1, 3, 0, 1, 1, 0},

 {2, 0, 0, 2, 0, 0, 0, 0},

 {3, 1, 3, 0, 0, 0, 0, 0},

 {2, 0, 2, 0, 0, 0, 0, 0},

 {1, 1, 0, 0, 0, 0, 0, 0}}



This matrix represents the following bunch of matches:

![img](./638 Largest Square Of Matches (Lai)/638img.png)

The largest square has a length of 2.

**Solution:**

```java
public class Solution {
  public int largestSquareOfMatches(int[][] matrix) {
    // Write your solution here
    // base case 
    if (matrix == null){
      return 0;
    }
    int n = matrix.length;
    int m = matrix[0].length;

    if (n == 0 || m == 0){
      return 0;
    }

    int[][] right = new int[n][m];
    int[][] down = new int[n][m];
    int result = 0;
    // induction

    for (int i = n - 1; i >= 0; i--){
      for (int j = m - 1; j >= 0; j--){
        if (matrix[i][j] == 1){
          right[i][j] = right[i][j+1] + 1;
        }else if (matrix[i][j] == 2){
          down[i][j] = down[i+1][j] + 1;
        }else if (matrix[i][j] == 3){
          right[i][j] = right[i][j+1]+1;
          down[i][j] = down[i+1][j] + 1;
        }

        for (int len = Math.min(right[i][j], down[i][j]); len > 0; len--){
          if (right[i+len][j] >= len && down[i][j+len] >= len){
            result = Math.max(result, len);
          }
        }

      }

    }

    return result;
  }
}

// TC: O(n^3)
// SC: O(n^2)
```

