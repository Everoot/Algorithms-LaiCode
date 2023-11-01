# L35 Search Insert Position

Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You must write an algorithm with `O(log n)` runtime complexity.

 

**Example 1:**

```
Input: nums = [1,3,5,6], target = 5
Output: 2
```

**Example 2:**

```
Input: nums = [1,3,5,6], target = 2
Output: 1
```

**Example 3:**

```
Input: nums = [1,3,5,6], target = 7
Output: 4
```

 

**Constraints:**

- `1 <= nums.length <= 104`
- `-104 <= nums[i] <= 104`
- `nums` contains **distinct** values sorted in **ascending** order.
- `-104 <= target <= 104`



```java
class Solution {
    public int searchInsert(int[] nums, int target) {
       int left = 0;
       int right = nums.length -1;
       if (nums.length == 1){
           if (nums[0] >= target){
               return 0;
           }else{
               return 1;
           }
       }

       while (left < right-1){ //  [1 3]
           int mid = left + (right - left)/2;
           if (nums[mid] == target){
               return mid;
           } else if (nums[mid] < target){
               left = mid;
           } else{
               right = mid;
           }
       }

    
       if (nums[left] >= target){
           if (left == 0){
               return 0;
           }else {
               return (left - 1);
           }
       } else if (nums[left] < target && target <= nums[right]){
           return (left + 1);
       } else {
           return (right + 1);
       }


    }
}
```



```java
class Solution {
  public int searchInsert(int[] nums, int target) {
    int pivot, left = 0, right = nums.length - 1;
    while (left <= right) {
      pivot = left + (right - left) / 2;
      if (nums[pivot] == target) return pivot;
      if (target < nums[pivot]) right = pivot - 1;
      else left = pivot + 1;
    }
    return left;
  }
}
```



Time complexity : $O(logN)$

Space complexity: $O(1)$



