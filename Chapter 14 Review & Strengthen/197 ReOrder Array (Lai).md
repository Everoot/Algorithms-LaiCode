# 197 ReOrder Array (Lai)

Given an array of elements, reorder it as follow:

- { N1, N2, N3, …, N2k } → { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k }
- { N1, N2, N3, …, N2k+1 } → { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k, N2k+1 }

Try to do it in place.

**Assumptions**

- The given array is not null

**Examples**

- { 1, 2, 3, 4, 5, 6} → { 1, 4, 2, 5, 3, 6 }
- { 1, 2, 3, 4, 5, 6, 7, 8 } → { 1, 5, 2, 6, 3, 7, 4, 8 }
- { 1, 2, 3, 4, 5, 6, 7 } → { 1, 4, 2, 5, 3, 6, 7 }

**Solution:**

```java
public class Solution {
  public int[] reorder(int[] array) {
    // Write your solution here
    if (array == null || array.length == 0){
      return array;
    }

    if (array.length % 2 == 0){
      helper(array, 0, array.length - 1);
    } else{
      // if array has odd number of elements, we ignore the last one 
      // when do the reordering
      helper(array, 0, array.length - 2);
    }
    return array;
  }

  private static void helper(int[] array, int left, int right){
    int length = right - left + 1;
    // if the subarray has 2 or 0 elements, we can just return
    // as this should be the base case
    if (length <= 2){
      return;
    }
    // Calculate the important mid points
    // 0 1     2 3      4 5     6 7
    // lm: 2, m: 4, rm: 6
    // 0 1    2 3 4        5 6     7 8 9 
    // lm: 2 , m: 5, rm: 7    

    int mid = left + length /2;// 用length 而不用left, right 是为了保证chunk1==chunk3
    int lmid = left + length /4;
    int rmid = left + length * 3 /4; // or mid + length /4;
    // I love yahoo trick
    reverse(array, lmid, mid-1);
    reverse(array, mid, rmid-1);
    reverse(array, lmid, rmid-1);
    // half of the left partition's size = lmid - left
    // 左半边子问题 left to 2*chunk1 size - 1 
    helper(array, left, left + (lmid - left) * 2 -1); // subproblem
    helper(array, left + (lmid-left) * 2, right);
  }

  private static void reverse(int[] array, int left, int right){
    while(left < right){
      int rem = array[left];
      array[left] = array[right];
      array[right] = rem;
      left++;
      right--;
    }
  }
}

// TC: O(n) where n is the length of string
// SC: O(n)
// "I love yahoo Trick" -> 局部顺序保持不变, 但是整体顺序反转
// chunk1 + chunk2 Match chunck3 + chunk4
// 一定保证chunk1的size和chunk3的size 必须相同!
// 3434 完美分割方法
// 这种题 需要记忆 方法来解题
```

