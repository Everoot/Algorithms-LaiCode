# 143 Reorder List

You are given the head of a singly linked-list. The list can be represented as:

```
L0 → L1 → … → Ln - 1 → Ln
```

*Reorder the list to be on the following form:*

```
L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
```

You may not modify the values in the list's nodes. Only nodes themselves may be changed.

 

**Example 1:**

<img src="https://assets.leetcode.com/uploads/2021/03/04/reorder1linked-list.jpg" alt="img" style="zoom: 50%;" />

```
Input: head = [1,2,3,4]
Output: [1,4,2,3]
```



**Example 2:**

<img src="https://assets.leetcode.com/uploads/2021/03/09/reorder2-linked-list.jpg" alt="img" style="zoom:50%;" />

```
Input: head = [1,2,3,4,5]
Output: [1,5,2,4,3]
```



Q5: N1 -> N2 ->N3 -> N4 -> N5 -> N6 -> ... ->Nn -> null

(convert to) (N1 -> Nn) -> (N2->N(n-1)) -> ...



N1 -> N2 -> N3             N4->N5->N6

cur1			  mid 



N6      N5      N4

cur2



==**Solution:**==

==Step1: find middle node==

==Step2: reverse the 2nd half==

==Step3: **dummyhead** to append n1 n2 repeatedly==

```java
public ListNode reorder(ListNode head){
  if (head == null || head.next == null){
    return head;
  }
  
  // 1. find the middle node
  ListNode mid = middleNode(head);
  ListNode one = head;
  ListNode two = mid.next;
  // de-link the second half from the list.
  mid.next = null;
  // 2. reverse the second half
  // 3. merge the two halves
  return merge(one, reverse(two));
}

private ListNode middleNode(ListNode head){
  ListNode slow = head;
  ListNode fast = head;
  while(fast.next != null && fast.next.next != null){
    slow = slow.next;
    fast = fast.next.next;
  }
  return slow;
}

private ListNode reverse(ListNode head){
  if (head == null || head.next == null){
    return head;
  }
  ListNode prev = null;
  while (head != null){
    ListNode next = head.next;
    head.next = prev;
    prev = head;
    head = next;
  }
  return prev;
}


private ListNode merge(ListNode one, ListNode two){
  ListNode dummy = new ListNode(0);
  ListNode cur = dummy;
  while (one != null && two != null){
    cur.next = one;
    one = one.next;
    
    cur.next.next = two;
    two = two.next;
    cur = cur.next.next;
  }
  if (one != null){
    cur.next = one;
  }else{
    cur.next = two;
  }
  return dummy.next;
  
}
```



TC: O(n)

SC: O(1)

