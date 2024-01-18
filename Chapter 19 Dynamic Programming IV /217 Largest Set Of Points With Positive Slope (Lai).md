# 217 Largest Set Of Points With Positive Slope (Lai)

Given an array of 2D coordinates of points (all the coordinates are integers), find the largest number of points that can form a set such that any pair of points in the set can form a line with positive slope. Return the size of such a maximal set.

**Assumptions**

- The given array is not null
- **Note**: if there does not even exist 2 points can form a line with positive slope, should return 0.

**Examples**

- <0, 0>, <1, 1>, <2, 3>, <3, 3>, the maximum set of points are {<0, 0>, <1, 1>, <2, 3>}, the size is 3.



**Solution:**

Step 1: Sort the input points by x condinates. O(nlogn)

Step 2: Find a subsequence in this sorted input of which the y coordinates will form an increasing sequence.

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

// slope = (y2 - y1) / (x2 - x1)
// 如果我们知道
// x1 < x2 < x3 <x4 < ... < xn
// y1	y2	y3	y4	...	yn
// y1 < y3 < y4 < y6 < yn
//slope = (y2 - y1) / (x2 - x1)
// 任意拿出一个分母一定为正的,所以只要判断 y2 - y1是正的OK了
public class Solution {
  public int largest(Point[] points) {
    // Assumptions: points is not null
    // we need to sort the points by x ascending and y descending
    // this comparator will sort the pointx by x ascending and y descending
    Arrays.sort(points, new Comparator<Point>(){
      @Override
      public int compare(Point p1, Point p2){
        if (p1.x != p2.x){
          if (p1.x < p2.x){
            return -1;
          }else{
            return 1;
          }
        }else{
          if (p1.y > p2.y){
            return -1;
          }else if (p1.y == p2.y){
            return 0;
          }else{
            return 1;
          }
        }
      }
    });
    // <1,2> <2,4> <2,3>
    //          y1    y2
    // 

    int result = 0;
    int[] longest = new int[points.length];
    for (int i = 0; i < longest.length; i++){
      for (int j = 0; j < i; j++){
        if (points[j].y < points[i].y){
          longest[i] = Math.max(longest[i], longest[j]);
        }
      }
      longest[i] = longest[i] + 1; // itself
      result = Math.max(result, longest[i]);
    }

    if (result == 1){
      return 0;
    }else{
      return result;
    }
  }

}

// TC: O(n^2)
// SC: O(n)

// 给定一堆二维坐标，要求找到最大的子集使得子集内部任意两点之间连线斜率都是正数。
// 看着复杂，其实就是把输入的坐标按照x轴排序，然后找到最长升序序列。
```

