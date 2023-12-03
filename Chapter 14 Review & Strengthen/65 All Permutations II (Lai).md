# 65 All Permutations II (Lai)

Given a string with possible duplicate characters, return a list with all permutations of the characters.

**Examples**

- Set = “abc”, all permutations are [“abc”, “acb”, “bac”, “bca”, “cab”, “cba”]
- Set = "aba", all permutations are ["aab", "aba", "baa"]
- Set = "", all permutations are [""]
- Set = null, all permutations are []



**Solution:**

```java
public class Solution {
  public List<String> permutations(String input) {
    // Write your solution here
    List<String> result = new ArrayList<String>();

    // base case
    if (input == null){
      return result;
    }

    char[] array = input.toCharArray();
    int index = 0;
    helper(array, index, result);
    return result;
  }

  private static void helper(char[] array, int index, List<String> result){
    // base case 
    if (index == array.length){
      result.add(new String(array));
      return;
    }

    Set<Character> valueSet = new HashSet<Character>();
    for (int i = index; i < array.length; i++){
      if (valueSet.contains(array[i])){
        continue;
      }else{
        valueSet.add(array[i]);
        swap(array, index, i);
        helper(array, index + 1, result);
        swap(array, index, i);
      }
    }
  }

  private static void swap(char[] array, int left, int right){
    char rem = array[left];
    array[left] = array[right];
    array[right] = rem; 
  }
}
  /*
                   a b c
               /     |     \
  0          a       b     c 
           / |      / \     | \
  1      ab  ac    ba bc    ca cb
         /    |    |   |     |  |
  2     abc  acb   bac bca cab  cba

  */

// TC: O(n!)
// SC: O(n^2)
```

