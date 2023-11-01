# 678 Valid Parenthesis String

Given a string `s` containing only three types of characters: `'('`, `')'` and `'*'`, return `true` *if* `s` *is **valid***.

The following rules define a **valid** string:

- Any left parenthesis `'('` must have a corresponding right parenthesis `')'`.
- Any right parenthesis `')'` must have a corresponding left parenthesis `'('`.
- Left parenthesis `'('` must go before the corresponding right parenthesis `')'`.
- `'*'` could be treated as a single right parenthesis `')'` or a single left parenthesis `'('` or an empty string `""`.

 

**Example 1:**

```
Input: s = "()"
Output: true
```

**Example 2:**

```
Input: s = "(*)"
Output: true
```

**Example 3:**

```
Input: s = "(*))"
Output: true
```

**Solution:**

```java
class Solution {
    public boolean checkValidString(String s) {
        Deque<Integer> brackets = new ArrayDeque<Integer>();
        Deque<Integer> stars = new ArrayDeque<Integer>();
        char[] sArray = s.toCharArray();
        for (int i = 0; i < sArray.length; i++){
            if (sArray[i] == '(' ){
                brackets.offerLast(i);
            }else if (sArray[i] == '*'){
                stars.offerLast(i);
            }else{
                // brackets == ')'
                if (!brackets.isEmpty()){
                    brackets.pollLast();
                }else if (!stars.isEmpty()){
                    stars.pollLast();
                }else{
                    // brackets.isempty && star.isEmpty()
                    return false;
                }
            }
        }

        while(!brackets.isEmpty()){
            if(stars.isEmpty()){
                return false;
            }else if (stars.peekLast() > brackets.peekLast()){
                brackets.pollLast();
                stars.pollLast();
            }else{
                return false;
            }
        }

        return true;

        
    }
}

// TC: O(n)
// SC: O(n)
```

