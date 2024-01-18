# 662 Word Ladder II (Lai)

Given a begin word, an end word and a dictionary, find the least number transformations from begin word to end word, and return the transformation sequences. Return empty list if there is no such transformations.

In each transformation, you can only change one letter, and the word should still in the dictionary after each transformation. 

Assumptions

1. All words have the same length.
2. All words contain only lowercase alphabetic characters.
3. There is no duplicates in the word list.

4.The *beginWord* and *endWord* are non-empty and are not the same.

Example: start = "git", end = "hot", dictionary = {"git","hit","hog","hot","got"}

Output: [["git","hit","hot"], ["git","got","hot"]]



**Solution:**

```java
public class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        Set<String> set = new HashSet<String>(wordList);
        Queue<String> queue=new LinkedList<String>();
        Map<String,Integer> map = new HashMap<>();

        map.put(beginWord,1);
        queue.add(beginWord);
        set.remove(beginWord);
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

        if(map.containsKey(endWord)){
            List<String> subResult = new ArrayList<>();
            subResult.add(endWord);
            dfs(beginWord, endWord, subResult, result, map);

        }

        return result;
    }
    
    public void dfs(String beginWord, String endWord, List<String> subResult, List<List<String>> result, Map<String,Integer> map){
        if(endWord.equals(beginWord)){
          List<String> list = new ArrayList<>(subResult);
          Collections.reverse(list);
          result.add(list);
          return;
        }
        
        String curWord = endWord;
        int curSteps = map.get(curWord);
        for(int i = 0; i < curWord.length(); i++){
            for(char ch ='a'; ch <='z'; ch++){
                char[] curWordArray = curWord.toCharArray();
                curWordArray[i] = ch;
                String newWord = new String(curWordArray);
                if(map.containsKey(newWord) && map.get(newWord) + 1 == curSteps){
                    subResult.add(newWord);
                    dfs(beginWord, newWord, subResult, result, map);
                    subResult.remove(subResult.size() - 1);
                }

            }
        }

    }

}

// TC: O(N * L * 26 + N * W)        N wordList.size L word.length 
// W 是单词列表中可能的单词转换的平均数量。这个数值取决于单词列表中单词的分布，也就是说，每个单词可以通过改变一个字母转换成多少其他单词。
// SC: O(N)
```

