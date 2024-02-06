# 431 Word Search II (Lai)

Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

For example,
Given **words** = `["oath","pea","eat","rain"]` and **board** =

```
[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
```

Return `["eat","oath"]`.



**Note:**
You may assume that all inputs are consist of lowercase letters `a-z`.



**Solution:**

```java
class Solution {
    public class TrieNode{
        TrieNode[] children = new TrieNode[26]; // 字母多的时候用比较好. 
      // 稀疏的话用map
      // An array of size 26, each index -> character, 'c':'c'-'a' =2 
        boolean isWord;
    }
  
  // Time: worst case guarantee O(m)
  // Space: if the trie is dense, since reusing the common prefix as many as 
  // possible, less space required. worst case O(nm), but usually much better than this.


    static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
  // 四个方向
    public List<String> findWords(char[][] board, String[] words) {
      	// 检查边界条件，如果板为空或单词列表为空，则直接返回空的结果列表
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0 || words == null || words.length == 0){
            return new ArrayList<String>();
        }

        Set<String> res = new HashSet<>(); // 使用一个HashSet res来存储找到的独特单词
        TrieNode root = buildDict(words); 
      // 调用buildDict方法，使用给定的单词构建Trie，并保存根节点
       	int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                helper(board, i, j, root, sb, res, visited);
            }
        }
        return new ArrayList<>(res);

    }

    private TrieNode buildDict(String[] words){
        TrieNode root = new TrieNode(); // 初始化Trie的根节点
        for (String word : words){ // 遍历每个单词，并将它们插入到Trie中
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++){
                TrieNode next = cur.children[word.charAt(i) - 'a'];
                if (next == null){
                  // 对于每个字符，如果在当前节点的children数组中不存在，就创建一个新的TrieNode
                    next = new TrieNode(); 
                    cur.children[word.charAt(i) - 'a'] = next;
                }
                cur = next;
            }
            cur.isWord = true; // 标记单词的最后一个字符所在节点的isWord为true
        }
        return root;
    }

    private void helper(char[][] board, int x, int y, TrieNode root, StringBuilder sb, Set<String> res, boolean[][] visited){
        int rows = board.length;
        int cols = board[0].length;
        if (x < 0 || x >= rows || y < 0 || y>= cols || visited[x][y]){
            return; // 检查当前位置是否越界或已访问
        }
        
       // 获取当前位置的字符，并检查当前Trie节点的children数组中是否存在对应的子节点
        char ch = board[x][y];
        if (root.children[ch - 'a'] == null){
            return;
        }

        sb.append(ch); // 将字符添加到StringBuilder中
        root = root.children[ch - 'a']; // 更新当前Trie节点为对应的子节点
        if (root.isWord){  // 如果当前节点是一个单词的结束，将StringBuilder中的内容添加到结果集中
            res.add(sb.toString());
        }
      
        visited[x][y] = true; // 标记当前位置为已访问
        for (int[] dir : DIRS){ // 遍历所有方向，并对每个方向递归调用helper方法
            int neiX = dir[0] + x;
            int neiY = dir[1] + y;
            helper(board, neiX, neiY, root, sb, res, visited);
        }
        visited[x][y] = false;
        sb.deleteCharAt(sb.length() - 1); 
      // 将当前位置标记为未访问，并从StringBuilder中移除最后一个字符
    }
}

// TC: O(4^(m*n))
// SC: O(m*n)
```

