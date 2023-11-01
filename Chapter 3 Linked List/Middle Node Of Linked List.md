# Middle Node Of Linked List

LeetCode 876 Middle of the Linked List

Given the `head` of a singly linked list, return *the middle node of the linked list*.

If there are two middle nodes, return **the second middle** node.

 

**Example 1:**

![img](./Middle Node Of Linked List.assets/lc-midlist1.jpg)

```
Input: head = [1,2,3,4,5]
Output: [3,4,5]
Explanation: The middle node of the list is node 3.
```

**Example 2:**

![img](./Middle Node Of Linked List.assets/lc-midlist2.jpg)

```
Input: head = [1,2,3,4,5,6]
Output: [4,5,6]
Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.
```



LaiCode 36 Middle Node of Linked List

Find the middle node of a given linked list.

**Examples**

- L = null, return null
- L = 1 -> null, return 1
- L = 1 -> 2 -> null, return 1
- L = 1 -> 2 -> 3 -> null, return 2
- L = 1 -> 2 -> 3 -> 4 -> null, return 2

 

1 -> 2 -> null

s 

f 



1 -> 2 -> 3 -> null (odd case)

​		s 

​				f

​						f.n



1 -> 2 -> 3 -> 4 -> null (even case)

​		s	  f	

​								f.n.n

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
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;

        while(fast.next != null && fast.next.next != null){ // odd and even case
            slow = slow.next; 
            fast = fast.next.next;
        }
        
        if (fast.next == null){
            return slow;
        } else {
            return slow.next;
        }

   
    }
}

// TC: O(n)
// SC: O(1)
```



