# 63 All Subsets II (Lai)

Given a set of characters represented by a String, return a list containing all subsets of the characters. Notice that each subset returned will be sorted to remove the sequence.

**Assumptions**

- There could be duplicate characters in the original set.

**Examples**

- Set = "abc", all the subsets are ["", "a", "ab", "abc", "ac", "b", "bc", "c"]
- Set = "abb", all the subsets are ["", "a", "ab", "abb", "b", "bb"]
- Set = "abab", all the subsets are ["", "a", "aa","aab", "aabb", "ab","abb","b", "bb"]
- 
- Set = "", all the subsets are [""]
- Set = null, all the subsets are []



**Solution:**

```java
public class Solution {
  public List<String> subSets(String set) {
    // Write your solution here.
    List<String> result = new ArrayList<String>();
    if (set == null){
      return result;
    }

    char[] setArray = set.toCharArray();
    Arrays.sort(setArray);

    StringBuilder sb = new StringBuilder();

    helper(setArray, 0, sb, result);
    return result;
  }

  private static void helper(char[] setArray, int index, StringBuilder sb, List<String> result){
    //base case
    if (index == setArray.length){
      result.add(sb.toString());
      return;
    }

    // add
    sb.append(setArray[index]);
    helper(setArray, index + 1, sb, result);

    sb.deleteCharAt(sb.length()-1);

    // not add
    while(index < setArray.length - 1 && setArray[index] == setArray[index + 1]){
      index++;
    }

    helper(setArray, index + 1, sb, result);
  }
}
// TC: O(2^n)
// SC: O(n)
```



