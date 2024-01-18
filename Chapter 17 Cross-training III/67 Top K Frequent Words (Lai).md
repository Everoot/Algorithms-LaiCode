#  67 Top K Frequent Words (Lai)

Given a composition with different kinds of words, return a list of the top K most frequent words in the composition.

**Assumptions**

- the composition is not null and is not guaranteed to be sorted
- K >= 1 and K could be larger than the number of distinct words in the composition, in this case, just return all the distinct words

**Return**

- a list of words ordered from most frequent one to least frequent one (the list could be of size K or smaller than K)

**Examples**

- Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 2 frequent words are [“b”, “c”]
- Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 4 frequent words are [“b”, “c”, "a", "d"]
- Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 5 frequent words are [“b”, “c”, "a", "d"]



**Solution:**

```java
public class Solution {
  public String[] topKFrequent(String[] combo, int k) {
    // Write your solution here
    // base case
    if (combo == null || combo.length < 1){
      return combo;
    }

    Map<String, Integer> map = new HashMap<String, Integer>();
    for (String word : combo){
      if (map.containsKey(word)){
        map.put(word, map.get(word) + 1);
      }else{
        map.put(word, 1);
      }
    }

    PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<Map.Entry<String, Integer>>(k, 
    new Comparator<Map.Entry<String, Integer>>(){
      @Override
      public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2){
        return e1.getValue().compareTo(e2.getValue()); // e1 > e2 >     re>0
      }
    }
    );

    for (Map.Entry<String, Integer> entry : map.entrySet()){
      if (minHeap.size() < k){
        minHeap.offer(entry);
      }else{
        if (entry.getValue() > minHeap.peek().getValue()){
          minHeap.poll();
          minHeap.offer(entry);
        }
      }
    }

    String[] result = new String[minHeap.size()];
    for (int i = minHeap.size() - 1; i >= 0; i--){
      result[i] = minHeap.poll().getKey();
    }

    return result;


  }
}


// TC: O(n+nlogk) = O(nlogn)
// SC: O(n)
```

