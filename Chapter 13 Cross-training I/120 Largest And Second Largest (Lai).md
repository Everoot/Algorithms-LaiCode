# 120 Largest And Second Largest (Lai)

Use the least number of comparisons to get the largest and 2nd largest number in the given integer array. Return the largest number and 2nd largest number.

**Assumptions**

- The given array is not null and has length of at least 2

**Examples**

- {2, 1, 5, 4, 3}, the largest number is 5 and 2nd largest number is 4.

**Solution:**

```java
public class Solution {
  static class Element{
    int value;
    List<Integer> comparedValues;

    Element(int value){
      this.value = value;
      this.comparedValues = new ArrayList<>();
    }
  }
  public int[] largestAndSecond(int[] array) {
    // Write your solution 
    int[] result = new int[2];
    result[0] = array[0]; // max
    result[1] = array[1]; // secondmax

    Element[] helper = convert(array);
    int len = array.length;
    // pick up winner
    while(len > 1){
      compareAndSwap(helper, len);
      len = (len + 1) /2;
    }

    result[0] = helper[0].value;
    result[1] = largest(helper[0].comparedValues);
    return result;
  }

  private static int largest(List<Integer> list){
    int max = list.get(0);
    for (int i = 0; i < list.size(); i++){
      max = Math.max(max, list.get(i));
    }
    return max;
  }

  private static void compareAndSwap(Element[] helper, int len){
    for (int i = 0; i < len/2; i++){
      if (helper[i].value < helper[len -1 -i].value){
        swap(helper, i, len - 1 - i);
      }
      helper[i].comparedValues.add(helper[len - 1 -i].value);
    }
  }

  private static void swap(Element[] helper, int i, int j){
    Element tmp = helper[i];
    helper[i] = helper[j];
    helper[j] = tmp;
  }

  private static Element[] convert(int[] array){
    Element[] helper = new Element[array.length];
    for (int i = 0; i < array.length; i++){
      helper[i] = new Element(array[i]);
    }
    return helper;
  }
}

/*

        {2, 1, 5, 4, 3}     -> return int[] reuslt = {5, 4}
         
         2          3    -> swap {3, 1, 5, 4, 2}
            1     4      -> swap {3, 4, 5, 1, 2}
               5 
         
         [3, 4, 5]

         3      5       -> swap {5, 4, 3}
             4          -> swap {5, 4, 3}

             let the winner at the begining 


             winner store history  -> pick the history value max one that's the biggest

         
TC: O(n)   = n/2 + n/4 .... 
SC: O(n)
*/
```



```java
public class Solution {
  // The Element class will be used to store the original value
  // in the array and all the values compared to it.
  static class Element{
    int value;
    List<Integer> compredValues;

    Element(int value){
      this.value = value;
      this.compredValues = new ArrayList<>();
    }
  }

  public int[] largestAndSecond(int[] array) {
    // Write your solution here 
    // Assumptions: array is not null, array.length >= 2.
    Element[] helper = convert(array);
    // largerLength is the left partition's length containing the 
    // larger values after each round of comparison
    // For each round, the comparison is still doing for each of 
    // the indices pairs (i, largerLength - 1 - i).
    // So that the larger elements are always on the left sid,
    // and the largerLength will be cut in half each round.
    // largerLength is obviously initiated by the array's length.
    int largerLength = array.length;
    // We will terminate when there is only one element left on
    // the larger partition, and it has to be the largest value.
    // the seconde largest value is the largest value in its
    // compared values.
    while (largerLength > 1){
      compareAndSwap(helper, largerLength);
      largerLength = (largerLength + 1)/2;
    }
    return new int[]{helper[0].value, largest(helper[0].compredValues)};
  }

  private Element[] convert(int[] array){
    Element[] helper = new Element[array.length];
    for (int i = 0; i < array.length; i++){
      helper[i] = new Element(array[i]);
    }
    return helper;
  }

  // Compare each of the indices pairs (i, largerLength - 1 -i),
  // swap the larger values on the left side if necessary,
  // and put the smaller value into the larger value's compare values list.
  private void compareAndSwap(Element[] helper, int largerLength){
    for (int i = 0; i < largerLength / 2; i++){
      if (helper[i].value < helper[largerLength - 1 - i].value){
        swap(helper, i, largerLength - 1 - i);
      }
      helper[i].compredValues.add(helper[largerLength - 1 - i].value);
    }
  }

  private void swap(Element[] helper, int left, int right){
    Element tmp = helper[left];
    helper[left] = helper[right];
    helper[right] = tmp;
  }

  private int largest(List<Integer> list){
    // This is guaranteed to be O(1)
    int max = list.get(0);
    // Using Iterator is guaranteed to be O(1) traversing each of the 
    // element int the list
    for (int num : list){
      max = Math.max(max, num);
    }
    return max;
  }
}

// TC: O(n)
// SC: O(n)
```

