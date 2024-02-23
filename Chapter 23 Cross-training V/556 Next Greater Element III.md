---
tags:
    - Math
    - Two Pointers
    - String
---

# 556 Next Greater Element III

Given a positive integer `n`, find *the smallest integer which has exactly the same digits existing in the integer* `n` *and is greater in value than* `n`. If no such positive integer exists, return `-1`.

**Note** that the returned integer should fit in **32-bit integer**, if there is a valid answer but it does not fit in **32-bit integer**, return `-1`.

**Example 1:**

```
Input: n = 12
Output: 21
```

**Example 2:**

```
Input: n = 21
Output: -1
```

**Solution:**

```java
class Solution {
    public int nextGreaterElement(int n) {
        char[] number = Integer.toString(n).toCharArray();

        int i = number.length - 2;

        while (i >= 0 && number[i] >= number[i + 1]){
            i--;
        }

        if (i == -1){
            return -1;
        }

        int j = number.length -1;
        while(number[j] <= number[i]){
            j--;
        }

        swap(number, i, j);
        
        reverse(number, i + 1, number.length -1);

        long result = Long.parseLong(new String(number));

        if (result > Integer.MAX_VALUE){
            return -1;
        }else{
            return (int) result;
        }
    }

    public void swap(char[] number, int i, int j){
        char temp = number[i];
        number[i] = number[j];
        number[j] = temp;
    }

    private void reverse(char[] number, int start, int end){
        while(start < end){
            swap(number, start++, end--);
        }
    }


}
```

这题... 题目看不懂... 

过例子

假设给定的正整数为 `n = 1243`，我们要找到一个比 `1243` 大的最小整数，且这个整数由 `1243` 中相同的数字组成。

按照上述步骤：

1. **转换为字符数组**：`['1', '2', '4', '3']`
2. **从右向左扫描**，找到第一个比右边数字小的数字，在这个例子中，`'2' < '4'`，所以 `'2'` 是我们要找的数字。
3. **在 `'2'` 右侧找到一个比它大的最小数字**，这里是 `'3'` 和 `'4'`，比 `'2'` 大的最小数字是 `'3'`。
4. **交换这两个数字**，交换后的数组为 `['1', '3', '4', '2']`。
5. **反转 `'3'` 后面的所有数字**（在这个例子中实际上不需要反转，因为交换后它们已经是升序的了）。
6. **检查结果是否符合32位整数范围**，得到的结果是 `1342`，它是一个有效的32位整数。

所以，对于输入 `1243`，按照上述步骤处理后，我们得到的答案是 `1342`。



