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
        // base case
        int[][] m = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word2.length(); i++){
            m[0][i] = i;
        }

        for (int j = 0; j <= word1.length(); j++){
            m[j][0] = j;
        }

        for (int i = 1; i <= word1.length(); i++){
            for(int j = 1; j <= word2.length(); j++){
                if (word1.charAt(i-1) == word2.charAt(j-1)){
                    m[i][j] = m[i-1][j-1];
                }else{
                    m[i][j] = Math.min(m[i-1][j-1],Math.min(m[i-1][j], m[i][j-1])) + 1;
                }
            }
        }

        return m[word1.length()][word2.length()];
        
    }

}

/*

      word2 | _ r o s
word1  size   0 1 2 3 
-       0     
h       1
o       2
r       3
s       4
e       5
*/
```





