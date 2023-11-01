# 45 Jump Game II

You are given a **0-indexed** array of integers `nums` of length `n`. You are initially positioned at `nums[0]`.

Each element `nums[i]` represents the maximum length of a forward jump from index `i`. In other words, if you are at `nums[i]`, you can jump to any `nums[i + j]` where:

- `0 <= j <= nums[i]` and
- `i + j < n`

Return *the minimum number of jumps to reach* `nums[n - 1]`. The test cases are generated such that you can reach `nums[n - 1]`.

 

**Example 1:**

```
Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
```

**Example 2:**

```
Input: nums = [2,3,0,1,4]
Output: 2
```





index 0 1 2 3 4 

​	 A=[2,3,1,1,4]

​				    1 0

​				<- linear scan

​										-> look back

​						

M[i]: represent minimum number of jumps needed from $ith$ index to last index

M[lastIndex] = 0



数学表达式 induction rule:

要不从自己这一步能跳到M[你] = 1

要不我能跳到一个已知能跳到target的中继节点

M[你] = M[中继节点] + 1

Min{all possible way}

M[i] = min(M[j] +1) or i 能直达: M[i] = 1



return: M[0]



```java
class Solution {
    public int jump(int[] nums) {
        // base case
        int[] m = new int[nums.length];
        m[nums.length -1] = 0;

        // induction rule
        for (int i = nums.length - 2; i >= 0; i--){
            m[i] = Integer.MAX_VALUE;
            for (int j = nums.length - 1; j > i; j--){
                if (i + 1 >= j || nums[i] + i >= j){
                    m[i] = Math.min(m[j] + 1, m[i]);
                }
            }
        }
        return m[0];
    }
}

/*

index  0    1   2   3   4
       2    3   1   1   4
M[i]   2     1    2    1    0      M[i]:represent the index i minmun step to the end
             <- linear scan 
             -> look back 
            i   
                           j

// induction rule
    for (i = nums.length -2; i >= 0; i--){
        M[i] = Integer.MAX_VALUE;
        for (j = nums.length -1 ; j > i; j--){
            if (i + 1 >= j || nums[i] + i >= j){
                m[i] = Math.min(m[j]+1, m[i])
            } 
        }

    }
    
// return  M[0]

*/
```

