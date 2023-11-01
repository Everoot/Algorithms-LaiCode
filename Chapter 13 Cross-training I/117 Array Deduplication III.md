# 117 Array Deduplication III

Given a sorted integer array, remove duplicate elements. For each group of elements with the same value do not keep any of them. Do this in-place, using the left side of the original array and and maintain the relative order of the elements of the array. Return the array after deduplication.

**Assumptions**

- The given array is not null

**Examples**

- {1, 2, 2, 3, 3, 3} → {1}



**Solution**:

```java
public class Solution {
  public int[] dedup(int[] array) {
    // Write your solution here
    if (array == null || array.length <= 1){
      return array;
    }

    int slow = 0;
    int begin = 0;
    while(begin < array.length){
      int fast = begin + 1;
      while(fast < array.length && array[begin] == array[fast]){
        fast++;
      }
      // after the inner while loop, 'begin' poinst to the first element
      // after after array[fast] that doesn't equal array[fast];
      if (fast - begin == 1){
        array[slow] = array[begin];
        slow++;
      }
      begin = fast;
    }

    int[] result = new int[slow];
    for (int i = 0; i < result.length; i++){
      result[i] = array[i];
    }
    return result;
  }
}

// TC: O(n)
// SC: O(1) / O(n)
```

