# 363 Heap Offer Operation (Lai)

Given a binary min heap in array format. The last cell of the array is not used.

Now offer a new element into the heap.

**Assumptions:**

- The given array is not null and has length >= 1

**Examples:**

array = {2, 3, 4, 0}, offer(1) --> {1, 2, 4, 3}



```java
public class Solution {
  public int[] offerHeap(int[] array, int ele) {
    // Write your solution here\
    int size = array.length;
    int index = size -1;
    array[index] = ele;
    percolateUp(array, index);
    return array;
  }

  private static void percolateUp(int[] array, int index){
    while (index > 0){
      int parentIndex = (index-1)/2;
      if (array[index] < array[parentIndex]){
        swap(array, index, parentIndex);
      }else{
        break;
      }

      index = parentIndex;
    }
  }

  private static void swap(int[] array, int i , int j){
    int tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
  }
}




/*

            2
           / \
           3 4
           / 
          1
              
     ->.    1
           / \
          2   4
         /
        3  

percolateUp
child compare with parent 
swap 
         0 1 2
         2 3 4

 2    p: (2 -1)/2 = 1/2 = 0
                

*/
```

