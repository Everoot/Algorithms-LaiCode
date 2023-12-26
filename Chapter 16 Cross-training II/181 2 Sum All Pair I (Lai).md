#  181 2 Sum All Pair I (Lai)

Find all pairs of elements in a given array that sum to the given target number. Return all the pairs of indices.

**Assumptions**

- The given array is not null and has length of at least 2.

**Examples**

- A = {1, 3, 2, 4}, target = 5, return [[0, 3], [1, 2]]
- A = {1, 2, 2, 4}, target = 6, return [[1, 3], [2, 3]]

**Solution**

```java
public class Solution {
  /* 1,2,3,4,3 */
  /* target = 6 */
  public List<List<Integer>> allPairs(int[] array, int target) {
    // Write your solution here
    // Assumptions: array is not null, array.length >= 2
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    // key: number, value: list of all possible indeices
    Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
    // value, index
    for (int i = 0; i < array.length; i++){
      List<Integer> indices = map.get(target-array[i]); // 6-4 =2 .get 1  // if not exit null
      // if target-array[i] is in the map,
      // we can get all the pairs(j,i), with i as the larger index
      if (indices != null){ 
        for (int j : indices){ // 1
          result.add(Arrays.asList(j,i));  //[1,3] 
        }
      } 
      // add current index i to all the possibtle indices for value of array[i]
      //   
      if (!map.containsKey(array[i])){
        map.put(array[i], new ArrayList<Integer>());    // map:<1,[0]>, <2,[]> <3,[]>
      }
      //   // exist add current index
      map.get(array[i]).add(i);    // map<1,[0]> <2,[1]> ,<3,[2]>  add index
    }
    return result;
  }
}

// TC: O(n^2) -> O(n)
// SC: O(n^2)
```

