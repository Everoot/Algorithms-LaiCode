# 118 Array Deduplication IV (Lai)

Given an unsorted integer array, remove adjacent duplicate elements repeatedly, from left to right. For each group of elements with the same value do not keep any of them.

Do this in-place, using the left side of the original array. Return the array after deduplication.

**Assumptions**

- The given array is not null

**Examples**

{1, 2, 3, 3, 3, 2, 2} → {1, 2, 2, 2} → {1}, return {1}



**Solution:**

```java
public class Solution {
  public int[] dedup(int[] array) {
    // Write your solution here
    // base case 
    if (array == null || array.length <= 1){
      return array;
    }

    Deque<Integer> stack = new ArrayDeque<Integer>();
    for (int i = 0; i < array.length; i++){
      if (stack.isEmpty() || stack.peekLast() != array[i]){
        stack.offerLast(array[i]);
      }else{
        while( i < array.length && array[i] == stack.peekLast()){
          i++;
        }
        i--;
        stack.pollLast();
      }
    }

    int[] result = new int[stack.size()];
    for (int i = stack.size() - 1; i >= 0; i--){
      result[i] = stack.pollLast();
    }
    return result;
  }
}

// TC: O(n)
// SC: O(n)

/*
    {1, 2, 3, 3, 3, 2, 2} -> {1 2, 2 2 } -> {1}
                       i
    stack: [  1  


stack.isEmpty() || stack.peekLast() != array[i]{
  stack.offerLast(array[i]);
}

else{
  // stack.peekLast() == array[i]
  // move i to the not equal
  while(i < array.length && array[i] != stack.peekLast()){
    i++;
  }
  i--;
  stack.pollLast();
}



*/
```







```java
public class Solution {
  public int[] dedup(int[] array) {
    // Write your solution here
    // base case 
    if (array == null || array.length <= 1){
      return array;
    }

    int slow = -1;
    for (int fast = 0; fast < array.length; fast++){
      if (slow == -1 || array[slow] != array[fast]){
        slow++;
        array[slow] = array[fast];
      }else{
        while (fast < array.length && array[fast] == array[slow]){
          fast++;
        }
        fast--;
        slow--;
      }
    }
    
    int[] result = new int[slow + 1];
    for (int i = 0; i < result.length; i++){
      result[i] = array[i];
    } 

    return result;

  }
}

/*
     -1 0  1  2  3  4   5  6
        1, 2, 3, 3 ,3 , 2, 2
        slow
                          fast

    if (slow == -1 || array[slow] != array[fast]){
      slow++;
      array[slow] = array[fast];
    } else{
      while(fast < array.length && array[fast] != array[slow]){
        fast++;
      }
      fast--;
      slow--;
    }

    int[] result = new array

    [[2,3,2,3,3,3,2,1,6]]
        s  
              f
*/
// TC: O(n)
// TC: O(1)
```



