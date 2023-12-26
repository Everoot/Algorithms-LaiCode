# 10 Quick Sort (Lai)

Given an array of integers, sort the elements in the array in ascending order. The quick sort algorithm should be used to solve this problem.

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
  public int[] quickSort(int[] array) {
    // Write your solution here

    // base case 
    if (array == null || array.length <= 1){
      return array;
    }

    int left = 0;
    int right = array.length - 1;
    quickSort(array, left, right);
    return array;
  }

  private void quickSort(int[] array, int left, int right){
    // base 
    if (left >= right){
      return;
    }

    int p = array[right];

    int i = 0;

    int j = right - 1;

    while (i <= j){             // logn    n 
      if (array[i] > p){
        swap(array, i, j);
        j--;
      }else{
        i++;
      }
    }

    swap(array, i, right);
    quickSort(array, left, i-1);
    quickSort(array, i+1, right);
    return;
  }

  private void swap(int[] array, int i, int j){
    int rem = array[i];
    array[i] = array[j];
    array[j] = rem; 
  }
}





/*
                p         p
           {-3, 1, 6, 4, 2 }
           left          right
             r 
                l                  [left, l-1] < p    [l+1, right] > p                

// TC:O(nlogn) -> n*n = n^2
// SC:O(n)

*/
```

