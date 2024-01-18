# 133 Merge K Sorted Array (Lai)

Merge K sorted array into one big sorted array in ascending order.

**Assumptions**

- The input arrayOfArrays is not null, none of the arrays is null either.

**Solution:**

```java
public class Solution {
  public int[] merge(int[][] arrayOfArrays) {
    // Write your solution here
    // Assumptions: arrayOfArrays is not null, none of the array is null either.
    PriorityQueue<Entry> minHeap = new PriorityQueue<Entry>(11, new MyComparator());
    // 创建了一个优先队列（最小堆） minHeap，其元素类型为 Entry。
    // 优先队列的初始容量设为11（这是一个任意的选择，可以根据需要调整），
    // 并使用自定义的比较器 MyComparator 来排序队列中的元素

    int length = 0;
    // [[[3],[1,2,3,4,5]]]

    for (int i = 0; i < arrayOfArrays.length; i++){ // x
      int[] array = arrayOfArrays[i];
      length = array.length + length;
      if (array.length != 0){
        // We use two index to record the position of each element:
        // the index of the array in the arrayOfArrays,
        // the index of the element in the array;
        minHeap.offer(new Entry(i, 0, array[0]));
        //                      x  y  [1]
        // 将当前数组的第一个元素及其在 arrayOfArrays 中的索引
        //（数组索引 i 和元素索引 0）作为一个新的 Entry 对象放入最小堆中
      }
    }

    int[] result = new int[length];
    int cur = 0;
    while(!minHeap.isEmpty()){
      Entry tmp = minHeap.poll(); // {1, 0, 1}         
      result[cur] = tmp.value;
      if (tmp.y + 1 < arrayOfArrays[tmp.x].length){
        // reuse the same Entry object but advance the index by 1.
        tmp.y = tmp + 1;
        tmp.value = arrayOfArrays[tmp.x][tmp.y];
        minHeap.offer(tmp);
      }
      cur = cur + 1;
    }
    return result;
  }

  static class MyComparator implements Comparator<Entry> {
    @Override
    public int compare(Entry e1, Entry e2){
      if (e1.value == e2.value){
        return 0;
      }
      return e1.value < e2.value ? -1 : 1;
    }
  }

  static class Entry{
    // The row number.
    int x; 
    // The column number.
    int y; 
    // The corresponding value.
    int value;

    Entry(int x, int y, int value){
      this.x = x;
      this.y = y;
      this.value = value;
    }
  }
}
```

k-way all together

in[][]

A1 xxXxxxxxxxxxx			n

​      i ->

A2 Yyyyyyyyyyyyyyyy                   n

​      j ->

A3 Zzzzzzzzzzzzzzzzzzzzzz          n

​     k -> 

solution: K个K个一起走, 谁小移谁

痛点: 1. K个同事比怎么找最小?

2. 同时存当前的K个array中的k个正在待比较的东西

data structure: min heap

A1: 1  3 5 7 9

​	i

A2: 2 6 8

​                 j

A3: 4 5 9

​		k

minheap:{5, 6, 4} -> minHeap里的存的是所有正在被比较的k个elements

minHeap放的东西: value, 他在哪个array的哪个位置

initialize: 基于k的array的array[0] -> initialize heap

step: 1. find the min -> heap.poll() ->

2. add to result and move pointer (谁小移谁)
3. push the next pointer



stop condition: minHeap is empty

result: 1, 2, 3

minHeap's size = K (不会扩容)

1. k pointer: data structure
2. initialization: put the k pointers into the data structure -- insert klogk
3. find the min of k pointers - nk times
   * find the min and pop out pop() -> minHeap - logk
   * move the min pointers to the next pointer - O(1)
   * push the next min pointer into this data structure offer(j+1) - logk

TC: klogK + nk\*logk ~ (K+nk)\*logk = nk\*logK

Space = O(k)

totoal time complexity: O(knlogk)



minHeap\<Obj> -> Obj(value, which array, index)

Heap<Element>

A1: 1 3 5 **7** 9

​			i

A2: 2 6 8

​       j

A3: 4 5 9

​	k



```java
class Element implements Comparable{
  int indexOfArray; // 我在那个array上 0 
  int indexInArray; // 我在这个array的哪个index 上2
  int value; // used as the key 5
  @Override
  public int compareTo(Element e1, Element e2){
    Integer.compare(e1.value, e2.value)
  }
}
```



