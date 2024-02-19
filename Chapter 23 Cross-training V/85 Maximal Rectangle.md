# 85 Maximal Rectangle

Given a `rows x cols` binary `matrix` filled with `0`'s and `1`'s, find the largest rectangle containing only `1`'s and return *its area*.

 

**Example 1:**

![img](./85 Maximal Rectangle/maximal.jpg)

```
Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 6
Explanation: The maximal rectangle is shown in the above picture.
```

**Example 2:**

```
Input: matrix = [["0"]]
Output: 0
```

**Example 3:**

```
Input: matrix = [["1"]]
Output: 1
```



**Solution:**

```java
class Solution {
    public int maximalRectangle(char[][] matrix) {

        if (matrix.length == 0) return 0;
        int maxarea = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if (matrix[i][j] == '1'){

                    // compute the maximum width and update dp with it
                    dp[i][j] = j == 0? 1 : dp[i][j-1] + 1;

                    int width = dp[i][j];

                    // compute the maximum area rectangle with a lower right corner at [i, j]
                    for(int k = i; k >= 0; k--){
                        width = Math.min(width, dp[k][j]);
                        maxarea = Math.max(maxarea, width * (i - k + 1));
                    }
                }
            }
        } return maxarea;
    }
}

// TC: O(N^2M)
// SC: O(NM)
```



```java
class Solution {
    public int maximalRectangle(char[][] matrix) {
        /*
        height[i][j] represent the longest concetive one starting at matrix[i][j]
        towards the top. (ending at i, j must including i,j)
        */

        int[][] heights = new int[matrix.length][matrix[0].length];
        buildHeightMatrix(matrix, heights);

        int max = 0;
        for (int[] heightArray : heights){
            max = Math.max(max, largestRectangleInHistogram(heightArray));
        }

        return max;
    }

    private void buildHeightMatrix(char[][] matrix, int[][] height){
        // linear scan + look back
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                if (matrix[i][j] == '1'){
                    if (i > 0){
                        height[i][j] = height[i - 1][j]+ 1;
                    }else{
                        height[i][j] = 1;
                    }
                }
            }
        }
    }


    private int largestRectangleInHistogram(int[] array){
        // Assumptions: array is not null, array.length >= 1
        // all the values in the array are non-negative.
        int result = 0;
        // Note that the stack contains the "index",
        // not the "value" of the array.
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i <= array.length; i++){
            // we need a way of popping out all the elements in the stack
            // at last, so that we explicitly add a bar of height 0.


            int cur = 0;
            if (i != array.length){
                cur = array[i];
            }
            while(!stack.isEmpty() && array[stack.peekFirst()] >= cur){
                int height = array[stack.pollFirst()];
                // determine the left boundary of the larges rectangle
                // with height array[i].
                int left = 0;
                if (!stack.isEmpty()){
                    left = stack.peekFirst() + 1;
                }
                // determine the right boundary of the largest rectangle
                // with height of the popped element.
                result = Math.max(result, height * (i - left));
            }
            stack.offerFirst(i);
        }
        return result;
    }

}

// TC: O(n^2)
// SC: O(n^2)
```

