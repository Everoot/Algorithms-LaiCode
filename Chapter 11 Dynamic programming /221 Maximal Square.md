#221 Maximal Square

Given an `m x n` binary `matrix` filled with `0`'s and `1`'s, *find the largest square containing only* `1`'s *and return its area*.

 

**Example 1:**

![img](./221 Maximal Square.assets/max1grid.jpg)

```
Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 4
```

**Example 2:**

![img](./221 Maximal Square.assets/max2grid.jpg)

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
        // base case
        int max = 0;
        int[][] m = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length ; i++){
            if (matrix[i][0] == '1'){
                m[i][0] = 1;
                max = 1;
            }else{
                m[i][0] = 0;
            }
        }

        for (int j = 0; j < matrix[0].length ; j++){
            if (matrix[0][j] == '1'){
                m[0][j] = 1;
                max = 1;
            }else{
                m[0][j] = 0;
            }
        }

        // induction rule
        for (int i = 1; i < matrix.length; i++){
            for (int j = 1; j < matrix[0].length; j++){
                if (matrix[i][j] == '1'){
                    if(matrix[i-1][j-1] == '1' && matrix[i-1][j] == '1' && matrix[i][j-1] == '1'){
                        m[i][j] = Math.min(m[i-1][j-1], Math.min(m[i-1][j], m[i][j-1])) + 1;
                    }else{
                        m[i][j] = 1;
                    }
                }else{
                    m[i][j] = 0;
                }
                max = Math.max(max, m[i][j]);
            }
        }



        // return 
        return max*max;
    
    }
}


/*

    1   0   1   0   0
    1   0   1   1   1
    1   1   1   1   1
    1   0   0   1   0

m   1   0   0   0   0
    1   0   1   1   1    
    1   1   1   2   2
    1   0   0   1   0 

    max = 2
    area = 2 * 2 = 4


    0 1
    1 0

m   0 1
    1 0
*/
```

