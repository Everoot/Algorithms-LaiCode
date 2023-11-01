# 21 Merge Two Sorted Lists

LeetCode 21

You are given the heads of two sorted linked lists `list1` and `list2`.

Merge the two lists in a one **sorted** list. The list should be made by splicing together the nodes of the first two lists.

Return *the head of the merged linked list*.

 

**Example 1:**

![img](./21 Merge Two Sorted Lists.assets/merge_ex1.jpg)

```
Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]
```

**Example 2:**

```
Input: list1 = [], list2 = []
Output: []
```

**Example 3:**

```
Input: list1 = [], list2 = [0]
Output: [0]
```



LaiCode 40 Merge Two Sorted Linked Lists

Merge two sorted lists into one large sorted list.

**Examples**

- L1 = 1 -> 4 -> 6 -> null, L2 = 2 -> 5 -> null, merge L1 and L2 to 1 -> 2 -> 4 -> 5 -> 6 -> null
- L1 = null, L2 = 1 -> 2 -> null, merge L1 and L2 to 1 -> 2 -> null
- L1 = null, L2 = null, merge L1 and L2 to null



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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // base case
        if (list1 == null && list2 == null){
            return list1;
        }else if (list1 != null && list2 == null){
            return list1;
        }else if (list1 == null && list2 != null){
            return list2;
        }

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        ListNode h1 = list1;
        ListNode h2 = list2;
        while(h1 != null && h2 != null){
            if (h1.val <= h2.val){
                cur.next = h1;
                h1 = h1.next;
            }else { // h1.val > h2.val
                cur.next = h2;
                h2 = h2.next;
            }
            cur = cur.next;
        }

        if (h1 != null){
            cur.next = h1;
        }

        if (h2 != null){
            cur.next = h2;
        }

        return dummy.next;
        
        
    }
}

// TC: O(n)
// SC: O(1)

/*  
    
    [1,2,4]
          h1 null 

    [1,3,4]
       h2 

    d 1  1 2 3 4 -> c2
    return d.next; 

     1,1,2,3,4,4

*/
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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null){
            return list2;
        }
        
        if (list2 == null){
            return list1;
        }

        int min = list1.val;
        if (list1.val > list2.val){
            min = list2.val;
            list2 = list2.next;
        }else{
            list1 = list1.next;
        }  
      
      // if use dummy node can do not check which one go first

        ListNode head = new ListNode(min);
        ListNode result = head;
        while (list1 != null && list2 != null){
            if (list1.val < list2.val){
                head.next = list1;
                head = head.next;
                list1 = list1.next;
            } else {
                head.next = list2;
                head = head.next;
                list2 = list2.next;
            }
        }

        if (list1 != null){
            head.next = list1;
        }

        if (list2 != null){
            head.next = list2;
        }
        return result;
    }
}
// TC: O(n)
// SC: O(n)
```



### Dummy head

best practice: 只有在真正有好处的时候使用, 不要无脑使用

It is not a must, but can make your life much easier, there are several  suitable conditions using a dummy will be very handy

* the head could be changed when solving the problem (头会变)
* not sure yet which node will be head when constructing the list (无法决定头)



--> 结果 直接 return dummy.next



N1 -> N2 -> N5 -> NULL

h1

N1.5 -> N3 -> N6 -> NULL

h2



use two points and do 谁小移谁



Solution: N1->N1.5-> N2->N3 ....

dummyHead -> N1 -> N1.5 -> N2...

​						   cur



Merge two sorted lists into one large sorted list.

**Examples**

- L1 = 1 -> 4 -> 6 -> null, L2 = 2 -> 5 -> null, merge L1 and L2 to 1 -> 2 -> 4 -> 5 -> 6 -> null
- L1 = null, L2 = 1 -> 2 -> null, merge L1 and L2 to 1 -> 2 -> null
- L1 = null, L2 = null, merge L1 and L2 to null

```java
package Class4LinkedList.Assignments;
/*
40. Merge Two Sorted Linked Lists
Easy
Merge two sorted lists into one large sorted list.

Examples

L1 = 1 -> 4 -> 6 -> null, L2 = 2 -> 5 -> null, merge L1 and L2 to 1 -> 2 -> 4 -> 5 -> 6 -> null
L1 = null, L2 = 1 -> 2 -> null, merge L1 and L2 to 1 -> 2 -> null
L1 = null, L2 = null, merge L1 and L2 to null
 */

public class Ex6MergeTwoSortedLinkedLists {
    public static class ListNode{
        public int value;
        public ListNode next;
        public ListNode(int value){
            this.value = value;
            next = null;
        }
    }

    public static void main(String[] args){
        int[] array1 = new int[]{1, 4, 6};
        int[] array2 = new int[]{2, 5};
        int[] array3 = new int[]{};
        int[] array4 = new int[]{1,2};
        int[] array5 = new int[]{};
        int[] array6 = new int[]{};
        ListNode node1 = buildFromArrays(array1);
        ListNode node2 = buildFromArrays(array2);
        ListNode node3 = buildFromArrays(array3);
        ListNode node4 = buildFromArrays(array4);
        ListNode node5 = buildFromArrays(array5);
        ListNode node6 = buildFromArrays(array6);
        ListNode merge1 = mergeTwoSortedLinkedLists(node1,node2);
        printListNode(merge1);
        ListNode merge2 = mergeTwoSortedLinkedLists(node3, node4);
        printListNode(merge2);
        ListNode merge3 = mergeTwoSortedLinkedLists(node5, node6);
        printListNode(merge3);
    }

    public static ListNode mergeTwoSortedLinkedLists(ListNode head1, ListNode head2){
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (head1 != null && head2 != null){
            if (head1.value <= head2.value){
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }
        // link the remaining possible nodes.
        if (head1 != null){
            cur.next = head1;
        } else {
            cur.next = head2;
        }
        return dummy.next;
    }
        /*
        ListNode head = new ListNode(0);
        if ((head1 == null && head2 == null) || (head1 != null && head2 == null)){
            return head1;
        }
        if ((head1 == null) && (head2 != null)){
            return head2;
        }
        if (head1.value < head2.value){
            head = head1;
            head1 = head1.next;
        }
        ListNode cur = head;
        while(head1 != null && head2 !=null){
            if (head1.value <= head2.value){
                cur.next = head1;
                head1 = head1.next;
            } else{
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }
        while(head1 != null){
            cur.next = head1;
            head1 = head1.next;
        }
        while(head2 != null){
            cur.next = head2;
            head2 = head2.next;
        }
        return head;
    }

         */

    public static ListNode buildFromArrays(int[] array){
        if (array == null || array.length == 0){
            return null;
        }
        ListNode head = new ListNode(array[0]);
        ListNode cur = head;
        for (int i = 1; i < array.length; i++){
            cur.next = new ListNode(array[i]);
            cur = cur.next;
        }
        return head;
    }

    public static void printListNode(ListNode head){
        if (head == null){
            System.out.println("null");
        } else{
            ListNode cur = head;
            while(cur != null){
                System.out.print(cur.value + "->");
                cur = cur.next;
            }
            System.out.println("null");
        }
    }
}
```

```
1->2->4->5->6->null
1->2->null
null

Process finished with exit code 0
```

