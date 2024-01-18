# 176 Longest Common Substring (Lai)

Find the longest common substring of two given strings.

**Assumptions**

- The two given strings are not null

**Examples**

- S = “abcde”, T = “cdf”, the longest common substring of S and T is “cd”

 

**Solution:**

```java
public class Solution {
  public String longestCommon(String source, String target) {
    // Write your solution here
    char[] sa = source.toCharArray();
    char[] ta = target.toCharArray();

    int[][] m = new int[sa.length][ta.length];
    

    int start = 0;

    int max = 0;

    for (int i = 0; i < sa.length; i++){
      for (int j = 0; j < ta.length; j++){
        if (sa[i] == ta[j]){
          if (i == 0 || j == 0){
            m[i][j] = 1;
            start = i;
          }else{
            m[i][j] = m[i-1][j-1] + 1;
          }
          if (m[i][j] > max){
            max = m[i][j];
            start = i - max + 1;
          }
        }
      }
    }

    return source.substring(start, start + max);
  }
}

// TC: O(n^2)
// SC: o(n^2)

/*

    m[i][j] represents the length of that between first i letter of first j letters of second string
    and a[i] and b[j] are included.

       i
     0 1 2 3 4
     a b c d e
 0 c 0 0 1 0 0      j
 1 d 0 0 0 2 0
 2 f 0 0 0 0 0


// base case
i = 0
j = 0


// induction rule

M[i][j] = M[i-1][j-1] + 1     if (s.i == s.j)


 max =  2
 return source 

*/
```



```java
public class Solution {
  public String longestCommon(String source, String target) {
    // Write your solution here
    char[] string1 = source.toCharArray();
    char[] string2 = target.toCharArray();

    int start = 0;
    int globalmax = 0;
    int[][] M = new int[string1.length][string2.length];
    for (int i = 0; i < string1.length; i++){
      for (int j = 0; j < string2.length; j++){
        if (string1[i] == string2[j]){
          if (i == 0 || j == 0){
            M[i][j] = 1;
          } else {
            M[i][j] = M[i - 1][j -1] + 1;
          }

          if (M[i][j] > globalmax){
            globalmax = M[i][j];
            start = i - globalmax + 1;
            // 0 1  2 3 4
            //      i
          }
        }
      }
    }
    return source.substring(start, start + globalmax);
  }
}

/*
Example, student & weden, then return "den".

M[i][j] = length of the ... between the first i letters of the first j letters of the second string and a[i] and b[j] are included.

i表示前i个letter of 第一个string

j表示前j个letter of 第二个string

Base case

M[0][j] = 0           0 空字符

M[i][0] = 0



Case 1: M[i][j] = 1 + M[i - 1][j - 1]            if 			a[i - 1] == b[j - 1]          继承

Case 2: M[i][j] = 0 									else		a[i -1] != b[j -1]          东山再起



TC: O(n*m) * O(1) *O(1) = O(n*m)

SC: O(n*m) => 只需要上一行的信息 -> optimize to O(m)



*/
```

<img src="./176 Longest Common Substring (Lai)/Screenshot 2024-01-17 at 05.25.56.png" alt="Screenshot 2024-01-17 at 05.25.56" style="zoom:50%;" />

