# 255 Implement Stack using Queues

Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all the functions of a normal stack (`push`, `top`, `pop`, and `empty`).

Implement the `MyStack` class:

- `void push(int x)` Pushes element x to the top of the stack.
- `int pop()` Removes the element on the top of the stack and returns it.
- `int top()` Returns the element on the top of the stack.
- `boolean empty()` Returns `true` if the stack is empty, `false` otherwise.

**Notes:**

- You must use **only** standard operations of a queue, which means that only `push to back`, `peek/pop from front`, `size` and `is empty` operations are valid.
- Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque (double-ended queue) as long as you use only a queue's standard operations.

 

**Example 1:**

```
Input
["MyStack", "push", "push", "top", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 2, 2, false]

Explanation
MyStack myStack = new MyStack();
myStack.push(1);
myStack.push(2);
myStack.top(); // return 2
myStack.pop(); // return 2
myStack.empty(); // return False
```



```java
class MyStack {

    Deque<Integer> q1;
    Deque<Integer> q2;
    int top;
    /*
    stack: [1 2 3 
    
    last in first out 
    q1 =                 <-  3   <-
    q2 =     <- 1 2     <-
    */

    public MyStack() {
        q1 = new ArrayDeque<Integer>();
        q2 = new ArrayDeque<Integer>();
    }
    
    public void push(int x) {
        q1.offerLast(x);
        top = x;
    }
    
    public int pop() {
        while (q1.size() > 1){
            q2.offerLast(q1.pollFirst());
        }

        int result = q1.pollFirst();
        int res = top;
        while(!q2.isEmpty()){
            res = q2.pollFirst();
            q1.offerLast(res);
        }
        top = res;

        return result;
    }
    
    public int top() {
        return top;
    }
    
    public boolean empty() {
        return (q1.isEmpty() && q2.isEmpty());
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */

// TC: O(n)
// SC: O(1)
```

