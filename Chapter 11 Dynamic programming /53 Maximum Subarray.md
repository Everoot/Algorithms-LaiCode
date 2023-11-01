# 53 Maximum Subarray

Given an integer array `nums`, find the subarray with the largest sum, and return *its sum*.

**Example 1:**

```
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.
```

**Example 2:**

```
Input: nums = [1]
Output: 1
Explanation: The subarray [1] has the largest sum 1.
```

**Example 3:**

```
Input: nums = [5,4,-1,7,8]
Output: 23
Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
```



M[i]: represent Largest Subarray sum ending at i must including i

base case: M[0] = nums[0]

index 0	1	2	3	4	

nums 5	4    -1   7    8

M[i]    5    9     8    15  23



什么时候继承? 

M[i] = M[i-1] + array[i]       M[i-1] > 0

什么时候东山再起?

M[i] = array[i]                      M[i-1] < 0



```java
class Solution {
    public int maxSubArray(int[] nums) {
        // base case 
        int[] m = new int[nums.length];
        m[0] = nums[0];

        // induction rule
        int max = nums[0];
        for (int i = 1; i < nums.length; i++){
            if (m[i-1] >= 0){
                m[i] = m[i-1] + nums[i];
            }else{
                m[i] = nums[i];
            }

            max = Math.max(max, m[i]);
        }

        return max;

    }
}

// TC: O(n)
// SC: O(n)
```



```java
// M[i] only depends on M[i-1]
class Solution {
    public int maxSubArray(int[] nums) {
        // base case 
        int m = nums[0];

        // induction rule
        int max = nums[0];
        for (int i = 1; i < nums.length; i++){
            if (m >= 0){
                m = m + nums[i];
            }else{
                m = nums[i];
            }

            max = Math.max(max, m);
        }

        return max;

    }
}

// TC: O(n)
// SC: O(1)
```

