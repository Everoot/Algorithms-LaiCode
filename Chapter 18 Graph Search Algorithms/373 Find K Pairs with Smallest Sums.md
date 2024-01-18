# 373 Find K Pairs with Smallest Sums

You are given two integer arrays `nums1` and `nums2` sorted in **non-decreasing order** and an integer `k`.

Define a pair `(u, v)` which consists of one element from the first array and one element from the second array.

Return *the* `k` *pairs* `(u1, v1), (u2, v2), ..., (uk, vk)` *with the smallest sums*.

 

**Example 1:**

```
Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]]
Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
```

**Example 2:**

```
Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
Output: [[1,1],[1,1]]
Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
```

**Example 3:**

```
Input: nums1 = [1,2], nums2 = [3], k = 3
Output: [[1,3],[2,3]]
Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
```



**Solution:**

```java
class Solution {
    static class Cell{
        int x;
        int y;
        int value;

        Cell(int x, int y, int value){
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;

        List<List<Integer>> result = new ArrayList<>();

        //boolean[][] visited = new boolean[m][n];
        Set<Pair<Integer, Integer>> visited = new HashSet<Pair<Integer, Integer>>();

        PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(k, new Comparator<Cell>(){
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
        

        minHeap.offer(new Cell(0, 0, nums1[0]+ nums2[0]));
        // visited[0][0] = true;
        visited.add(new Pair<Integer,Integer>(0,0));

        for (int i = 0; i < k; i++){
            Cell cur = minHeap.poll();
            result.add(List.of(nums1[cur.x], nums2[cur.y]));

            if (cur.x + 1 < m && !visited.contains(new Pair<Integer, Integer>(cur.x+ 1, cur.y))){
                minHeap.offer(new Cell(cur.x + 1, cur.y, nums1[cur.x+1]+ nums2[cur.y]));
                // visited[cur.x+1][cur.y] = true;
                visited.add(new Pair<Integer, Integer>(cur.x+1, cur.y));
            }

            if (cur.y + 1 < n && !visited.contains(new Pair<Integer, Integer>(cur.x, cur.y + 1))){
                minHeap.offer(new Cell(cur.x, cur.y + 1, nums1[cur.x]+ nums2[cur.y+1]));
                visited.add(new Pair<Integer, Integer>(cur.x, cur.y + 1));
            }

        }

        return result;
    }
}
// TC: O(klogk)
// SC: O(k)
```



```java
class Solution {
    static class Cell{
        int x;
        int y;
        int value;

        Cell(int x, int y, int value){
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;

        List<List<Integer>> result = new ArrayList<>();
        boolean[][] visited = new boolean[m][n];

        PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(k, new Comparator<Cell>(){
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

        minHeap.offer(new Cell(0, 0, nums1[0]+ nums2[0]));
        visited[0][0] = true;

        for (int i = 0; i < k; i++){
            Cell cur = minHeap.poll();
            result.add(List.of(nums1[cur.x], nums2[cur.y]));

            if (cur.x + 1 < m && !visited[cur.x+ 1][cur.y]){
                minHeap.offer(new Cell(cur.x + 1, cur.y, nums1[cur.x+1]+ nums2[cur.y]));
                visited[cur.x+1][cur.y] = true;
            }

            if (cur.y + 1 < n && !visited[cur.x][cur.y + 1]){
                minHeap.offer(new Cell(cur.x, cur.y + 1, nums1[cur.x]+ nums2[cur.y+1]));
                visited[cur.x][cur.y+1] = true;
            }

        }

        return result;
    }
}

// TC: O(klogk)
// SC: O(k)
```





