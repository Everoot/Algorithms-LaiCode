# 912 Sort an Array (Merge Sort)

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
        return mergeSort(nums, left, right);
        
    }

    private int[] mergeSort(int[] nums, int left, int right){
        // base case
        if (left == right){
            return new int[]{nums[left]};
        }

        int mid = left + (right - left)/2; 

        int[] leftResult = mergeSort(nums, left, mid);
        int[] rightResult = mergeSort(nums, mid+1, right);

        return merge(leftResult, rightResult);
    }

    private int[] merge(int[] leftResult, int[] rightResult){
        int[] result = new int[leftResult.length + rightResult.length];
        int leftIndex = 0;
        int rightIndex = 0;
        int resultIndex = 0;

        while (leftIndex < leftResult.length && rightIndex < rightResult.length){
            if (leftResult[leftIndex] < rightResult[rightIndex]){
                result[resultIndex] = leftResult[leftIndex];
                resultIndex++;
                leftIndex++;
            }else{
                result[resultIndex] = rightResult[rightIndex];
                resultIndex++;
                rightIndex++;
            }
        }

        while (leftIndex < leftResult.length){
            result[resultIndex] = leftResult[leftIndex];
            resultIndex++;
            leftIndex++;
        }

        while (rightIndex < rightResult.length){
            result[resultIndex] = rightResult[rightIndex];
            resultIndex++;
            rightIndex++;
        }

        return result;


    }
}
// TC: O(n)+O(nlogn) = O(nlogn)
// SC: stack + heap = O(logn + n) = O(n)
/* Time Complexity:
	 * 			  []		O(1)  heap space = O(n) 
	 *			 /  \                  	
	 *		       []   []          O(2)  heap space = O(n/2) 
	 *		      /  \  / \
	 *		    []  [] [] []        O(4)  heap space = O(n/4)
	 *		                        ...
	 *		                        O(n/2)
	 *  How many level? ---> n = 2^x => x = logn levels
	 *  And each level will be O(n) => O(nlogn)
	 *  1 + 2 + 4 + ...n/2 = O(n)
	 *  -------------------------------------------
	 *  + 
	 *
	 *  merge         []   []   []   []    O(n)
	 *  		    \  /     \  /
	 *  		     []       []       O(n) 
	 *  		        \   / 
	 *  		         []            O(n) 
	 *
	 *
	 *  level: logn
	 *
	 *  O = (n+n+n+ ... n) = nlogn
	 *
	 *  O(n) + O(nlogn) = O(nlogn)
	 *
	 *  S(n) : stack + heap
	 *  	   logn  +  (n + n/2 + n/4 + ... + 1) = O(n)
	 *  S(n) = O(n)
*/
```

