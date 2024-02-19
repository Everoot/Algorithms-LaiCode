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
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1); // Initialize result array with -1
        Stack<Integer> stack = new Stack<>(); // Stack to keep track of indices

        // Iterate through the array twice to simulate circular array
        for (int i = 0; i < n * 2; i++) {
            int num = nums[i % n];
            // If the current element is greater than the element corresponding to the index at the stack's top,
            // update the result for that index
            while (!stack.isEmpty() && nums[stack.peek()] < num) {
                result[stack.pop()] = num;
            }
            if (i < n) {
                stack.push(i); // Push the current index to the stack
            }
        }

        return result;
    }
}
```

