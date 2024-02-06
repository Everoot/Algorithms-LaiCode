#  414 Remove Linked List Elements (Lai)

Remove all elements from a linked list of integers that have value **val**.

**Example**
***Given:*** 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, **val** = 6
**Return:** 1 --> 2 --> 3 --> 4 --> 5



**Solution:**

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

    ListNode cur = head;
    dummy.next = cur;
    ListNode prev = dummy;

    while(cur != null){
      ListNode next = cur.next;
      if (cur.value == val){
        prev.next = next;
        cur = next;
      }else{
        prev = cur;
        cur = next;
      }
    }

    return dummy.next;
  }
}


// TC: O(n)
// SC: O(1)

```

