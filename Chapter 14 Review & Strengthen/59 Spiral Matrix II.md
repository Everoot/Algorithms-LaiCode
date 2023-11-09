# 59 Spiral Matrix II

Given a positive integer `n`, generate an `n x n` `matrix` filled with elements from `1` to `n2` in spiral order.

**Example 1:**

![img](./59 Spiral Matrix II/spiraln.jpg)

```
Input: n = 3
Output: [[1,2,3],[8,9,4],[7,6,5]]
```

**Example 2:**

```
Input: n = 1
Output: [[1]]
```



**Solution:**

```java
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int left = 0;
        int right = n-1;
        int up = 0;
        int down = n-1;
        int number = 1;

        while (left < right){
            // -> left -> right
            for (int i = left; i < right; i++){
                matrix[up][i] = number++;
            }


            // -> up -> down
            for (int j = up; j < down; j++ ){
                matrix[j][right] = number++;
            }

            // -> right -> left
            for (int i = right; i > left; i--){
                matrix[down][i] = number++;
            }      
            // down -> up
            for (int j = down; j > up; j--){
                matrix[j][left] = number++;
            }

            left++;
            right--;
            up++;
            down--;
        }

        if (left == right){
            matrix[left][right] = number;
        }

        return matrix;
    }
}
// TC: O(n^2)
// SC: O(n)
```

