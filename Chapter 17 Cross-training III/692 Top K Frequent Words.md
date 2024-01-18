#  692 Top K Frequent Words

Given an array of strings `words` and an integer `k`, return *the* `k` *most frequent strings*.

Return the answer **sorted** by **the frequency** from highest to lowest. Sort the words with the same frequency by their **lexicographical order**.

 

**Example 1:**

```
Input: words = ["i","love","leetcode","i","love","coding"], k = 2
Output: ["i","love"]
Explanation: "i" and "love" are the two most frequent words.
Note that "i" comes before "love" due to a lower alphabetical order.
```

**Example 2:**

```
Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
Output: ["the","is","sunny","day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.
```



**Solution:**

```java
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        // handle the special case of empty words at the very beginning.
        List<String> result = new ArrayList<String>();
        if (words.length == 0){
            return result;
        }
        // get all the distinct strings as keys and their frequencies as values.
        // Notice: the freqMap has at least size 1.
        // Step 1: put the word in the map
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String word: words){
            if (map.get(word) == null){
                map.put(word, 1);
            }else{
                map.put(word, map.get(word) + 1);
            }
        }
        // minHeap on the frequencies of the strings.
        // NOTICE: using map.Entry as the element type directly so that all the 
        // operations are mostly efficient.
        // MyComparator comparator = new MyComparator();
        MyComparator comparator = new MyComparator();
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(comparator);

        for (Map.Entry<String, Integer> entry : map.entrySet()){
            if (minHeap.size() < k){
                minHeap.offer(entry);
            }else if (comparator.compare(entry, minHeap.peek())>0){
                minHeap.poll();
                minHeap.offer(entry);
            }

        }

        // since the returned list require the strings sorted by their
        // frequencies, use a separat helper method to do this operation
        for (int i = 0; i < k; i++){
            result.add(0, minHeap.poll().getKey());
          // add(0, ...) 方法将提取的单词插入到列表的开始位置。在这里，0是插入位置的索引，意味着每次调用都会把新的单词放在列表的最前面.
        }
        return result;
    }
}

class MyComparator implements Comparator<Map.Entry<String, Integer>> {
    public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2){
        String word1 = e1.getKey();
        int freq1 = e1.getValue();
        String word2 = e2.getKey();
        int freq2 = e2.getValue();
        if (freq1 != freq2){
            return freq1 - freq2;
        }else{
            return word2.compareTo(word1);
        }
    }
}


        // TC: O(n + nlogk)
        // SC: O(n)

/*
    An int value: 0 if the string is equal to the other string.
< 0 if the string is lexicographically less than the other string
> 0 if the string is lexicographically greater than the other string (more characters)
*/

```

