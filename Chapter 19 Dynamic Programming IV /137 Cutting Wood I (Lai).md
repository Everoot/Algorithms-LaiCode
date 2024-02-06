# 137 Cutting Wood I (Lai)

There is a wooden stick with length L >= 1, we need to cut it into pieces, where the cutting positions are defined in an int array A. The positions are guaranteed to be in ascending order in the range of [1, L - 1]. The cost of each cut is the length of the stick segment being cut. Determine the minimum total cost to cut the stick into the defined pieces.

**Examples**

- L = 10, A = {2, 4, 7}, the minimum total cost is 10 + 4 + 6 = 20 (cut at 4 first then cut at 2 and cut at 7)



**Solution:**

```java
public class Solution {
  public int minCost(int[] cuts, int length) {
    // Write your solution here
    // Assumptions: cuts is not null, length >= 0, all cuts are valid numbers.
    // First we need to pad the original array at leftmost and 
    // rightmost position.
    int[] helper = new int[cuts.length + 2]; // [3+2] =[5] [ _ , _ , _ , _ ,_ ]
    helper[0] = 0;   // helper: [0, _, _ , _ , _ ]
    for (int i = 0; i < cuts.length; i++){
      helper[i + 1] = cuts[i];
    } // helper[0, 2, 4, 7, _]

    helper[helper.length - 1] = length; // helper[0, 2, 4, 7, 10]

    // dp[i][j]: the min cost of cutting the partition(i, j).
    int[][] dp = new int[helper.length][helper.length];
    /*
             0 1 2 3 4 
             ------------ 
          0| 
          1| 
          2|
          3|
          4|
        
    */

    for (int j = 1; j < helper.length; j++){
      for (int i = j -1; i >= 0; i--){
        if (i == j - 1){ // 如果 i 和 j 相邻，则不需要切割，成本为 0
          dp[i][j] = 0;
          /*
          假设我们有一个切割点数组 cuts = [2, 4, 7]，
          那么 helper 数组将会是 [0, 2, 4, 7, 10]（这里假设木棍长度为 10）。
          在这个数组中，如果我们考虑 i = 1 和 j = 2 的情况，
          它们对应的是 helper 数组中的值 2 和 4。因为在 2 和 4 之间没有其他切割点，
          我们不需要在这个区间进行切割，所以这个区间的切割成本为 0。
          */
        } else { 
          // 计算 i 到 j 的最小切割成本。
          dp[i][j] = Integer.MAX_VALUE;
          for (int k = i + 1; k < j; k++){
            // 循环变量 k 遍历 i 和 j 之间的所有切割点，找到成本最小的组合
            dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
          }
          // 最后加上当前切割段的长度（helper[j] - helper[i]）
          dp[i][j] = dp[i][j] + helper[j] - helper[i];
        }
      }
    }
    return dp[0][helper.length - 1];
  }
}

// M[i][j]: represents the min cost to cut wood between index i and index j

// TC: O(n^3)
// SC: O(n^2)
```

Give a length L wood and an array A[0... N] of allowed points to cut 

有一个长为L米的木材需要割开，需要切的位置在一个数组里A[0...N], **从一个地方切开的cost是当前所切木料的长度。按不同的顺序切割， 得到的total cost是不一样的，** 问怎么切cost最小。比如一个木料现在10米长，然后切的位置是2米处，4米处和7米处(就是说array A 里 A[1]是2, A[2]是4, A[3] 是7)。 那么比如先切2米，那么得到的cost是10 (因为现在木料长度为10)， 然后切4米处， 那么cost变成10+8 (因为8是现在切的时候木料的长度)。然后切7米处，cost变成10+8+6。那么这种切法总共的cost是24.



   — —  |— — |— — —| — — —         L米

A[cut的位置]       A[0, 2, 4, 7]

2:   cost = 10						— —  |— — — — — — — —

4: cost = 10 + 8 = 18          — —  | 														— — |— — — — — —

7: cost = 18 + 6 = 24        — —  |— — |												— — —| — — —





index

0		 1			2				3				4

0	1	2	3	4	5	6	7	8	9	10

\| — — |— — | — — —   | —  — —  |

​			[i=1											j = 4]

index = 0 	1	2	3	4

A[5] = (0, 2, 4, 7, 10)





0		 1			2				3		

0	1	2	3	4	5	6	7

\| — — |— — | — — —   |

[0, 7] - >

option1:

- step 1: cut at 2. cost 7 break into [0,2] &[2,7]
- step 2: cut at 4. cost 5, break into [0,2] &[2,4] &. [4,7]
- total cost 7 + 5 =12



option2:

* step 1: cut at 4. cost 7, break into [0, 4] & [4, 7]
* step 2: cut at 2. cost 4, break into [0,2] & [2, 4] & [4, 7]
* total cost 7 + 4 = 11



M[i] represents min cost if we cut at i

M[0] = 0

M[1] = NA

M[2] = 0

M[3] = NA

M[4] = M[2] + M[2] + 4 =4

M[5] = NA

M[6] = NA

M[7] = M[2] + M[5]???

​		 = M[4] +M[3] ???

一维不能做



M\[i][j]: represents the min cost to cut wood between index i and index j

Base case

- the minimum section(== 1) of wood: M\[i][i+1]
- examples: M\[0][1], M\[1][2] , ..., M\[n-2][n -1]

Induction rule:

* M\[i][j] = min(M\[i][k] + M\[k][j]) + (A[j] - A[i])	i < k < j

  ​															cost

Result:

​	M\[0][n-1]



{0, 2, 4, 7, 10}

index

0		 1			2				3				4

0	1	2	3	4	5	6	7	8	9	10

\| — — |— — | — — —   | —  — —  |

​			[i=1											j = 4]

Induction rule:

size == 2 section of wood



M\[0][2], only one cut point at 2, M\[0][2] = M\[0][1] + M\[1][2] + (4 - 0) = 4

0		 1			2		

0	1	2	3	4	

\| — — |— — | 

​			[i=1     j ]			



M\[1][3], only one cut point at 4, M\[1][3] = M\[1][2] + M\[2][3] + (7 -2) = 5

 1		 2				3	

2	3	4	5	6	7	

 |— — | — — —   | 

[i=1						j ]



M\[2][4], only one cut point at 7, M\[2][4] = M\[2][3] + M\[3][4] + (10 - 4) = 6

2				3				4

4	5	6	7	8	9	10

| — — —   | —  — —  |

[i=1								j ]



size == 3 sections of wood



M\[0][3] two cuts points options -> choose the minimum value

0		 1			2				3	

0	1	2	3	4	5	6	7	

\| — — |— — | — — —   |

​			[i=1						j ]

* Opt1: cut at index 1 (L == 2), M\[0][3] = M\[0][1] + M\[1][3] + (7 - 0) =..

* Opt2: cut at index 2 (L == 4), M\[0][3] = M\[0][2]+M\[2][3] + (7 - 0) =..



M\[1][4], two cut at 

* Opt1: cut at index 2 (L==4), M\[1][4] = M\[1][2] + M\[2][4] + (10 - 2) = ..

* Opt2: cut at index 3 (L == 7), M\[1][4] = M\[1][3] + M\[3][4] + (10 -2) = ..



size == 4 sections of wood

M\[0][4] -> k = 1/2/3 cut points

M\[0][4] = min{

* Case 1: Cut at position 1

  M\[0][1] + M\[1][4] + (length of the current board) A[4] - A[0]

* Case 2: Cut at position 2

  M\[0][2] + M\[2][4] + (length of the current board) A[4] - A[0]

* Case 3: Cut at position 3

  M\[0][3] + M\[3][4] + (length of the current board) A[4] - A[0]

}

==从中心开花, [index = 0. 1. 2. 3. N - 1], for each M[i, j], we usually need to try out all possible k that (i <k <j ), M[i, j] = min (A[j] - A[i] + M\[i][k] + M\[k][j]) for all possible k.==



M\[0][1]

​						M\[1][2]

​										M\[2][3]

​															M\[3][4]

它是斜着填的

M\[0][1]		  M\[0][2]	

​						M\[1][2]	M\[1][3]

​									 	M\[2][3]	 M\[2][4]

​															M\[3][4]



M\[0][1]		  M\[0][2]	M\[0][3]	 

​						M\[1][2]	M\[1][3]     M\[1][4]

​									 	M\[2][3]	 M\[2][4]

​															M\[3][4]



M\[0][1]		  M\[0][2]	M\[0][3]	 M\[0][4]

​						M\[1][2]	M\[1][3]     M\[1][4]

​									 	M\[2][3]	 M\[2][4]

​															M\[3][4]

TC: O(n*n) * O(n) * O(1) = O(n^3)

​       表格        rule枚举



![Screenshot 2024-01-18 at 15.03.17](./137 Cutting Wood I (Lai)/Screenshot 2024-01-18 at 15.03.17.png)

