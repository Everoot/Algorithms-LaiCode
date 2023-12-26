# 188 4 Sum (Lai)

Determine if there exists a set of four elements in a given array that sum to the given target number.

**Assumptions**

- The given array is not null and has length of at least 4

**Examples**

- A = {1, 2, 2, 3, 4}, target = 9, return true(1 + 2 + 2 + 4 = 9)
- A = {1, 2, 2, 3, 4}, target = 12, return false



**Solution:**

```java
public class Solution {
  public boolean exist(int[] array, int target) {
    // Write your solution here
    Arrays.sort(array);
    for (int i = 0; i < array.length - 4 + 1; i++){
      for (int j = i + 1; j < array.length - 4 + 2; j++){
        int left = j+1;
        int right = array.length-1;
        int curTarget = target - array[i] - array[j];
        while(left < right){
          if (array[left] + array[right] == curTarget){
            return true;
          }else if (array[left] + array[right] < curTarget){
            left++;
          }else{
            right--;
          }
        }
      }
    }
    return false;

  }
}

// TC: O(n^3)
// SC: O(1)


/*
    0  1  2  3  4
   {1, 2, 2, 3, 4}
    i  j
         l      r


*/
```

