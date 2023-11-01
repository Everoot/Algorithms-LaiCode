# 179 All Valid Permutations Of Parentheses II (Lai)

Get all valid permutations of l pairs of (), m pairs of <> and n pairs of {}.

**Assumptions**

- l, m, n >= 0
- l + m + n > 0

**Examples**

l = 1, m = 1, n = 0, all the valid permutations are ["()<>", "(<>)", "<()>", "<>()"]

**Solution:**

<img src="./179 All Valid Permutations Of Parentheses II (Lai).assets/Screenshot 2023-08-26 at 23.30.39.png" alt="Screenshot 2023-08-26 at 23.30.39" style="zoom: 33%;" />

```java
public class Solution {
  private static final char[] PS = new char[]{'(', ')', '<', '>', '{', '}'};
  public List<String> validParentheses(int l, int m, int n) {
    // Write your solution here
    List<String> result = new ArrayList<String>();
    StringBuilder sb = new StringBuilder();
    Deque<Character> stack = new ArrayDeque<Character>();
    int[] count = new int[]{l, l, m, m, n, n};
    int len = 2 * (l + m + n);
    helper(sb, len, result, count, stack);
    return result;
  }

  public static void helper(StringBuilder sb, int len, List<String> result, int[] count, Deque<Character> stack){
    // base case 
    if (sb.length() == len){
      result.add(sb.toString());
      return;
    }

    for (int i = 0; i < count.length; i++){ // brach
      if (i % 2 == 0){// left 
        if (count[i] > 0){
          sb.append(PS[i]);
          stack.offerLast(PS[i]);
          count[i] = count[i] - 1;
          helper(sb, len, result, count, stack);

          sb.deleteCharAt(sb.length() - 1);
          stack.pollLast();
          count[i] = count[i] + 1;
        }
      }else {
        // right
        if (!stack.isEmpty() && stack.peekLast() == PS[i-1]){
          sb.append(PS[i]);
          stack.pollLast();
          count[i] = count[i] - 1;
          helper(sb, len, result, count, stack);

          sb.deleteCharAt(sb.length() - 1);
          stack.offerLast(PS[i-1]);
          count[i] = count[i] + 1;
        }

      }
    }
  }
}


/*
               ()<>{}
               llmmnn
               /  \ \ \  \ \
              (   ) < >   { }
           / \\\\   

branch represent: whether which kind of                 
level: (l + m + n) * 2;

([)]
)
: ) ]
*/ 
// TC: O(2n * branch ^ level) = O( 2n ^ 2n) = n^n
// SC: O(n)
```

