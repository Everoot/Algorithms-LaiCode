# 48 Rotate Image

You are given an `n x n` 2D `matrix` representing an image, rotate the image by **90** degrees (clockwise).

You have to rotate the image [**in-place**](https://en.wikipedia.org/wiki/In-place_algorithm), which means you have to modify the input 2D matrix directly. **DO NOT** allocate another 2D matrix and do the rotation.

 

**Example 1:**

![img](./48 Rotate Image/mat1.jpg)

```
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]
```



**Example 2:**

![img](./48 Rotate Image/mat2.jpg)

```
Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
```

 

**Solution:**

不难但很烦

```java
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n <= 1){
            return; 
        }

        for (int round = 0; round < n/2; round++){
            int left = round;
            int right = n - 1 - round; // 4 - 1 = 3 - 1 =2 
            int up = round; // up = left
            int down = n - 1 - round; // down = right

            for (int i = left; i < right; i++){
                int tmp = matrix[up][i];
                // down -> up
                matrix[up][i] = matrix[n - 1 - i][left];

                // right -> left
                matrix[n - 1 - i][left] = matrix[down][n-1-i];

                // up -> down
                matrix[down][n-1-i] = matrix[i][right];

                // left -> right

                matrix[i][right] = tmp;
            }

        }
    }
}

// TC: O(n^2)
// SC: O(1)
```



Method 2: 必须保证正方形

[1, 2, 3],

[4, 5, 6],

[7, 8, 9]

Step 1: 对角线交换

[1, 4, 7],

[2, 5, 8],

[3, 6, 9]

Step 2: 以中轴左swap 左右reverse每一行

[7, 4, 1],

[8, 5, 2],

[9, 6, 3]



**solution 2:**

```java
class Solution {
    public void rotate(int[][] matrix) {
       int n = matrix.length; 
       if (n <= 1){
           return;
       } 

       // Step 1 对角线交换
       for (int i = 0; i < n; i++){
           for (int j = i; j < n; j++){
               int tmp = matrix[i][j];
               matrix[i][j] = matrix[j][i];
               matrix[j][i] = tmp;
           }
       } 

       // Step 2 以中轴左swap 左右 reverse每一行
       for (int i = 0; i < n; i++){
           for (int j = 0; j < n/2; j++){
               int tmp = matrix[i][j];
               matrix[i][j] = matrix[i][n - j - 1];
               matrix[i][n - j - 1] = tmp;
           }
       }
    }
}

//TC: O(n^2)
//SC: O(1)
```



