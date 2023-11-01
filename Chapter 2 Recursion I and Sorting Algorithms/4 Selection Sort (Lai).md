# Selection Sort

LaiOffer 4

Given an array of integers, sort the elements in the array in ascending order. The selection sort algorithm should be used to solve this problem.

**Examples**

- {1} is sorted to {1}
- {1, 2, 3} is sorted to {1, 2, 3}
- {3, 2, 1} is sorted to {1, 2, 3}
- {4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6}

**Corner Cases**

- What if the given array is null? In this case, we do not need to do anything.
- What if the given array is of length zero? In this case, we do not need to do anything.



```java
/* Given an array of integers, sort the elements in the array in ascending order. The selection sort algorithm should be used to solve this problem.

Examples

{1} is sorted to {1}
{1, 2, 3} is sorted to {1, 2, 3}
{3, 2, 1} is sorted to {1, 2, 3}
{4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6}
Corner Cases

What if the given array is null? In this case, we do not need to do anything.
What if the given array is of length zero? In this case, we do not need to do anything.
*/
import java.util.*;

public class SelectionSort4{
	public static void main(String[] args){
		int[] a1 = new int[]{1};
		int[] a2 = new int[]{1,2,3};
		int[] a3 = new int[]{3,2,1};
		int[] a4 = new int[]{4,2,-3,6,1};
		System.out.println(Arrays.toString(SelectionSort(a1)));
		System.out.println(Arrays.toString(SelectionSort(a2)));
		System.out.println(Arrays.toString(SelectionSort(a3)));
		System.out.println(Arrays.toString(SelectionSort(a4)));
	}


	public static int[] SelectionSort(int[] array){
		// base case
		if (array == null || array.length == 0){
			return array;
		}

		for (int i = 0; i < array.length - 1; i++){ 
		// outer loop: how many iterations	
			int global = i;
			for (int j = i + 1; j < array.length; j++){
				// inner loop: find the global min from the rest elements
				if (array[j] < array[global]){
					// record the index of the smallest element.
					global = j;	
				}
			}
			// swap the global min (a[globalMin) with a[i];
			int rem = array[i];
			array[i] = array[global];
			array[global] = rem;
		}

		return array;
	}

	/* Time complexity: O((n-1)*(n-1))=O(n^2)
	 * Space complexity: O(1)
	 */



}

```



,