---
tags:
    - Linked List
    - Recursion
---

# 203 Remove Linked List Elements

Given the `head` of a linked list and an integer `val`, remove all the nodes of the linked list that has `Node.val == val`, and return *the new head*.

 

**Example 1:**

![img](./203 Remove Linked List Elements/removelinked-list.jpg)

```
Input: head = [1,2,6,3,4,5,6], val = 6
Output: [1,2,3,4,5]
```

**Example 2:**

```
Input: head = [], val = 1
Output: []
```

**Example 3:**

```
Input: head = [7,7,7,7], val = 7
Output: []
```



**Solution:**

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
    public ListNode removeElements(ListNode head, int val) {
        // base case
        if (head == null){
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode cur = head;
        ListNode pre = dummy;
        while(cur != null){
            ListNode next = cur.next;
            if (cur.val == val){
                pre.next = next;
                cur = next;
            }else{
                pre = cur;
                cur = next;
                
            }

        }


        return dummy.next;
    }
}

// TC: O(n)
// SC: O(1)
```

