---
tags:
    - Array
    - Stack
    - Monotonic Stack
---

# 84 Largest Rectangle in Histogram

Given an array of integers `heights` representing the histogram's bar height where the width of each bar is `1`, return *the area of the largest rectangle in the histogram*.

 

**Example 1:**

![img](./84 Largest Rectangle in Histogram/histogram.jpg)

```
Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.
```

**Example 2:**

![img](./84 Largest Rectangle in Histogram/histogram-1.jpg)

```
Input: heights = [2,4]
Output: 4
```

 

**Solution:**

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        // Assumptions: array is not null, array.length >= 1,
        // all the values in the array are non-negativ.
        int result = 0;
        // Note that the stack contains the "index",
        // not the "value" of the array
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i <= heights.length; i++){
            // we need a way of popping out all the elements in the stack
            // at last, so that we explicitly add a bar of height 0.
            int cur;
            if (i == heights.length){
                cur = 0;
            }else{
                cur = heights[i];
            }

            while(!stack.isEmpty() && heights[stack.peekFirst()] >= cur){
                int height = heights[stack.pollFirst()];
                // determine the left boundary of the largest rectangle
                // with height array[i].
                int left;
                if (stack.isEmpty()){
                    left = 0;
                }else{
                    left = stack.peekFirst() + 1;
                }
                // determine the right boundary of the largest rectangle
                // with height of the popped element
                result = Math.max(result, height * (i - left));
            }
            stack.offerFirst(i);
        }
        return result;
    }
}
// TC: O(n) 算法的时间复杂度为O(n)，因为每个条形最多被压入栈和弹出栈一次。
// SC: O(n)
```



