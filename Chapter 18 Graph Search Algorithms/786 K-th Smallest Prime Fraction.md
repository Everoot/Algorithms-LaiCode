# 786 K-th Smallest Prime Fraction

You are given a sorted integer array `arr` containing `1` and **prime** numbers, where all the integers of `arr` are unique. You are also given an integer `k`.

For every `i` and `j` where `0 <= i < j < arr.length`, we consider the fraction `arr[i] / arr[j]`.

Return *the* `kth` *smallest fraction considered*. Return your answer as an array of integers of size `2`, where `answer[0] == arr[i]` and `answer[1] == arr[j]`.

 

**Example 1:**

```
Input: arr = [1,2,3,5], k = 3
Output: [2,5]
Explanation: The fractions to be considered in sorted order are:
1/5, 1/3, 2/5, 1/2, 3/5, and 2/3.
The third fraction is 2/5.
```

**Example 2:**

```
Input: arr = [1,7], k = 1
Output: [1,7]
```



**Solution:**

```java
class Solution {
    static class Cell{
        int i;
        int j;
        double value;
        Cell(int i, int j, double value){
            this.i = i;
            this.j = j;
            this.value = value;
        }
    }

    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(new Comparator<Cell>(){
            @Override
            public int compare(Cell c1, Cell c2){
                if (c1.value == c2.value){
                    return 0;
                }else if (c1.value < c2.value){
                    return -1;
                }else{
                    return 1;
                }
            }
        });

        // Initialize the min heap with the first element in each row
        for (int i = 0; i < n-1; i++){
            for (int j = i + 1; j < n; j++){
                minHeap.offer(new Cell(i, j, (double) arr[i] / arr[j]));
            }

        }
        /*
        1, 4, 1/5
        2, 4, 2/5
        3, 4, 3/5
        */

        Cell c = null;
        while(k > 0){
            c = minHeap.poll(); // 1, 4, 1/5
            k--;
        }


        return new int[]{arr[c.i], arr[c.j]};
        
    }
}

// TC: O(n^2)
// SC: O(n)
```



```java
class Solution {
    static class Cell{
        int i;
        int j;
        double value;
        Cell(int i, int j, double value){
            this.i = i;
            this.j = j;
            this.value = value;
        }
    }

    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(new Comparator<Cell>(){
            @Override
            public int compare(Cell c1, Cell c2){
                if (c1.value == c2.value){
                    return 0;
                }else if (c1.value < c2.value){
                    return -1;
                }else{
                    return 1;
                }
            }
        });

        // Initialize the min heap with the first element in each row
        for (int i = 0; i < n - 1; i++){
            minHeap.offer(new Cell(i, n - 1, (double) arr[i] / arr[n - 1]));
        }

        Cell c = null;
        while(k > 0){
            c = minHeap.poll();
            if (c.j - 1 > c.i){
                minHeap.offer(new Cell(c.i, c.j - 1, (double) arr[c.i] / arr[c.j -1]));
            }
            
            k--;
        }

        return new int[]{arr[c.i], arr[c.j]};
        
    }
}

// TC: O(n + klogn)
// SC: O(n)
```



