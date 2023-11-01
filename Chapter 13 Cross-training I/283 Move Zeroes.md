# 283 Move Zeroes

Given an integer array `nums`, move all `0`'s to the end of it while maintaining the relative order of the non-zero elements.

**Note** that you must do this in-place without making a copy of the array.

 

**Example 1:**

```
Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]
```

**Example 2:**

```
Input: nums = [0]
Output: [0]
```



**Solution:**

```java
class Solution {
    public void moveZeroes(int[] nums){
        // base case
        if (nums == null || nums.length <= 1){
            return;
        }

        int slow = 0;
        int fast = 0;
        
        while(fast < nums.length){
            if (nums[fast] != 0){
                nums[slow] = nums[fast];
                slow++;
                fast++;
            }else{
                // nums[slow] == 0
                fast++;
            }
        }


        while(slow < nums.length){
            nums[slow] = 0;
            slow++;
        }
    }


}
// TC: O(n)
// SC: O(1)
/*
[1,3,12,3,12]
       s
             f
*/
```

