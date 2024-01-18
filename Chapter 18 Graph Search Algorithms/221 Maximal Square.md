# 221 Maximal Square

Given an `m x n` binary `matrix` filled with `0`'s and `1`'s, *find the largest square containing only* `1`'s *and return its area*.

 

**Example 1:**

![img](./221 Maximal Square/max1grid.jpg)

```
Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 4
```

**Example 2:**

![img](./221 Maximal Square/max2grid.jpg)

```
Input: matrix = [["0","1"],["1","0"]]
Output: 1
```

**Example 3:**

```
Input: matrix = [["0"]]
Output: 0
```



**Solution:**

```java
class Solution {
    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        int[][] m = new int[row][col];

        int result = 0;

        for (int i = 0; i < row; i++){
            if (matrix[i][0] == '1'){
                m[i][0] = 1;
                result = 1;
            }
        }

        for (int j = 0; j < col; j++){
            if (matrix[0][j] == '1'){
                m[0][j] = 1;
                result = 1;
            }
        }

        for (int i = 1; i < row; i++){
            for (int j = 1; j < col; j++){
                if (matrix[i][j] == '1'){ 
                    // if (matrix[i-1][j] == '1' && matrix[i-1][j-1] == '1' && matrix[i][j-1] == '1'){
                    m[i][j] = Math.min(m[i-1][j] , Math.min(m[i-1][j-1], m[i][j-1])) + 1;
                    // }else {}
                }

                result = Math.max(result, m[i][j]);
            }
        }

        return result * result;
    }
}

// TC: O(n^2)
// SC: O(n^2)


/*
   m

   1 0   1  0. 0
   1 0   1  1   1
   1  1   1  2  2
   1  


*/
```

