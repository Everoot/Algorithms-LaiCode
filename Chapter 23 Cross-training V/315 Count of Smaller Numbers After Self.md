---
tags:
    - Array
    - Binary Search
    - Divide and Conquer
    - Binary Indexed Tree
    - Segment Tree
    - Merge Sort
    - Ordered Set

---

#  315 Count of Smaller Numbers After Self

Given an integer array `nums`, return *an integer array* `counts` *where* `counts[i]` *is the number of smaller elements to the right of* `nums[i]`.

**Example 1:**

```
Input: nums = [5,2,6,1]
Output: [2,1,1,0]
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
```

**Example 2:**

```
Input: nums = [-1]
Output: [0]
```

**Example 3:**

```
Input: nums = [-1,-1]
Output: [0,0]
```

 

**Solution:**

```java
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        // nums:  [5, 2, 6, 1]
        int n = nums.length;
        int[] counts = new int[n]; // 存储最终的计数结果 

        int[] indices = new int[n]; // 存储每个元素的索引 


        // 这个循环初始化 indices 数组, 使其元素的值从0到n-1
        for (int i = 0; i < n; i++){
            indices[i] = i;            
        }

        // indices: [0, 1, 2, 3]
        // 开始对数组进行排序, 并在此过程中计算每个右侧小于它的元素数量
        // 这个方法采用分而治之的策略, 通过递归分解数组, 然后在合并过程中进行计数
        mergeSort(nums, indices, counts, 0, n - 1);   
        List<Integer> result = new ArrayList<Integer>();
        for (int count : counts){
            result.add(count);
        }

        return result;
        
    }

    //  nums: [5,2,6,1], indices: [0, 1, 2, 3], counts: [0, 0, 0, 0], 0, 3
    private void mergeSort(int[] nums, int[] indices, int[] counts, int left, int right){
        if (left >= right){
            return;
        }

        int mid = left + (right - left)/2; // 0 + (3 -0)/2 = 1
        mergeSort(nums, indices, counts, left, mid); // 
        // nums: [5,2,6,1], indices: [0, 1, 2, 3], counts: [0, 0, 0, 0], 0, 1
        /*     mid = 0 + (1-0)/2 = 0 
                nums: [5,2,6,1], indices: [0, 1, 2, 3], counts: [0, 0, 0, 0], 0, 0

                nums: [5,2,6,1], indices: [0, 1, 2, 3], counts: [0, 0, 0, 0], 1,1
         */
        mergeSort(nums, indices, counts, mid + 1, right);
        //  nums: [5,2,6,1], indices: [0, 1, 2, 3], counts: [0, 0, 0, 0], 2, 3
        /* 
              mid = 2 + (3-2)/2 = 2 + 0=2
            nums: [5,2,6,1], indices: [0, 1, 2, 3], counts: [0, 0, 0, 0], 2, 2
            nums: [5,2,6,1], indices: [0, 1, 2, 3], counts: [0, 0, 0, 0], 3, 3
        */
        merge(nums, indices, counts, left, right);
        //  nums: [5,2,6,1], indices: [0, 1, 2, 3], counts: [0, 0, 0, 0], 0, 1
    }

    //    nums: [5,2,6,1], indices: [0, 1, 2, 3], counts: [0, 0, 0, 0], 2, 3
    private void merge(int[] nums, int[] indices, int[] counts, int left, int right){
        int mid = left + (right - left)/2; // 计算中间点，用于分割数组为左右两部分
        // 2+(3-2)/2 = 2 + 1 /2 = 2
        int leftIndex = left; // 2 
        int rightIndex = mid + 1; // 3
        // 初始化两个指针，leftIndex指向左半部分的起始位置，rightIndex指向右半部分的起始位置
        int rightCount = 0;
        // 初始化rightCount变量，用于记录当前右侧已经合并到结果数组中的元素数量，这对于更新计数非常关键
        int[] sorted = new int[right - left + 1]; // 3-2+1 = 2        sorted[3, 2]
        int sortedIndex = 0;
        // 创建一个临时数组sorted用于存放当前范围内排序后的元素，
        // sortedIndex用于跟踪当前填充到sorted数组中的位置。

        while(leftIndex <= mid && rightIndex <= right){
            // 2 <= 2  && 3 <= 3
            if (nums[indices[rightIndex]] < nums[indices[leftIndex]]){
                // nums[indices[3]] = nums[3] = 1 < nums[indics[2]] = nums[2] = 6
                rightCount++; // 1
                sorted[sortedIndex] = indices[rightIndex]; // sorted[0] = indices[3]=3
                sortedIndex++;// 1
                rightIndex++;// 3+1
            } else {
                counts[indices[leftIndex]] = counts[indices[leftIndex]] + rightCount;
                sorted[sortedIndex] = indices[leftIndex];
                sortedIndex++;
                leftIndex++;
            }
        }

        while(leftIndex <= mid){
            // 2 <= 2
            counts[indices[leftIndex]] = counts[indices[leftIndex]] + rightCount;
            // counts[indices[2]]= counts[2] = 0 + 1;
            sorted[sortedIndex] = indices[leftIndex];
            // sorted[1] = indices[2] = 2
            sortedIndex++;// 3
            leftIndex++; // 3
        }


        while(rightIndex <= right){
            sorted[sortedIndex] = indices[rightIndex];
            sortedIndex++;
            rightIndex++;
        }

        for (int i = left; i <= right; i++){
            indices[i] = sorted[i - left];
        }
        // 2 <=3
        // indices[2] = sorted[2-2] = 3 
        // 2
    }
}

// Time Complexity: O(Nlog⁡(N)). 

// Space Complexity: O(N)
```

Hard!!!!
