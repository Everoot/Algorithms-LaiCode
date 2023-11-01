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

	
