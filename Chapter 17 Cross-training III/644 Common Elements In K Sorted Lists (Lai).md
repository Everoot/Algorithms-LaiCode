# 644 Common Elements In K Sorted Lists (Lai)

Find all common elements in K sorted lists.

**Assumptions**

The input and its elements are not null, and support fast random access.

There could be duplicate elements in each of the lists.

**Examples**

Input = {{1, 2, 2, 3}, {2, 2, 3, 5}, {2, 2, 4}}, the common elements are {2, 2}.



**Solution:**

```java
public class Solution {
  public List<Integer> commonElementsInKSortedArrays(List<List<Integer>> input) {
    // Write your solution here
    List<Integer> result = input.get(0);
    for (int i = 0; i < input.size(); i++){
      result = helper(result, input.get(i));
    }

    return result;
  }

  public List<Integer> helper(List<Integer> a, List<Integer> b){
    List<Integer> result = new ArrayList<Integer>();
    int i = 0;
    int j = 0;
    while (i < a.size() && j < b.size()){
      int compare = a.get(i).compareTo(b.get(j));
      if (compare == 0){
        result.add(a.get(i));
        i++;
        j++;
      }else if (compare < 0){
        i++;
      }else{
        j++;
      }
    }

    return result;
  }
}


// TC: O(n)
// SC: O(n)
```





