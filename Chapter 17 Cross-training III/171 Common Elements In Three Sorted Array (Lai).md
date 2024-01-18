# 171 Common Elements In Three Sorted Array (Lai)

Find all common elements in 3 sorted arrays.

**Assumptions**

- The 3 given sorted arrays are not null
- There could be duplicate elements in each of the arrays

**Examples**

- A = {1, 2, 2, 3}, B = {2, 2, 3, 5}, C = {2, 2, 4}, the common elements are [2, 2]

 

**Solution:**

```java
public class Solution {
  public List<Integer> common(int[] a, int[] b, int[] c) {
    // Write your solution here
    List<Integer> result = new ArrayList<Integer>();

    int i = 0;
    int j = 0;
    int k = 0;
    while (i < a.length && j < b.length && k < c.length){
      if (a[i] == b[j] && b[j] == c[k]){
        result.add(a[i]);
        i++;
        j++;
        k++;
      }else{
        if (a[i] <= b[j] && a[i] <= c[k]){
          i++;
        }else if (b[j] <= a[i] && b[j] <= c[k]){
          j++;
        }else{
          k++;
        }
      }
    }

    return result;
  }
}
// TC: O(n)
// SC: O(1)

```

