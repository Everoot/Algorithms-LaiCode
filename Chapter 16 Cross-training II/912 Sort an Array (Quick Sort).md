# 912 Sort an Array (Quick Sort)

Given an array of integers `nums`, sort the array in ascending order and return it.

You must solve the problem **without using any built-in** functions in `O(nlog(n))` time complexity and with the smallest space complexity possible.

 

**Example 1:**

```
Input: nums = [5,2,3,1]
Output: [1,2,3,5]
Explanation: After sorting the array, the positions of some numbers are not changed (for example, 2 and 3), while the positions of other numbers are changed (for example, 1 and 5).
```

**Example 2:**

```
Input: nums = [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]
Explanation: Note that the values of nums are not necessairly unique.
```

 

**Solution:**

```java
class Solution {
    public int[] sortArray(int[] nums) {
        // base case 
        if (nums == null || nums.length <= 1){
            return nums;
        }

        int left = 0;
        int right = nums.length -1;
        quickSort(nums, left, right);
        return nums;
        
    }

    private void quickSort(int[] nums, int left, int right){
        if (left >= right){
            return;
        }

        int p = nums[right];

        int l = left;
        int r = right -1;

        while(l <= r){
            if (nums[l] >= p){
                swap(nums, l, r);
                r--;
            }else{
                l++;
            }
        }

        swap(nums,l,right);

        quickSort(nums, left, l-1);
        quickSort(nums,l+1, right);
    
    }

    private void swap(int[] nums, int i, int j){
        int res = nums[i];
        nums[i] = nums[j];
        nums[j] = res;
    }
}
// TC: O(n^2) O(nlogn )
// SC: O(1)
```

