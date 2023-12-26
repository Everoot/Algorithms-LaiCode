# 9 Merge Sort (Lai)

Given an array of integers, sort the elements in the array in ascending order. The merge sort algorithm should be used to solve this problem.

**Examples**

- {1} is sorted to {1}
- {1, 2, 3} is sorted to {1, 2, 3}
- {3, 2, 1} is sorted to {1, 2, 3}
- {4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6}

**Corner Cases**

- What if the given array is null? In this case, we do not need to do anything.
- What if the given array is of length zero? In this case, we do not need to do anything.



**Solution:**

```java
public class Solution {
  public int[] mergeSort(int[] array) {
    // Write your solution here
    // base case 
    if (array == null || array.length <= 1){
      return array;
    }

    int l = 0;
    int r = array.length -1;
    return mergeSort(array, l, r);
  }

  private int[] mergeSort(int[] arr, int l, int r){
    // base case 
    if (l == r){
      return new int[]{arr[l]};
    }

    int m = l + (r-l)/2;
    int[] lArr = mergeSort(arr, l, m);
    int[] rArr = mergeSort(arr, m+1, r);

    return merge(lArr, rArr);
  }

  private int[] merge(int[] lArr, int[] rArr){
    int[] result = new int[lArr.length + rArr.length];

    int index = 0;
    int l = 0;
    int r = 0;

    while(l < lArr.length && r < rArr.length){
      if (lArr[l] < rArr[r]){
        result[index] = lArr[l];
        l++;
        index++;
      }else{
        result[index] = rArr[r];
        r++;
        index++;
      }
    }

    while (l < lArr.length){
      result[index] = lArr[l];
      l++;
      index++;
    }

    while (r < rArr.length){
      result[index] = rArr[r];
      r++;
      index++;
    }

    return result;
  }
}


/*
                0  1   2  3  4
               {4, 2, -3, 6, 1}    {-3, 1, 2, 4, 6}
                l            r     mid = l+(r-l)/2 = 0+ (4-0)/2 = 2
               /             \
[-3,2,4]    4,2,-3              6,1 [1,6]
          / \               / \
[2,4]    4,2  -3             6  1  
        /\
       4  2 
       

// TC: O(n+ nlogn) = O(nlogn)
// SC: stack + heap = O(logn)+O(n) = O(n)
*/

```

