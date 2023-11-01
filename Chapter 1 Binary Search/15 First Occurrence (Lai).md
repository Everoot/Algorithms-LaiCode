# 15 First Occurrence (Lai)

Given a target integer T and an integer array A sorted in ascending order, find the index of the first occurrence of T in A or return -1 if there is no such index.

**Assumptions**

- There can be duplicate elements in the array.

**Examples**

- A = {1, 2, 3}, T = 2, return 1
- A = {1, 2, 3}, T = 4, return -1
- A = {1, 2, 2, 2, 3}, T = 2, return 1

**Corner Cases**

- What if A is null or A of zero length? We should return -1 in this case.



```java
public class Solution {
  public int firstOccur(int[] array, int target) {
    // Write your solution here
    if (array == null || array.length == 0){
      return -1;
    }

    int left = 0;
    int right = array.length -1;
    while (left < right-1 ){
      int mid = left + (right - left)/2;
      if (array[mid] == target){
        right = mid;             // move right closer 
      } else if (array[mid] < target){
        left = mid + 1;
      } else {
        right = mid -1;
      }
    }
    
    if (array[left] == target){
      return left;
    }else if(array[right] == target){
      return right;
    }else {
      return -1;
    }

  }
}
```



```java
public class Solution {
  public int firstOccur(int[] array, int target) {
    // Write your solution here
    if (array == null || array.length == 0){
      return -1;
    }
    int left = 0;
    int right = array.length -1;
    int count = -1;
    while (left <= right){
      int mid = left + (right - left)/2;
      if (array[mid] == target){
        count = mid;
        break;
      }else if (array[mid] < target){
        left = mid + 1;
      }else {
        right = mid -1;
      }
    }

    if (count == -1){
      return -1;
    } 

    while (count!= 0 &&(array[count-1] == target)){
      count = count - 1;
    }
    return count;
  }
}

```



