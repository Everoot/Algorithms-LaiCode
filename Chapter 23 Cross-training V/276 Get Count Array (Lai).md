# 276 Get Count Array (Lai)

Given an array A of length N containing all positive integers from [1...N]. How to get an array B such that B[i] represents how many elements A[j] (j > i) in array A that are smaller than A[i].

**Assumptions**:

- The given array A is not null.

**Examples:**

- A = { 4, 1, 3, 2 }, we should get B = { 3, 0, 1, 0 }.

**Requirement:**

- time complexity = O(nlogn).

**Solution:**

```java
public class Solution {
  public int[] countArray(int[] array) {
    // Write your solution here

    int[] result = new int[array.length];

    int[] indices = new int[array.length];
    for (int i = 0; i < array.length; i++){
      indices[i] = i;
    }

    int left = 0;
    int right = array.length-1;

    mergeSort(array, result, indices, left, right);
    return result;
  }

  private void mergeSort(int[] array, int[] result, int[] indices, int left, int right){
    if (left >= right){
      return;
    }

    int mid = left + (right- left)/2;
    mergeSort(array, result, indices, left, mid);
    mergeSort(array, result, indices, mid + 1, right);
    merge(array, result, indices, left, right);
  }

  private void merge(int[] array, int[] result, int[] indices, int left, int right){
    int mid = left + (right - left)/2;
    int leftIndex = left;
    int rightIndex = mid+1;
    
    int rightCount = 0;

    int[] sorted = new int[right - left + 1];
    int sortedIndex = 0;

    while (leftIndex <= mid && rightIndex <= right){
      if (array[indices[rightIndex]] < array[indices[leftIndex]]){
        rightCount++;
        sorted[sortedIndex] = indices[rightIndex];
        sortedIndex++;
        rightIndex++;
      }else{
        result[indices[leftIndex]] = result[indices[leftIndex]] + rightCount;
        sorted[sortedIndex] = indices[leftIndex];
        sortedIndex++;
        leftIndex++;
      }
    }

    while(leftIndex <= mid){
      result[indices[leftIndex]] += rightCount;
      sorted[sortedIndex] = indices[leftIndex];
      sortedIndex++;
      leftIndex++;
    }

    while(rightIndex <= right){
      sorted[sortedIndex] = indices[rightIndex];
      sortedIndex++;
      rightIndex++;
    }

    for (int i = left; i <= right; i++){
      indices[i] = sorted[i - left];
    }
  }
}


// TC: O(nlogn)
// SC: O(n)
```



```java
public class Solution {
  public int[] countArray(int[] array) {
    // Write your solution here
    // The indexArray contians the indices in the original array,
    // original array.
    // The countArray is the actual return array.
    // The helper array is to help the merge sort.
    int[] indexArray = new int[array.length];
      // The indices are just 0 - (array.length -1)
    for (int i = 0; i < array.length; i++){
      indexArray[i] = i;
    }


    int[] countArray = new int[array.length];
    int[] helper = new int[array.length];
    mergeSort(array, indexArray, countArray, helper, 0, array.length - 1);
    return countArray;
  }

  private void mergeSort(int[] array, int[] indexArray, int[] countArray, int[] helper, int left, int right){
    if (left >= right){
      return;
    }

    int mid = left + (right - left)/2;
    mergeSort(array, indexArray, countArray, helper, left, mid);
    mergeSort(array, indexArray, countArray, helper, mid + 1, right);
    merge(array, indexArray, countArray, helper, left, mid, right);
  }

  private void merge(int[] array, int[] indexArray, int[] countArray, int[] helper, int left, int mid, int right){
    copyArray(indexArray, helper, left, right);
    int l = left;
    int r = mid + 1;

    int cur = left;
    while(l <= mid){
      // When sorting the indexArray, we use the corresponding value in the 
      // original array.
      if (r > right || array[helper[l]] <= array[helper[r]]){
        countArray[helper[l]] += (r - mid -1);
        indexArray[cur++] = helper[l++];
      }else{
        indexArray[cur++] = helper[r++];
      }
    }
  }

  private void copyArray(int[] indexArray, int[] helper, int left, int right){
    for (int i = left; i <= right; i++){
      helper[i] = indexArray[i];
    }
  }

}

// TC: O(nlogn)
// SC: O(n)
```



