# 79 Remove Adjacent Repeated Characters I (Lai)

Remove adjacent, repeated characters in a given string, leaving only one character for each group of such characters.

**Assumptions**

- Try to do it in place.

**Examples**

- “aaaabbbc” is transferred to “abc”

**Corner Cases**

- If the given string is null, returning null or an empty string are both valid.



**Solution:**

```java
public class Solution {
  public String deDup(String input) {
    // Write your solution here
    if (input == null || input.length() <= 1){
      return input;
    }

    char[] array = input.toCharArray();
    int slow = 0;
    for (int fast = 0; fast < array.length; fast++){
      // repeated characters will be ignored except
      // for the first character in the sequence.
      if (fast == 0 || array[fast] != array[slow - 1]){
        array[slow] = array[fast];
        slow++;
      }
    }

    String result = new String(array, 0, slow);
    return result;
  }
}


/*

  abccde
     s 
       f

*/

```

