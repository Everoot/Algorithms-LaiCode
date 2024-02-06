# 169 Majority Element

Given an array `nums` of size `n`, return *the majority element*.

The majority element is the element that appears more than `⌊n / 2⌋` times. You may assume that the majority element always exists in the array.

**Example 1:**

```
Input: nums = [3,2,3]
Output: 3
```

**Example 2:**

```
Input: nums = [2,2,1,1,1,2,2]
Output: 2
```



**Solution:**

```java
class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
}

// TC: O(nlogn)
// SC: O(n) or O(1)
```



Boyer-Moore Voting Algorithm

```java
class Solution {
    public int majorityElement(int[] nums) {
        int candidate = nums[0]; // 3
        int count = 1;
        for (int i = 1; i < nums.length; i++){
            if (count == 0){
                count++;
                candidate = nums[i];
            }else if (candidate == nums[i]){
                count++;
            }else{
                count--;
            }
        }
        return candidate;
    }
}
//  Boyer-Moore Voting Algorithm

// TC: O(n)
// SC: O(1)
```



