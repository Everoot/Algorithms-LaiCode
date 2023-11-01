# 264 Keep Distance For Identical Elements (Lai)

Given an integer k, arrange the sequence of integers [1, 1, 2, 2, 3, 3, ...., k - 1, k - 1, k, k], such that the output integer array satisfy this condition:

Between each two i's, they are exactly i integers (for example: between the two 1s, there is one number, between the two 2's there are two numbers).

**If there does not exist such sequence, return null.**

**Assumptions:**

- k is guaranteed to be > 0

**Examples:**

- k = 3, The output = { 2, 3, 1, 2, 1, 3 }.



**Solution:**

```java
public class Solution {
  public int[] keepDistance(int k) {
    // Write your solution here.
    int[] array = new int[2 * k];
    boolean result = helper(array, k);
    if (result == true){
      return array;
    }else{
      return null;
    }
  }

  private static boolean helper(int[] array, int n){
    if (n == 0){
      return true;
    }

    for (int i = 0; i < array.length - n - 1; i++){
      if (array[i] == 0 && array[i + n + 1] == 0){
        array[i] = n;
        array[i + n + 1] = n;
        if (helper(array, n - 1) == true){
          return true;
        }
        array[i] = 0;
        array[i + n + 1] = 0;
      }
    }

    return false;
  }
}

//Time: O(n!)
// Space: O(n)
/*
如果现在要放的数字是k, 准备把k放在index i 
第一个如果放在了index i
第二个k就要放在 i + k + 1

这两个index不能越界
这两个位置上不能放过别人

*/
```

