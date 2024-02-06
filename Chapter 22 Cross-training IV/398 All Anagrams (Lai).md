# 398 All Anagrams (Lai)

Find all occurrence of anagrams of a given string s in a given string l. Return the list of starting indices.

**Assumptions**

- sh is not null or empty.
- lo is not null.

**Examples**

- l = "abcbac", s = "ab", return [0, 3] since the substring with length 2 starting from index 0/3 are all anagrams of "ab" ("ab", "ba").

**Solution:**

```java
public class Solution {
  public List<Integer> allAnagrams(String sh, String lo) {
    // Write your solution here

    List<Integer> result = new ArrayList<Integer>();
    if (sh.length() == 0 || lo.length() == 0 || sh.length() > lo.length()){
      return result;
    }

    Map<Character, Integer> map = new HashMap<Character, Integer>();
    
    for (int i = 0; i < sh.length(); i++){
      map.put(sh.charAt(i), map.getOrDefault(sh.charAt(i), 0) + 1);
    }

    int slow = 0;
    int match = 0;

    for (int fast = 0; fast < lo.length(); fast++){
      char tmp = lo.charAt(fast);
      Integer count = map.get(tmp);
      if (count != null){
        if (count == 1){
          match++;
        }
        map.put(tmp, count -1);
      }


      if (fast >= sh.length()){
        char slowTmp = lo.charAt(slow);
        count = map.get(slowTmp);
        if (count != null){
          if (count == 0){
            match--;
          }
          map.put(slowTmp, count +1);
        }
        slow++;
      }

      if (match == map.size()){
        result.add(slow);
      }
    }

    return result;
  }
}

// TC: O(n)
// SC: O(n)
```

