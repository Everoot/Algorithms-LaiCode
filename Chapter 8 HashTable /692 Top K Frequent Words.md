# 692 Top K Frequent Words

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



```java
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        // Assumptions: words is not, and k >= 1
        List<String> result = new ArrayList<String>();
        if (words.length == 0){
            return result;
        }
        // get all the distinct strings as keys and their frequencies as values.
        // Notice: the freqMap has at least size 1.
        // Step 1: put word in the map
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String word: words){
            if (map.get(word) == null){
                map.put(word, 1);
            }else{
                map.put(word, map.get(word)+1);
            }
        }

        // O(n)

        // minHeap on the frequencies of the strings.
        // Notice: using Map.Entry as the element
        // Step 2: min Heap
        // MyComparator comparator = new MyComparator();
        MyComparator comparator = new MyComparator();
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(comparator);


        for (Map.Entry<String, Integer> entry : map.entrySet()){
            if (minHeap.size() < k){
                minHeap.offer(entry);
            }else {
                if (comparator.compare(entry, minHeap.peek())>0){ // need smaller string
                minHeap.poll();
                minHeap.offer(entry);
                }
            }
        }
        //log 1 + log2 + ... + logk = klogk
        //size > k pop, time = (n-k)logk
        // time = O(klogk) + O(n-k)logk = O(nlogk)

        // sicne the returned List require the strings sorted by their
        // frequencies, use a separate helper method to do this operation
        for (int i = 0; i < k; i++){
            result.add(0, minHeap.poll().getKey()); 
            //add the new items at the start of the list. It moves all the existing items to their right. 
        }
        //O(klogk)

        

        //Collections.reverse(result);
        return result;

        // Step 3: pick

        // TC: O(n + nlogk)
        // SC: O(n)
    }
}

class MyComparator implements Comparator<Map.Entry<String, Integer>> {
    public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2){
        String word1 = e1.getKey();
        int freq1 = e1.getValue();
        String word2 = e2.getKey();
        int freq2 = e2.getValue();
        if(freq1 != freq2){
            return freq1-freq2;
        }
        else {
            return word2.compareTo(word1);
        }
    }
    /*
    An int value: 0 if the string is equal to the other string.
< 0 if the string is lexicographically less than the other string
> 0 if the string is lexicographically greater than the other string (more characters)
*/
}
```

