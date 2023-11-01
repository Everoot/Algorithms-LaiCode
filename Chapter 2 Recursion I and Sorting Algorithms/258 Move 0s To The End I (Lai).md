# 258 Move 0s To The End I (Lai)

LaiCode 258

Given an array of integers, move all the 0s to the right end of the array.

The relative order of the elements in the original array does not need to be maintained.

**Assumptions:**

- The given array is not null.

**Examples:**

- {1} --> {1}
- {1, 0, 3, 0, 1} --> {1, 3, 1, 0, 0} or {1, 1, 3, 0, 0} or {3, 1, 1, 0, 0}

```java
public class Solution {
  public int[] moveZero(int[] array) {
    // Write your solution here
    if (array == null || array.length == 0){
      return array;
    }
    int j = array.length-1;
    int i = 0;
    while(i <= j ){
      if (array[i] == 0){
        swap(array, i, j);
        j--;
      }else {
        i++;
      }
    }
    return array;
  }

  public static void swap(int[] array, int i, int j){
    int rem = array[i];
    array[i] = array[j];
    array[j] = rem;
  }
}

```



LeetCode is harder.
