# 1047 Remove All Adjacent Duplicates In String

You are given a string `s` consisting of lowercase English letters. A **duplicate removal** consists of choosing two **adjacent** and **equal** letters and removing them.

We repeatedly make **duplicate removals** on `s` until we no longer can.

Return *the final string after all such duplicate removals have been made*. It can be proven that the answer is **unique**.

 

**Example 1:**

```
Input: s = "abbaca"
Output: "ca"
Explanation: 
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
```

**Example 2:**

```
Input: s = "azxxzy"
Output: "ay"
```

```java
class Solution {
    public String removeDuplicates(String s) {

        if (s == null || s.length() <= 1){
            return s;
        }

        StringBuilder sb = new StringBuilder(s);
        
        int slow  = 0;
        for (int fast = 0; fast < sb.length(); fast++){
            if (slow == 0){
                sb.setCharAt(slow, sb.charAt(fast));
                slow = slow + 1;
                continue;
            }

            if (sb.charAt(fast) != sb.charAt(slow-1)){
                sb.setCharAt(slow, sb.charAt(fast));
                slow = slow+1;
            }else{// equal
                slow = slow-1;
            }
        }

        sb.delete(slow, sb.length());
        return sb.toString();
    }
}


/*
[0, s)
            c   b   b   a   c   a
                s   
                f 



*/

// TC: O(n)
// SC: O(n)

```



The main aim is to remove 2 duplicate ADJACENT characters, until there are none left.

For instance: TC - "aaaaaaaa" has 8 a's, hence your code should discard 4 pairs of a i.e 8 a's in this case.

But for TC - "aaaaaaaaa" you still remove 4 pairs of a's i.e 8 a's and hence your output demands a at the end.

Lastly, for TC - "abbbabaaa"
Step 1 - remove 2 adjacent b's, making it "ababaaa"
Step 2 - remove 2 adjacent a's, making it "ababa"

这题与Laioffer的那道题82 Remove Adjacent Repeated Characters IV不太一样那个是消消乐, 这个是每次删掉两个重复循环.



```java
class Solution {
    public String removeDuplicates(String s) {
        if (s == null || s.length() <= 1){
          return s;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++){
          if (sb.length() <= 0){
            sb.append(s.charAt(i));
            continue;
          }

          if (s.charAt(i) == sb.charAt(sb.length()-1)){
            sb.deleteCharAt(sb.length()-1);
          }else{
            sb.append(s.charAt(i));
          }

        }

        return sb.toString();
    }




  }

    /* 
    
    a b b a c a
      i

    sb: [a b 
    */
```

