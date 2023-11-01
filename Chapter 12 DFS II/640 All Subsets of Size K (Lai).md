# 640 All Subsets of Size K (Lai)

Given a set of characters represented by a String, return a list containing all subsets of the characters whose size is K.

**Assumptions**

There are no duplicate characters in the original set.

**Examples**

Set = "abc", K = 2, all the subsets are [“ab”, “ac”, “bc”].

Set = "", K = 0, all the subsets are [""].

Set = "", K = 1, all the subsets are [].



**Solution**:

```java
public class Solution {
  public List<String> subSetsOfSizeK(String set, int k) {
    // Write your solution here
    // base case 
    List<String> result = new ArrayList<String>();
    if (set == null){
      return result;
    }

    int index = 0;
    char[] setArray = set.toCharArray();
    StringBuilder sb = new StringBuilder();

    helper(setArray, index, sb, result, k);
    return result;
  }

  private static void helper(char[] setArray, int index, StringBuilder sb, List<String> result, int k){
    // check 
    if (sb.length() == k){
      result.add(sb.toString());
      return;
    }

    if (index == setArray.length){
      return;
    }

    // add
    sb.append(setArray[index]);
    helper(setArray, index + 1, sb, result, k);

    sb.deleteCharAt(sb.length() - 1);
    // not add
    helper(setArray, index + 1, sb, result, k);
  }

}


/*
// TC: O(n * branch ^level) = O(n* 2^n)
// SC: O(n)
                      abc
                    /     \
   index 0        a        -
                /   \     /  \
   index 1     ab   a    b    -
                   / \   / \  / \
   index 2        ac a bc b  c -
                     /\
   index 3    


*/
```

