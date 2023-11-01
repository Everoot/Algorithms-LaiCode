# 106 Largest SubMatrix Sum (Lai)

Given a matrix that contains integers, find the submatrix with the largest sum.

Return the sum of the submatrix.

**Assumptions**

- The given matrix is not null and has size of M * N, where M >= 1 and N >= 1

**Examples**

{ {1, -2, **-1, 4**},

 {1, -1,  **1, 1**},

 {0, -1, **-1, 1**},

 {0,  0,  **1, 1**} }

the largest sub-matrix sum is (-1) + 4 + 1 + 1 + (-1) + 1 + 1 + 1 = 7.



**Solution 0 $O(n^6)$:**

```java
public class Solution {
  public int largest(int[][] matrix) {
    // Write your solution here
    // base case 

    int n = matrix.length;
    int m = matrix[0].length;
    if (n == 1 && m == 1){
      return matrix[0][0];
    }
  

    int result = matrix[0][0];
    for (int i = 0; i < n; i++){
      for (int j = 0; j < m; j++){
        for (int k = 0; k < n; k++){
          for (int z = 0; z < m; z++){
            int sum = helper(matrix, i, j, k, z);
            result = Math.max(result, sum);
          }
        }
      }
    }
    return result;
  }

  private static int helper(int[][] matrix, int i, int j, int k, int z){
    int sum = 0;
    for (int startRow = i; startRow <= k; startRow++){
      for (int startCol = j; startCol <= z; startCol++){
        sum = sum + matrix[startRow][startCol];
      }
    }
    return sum;
  }
}

/*

TC: O(n^6)
SC: O(1)
startrow 
startcol 

stoprow
stopcol



*/
```



**Solution 1 (prefixSum 1D) $O(n^5)$:**

```java
public class Solution {
  public int largest(int[][] matrix) {
    // Write your solution here
    // base case 

    int n = matrix.length;
    int m = matrix[0].length;
    if (n == 1 && m == 1){
      return matrix[0][0];
    }
    int result = matrix[0][0];


    // prefixSum row
    // |
    // V
    for (int i = 0; i < n ; i++){ // up
      for (int k = i; k < n; k++){ // bottom
        int[] prefixSum = new int[m];
        for (int startRow = i; startRow <= k; startRow++){
          for (int j = 0; j < m; j++){
            prefixSum[j] = prefixSum[j] + matrix[startRow][j];  
          }
        }

        for (int startCol = 0; startCol < m; startCol++){
          for (int end = startCol; end < m; end++){
            int sum = 0;
            for (int z = startCol; z <= end; z++){
              sum = prefixSum[z] + sum;
              result = Math.max(sum, result);
            }
          }
        } 
      }
    }
    return result;
  }
}

```



**Solution 2 (prefixSum 2D) $O(n^4)$:**

<img src="./106 Largest SubMatrix Sum (Lai).assets/Screen Shot 2022-06-13 at 14.36.17-5102183.png" alt="Screen Shot 2022-06-13 at 14.36.17-5102183" style="zoom: 25%;" />

<img src="./106 Largest SubMatrix Sum (Lai).assets/Screen Shot 2022-06-13 at 14.40.07.png" alt="Screen Shot 2022-06-13 at 14.40.07" style="zoom:25%;" />

```java
public class Solution {
  public int largest(int[][] matrix) {
    // Write your solution here

    // base case 
    int n = matrix.length;
    int m = matrix[0].length;
    if (n == 1 && m == 1){
      return matrix[0][0];
    }

    // prefixRowSum
    int[][] prefixRowSum = new int[n][m];
    for (int i = 0; i < n; i++){
      for (int j = 0; j < m; j++){
        if (j == 0){
          prefixRowSum[i][j] = matrix[i][j];
        }else{
          prefixRowSum[i][j] = prefixRowSum[i][j-1] + matrix[i][j];
        }
      }
    }

    // prefix 
    int[][] prefixSum = new int[n][m];
    for (int i = 0; i < n; i++){
      for (int j = 0; j < m; j++){
        if (i == 0){
          prefixSum[i][j] = prefixRowSum[i][j];
        }else{
          prefixSum[i][j] = prefixRowSum[i][j] + prefixSum[i-1][j];
        }
      }
    }

    int result = matrix[0][0];

    for (int k = 0; k < n; k++){
      for (int z = 0; z < m; z++){
        for (int i = k; i < n; i++){
          for (int j = z; j < m; j++){
            int sum = 0;
            if (k == 0 && z == 0){
              sum = prefixSum[i][j];
            }else if (k == 0){
              sum = prefixSum[i][j] - prefixSum[i][z-1];
            }else if (z == 0){
              sum = prefixSum[i][j] - prefixSum[k-1][j];
            }else{
              sum = prefixSum[i][j] - prefixSum[i][z-1] - prefixSum[k-1][j] + prefixSum[k-1][z-1];
            }

            result = Math.max(sum, result);
          }
        }
      }
    }
    return result;


  }
}

// TC: O(n^4)
// SC: O(n^2)

/*
  1. prefixRowSum
  prefixRowSum[i][j] : represent the from row 0 to i sum of the col

  2. prefixSum 
  prefixSum[i][j]: represent the from col 0 sum of the row


  // base case 
  if (k == 0 && z == 0)
  if (k == 0)
  if (z == 0)
  induction rule
  sum = prefixSum[i][j] - prefixSum[k-1][j] - prefexSum[i][z-1] + prefixSum[i-1][z-1];
  

  return result;



*/
```



**Solution 3 $O(n^3)$:**

```java
public class Solution {
  public int largest(int[][] matrix) {
    // Write your solution here
    // base case 
    int n = matrix.length;
    int m = matrix[0].length;
    if (n == 1 && m == 1){
      return matrix[0][0];
    }

    int result = Integer.MIN_VALUE;
    for (int top = 0; top < n; top++){
      int[] prefixSum = new int[m];
      for (int bottom = top; bottom < n; bottom++){
        for (int col = 0; col < m; col++){
          prefixSum[col] = prefixSum[col] + matrix[bottom][col];
        }

        int sum = helper(prefixSum);
        
        result = Math.max(result, sum);
      }
    }
    return result;

  }


  private static int helper(int[] prefixSum){
    int result = prefixSum[0];
    int pre = prefixSum[0];
    for (int i = 1; i < prefixSum.length; i++){
      pre = Math.max(pre + prefixSum[i], prefixSum[i]);
      result = Math.max(result, pre);
    }
    return result;
  }
}


// TC: O(n^3)
// SC: O(n^2)
/*
{ {1, -2, -1, 4},       ---- top line    -    prefixSum == 1, -2, -1, 4       -> subarray dp 

  {1, -1,  1, 1},                 prefixSum 

  {0, -1, -1, 1},       ---- bottom line 

  {0,  0,  1, 1} }


*/
```



```java
public class Solution {
  public int largest(int[][] matrix) {
    // Write your solution here

    // base case 
    int n = matrix.length;
    int m = matrix[0].length;
    if (n == 1 && m == 1){
      return matrix[0][0];
    }
    // prefix 
    int result = Integer.MIN_VALUE;
    for (int top= 0; top < n; top++){ // top line
      int[] prefixSum = new int[m];
      for (int bottom = top; bottom < n; bottom++){// bottom line
        // for each row i, we add the rows one by one for row j
        // to make sure each time we only introduce O(n) time.
        for (int col = 0; col < m; col++){
            prefixSum[col] = prefixSum[col] + matrix[bottom][col];
        }
        
        // for each possible pair of rows i,j,
        // we would like to know what is the largest submatrix sum
        // using top row as row i and bottom as row j
        // we "flatten" these area to a one dimensinal array.

        result = Math.max(result, helper(prefixSum));
      }
    }
    
    return result;
  }

  // subarray
  private static int helper(int[] prefixSum){
    int result = prefixSum[0];
    int tmp = prefixSum[0];
    for (int i = 1; i < prefixSum.length; i++){
      tmp = Math.max(tmp + prefixSum[i], prefixSum[i]);
      result = Math.max(result, tmp);
    }
    return result;
  }
}
```

