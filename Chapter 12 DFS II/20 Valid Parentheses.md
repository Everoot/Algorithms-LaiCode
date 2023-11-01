# 20 Valid Parentheses

Given a string `s` containing just the characters `'('`, `')'`, `'{'`, `'}'`, `'['` and `']'`, determine if the input string is valid.

An input string is valid if:

1. Open brackets must be closed by the same type of brackets.
2. Open brackets must be closed in the correct order.
3. Every close bracket has a corresponding open bracket of the same type.

 

**Example 1:**

```
Input: s = "()"
Output: true
```

**Example 2:**

```
Input: s = "()[]{}"
Output: true
```

**Example 3:**

```
Input: s = "(]"
Output: false
```



**Solution:**

```java
class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<Character>();
        char[] sArray = s.toCharArray();
        for (int i = 0; i < sArray.length; i++){
            if (sArray[i] == '('){
                stack.offerLast(')');
                continue;
            }

            if (sArray[i] == '{'){
                stack.offerLast('}');
                continue;
            }
            
            if (sArray[i] == '['){
                stack.offerLast(']');
                continue;
            }

            if (stack.isEmpty()){
                return false;
            }

            if (stack.pollLast() != sArray[i]){
                return false;
            }

    
        }

        return stack.isEmpty();
    }
}

// TC: O(n)
// SC: O(n)
/*

 [   { }   ]

 

 ^              }
 |             ]

 [:     
*/
```

