# 194 Kth Closest Point To <0,0,0> (Lai)

Given three arrays sorted in ascending order. Pull one number from each array to form a coordinate <x,y,z> in a 3D space. Find the coordinates of the points that is k-th closest to <0,0,0>.

We are using euclidean distance here.

**Assumptions**

- The three given arrays are not null or empty, containing only non-negative numbers
- K >= 1 and K <= a.length * b.length * c.length

**Return**

- a size 3 integer list, the first element should be from the first array, the second element should be from the second array and the third should be from the third array

**Examples**

- A = {1, 3, 5}, B = {2, 4}, C = {3, 6}
- The closest is <1, 2, 3>, distance is sqrt(1 + 4 + 9)
- The 2nd closest is <3, 2, 3>, distance is sqrt(9 + 4 + 9)

**Solution:**

```java
public class Solution {
  public List<Integer> closest(int[] a, int[] b, int[] c, int k) {
    // Write your solution here
    // Assumptions: a, b, c are not null, length >= 1
    // k >= 1 && k <= a.length * b.length * c.length 
    // we will need a min heap, with comparator to compare the distance.
    // Note that we are using the index in a,b,c as values in the List<Integer>.
    PriorityQueue<List<Integer>> minHeap = new PriorityQueue<>(2 * k, new Comparator<List<Integer>>(){
      @Override
      public int compare(List<Integer> o1, List<Integer> o2){
        long d1 = distance(o1, a, b, c);
        long d2 = distance(o2, a, b, c);
        if (d1 == d2){
          return 0;
        }else if (d1 < d2){
          return -1;
        }else {
          return 1;
        }
      }
    });

    // Note that List's equals() method has been already overridden,
    // and it is comparing the actual elements in the List.
    Set<List<Integer>> visited = new HashSet<>();
    // The initial state is <0,0,0>, meaning picking the smallest elements
    // from the three arrays.
    List<Integer> cur = Arrays.asList(0, 0, 0);// [0,0,0]
    visited.add(cur); // [0,0,0]
    minHeap.offer(cur); // [0,0,0]
    while (k > 1){
      cur = minHeap.poll(); //[0,0,0]
      List<Integer> n = Arrays.asList(cur.get(0) + 1, cur.get(1), cur.get(2));//[1,0,0]
      if (n.get(0) < a.length && !visited.contains(n)){ 
        visited.add(n);
        minHeap.offer(n);
      }
      n = Arrays.asList(cur.get(0), cur.get(1) + 1, cur.get(2)); // [0,1,0]
      if (n.get(1) < b.length && !visited.contains(n)){
        visited.add(n);
        minHeap.offer(n);
      }
      n = Arrays.asList(cur.get(0), cur.get(1), cur.get(2) + 1);
      if (n.get(2) < c.length && !visited.contains(n)){ // [0,0,1]
        visited.add(n);
        minHeap.offer(n);
      }
      k--; // k
    }
    // at last, we replace the index with actual values in a, b, c.
    List<Integer> kthIndex = minHeap.poll(); // just index
    List<Integer> result = Arrays.asList(a[kthIndex.get(0)], b[kthIndex.get(1)], c[kthIndex.get(2)]);
    return result;
  }

  private long distance(List<Integer> point, int[] a, int[] b, int[] c){
    long dis = 0;
    dis = a[point.get(0)] * a[point.get(0)] + b[point.get(1)] * b[point.get(1)] + c[point.get(2)] * c[point.get(2)];
    return dis;
  }
}

// TC: O(klogk)
// SC: O(k)
```

