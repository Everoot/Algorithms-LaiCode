---
tags:
    - Array
    - Binary Search
---



# 202 Kth Smallest In Two Sorted Arrays (Lai)

Given two sorted arrays of integers, find the Kth smallest number.

**Assumptions**

- The two given arrays are not null and at least one of them is not empty
- K >= 1, K <= total lengths of the two sorted arrays

**Examples**

- A = {1, 4, 6}, B = {2, 3}, the 3rd smallest number is 3.
- A = {1, 2, 3, 4}, B = {}, the 2nd smallest number is 2.



**Solution:**

```java
public class Solution {
 public int kth(int[] a, int[] b, int k) {
    int[] merged = new int[a.length + b.length];
    int i = 0, j = 0, idx = 0;
    
    // 合并两个有序数组
    while (i < a.length && j < b.length) {
      if (a[i] < b[j]) {
        merged[idx++] = a[i++];
      } else {
        merged[idx++] = b[j++];
      }
    }
    
    // 如果有剩余，直接复制到合并后的数组中
    while (i < a.length) {
      merged[idx++] = a[i++];
    }
    while (j < b.length) {
      merged[idx++] = b[j++];
    }
    
    // 直接访问第 K 个元素
    return merged[k - 1];
  }

}

// TC: O(n)
// SC: O(n)

```



```java
public class Solution {
  public int kth(int[] a, int[] b, int k) {
    // Assumptions: a, b is not null, at least one of them
    // is not empty, k <= a.length + b.length, k >= 1
    return kth(a, 0, b, 0, k);
  }

  // in the subarray of a starting from index aleft, and
  // subarray of b starting from index bleft, find the kth smallest
  // element among these two subarrays.
  private int kth(int[] a, int aleft, int[] b, int bleft, int k){
    // three base cases:
    // 1. we already eliminate all the elements in a.
    // 2. we already eliminate all the elements in b.
    // 3. when k is reduced to 1, don't miss this base case.
    // The reason why we have as base case is in the following
    // logic we need k >= 2 to make it work.
    if (aleft >= a.length){
      return b[bleft + k - 1];
    }

    if (bleft >= b.length){
      return a[aleft + k - 1];
    }

    if (k == 1){
      return Math.min(a[aleft], b[bleft]);
    }

    // we compare the k/2 the element in a's subarray
    // and the k/2 th element in b's subarray.
    // to determine which k/2 partition can be surely included
    // in the smallest k elements.
    int amid = aleft + k / 2 - 1;
    int bmid = bleft + k / 2 - 1;
    int aval;
    int bval;
    if (amid >= a.length){
      aval = Integer.MAX_VALUE;
    }else{
      aval = a[amid];
    }

    if (bmid >= b.length){
      bval = Integer.MAX_VALUE;
    }else{
      bval = b[bmid];
    }

    if (aval <= bval){
      return kth(a, amid + 1, b, bleft, k - k/2);
    }else{
      return kth(a, aleft, b, bmid + 1, k - k/2);
    }
  }
}

// TC: O(logk)
// SC: O(logk)
```

