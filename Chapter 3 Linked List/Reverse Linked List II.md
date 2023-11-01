# Reverse Linked List II

LeetCode 92

Given the `head` of a singly linked list and two integers `left` and `right` where `left <= right`, reverse the nodes of the list from position `left` to position `right`, and return *the reversed list*.

 

**Example 1:**

![img](https://assets.leetcode.com/uploads/2021/02/19/rev2ex2.jpg)

```
Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]
```

**Example 2:**

```
Input: head = [5], left = 1, right = 1
Output: [5]
```

 

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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null){
            return head;
        }
        if (left == right){
            return head;
        }

        ListNode leftPart = null;
        ListNode rightPart = null;
        ListNode start = null;
        ListNode extra = new ListNode(0, head);
        ListNode temp = extra;

        while (temp != null){
            if (left == 1){
                start = temp.next;
                leftPart = temp;
            }
            if (right == 0){
                rightPart = temp.next;
                temp.next = null;
            }
            right--;
            left--;
            temp = temp.next;
        }

        ListNode rev = subreverse(start);
        leftPart.next = rev; // appending left part
        start.next = rightPart; // appending right part
        return extra.next;
        
    }

    public static ListNode subreverse(ListNode head){
        ListNode prev = null;
        ListNode cur = head;
        ListNode next = null;
        while (cur != null){
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;    
        }

        return prev;
    }
}

// TC: O(n)
// SC: O(n)
```

