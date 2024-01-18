# 23 Merge k Sorted Lists

You are given an array of `k` linked-lists `lists`, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.

**Example 1:**

```
Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6
```

**Example 2:**

```
Input: lists = []
Output: []
```

**Example 3:**

```
Input: lists = [[]]
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
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(11, new MyComparator());


        for(ListNode node : lists){
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
            if (o1.val == o2.val){
                return 0;
            }else{
                if (o1.val < o2.val){
                    return -1;
                }else{
                    return 1;
                }
            }
        }
    }
}

// TC: O(nlogk + n) = O(nlogk)
// SC: O(k)
```

