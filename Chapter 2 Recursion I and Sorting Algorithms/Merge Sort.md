# 9 Merge Sort

LeetCode 912 Sort an Array

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



LaiCode 9 Merge Sort

Given an array of integers, sort the elements in the array in ascending order. The merge sort algorithm should be used to solve this problem.

**Examples**

- {1} is sorted to {1}
- {1, 2, 3} is sorted to {1, 2, 3}
- {3, 2, 1} is sorted to {1, 2, 3}
- {4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6}

**Corner Cases**

- What if the given array is null? In this case, we do not need to do anything.
- What if the given array is of length zero? In this case, we do not need to do anything.





````java
/*LeetCode 912 Sort an Array

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



LaiCode 9 Merge Sort

Given an array of integers, sort the elements in the array in ascending order. The merge sort algorithm should be used to solve this problem.

**Examples**

- {1} is sorted to {1}
- {1, 2, 3} is sorted to {1, 2, 3}
- {3, 2, 1} is sorted to {1, 2, 3}
- {4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6}

**Corner Cases**

- What if the given array is null? In this case, we do not need to do anything.
- What if the given array is of length zero? In this case, we do not need to do anything.

*/

import java.util.*;

public class MergeSort9{

	public static void main(String[] args){
		int[] array = new int[]{5,2,3,1};
		
		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(sort(array)));
	}

	public static int[] sort(int[] array){
		// base case
		if (array == null || array.length == 0){
			return array;
		}

		return mergeSort(array, 0, array.length-1);
	}

	public static int[] mergeSort(int[] array, int left, int right){
		// base case 
		if (left == right){
			int[] result = new int[]{array[left]};
			return result;
		}

		// subproblem
		int mid = left + (right-left)/2;
		int[] leftResult = mergeSort(array, left, mid);
		int[] rightResult = mergeSort(array, mid + 1, right);

		// recursive rule
		return merge(leftResult, rightResult);
	}

	public static int[] merge(int[] leftResult, int[] rightResult){
		int[] result = new int[leftResult.length + rightResult.length];
		int i = 0;
		int j = 0;
		int k = 0;
		while(i < leftResult.length && j <rightResult.length){
			if (leftResult[i] < rightResult[j]){
				result[k] = leftResult[i];
				i++;
				k++;
			}else{
				result[k] = rightResult[j];
				j++;
				k++;
			}
		}
		
		while (i < leftResult.length){
			result[k] = leftResult[i];
			i++;
			k++;
		}
		
		while (j < rightResult.length){
			result[k] = rightResult[j];
			j++;
			k++;
		}
		
		return result;
	
	}

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
	 *
	 *
	 *
	 *  
	 *		                                
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 */


}

````



#### Merge sort

递归概念复习: Recursion的时候

* Step 1: Function 能做什么
  * signature: 说明input和output是什么
  * function: 能做什么事
* Step 2: Base Case
  * 小问题的最小单元, 直接return
* Step 3: Subproblem
  * 把大问题拆解为小问题
* Step 4: Recursion Rule
  * 根据小问题的结果, 如果得到大问题的答案



无限递归:

f(x) = 从前有座山, 山里有座庙, 庙里有个老和尚, 正在给小和尚讲故事呢! 故事是什么呢 "f(x)"

 从前有座山, 山里有座庙, 庙里有个老和尚, 正在给小和尚讲故事呢! 故事是什么呢 " 从前有座山, 山里有座庙, 庙里有个老和尚, 正在给小和尚讲故事呢! 故事是什么呢 " 从前有座山, 山里有座庙, 庙里有个老和尚, 正在给小和尚讲故事呢! 故事是什么呢 " ...."



有限递归: base case 就是格物

《礼记:大学》:
“古之欲明明德于天下者，先治其国;
		 欲治其国者，先齐其家;
						欲齐其家者，先修其身;
								欲修其身者，先正其心;
										欲正其心者，先诚其意;
													欲诚其意者，先致其知，
														致知在==格物==。
														物格而后知至，
													知至而后意诚,
										意诚而后心正，
								心正而后身修,
						 身修而后家齐,
			家齐而后国治,
国治而后天下平。



**How to split an array?**

<img src="./Merge Sort.assets/Screen Shot 2022-02-28 at 20.40.55.png" alt="Screen Shot 2022-02-28 at 20.40.55" style="zoom:50%;" />



<img src="./Merge Sort.assets/Screen Shot 2022-02-28 at 20.41.21.png" alt="Screen Shot 2022-02-28 at 20.41.21" style="zoom:50%;" />

一个Merge Sort思路的例子: 排列一摞卷子, 按分数从小到大

​           [5 2 7 4 1 3 8 6]

[5 2 7 4]					[1 3 8 6]

​     |                                |

[2 4 5 7]                    [1 3 6 8]

​        \                             /

​            [1 2 3 4 5 6 7 8]

排列卷子的base case是什么? 这一摞卷子只有一张!



​                 [5, 2, 7, 4 | 1, 3, 8, 6]

​               /                             \ 

​       [5, 2 | 7, 4]					[1, 3 | 8, 6]

​       /               \                            /        \

   [5 | 2]       [7 | 4]              [1 | 3]      [8 | 6]

​    /    \             /    \                  /   \            /    \

[5]   [2]         [7]    [4]           [1]    [3]      [8]   [6]

[5, 2, 7, 4 , 1, 3, 8, 6], left = 1, right = 3

```java
int[] mergeSort(int[] array, int left, int right) // Merge sort array with
range [left, right]

> base case
  subproblem的array长度为1
  left == right
  
>	Subproblem
  int mid = left + (right - left)/2;
	mergeSort(array, left, mid);
	mergeSort(array, mid + 1, right);

  [x x x], L=1, R=3, M=2
  [x x][x]
  [x] [x]
    
> recursive rule
  merge function: intput: two sorted array
    							output: merged sorted array
  merge([2, 4, 5, 7], [1, 3, 6, 8]=>[1, 2, 3, 4, 5, 6, 7, 8])
    
  [2, 4, 5, 7]
    					 i = leftResult.length
  leftResult[leftResult.length] => 数组越界
  [1, 3, 6, 8]
    				j
  result = [1, 2, 3, 4, 5, 6, 7, 8]
  Output = [1, 2, 3, 4, 5, 6, 7, 8]
  merge:TC: O(N)
    
```



```java
package Class10;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args){
        int[] a = {5, 2, 7, 4, 1, 3, 8, 6};
        MergeSort solution = new MergeSort();
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(solution.mergeSort(a)));
    }

    public static int[] mergeSort(int[] array){  // {5, 2, 7, 4, 1, 3, 8, 6}
        // corner case
        if (array == null || array.length <= 1){
            return array;
        }

        return mergeSort(array, 0, array.length-1); // {5, 2, 7, 4, 1, 3, 8, 6}, 0, 7//
    }
    // {5, 2, 7, 4, 1, 3, 8, 6}
    private static int[] mergeSort(int[] array, int left, int right){
        // base case
        if (left == right){
            return new int[] {array[left]};
        }

        // subproblem
        int mid = left + (right - left)/2;  // mid = 0+(7-0)/2 =3
        int[] leftResult = mergeSort(array, left, mid); // [5 2 7 4]
        int[] rightResult = mergeSort(array, mid + 1, right); // [1 3 8 6]

        // recursive rule
        return merge(leftResult, rightResult); //
    }

    private static int[] merge(int[] leftResult, int[] rightResult){
        //[2 4 5 7],[ 1 3 6 8] -> [1 2 3 4 5 6 7 8]
        int[] result = new int[leftResult.length + rightResult.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < leftResult.length && j < rightResult.length){
            if (leftResult[i] > rightResult[j]){
                result[k] = rightResult[j];
                j++;
            } else{
                result[k] = leftResult[i];
                i++;
            }
            k++;
        }
        // case1. left result has some not merged, right result doesn't
        // case2. right result has some not merged, left resule doesn't
        // [X] case3. both result are empty?
        // [X] case4. both result have some not merged?

        while(i < leftResult.length){
            result[k] = leftResult[i];
            i++;
            k++;
        }
        while(j < rightResult.length){
            result[k] = rightResult[j];
            j++;
            k++;
        }
        return result;
    }
}
```

```
[5, 2, 7, 4, 1, 3, 8, 6]
[1, 2, 3, 4, 5, 6, 7, 8]

Process finished with exit code 0
```



```java
22 private static int[] mergeSort(int[] array, int left, int right){
23        // base case
24        if (left == right){
25            return new int[] {array[left]};
26        }

27        // subproblem
28        int mid = left + (right - left)/2;  // mid = 0+(7-0)/2 =3
29        int[] leftResult = mergeSort(array, left, mid); // [5 2 7 4]
30        int[] rightResult = mergeSort(array, mid + 1, right); // [1 3 8 6]

31        // recursive rule
32        return merge(leftResult, rightResult); //
33    }
```

运行过程，输入[5, 2 , 7, 1, 3, 8, 6]

L0                        					   (L = 0, R = 7) M = 3, line:30

​                             LR=[2, 4, 5, 7]                 						     RR= [1, 3, 8, 6]

​                                    /																			\

L1               (L = 0, R = 3) M =1, line:32                      				   (L=4, R=7)M=5, line:30

​               LR = [2 5]     RR = [4 7]  res = [2 4 5 7]-.       		         LR =[1 3]                   	  RR =

​               /   																	\                    											    \ 

L2      (L=0, R=1) M=0 line:32										(L=2, R=3) M=2 line:32                          (L=6, R =7) M=6, L=21

​           LR =[5]       RR=[2] res =[2 5]                             LR =[7]     RR = [4]     res = [4, 7]            LR=[8]     RR=

​            /                           \ 																																	\

L3 (L=0, R=0) line:25       (L=1, R=1) line:25																				(L=7, R=7) M=7, L=17

​      res=[5]                              res = [2]              																					res =[6]







index 0 					7                          a[0......7] ->a[0....3] merge a[4....7]

​          5, 2 , 7, 1, 3, 8, 6

​              /                   \

​      5274				  1386                        this level time complexity = O(2)

  	 /   \                       /  \             

​     52  74                  13  86                     this level time complexity = O(4)

​	 /\    /\             	  /\     /\                       ....

  5  2  7  4		       1  3  8  6                    this leve time complexity = O(n)

How many levels above this line = O(logn)



How many levels below this line =O(logn)

Time complexity below this line = O(n)*O(logn) = O(nlogn)

1                  5	2	7	4	1	3	8	6

...                  \     /     \    /      \   /       \   /

n/4 space      25		47		13		 68                      this level time complexity = O(n)

  						  \    /			   	\	/

n/2 space          2357				 1368                           this level time complexity = O(n)

​								  \     				/                         

​             					   12345678									this level time complexity = O(n)

2^(logn) = n

Total time = O(n) + O(nlogn) = O(nlogn)

stack: O(logn)

heap: O(n)

1+2+4 =7 =8-1

1+2+4+8 = 15 = 16-1

1+ 2+4+8+16 = 31 =32-1

...

2(1+2+4+... n/2) = n

1 < logn < n

n < nlogn < n^2





sort 偶数个数

Divide 过程

​																		[4, 2, 3, 1]

​															/                                     \

​										  [==4, 2==, 3, 1]                                          [4, 2 , ==3, 1==]

​									    /         \						                                	/	  \

​                      [==4==, 2, 3, 1]         	[4, ==2==, 3, 1]                          [4, 2, ==3==, 1]            [4, 2, 3, ==1==]

Recursion Function             left     right       operation

result

--------------     divide whole array

\----------------

mergeSort(array,0,3)              [4,2] [3,1]               call mergeSort(array,0,1)

\------------------- divide left subarray

\--------------------

mergeSort(array,0,1)              [4]   [2]                  call mergeSort(array, 0, 0)

mergeSort(array,0,0)              [/]    [/]                  base case, return [4]                           [4]

mergeSort(array, 1,1)             [/]     [/]                 base case, return [2]                           [2]

------------------------------- divide right subarray

\-------------------------------

mergeSort(array,2,3)              []      [3,1]                call mergeSort(array,2, 2)

mergeSort(array,2,2)              [/]     [/]                    base case, return[3]                           [3]

mergeSort(array,3,3)              [/]      [/]                   base case, return[1]                           [1]

​							

​                        												[4, 2, 3, 1]

​															/                                     \

​										  [==4, 2==, 3, 1]                                          [4, 2 , ==3, 1==]

​									    /         \						                                	/	  \

​                      [==4==, 2, 3, 1]         	[4, ==2==, 3, 1]                          [4, 2, ==3==, 1]            [4, 2, 3, ==1==]

​                             |     						|											|						|

\---------------------------------------------------------------------------------------------------------------------------------------

​                             | 							|											|						|

​                            [4]                          [2]                                           [3]                     [1]

​                                  \                      /     												\ 				/

​              							[2, 4]																	[1, 3]

​														\                                                        /

​																\											/

​																		\							/

​																			 [1 , 2, 3, 4]



merge的过程：

------------------------------------------------- merge right subarray

\-------------------------------------------------

merge(array, 2, 3)                      [3]        [1]                                                           [1, 3]

\-------------------------------------------------- merge left subarray

\-----------------------------------------------------

merge(array,0,1)                         [4]          [2]          												[2, 4]

\--------------------------------------------------- merge whole array

\---------------------------------------------------

merge(array,0,3)     					[2,4] 		[1,3]	

[1,2,3,4]



sort奇数个

Divide的过程：

​																[4, 2, 3, 1, 0]

​															/						\

​									[==4, 2, 3==, 1, 0]                                [4, 2, 3, ==1, 0==]

​                            /                    \                                         /             \  

​          [==4, 2==, 3, 1, 0]              [4, 2, ==3==, 1, 0]             [4, 2, 3, ==1==, 0]		[4, 2, 3, 1, ==0==]

​		/					\															|

[==4==, 2, 3, 1, 0]         [4, ==2==, 3, 1, 0]



Recursion Function      left	 right            operation                  result

\-----------------------      divide whole array

\---------------------------

mergeSort(array, 0, 4)        [4, 2, 3] [1, 0]		 call mergeSort(array, 0, 2)

\------------------------------------ divide left subarray

\---------------------------------------

mergeSort(array, 0 , 4)        [4,  2] [3] 	           call mergeSort(array, 0, 1)

mergeSort(array, 0, 1)		 [4] 	  [2]				call mergeSort(array, 0 , 0)

mergeSort(array, 0 , 0)		[/]		[/]				 base case, return [4]				  	[4]

mergeSort(array, 1, 1)         [/]		[/] 				base case, return [2]                      [2]

mergeSort(array, 2, 2)         [/]        [/]                 base case, return [3]					  [3]

----------------------------------------divide right subarray

mergeSort(array, 3, 4)			[1]       [0]			 call mergeSort(array, 3, 3)		

mergeSort(array, 3, 3)			[/]		[/]			  base case, return[1]						 [1]

mergeSort(array, 4, 4)            [/]        [/]              base case, return[0]                         [0]



​															 [4, 2, 3, 1, 0]

​															/						\

​									[==4, 2, 3==, 1, 0]                                [4, 2, 3, ==1, 0==]

​                            /                    \                                         /             \  

​          [==4, 2==, 3, 1, 0]              [4, 2, ==3==, 1, 0]             [4, 2, 3, ==1==, 0]		[4, 2, 3, 1, ==0==]

​		/					\							|								|						|

[==4==, 2, 3, 1, 0]         [4, ==2==, 3, 1, 0]		|								|						|

​		|								|				|								|						|

\----------------------------------------------------------------------------------------------------------------

 |								|			      	 |						    		|						|

  [4]							 [2]				    [3]						   	[1]					[0]

​				\				/							|									|                    |

​                     [2, 4]                               [3]                                [1]                    [0]

​                                      \            /												     \           /

​										[2, 3, 4]														[0 ,1]

​														\												/

​															\									   /

​																	\						/

​																		[0, 1, 2, 3, 4]

merge的过程：

--------------------------------------------- merge right subarray

\------------------------------------------------------

merge(array, 3, 4)                       [1]    [0]														[0,1]

\-------------------------------------------- merge left subarray

\------------------------------------------------

merge(array, 0, 1)						[4]	[2]														 [2, 4]

merge(array, 0, 2)						[2, 4] [3]														[2, 3, 4]

\----------------------------------------------------------- merge whole array

\------------------------------------------------------------

merge(array, 0 , 4)					  [2, 3, 4] [0, 1] 												[0, 1, 2, 3, 4]



**Wrap Up**

* What is sorting? What is it used for ?
  * 对容器中的元素按照某种顺序排列
* Selection Sort
  * index i的意义： 有序和无序的挡板
  * 复杂度分析
    * T[O(n^2)], S[O(1)]
* Merge Sort
  * 局部有序-> 整体有序
  * 挡板思想：用index对数组虚拟划分
  * 归并： 谁小移谁
  * 复杂度分析
    * T[O(nlogn)]: 拆分O(n) , 归并O(nlogn)
    * S[O(n)]: heap maxO(n), stack maxO(logn)



​	

​            

 

<img src="./Merge Sort.assets/Screen Shot 2022-02-28 at 20.41.52.png" alt="Screen Shot 2022-02-28 at 20.41.52" style="zoom:50%;" />





<img src="./Merge Sort.assets/Screen Shot 2022-02-28 at 20.42.04.png" alt="Screen Shot 2022-02-28 at 20.42.04" style="zoom:50%;" />

## 举一反二 (中等难度)

Question3: Could we use Merge Sort to sort a linked list? What is the time complexity if so ?

Yes. does not change



Question4: Given a string "A1B2C3D4", how to convert it to another string "ABCD1234"

​								A1B2C3D4

​							A1B2 | C3D4

​						A1     B2     C3    D4

​                   A   1   B 	2	C	3	D	4

\---------------------------------------------------------------------

​    				 	A1     B2     C3     D4		

​       	 				 A1B2 | C3D4                                  2nd last step

​								A1B2C3D4									last step

1st A

​	   i

2nd 1

​		 j

solu = {A 1}



How to merge: 1. ASCII Code

2. i, j comparator.

   把letter 的优先级高于数字





## Quick Sort

Quick Sort()

第三个想法

1. 随机选一个学生 所有比他高的站右边 矮的站左边
2. 左边和右边的用同样方法排序





