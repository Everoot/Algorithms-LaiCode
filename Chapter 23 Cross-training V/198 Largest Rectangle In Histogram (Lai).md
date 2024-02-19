# 198 Largest Rectangle In Histogram (Lai)

Given a non-negative integer array representing the heights of a list of adjacent bars. Suppose each bar has a width of 1. Find the largest rectangular area that can be formed in the histogram.

**Assumptions**

- The given array is not null or empty

**Examples**

- { 2, 1, **3, 3, 4** }, the largest rectangle area is 3 * 3 = 9(starting from index 2 and ending at index 4)



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

