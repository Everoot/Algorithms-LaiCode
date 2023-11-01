# 232 Implement Queue using Stacks

Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (`push`, `peek`, `pop`, and `empty`).

Implement the `MyQueue` class:

- `void push(int x)` Pushes element x to the back of the queue.
- `int pop()` Removes the element from the front of the queue and returns it.
- `int peek()` Returns the element at the front of the queue.
- `boolean empty()` Returns `true` if the queue is empty, `false` otherwise.

**Notes:**

- You must use **only** standard operations of a stack, which means only `push to top`, `peek/pop from top`, `size`, and `is empty` operations are valid.
- Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.

 

**Example 1:**

```
Input
["MyQueue", "push", "push", "peek", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 1, 1, false]

Explanation
MyQueue myQueue = new MyQueue();
myQueue.push(1); // queue is: [1]
myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
myQueue.peek(); // return 1
myQueue.pop(); // return 1, queue is [2]
myQueue.empty(); // return false
```



```java
class MyQueue {
    // first in frist Out
    // s1 []
    //          --
    //         <- -   
    //          --
    //

    // Queue     <-  1 2 3 4 5  <-    
    // s1 (push) [ 1 2 3 4
     //s2 (pop) [ 
         // when empty s1 ->s2
         // s2 [ 4 3 2 1
         //            |   

    Deque<Integer> s1 = new ArrayDeque<>();
    Deque<Integer> s2 = new ArrayDeque<>();

    // Initialzie your data structure here
    public MyQueue() {

    }
    
    // Push element x to the back of queue
    public void push(int x) {
        s1.push(x);
        
    }
    //O(1)
    
    // Removes the element from  in front of queue and return that element
    public int pop() {
        if(s2.isEmpty()){
            while(!s1.isEmpty()){
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }
    // amotize O(1)
    
    // Get the front element
    public int peek() {
        if(s2.isEmpty()){
            while(!s1.isEmpty()){
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }
    // amotize O(1)


    // Return whether the queue is empty
    public boolean empty() {
        return s2.isEmpty() && s1.isEmpty();
    }
    // O(1)
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
```





Line 16: error: Deque is abstract; cannot be instantiated [in MyQueue.java]    Deque<Integer> s1 = new Deque<>();

## Stacks

A stack is a data structure similar to a list, except operations are only performed on the element that is most recently added. In other words, it is first-in last-out (FILO). In particular, stacks use the following routines.

- `push(a)`

  Pushes element `a` onto the top of the stack.

- `peek()`

  Returns the element on the top of the stack. Throws an exception if the stack is empty.

- `pop()`

  Removes the top element from the stack and returns that element. Throws an exception if the stack is empty.

Any good implementation of a stack would be designed to allow these routines to be as efficient as possible. We present one such efficient way: using an array implementation.

https://docs.oracle.com/javase/8/docs/api/java/util/Stack.html