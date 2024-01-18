# 681 Seven Puzzle (Lai)

Given eight cards with number 0, 1, 2, ..7 on them, the cards are placed in two rows with 4 cards in each row. In each step only card 0 could swap with one of its adjacent(top, right, bottom, left) card. Your goal is to make all cards placed in order like this:

0 1 2 3

4 5 6 7

Find the minimum number of steps from the given state to the final state. If there is no way to the final state, then return -1.

The state of cards is represented by an array of integer, for example [0,1,2,3,4,5,6,7] where the first four numbers are in the first row from left to right while the others are placed in the second row from left to right. 



**Example:** 

Input: [4,1,2,3,5,0,6,7]    Output: 2

**Explanation:**

Initial state is:

4 1 2 3

5 0 6 7

First swap 0 with 5, then the state is:

4 1 2 3

0 5 6 7

Then swap 0 with 4, then we get the final state:

0 1 2 3

4 5 6 7



**Solution:**

```java
public class Solution {
  // Board类是为了表示游戏的当前状态 
  static class Board{
    public final static int R = 2;
    public final static int C = 4;

    public Board(){

    }
		
    // 构造函数: 创建一个具有特定值的游戏板
    public Board(int[] values){
      for (int i = 0; i < R; i++){
        for (int j = 0; j < C; j++){
          board[i][j] = values[i * C + j];
        }
      }
    }
		
    // 交换游戏板上两个位置的卡片
    public void swap(int i1, int j1, int i2, int j2){
      int temp = board[i1][j1];
      board[i1][j1] = board[i2][j2];
      board[i2][j2] = temp;
    }

    // 找到数字0卡片的位置
    public int[] findZero(){
      for (int i = 0; i < R; i++){
        for (int j = 0; j < C; j++){
          if (board[i][j] == 0){
            return new int[]{i, j};
          }
        }
      }

      return null;
    }
		
    // 检查给定的索引是否在游戏板
    public boolean outOfBound(int i, int j){
      return i < 0 || i >= R || j < 0 || j >= C;
    }
		
    // 重写hashCode方法, 以便于将Board对象用作哈希表(如HashMap)的键. 它通过遍历整个
    // 游戏板并将每个数字添加到hashcod中生成唯一个hashcode
    @Override
    public int hashCode(){
      int code = 0;
      for (int i = 0; i < R; i++){
        for (int j = 0; j < C; j++){
          code = code * 10 + board[i][j];
        }
      }
      return code;
    }
		
    // 重写了equals方法, 以便于比较两个board对象是否相等. 如果两个board的所有位置上的数字都相同
    // 则认为它们相等
    @Override
    public boolean equals(Object o){ 
      // checks if the object o is an instance of the Board class
      if (!(o instanceof Board)){
        return false;
      }
      Board b = (Board) o;
      for (int i = 0; i < R; i++){
        for (int j = 0; j < C; j++){
          if (board[i][j] != b.board[i][j]){
            return false;
          }
        }
      }
      return true;
    }

    // 创建并返回当前board对象的一个副本, 他会逐个复制数组中的元素
    @Override 
    public Board clone(){
      Board c = new Board();
      for (int i = 0; i < R; i++){
        for (int j = 0; j < C; j++){
          c.board[i][j] = board[i][j];
        }
      }

      return c;
    }

    private int[][] board = new int[R][C];
  }
	
  // DIRS 用于表示可能的移动方向 上下左右
  //                           row col
  final static int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
  public int numOfSteps(int[] values) {
    // Write your solution here
    // // 初始化了一个队列queue 和映射boardStep, 用于广度优先搜索 BFS
    Queue<Board> queue = new ArrayDeque<Board>(); // n board
    Map<Board, Integer> map = new HashMap<Board, Integer>(); // n board
		// 创建了一个表示目标状态的Board对象, 并将其及其步数0添加到队列和映射中.
    Board start = new Board(new int[]{0,1,2,3,4,5,6,7});
    queue.offer(start);
    map.put(start, 0);

    while(!queue.isEmpty()){ // n
      Board cur = queue.poll();
      int step = map.get(cur);

      int[] zeroPos = cur.findZero();
      int zeroI = zeroPos[0];
      int zeroJ = zeroPos[1];

      for (int[] dir : DIR){ //4
        int i = zeroI + dir[0];
        int j = zeroJ + dir[1];
        if (!cur.outOfBound(i,j)){ 
          cur.swap(zeroI, zeroJ, i, j);
          if (!map.containsKey(cur)){
            Board newBoard = cur.clone();
            queue.offer(newBoard);
            map.put(newBoard, step +1);
          }
          cur.swap(zeroI, zeroJ, i, j); // 恢复原状 下一个方向 即下一种情况
        }
      }
    }
    return map.getOrDefault(new Board(values), -1);
  }
}



// 这题的解法的核心就是类似游戏存档, 把有种可能情况逐步存入map中. 然后最后从map中提取当前的值.
// 以移动0的位置以产生不同的情况

// assuming the board is always 2 by 4
// Time: O(|number of unique boards|) O(8!)
// Space: O(|number of unique boards| * 8) O(8!*8)
```

Why need override hashcode?

In Java, overwriting the `hashCode` method is commonly done alongside overwriting the `equal` method, especially when you intend to use objects as keys in hash-based data structures (like `HashMap`, `HashSet`, etc). This is because hash data structures use hash codes to quickly locate the storage position of keys. Here are some key points:

1. Consistency: If two objects are considered equal according to the `equals` method, then they must also have the same hash code. This is because hash data structures first locate objects using their hash code. 
2. This is because hash data structures first locate objects using their hash code, and then use the `equals` method to check if the objects are truly equal.
3. **Performance**: A good hash function should distribute objects evenly across the hash table, which minimizes hash collisions and thereby improves the performance of the data structure.
4. **Problem with Default Implementation**: The default `hashCode` implementation in the Java `Object` class is typically based on the object's memory address. This means that even if two objects are logically equal (i.e., their `equals` method returns `true`), their default hash codes might still be different. This can cause problems when using hash collections.

The `hashCode` method of the `Board` class is overridden to ensure that two logically equivalent `Board` instances (i.e., those with the same arrangement of numbers) will have the same hash code. This is achieved by calculating a unique hash code based on the numbers in the grid. As a result, when `Board` objects are used as keys in a hash map, equivalent `Board` objects will be correctly identified and handled.



```java
public class Solution {
      public static int numOfSteps(int[] values) {
        StringBuilder boardBuilder = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            boardBuilder.append(values[i]);
        }
        Queue<State> queue = new LinkedList<>();
        Set<String> vis = new HashSet<>();
        int[][] nextIndexMap =
                new int[][] {{1, 4}, {0, 2, 5}, {1, 3, 6}, {2, 7}, {0, 5}, {1, 4, 6}, {2, 5, 7}, {3, 6}};

        String initialBoard = boardBuilder.toString();
        State initial = new State(initialBoard, initialBoard.indexOf('0'));
        queue.offer(initial);
        vis.add(initial.board);
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                State curr = queue.poll();
                if (isSolved(curr.board)) return steps;
                for (int nextIndex : nextIndexMap[curr.zeroIndex]) {
                    String nextBoard = swap(curr.board, curr.zeroIndex, nextIndex);
                    boolean unvisited = vis.add(nextBoard);
                    if (!unvisited) continue;
                    queue.offer(new State(nextBoard, nextIndex));
                }
            }
            steps++;
        }

        return -1;
    }

    private static boolean isSolved(String state) {
        String solved = "01234567";
        return state.equals(solved);
    }

    private static String swap(String s, int i, int j) {
        StringBuilder sb = new StringBuilder(s);
        sb.setCharAt(i, s.charAt(j));
        sb.setCharAt(j, s.charAt(i));
        return sb.toString();
    }

    static class State {
        String board;
        int zeroIndex;

        State(String board, int zeroIndex) {
            this.board = board;
            this.zeroIndex = zeroIndex;
        }
    }
}

```

