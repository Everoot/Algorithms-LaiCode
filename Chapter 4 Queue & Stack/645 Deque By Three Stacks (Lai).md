# 645 Deque By Three Stacks (Lai)

Java: Implement a deque by using three stacks. The queue should provide size(), isEmpty(), offerFirst(), offerLast(), pollFirst(), pollLast(), peekFirst() and peekLast() operations. When the queue is empty, pollFirst(), pollLast(), peekFirst() and peek() should return null.

Python: Implement a deque by using three stacks. The queue should provide size(), isEmpty(), offerFirst(), offerLast(), pollFirst(), pollLast(), peekFirst() and peekLast() operations. When the queue is empty, pollFirst(), pollLast(), peekFirst() and peek() should return None

C++: Implement a deque by using three stacks. The queue should provide size(), isEmpty(), push_front(), push_back(), pop_front(), pop_back(), front() and back() operations. When the queue is empty, front() and back() should return -1.

**Assumptions**

The elements in the queue are all Integers.

size() should return the number of elements buffered in the queue.

isEmpty() should return true if there is no element buffered in the queue, false otherwise.

The amortized time complexity of all operations should be O(1).



```java
public class Solution {
  /*
  
    <-  1 2 | 3 4 5        ->

    <->      1  2] s1   s2: [ 3 4 5         <->
    s1:[ 2 1       ->    1 2 ]s1:
    s2:[ 4 5
    s3(buffer):[ 


  */

  Deque<Integer> s1; 
  Deque<Integer> s2;
  Deque<Integer> s3;
  public Solution() {
    // Write your solution here.
    s1 = new ArrayDeque<Integer>();
    s2 = new ArrayDeque<Integer>();
    s3 = new ArrayDeque<Integer>();
  }
  
  public void offerFirst(int element) {
    s1.offerLast(element);
  }
  
  public void offerLast(int element) {
    s2.offerLast(element);
  }
  
  public Integer pollFirst() {
    if (!s1.isEmpty()){
      return s1.pollLast();
    }else {
      // s1.isEmpty()
      while (!s2.isEmpty()){
        s3.offerLast(s2.pollLast());
      }
      Integer result = s3.pollLast();

      while (!s3.isEmpty()){
        s2.offerLast(s3.pollLast());
      }
      return result;
    }
  }
  
  public Integer pollLast() {
    if (!s2.isEmpty()){
      return s2.pollLast();
    }else{
      // s2.isEmpty()
      while (!s1.isEmpty()){
        s3.offerLast(s1.pollLast());
      }
      Integer result = s3.pollLast();

      while(!s3.isEmpty()){
        s1.offerLast(s3.pollLast());
      }
      return result;
    }
  }
  
  public Integer peekFirst() {
    if (!s1.isEmpty()){
      return s1.peekLast();
    }else{
      // s1.isEmpty()
      while (!s2.isEmpty()){
        s3.offerLast(s2.pollLast());
      }
      Integer result = s3.peekLast();

      while(!s3.isEmpty()){
        s2.offerLast(s3.pollLast());
      }
      return result;
    }
  }
  
  public Integer peekLast() {
    if (!s2.isEmpty()){
      return s2.peekLast();
    }else{
      while(!s1.isEmpty()){
        s3.offerLast(s1.pollLast());
      }
      Integer result = s3.peekLast();
      while(!s3.isEmpty()){
        s1.offerLast(s3.pollLast());
      }
      return result;
    }
  }
  
  public int size() {
    return (s1.size() + s2.size() + s3.size());
  }
  
  public boolean isEmpty() {
    return (s1.isEmpty() && s2.isEmpty() && s3.isEmpty());
  }
}

/*
[pollLast(), isEmpty(), pollFirst(), offerLast()#141, isEmpty(), offerFirst()#240, peekFirst(), pollLast()] 
expected:<[null, true, null, offerLast(141), false, offerFirst(240), 240, 141]> 
but was:<[null, true, null, offerLast(141), true, offerFirst(240), 240, 141]>

*/
```

