# 134 Merge K Sorted Lists (Lai)

Merge K sorted lists into one big sorted list in ascending order.

**Assumptions**

- ListOfLists is not null, and none of the lists is null.



**Solution**

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
  public ListNode merge(List<ListNode> listOfLists) {
    // Write your solution here/.
    PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(11, new MyComparator());

    for (ListNode node : listOfLists){
      if (node != null){
        minHeap.offer(node);
      }
    }

    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;

    while(!minHeap.isEmpty()){
      cur.next = minHeap.poll();
      if (cur.next.next != null){
        minHeap.offer(cur.next.next);
      }
      cur = cur.next;
    }

    return dummy.next;
  }

  static class MyComparator implements Comparator<ListNode> {
    public int compare(ListNode o1, ListNode o2){
      if (o1.value == o2.value){
        return 0;
      }else {
        if (o1.value < o2.value){
          return -1;
        }else{
          return 1;
        }
      }
    }
  }
}

// TC: O(nlogk)
// SC: O(k)
```

