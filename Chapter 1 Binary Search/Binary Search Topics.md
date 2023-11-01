# Binary Search Topics

10000 words in a dictionary, search elephant



1		2		3		4		5			2500		2579							5000				10000

a1	a2		b1	b2		b3	 ...**ea**sy	...**el**ephant...fast floor **good**... yahoo...zebra

[L																								 		M=5000				R]  size 10000

[L2												M2=2500										R2]					size 5000

​														[L3												R3]                     size 2500

​														2501											4999



Principles of Binary Search

1. We must guarantee that the **search space decreases** over time (after each iteration)
2. We must guarantee that the **target (if exists) cannot be ruled out** accidentally, when we change the value of Left or Right. (**It is critical to define the rule about how to move the range for search**)



Sorted array: search an element



What is **binary search** in the context of an array?

1. Array has to be sorted. ascending or descending 1 2 3 5 7 9...

2. Problem to solve ? Given a target, try to find the target in the array.

   

**Example: a[7] = 1 2 4 5 7 8 9** whether **4** is in this array or not.

Target = 4

| index | 0    | 1    | 2     | 3    | 4    | 5    | 6    |
| ----- | ---- | ---- | ----- | ---- | ---- | ---- | ---- |
| A[7]  | 1    | 2    | ==4== | 5    | 7    | 8    | 9    |

​											[L = 0                     									M = 3														R = 6]

​											[L2 = 0          M2 = 1		R2 = M-1=2]

​																				[L3 = M2+1=2 M3 =2 R3=2] Bingo! return 2;



Target = 2

| index | 0    | 1     | 2    | 3    | 4    | 5    | 6    |
| ----- | ---- | ----- | ---- | ---- | ---- | ---- | ---- |
| A[7]  | 1    | ==2== | 4    | 5    | 7    | 8    | 9    |

​											[L = 0                                     					M = 3													R = 6 ]

​											[L2 = 0          M2 = 1		R2 = M-1 =2 ] Return as early as possible, return 1;



Target = 3

| index | 0    | 1    | 2    | 3    | 4    | 5    | 6    |
| ----- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| A[7]  | 1    | 2    | 4    | 5    | 7    | 8    | 9    |

​         									[L = 0													M = 3															R = 6]

​											 [L2 = 0        M2 = 1 		 R2 = M-1=2]

​																					[L3 =2 M3 =2 R3 =2]

​        														R3 = M3 - 1 = 1][L4 = 2 return -1;

[L, R] -> if the target exists, it must be within [L, R]

挡板法

  0 1 2 3 4 5 6

[ 1 2 4 5 7 8 9 ]

​						 L

​				     M

​					 R



[L, R]



If found 

Target = 4.

Target = 2.

Target = 9

If not found

Target 3 

Target 0 

Target 10 

| index | 0    | 1    | 2     | 3    | 4    | 5    | 6    |
| ----- | ---- | ---- | ----- | ---- | ---- | ---- | ---- |
| A[7]  | 1    | 2    | ==4== | 5    | 7    | 8    | 9    |

​											L																

​																												M	

​																																												R

target > array[mid]? -> [mid + 1, R]

| index | 0    | 1    | 2     | 3    | 4    | 5    | 6    |
| ----- | ---- | ---- | ----- | ---- | ---- | ---- | ---- |
| A[7]  | 1    | 2    | ==4== | 5    | 7    | 8    | 9    |

​											L

​																	M	

​																						R

target < arrary[mid]? ->[mid + 1, R]

| index | 0    | 1    | 2     | 3    | 4    | 5    | 6    |
| ----- | ---- | ---- | ----- | ---- | ---- | ---- | ---- |
| A[7]  | 1    | 2    | ==4== | 5    | 7    | 8    | 9    |

​											L

​																 	M

​																						R																		

target < array[mid] ? -> [L, mid - 1]

| index | 0    | 1    | 2     | 3    | 4    | 5    | 6    |
| ----- | ---- | ---- | ----- | ---- | ---- | ---- | ---- |
| A[7]  | 1    | 2    | ==4== | 5    | 7    | 8    | 9    |

​																						L

​																						M

​																						R

target == array[mid] -> return mid;                                 // index

**Iteration 1**: L = 0, R = 6, M = 3 A[M] == A[3] == 5 > target == 4, so R = M - 1 = 2;

**Iteration 2**: L = 0, R = 2, M = 1 A[M] == A[1] == 2 < target == 4, so L = M + 1 = 2;

**Iteration 3**: L = 2, R = 2, M = 2 A[M] == A[2] == 4 == target, so Done!!!

Discussion:

1. What is the physical meaning of L and R
2. What is the time complexity
3. What is the space complexity

```java
package Class8;
// Classical Version 1.0
// return any target element's index
public class ClassicalVersion {
    public static void main (String[] args){
        int[] a = new int[]{1,2,4,6};
        binarySearch(a, 4);

    }
    public static int binarySearch (int[] a, int target){
        int left = 0;
        int right = a.length - 1;
        while (left <= right) {           // [L, R]  left < right WRONG
            //int mid = left + (left - right)/2;
            int mid = left + (right-left)/2;
            if (a[mid] == target) {
                return mid;
            } else if (a[mid] > target){
                right = mid - 1;
            } else {
                left = mid + 1; // left = mid WRONG
            }
        }
        return -1;
    }
}
/*
常见错误： 如果写成 int mid = left + (left - right)/2;
会产生报错样例如下， 表示数组越界：
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 4
	at Class8.ClassicalVersion.binarySearch(ClassicalVersion.java:16)
	at Class8.ClassicalVersion.main(ClassicalVersion.java:7)
	
常见错误：如果mid的更新写到while之外，则会导致死循环	
 */
```

常见错误： 如果写成 int mid = left + (left - right)/2;
会产生报错样例如下， 表示数组越界：

```
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 4
	at Class8.ClassicalVersion.binarySearch(ClassicalVersion.java:16)
	at Class8.ClassicalVersion.main(ClassicalVersion.java:7)	
常见错误：如果mid的更新写到while之外，则会导致死循环	
```

Time: 

10,000,	5,000,	2,500, ...... 8,	4,	2,	1,	0

1 = 2^0

2 = 2^1

... 

...



10,000 = 2^k

K = log以2为底10000的对数-> logn



Space: 额外占用的空间

1. Stack: 调用这个程序的时候在stack上开辟的内存
2. Heap: new object

O(1)



### Discussion

1. 每轮search space 必须严格缩小.
2. 不能把target 错误排除掉



把逻辑对应到正确的代码:

1. what does left, right mean?

   **Searching range: We guarantee the final answer is in [left, right] all the time.**

2. What does while loop do?

   **Using mid element to determine and guarantee next round, point 1 holds true.**

3. When should the while loop terminate(base case)?

   * It **MUST** be able to terminate, there should not be any infinite loop

   * depends on how you define the recursive rule



```java
package Class8;

public class BinarySearch {
    public static void main(String[] args){
        int[] array = new int[]{1, 2, 4, 5, 7, 8, 9};
        System.out.println(binarySearch(array, 4));
        System.out.println(binarySearch(array, 3));
        
    }
    public static int binarySearch(int array[], int target){
        // corner case
        if (array == null || array.length == 0) {
            return -1;
        }

        int left = 0;
        int right = array.length -1;
        while (left <= right){
            //Overflow
            // Maxint: 2^31 - 1 = 2.18
            //
            // (left + right)/2 ;
            // array.length = 2 ^31 -1
            // Iter 0: left = 0, right = 2^31 - 1, mid = 2^30 -1
            // Iter 1: left = 2^30 -1, right = 2^31 -1
            //
            // 1. right - left: SAFE.
            // 2. (*) / 2 : SAFE
            // 3. left + (*): SAFE
            //    left + (*) <= left + (right - left) = right
            //
            // left / 2 + right /2 . L = 3, R = 5
            // (left + right) / 2 =4
            // left/ 2 + right /2 = 1 + 2 = 3
            //
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target){
                // [1, 2], target = 2;
                // left = 0, right = 1, mid = 0;
                // New iteration: left = 0, right =1
                left = mid + 1;
            } else{//array[mid] > target
                right = mid -1; }
        }
        return -1;
    }
}
```

```
2
-1

Process finished with exit code 0
```



Index: 0 1 2 3 4 5 6

Array: 1 2 4 5 7 8 9

**Case1: Target can be found**

**Return early!**



**Case2: Target cannot be found, but within the range of the [min, max]**

Target = 3

Index: 0 1 2 3 4 5 6

Array: 1 2 4 5 7 8 9

Left: 		  l

Mid: 		  m

Right:      r 

Stop status: r = l -1, and the target value should be inserted between r and l



**Case 3: Target cannot be found, and it's smaller than min**

Target = 0

Index: 	0 1 2 3 4 5 6

Array: 	1 2 4 5 7 8 9

Left:    	l

Mid:    	m

Right:  r

Stop status: r = -1, l = 0



**Case 4: Target cannot be found, and it's larger than max**

Target = 10

Index: 	0 1 2 3 4 5 6 

Array: 	1 2 4 5 7 8 9

Left: 						         l

Mid: 						   m

Right: 						 r

Stop status: r = n - l, l = n



**Time complexity** 

10000 5000 2500 ....... 16 	8 	4 	2 	1

2^x                                                       2^1  2^0

2^x = 10000(n)

X = log_2N



**Space complexity**

O(1)

All other problems are just applications of this single algorithm!



### Lai14 Classical Binary Search

DescriptionNotes

Easy

Given a target integer T and an integer array A sorted in ascending order, find the index i such that A[i] == T or return -1 if there is no such index.

**Assumptions**

- There can be duplicate elements in the array, and you can return any of the indices i such that A[i] == T.

**Examples**

- A = {1, 2, 3, 4, 5}, T = 3, return 2
- A = {1, 2, 3, 4, 5}, T = 6, return -1
- A = {1, 2, 2, 2, 3, 4}, T = 2, return 1 or 2 or 3

**Corner Cases**

- What if A is null or A is of zero length? We should return -1 in this case.

```java
/*
14ClassicalBinarySearch
Easy
Given a target integer T and an integer array A sorted in ascending order,
find the index i such that A[i] == T or return -1 if there is no such index.
**Assumptions**
- There can be duplicate elements in the array, and you can return any of the indices i such that A[i] == T.
**Examples**
- A = {1, 2, 3, 4, 5}, T = 3, return 2
- A = {1, 2, 3, 4, 5}, T = 6, return -1
- A = {1, 2, 2, 2, 3, 4}, T = 2, return 1 or 2 or 3
**Corner Cases**
- What if A is null or A is of zero length? We should return -1 in this case.
 
 */
public class ClassicalBinarySearch_14{
 	public static void main(String[] args){
		int[] A = {1,2,3,4,5};
		int TA = 3;
		int[] B = {1,2,3,4,5};
		int TB = 6;
		int[] C = {1,2,2,2,3,4};
		int TC = 2;
		System.out.println(binarySearch(A, TA));
		System.out.println(binarySearch(B, TB));
		System.out.println(binarySearch(C, TC));
	}
	public static int binarySearch(int[] array, int target){
		if (array == null || array.length ==0){
			return -1;
		}
		int left = 0;
		int right = array.length -1;
		while (left <= right){
			int mid = left + (right - left)/2;
			if (array[mid] == target){
				return mid;
			}else if (array[mid] < target){
				left = mid + 1;
			}else{
				right = mid - 1;
			}
		}
		return -1;
	}
}
```



### Lai267 Search In Sorted Matrix I

DescriptionNotes

Medium

Given a 2D matrix that contains integers only, which each row is sorted in an ascending order. The first element of next row is larger than (or equal to) the last element of previous row.

Given a target number, returning the position that the target locates within the matrix. If the target number does not exist in the matrix, return {-1, -1}.

**Assumptions:**

- The given matrix is not null, and has size of N * M, where N >= 0 and M >= 0.

**Examples:**

matrix = { {1, 2, 3}, {4, 5, 7}, {8, 9, 10} }

target = 7, return {1, 2}

target = 6, return {-1, -1} to represent the target number does not exist in the matrix.





### Lai17 Closest In Sorted Array

DescriptionNotes

Medium

Given a target integer T and an integer array A sorted in ascending order, find the index i in A such that A[i] is closest to T.

**Assumptions**

- There can be duplicate elements in the array, and we can return any of the indices with same value.

**Examples**

- A = {1, 2, 3}, T = 2, return 1
- A = {1, 4, 6}, T = 3, return 1
- A = {1, 4, 6}, T = 5, return 1 or 2
- A = {1, 3, 3, 4}, T = 2, return 0 or 1 or 2

**Corner Cases**

- What if A is null or A is of zero length? We should return -1 in this case.



### Lai15 First Occurrence

DescriptionNotes

Medium

Given a target integer T and an integer array A sorted in ascending order, find the index of the first occurrence of T in A or return -1 if there is no such index.

**Assumptions**

- There can be duplicate elements in the array.

**Examples**

- A = {1, 2, 3}, T = 2, return 1
- A = {1, 2, 3}, T = 4, return -1
- A = {1, 2, 2, 2, 3}, T = 2, return 1

**Corner Cases**

- What if A is null or A of zero length? We should return -1 in this case.



### Lai16 Last Occurrence

DescriptionNotes

Medium

Given a target integer T and an integer array A sorted in ascending order, find the index of the last occurrence of T in A or return -1 if there is no such index.

**Assumptions**

- There can be duplicate elements in the array.

**Examples**

- A = {1, 2, 3}, T = 2, return 1
- A = {1, 2, 3}, T = 4, return -1
- A = {1, 2, 2, 2, 3}, T = 2, return 3

**Corner Cases**

- What if A is null or A is array of zero length? We should return -1 in this case.



### Lai19 K Closest In Sorted Array

DescriptionNotes

Medium

Given a target integer T, a non-negative integer K and an integer array A sorted in ascending order, find the K closest numbers to T in A. If there is a tie, the smaller elements are always preferred.

**Assumptions**

- A is not null
- K is guranteed to be >= 0 and K is guranteed to be <= A.length

**Return**

- A size K integer array containing the K closest numbers(not indices) in A, sorted in ascending order by the difference between the number and T. 

**Examples**

- A = {1, 2, 3}, T = 2, K = 3, return {2, 1, 3} or {2, 3, 1}
- A = {1, 4, 6, 8}, T = 3, K = 3, return {4, 1, 6}



### 636 Smallest Element Larger than Target

DescriptionNotes

Medium

Given a target integer T and an integer array A sorted in ascending order, find the index of the smallest element in A that is larger than T or return -1 if there is no such index.

**Assumptions**

There can be duplicate elements in the array.



**Examples**

A = {1, 2, 3}, T = 1, return 1

A = {1, 2, 3}, T = 3, return -1

A = {1, 2, 2, 2, 3}, T = 1, return 1



**Corner Cases**

What if A is null or A of zero length? We should return -1 in this case.





### Lai20 Search In Unknown Sized Sorted Array

DescriptionNotes

Medium

Given a integer dictionary A of unknown size, where the numbers in the dictionary are sorted in ascending order, determine if a given target integer T is in the dictionary. Return the index of T in A, return -1 if T is not in A.

**Assumptions**

- dictionary A is not null
- dictionary.get(i) will return null(Java)/INT_MIN(C++)/None(Python) if index i is out of bounds

**Examples**

- A = {1, 2, 5, 9, ......}, T = 5, return 2
- A = {1, 2, 5, 9, 12, ......}, T = 7, return -1



### Lai26 Kth Smallest Number In Sorted Matrix

DescriptionNotes

Medium

Given a matrix of size N x M. For each row the elements are sorted in ascending order, and for each column the elements are also sorted in ascending order. Find the Kth smallest number in it.

**Assumptions**

- the matrix is not null, N > 0 and M > 0
- K > 0 and K <= N * M

**Examples**

{ {1,  3,  5,  7},

 {2,  4,  8,  9},

 {3,  5, 11, 15},

 {6,  8, 13, 18} }

- the 5th smallest number is 4
- the 8th smallest number is 6



### Lai368. Lowest Common Ancestor Binary Search Tree I

DescriptionNotes

Easy

Given two keys in a binary search tree, find their lowest common ancestor.

**Assumptions**

- There is no parent pointer for the nodes in the binary search tree
- There are no duplicate keys in the binary search tree
- The given two nodes are guaranteed to be in the binary search tree

**Examples**

​    5

​    /  \

   2   12

  /  \    \

 1  3   14

The lowest common ancestor of 1 and 14 is 5

The lowest common ancestor of 1 and 3 is 2



### Lai504. Closest Number In Binary Search Tree II

DescriptionNotes

Medium

In a binary search tree, find k nodes containing the closest numbers to the given target number. return them in sorted array

**Assumptions:**

- The given root is not null.
- There are no duplicate keys in the binary search tree.

**Examples:**

   5

 /   \

2    11

   /   \

  6   14

closest number to 4 is 5

closest number to 10 is 11

closest number to 6 is 6

**How is the binary tree represented?**

We use the level order traversal sequence with a special symbol "#" denoting the null node.

**For Example:**

The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

  1

 /  \

 2   3

   /

  4



### Lai135. Closest Number In Binary Search Tree

DescriptionNotes

Medium

In a binary search tree, find the node containing the closest number to the given target number.

**Assumptions:**

- The given root is not null.
- There are no duplicate keys in the binary search tree.

**Examples:**

   5

 /   \

2    11

   /   \

  6   14

closest number to 4 is 5

closest number to 10 is 11

closest number to 6 is 6

**How is the binary tree represented?**

We use the level order traversal sequence with a special symbol "#" denoting the null node.

**For Example:**

The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

  1

 /  \

 2   3

   /

4





### 136 Largest Number Smaller In Binary Search Tree

DescriptionNotes

Medium

In a binary search tree, find the node containing the largest number smaller than the given target number.

If there is no such number, return -2^31.

**Assumptions:**

- The given root is not null.
- There are no duplicate keys in the binary search tree.

**Examples**

  5

 /   \

2    11

   /   \

  6   14

largest number smaller than 1 is Integer.MIN_VALUE(Java) or INT_MIN(c++)

largest number smaller than 10 is 6

largest number smaller than 6 is 5

**How is the binary tree represented?**

We use the level order traversal sequence with a special symbol "#" denoting the null node.

**For Example:**

The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

  1

 /  \

 2   3

   /

  4





### Lai211. Reconstruct Binary Search Tree With Postorder Traversal

DescriptionNotes

Medium

Given the postorder traversal sequence of a binary search tree, reconstruct the original tree.

**Assumptions**

- The given sequence is not null
- There are no duplicate keys in the binary search tree

**Examples**

postorder traversal = {1, 4, 3, 11, 8, 5}

the corresponding binary search tree is

​    5

   /   \

  3     8

 /  \     \

1    4     11

**How is the binary tree represented?**

**We use the pre order traversal sequence with a special symbol "#" denoting the null node.**

**For Example:**

The sequence [1, 2, #, #, 3, 4, #, #, #] represents the following binary tree:

  1

 /  \

 2   3

   /

  4



### Lai26. Kth Smallest Number In Sorted Matrix

DescriptionNotes

Medium

Given a matrix of size N x M. For each row the elements are sorted in ascending order, and for each column the elements are also sorted in ascending order. Find the Kth smallest number in it.

**Assumptions**

- the matrix is not null, N > 0 and M > 0
- K > 0 and K <= N * M

**Examples**

{ {1,  3,  5,  7},

 {2,  4,  8,  9},

 {3,  5, 11, 15},

 {6,  8, 13, 18} }

- the 5th smallest number is 4
- the 8th smallest number is 6



### Lai202. Kth Smallest In Two Sorted Arrays

DescriptionNotes

Hard

Given two sorted arrays of integers, find the Kth smallest number.

**Assumptions**

- The two given arrays are not null and at least one of them is not empty
- K >= 1, K <= total lengths of the two sorted arrays

**Examples**

- A = {1, 4, 6}, B = {2, 3}, the 3rd smallest number is 3.
- A = {1, 2, 3, 4}, B = {}, the 2nd smallest number is 2.



### Lai636. Smallest Element Larger than Target

DescriptionNotes

Medium

Given a target integer T and an integer array A sorted in ascending order, find the index of the smallest element in A that is larger than T or return -1 if there is no such index.

**Assumptions**

There can be duplicate elements in the array.



**Examples**

A = {1, 2, 3}, T = 1, return 1

A = {1, 2, 3}, T = 3, return -1

A = {1, 2, 2, 2, 3}, T = 1, return 1



**Corner Cases**

What if A is null or A of zero length? We should return -1 in this case.







### Lai683. Count Ascending Subsequence

DescriptionNotes

Medium

Given an array A[0]...A[n-1] of integers, count the number of ascending subsequences.

In case that the result is larger than 32-bit integer, return the result in 10^9+7 modulo.

**Assumptions**

- A is not null

**Examples**
Input: A = {1,2,3}
Output: 7
Explanation: [1],[2],[3],[1,2],[1,3],[2,3],[1,2,3]







### Lai21 Search In Shifted Sorted Array I

DescriptionNotes

Medium

Given a target integer T and an integer array A, A is sorted in ascending order first, then shifted by an arbitrary number of positions.

For Example, A = {3, 4, 5, 1, 2} (shifted left by 2 positions). Find the index i such that A[i] == T or return -1 if there is no such index.

**Assumptions**

- There are no duplicate elements in the array.

**Examples**

- A = {3, 4, 5, 1, 2}, T = 4, return 1
- A = {1, 2, 3, 4, 5}, T = 4, return 3
- A = {3, 5, 6, 1, 2}, T = 4, return -1

**Corner Cases**

- What if A is null or A is of zero length? We should return -1 in this case.





### Lai22. Search In Shifted Sorted Array II

DescriptionNotes

Hard

Given a target integer T and an integer array A, A is sorted in ascending order first, then shifted by an arbitrary number of positions.

For Example, A = {3, 4, 5, 1, 2} (shifted left by 2 positions). Find the index i such that A[i] == T or return -1 if there is no such index.

**Assumptions**

- There could be duplicate elements in the array.
- Return the smallest index if target has multiple occurrence. 

**Examples**

- A = {3, 4, 5, 1, 2}, T = 4, return 1
- A = {3, 3, 3, 1, 3}, T = 1, return 3
- A = {3, 1, 3, 3, 3}, T = 1, return 1

**Corner Cases**

- What if A is null or A is of zero length? We should return -1 in this case.





### Lai23. Shift Position

DescriptionNotes

Medium

Given an integer array A, A is sorted in ascending order first then shifted by an arbitrary number of positions, For Example, A = {3, 4, 5, 1, 2} (shifted left by 2 positions). Find the index of the smallest number.

**Assumptions**

- There are no duplicate elements in the array

**Examples**

- A = {3, 4, 5, 1, 2}, return 3
- A = {1, 2, 3, 4, 5}, return 0

**Corner Cases**

- What if A is null or A is of zero length? We should return -1 in this case.



### Lai24. Total Occurrence

DescriptionNotes

Medium

Given a target integer T and an integer array A sorted in ascending order, Find the total number of occurrences of T in A.

**Examples**

- A = {1, 2, 3, 4, 5}, T = 3, return 1
- A = {1, 2, 2, 2, 3}, T = 2, return 3
- A = {1, 2, 2, 2, 3}, T = 4, return 0

**Corner Cases**

- What if A is null? We should return 0 in this case.



### Lai69. Missing Number II

DescriptionNotes

Easy

Given an integer array of size N - 1 sorted by ascending order, containing all the numbers from 1 to N except one, find the missing number.

**Assumptions**

- The given array is not null, and N >= 1

**Examples**

- A = {1, 2, 4}, the missing number is 3
- A = {1, 2, 3}, the missing number is 4
- A = {}, the missing number is 1



### Lai144. Recover Binary Search Tree

DescriptionNotes

Medium

Given a Binary Search Tree with only two nodes swapped. Try to find them and recover the binary search tree.

Input: 

​        4

​        / \

​       2  6  

​       / \  / \

​      1  5 3  7

Output:    4

​         / \

​        2  6

​        /  \  / \

​       1  3  5  7





### Lai146. Find Number of BSTs Generated

DescriptionNotes

Medium

Find the number of different Binary Search Trees generated from 1-n.

**Example:**

Input: 3, Return: 5





### Lai161. Square Root I

DescriptionNotes

Medium

Given an integer number n, find its integer square root.

Assumption:

- n is guaranteed to be >= 0.

**Example:**

Input: 18, Return: 4

Input: 4, Return: 2



### Lai203. Median Of Two Arrays

DescriptionNotes

Hard

Given two arrays of integers, find the median value.

**Assumptions**

- The two given array are not null and at least one of them is not empty
- The two given array are not guaranteed to be sorted

**Examples**

- A = {4, 1, 6}, B = {2, 3}, median is 3
- A = {1, 4}, B = {3, 2}, median is 2.5



### Lai210. Reconstruct Binary Search Tree With Preorder Traversal

DescriptionNotes

Medium

Given the preorder traversal sequence of a binary search tree, reconstruct the original tree.

**Assumptions**

- The given sequence is not null
- There are no duplicate keys in the binary search tree

**Examples**

preorder traversal = {5, 3, 1, 4, 8, 11}

The corresponding binary search tree is

​    5

   /   \

  3     8

 /  \     \

1    4     11

**How is the binary tree represented?**

**We use the pre order traversal sequence with a special symbol "#" denoting the null node.**

**For Example:**

The sequence [1, 2, #, 3, 4, #, #, #] represents the following binary tree:

  1

 /  \

 2   3

   /

  4



### Lai212. Reconstruct Binary Search Tree With Level Order Traversal

DescriptionNotes

Medium

Given the levelorder traversal sequence of a binary search tree, reconstruct the original tree.

**Assumptions**

- The given sequence is not null
- There are no duplicate keys in the binary search tree

**Examples**

levelorder traversal = {5, 3, 8, 1, 4, 11}

the corresponding binary search tree is

​    5

   /   \

  3     8

 /  \     \

1    4     11

**How is the binary tree represented?**

**We use the pre order traversal sequence with a special symbol "#" denoting the null node.**

**For Example:**

The sequence [1, 2, #, 3, 4, #, #, #] represents the following binary tree:

  1

 /  \

 2   3

   /

  4



### Lai410. Median Of Two Sorted Arrays

DescriptionNotes

Hard

Given two sorted arrays of integers in ascending order, find the median value.

**Assumptions**

- The two given array are not null and at least one of them is not empty
- The two given array are guaranteed to be sorted

**Examples**

- A = {1, 4, 6}, B = {2, 3}, median is 3
- A = {1, 4}, B = {2, 3}, median is 2.5



### Lai1. Longest Ascending Subsequence

DescriptionNotes

Medium

Given an array A[0]...A[n-1] of integers, find out the length of the longest ascending subsequence.

**Assumptions**

- A is not null

**Examples**
Input: A = {5, 2, 6, 3, 4, 7, 5}
Output: 4
Because [2, 3, 4, 5] is the longest ascending subsequence.





### 682. Longest Ascending Subsequence II

DescriptionNotes

Medium

Given an array A[0]...A[n-1] of integers, find out the longest ascending subsequence. If there are multiple results, then return any valid result.

**Assumptions**

- A is not null

**Examples**
Input: A = {5, 2, 6, 3, 4, 7, 5}
Output: [2,3,4,5]
Because [2, 3, 4, 5] is one of the longest ascending subsequences.





### L704 Binary Search 

Given an array of integers `nums` which is sorted in ascending order, and an integer `target`, write a function to search `target` in `nums`. If `target` exists, then return its index. Otherwise, return `-1`.

You must write an algorithm with `O(log n)` runtime complexity.

**Example 1:**

```
Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4
```

**Example 2:**

```
Input: nums = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: 2 does not exist in nums so return -1
```

**Constraints:**

- `1 <= nums.length <= 104`
- `-104 < nums[i], target < 104`
- All the integers in `nums` are **unique**.
- `nums` is sorted in ascending order.

```c
/*
 Given an array of integers `nums` which is sorted in ascending order, and an integer `target`,
 write a function to search `target` in `nums`. If `target` exists, then return its index. 
 Otherwise, return `-1`.
 You must write an algorithm with `O(logn)` runtime complexity.
Ex1:
Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4

Ex2: 
Input: nums = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: 2 does not exist in nums so return -1
 */
#include <stdio.h>

int search(int* nums,int numsSize, int target);
int main(void){
	int nums[] = {-1, 0, 3, 5, 9, 12};
	int target = 9;
	int numsSize = sizeof(nums)/sizeof(nums[0]);
       // printf("target: %d, numsSize: %d\n", target, numsSize);
	printf("target: %d", search(nums, numsSize, target));
}

int search(int* nums, int numsSize, int target){
	if (numsSize == 0){
		return -1;
	}
	int left = 0;
	int right = numsSize - 1;
	while (left <= right){
		int mid = left + (right - left)/2;
		if (nums[mid] == target){
			return mid;
		}else if (nums[mid] < target){
			left = mid + 1;
		}else{
			right = mid - 1;
		}
	}
	return -1;
}
```







### L4 Median of Two Sorted Arrays (Lai203)

Given two sorted arrays `nums1` and `nums2` of size `m` and `n` respectively, return **the median** of the two sorted arrays.

The overall run time complexity should be `O(log (m+n))`.

**Example 1:**

```
Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.
```

**Example 2:**

```
Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
```

**Constraints:**

- `nums1.length == m`
- `nums2.length == n`
- `0 <= m <= 1000`
- `0 <= n <= 1000`
- `1 <= m + n <= 2000`
- `-106 <= nums1[i], nums2[i] <= 106`