# 230 Kth Smallest Element in a BST

Given the `root` of a binary search tree, and an integer `k`, return *the* `kth` *smallest value (**1-indexed**) of all the values of the nodes in the tree*.

 

**Example 1:**

![img](./230 Kth Smallest Element in a BST.assets/kthtree1.jpg)

```
Input: root = [3,1,4,null,2], k = 1
Output: 1
```

**Example 2:**

![img](./230 Kth Smallest Element in a BST.assets/kthtree2.jpg)

```
Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3
```





```java
class Solution {

    // static class MaxComparator implements Comparator<Integer>{
    //     @Override
    //     public int compare(Integer i1, Integer i2){
    //         if (i1.equals(i2)){
    //             return 0;
    //         }else if (i1 > i2){
    //             return -1;
    //         }else{
    //             // i1 < i2
    //             return 1;
    //         }
    //     }
    // }
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        for (int i = 0; i < nums.length; i++){
            maxHeap.offer(nums[i]);
        }

        for (int i = 0; i < k - 1; i++){
            maxHeap.poll();
        }

        return maxHeap.poll();
    }
}

// TC:O(nlogn)

// SC:O(n)


/*

*/
```



```java
class Solution {

    class MaxComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer i1, Integer i2){
            if (i1.equals(i2)){
                return 0;
            }else if (i1 > i2){
                return -1;
            }else{
                return 1;
            }
        }
    }
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new MaxComparator());
        int len = nums.length;
        for (int i = 0; i < len; i++){   // n
            maxHeap.offer(nums[i]);    // logn    
        }

        for (int i = 0; i < k-1; i++){
            maxHeap.poll();                   // logn
        }

        return maxHeap.poll();
        
    }
}

// TC: O(nlogn)
// SC: O(N)
```



```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        int len = nums.length;

        for (int i = 0; i < len; i++){
            minHeap.offer(nums[i]);     // logn
        }

        int times = len - k;
        for (int i = 0; i < times; i++){
            minHeap.poll();
        }


        return minHeap.poll();

    }
}



/*
       min  3   2  1    5  6  4
       max  4   5  6    2 1   3
            3   2  1    5 6   4
            

          k = 2nd larger  => (len - k) + 1 th min

          --> pop (len-k)        result = pop()
             
            1
            / \ 
            3  2
           / \  / 
           5  6 4


*/
```



