# 170 Two Sum III - Data structure design

Design a data structure that accepts a stream of integers and checks if it has a pair of integers that sum up to a particular value.

Implement the `TwoSum` class:

- `TwoSum()` Initializes the `TwoSum` object, with an empty array initially.
- `void add(int number)` Adds `number` to the data structure.
- `boolean find(int value)` Returns `true` if there exists any pair of numbers whose sum is equal to `value`, otherwise, it returns `false`.

 

**Example 1:**

```
Input
["TwoSum", "add", "add", "add", "find", "find"]
[[], [1], [3], [5], [4], [7]]
Output
[null, null, null, null, true, false]

Explanation
TwoSum twoSum = new TwoSum();
twoSum.add(1);   // [] --> [1]
twoSum.add(3);   // [1] --> [1,3]
twoSum.add(5);   // [1,3] --> [1,3,5]
twoSum.find(4);  // 1 + 3 = 4, return true
twoSum.find(7);  // No two integers sum up to 7, return false
```



**Solution**

```java
class TwoSum {
    private List<Integer> nums;
    private boolean is_sorted;
    public TwoSum() {
        this.nums = new ArrayList<Integer>();
        this.is_sorted = false;
    }
    
    public void add(int number) { // O(1)
        nums.add(number);
        is_sorted = false;
        
    }
    
    // Find if there exists any pair of numbers which sum is equal to the value
    public boolean find(int value) {
        if (is_sorted == false){
            Collections.sort(nums);     // nlogn
            is_sorted = true;
        }

        int left = 0;
        int right = nums.size() - 1;
        while (left < right){           // n
            int curSum = nums.get(left) + nums.get(right);
            if (curSum == value){
                return true;
            }else if (curSum < value){
                left++;
            }else{
                right--;
            }
        }

        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */

 // TC: O(nlogn)
 // SC: O(n)
```

