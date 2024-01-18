# 473 Matchsticks to Square

You are given an integer array `matchsticks` where `matchsticks[i]` is the length of the `ith` matchstick. You want to use **all the matchsticks** to make one square. You **should not break** any stick, but you can link them up, and each matchstick must be used **exactly one time**.

Return `true` if you can make this square and `false` otherwise.

 

**Example 1:**

![img](./473 Matchsticks to Square/matchsticks1-grid.jpg)

```
Input: matchsticks = [1,1,2,2,2]
Output: true
Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
```

**Example 2:**

```
Input: matchsticks = [3,3,3,3,4]
Output: false
Explanation: You cannot find a way to form a square with all the matchsticks.
```



**Solution:**

```java
class Solution {
    public boolean makesquare(int[] matchsticks) {
        // base case 
        if (matchsticks == null || matchsticks.length <= 3){
            return false;
        }

        int total = 0;

        for (int i = 0; i < matchsticks.length; i++){
            total = total + matchsticks[i];
        }

        if (total % 4 != 0){
            return false;
        }

        int target = total / 4;

        int index = matchsticks.length -1;
        int top = 0;
        int bottom = 0;
        int left = 0;
        int right = 0;

        Arrays.sort(matchsticks);
        return dfs(matchsticks, index, top, bottom, left, right, target);
        
    }

    public static boolean dfs(int[] matchsticks, int index, int top, int bottom, int left, int right, int target){
        if (top == target && bottom == target && left == target && right == target){
            return true;
        }

        if (top > target || bottom > target || left > target || right > target){
            return false;
        }

        int cur = matchsticks[index];

        if (dfs(matchsticks, index -1, top+ cur, bottom, left, right, target) == true){
            return true;
        }

        if (dfs(matchsticks, index -1, top, bottom + cur, left, right, target) == true){
            return true;
        }

        if (dfs(matchsticks, index -1, top, bottom, left +cur, right, target) == true){
            return true;
        }

        if (dfs(matchsticks, index -1, top, bottom, left, right+cur, target) == true){
            return true;
        }

        return false;
    }
}


/*
 3 + 3 +3 +3 + 4 = 12 + 4 = 16 /4     -> 4 

 1 + 1 + 2 + 3 =  2 + 5 = 7 / 4 

            1    1     2     2     4                 matchsticks[index] 
                    top, bottom, left, right
                    [0, 0, 0, 0]
                    /          \       \           \         
  index 0     [1, 0, 0,0]    [0,1,0,0] [0,0,1,0]   [0,0,0,1] 
               /|\ \
   1          [2,0,0,0]

   2    [4,0,0,0]



   top == target && bottom == target left == target right == target 
   return true;

   return false 
   top > target 
 */

 // TC: O(branch^level) O(4^n)
 // SC: O(n)
```



```java
class Solution {
    public boolean makesquare(int[] matchsticks) {
        // base case 
        if (matchsticks == null || matchsticks.length <= 3){
            return false;
        }

        int total = 0; 

        for (int i = 0; i < matchsticks.length; i++){
            total = matchsticks[i] + total;
        }

        if (total % 4 != 0){
            return false;
        }

        int index = 0;
        int top = 0;
        int bottom = 0;
        int left = 0;
        int right = 0;
        int target = total/4;
        return dfs(matchsticks, index, top, bottom, left, right, target);
    }

    private static boolean dfs(int[] matchsticks, int index, int top, int bottom , int left, int right, int target){

        if (top == target && bottom == target && left == target && right == target){
            return true;
        }

        if (top > target || bottom > target || left > target || right > target){
            return false;
        }

        int cur = matchsticks[index];

        if (dfs(matchsticks, index +1, top + cur, bottom, left, right, target) == true){
            return true;
        }

        if (dfs(matchsticks, index +1, top, bottom+cur, left, right, target) == true){
            return true;
        }

        if (dfs(matchsticks, index +1, top, bottom, left + cur, right, target) == true){
            return true;
        }

        if (dfs(matchsticks, index +1, top, bottom, left, right + cur, target) == true){
            return true;
        }
        return false;
    }
}
//TC: O(4^n)
//SC: O(n)


// TLE
```

