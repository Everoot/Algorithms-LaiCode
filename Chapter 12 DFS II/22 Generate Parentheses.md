# 22 Generate Parentheses

Given `n` pairs of parentheses, write a function to *generate all combinations of well-formed parentheses*.

 **Example 1:**

```
Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
```

**Example 2:**

```
Input: n = 1
Output: ["()"]
```

**Solution:**

```java
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        if (n == 0){
            return result;
        }

        StringBuilder sb = new StringBuilder();
        int left = 0;
        int right = 0;
        helper(sb, result, n, left, right);
        return result;
    }

    private static void helper(StringBuilder sb, List<String> result, int n, int left, int right){
        // base case
        if (sb.length() == 2 * n){
            result.add(sb.toString());
            return;
        }

        // left
        if (left < right){
            return;
        }

                    
        // add left
        if (left < n){

            sb.append('(');
            helper(sb, result, n, left+1, right);
            sb.deleteCharAt(sb.length() - 1);
        }

        // add right
        if (left > right){
 
            sb.append(')');
            helper(sb, result, n, left, right + 1);
            sb.deleteCharAt(sb.length() - 1);

        }

    }
}


/*
                                ((()))
                                /      \
                              (            )
                           /      \          / \
                        ((          ()     x   x
                       /   \      /   \
                    (((     (()  ()(   x
                    / \     /  \
                   x  ((() (()( (())  


// O(2^(2n))
// O(n)
*/
```



