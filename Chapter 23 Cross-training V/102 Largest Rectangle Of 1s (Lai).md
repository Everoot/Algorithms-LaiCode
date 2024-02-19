# 102 Largest Rectangle Of 1s (Lai)

Determine the largest rectangle of 1s in a binary matrix (a binary matrix only contains 0 and 1), return the area.

**Assumptions**

- The given matrix is not null and has size of M * N, M >= 0 and N >= 0

**Examples**

{ {0, 0, 0, 0},

 {1, **1, 1, 1**},

 {0, **1, 1, 1**},

 {1, 0, 1, 1} }

the largest rectangle of 1s has area of 2 * 3 = 6



 **Solution:**

```java
public class Solution {
  public int largest(int[][] matrix) {
    // Write your solution here

    if (matrix.length == 0){
      return 0;
    }

    int result = 0;
    int[][] dp = new int[matrix.length][matrix[0].length];

    for (int i = 0; i < matrix.length; i++){
      for (int j = 0; j < matrix[0].length; j++){
        if (matrix[i][j] == 1){
          if (j == 0){
            dp[i][j] = 1;
          }else{
            dp[i][j] = dp[i][j-1] + 1;
          }
        }

        int width = dp[i][j];

        for (int k = i; k >= 0; k--){
          width = Math.min(width, dp[k][j]);
          result = Math.max(result, width * (i - k + 1));
        }
      }
    }

    return result;
  }
}

// TC: O(n^2 * m )
// SC: O(n*m)
```



```java
public class Solution {
  public int largest(int[][] matrix) {
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

    private void buildHeightMatrix(int[][] matrix, int[][] height){
        // linear scan + look back
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                if (matrix[i][j] == 1){
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

