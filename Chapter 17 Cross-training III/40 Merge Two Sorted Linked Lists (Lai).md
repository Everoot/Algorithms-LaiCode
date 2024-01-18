# 40 Merge Two Sorted Linked Lists (Lai)

Merge two sorted lists into one large sorted list.

**Examples**

- L1 = 1 -> 4 -> 6 -> null, L2 = 2 -> 5 -> null, merge L1 and L2 to 1 -> 2 -> 4 -> 5 -> 6 -> null
- L1 = null, L2 = 1 -> 2 -> null, merge L1 and L2 to 1 -> 2 -> null
- L1 = null, L2 = null, merge L1 and L2 to null

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
  public ListNode merge(ListNode one, ListNode two) {
    // Write your solution here
    ListNode dummy = new ListNode(-1);
    ListNode cur = dummy;
    while(one != null && two != null){
      if (one.value <= two.value){
        cur.next = one;
        cur = cur.next;
        one = one.next;
      }else{
        cur.next = two;
        cur = cur.next;
        two = two.next;
      }
    }

    if (one != null){
      cur.next = one;
    }
    if (two != null){
      cur.next = two;
    }

    return dummy.next;
  }
}

// TC: O(n)
// SC: O(1)
```

