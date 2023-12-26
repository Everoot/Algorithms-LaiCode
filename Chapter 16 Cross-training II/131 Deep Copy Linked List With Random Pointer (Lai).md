# 131 Deep Copy Linked List With Random Pointer (Lai)

Each of the nodes in the linked list has another pointer pointing to a random node in the list or null. Make a deep copy of the original list.

**Solution**:

```java
/**
 * class RandomListNode {
 *   public int value;
 *   public RandomListNode next;
 *   public RandomListNode random;
 *   public RandomListNode(int value) {
 *     this.value = value;
 *   }
 * }
 */
public class Solution {
  public RandomListNode copy(RandomListNode head) {
    // Write your solution here.
    // base case 
    if (head == null){
      return null;
    }

    RandomListNode dummy = new RandomListNode(0);
    RandomListNode cur = dummy;

    Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();

    while(head != null){
      if (!map.containsKey(head)){
        map.put(head, new RandomListNode(head.value));
      }

      cur.next = map.get(head);

      if (head.random != null){
        if (!map.containsKey(head.random)){
          map.put(head.random, new RandomListNode(head.random.value));
        }

        cur.next.random = map.get(head.random);
      }
      
      head = head.next;
      cur = cur.next;
    }



    return dummy.next;
  }
}

// TC: O(n)
// SC: O(n)
```

