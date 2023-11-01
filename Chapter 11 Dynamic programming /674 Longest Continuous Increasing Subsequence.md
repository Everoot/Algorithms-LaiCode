# 674 Longest Continuous Increasing Subsequence

Given an unsorted array of integers `nums`, return *the length of the longest **continuous increasing subsequence** (i.e. subarray)*. The subsequence must be **strictly** increasing.

A **continuous increasing subsequence** is defined by two indices `l` and `r` (`l < r`) such that it is `[nums[l], nums[l + 1], ..., nums[r - 1], nums[r]]` and for each `l <= i < r`, `nums[i] < nums[i + 1]`.

 

**Example 1:**

```
Input: nums = [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5] with length 3.
Even though [1,3,5,7] is an increasing subsequence, it is not continuous as elements 5 and 7 are separated by element
4.
```

**Example 2:**

```
Input: nums = [2,2,2,2,2]
Output: 1
Explanation: The longest continuous increasing subsequence is [2] with length 1. Note that it must be strictly
increasing.
```



**Solution:**

| index | 0    | 1    | 2    | 3    | 4    | 5    | 6    | 7    |
| ----- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| array | 7    | 2    | 3    | 1    | 5    | 8    | 9    | 6    |
| M[i]  | 1    | 1    | 2    | 1    | 2    | 3    | 4    | 1    |

global max = 4

M[0]: 1

M[1]: if if input[0] < input[1] : M[0] + 1, else : 1

M[2]: if if input[1] < input[2]: M[1] + 1, else: 1

...



**Base case:** only one element M[0] = 1

**Induction rule:** M[i] represents the max length of the ascending subarray within the range of [0, i] **(including the $i-th$ element).**

```
if input[i - 1] < input[i]		
		M[i] = M[i-1] + 1	
otherwise
		1
```

**Return:** global max

Time = O(n): 外面一个O(n) 里面一个O(1)

Space = O(n) -> Optimize to O(1)

linear scan 回头看

回头看1个.



```java
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        // base case 
        if (nums.length <= 1){
            return nums.length;
        }

        int cur = 1;
        int result = 1;
        for (int i = 1; i < nums.length; i++){
            if (nums[i] > nums[i-1]){
                cur = cur + 1;
                result = Math.max(cur, result);
            }else{
                cur = 1;
            }
        }
        return result;
    }
}

/*
      1    3     5    4     7
                      i
cur = 1
r = 3


if (nums[i] > nums[i-1]){
    cur = cur+1;
    r = Math.max(cur, r);
}else{
    cur = 1;
}

return result; 
*/

// TC: O(n)
// SC: O(1)
```

