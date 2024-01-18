# 973 K Closest Points to Origin

Given an array of `points` where `points[i] = [xi, yi]` represents a point on the **X-Y** plane and an integer `k`, return the `k` closest points to the origin `(0, 0)`.

The distance between two points on the **X-Y** plane is the Euclidean distance (i.e., `√(x1 - x2)2 + (y1 - y2)2`).

You may return the answer in **any order**. The answer is **guaranteed** to be **unique** (except for the order that it is in).

 

**Example 1:**

<img src="./973 K Closest Points to Origin/closestplane1.jpg" alt="img" style="zoom: 33%;" />

```
Input: points = [[1,3],[-2,2]], k = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
```

**Example 2:**

```
Input: points = [[3,3],[5,-1],[-2,4]], k = 2
Output: [[3,3],[-2,4]]
Explanation: The answer [[-2,4],[3,3]] would also be accepted.
```



**Solution:**

```java
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>(k, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                int d1 = distance(o1);
                int d2 = distance(o2);
                if (d1 == d2){
                    return 0;
                }else if (d1 < d2){
                    return -1;
                }else{
                    return 1;
                }
            }
        });

        // add all points to the heap
        for (int[] point : points){
            minHeap.offer(point);
            
        }

        // Retrieve the closest k points
        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++){
            result[i] = minHeap.poll();
        }
        return result;
        
    }

    private int distance(int[] point){
        return point[0]*point[0] + point[1]*point[1];
    }
}

// TC: O(nlogn)
// SC: O(n)
```

