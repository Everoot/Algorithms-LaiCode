# 318 Maximum Product of Word Lengths

Given a string array `words`, return *the maximum value of* `length(word[i]) * length(word[j])` *where the two words do not share common letters*. If no such two words exist, return `0`.

 

**Example 1:**

```
Input: words = ["abcw","baz","foo","bar","xtfn","abcdef"]
Output: 16
Explanation: The two words can be "abcw", "xtfn".
```

**Example 2:**

```
Input: words = ["a","ab","abc","d","cd","bcd","abcd"]
Output: 4
Explanation: The two words can be "ab", "cd".
```

**Example 3:**

```
Input: words = ["a","aa","aaa","aaaa"]
Output: 0
Explanation: No such pair of words.
```



**Solution:**

Solution1:

```java
class Solution {
    public int maxProduct(String[] words) {
        List<Set<Character>> list = new ArrayList<Set<Character>>();
        for (String word : words){ // O(n)
            Set<Character> set = new HashSet<Character>();
            for (int i = 0; i < word.length(); i++){ // O(L)
                set.add(word.charAt(i));
            }
            list.add(set);
        }
        /*
            {<a,b,c,w>, <b, a, z>, <f,o>, <b,a,r>, <x, t, f, n> <a, b, c, d, e, f>}
        */
        

        int largest = 0;
        for (int i = 0; i < words.length; i++){ // O(N)
            for (int j = i + 1; j < words.length; j++){ // O(N)
                if (isValid(list.get(i), words[j])){  // O(L)
                    // <a,b,c,w> , xtfn
                    int cur = words[i].length() * words[j].length();
                    // 4 * 4
                    largest = Math.max(cur, largest);
                }
            }
        }

        return largest;
    }

    private static boolean isValid(Set<Character> set, String word){
        // <a,b,c,w>, xtfn
        for (int i = 0; i < word.length(); i++){
            if (set.contains(word.charAt(i))){
                return false;
            }
        }

        return true;
    }
}

// TC: O(N^2 * L)
// SC: O(N*L)
```



Solution2:

```java
class Solution {
    public int maxProduct(String[] words) {
        // Assumptions: words is not null and has length >= 2;
        // there is no null String in the dict.
        // The words in the dict only use characters 'a' - 'z'.
        // Get the bit mask for each of the word in the dict,
        // the bit mask is represented by the lower 26 bits of an integer.
        // each of the bit represents one of the characters in 'a' - 'z'.
        Map<String, Integer> bitMasks = getBitMasks(words);
        
        // sort the dict by length of the words in descending order.
        Arrays.sort(words, new Comparator<String>(){
            @Override
            public int compare(String s0, String s1){
                if (s0.length() == s1.length()){
                    return 0;
                }else if (s0.length() < s1.length()){
                    return 1;
                }else{
                    return -1;
                }
            }
        });

        int largest = 0;
        // note the order of constructing all the pairs,
        // we make our best to try largest product.
        for (int i = 1; i < words.length; i++){
            for (int j = 0; j < i; j++){
                // early break if the product is already smaller than
                // the current largest one.
                int prod = words[i].length() * words[j].length();
                if (prod <= largest){
                    break;
                }
                int iMask = bitMasks.get(words[i]);
                int jMask = bitMasks.get(words[j]);

                if ((iMask & jMask) == 0){
                    largest = prod;
                }
            }
        }

        return largest;
        
    }

    // Get the bit mask for each of the words.
    private Map<String, Integer> getBitMasks(String[] words){
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String word : words){
            int bitMask = 0;
            for (int i = 0; i < word.length(); i++){
                // the 26 characters 'a' - 'z' are mapped to 0-25th bit.
                // to determine which bit it is for a character x,
                // use (x - 'a') since their values are in a consecutive range.
                // if character x existes in the word, we set the bit at 
                // corresponding index to 1.
                bitMask |= 1 << (word.charAt(i) - 'a');
            }
            map.put(word, bitMask);
        }

        return map;
    }
}
// TC: O(n^2 + |sum of all words' length|))
// SC: O(n)
```

