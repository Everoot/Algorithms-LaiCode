# 182 2 Sum All Pair II (Lai)

Find all pairs of elements in a given array that sum to the pair the given target number. Return all the **distinct** pairs of values.

**Assumptions**

- The given array is not null and has length of at least 2
- The order of the values in the pair does not matter

**Examples**

- A = {2, 1, 3, 2, 4, 3, 4, 2}, target = 6, return [[2, 4], [3, 3]]

 

**Solution:**

```java
public class Solution {
  public List<List<Integer>> allPairs(int[] array, int target) {
    // Write your solution here
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    // Assumptions: array is not null, array.length >= 2
    Arrays.sort(array);

    int left = 0;
    int right = array.length - 1;
    while(left < right){
      // ignore all the consecutive duplicate values when we want 
      // to determine the smaller element of the pair.
      if (left > 0 && array[left] == array[left - 1]){
        left++;
        continue;
      }

      int cur = array[left] + array[right];

      if (cur == target){
        result.add(Arrays.asList(array[left], array[right]));
        left++;
        right--;
      }else if (cur < target){
        left++;
      }else{
        right--;
      }
    }
    return result;

  }
}

// TC: O(nlogn)
// SC: O(n)
```

