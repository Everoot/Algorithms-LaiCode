# 82 Remove Adjacent Repeated Characters IV (Lai)

Repeatedly remove all adjacent, repeated characters in a given string from left to right.

No adjacent characters should be identified in the final string.

**Examples**

- "abbbaaccz" → "aaaccz" → "ccz" → "z"
- "aabccdc" → "bccdc" → "bdc"



**Solution:**

```java
public class Solution {
  public String deDup(String input) {
    // Write your solution here
    if (input == null || input.length() <= 1){
      return input;
    }

    int slow = 0;
    char[] array = input.toCharArray();

    for (int fast = 1; fast < array.length; fast++){
      if (slow == -1){
        slow = 0;
        array[slow] = array[fast];
      }else if (array[slow] != array[fast]){
        slow++;
        array[slow] = array[fast];
      }else{
        // array[fast] == array[slow]
        slow--;
        while(fast + 1 < array.length && array[fast] == array[fast + 1]){
          fast++;   
        }// for loop fast will + 1    so pointer fast stop last one
      }
    }

    return new String(array, 0, slow +1);

  }
}

/*
    [0, slow]

    abbbaaccz
     s]
       f
*/
// TC: O(n)
// SC: O(n)

```

