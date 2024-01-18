# 652 Common Numbers Of Two Sorted Arrays(Array version) (Lai)

Find all numbers that appear in both of two sorted arrays (the two arrays are all sorted in ascending order).

**Assumptions**

- In each of the two sorted arrays, there could be duplicate numbers.
- Both two arrays are not null.

**Examples**

- A = {1, 1, 2, 2, 3}, B = {1, 1, 2, 5, 6}, common numbers are [1, 1, 2]



**Solution:**

```java
public class Solution {
  public List<Integer> common(int[] A, int[] B) {
    // Write your solution here
    List<Integer> result = new ArrayList<Integer>();

    int i = 0; // A 
    int j = 0; // B

    while(i < A.length && j < B.length){
      if (A[i] == B[j]){
        result.add(A[i]);
        i++;
        j++;
      }else if (A[i] < B[j]){
        i++;
      }else{
        j++;
      }
    }

    return result;

  }
}

// TC: O(n)
// SC: O(n)

```

