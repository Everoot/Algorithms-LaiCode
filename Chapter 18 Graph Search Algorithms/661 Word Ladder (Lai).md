# 661 Word Ladder (Lai)

Given a begin word, an end word and a dictionary, find the least number transformations from begin word to end word, and return the length of the transformation sequence. Return 0 if there is no such transformations.

In each transformation, you can only change one letter, and the word should still in the dictionary after each transformation. 

Assumptions

1. All words have the same length.
2. All words contain only lowercase alphabetic characters.
3. There is no duplicates in the word list.

4.The *beginWord* and *endWord* are non-empty and are not the same.

Example: start = "git", end = "hot", dictionary = {"git","hit","hog","hot"}

Output: 3

Explanation: git -> hit -> hot



**Solution:**

```java
class Solution {
    static class Pair{
        String word;
        int step;

        Pair(String word, int step){
            this.word = word;
            this.step = step;
        }
    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<Pair> queue = new LinkedList<Pair>();
        queue.add(new Pair(beginWord, 1));

        Set<String> set = new HashSet<String>();
        int len = wordList.size();
        for (int i = 0; i < len; i++){
            set.add(wordList.get(i));
        }

        set.remove(beginWord);
        while(!queue.isEmpty()){
            Pair cur = queue.poll(); // <hit, 1>
            String curWord = cur.word; // hit
            int curStep = cur.step; // 1

            if (curWord.equals(endWord)){
                return curStep;
            }

            for (int i = 0; i < curWord.length(); i++){ // 3
                for (char ch = 'a'; ch <= 'z'; ch++){// 
                    char[] rCharArray = curWord.toCharArray(); // h i t
                    rCharArray[i] = ch; // h i t => a i t
                    String replaceWord = new String(rCharArray); // ait
                    if (set.contains(replaceWord)){ 
                        queue.add(new Pair(replaceWord, curStep + 1));
                        set.remove(replaceWord);
                    }
                }
            }

        }

        return 0;
        
    }
}

// TC:  O(N * L * 26) N: wordlist.length L word.length 
// SC:  O(N)
```

