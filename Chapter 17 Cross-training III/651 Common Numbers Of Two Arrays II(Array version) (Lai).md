#651 Common Numbers Of Two Arrays II(Array version) (Lai)

Find all numbers that appear in both of two unsorted arrays.

**Assumptions**

- Both of the two arrays are not null.
- In any of the two arrays, there could be duplicate numbers.

**Examples**

- A = {1, 2, 3, 2}, B = {3, 4, 2, 2, 2}, return [2, 2, 3] (there are both two 2s in A and B)

 

**Solution:**

```java
public class Solution {
  public List<Integer> common(int[] A, int[] B) {
    // Write your solution here
    List<Integer> result = new ArrayList<Integer>();

    Arrays.sort(A);
    Arrays.sort(B);

    int i = 0;
    int j = 0;
    
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


// TC: O(nlogn)

// SC: O(n)
```

