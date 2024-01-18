# 650 Common Numbers Of Two Arrays I(Array version) (Lai)

Find all numbers that appear in both of the two unsorted arrays, return the common numbers in increasing order.

**Assumptions**

- Both arrays are not null.
- There are no duplicate numbers in each of the two arrays respectively.

**Exmaples**

- A = {1, 2, 3}, B = {3, 1, 4}, return [1, 3]
- A = {}, B = {3, 1, 4}, return []



**Solution:**

```java
public class Solution {
  public List<Integer> common(int[] a, int[] b) {
    // Write your solution here

    List<Integer> result = new ArrayList<Integer>();

    Set<Integer> set = new HashSet<Integer>();

    for (int i = 0; i < a.length; i++){
      set.add(a[i]);
    }

    Arrays.sort(b);

    for (int j = 0; j < b.length; j++){
      if (set.contains(b[j])){
        result.add(b[j]);
      }
    }

    return result;
  }
}
// TC: O(nlogn)
// SC: O(n)
```

