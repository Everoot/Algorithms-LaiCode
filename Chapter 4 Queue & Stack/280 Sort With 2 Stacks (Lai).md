# 280 Sort With 2 Stacks (Lai)

Given an array that is initially stored in one stack, sort it with one additional stacks (total 2 stacks).

After sorting the original stack should contain the sorted integers and from top to bottom the integers are sorted in ascending order.

**Assumptions:**

- The given stack is not null.
- There can be duplicated numbers in the give stack.

**Requirements:**

- No additional memory, time complexity = O(n ^ 2).



```java
public class Solution {
  public void sort(LinkedList<Integer> s1) {
    LinkedList<Integer> s2 = new LinkedList<Integer>();
    // Write your solution here.
    // base case
    if (s1.size() <= 1){
      return;
    }

    sort2(s1, s2);
    return;
  }

  public void sort2(LinkedList<Integer> s1, LinkedList<Integer> s2){
    int size = s1.size();
    int count = 0;

    while (count < size){
      int min = s1.peekLast();
      int addcount = 1;
      int remain = size - count;
      while (!s1.isEmpty() &&remain >= 0){
        int i = s1.pollLast();
        if (i < min){
          min = i;
        }

        s2.offer(i);
        remain--;
      }

      s1.offer(min);
      count = count + 1;

      while(!s2.isEmpty()){
        int j = s2.pollLast();
        if (addcount == 1 && j == min){
          addcount = addcount -1;
          continue;
        }
        s1.offer(j);
      }
    }
  }

}


/*
  min = 3; 
  s1 (intput)/(result) [ 1 | 3 5 6
       
  s2  (buffer) [ 6 5 3

  stack: LIFO 


*/
```

