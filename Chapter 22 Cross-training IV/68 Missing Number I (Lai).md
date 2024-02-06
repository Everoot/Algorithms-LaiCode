# 68 Missing Number I (Lai)

Given an integer array of size N - 1, containing all the numbers from 1 to N except one, find the missing number.

**Assumptions**

- The given array is not null, and N >= 1

**Examples**

- A = {2, 1, 4}, the missing number is 3
- A = {1, 2, 3}, the missing number is 4
- A = {}, the missing number is 1



**Solution:**

```java
public class Solution {
  public int missing(int[] array) {
    // Write your solution here

    // base case
    if (array.length == 0){
      return 1;
    }

    Set<Integer> set = new HashSet<Integer>();

    for (int i = 0; i < array.length; i++){
      set.add(array[i]);
    }

    int result = array.length + 1;
    for (int i = 1; i < array.length + 1; i++){
      if (!set.contains(i)){
        result = i;
        break;
      }
    }

    return result;
  }
}
// TC: O(n)
// SC: O(n)

```

