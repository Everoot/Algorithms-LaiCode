# Reverse Linked List (Iterative)

LaiCode 34

Reverse a singly-linked list iteratively.

**Examples**

- L = null, return null
- L = 1 -> null, return 1 -> null
- L = 1 -> 2 -> 3 -> null, return 3 -> 2 -> 1 -> null



LeetCode 206

Given the `head` of a singly linked list, reverse the list, and return *the reversed list*.



**Example 1:**

![img](./Reverse Linked List (Iterative).assets/rev1ex1.jpg)

```
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
```

**Example 2:**

![img](./Reverse Linked List (Iterative).assets/rev1ex2.jpg)

```
Input: head = [1,2]
Output: [2,1]
```

**Example 3:**

```
Input: head = []
Output: []
```



N1 -> N2 -> N3 -> N4 -> N5



1:           N1 -> N2 -> N3 -> N4 -> N5

​	   p 	 c        n

2:         <- N1    N2 -> N3 -> N4 -> N5

​		p       c        n

3:             p         c,n

...



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
        ListNode cur = head;
        ListNode prev = null;
        ListNode next = null; // 可以放在循环内
        //  <-1  2 <- 3.       cn
        //   p   cn   p
        while(cur != null){ // Why not cur.next == null?
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev; 




    }
}

// TC: O(n)
// SC: O(1)
```

Do we really need to use a cur variable? No, we will pass the new head back to the caller.

但一般不要动head
