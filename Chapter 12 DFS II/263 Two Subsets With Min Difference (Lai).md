# 263 Two Subsets With Min Difference (Lai)

Given a set of n integers, divide the set in two subsets of n/2 sizes each such that the difference of the sum of two subsets is as minimum as possible.

Return the minimum difference(**absolute value**).

**Assumptions:**

- The given integer array is not null and it has length of >= 2.

**Examples:**

- {1, 3, 2} can be divided into {1, 2} and {3}, the minimum difference is 0

 

**Solution:**

```java
public class Solution {
  public int minDifference(int[] array) {
    // Write your solution here
    int n = array.length;
    // 1. find all subsets with size == n/2
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> cur = new ArrayList<>();
    getSubsets(array, 0, cur, result);
    //2. compare two subsets with required size and find the min deference 
    int arraySum = 0;
    for (int i = 0; i < array.length; i++){
      arraySum = arraySum + array[i];
    }

    int globalMin = Integer.MAX_VALUE;
    for (int i = 0; i < result.size(); i++){
      int subSetSum = 0;
      List<Integer> subSet = result.get(i);
      for (int j = 0; j < subSet.size(); j++){
        subSetSum = subSet.get(j) + subSetSum;
      }
      globalMin = Math.min(globalMin, Math.abs((arraySum - subSetSum) - subSetSum));
    }
    return globalMin;
  }

  private void getSubsets(int[] array, int index, List<Integer> cur, List<List<Integer>> result){
    if (index == array.length){
      return;
    }  
    if (cur.size() == array.length/2){
      result.add(new ArrayList(cur));
      return;
    }

    // add
    cur.add(array[index]);
    getSubsets(array, index + 1, cur, result);
    cur.remove(cur.size() - 1);

    // not add
    getSubsets(array, index + 1, cur, result);
  }
}

```

