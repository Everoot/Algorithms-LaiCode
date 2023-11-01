# 325 Heapify (Lai)

Heapify an unsorted array to min heap.



```java
public class Solution {
  public int[] heapify(int[] array) {
    // Write your solution here
    if (array == null || array.length <= 1){
      return array;
    }

    int lastindex = array.length - 1;
    // leftchild or right child
    // suppose it is left child
    int lastNodeWithChild = (lastindex-1) /2;

    if (lastindex % 2 == 0){
      // rightchild
      lastNodeWithChild = (lastindex -2)/ 2;
    }

    for (int i = lastNodeWithChild; i >= 0; i--){
      percolateDown(array, i, lastNodeWithChild);
    }

    return array;
    
  }

  private static void percolateDown(int[] array, int index, int last){
    while (index <= last){
      int leftChild = 2*index +1;
      int rightChild = 2 * index + 2;

      // pick left or right
      int swapmay = leftChild;
      if (rightChild <= array.length -1 && array[leftChild] > array[rightChild]){
        swapmay = rightChild;
      }

      // swap
      if (array[index] >= array[swapmay]){
        swap(array, index, swapmay);
        index = swapmay;
      }else{
        break;
      }
    }
  }

  private static void swap(int[] array, int i, int j){
    int rem = array[i];
    array[i] = array[j];
    array[j] = rem;
   }
}

// TC: O(n)
// SC: O(1)


/*
                   2
                   / \
                  3  4
                / \.  /\
               10   8. 7  6
              /\
             13 11

index  0   1  2  3 | 4  5  6  7  8
      10, 11, 7, 2,| 8, 4, 6, 13, 3


parent i 
leftchild: i * 2 + 1;
rightchild: i * 2 + 2;


i = (leftchild -1)/2;          j % 2 == 1
i = (rightchild - 2)/2;        j % 2 == 0


nodes with child need to percolate down

last node index = array.length -1 = j
=> last nodes with child through the last node index
=> j % 2 == ?         => lastnodewithchildindex 



*/

```

