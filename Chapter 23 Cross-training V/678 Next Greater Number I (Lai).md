# 678 Next Greater Number I (Lai)

Given two integer arrays **all** and **partial** without duplicate numbers**,** array **partial** is subset of array **all.** For each number in partial, find the first number on its right in all that greater than it. If no such number existed, then assign the result to -1.

Example 1: 

Input: all = [3,4,1,2]  partial = [4,1,2]

Output: [-1, 2, -1]

Example 2:

Input: all = [1, 2, 3, 4] partial = [4,1,2]

Output: [-1, 2, 3]



**Solution:**

```java
public class Solution {
  public int[] nextGreaterElement(int[] partial, int[] all) {
    // Write your solution here
    int[] result = new int[partial.length];

    for (int i = 0; i < result.length; i++){
      result[i] = -1;
    }

    for (int i = 0; i < partial.length; i++){
      boolean found = false;
      for (int j = 0; j < all.length; j++){
        if (found == true && all[j] > partial[i]){
          result[i] = all[j];
          break;
        } 

        if (all[j] == partial[i]){
          found = true;
        }
      }
    }

    return result;
  }
}

// TC: O(n*m)
// SC: O(n)
```



```java
public class Solution {
  public int[] nextGreaterElement(int[] partial, int[] all) {
    // Write your solution here
    /*
    Map<Integer, Integer> resultMap;
    key: each distinct element in all array
    value: the first greater element on the right side of the key (this
    element) in all array
    Deque<Integer> monoStack:
    Monotonic Non-Increasing Stack
    int[] result:
      result array to be return, should be same length as partial,
      result[i] represents the first number on its right in all array that greater than it
    */

    Map<Integer, Integer> resultMap = new HashMap<>();
    Deque<Integer> monoStack = new ArrayDeque<>();
    int[] result = new int[partial.length];

    // update all array using monoStack
    for (int i = 0; i < all.length; i++){
      // Montonic Non-Increasing Stack: the condition to pop element should be strictly larger
      // e.g element on the top of stack: 3 and all[i] is 4, then 3,4 is increasing whic is violet the 
      // rule of monotonic non-increasing
      while(!monoStack.isEmpty() && monoStack.peekLast() < all[i]){
        resultMap.put(monoStack.pollLast(), all[i]);
      }
      monoStack.offerLast(all[i]);
    }
    // fill in result;
    for (int i = 0; i < partial.length; i++){
      /* 
      Case 1: for those element who still stay in monoStack(doesn't exist in resultMap),
      that means there is no greater element on the right side of them in all array. Their 
      result should be -1; 
      Case 2: for those elements who exist in resultMap, their value is the result;
      */
      result[i] = resultMap.getOrDefault(partial[i], -1);
    }
    return result;
  }
}
// TC: O(n)
// SC: O(n)
```

