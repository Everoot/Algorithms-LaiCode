# 180 2 Sum (Lai)

Determine if there exist two elements in a given array, the sum of which is the given target number.

**Assumptions**

- The given array is not null and has length of at least 2

**Examples**

- A = {1, 2, 3, 4}, target = 5, return true (1 + 4 = 5)
- A = {2, 4, 2, 1}, target = 4, return true (2 + 2 = 4)
- A = {2, 4, 1}, target = 4, return false

 

**Solution:**

```java
public class Solution {
  public boolean existSum(int[] array, int target) {
    // Write your solution here

    int left = 0;
    int right = array.length -1;
    Arrays.sort(array);
    while(left < right){
      int sum = array[left] + array[right];
      if (sum == target){
        return true;
      }else if (sum < target){
        left++;
      }else{
        right--;
      }
    }
    return false;
  }
}

// TC: O(nlogn)
// SC: O(1)
```



```java
public class Solution {
  public boolean existSum(int[] array, int target) {
    // Write your solution here

    Set<Integer> set = new HashSet<Integer>();
    for (int i = 0; i < array.length; i++){
      if (set.contains(target - array[i])){
        return true;
      }
      set.add(array[i]);
    }

    return false;
  }
}

// TC: O(n)
// SC: O(n)
```

