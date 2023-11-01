# 254 Factor Combinations

Numbers can be regarded as the product of their factors.

- For example, `8 = 2 x 2 x 2 = 2 x 4`.

Given an integer `n`, return *all possible combinations of its factors*. You may return the answer in **any order**.

**Note** that the factors should be in the range `[2, n - 1]`.

**Example 1:**

```
Input: n = 1
Output: []
```

**Example 2:**

```
Input: n = 12
Output: [[2,6],[3,4],[2,2,3]]
```

**Example 3:**

```
Input: n = 37
Output: []
```



**Solution:** 

```java
class Solution {
    public List<List<Integer>> getFactors(int n) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (n <= 1){
            return result;
        }

        List<Integer> subResult = new ArrayList<Integer>();
        // we can not sure the length so we use List
        List<Integer> factors = getFactor(n);

        helper(0, n, factors, subResult, result);
        return result;
    }


    private static void helper(int index, int n, List<Integer> factors, List<Integer> subResult, List<List<Integer>> result){
        // base case;
        if (index == factors.size()){
            if (n == 1){ //能够整除数
                result.add(new ArrayList<Integer>(subResult));
            }
            return;
        }

        // 不拿
        helper(index+1, n, factors, subResult, result);

        // 要拿几个?
        int factor = factors.get(index);
        int count = 0; // 当前层我加了几个factor
      	// int size = subResult.size();
        while(n % factor == 0){
            subResult.add(factor);
            n = n / factor;
            count = count +1;
            helper(index+1, n, factors, subResult, result);
        }

        // truncate list back to it was before adding anything
        // 加了多少, 原样退回去
        for (int i = 0; i < count; i++){
            subResult.remove(subResult.size() - 1);
        }
      // Or truncate list: cur.subList(size, cur.size()).clear();
			// https://stackoverflow.com/questions/1279476/truncate-a-list-to-a-given-number-of-elements
    }

    private static List<Integer> getFactor(int n){
        List<Integer> factors = new ArrayList<>();
        for (int i = n / 2; i > 1; i--){
            if (n % i == 0){
                factors.add(i);
            }
        }
        return factors;
    }
}

// TC: branch^level
// branch: a * a * a * a = n -> a^k = n -> k = logn 当a = 2 分最多的层数
// level: num of factor
// TC: O(logn ^ # of factors)

// 我想拿n个m -> 我得吃n个m:
// 吃
/*
for(int i = 0; i < n; i++){
  curr.add(m)
}
DFS()
  
// 吐
for (int i = 0; i < n; i++){
  curr.remove(cur.size() -1)
}
*/
```





