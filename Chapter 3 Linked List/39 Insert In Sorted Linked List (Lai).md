# 39 Insert In Sorted Linked List (Lai)

39. ==Insert In Sorted Linked List==

Insert a value in a sorted linked list.

**Examples**

- L = null, insert 1, return 1 -> null
- L = 1 -> 3 -> 5 -> null, insert 2, return 1 -> 2 -> 3 -> 5 -> null
- L = 1 -> 3 -> 5 -> null, insert 3, return 1 -> 3 -> 3 -> 5 -> null
- L = 2 -> 3 -> null, insert 1, return 1 -> 2 -> 3 -> null



1 -> 3 -> 6 -> 9 -> null              0 or 7 or 10

Case 1: 插入head`head == null || target <= head.value`       , return new N

Case 2: 插中间 `prev.next.value >= target`      return head

Case 3: 插入tail prev.next = null        return head



**Solution 1:** 

Case1: `head == null || head.value >= value`                          insert before the head;

Case2+3: `prev.next == null || prev.next.value >=value`   insert to the following places.

```java
package Class4LinkedList.Assignments;

import java.awt.*;

/*
39. Insert In Sorted Linked List
Insert a value in a sorted linked list.

Examples
L = null, insert 1, return 1 -> null
L = 1 -> 3 -> 5 -> null, insert 2, return 1 -> 2 -> 3 -> 5 -> null
L = 1 -> 3 -> 5 -> null, insert 3, return 1 -> 3 -> 3 -> 5 -> null
L = 2 -> 3 -> null, insert 1, return 1 -> 2 -> 3 -> null
 */
public class Ex5InsertInSortedLinkedList {
    public static class ListNode{
        public int value;
        public ListNode next;
        public ListNode(int value){
            this.value = value;
        }
    }

    public static void main(String[] args){
        int[] array1 = new int[]{};
        int[] array2 = new int[]{1,3,5};
        int[] array3 = new int[]{1};
        int[] array4 = new int[]{2,3};
        int[] array5 = new int[]{-2147483648};
        int[] array6 = new int[]{3,3,3,3,3,3};
        printListNode(insert(buildListNodeFromArrays(array1), 2));
        printListNode(insert(buildListNodeFromArrays(array2),2));
        printListNode(insert(buildListNodeFromArrays(array2),3));
        printListNode(insert(buildListNodeFromArrays(array3),2));
        printListNode(insert(buildListNodeFromArrays(array4),1));
        printListNode(insert(buildListNodeFromArrays(array5),-2147483648));
        printListNode(insert(buildListNodeFromArrays(array6),4));

    }
		// 参考答案
    public static ListNode insert(ListNode head, int value){
        ListNode newNode = new ListNode(value);
        // 1. determine if the inserted node is before head.
        if (head == null || head.value >= value){
            newNode.next = head;
            return newNode;
        }
        // 2. insert the new node to the right position.
        // using the previous node to traverse the linked list
        // the insert position of the new node should be between prev and prev.next
        ListNode prev = head;
        while (prev.next != null && prev.next.value < value){
            prev = prev.next;
        }
        newNode.next = prev.next;
        prev.next = newNode;
        return head;
    }
    /*
    Time Complexity: O(n)
    Space complexity: O(1)
     */

    public static ListNode buildListNodeFromArrays(int[] array){
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
        }else{
            while(head != null){
                System.out.print(head.value + " -> ");
                head = head.next;
            }
            System.out.println("null");
        }
    }
}
```

```
2 -> null
1 -> 2 -> 3 -> 5 -> null
1 -> 3 -> 3 -> 5 -> null
1 -> 2 -> null
1 -> 2 -> 3 -> null
-2147483648 -> -2147483648 -> null
3 -> 3 -> 3 -> 3 -> 3 -> 3 -> 4 -> null

Process finished with exit code 0
```



