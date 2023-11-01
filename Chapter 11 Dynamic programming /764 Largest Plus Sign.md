# 764 Largest Plus Sign

You are given an integer `n`. You have an `n x n` binary grid `grid` with all values initially `1`'s except for some indices given in the array `mines`. The `ith` element of the array `mines` is defined as `mines[i] = [xi, yi]` where `grid[xi][yi] == 0`.

Return *the order of the largest **axis-aligned** plus sign of* 1*'s contained in* `grid`. If there is none, return `0`.

An **axis-aligned plus sign** of `1`'s of order `k` has some center `grid[r][c] == 1` along with four arms of length `k - 1` going up, down, left, and right, and made of `1`'s. Note that there could be `0`'s or `1`'s beyond the arms of the plus sign, only the relevant area of the plus sign is checked for `1`'s.

 

**Example 1:**

![img](./764 Largest Plus Sign.assets/plus1-grid.jpg)

```
Input: n = 5, mines = [[4,2]]
Output: 2
Explanation: In the above grid, the largest plus sign can only be of order 2. One of them is shown.
```

**Example 2:**

![img](./764 Largest Plus Sign.assets/plus2-grid.jpg)

```
Input: n = 1, mines = [[0,0]]
Output: 0
Explanation: There is no plus sign, so return 0.
```



**Solution:**

```java
class Solution {
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        // base case 
        if (n == 0){
            return 0;
        }
        
        if (n == 1 && mines[0].length == 0){
            return 0;
        }
        
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                matrix[i][j] = 1;
            }
        }

        for (int[] mine : mines){
            matrix[mine[0]][mine[1]] = 0;
        }

        // leftup records the longest possible length for left and up
        // arms ending at each cells in the matrix
        int[][] leftUp = leftUp(matrix, n);
        
        // rightdown records the longest possible length for right and down
        // arms ending at each cells in the matrix
        int[][] rightDown = rightDown(matrix, n);

        // merge the two matrix by getting the min value for each cell,
        // and among all the cells get the max value;
        return merge(leftUp, rightDown, n);
    }

    // merge leftUp and rightDown matrix into leftUp,
    // the value of each cell is the min value of the corresponding cells
    // in the two matrix, also it returns the max value among all the cells
    // in the merged matrix.
    private int merge(int[][] leftUp, int[][] rightDown, int n){
        int result = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                leftUp[i][j] = Math.min(leftUp[i][j], rightDown[i][j]);
                result = Math.max(result, leftUp[i][j]);
            }
        }

        return result;
    }

    // calculate the max possible length of left and up arms
    // for each of the cells in the matrix.
    private int[][] leftUp(int[][] matrix, int n){
        int[][] left = new int[n][n];
        int[][] up = new int[n][n];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (matrix[i][j] == 1){
                    if (i == 0 && j == 0){// 第一行 第一列
                        up[i][j] = 1; 
                        left[i][j] = 1;
                    } else if (i == 0){// 第一行
                        up[i][j] = 1;
                        left[i][j] = left[i][j-1] + 1;
                    } else if (j == 0){// 第一列
                        up[i][j] = up[i-1][j] + 1;
                        left[i][j] = 1;
                    } else {
                        up[i][j] = up[i-1][j] + 1;
                        left[i][j] = left[i][j - 1] + 1;
                    }
                }
            }
        }

        merge(left, up, n);
        return left;
    }


    /*
    1   1   1   1   1
    1   1   1   1   1
    1   1   1   1   1
    1   1   1   1   1
    1   1   0   1   1

    left
    1   2   3   4   5           1   1   1   1   1
    1   2   3   4   5           1   2   2   2   2
    1   2   3   4   5     ->    1   2   3   3   3
    1   2   3   4   5           1   2   3   4   4
    1   2   0   1   2           1   2   0   1   2


    up
    1   1   1    1   1
    2   2   2    2   2
    3   3   3    3   3
    4   4   4    4   4
    5   5   0    5   5



    */


    // calculate the max possible length of right and down arms
    // for each of the cells in the matrix
    private int[][] rightDown(int[][] matrix, int n){
        int[][] right = new int[n][n];
        int[][] down = new int[n][n];
        for (int i = n-1; i >= 0; i--){
            for (int j = n-1; j>=0; j--){
                if (matrix[i][j] == 1){
                    if (i == n - 1 && j == n-1){ // 最后一行最后一列个
                        down[i][j] = 1;
                        right[i][j] = 1;
                    } else if (i == n-1){ // 最后一行
                        down[i][j] = 1;
                        right[i][j] = right[i][j+1]+1;
                    } else if (j == n-1){
                        down[i][j] = down[i + 1][j] + 1;
                        right[i][j] = 1;
                    } else {
                        down[i][j] = down[i + 1][j] + 1;
                        right[i][j] = right[i][j + 1] +1;
                    }
                } 
            }
        }
        // merge right and down, return the merge matrix
        merge(right, down, n);
        return right;
    }

    /*
    right
    5    4    3    2    1
    5    4    3    2    1
    5    4    3    2    1
    5    4    3    2    1
    2    1    0    2    1

    down
    5    5    4    5    5
    4    4    3    4    4
    3    3    2    3    3
    2    2    1    2    2
    1    1    0    1    1

    merge
    5    4    3    2    1
    4    4    3    2    1
    3    3    2    2    1
    2    2    1    2    1
    1    1    0    1    1


        up
    1   1   1   1   1
    1   2   2   2   2
    1   2   3   3   3
    1   2   3   4   4
    1   2   0   1   2


    1   1   1   1   1
    1   2   2   2   1
    1   2   2   2   1
    1   2   1   2   1
    1   1   0   1   1


    */

}

// TC: O(n^2)
// SC: O(n^2)
```

