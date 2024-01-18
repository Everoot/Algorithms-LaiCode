# 126 Word Ladder II

A **transformation sequence** from word `beginWord` to word `endWord` using a dictionary `wordList` is a sequence of words `beginWord -> s1 -> s2 -> ... -> sk` such that:

- Every adjacent pair of words differs by a single letter.
- Every `si` for `1 <= i <= k` is in `wordList`. Note that `beginWord` does not need to be in `wordList`.
- `sk == endWord`

Given two words, `beginWord` and `endWord`, and a dictionary `wordList`, return *all the **shortest transformation sequences** from* `beginWord` *to* `endWord`*, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words* `[beginWord, s1, s2, ..., sk]`.

 

**Example 1:**

```
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
Explanation: There are 2 shortest transformation sequences:
"hit" -> "hot" -> "dot" -> "dog" -> "cog"
"hit" -> "hot" -> "lot" -> "log" -> "cog"
```

**Example 2:**

```
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: []
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
```

 

**Solution:**

```java
public class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>(); // 存储所有找到的最短路径
        Set<String> set = new HashSet<String>(wordList); //包含 wordList 中所有单词的集合，用于快速查找
        Queue<String> queue=new LinkedList<String>(); // 用于 BFS 的队列
        Map<String,Integer> map = new HashMap<>(); //映射每个单词到它的步数（即到达该单词的最短路径长度）

        map.put(beginWord,1);
        queue.add(beginWord);
        set.remove(beginWord); // avoid duplicate
        // BFS
        while(!queue.isEmpty()){
           String curWord = queue.poll();
           int curStep = map.get(curWord);
           if(curWord.equals(endWord)){
               break;
           }

           for(int i = 0; i < curWord.length(); i++){
               for(char ch ='a'; ch <='z'; ch++){
                   char[] curWordArray = curWord.toCharArray();
                   curWordArray[i] = ch;
                   String newWord = new String(curWordArray);
                   if(set.contains(newWord)){
                       queue.add(newWord);
                       map.put(newWord, curStep +1);
                       set.remove(newWord);
                   }
               }
           }
        }
				
      // 如果 map 包含 endWord，则从 endWord 开始使用 DFS 方法寻找所有路径。
        if (map.containsKey(endWord)){
            List<String> path = new ArrayList<String>();
            path.add(endWord);
            dfs(beginWord, endWord, path, result, map);
        }

        return result;
        
    }
    
    public void dfs(String beginWord, String endWord, List<String> path, List<List<String>> result, Map<String, Integer> map){
        if (endWord.equals(beginWord)){
            List<String> subResult = new ArrayList<String>(path);
            Collections.reverse(subResult);
            result.add(subResult);
            return;
        }

        String curWord = endWord;
        int curStep = map.get(curWord);
        for (int i = 0; i < curWord.length(); i++){
            for (char ch = 'a'; ch <= 'z'; ch++){
                char[] curWordArray = curWord.toCharArray();
                curWordArray[i] = ch;
                String newWord = new String(curWordArray);
                if (map.containsKey(newWord) && map.get(newWord) + 1 == curStep){
                    path.add(newWord);
                    dfs(beginWord, newWord, path, result, map);
                    path.remove(path.size() - 1);
                }
            }
        }
    }
}
// TC: O(N * L * 26 + N * W)        N wordList.size L word.length 
// W 是单词列表中可能的单词转换的平均数量。这个数值取决于单词列表中单词的分布，也就是说，每个单词可以通过改变一个字母转换成多少其他单词。
// SC: O(N)
```



```java
class Solution {
    static class NeighborFinder{
        public NeighborFinder(List<String> words){
            for (int i = 0; i < words.size(); i++){
                String word = words.get(i);
                wordIndex.put(word, i);
            }

            this.words = words;
        }

        public List<Integer> findNeighbors(int i){
            List<Integer> neighbors = new ArrayList<>(16);
            String word = words.get(i);
            StringBuilder wordModifier = new StringBuilder(word);
            for (int j = 0; j < wordModifier.length(); j++){
                char orig = word.charAt(j);
                for (char c = 'a'; c <= 'z'; c++){
                    if (c == orig){
                        continue;
                    }

                    wordModifier.setCharAt(j, c);
                    int neighbor = wordIndex.getOrDefault(wordModifier.toString(), -1);
                    if (neighbor != -1){
                        neighbors.add(neighbor);
                    }
                }
                wordModifier.setCharAt(j, orig);

            }
            return neighbors;
        }

        private Map<String, Integer> wordIndex = new HashMap<>();
        private List<String> words;

    }

    static class Tracer{
        public Tracer(List<String> words){
            this.words = words;

            preds = new ArrayList<>(words.size());
            for (int i = 0; i < words.size(); i++){
                preds.add(new ArrayList<>(16));
            }
        }

        public void addPredecessor(int x, int y){
            preds.get(y).add(x);
        }

        public List<List<String>> findLadders(int beginIndex, int y){
            List<List<String>> ladders = new ArrayList<>();
            List<String> trace = new ArrayList<>();
            trace.add(words.get(y));
            findLaddersHelper(beginIndex, y, trace, ladders);
            return ladders;
        }

        private void findLaddersHelper(int beginIndex, int y, List<String> trace, List<List<String>> ladders){
            if (beginIndex == y){
                List<String> ladder = new ArrayList<>(trace);
                Collections.reverse(ladder);
                ladders.add(ladder);
                return;
            }

            for (int x : preds.get(y)){
                trace.add(words.get(x));
                findLaddersHelper(beginIndex, x, trace, ladders);
                trace.remove(trace.size() -1);
            }
        }

        private List<String> words;
        private List<List<Integer>> preds;
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
      	// checking for the existence of 'endWord', not exist return -1
        int endIndex = wordList.indexOf(endWord);
        if (endIndex == -1){
            return new ArrayList<>();
        }

        List<String> words;
        // checking for the existence of 'beginWord'
        int beginIndex = wordList.indexOf(beginWord);
        if (beginIndex == -1){
            words = new ArrayList<String>(wordList);
            words.add(beginWord);
            beginIndex = words.size() - 1;
        } else {
            words = wordList;
        }

        NeighborFinder finder = new NeighborFinder(words);

        Queue<Integer> queue = new ArrayDeque<>();

        int[] step = new int[words.size()];
        Arrays.fill(step, -1);

        queue.offer(beginIndex);
        step[beginIndex] = 0;
        Tracer tracer = new Tracer(words);
        while(!queue.isEmpty()){
            int x = queue.poll();
            if (x == endIndex){
                return tracer.findLadders(beginIndex, endIndex);
            }
            for (int y : finder.findNeighbors(x)){
                if (step[y] == -1){
                    queue.offer(y);
                    step[y] = step[x] + 1;
                }

                if (step[x] + 1 == step[y]){
                    tracer.addPredecessor(x,y);
                }
            }
        }
        return new ArrayList<>();
        
    }
}

// TC: O(E+V)
// SC: O(E+V)
```





