 # 34 Reverse Linked List (iterative) (Lai)

Reverse a singly-linked list iteratively.

**Examples**

- L = null, return null
- L = 1 -> null, return 1 -> null
- L = 1 -> 2 -> 3 -> null, return 3 -> 2 -> 1 -> null



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
  public ListNode reverse(ListNode head) {
    // Write your solution here
    // base case 
    if (head == null || head.next == null){
      return head;
    }

    ListNode cur = head;
    ListNode pre = null;
    ListNode next = null;
    while(cur != null){
      next = cur.next;
      cur.next = pre;
      pre = cur;
      cur = next;
    }

    return pre;

  }
}
// TC: O(n)
// SC: O(1)
```

