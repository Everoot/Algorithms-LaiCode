# 395 Remove Certain Characters (Lai)

Remove given characters in input string, the relative order of other characters should be remained. Return the new string after deletion.

**Assumptions**

- The given input string is not null.
- The characters to be removed is given by another string, it is guaranteed to be not null.

**Examples**

- input = "abcd", t = "ab", delete all instances of 'a' and 'b', the result is "cd".



```java
public class Solution {
  public String remove(String input, String t) {
    // Write your solution here
    char[] array = input.toCharArray();
    Set<Character> set = new HashSet<Character>();
    for (int i = 0; i < t.length(); i++){
      set.add(t.charAt(i));
    }

    int slow = 0;
    for (int fast = 0; fast < array.length; fast++){
      if (set.contains(array[fast])){
        continue;
      }else{
        // not contains
        array[slow] = array[fast];
        slow++;
      }
    }

    return new String(array, 0, slow);
  }
}
/*
  abcd             t =ab   => a      b 
  1. abcd => array
  2. t= ab => set 
  3. check for (array) => set contain(array[i])
  4.         [c   d      c     d]
                       slow
                                  fast 

    return (0, slow)



// TC: O(n)
// SC: O(n)
*/
```

