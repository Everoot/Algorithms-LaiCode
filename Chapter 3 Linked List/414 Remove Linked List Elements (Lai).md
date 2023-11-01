# 414 Remove Linked List Elements (Lai)

Easy
Remove all elements from a linked list of integers that have value val.

Example
Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
Return: 1 --> 2 --> 3 --> 4 --> 5

```java
/**
 * class ListNode {
 *   public int value;
 *   public ListNode next;
 *   public ListNode(int value) {
 *     this.value = value;
 *     next = null;
 *   }
 * }
 */
public class Solution {
  public ListNode removeElements(ListNode head, int val) {
    // Write your solution here
    // base case
    if (head == null){
      return head;
    }

    ListNode dummy = new ListNode(-1);
    ListNode cur = dummy;
    ListNode prev = null;
    ListNode next = null;

    while (head != null){
      next = head.next;
      head.next = null;
      if (head.value != val){
        cur.next = head;
        cur = cur.next;
      }
      head = next;
    }
    return dummy.next;
  }
}

// TC O(n)
// SC O(1)

/* 
    1 -> 2-> null     6   3   4 -> 5 -> 6       val = 6
         pr               h  n 


    dummy: -1 ->  1 -> 2  -> 3
                             cur
      
    

*/ 

```

