# 82 Remove Adjacent Repeated Characters IV

Repeatedly remove all adjacent, repeated characters in a given string from left to right.

No adjacent characters should be identified in the final string.

**Examples**

- "abbbaaccz" → "aaaccz" → "ccz" → "z"
- "aabccdc" → "bccdc" → "bdc"



```java
public class Solution {
  public String deDup(String input) {
    // Write your solution here
    if (input == null || input.length() <= 1){
      return input;
    }

    char[] array = input.toCharArray();
    Deque<Character> stack = new ArrayDeque<Character>();
    int fast = 0;
    while(fast < array.length){
      char cur = array[fast];
      if (stack.isEmpty()){
        stack.offerLast(cur);
        fast++;
      }else{
        // !stack empty()
        if (cur != stack.peekLast()){
          stack.offerLast(cur);
          fast++;
        }else{
          while(fast < array.length && stack.peekLast() == array[fast]){
            fast++;
          }
          stack.pollLast();
        }
      }
    }

    int len = stack.size();

    for (int i = len -1; i >= 0; i--){
      array[i] = stack.pollLast();
    }

    return new String(array, 0, len);
  }
}

// TC: O(n)
// SC: O(n)
/*

a   a  b c c d c  -> char[] array
fast 

condition
1. stack.isempty

2. stack. not empty
    2.1 peek == fast
    2.2 peek != fast

stack:[ 


the result in the stack

for poll stack 




*/ n
```

