---
tags:
    - Array
    - Sliding Window
---

# 713 Subarray Product Less Than K

Given an array of integers `nums` and an integer `k`, return *the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than* `k`.

 

**Example 1:**

```
Input: nums = [10,5,2,6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are:
[10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
```

**Example 2:**

```
Input: nums = [1,2,3], k = 0
Output: 0
```



**Solution:**

```java
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1){
            return 0;
        }

        int product = 1;
        int result = 0;
        int slow = 0;

        for (int fast = 0; fast < nums.length; fast++){
            product = product * nums[fast];

            while (product >= k){
                product = product / nums[slow];
                slow++;
            }

            result = result + fast - slow + 1;
        }

        return result;
        
    }
}

// TC: O(n) The time complexity is O(n) because each character in the string is visited at most twice.
// SC: O(1)

/*
Coding Block:
while (fast < array.length) {
	all the sliding windows, ending at fast
	// each time we calculate the representative value/data structure for the sliding
	window ending at fast.
	// 1. add the delta for fast;
	// 2. remove delta for slow
	// 3. check the representative value ./data structure
	fast++;
	slow++;
}


*/
```

