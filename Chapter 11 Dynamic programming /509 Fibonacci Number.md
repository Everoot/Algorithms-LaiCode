# 509 Fibonacci Number

The **Fibonacci numbers**, commonly denoted `F(n)` form a sequence, called the **Fibonacci sequence**, such that each number is the sum of the two preceding ones, starting from `0` and `1`. That is,

```
F(0) = 0, F(1) = 1
F(n) = F(n - 1) + F(n - 2), for n > 1.
```

Given `n`, calculate `F(n)`.

 

**Example 1:**

```
Input: n = 2
Output: 1
Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
```

**Example 2:**

```
Input: n = 3
Output: 2
Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
```

**Example 3:**

```
Input: n = 4
Output: 3
Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
```



**DP Solution:**

———————————————————————————————————————————> linear scan

​																															回头看 每次看两个<--------------------------

| index | 0    | 1    | 2    | 3    | 4    | 5    | ...  | 1000    |
| ----- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ------- |
| F(n)  | 0    | 1    | 1    | 2    | 3    | 5    |      | F(1000) |

==DP解题的方式就是如何定义表格里每个element的意义,以及把表格里的value填满==

**Base case**: F(0) = 0, F(1) = 1

**Induction rule** 归纳 (填表的过程): 这个意义是前面两个加起来 F(i) represents i-th fibonacci number which is the sum of F(i -1) and F(i - 2) 

F(i) = F[i - 1] + F [i-2] 

**Return: F(n)**

比如在index 5的时候, 只需2, 3 这个两个value 其他的不需要 

Time: O(n) rather than the naive recursion way = O(2^n)

Space = O(n) on heap rather than the recursion way = O(n) on call stack 

​			-> Optimize to O(1) as we only care about the 2 previous values 滚动地只记录两个



```java
class Solution {
    public int fib(int n) {
       // base case
       if (n == 0 || n == 1){
           return n;
       } 

       int pre = 1;
       int prepre = 0;
       int cur = 0;

       for (int i = 2; i <= n; i++){
           cur = pre + prepre;    
           prepre = pre;
           pre = cur;
       }

       return cur;
    }
}
// TC: O(n)
// SC: O(1)
```



```java
// Method1 
public static long fibonacci1(int k){
    if (k <= 0){
        return 0;
    }
    long[] array = new long[k + 1];
    array[1] = 1;
    for (int i = 2; i <= k; i++){
        array[i] = array[i - 2] + array[i - 1];
    }
    return array[k];
}

// Methode2: dp solution with O(1) space.
public static long fibonacciI2(int k){
    long a = 0;
    long b = 1;
    if (k <= 0){
        return a;
    }
    while (k > 1){
        long temp = a + b;
        a = b;
        b = temp;
        k--;
    }
    return b;
}
```



## DP的核心思想类似于我们高中学习的数据归纳法:

1. 把一个大问题(size == n)的解决方案用比他小的问题(问题们)来解决, 也就是思考从问题size = n-1增加到size=n的时候,如何用小问题的solution构建大问题desolution.

2. 与recursion的关系:

   2.1 recursion从大到小来解决问题, 不记录任何sub-solution, 只考虑base case 和 recursive rule

   2.2 <u>DP从小到大来解决问题, 记录sub-solution</u>

   ​	  2.2.1. base case

   ​	  2.2.2 induction rule: 由size(<n)的subsolution(s) -> size(n) 的solution



不是所有的recursion 都能写成DP.



## DP的解题常用方法:

1. 一维的original data (such as a rope, a word, a piece of wood), 求MAX or MIN( cut, merge, etc...)

   * if the weight of each smallest element in the original data is identical/similar
     * e.g. Identical: 1 meter of rope
     * e.g. similar: a letter, a number

   Then this kinde of problem is usually simple:

   ==**Linear sacn and look back to the previous elements(s)**==

   For example: 

   **Longest Ascending Subarray (when at i, look back at i-1)**

   **Longest Ascending Subsequence (when at i, look back at 1.. i -1)**

   **Cut rope**

   **Cut palindrome**

   * if the weight are not the same:

     1.2.1. e.g. 加强练习: 切木头

     从中心开花, [index = 0, 1, 2, 3, N-1], for each M[i, j], we usually need to try out all possible k that (i<k<j). M[i, j] = max (M[i, k] + /-/* M[k, j]) (for all possible k)

   * (加强练习) Piaza问题, 两头凑

1. 二维的original data

   * Matrix 问题, 大班课基本涵盖

   * Two Strings 寻找Minimum Edit Distance, Longest Common

     Substring/ Subsequence, 一般解题方法都是S1的前i个letter 和S2的前j个letter 比较. Induction;rule 一般要看M\[i][j]和M\[i-1][j -1]之间关系.

