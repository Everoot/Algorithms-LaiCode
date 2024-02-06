# 657 Can I Win II (Lai)

There is an array of positive integers, in which each integer represents a piece of Pizza’s size, you and your friend take turns to pick pizza from either end of the array.  Your friend follows a simple strategy: He will always pick the larger one he could get during his turn. The winner is the one who gets the larger total sum of all pizza. Return the max amount of pizza you can get.

**Assumption:** If during your friend's turn, the leftmost pizza has the same size as the rightmost pizza, he will pick the rightmost one.

**Example:**

Input: [2,1,100,3]

Output: 102

Explanation: To win the game, you pick 2 first, then your friend will pick either 3, after that you could pick 100. In the end you could get 2 + 100 = 102, while your friend could only get 1 + 3 = 4.

**Solution:**

```java
public class Solution {
  public int canWin(int[] nums) {
    // Write your solution here
        if (nums == null || nums.length == 0){
            return 0;
        }

        int[][] M = new int[nums.length][nums.length];
        //Assumption: If during your friend's turn, the leftmost pizza has the same size as the rightmost pizza,
        // he will pick the rightmost one.
        /*
        M[i][j] represents [from the i-th pizza to the j-th pizza] the largest total 
        sum of all pizza you can pick assuming you start first.
        */
        for (int j = 0; j < nums.length; j++){
            for (int i = j; i >=0; i--){
                // left right
                // left
            if (i == j){ // 1个pizza
                M[i][j] = nums[i];
            } else if (i + 1 == j){// 2 个pizza
                M[i][j] = Math.max(nums[i], nums[j]);
            } else {//3个pizza以上
                int curleft;
                int curright;
                if (nums[i + 1] <= nums[j]) {
          // Suppose I pick nums[i], then my friend while pick Math.max(i+1, j) -> j
          // then I have to pick from i+1 , j - 1
                    curleft = nums[i] + M[i + 1][j - 1];
                } else { 
                    curleft = nums[i] + M[i + 2][j];
                }
                // right
                if (nums[i] <= nums[j - 1]) {
                    curright = nums[j] + M[i][j - 2];
                } else {
                    curright = nums[j] + M[i + 1][j - 1];
                }
                M[i][j] = Math.max(curleft, curright);
                }
            }
        }
        return M[0][nums.length-1];
    }
}

// TC: O(n^2)
// SC: O(n^2)
/*
   	j->
		0 1 2   3  
i 0 2 2 102 102
| 1   1 100 103
V 2     101 100
  3          3
*/
```



There is an array of positive integers **with no duplicate**, in which each integer represents a piece of Pizza's size, you and your friend take turns to pick pizza from the array. Your friend's strategy is pretty simple, he always picks a **larger** size pizza from either end of the remaining pizzas each time. Your strategy is also to pick a piece of pizza from either and each time. What's the **largest** total sum of all pizza **you can pick assuming you start first**.

1	2	100	4	3	5	6



me: 6	3	100

friends: 5	4



Case 1

pick 1, your friend will pick 6, you pick first from 2	100	4	3	5

Result for case 1:

1 + result from picking from 2	100	4	3	5



pick  6 

pick 6, you friend will pick 5.

Result for case2:

6 + result from picking from 1	2	100	4	3	5	6



**M\[i][j] represents [from the i-th pizza to the j-th pizza] the largest total sum of all pizza you can pick assuming you start first.**



Solution:

1. Base case: 1 piece of pizza M\[i][i] = input[i]

   ​					2 piece of pizza M\[i][i+1] = max{input[i], input[i+1]}

   

2. Induction rule:

   M\[i][j] represents [from the i-th pizza to the j-th pizza] the largest total sum of all pizza you can pick assuming you start first.

   

   M\[i][j] = max{case 1: if we take the left pizza

   ​                                      input[i] + M\[i+2][j]                      if input[i+1] > input[j]     friend 拿走大的了

   ​                                      input[i] + M\[i][j-1]                       if input[i+1] < input[j]

   case 2: if we take right pizza

   ​                                     input[j] + M\[i+1][j-1]                   if input[i] > input[j - 1]

   ​                                    input[j] + M\[i][j-2]                         if input[i] < input[j - 1]                       

   ​                      }

   M\[i][j] result will be max(result from case 1, result from case 2)

   

|      | 0    | 1    | 2    | 3    | 4    | 5    | 6    |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| 0    | 1    | 2    |      |      |      |      |      |
| 1    |      | 2    | 100  |      |      |      |      |
| 2    |      |      | 100  | 100  |      |      |      |
| 3    |      |      |      | 4    | 4    |      |      |
| 4    |      |      |      |      | 3    | 5    |      |
| 5    |      |      |      |      |      | 5    | 6    |
| 6    |      |      |      |      |      |      | 6    |



只有一个pizza的时候

|      | 0    | 1    | 2    | 3    | 4    | 5    | 6    |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| 0    | 1    |      |      |      |      |      |      |
| 1    |      | 2    |      |      |      |      |      |
| 2    |      |      | 100  |      |      |      |      |
| 3    |      |      |      | 4    |      |      |      |
| 4    |      |      |      |      | 3    |      |      |
| 5    |      |      |      |      |      | 5    |      |
| 6    |      |      |      |      |      |      | 6    |



0	1	2        3	4	5	6

1	2	100	4	3	5	6

两个pizza的时候

0 1

1  2    =    2



1 2

2 100   = 100



2	    3

100	4	= 100



3		4

4		3   = 4



4	5

3	5    = 5



5	6

5	6 = 6





|      | 0    | 1    | 2    | 3    | 4    | 5    | 6    |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| 0    |      | 2    |      |      |      |      |      |
| 1    |      |      | 100  |      |      |      |      |
| 2    |      |      |      | 100  |      |      |      |
| 3    |      |      |      |      | 4    |      |      |
| 4    |      |      |      |      |      | 5    |      |
| 5    |      |      |      |      |      |      | 6    |
| 6    |      |      |      |      |      |      |      |



三个pizza的时候

0	1	2	   3	4	5	6

1	2	100	4	3	5	6

M\[0][2]:

0	1	2	 

1	2	100	

Case 1:  left

​         input[i+1] = input[1] = 2        input[j] = input[2] = 100 

​		  if input[i+1] > input[j] friend 拿走了input[i+1]			input[i] + M\[i+2][i]                                 

​		      	 		            												              

​		  if input[i+1] < input[j] friend 拿走 input[j]        input[i] + M\[i+1][j-1]

​			 ✅																				1 + M\[1][1] = 3

​	Case 2: right 

​         input[i] = input[0] =  1    input[j-1] = 2 

​         if input[i] > input[j-1]  friend 拿走了input[i]            input[j] + M\[i+1][j-1]

​																								 

​		if input[i] < input[j - 1] friend 拿走了input[j-1]        input[j] + M\[i][j-2] 

​					✅																		 	100 + M\[0][0] = 101

left < right          101



M\[1][3]

0	1	2	   3	4	5	6

1	2	100	4	3	5	6

left:   2 + M\[3][3] = 2 + 4 = 6

right: 4 + M\[1][1] = 4 + 2 = 6



M\[2][4] 

left : 100 + M\[4][4]. = 100 +3 = 103

right: 3 + M\[3][3] =  3+ 4 =7

  

|      | 0    | 1    | 2    | 3    | 4    | 5    | 6    |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| 0    | 1    | 2    | 101  | 101  | 104  | 106  | 110  |
| 1    |      | 2    | 100  | 6    | 103  | 11   | 109  |
| 2    |      |      | 100  | 100  | 103  | 104  | 108  |
| 3    |      |      |      | 4    | 4    | 8    | 10   |
| 4    |      |      |      |      | 3    | 5    | 9    |
| 5    |      |      |      |      |      | 5    | 6    |
| 6    |      |      |      |      |      |      | 6    |

i: 表示pizza的index

j: 表示pizza的index



Time: O(n^2) * O(1) = O(n^2)

Space: O(n^2)   



