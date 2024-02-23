---
tags:
    - Array
    - Stack
    - Monotonic Stack
---

# 503 Next Greater Element II

Given a circular integer array `nums` (i.e., the next element of `nums[nums.length - 1]` is `nums[0]`), return *the **next greater number** for every element in* `nums`.

The **next greater number** of a number `x` is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, return `-1` for this number.

 

**Example 1:**

```
Input: nums = [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number. 
The second 1's next greater number needs to search circularly, which is also 2.
```

**Example 2:**

```
Input: nums = [1,2,3,4,3]
Output: [2,3,4,-1,4]
```

 

**Solution:**

```java
class Solution {
  // nums[2, 1, 2, 4, 3]
  //                  i
  //      0  1  2  3  4
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length; // 5
        int[] result = new int[n]; 

        Arrays.fill(result, -1); // Initialize result array with -1
      // result: [4, 4, 4, -1, 4] 

        Deque<Integer> stack = new ArrayDeque<Integer>(); 
        // Stack to keep track of indices
      
      // Stack [  3 

        // Iterate through the array twice to simulate circular array
        for (int i = 0; i < n * 2; i++){
            int cur = nums[i % n]; // 0 % 5 = 0 nums[0] = 2
          // 1 % 5 = 1 nums[1] = 1
          // 2 % 5 = 2 nums[2] = 2 
          // 3 % 5 = 3 nums[3] = 4
          // 4 % 5 = 4 nums[4] = 3 
          // 5 % 5 = 0 nums[0] = 2
          // 6 % 5 = 1 nums[1] = 1
          // 7 % 5 = 2 nums[2] = 2
          // 8 % 5 = 3 nums[3] = 4 
          // 9 % 5 = 4 nums[4] = 4 
        
            // If the current element is greater than the element corresponding to the index at the stack's top,
            // update the result for that index

          	// empty -> pass
            // ! && nums[0] = 2 !< 1 
          // ! && nums[1] = 1 < 2 // result[1] = 2
          // ! && nums[2] = 2 < 4 // result[2] = 4; ! && nums[0] = 2 < 4 // result[0] = 4
          // ! && nums[3] = 4 !< 3
          // ! && nums[4] = 3 !< 2
          // ! && nums[4] = 3 !< 1
          // ! && nums[4] = 3 !< 2
          // ! && nums[4] = 3 < 4 // result[3] = 4; 1 & nums[3] = 4 !< cur 
          // ! && nums[4] = 4 !< 4
            while(!stack.isEmpty() && nums[stack.peekLast()] < cur){
                result[stack.pollLast()] = cur;
            }
						
          // 0 < 5 
          // 1 < 5 
          // 2 < 5
          // 3 < 5 
          // 4 < 5
          // 5 !< 5
            if (i < n){
                stack.offerLast(i); // Push the current index to the stack
            }
        }

        return result;
        
    }
}

// TC : O(n). Only two traversals of the numsnumsnums array are done. Further, at most 2n elements are pushed and popped from the stack.

// SC : O(n)O(n)O(n). A stack of size nnn is used. resresres array of size nnn is used.
```

| 数据结构(逻辑层面) | 内存里存放的方法  | 对应java class        | 对应Java interface |
| ------------------ | ----------------- | --------------------- | ------------------ |
| queue(FIFO)        | array/linked list | ArrayDeque/Linkedist  | Queue              |
| stack(LIFO)        | array/linked list | ArrayDeque/Linkedlist | Deque              |
| deque              | array/linked list | ArrayDeque/Linkedlist | Deque              |

逻辑会有点儿绕, 过个例子有助于理解.
