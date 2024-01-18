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



**Solution:**

```java
class Solution {
    public String removeDuplicates(String s) {
        // base case
        if (s == null || s.length() <= 1){
            return s;
        }

        char[] array = s.toCharArray();

        int slow = 0;

        for (int fast = 0; fast < array.length; fast++){
            if (slow == 0){
                array[slow] = array[fast];
                slow++;
            }else if (array[fast] != array[slow - 1]){
                array[slow] = array[fast];
                slow++;
            }else{
                slow--;
            }
        }

        return new String(array, 0, slow);

    }
}
// TC: O(n)
// SC: O(n)

/*
        c  a   b  a  c    a
               s
                             f

s == 0   slow = fast    f++ s++

fast != slow -1    slow = fast f++ s++
fast == slow -1 : slow--
        result [0, slow) 

*/

/*
The main aim is to remove 2 duplicate ADJACENT characters, until there are none left.

For instance: TC - "aaaaaaaa" has 8 a's, hence your code should discard 4 pairs of a i.e 8 a's in this case.

But for TC - "aaaaaaaaa" you still remove 4 pairs of a's i.e 8 a's and hence your output demands a at the end.

Lastly, for TC - "abbbabaaa"
Step 1 - remove 2 adjacent b's, making it "ababaaa"
Step 2 - remove 2 adjacent a's, making it "ababa"

这题与Laioffer的那道题82 Remove Adjacent Repeated Characters IV不太一样那个是消消乐, 这个是每次删掉两个重复循环.

这类题 -> stack的思想非常常见
*/
```

