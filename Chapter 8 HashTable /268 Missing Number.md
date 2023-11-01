# 268 Missing Number

Given an array `nums` containing `n` distinct numbers in the range `[0, n]`, return *the only number in the range that is missing from the array.*

 

**Example 1:**

```
Input: nums = [3,0,1]
Output: 2
Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
```

**Example 2:**

```
Input: nums = [0,1]
Output: 2
Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.
```

**Example 3:**

```
Input: nums = [9,6,4,2,3,5,7,0,1]
Output: 8
Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.
```



```java
class Solution {
    public int missingNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int num: nums){
            set.add(num);
        }

        for (int i = 0; i <= nums.length; i++){
            if (!set.contains(i)){
                return i;
            }
        }

        return -1;

    }
}
```

- Time complexity : O(n

  Because the set allows for O(1) containment queries, the main loop
  runs in O(n) time. Creating `num_set` costs O(n) time, as each set insertion
  runs in amortized O(1) time, so the overall runtime is O(n+n)=O(n)

- Space complexity : O(n)

  `nums` contains n−1 distinct elements, so it costs O(n) space to
  store a set containing all of them.

