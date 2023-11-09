# 54 Spiral Matrix

Given an `m x n` `matrix`, return *all elements of the* `matrix` *in spiral order*.

**Example 1:**

![img](./54 Spiral Matrix/spiral1.jpg)

```
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]
```

**Example 2:**

![img](./54 Spiral Matrix/spiral.jpg)

```
Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
```



**Solution:** 

```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();
        int row = matrix.length; // 3
        int col = matrix[0].length; // [1,2,3,4] 4
        // base case
        if (row == 0 || col == 0){
            return result;
        }

        if (row == 1){
            // [[1,2,3,4]]
            for (int i = 0; i < col; i++){
                result.add(matrix[row-1][i]);
            }
            return result;
        }

        if (col == 1){
            // [[1] [2] [3]]
            for (int i = 0; i < row; i++){
                result.add(matrix[i][col-1]);
            }
            return result;
        }

        // index
        int left = 0;
        int right = col -1;
        int up = 0;
        int down =  row -1;

        while(left < right && up < down){

            // left -> right
            for (int i = left; i < right; i++){
                result.add(matrix[up][i]);
            }
            
            // up -> down
            for (int j = up; j < down; j++){
                result.add(matrix[j][right]);
            }

            // right->left
            for (int i = right; i > left; i--){
                result.add(matrix[down][i]);
            }

            // down -> up
            for (int j = down; j > up; j--){
                result.add(matrix[j][left]);
            }

            left++;
            right--;
            up++;
            down--;
        }

        // if there is nothing left
        if (left > right || up > down){
            return result;
        }

        // if there is left  
        // one col or one row
        if (left == right){
            // col
            for (int i = up; i <= down; i++){
                result.add(matrix[i][left]);
            }
        }else{
            // row
            for (int i = left; i <= right; i++){
                result.add(matrix[up][i]);
            }
        }
        return result;
    }
}

// TC: O(n)
// SC: O(1)


```



