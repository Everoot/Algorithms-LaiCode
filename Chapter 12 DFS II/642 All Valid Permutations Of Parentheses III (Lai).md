# 642 All Valid Permutations Of Parentheses III (Lai)

Get all valid permutations of l pairs of (), m pairs of <> and n pairs of {}, subject to the priority restriction: {} higher than <> higher than ().

**Assumptions**

  l, m, n >= 0

  l + m + n >= 0

**Examples**

  l = 1, m = 1, n = 0, all the valid permutations are ["()<>", "<()>", "<>()"].

  l = 2, m = 0, n = 1, all the valid permutations are [“()(){}”, “(){()}”, “(){}()”, “{()()}”, “{()}()”, “{}()()”].

**Solution:**

```java
public class Solution {
  private static final char[] PS = new char[]{'(', ')', '<', '>', '{', '}'};
  public List<String> validParenthesesIII(int l, int m, int n) {
    // Write your solution here
    List<String> result = new ArrayList<String>();
    int len = 2 * (l + m + n);
    StringBuilder sb = new StringBuilder();
    int[] count = new int[]{l, l, m, m, n, n};
    Deque<Character> stack = new ArrayDeque<Character>();
    Map<Character, Integer> priority = new HashMap<>();
    priority.put('(', 0);
    priority.put('<', 1);
    priority.put('{', 2);
    // { () }    x ({})      addcurleft < left.priority

    helper(sb, len, count, stack, priority, result);
    return result;
  }

  private static void helper(StringBuilder sb, int len, int[] count, Deque<Character> stack, Map<Character, Integer> priority,
   List<String> result){
    // base case
    if (sb.length() == len){
      result.add(sb.toString());
      return;
    }

    for (int i = 0; i < count.length; i++){
      if (i % 2 == 0){
        // left
        if (count[i] > 0 && (stack.isEmpty() || priority.get(PS[i])< priority.get(stack.peekLast()))){
          sb.append(PS[i]);
          stack.offerLast(PS[i]);
          count[i] = count[i] - 1;
          helper(sb, len, count, stack, priority, result);

          sb.deleteCharAt(sb.length() - 1);
          stack.pollLast();
          count[i] = count[i] + 1;
        } 
      }else{
        if (!stack.isEmpty() && stack.peekLast() == PS[i-1]){
          sb.append(PS[i]);
          stack.pollLast();
          count[i] = count[i] - 1;
          helper(sb, len, count, stack, priority, result);

          sb.deleteCharAt(sb.length() - 1);
          stack.offerLast(PS[i - 1]);
          count[i] = count[i] + 1;
        }
      }
    }
  }
}
// TC: O(2n^ 2n) = O(n^n)
// SC: O(n)

```

括号问题:

1. 就一种permutation
2. n 种没有优先级 -> 本质是对加右括号条件扩展 考点: Stack
3. 优先级 -> 本质是对加左括号条件扩展 考点: map + stack

