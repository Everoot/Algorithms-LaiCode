# L33 Search in Rotated Sorted Array 

There is an integer array `nums` sorted in ascending order (with **distinct** values).

Prior to being passed to your function, `nums` is **possibly rotated** at an unknown pivot index `k` (`1 <= k < nums.length`) such that the resulting array is `[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]` (**0-indexed**). For example, `[0,1,2,4,5,6,7]` might be rotated at pivot index `3` and become `[4,5,6,7,0,1,2]`.

Given the array `nums` **after** the possible rotation and an integer `target`, return *the index of* `target` *if it is in* `nums`*, or* `-1` *if it is not in* `nums`.

You must write an algorithm with `O(log n)` runtime complexity.

**Example 1:**

```
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
```

**Example 2:**

```
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
```

**Example 3:**

```
Input: nums = [1], target = 0
Output: -1
```

**Constraints:**

- `1 <= nums.length <= 5000`
- `-104 <= nums[i] <= 104`
- All values of `nums` are **unique**.
- `nums` is an ascending array that is possibly rotated.
- `-104 <= target <= 104`



```java
public class search_In_Rotated_Sorted_Array_L33{
	public static void main(String[] args){
		int[] nums = {4, 5, 6, 7, 0, 1, 2};
		int target = 0;
		int target2 = 3;
		int[] nums2= {1};
		int target3 = 0;
		int[] num3 = {4, 5, 6, 7, 0, 1, 2};
		System.out.println(search(nums, target));
		System.out.println(search(nums, target2));
		System.out.println(search(nums2, target3));
	}

	public static int search(int[] nums, int target){
		// base case
		if (nums == null || nums.length == 0){
			return -1;
		}
		int k = 0;
		for (int i = 1; i < nums.length; i++){
			if (nums[i] < nums[i -1]){
				k = i;
			}	
		}

		if (k == 0){
			return binarysearch(nums, target);
		}

		int[] partone = new int[k];
		for (int i = 0; i < k; i++){
			partone[i] = nums[i];
		}

		int[] parttwo = new int[nums.length - k];
		for (int i = 0; i < nums.length - k; i++){
			parttwo[i] = nums[k+i];
		}
		
		if (target >= nums[0]){
			return binarysearch(partone, target);
		}else {
			int result = binarysearch(parttwo, target);
			if (result != -1){
				return (result+k);
			}else{
				return result;
			}
		}
	}	

	public static int binarysearch(int[] nums, int target){
		int left = 0;
		int right = nums.length -1;
		while(left <= right){
			int mid = left + (right - left)/2;
			if (nums[mid] == target){
			       return mid;
			}else if (nums[mid] < target){
		 		left = mid + 1;
			}else {
				right = mid - 1;
			}
		}	
		return -1;

	}
}

```

Time Complexity: O(logN)

Space Complexity: O(1)

