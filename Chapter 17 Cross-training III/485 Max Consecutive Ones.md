# 485 Max Consecutive Ones

Given a binary array `nums`, return *the maximum number of consecutive* `1`*'s in the array*.

 

**Example 1:**

```
Input: nums = [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
```

**Example 2:**

```
Input: nums = [1,0,1,1,0,1]
Output: 2
```

 

**Solution:**

```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == 0){
                continue;
            } else{
                // nums[i] == 1
                if (i == 0){
                    nums[i] = 1;
                    result = 1;
                }else{
                    nums[i] = nums[i-1] + 1;
                    result = Math.max(result, nums[i]);
                }
            }
        }
        return result;
        
    }
}

// TC: O(n)
// SC: O(1)
```

