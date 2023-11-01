# 119 Largest And Smallest (Lai)

Use the least number of comparisons to get the largest and smallest number in the given integer array. Return the largest number and the smallest number.

**Assumptions**

- The given array is not null and has length of at least 1

**Examples**

- {2, 1, 5, 4, 3}, the largest number is 5 and smallest number is 1. return [5, 1].



**Solution 1:**

```java
public class Solution {
  public int[] largestAndSmallest(int[] array) {
    // Write your solution here
    if (array == null || array.length <= 0){
      return array;
    }

    int[] result = new int[2];
    result[0] = array[0]; // max
    result[1] = array[0]; // min
    for (int i = 0; i < array.length; i++){
      if (array[i] > result[0]){
        result[0] = array[i];
      }
      if (array[i] < result[1]){
        result[1] = array[i];
      }
    }

    return result;

  }
}
// TC: O(n)
// SC: O(1)
```



**Solution 2:**

```java
public class Solution {
  public int[] largestAndSmallest(int[] array) {
    // Write your solution here
    int[] result = new int[2];
    for (int i = 0; i < array.length/2; i++){
      if (array[i] < array[array.length - 1 - i]){
        swap(i, array.length - 1 - i, array);
      } 
    }

    result[0] = largest(array, 0, array.length/2);
    result[1] = smallest(array, array.length/2, array.length-1);
    return result; 
  }

  private static void swap(int i, int j, int[] array){
    int tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
  }

  private static int largest(int[] array, int left, int right){
    int largest = array[left];
    for (int i = left + 1; i <= right; i++){
      largest = Math.max(largest, array[i]);
    }
    return largest;
  }

  private static int smallest(int[] array, int left, int right){
    int smallest = array[left];
    for (int i = left + 1; i <= right; i++){
      smallest = Math.min(smallest, array[i]);
    }
    return smallest;
  }
}

/*
 0  1  2. 3. 4          5 /=2
{3, 4, 5, 1, 2}
       i             


// TC: O(n)
// SC: O(1)
*/

```



```java
public class Solution {
  public int[] largestAndSmallest(int[] array) {
    // Write your solution here
    // Assumptions: array is not null && array.length >=1
    int n = array.length;
    // indices (x, n-1-x) will be paried up, the larger number of 
    // each pair will be put on the left side, so after the comparison,
    // the left half of the array are the larger values for each pairs.
    // the right half of the array are the smaller values for each pairs.
    for (int i = 0; i < n / 2; i++){
      if (array[i] < array[n - 1 - i]){
        swap(array, i, n-1-i);
      }
    }
    // The largest value should be the largest of the left half,
    // The smallest value should be the smallest of the right half.
    return new int[]{largest(array, 0, (n-1)/2), smallest(array, n / 2, n - 1)};
  }

  private int largest(int[] array, int left, int right){
    int largest = array[left];
    for (int i = left + 1; i <= right; i++){
      largest = Math.max(largest, array[i]);
    }
    return largest;
  }

  private int smallest(int[] array, int left, int right){
    int smallest = array[left];
    for (int i = left + 1; i <= right; i++){
      smallest = Math.min(smallest, array[i]);
    }
    return smallest;
  }

  private void swap(int[] array, int i, int j){
    int tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
  }
}
// TC: O(n)
// SC: O(1)
```

