# 186 3 Sum (Lai)

Determine if there exists three elements in a given array that sum to the given target number. Return all the triple of values that sums to target.

**Assumptions**

- The given array is not null and has length of at least 3
- No duplicate triples should be returned, order of the values in the tuple does not matter

**Examples**

- A = {1, 2, 2, 3, 2, 4}, target = 8, return [[1, 3, 4], [2, 2, 4]]

 

**Solution**

```java
public class Solution {
  public List<List<Integer>> allTriples(int[] array, int target) {
    // Write your solution here

    List<List<Integer>> result = new ArrayList<List<Integer>>();
    
    Arrays.sort(array);

    for (int i = 0; i < array.length - 2; i++){
      // check wether if duplicate
      if (i > 0 && array[i] == array[i-1]){
        continue;
      }

      int j = i+1;
      int k = array.length - 1;

      while(j < k){
        int cur = array[j] + array[k];
        if (array[i] + cur == target){
          result.add(Arrays.asList(array[i], array[j], array[k]));
          j++;
          // check whether nums[j] duplicate
          while(j < k && array[j] == array[j-1]){
            j++;
          }
        }else if (array[i] + cur < target){
          j++;
        }else{
          k--;
        }
      }
    }
    return result;
  }
}


// TC: O(n^2)
// sC: O(n)

```

