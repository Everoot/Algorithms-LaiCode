# 796 Rotate String

Given two strings `s` and `goal`, return `true` *if and only if* `s` *can become* `goal` *after some number of **shifts** on* `s`.

A **shift** on `s` consists of moving the leftmost character of `s` to the rightmost position.

- For example, if `s = "abcde"`, then it will be `"bcdea"` after one shift.

 

**Example 1:**

```
Input: s = "abcde", goal = "cdeab"
Output: true
```

**Example 2:**

```
Input: s = "abcde", goal = "abced"
Output: false
```



```java
class Solution {
    public boolean rotateString(String s, String goal) {
        // base case
        if (s == null && goal == null){
            return false;
        }
        
        if (s.length() != goal.length()){
            return false;
        }

        if (s.length() == 0 && goal.length() == 0){
            return true;
        }

        int i = 0;
        char c = s.charAt(i);
        while (i < s.length()){
            while(i < goal.length() && goal.charAt(i) != c){ // find the first same character
                i++;
            }

            if (i == goal.length()){
                return false;
            }

            for (int j = 0; j < s.length(); j++){
                if (s.charAt(j) != goal.charAt((j + i) % goal.length())){
                    break;
                }
                /*
                j = 0
                0 + 3 = 3 
                1 + 3 = 4 
                (2 + 3) % 5 = 0 
                */
                // same 
                if (j == goal.length() -1){
                    return true;
                }
            }
            i++;

        }
        return false;
    }


    /* 

    s
        a   b   c   d   e
            j
    
    goal
        c   d   e   a   b
                        i


        a   b   c   d   e
                    j 

        a   b   c   e   d
                    i  

    */
}
```

