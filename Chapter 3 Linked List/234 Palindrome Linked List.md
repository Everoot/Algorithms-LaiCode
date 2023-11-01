# 234 Palindrome Linked List

Given the `head` of a singly linked list, return `true` *if it is a* 

*palindrome*

 *or* `false` *otherwise*.



 

**Example 1:**

![img](./234 Palindrome Linked List.assets/pal1linked-list.jpg)

```
Input: head = [1,2,2,1]
Output: true
```

**Example 2:**

![img](./234 Palindrome Linked List.assets/pal2linked-list.jpg)

```
Input: head = [1,2]
Output: false
```





```java
nition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null){
            return true;
        }

        ListNode mid = findmid(head);
        ListNode right = reverse(mid.next);


        /* 1 2
          2 1     1->2

          1 2 3 2 1    1->2
          1 2 mi 5->4
          
          1 2 3 4  5 4 3 2 1
                  mid
         1 2 3 4 
          */ 
              
        while (right != null){
            if (head.val != right.val){
                return false;
            }

            head = head.next;
            right = right.next;
        }

        return true;

        
        
    }

    private static ListNode findmid(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }


        return slow;
    }

    /* 1 2 3 2 1*/
    //     s    f

    private static ListNode reverse(ListNode head){
        ListNode cur = head;
        ListNode prev = null;
        ListNode next = null;
        while(cur != null){
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;              // 注意不是 cur.next 是next 别手滑 卡bug
        }
        return prev;
    }

    /* 1 2 3 2 1 */
}

// TC(O(n))
// SC(O(1))
```

