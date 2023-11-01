#55 Jump Game

You are given an integer array `nums`. You are initially positioned at the array's **first index**, and each element in the array represents your maximum jump length at that position.

Return `true` *if you can reach the last index, or* `false` *otherwise*.

 

**Example 1:**

```
Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
```

**Example 2:**

```
Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
```



**Solution:**

```java
class Solution {
    public boolean canJump(int[] nums) {
        // base case
        if (nums == null || nums.length <= 0){
            return false;
        }

        boolean[] M = new boolean[nums.length];
        M[nums.length -1] = true;

        for (int i = nums.length - 2; i >= 0; i--){
            for (int j = nums.length -1; j > i; j--){
                if (M[j] == true && nums[i] + i >= j){
                    M[i] = true;
                    break;
                }
            }
        }
        return M[0];
    }
}



/*

// TC: O(n^2)
// SC: O(n)

boolean M[nums.length]
M[nums.length - 1] = true

index.   0  1  2  3  4
M[]               T  T             M[i]: represent whether can jump from i to the end 
        [2, 3, 1, 1, 4]  
               i                   i start from nums.length - 2
                     j                j start from nums.length - 1 

induction rule: 
if (M[j] == true && nums[i] + i >= j)
M[i] = true;
break;

return: M[0]


*/
```



<---------------------------------------------------------------------------------------------------------------------------------------------------------

| index      i | 0                      | 1                      | 2                                         | 3                                                            | 4                   |
| ------------ | ---------------------- | ---------------------- | ----------------------------------------- | ------------------------------------------------------------ | ------------------- |
| input        | 2                      | 3                      | 0                                         | 1                                                            | 4                   |
| M[i]         | T<br />可以跳到t的地方 | T<br />可以跳到t的地方 | F<br />我只能跳0步<br />即不能跳到T的地方 | T<br />在这儿的时候, <br />往右看,我只能跳一步 <br />能否跳到一个t的地方 | T<br />出生就在罗马 |

**Base case**: M[length - 1] = true, because it's target itself.

**Induction rule:**

M[i] represents whether I can jump from the i-th element to the target. (能否从i跳到尾)	
$$
M[i] = true \ \ if \ \ \exists_j \ M[j] == true \ OR  \ \ i + A[i] \geqslant target
$$
i < j <= i + input[i]

如果我能从i跳到某个中继站j而且已经知道j能够到达终点了

false 	otherwise

**Return M[0]**

linear scan 回头看

外面是个O(n) 里面也是O(n)

TC: O(n^2)

Space: O(n)





### Solution 2: 从左往右 

但比第一种差

index  = 0	1	2	3	4

input = [2,  3,    1,    1,   4]

M[]     = T    T	 T	T	T



前面有人自己是T 还能跳到我的 填T

base case: M[0] = true

M[i] represents whether I can jump from the start element to the i-th element. (能否从0跳到i)



linear scan 回头看 



Time: $O(n^2)$



