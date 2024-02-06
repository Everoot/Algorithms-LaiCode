---
tags:
    - Hash Table
    - Linked List
    - Design
    - Doubly-Linked List
---

# 146 LRU Cache

Design a data structure that follows the constraints of a **[Least Recently Used (LRU) cache](https://en.wikipedia.org/wiki/Cache_replacement_policies#LRU)**.

Implement the `LRUCache` class:

- `LRUCache(int capacity)` Initialize the LRU cache with **positive** size `capacity`.
- `int get(int key)` Return the value of the `key` if the key exists, otherwise return `-1`.
- `void put(int key, int value)` Update the value of the `key` if the `key` exists. Otherwise, add the `key-value` pair to the cache. If the number of keys exceeds the `capacity` from this operation, **evict** the least recently used key.

The functions `get` and `put` must each run in `O(1)` average time complexity.



**Example 1:**

```
Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4
```



**Solution:**

```java
class LRUCache {
  	static class Node{
        int key; 
        int value;
        Node next;
        Node prev;
        Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, Node> cache = new HashMap<Integer, Node>();
    private int size;
    private int capacity;
    private Node head;
    private Node tail;
    // 伪头部和伪尾部节点，用于方便地添加和删除节点

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;

        // 初始化伪头部和伪尾部节点
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        
    }
    
    public int get(int key) {
        Node node = cache.get(key);
        if (node == null){
            return -1;
        }

        // 将访问的节点移动到双向链表的头部
        moveToHead(node);
        return node.value;
        
    }
    
    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null){
            // 如果键不存在，创建新的节点
            Node newNode = new Node(key, value);
            // 添加进哈希表
            cache.put(key, newNode);
             // 添加至双向链表的头部
            addToHead(newNode);
            size = size + 1;
            if (size > capacity){
                // 如果超过容量，删除双向链表的尾部节点
                Node tail = removeTail();
                cache.remove(tail.key);
                size = size - 1;
            }
        }else{
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(Node node){
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(Node node){
        removeNode(node);
        addToHead(node);
    }

    private Node removeTail(){
        Node res = tail.prev;
        removeNode(res);
        return res;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

// TC: O(1)
// SC: O(1)
```

