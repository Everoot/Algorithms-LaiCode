# Insertion Sort 

Input: unsorted array

output: sorted 

example: {5, 2,4, 6, 1, 3}

```java
import java.util.*;

public class InsertionSort{
	public static void main(String[] args){
		int[] array = new int[]{5,2,4,6,1,3};
		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(Sort(array)));


	}

	public static int[] Sort(int[] array){
		// base case 
		if (array == null || array.length == 0){
			return array;
		}

		for(int i = 1; i < array.length;i++){
			int key = array[i]; // 2 
			int j = i-1; // i = 1, j = 0;
			
			while (j >= 0 && array[j] > key){
				array[j+1] = array[j]; //j+1 = 1; j = 0;  5 5 4 6 1 3  left move forward one index.
			        j = j - 1;   //  j = -1 stop
			}
			array[j+1] = key; // array[i] // j+1 = 0 array[0] = 2 // where the new card insert.
					  // 2 5 4 6 1 3
					  
		
		}
		return array;
	}
	/* Time complexity: O(n);
	 * Space complexity: O(1);
	 */



}

```



