# Reverse Linked List (Recursion)

LeetCode 206

Given the `head` of a singly linked list, reverse the list, and return *the reversed list*.



**Example 1:**

![img](./Reverse Linked List (Recursion).assets/rev1ex1.jpg)

```
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
```

**Example 2:**

![img](./Reverse Linked List (Recursion).assets/rev1ex2.jpg)

```
Input: head = [1,2]
Output: [2,1]
```

**Example 3:**

```
Input: head = []
Output: []
```



LaiCode Reverse Linked List (recursive) 653

Reverse a singly-linked list recursively.

**Examples**

- L = null, return null
- L = 1 -> null, return 1 -> null
- L = 1 -> 2 -> 3 -> null, return 3 -> 2 -> 1 -> null

 

Recursion 需要掌握的知识点

1) 表象上: function calls itself

2) 实质上: Boil down a big problem to smaller ones (size n depends on size $n-1$, or $n-2$ or $\dots \frac{n}{2}$)

3) Implementation上:

   a) Base case: smallest problem to solve

   b) Recursive rule: how to make the problem smaller (If we can resolve the same problem but with a smaller size, then what is left to do for the current problem size n)



reverse (n1 -> n2 -> n3 -> n4 -> n5) (信任子问题)

n1 <- reverse(n2->n3->n4->n5) 

n1<-n2 <- reverse(n3->n4->n5) 

n1<-n2 <- n3 <- reverse(n4->n5)

n1<-n2 <- n3 <- n4 <- reverse(n5)

 

Example:

Prove the following formula

$0+1+2+3+4+\dots + n = \frac{n(n+1)}{2}$

Problem: prove $0+1+2+\dots + n = \frac{n(n+1)}{2}$

数学归纳法: 

1. n = 0
2. Assume n =k
3. n = k +1

Assume P(K) = k(k+1)/2 holds (for some unspecified value of k). It must then be shown that P(k + 1) holds, that is: 

$\frac{k(k+1)}{2} + (k+1) = \frac{k(k+1) + 2(k+1)}{2} = \frac{(k+1)(k+2)}{2}=\frac{(k+1)(k+1)+1}{2}$

Did you let your thoughts follow the path why 0 + 1+...+k = k(k+1)/2?

No, you assumed that.



recursion和数学归纳法的原理类似:

1. 在已知base case的情况下
2. Assume 下一层会返回正确的结果
3. 我们只需要关心当前层的逻辑



Reverse linked list 的数学归纳法原理:

1. base case: 0 node (head == null) or 1 node (head.next == null), return head.

2. Assume reverse (head.next) will give me the new Head (last node) of reversed sub-list (starting from head next).

3. What should we do at the current level?

   a. reverse

   b. break cross-pointing (to avoid infinite loop)

   c. return

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null){
            return head;  // base case 
        }

        //1 -> 2 <- 3 <- 4
        //h   h.n

        ListNode newHead = reverseList(head.next); // 反转完了
        head.next.next = head;
        head.next = null; // 原来的那个head 成为尾巴了
        return newHead;
    }
}
```

TC: O(n)

SC: O(n) -- n level on call stack and O(1) on heap



Discussion: 

When to use iterative way vs Recursive way

*  In real work environment, consider using iterative way first to avoid call stack overflow
*  In interview environment
  * For tree related problems, the recursion way is more preferred (usually easier).
  * For other problems, you can evaluate the real problem
    * If you can both methods, and their time complexities are the same, then you can use either way.
    * If their time complexity is different. E.g Fibonacci, recursion vs. DP (iterative way). Of course, you should pick the one with smaller time complexity.





