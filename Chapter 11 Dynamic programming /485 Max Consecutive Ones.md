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



**Solution**:

```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        // base case 
        if (nums == null || nums.length == 0){
            return 0;
        }

        int prem = 0;
        if (nums[0] == 1){
            prem = 1;
        }

        int max = prem;

        // induction rule
        for (int i = 1; i < nums.length; i++){
            if (nums[i] == 1){
                prem = prem + 1;
            }else{
                prem = 0;
            }

            max = Math.max(prem, max);
        }

        return max;
    }
}
// TC: O(n)
// SC: O(1)
/*
index i. 0 1 2 3 4 5
         1,1,0,1,1,1

prem: reprent curren longest 1

// base case
if (nums == null || nums.length == 0){
    return 0;
}

int prem = 0;
if (nums[0] == 1){
    prem = 1;
}

int max = prem

// induction rule
i = 1 
prem = prem + 1;

Math.max(prem, max);

// return max
*/
```



