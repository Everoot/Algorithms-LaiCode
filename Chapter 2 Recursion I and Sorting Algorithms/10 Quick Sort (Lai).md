# Quick Sort

 LaiCode Quick Sort 10

Given an array of integers, sort the elements in the array in ascending order. The quick sort algorithm should be used to solve this problem.

**Examples**

- {1} is sorted to {1}
- {1, 2, 3} is sorted to {1, 2, 3}
- {3, 2, 1} is sorted to {1, 2, 3}
- {4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6}

**Corner Cases**

- What if the given array is null? In this case, we do not need to do anything.
- What if the given array is of length zero? In this case, we do not need to do anything.



```java
/* 10. Quick Sort
Given an array of integers, sort the elements in the array in ascending order. The quick sort algorithm should be used to solve this problem.

Examples

{1} is sorted to {1}
{1, 2, 3} is sorted to {1, 2, 3}
{3, 2, 1} is sorted to {1, 2, 3}
{4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6}
Corner Cases

What if the given array is null? In this case, we do not need to do anything.
What if the given array is of length zero? In this case, we do not need to do anything.
*
*/

import java.util.Arrays;
import java.util.Random;

public class QuickSort10{
	public Random random = new Random();
	public static void main(String[] args){
		int[] array = new int[]{4,2,-3,6,1};
		System.out.println(Arrays.toString(array));
		QuickSort10 q = new QuickSort10();
		q.sort(array);
		System.out.println(Arrays.toString(array));
	}

	public void sort(int[] array){
		// base case
		if (array == null || array.length <= 1){
			return;
		}

		quickSort(array, 0, array.length-1);

	}


	public void quickSort(int[] array, int left, int right){
		if (left >= right){
			return;
		}

		// pre-process
		// step 1. choose a pivot
		// goal: a random integer in [left, right]
		// random.nextInt(x) -> a random integer in [0,x)
		//
		// [2, 3, 4, 5], left = 2 ,right = 5
		// 2 + random.nextInt(4)
		// random.nextInt(3) -> a random integer in [0,3) = [0, 1, 2]
		//

		int pivot = left + random.nextInt(right - left + 1);
		// put pivot in the last
		swap(array, pivot, right);
		
		// step 2. partition logic
		int i = left;
		int j = right -1;
		while(i <= j){
			if (array[i] >= array[right]){
				swap(array,i,j);
				j--;
			}else{
				i++;
			}
		}

		// step 3.move the pivot back
		swap(array, i, right); // swap i not j
		
		// subproblem/ recursive rule
		quickSort(array, left, i-1);
		quickSort(array, i+1, right);

	}


	public static void swap(int[] array, int pivot, int right){
		int rem = array[pivot];
		array[pivot] = array[right];
		array[right] = rem;	

	}

	/* Time complexity: n^2
	 *
	 * Space time: O(n)
	 *
	 *
	 *
	 *
	 *
	 *
	 */




}

/*
 * 常见问题:
1) Random 是什么？
随机数生成的工具类，在本例中用于生成指定范围内均匀分布的整数随机数
2) rand.nextInt(right-left+1)表示什么意思
rand类型为Random, 该实例的方法nextInt(x) 表示产生一个范围[0, x-1]的均匀分布的随机整数，所以nextInt(right-left+1)
表示产生范围[0, right-left]的均匀分布的随机整数。
参考程序中的代码
int pivotIndex = left + random.nextInt(right-left+1);
pivotIndex实际随机数范围为[left, right].
3) 为什么是i <= j, i < j可不可以？
中间为探索区域由挡板i和j定义为[i,j]即i==j时仍存在一个未处理元素要检查。若i<j则无法对最后一个元素做检查。
4）Partition之后i和j分别停在哪儿？
i在j的右边第一个
5）为什么开始pivot跟right交换？为什么不跟left交换
可以选择和left交换，两边都是可以的
6）为什么最后right要和i交换，和j交换可不可以？
i在j的右边第一个，如果与j交换则会将一个小于pivot的值交换到右侧。
*/


```



Quick Sort()

第三个想法

1. 随机选一个学生 所有比他高的站右边 矮的站左边
2. 左边和右边的用同样方法排序



~Bob~ **David** Eva Charlie Alice

​        (pivot)



Subproblem for smaller

<u>~Bob~</u> **David** Eva Charlie Alice

​        (pivot)



Subproblem for normal

~Bob~ **David** <u>Eva Charlie Alice</u>

​        (pivot)



e.g. Input[N] = {1 9 8 5 3}

| Initial state | 1    | 9    | 8    | 3    | ==5== |
| ------------- | ---- | ---- | ---- | ---- | ----- |

​																																										say pivot =5

两个挡板 i j, 三个区域 a) b) c)的思想:

a) [0 ... i): i的左侧(不包含i) 全部为比pivot小的数

b) [i...j]: i 和j之间为未知探索区域

c) (j... n-1): j的右侧(不包含j)全部为比pivot大或等于的数字

e.g. Pivot == 5

| index | 0    | 1    | 2     | 3       | 4       | 5    | 6    |
| ----- | ---- | ---- | ----- | ------- | ------- | ---- | ---- |
| A[7]  | ~1~  | ~3~  | **9** | **...** | **...** | 8    | 5    |

​																						i->										<- j     

​											i->																											<- j                                       

**1st Question**: what is the final position of 5? 5 is randomly selected (5 is called pivot).

**principle**: iterate over the whole array, and put all numbers smaller than 5 to the left, then put 5 following (all numbers larger than 5 are already on 5's r-hand).

**implementation details:** first put 5 to the right-most position(swap(3,5))



例子 input[N] = {1 9 8 5 3}

| Initial state | 1    | 9    | 8    | ==5== | 3    |
| ------------- | ---- | ---- | ---- | ----- | ---- |

​																																					Pivot=5

定义区域: 小 [0, i), 大或等于 (j, n-1]

定义挡板: i, j

| index          | 0    | 1    | 2    | 3    | 4            |
| -------------- | ---- | ---- | ---- | ---- | ------------ |
| swap(3,5)      | 1    | 9    | 8    | 3    | ==5(pivot)== |
| current index: | i->  |      |      | <-j  |              |



| index          | 0    | 1    | 2    | 3    | 4            |
| -------------- | ---- | ---- | ---- | ---- | ------------ |
| swap(3,5)      | ~1~  | 9    | 8    | 3    | ==5(pivot)== |
| current index: |      | i->  |      | <-j  |              |

| index          | 0    | 1    | 2    | 3     | 4            |
| -------------- | ---- | ---- | ---- | ----- | ------------ |
| swap(3,5)      | ~1~  | ~3~  | 8    | **9** | ==5(pivot)== |
| current index: |      | i->  | <-j  |       |              |

| index          | 0    | 1    | 2                | 3     | 4            |
| -------------- | ---- | ---- | ---------------- | ----- | ------------ |
| swap(3,5)      | ~1~  | ~3~  | **8**            | **9** | ==5(pivot)== |
| current index: |      |      | i ->  <br /><- j |       |              |

| index          | 0    | 1    | 2     | 3     | 4            |
| -------------- | ---- | ---- | ----- | ----- | ------------ |
| swap(3,5)      | ~1~  | ~3~  | **8** | **9** | ==5(pivot)== |
| current index: |      | <- j | i ->  |       |              |

swap(4, i)

| index          | 0    | 1    | 2            | 3     | 4     |
| -------------- | ---- | ---- | ------------ | ----- | ----- |
| swap(3,5)      | ~1~  | ~3~  | **5(pivot)** | **9** | **8** |
| current index: |      | <- j | i ->         |       |       |

swap pivot to the end of array

\------------------------------------------------

if i th element < pivot: i++

if i th element >= pivot; swap(i, j) j--

\-------------------------------------------------------

swap(current pivot index,i)

\--------------------------------------------------------



**Summary:** 

把一个pivot value 找到真正属于它的index, 在寻找的同时, 把所有比该value小的放在了左, 大的放在右

Function: void quickSort(int[] array, int left, int right); // sort array with array[left, right]

base case: left >= right

quickSort(array, 7, 6)[a : b]-> a<= x<=b, [7:6] -> 7<=x <=6

subproblem:

recursive rule:

| index         | 0    | 1    | 2    | 3    | 4    | 5    | 6    | 7    |
| ------------- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| A[7]          | x    | x    | x    | x    | x    | x    | x    | P    |
| current index |      |      |      |      |      |      | i    | j    |

quickSort(array, 7, 6)

quickSort(array, left, i -1);

​			quickSort(array, 0, 5); OK

quickSort(array, i+1, right);

​			quickSort(array, 7, 6);



| index         |      | 0    | 1    | 2    | 3    | 4    | 5    | 6    | 7    |
| ------------- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| A[7]          |      | P    | x    | x    | x    | x    | x    | x    | x    |
| current index | j    | i    |      |      |      |      |      |      |      |

quickSort(array, 0 , -1)

​		quickSort(array, left, i-1);

​				quickSort(array, 0, -1);

​		quickSort(array, i+1, right);

​				quickSort(array, 1, 6);



Preprocess: 分组

Subproblem = Recursive rule: 

Quicksort all numbers to the left of 5

Quicksort all numbers to the right of 5

quickSort(array, left, i-1);

quickSort(array, i+1, right);



```java
package Class12;

import java.util.Arrays;
import java.util.Random;

public class QuickSort2 {
    private Random random = new Random();
    public void quickSort(int[]  array){
        // corner case
        if (array == null || array.length <= 1){
            return;
        }
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int left, int right){
        // [left, right]
        // base case
        if (left >= right){
            return;
        }

        // pre-process
        // step 1. choose a pivot
        // goal: a random integer in [left, right]
        //       random.nextInt(x) -> a random integer in [0,x)
        //
        // [2, 3, 4, 5], left = 2, right =5
        // 2 + random.nextInt(4)
        // random.nextInt(3) -> a random integer in [0,3) = [0, 1, 2]
        //[left, right] -> left + [0, right -left]
        //              -> left + [0, right - left +1]
        int pivotIndex = left + random.nextInt(right - left + 1);
        swap(array,pivotIndex, right);
        // step 2. partition logic
        int i = left;
        int j = right - 1;
        while(i <= j){
            if(array[i]>= array[right]){
                swap(array,i,j);
                j--;
            } else {
                i++;
            }
        }
        // step 3.move the pivot back
        swap(array,i,right);
        // subproblem / recursive rule
        quickSort(array, left, i-1);
        quickSort(array, i+1, right);
    }

    public void swap(int[] array, int x, int y){
        int tmp = array[x];
        array[x] = array[y];
        array[y] = tmp;
    }

    public static void main(String[] args){
        int a[] = {1,9,8,5,3};
        System.out.println(Arrays.toString(a));
        QuickSort2 qs = new QuickSort2();
        qs.quickSort(a);
        System.out.println(Arrays.toString(a));
    }

}


/*
常见问题：
1）为什么有两个quickSort？
method overloading. 函数名字相同，但parameter type list不同的functions.
2) Random 是什么？
随机数生成的工具类，在本例中用于生成指定范围内均匀分布的整数随机数
3）rand.nextInt(right-left+1)表示什么意思
rand类型为Random, 该实例的方法nextInt(x) 表示产生一个范围[0, x-1]的均匀分布的随机整数，所以nextInt(right-left+1)
表示产生范围[0, right-left]的均匀分布的随机整数。
参考程序中的代码
int pivotIndex = left + random.nextInt(right-left+1);
pivotIndex实际随机数范围为[left, right].
4) 为什么是i <= j, i < j可不可以？
中间为探索区域由挡板i和j定义为[i,j]即i==j时仍存在一个未处理元素要检查。若i<j则无法对最后一个元素做检查。
5）Partition之后i和j分别停在哪儿？
i在j的右边第一个
6）为什么开始pivot跟right交换？为什么不跟left交换
可以选择和left交换，两边都是可以的
7）为什么最后right要和i交换，和j交换可不可以？
i在j的右边第一个，如果与j交换则会将一个小于pivot的值交换到右侧。
 */
```

```
[1, 9, 8, 5, 3]
[1, 3, 5, 8, 9]

Process finished with exit code 0
```



Q： 为什么要如此调用？

```java
QuickSort2 qs = new QuickSort2();
        qs.quickSort(a);
```

A(from stack overflow):

-----

You must understand the difference between a class and an instance of that class. If you see a car on the street, you know immediately that it's a car even if you can't see which model or type. This is because you compare what you see with the *class* "car". The class contains which is similar to all cars. Think of it as a template or an idea.

At the same time, the car you see is an instance of the *class* "car" since it has all the properties which you expect: There is someone driving it, it has an engine, wheels.

So the class says "all cars have a color" and the instance says "this specific car is red".

In the OO world, you define the class and inside the class, you define a field of type `Color`. **When the class is instantiated (when you create a specific instance), memory is reserved for the color and you can give this specific instance a color. Since these attributes are specific, they are non-static.**

Static fields and methods are shared with all instances. They are for values which are specific to the class and not a specific instance. For methods, this usually are global helper methods (like `Integer.parseInt()`). For fields, it's usually constants (like car types, i.e. something where you have a limited set which doesn't change often).

**To solve your problem, you need to instantiate an instance (create an object) of your class so the runtime can reserve memory for the instance** (otherwise, different instances would overwrite each other which you don't want).

In your case, try this code as a starting block:

```java
public static void main (String[] args)
{
    try
    {
        MyProgram7 obj = new MyProgram7 ();
        obj.run (args);
    }
    catch (Exception e)
    {
        e.printStackTrace ();
    }
}

// instance variables here

public void run (String[] args) throws Exception
{
    // put your code here
}
```

The new `main()` method creates an instance of the class it contains (sounds strange but since `main()` is created with the class instead of with the instance, it can do this) and then calls an instance method (`run()`).

---





Discussion:

1. What is the worst case scenario for quicksort? Can you provide an example?

   8 7 6 5 4 3 

   3 7 6 5 4 8

   Time:

   worst case: O(n^2)

   average case: O(nlogn)

   best case: O(nlogn)

   Space:

   worst case: O(n)

   ​	stack: O(n)

   ​	heap: O(1)

   average case: O(logn)

   ​	stack: O(logn)

   ​	heap: O(1)

   best case: O(logn)

   ​	stack:O(logn)

   ​	heap: O(1)



​			xxx P XXX

​         xPX         xPX

​     x         X       x      X

Level1                       xxxxxxx P1 xxxxxx                     O(n)

​                                  L                             R       

Level2                       xxxxxxxxxxxx P2                         O(n-2)

...

Leveln                                  time = O(n^2)

n-1+n-2+,,,+1= n*(n-1)/2 = O(n^2)

2. How to select pivot?

   1. Always choose the leftmost one sorted/ X sorted
   2. Always choose the rightmost one sorted/ X sorted
   3. Always choose the middle one       V/W-shape       10, 2, 12
   4. Randomly choose three and use its median better
   5. Randomly choose three and use its median better

3. Time Complexity? Average O( nlog n ), worst case O(n^2)

4. Space complexity? Average O(log n), worst case O(n)

5. What if all the elements in the original array are duplicate

   [==1==, 1, 1, 1, 1, 1, 1]

   ​	 [1, 1, 1, 1, 1, 1]

   ... 

   ​						  [1]

   [0, 1, 2, 2, 3, ==2==]

   [0, 1, ==2, 2, 2==, **3**]

   [0, 1]     [2, 2, 3]

   

   [1, 1, 1, 1, 1, 1,1]

   []                        []

   

   [5, 4, 3, 2, 1]

   [2, 1, 3, 4, 5]

   [2, 1]      [4,5]

