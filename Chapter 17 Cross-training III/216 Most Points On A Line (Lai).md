#  216 Most Points On A Line (Lai)

Given an array of 2D coordinates of points (all the coordinates are integers), find the largest number of points that can be crossed by a single line in 2D space.

**Assumptions**

- The given array is not null and it has at least 2 points

**Examples**

- <0, 0>, <1, 1>, <2, 3>, <3, 3>, the maximum number of points on a line is 3(<0, 0>, <1, 1>, <3, 3> are on the same line)

**Solution:**

```java
/*
* class Point {
*   public int x;
*   public int y;
*   public Point(int x, int y) {
*     this.x = x;
*     this.y = y;
*   }
* }
*/
public class Solution {
  public int most(Point[] points) {
    // Write your solution here.
    // Assumptions: points is not null, and points.length >= 2
    // record the maximum number of points on the same line
    int result = 0;
    // use each pair of points to form a line
    for (int i = 0; i < points.length; i++){
      // any line can be represented by a point and a slope
      // take the point as seed and try to find all possible slopes.
      // record the points with same <x,y>
      int x1 = points[i].x;
      int y1 = points[i].y;

      int same = 0;
      // record the points with same x, for the special case of 
      // infinite slope

      // record the maximum number of points on the same line
      // crossing the seed point.
      int most = 0; // curpoint most count

      // a map with all possible slopes.
      HashMap<Double, Integer> map = new HashMap<Double, Integer>();
      for (int j = 0; j < points.length; j++){
        if (i == j){
          continue;
        }

        int x2 = points[j].x;
        int y2 = points[j].y;
        if (x1 == x2 && y1 == y2){
          // handle the points with same <x,y>.
          same++;
        }else {
          // otherwise, just calculate the slope and increment the counter
          // for the calculated slope.
          double slope = (y2-y1) * 1.0d / (x2 - x1)* 1.0d;

          int count = map.getOrDefault(slope, 1) + 1;
          map.put(slope, count);
          most = Math.max(most, count);
        }
      }
      most = most + same;
      result = Math.max(result, most);
    }
    return result;
  }
}

// TC: O(n^2)
// SC: O(n)
```

