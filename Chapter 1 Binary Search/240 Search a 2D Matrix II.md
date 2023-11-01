# 240 Search a 2D Matrix II

Write an efficient algorithm that searches for a value `target` in an `m x n` integer matrix `matrix`. This matrix has the following properties:

- Integers in each row are sorted in ascending from left to right.
- Integers in each column are sorted in ascending from top to bottom.

 

**Example 1:**

<img src="./240 Search a 2D Matrix II.assets/searchgrid2.jpg" alt="img" style="zoom: 50%;" />

```
Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
Output: true
```

**Example 2:**

<img src="./240 Search a 2D Matrix II.assets/searchgrid.jpg" alt="img" style="zoom:50%;" />

```
Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
Output: false
```



```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix[0].length == 0){
            return false;
        }

        for (int i = 0; i < matrix.length; i++){
            boolean result = binarySearch(matrix[i], target);
            if (result == true){
                return true;
            }
        }   
        return false;
    }

    public static boolean binarySearch(int[] array, int target){
        int left = 0; 
        int right = array.length -1;
        while (left <= right){
            int mid = left + (right - left)/2;
            if (array[mid] == target){
                return true;
            }else if (array[mid] < target){
                left = mid + 1;
            }else if (array[mid] > target){
                right = mid - 1;
            } 
        }

        return false;
    }
}

// TC O(nlogn)
// SC O(1)
```



matrix.length = row

matrix[0].length = col

