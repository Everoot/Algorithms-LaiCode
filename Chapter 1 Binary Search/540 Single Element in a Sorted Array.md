# ==540 Single Element in a Sorted Array==

You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.

Return *the single element that appears only once*.

Your solution must run in `O(log n)` time and `O(1)` space.

 

**Example 1:**

```
Input: nums = [1,1,2,3,3,4,4,8,8]
Output: 2
```

**Example 2:**

```
Input: nums = [3,3,7,7,10,11,11]
Output: 10
```



| index | 0    | 1    | 2     | 3    | 4     | 5    | 6    | 7    | 8     |
| ----- | ---- | ---- | ----- | ---- | ----- | ---- | ---- | ---- | ----- |
| nums  | 1    | 1    | 2     | 3    | 3     | 4    | 4    | 8    | 8     |
|       | left |      |       |      | mid   |      |      |      | right |
|       | left |      | mid   |      | right |      |      |      |       |
|       | left | mid  | right |      |       |      |      |      |       |
|       |      |      |       | left |       |      |      |      |       |



It makes sense to try and convert the linear search into a binary search. In order to use binary search, we need to be able to look at the middle item and then determine whether the solution is the middle item, or to the left, or to the right. The key observation to make is that the starting array must always have an odd number of elements appearing once, and all the other elements appearing twice.

| index | 0    | 1    | 2    | 3    | 4    | 5    | 6    | 7    | 8    | 9    | 10    |
| ----- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ----- |
| nums  | 1    | 1    | 4    | 4    | 5    | 5    | 6    | 6    | 8    | 9    | 9     |
|       | left |      |      |      |      | mid  |      |      |      |      | right |

Here is what happens when we remove a pair from the center. We are left with a left subarray and a right subarray.

| index | 0    | 1    | 2    | 3    | 4     | 5       | 6    | 7    | 8    | 9    | 10    |
| ----- | ---- | ---- | ---- | ---- | ----- | ------- | ---- | ---- | ---- | ---- | ----- |
| nums  | 1    | 1    | 4    | 4    | ~~5~~ | ~~5~~   | 6    | 6    | 8    | 9    | 9     |
|       | left |      |      |      |       | ~~mid~~ |      |      |      |      | right |

Like the original array, the subarray containing the single element must be odd-lengthed. The subarray not containing it must be even-lengthed. So by taking a pair out of the middle and then calculating which side is now odd-lengthed, we have the information needed for binary search.

Algorithm

We start by setting `left` and `right` to be the lowest and highest index (inclusive) of the array, and then iteratively halve the array until we find the single element or until there is only one element left. We know that if there is only one element in the search space, it must be the single element, so should terminate the search.

On each loop iteration, we find `mid`, and determine the odd/evenness of the sides and save it in a variable called `halvesAreEven`. By then looking at which half the middle element's partner is in (either last element in the left subarray or first element in the right subarray), we can decide which side is now (or remained) odd-lengthed and set `left` and `right` to cover the part of the array we now know the single element must be in.

**<u>The trickiest part is ensuring we update `left` and `right` correctly based on the values of `mid` and `halvesAreEven`. These diagrams should help you understand the cases. When solving problems like this, it's often good to draw a diagram and think really carefully about it to avoid off-by-one errors. Avoid using a guess and check approach.</u>**



#### Case 1: Mid's partner is to the right, and the halves were originally even.

The right side becomes odd-lengthed because we removed `mid`'s partner from it. We need to set `left` to `mid + 2` so that the remaining array is the part above `mid`'s partner.

| 0    | 1    | 2    | 3    | 4     | 5     | 6        | 7    | 8     |
| ---- | ---- | ---- | ---- | ----- | ----- | -------- | ---- | ----- |
| 1    | 1    | 4    | 4    | ~~5~~ | ~~5~~ | 6        | 8    | 8     |
| left |      |      |      | mid   |       |          |      | right |
|      |      |      |      |       |       | new left |      |       |

mid % 2 == 0

nums[mid] =nums[mid+1]

left =  mid +2

#### Case 2: Mid's partner is to the right, and the halves were originally odd

The left side remains odd-lengthed. We need to set `right` to `mid-1` so that the remaining array is the part below `mid`.

| 0    | 1    | 2    | 3    | 4     | 5     | 6     | 7    | 8     | 9    | 10   |      |
| ---- | ---- | ---- | ---- | ----- | ----- | ----- | ---- | ----- | ---- | ---- | ---- |
| 1    | 1    | 4    | 5    | 5     | ~~6~~ | ~~6~~ | 8    | 8     | 9    | 9    |      |
| left |      |      |      |       | mid   |       |      | right |      |      |      |
|      |      |      |      | right |       |       |      |       |      |      |      |

mid % 2 == 1

nums[mid] = nums[mid+1]

right = mid-1

#### Case 3: Mid's partner is to the left, and the halves were originally even

The left side becomes odd-lengthed because we removed `mid`'s parter from it. We need to set `right` to `mid-2` so that the remaining array is the part below `mid`.

| 0    | 1    | 2         | 3     | 4     | 5    | 6    | 7    | 8     |
| ---- | ---- | --------- | ----- | ----- | ---- | ---- | ---- | ----- |
| 1    | 1    | 4         | ~~5~~ | ~~5~~ | 6    | 6    | 8    | 8     |
| left |      |           |       | mid   |      |      |      | right |
|      |      | new right |       |       |      |      |      |       |

mid % 2 == 0

nums[mid] != nums[mid+1]

nums[mid] == nums[mid-1]

right =mid-2



### Case 4: Mid's partner is to the left, and the halves were originally odd.

The right side remains odd-lengthed. We need to set `left` to `mid + 1` so that the remaining array is the part above `mid`.

| 0    | 1    | 2    | 3    | 4    | 5    | 6        | 7    | 8    | 9    | 10    |
| ---- | ---- | ---- | ---- | ---- | ---- | -------- | ---- | ---- | ---- | ----- |
| 1    | 1    | 4    | 4    | 5    | 5    | 6        | 6    | 8    | 9    | 9     |
| left |      |      |      |      | mid  |          |      |      |      | right |
|      |      |      |      |      |      | new left |      |      |      |       |

mid % 2 == 1 

nums[mid] != nums[mid +1]

nums[mid] == nums[mid-1]

left = mid +1



Another interesting observation you might have made is that this algorithm will still work even if the array isn't fully sorted. As long as pairs are always grouped together in the array (for example, [10, 10, 4, 4, 7, 11, 11, 12, 12, 2, 2]), it doesn't matter what order they're in. Binary search worked for this problem because we knew the subarray with the single number is always odd-lengthed, not because the array was fully sorted numerically. We commonly call this an invariant, something that is always true (i.e "The array containing the single element is always odd-lengthed"). Be on the lookout for invariants like this when solving array problems, as binary search is very flexible!

#### Complexity Analysis

* Time complexity: $O(logn)$. On each iteration of the loop, we're halving the number of items we still need to search.
* Space complexity: $O(1)$. We are only using constant space to keep track of where we are in the search.



```java
class Solution {
    public int singleNonDuplicate(int[] nums) {
        //  0 1 2 3 4 5 6 7 8 
        //  1,1,2,3,3,4,4,8,8
        //  l               r   
        //          m               m % 2 ==0
        //      r                   leftpart -> nums[mid] == nums[mid-1]  right = mid-2
        //  l   r                   rightpart -> nums[mid] != nums[mid-1]  left = mid
        //    m                     
        //      r               ----> nums[right]    
        //  0 1 2 3 4 5 6 7 8 
        //  1,1,3,3,4,5,5,8,8
        //  l       m       r          m % 2 ==0
        //          l=m                rightpart -> nums[mid] != nums[mid-1]  left = mid
      //                               leftpart -> nums[mid] == nums[mid-1]  mid = mid-2
      	//          l   m   r
        //          l
        //         r=m-2       ----> nums[right]   
        //.         
        //  0 1 2 3  4  5 6   
        // [3,3,7,7,10,11,11]          m % 2 = 1       
        //  l              r           rightpart -> nums[mid] == nums[mid-1]   left = mid+1
        //        m                    leftpart -> nums[mid] != nums[mid-1]  right =mid-1
        //           l     r
        //              m
         //          l
      //             r=m-1             --->nums[right]
        //  0 1 2 3  4  5 6 
        // [3,3,2,7,7,11,11]           m % 2 = 1
        //        m                    leftpart -> nums[mid] != nums[mid-1]   right = mid -1
        //  l.            r            rightpart -> nums[mid] == nums[mid-1]  left = mid +1 
        //  l   r
        //    m
        //  l   r 
        //      l=m+1                ---> nums[right]
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            int mid = left + (right - left)/2;
            if (mid % 2 == 1){

                // right part
                if (nums[mid] == nums[mid -1]){
                    left = mid+1;
                }else{ // left part

                    right = mid -1;
                }

            }else { // mid % 2 == 0
                // left part
                if (nums[mid] == nums[mid-1]){
                    right = mid -2;
                }else{ // nums[mid] != nums[mid-1]
                    left = mid;
                }
                // right part

            }
        }    

        return nums[right];
        
    }
}
```





​																			mid % 2 

​																			/		\	

​																		 0             1

​																		/	\		/	\	

​																	left right   left right

