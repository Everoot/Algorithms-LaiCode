---
tags:
    - Array
    - Hash Table
    - Prefix Sum
---

# 560 Subarray Sum Equals K

Given an array of integers `nums` and an integer `k`, return *the total number of subarrays whose sum equals to* `k`. A subarray is a contiguous **non-empty** sequence of elements within an array.

 

**Example 1:**

```
Input: nums = [1,1,1], k = 2
Output: 2
```

**Example 2:**

```
Input: nums = [1,2,3], k = 3
Output: 2
```

 

**Solution:**

```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        int result = 0;
        int sum = 0;
        Map<Integer, Integer> preSum = new HashMap<Integer, Integer>();
        preSum.put(0, 1); 
        // Initialize with 0:1 for the case where the subarray starts from index 0

        for (int i = 0; i < nums.length; i++){
            
            sum = sum + nums[i];

            if (preSum.containsKey(sum - k)){
                result = result + preSum.get(sum - k);

            }

            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }
        return result;
        
    }
}

// TC: O(n)
// SC: O(n)

```



```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        if (nums == null){
            return Integer.MIN_VALUE;
        }

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int count = 0;
        map.put(0,1);
        int sum = 0;

        for (int i = 0; i < nums.length; i++){
            sum = nums[i] + sum;
            if (map.containsKey(sum - k)){
                count = count + map.get(sum-k); // update global count;
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
        
    }
}


// TC: O(n)
// SC: O(n)


/*
            0 1 2
            1 2 3
    sum     1 3 6              3,1         sum - 3   in map
    Map<Integer, Integer>            global count
        sum       count          
         0,    1

         curSum - k = 1 - 3 =-2     add 1 , 1
         3 - 3 = 0                      3,   get(sum) + 1 

         6 - 3 = 3               6,  get(3 ) + 1 =  2 

         count  =2 



*/
```

