# **283 Move Zeors** 

LeetCode Move zeros 

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



```java
class Solution {
    public void moveZeroes(int[] nums) {
        // base case
        if (nums == null || nums.length == 0){
            return;
        }

        int j = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] != 0){
                nums[j] = nums[i];
                j++;
            }
        }

        for (int k = j; k < nums.length; k++){
            nums[k] = 0;
        }
    }
}


/*
    [0, 1, 0, 3, 12]
        i 
     j

     [1,3, 12]
      j -----
     [1, 3, 12 , 0 0]
             j

TC: O(n)
SC: O(1)
*
```



```java
class Solution {
    public void moveZeroes(int[] nums) {
       if (nums == null || nums.length <= 1){
           return;
       } 
       //
       // [0, 1, 0, 3, 12]
       //     i  
       //     j   
       //  j  0
       //  1  0
       //           i
       int j = 0;
       for (int i = 0; i < nums.length; i++){
           if (nums[i] != 0){
               swap(nums, i, j);
               j++;
           } 
       }
    }

    public static void swap(int[] nums, int i , int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

// TC: O(n)
// SC: O(1)
```

