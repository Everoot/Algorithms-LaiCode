#1139 Largest 1-Bordered Square

Given a 2D `grid` of `0`s and `1`s, return the number of elements in the largest **square** subgrid that has all `1`s on its **border**, or `0` if such a subgrid doesn't exist in the `grid`.

**Example 1:**

```
Input: grid = [[1,1,1],[1,0,1],[1,1,1]]
Output: 9
```

**Example 2:**

```
Input: grid = [[1,1,0,0]]
Output: 1
```



**Solution:**

```java
class Solution {
    public int largest1BorderedSquare(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        if (n == 0 || m == 0){
            return 0;
        }
        
        int[][] left = new int[n][m];
        int[][] up = new int[n][m];
        for(int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (grid[i][j] == 1){
                    if (i == 0 && j == 0){
                        left[i][j] = 1; 
                        up[i][j] = 1;
                    }else if (i == 0){
                        left[i][j] = left[i][j-1] + 1;
                        up[i][j] = 1;
                    }else if (j == 0){
                        left[i][j] = 1;
                        up[i][j] = up[i-1][j] + 1;
                    }else{
                        left[i][j] = left[i][j-1] + 1;
                        up[i][j] = up[i-1][j] + 1;
                    }
                }
            }
        }

        int result = 0;

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                int cur = Math.min(left[i][j], up[i][j]);
                for (int z = cur; cur >=1; cur--){
                    if (left[i - cur + 1][j] >= cur && up[i][j-cur + 1] >= cur){
                        result = Math.max(result, cur);
                        break;
                    }
                }
            }   
        }
        return result*result;
    }
}
//TC: O(n^3)
//SC: O(n^2)
```



```java
class Solution {
    public int largest1BorderedSquare(int[][] grid) {
        // Assumptions: matrix is not null, size of M * N, where M, N >= 0
        // the elements in the matrix are either 0 or 1.
       if (grid.length == 0 || grid[0].length == 0){
           return 0;
       } 
       int result = 0;
       int n = grid.length;
       int m = grid[0].length;
       // the longest left arm length ending at each position in the matrix.
       // we here apply a trick for ease of later processing:
       // left[i][j] is actually mapped to matrix[i-1][j-1],
       // this will reduced the corner cases.
       int[][] left = new int[n + 1][m + 1];
       int[][] up = new int[n + 1][m + 1];
       for (int i = 0; i < n; i++){
           for (int j = 0; j < m; j++){
               if (grid[i][j] == 1){
                    left[i + 1][j + 1] = left[i + 1][j] + 1;
                    up[i + 1][j + 1] = up[i][j + 1] + 1;
                    // the maximum length of a surrouded by 1 matrix with rightbottom
                    // position at matrix[i][j] is determined by the min value of 
                    // left[i+1][j+1] and up[i+1][j+1],
                    // and we check one by one all the possible lengths if it can 
                    // provide the actual matrix.
                    // we only need to check the longest left arm length of the righttop
                    // cell and the longest up arm length of the leftbottom call.
                    for (int maxLength = Math.min(left[i+1][j+1], up[i +1][j + 1]); maxLength >= 1; maxLength--){
                        if (left[i + 2 - maxLength][j + 1] >= maxLength && up[i + 1][j + 2 - maxLength] >= maxLength){
                            result = Math.max(result, maxLength);
                            break;
                        }
                    }
               }
           }
       }
       return result * result;
    }
}
```

