#Rainbow Sort

LeetCode Sort Colors 75

Given an array `nums` with `n` objects colored red, white, or blue, sort them **[in-place](https://en.wikipedia.org/wiki/In-place_algorithm)** so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers `0`, `1`, and `2` to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function.

 

LaiCode

Rainbow Sort 11

Given an array of balls, where the color of the balls can only be Red, Green or Blue, sort the balls such that all the Red balls are grouped on the left side, all the Green balls are grouped in the middle and all the Blue balls are grouped on the right side. (Red is denoted by -1, Green is denoted by 0, and Blue is denoted by 1).

**Examples**

- {0} is sorted to {0}
- {1, 0} is sorted to {0, 1}
- {1, 0, 1, -1, 0} is sorted to {-1, 0, 0, 1, 1}

**Assumptions**

- The input array is not null.

**Corner Cases**

- What if the input array is of length zero? In this case, we should not do anything as well.



(abcccabbcbbacaa -> aaaaa bbbbb ccccc)

Dutch Flag problem 荷兰旗问题

[3333222211133323122333]

```java
int c1 = 0, c2 = 0, c3 = 0;
for(int x: array){           //  same to  for(int x = 0; x > array.length; x++)
  	if (x == 1){
      	c1++;
    }else if (x == 2){
      	c2++;
    }else {
      	c3++;
    }
}
for (int i =0; i < c1; i++){
  	array[i] = 1;
}
for (int i = c1; i < c1+c2;i++){
  	array[i] = 2;
}
for (int i = c1 + c2; i < c1+c2+c3; i++){
  	array[i] = 3;
}
```

[1, -3, 0, 2, 4]

<0, =0, >0

[-3, 0 ,1, 4, 2] OK

[-3, 0, 4, 2, 1] OK

[-3, 0, 1, 2, 4] OK



```java
class Student{
  	public String name;
  	public int gpa;
}

Student[] array
```

区域:

对比挡板法在quicksort的运用

两个挡板 i j, 三个区域 a) b) c)的思想:

a) [0 ... i): i的左侧(不包含i) 全部为比pivot小的数

b) [i...j]: i 和j之间为未知探索区域

c) (j... n-1): j的右侧(不包含j)全部为比pivot大或等于的数字

e.g. Pivot == 5

| index | 0    | 1    | 2     | 3       | 4       | 5    | 6    |
| ----- | ---- | ---- | ----- | ------- | ------- | ---- | ---- |
| A[7]  | ~1~  | ~3~  | **9** | **...** | **...** | 8    | 5    |

​																						i->										<- j     



Example:

Rainbow sort中挡板法的物理意义

[0, i ) -> 1

[i, j) -> 2

[j, k] -> to be sorted

(k, n-1) ->3



[x x x x x y y y y  y y ? ? ? z z z z ]

​                 i                 j     k

if x: swap(i, j); i++; j++;

if y: j++;

if z: swap(j, k); k++;

[1 1 2 2 3 3]

​		i   k j



[x x x x x y y y y  y x ? ? ? z z z z ]

​                 i                j     k

[x x x x x x y y y  y ? ? ? z z z z ]

​                    i             j     k



```java
package Class12;

import java.util.Arrays;

public class RainbowSort2 {
    public void rainbowSort(int[] array){
        // corner case
        if (array == null|| array.length ==0){
            return;
        }

        int i = 0;
        int j = 0;
        int k = array.length -1;
        while(j <= k){
            if (array[j] == 1) { // array[j].gpa<3
                // red
                swap(array,i,j);
                i++;
                j++;
            } else if (array[j] == 2){ // array[j].gpa ==3
                // yellow
                j++;
            } else {
                // blue // array[j].gpa >3
                swap(array, j, k);
                k--;
            }
        }
    }
    public void swap(int[] array, int x, int y){
        int tmp = array[x];
        array[x] = array[y];
        array[y] = tmp;
    }

    public static void main(String[] args){
        int a[] = {3, 2, 1, 2, 3, 3, 2, 2, 1,3};
        RainbowSort2 rs = new RainbowSort2();
        rs.rainbowSort(a);
        System.out.println(Arrays.toString(a));
    }
}
/*
常见问题
1）为什么while循环是j<=k，可不可以j<k?
2) 为什么其中两种情况要swap元素，一种不需要swap？
注意物理定义，当当前处理元素在最左边的一段或者最右边一段时，必须要swap，因为
    a.逻辑上最左边的一段和待探索区域之间还有第二段内容
    b.最右边的一段和待探索区域最左端还有未处理的探索元素
3） 为什么k--的时候不需要j++,而i++的时候j++？
参考上一个问题关于物理意义的概念
 */
```

```
[1, 1, 2, 2, 2, 2, 3, 3, 3, 3]

Process finished with exit code 0
```

Discussion:

1)TC : O(n)

2)SC : O(1)

###