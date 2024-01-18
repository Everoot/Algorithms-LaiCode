# 149 Max Points on a Line

Given an array of `points` where `points[i] = [xi, yi]` represents a point on the **X-Y** plane, return *the maximum number of points that lie on the same straight line*.

**Example 1:**

<img src="./149 Max Points on a Line/plane1.jpg" alt="img" style="zoom:50%;" />

```
Input: points = [[1,1],[2,2],[3,3]]
Output: 3
```

**Example 2:**

<img src="./149 Max Points on a Line/plane2.jpg" alt="img" style="zoom:50%;" />

```
Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
```

 

**Solution:**

```java
class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n == 1){
            return 1;
        }

        int result = 0;
        for (int i = 0; i < n; i++){
            Map<Double, Integer> map = new HashMap<Double, Integer>();
          // slope, count 
            int x1 = points[i][0];
            int y1 = points[i][1];
            for (int j = 0; j < n; j++){
                if (i == j){
                    continue;
                }
                int x2 = points[j][0];
                int y2 = points[j][1];
                
    
                //if denominator is not zero. calculate slope
                double slope = (y2 - y1) * 1.0d / (x2 - x1) * 1.0d;

                int count = map.getOrDefault(slope, 1) + 1; 
              	// how much points on this line

                // update max variable
                result = Math.max(result, count);

                map.put(slope, count);
            }

        }

        return result;
    }
}

// TC: O(n^2)
// SC: O(n)
```

