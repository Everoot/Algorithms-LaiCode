# 367 Heap Update Operation (Lai)

Given a binary min heap in array format. Update the element at a specified index.

**Assumptions:**

- The given array is not null or empty.
- The specified index is guaranteed to be within the range.

**Examples:**

array = {1, 2, 3, 4}, update the element at index 1 to 5, the array becomes {1, 4, 3, 5}





```java
public class Solution {
  public int[] updateHeap(int[] array, int index, int ele) {
    // Write your solution here
    int size = array.length;
    int result = array[index];
    array[index] = ele;
    if (result > ele){
      percolateUp(array, index);
    }else{
      percolateDown(array, index, size);
    }
    return array;
  }

  private void percolateUp(int[] array, int index){
    while (index > 0){
      int parentIndex = (index - 1)/2;
      if (array[parentIndex] > array[index]){
        swap(array, parentIndex, index);
      }else{
        break;
      }
      index = parentIndex;
    }
  }

  private void percolateDown(int[] array, int index, int size){
    while (index <= size/2-1){
      int leftChildIndex = index * 2 +1;
      int rightChildIndex = index * 2 + 2;
      int swapCandidate = leftChildIndex; 

      if (rightChildIndex <= size -1 && array[leftChildIndex] >= array[rightChildIndex]){
        swapCandidate = rightChildIndex;
      }

      if (array[swapCandidate] < array[index]){
        swap(array, swapCandidate, index);
      }else{
        break;
      }

      index = swapCandidate;
    }
  }

  private static void swap(int[] array, int i, int j){
    int tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
  }
}

```

