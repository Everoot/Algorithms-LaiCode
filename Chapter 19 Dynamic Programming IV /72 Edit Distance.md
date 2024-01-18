# 72 Edit Distance

Given two strings `word1` and `word2`, return *the minimum number of operations required to convert `word1` to `word2`*.

You have the following three operations permitted on a word:

- Insert a character
- Delete a character
- Replace a character

 

**Example 1:**

```
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
```

**Example 2:**

```
Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
```



**Solution:**

```java
class Solution {
    public int minDistance(String word1, String word2) {
        //base case 
        int[][] m = new int[word1.length() + 1][word2.length() + 1];
        for(int i = 0; i < word1.length() + 1; i++){
            m[i][0] = i;
        }

        for (int j = 0; j < word2.length() + 1; j++){
            m[0][j] = j;
        }

        // induction rule
        for (int i = 1; i < word1.length() + 1; i++){
            for (int j = 1; j < word2.length() + 1; j++){
                if (word1.charAt(i - 1) == word2.charAt(j - 1)){
                    m[i][j] = m[i-1][j-1];
                }else{
                    m[i][j] = Math.min(m[i-1][j-1], Math.min(m[i-1][j], m[i][j-1])) + 1;
                }
            }
        }

        return m[word1.length()][word2.length()];
    }
}


/*

// TC: O(n^2)
// SC: O(n^2)

base case
             j     0 1 2 3
            word2| - r o s             ""
  i word1    
    ----   
  0   -             0 1 2 3
  1   h             1 1 2 3 
  2   o             2 2 1 2
  3   r             3 2 2 2
  4   s             4 3 3 2
  5   e             5 4 4 3

  m[i][j]: present the minmum number pf operations required to covert word1 begin to i and change to the word2 begin to j

induction rule:
if (word1(i) == word2(j)){
    m[i][j] = m[i-1][j-1]
}else{
    m[i][j] = Math.min(m[i-1][j-1], m[i-1][j], m[i][j-1])+1
}


return m[word1.length][word2.length]

*/
```

