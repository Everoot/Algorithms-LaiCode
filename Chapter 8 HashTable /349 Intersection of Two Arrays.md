# 349 Intersection of Two Arrays

Given two integer arrays `nums1` and `nums2`, return *an array of their intersection*. Each element in the result must be **unique** and you may return the result in **any order**.

 

**Example 1:**

```
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
```

**Example 2:**

```
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Explanation: [4,9] is also accepted.
```

```java
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<Integer>();
        int l1 = nums1.length;
        int l2 = nums2.length;

        HashSet<Integer> setResult = new HashSet<Integer>();
        for(int num : nums1){
            set.add(num);
        }

        for(int i = 0; i < nums2.length; i++){
            if (set.contains(nums2[i])){
                setResult.add(nums2[i]);
            }else{
                continue;
            }
        }

        int[] result = new int[setResult.size()];
        int i = 0;
        for(int ele : setResult){
            result[i] = ele;
            i++;
        }
        return result;
        
    }
}
```

**Complexity Analysis**

- Time complexity : O(n+m), where `n` and `m` are arrays' lengths. O(n) time is used to convert `nums1`
  into set, O(m) time is used to convert `nums2`, and `contains/in` operations are O(1) in the average case.
- Space complexity : O(m+n) in the worst case when all elements in the arrays are different.