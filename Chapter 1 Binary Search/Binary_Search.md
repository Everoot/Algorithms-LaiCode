# Binary Search

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



第一步, 将 n 个数进行对半分 n/2

f(n) = 1 + f(n/2) 

重复

f(n) = 1 + f(n/2)

​		= 1 + (1+f(n/4))

​		= 2 + f(n/4)

f(n) = k + f(n/2^k)

如果以上步骤重复了m次之后, 数组只余一个元素无法再分, 计算结束.

f(n) = m + f(1) = m + 1

n/(2^k) = 1

n= 2^k

k = log2(n)



**Space complexity**

O(1)

All other problems are just applications of this single algorithm!

