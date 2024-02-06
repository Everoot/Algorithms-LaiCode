# 205 Implement LRU Cache (Lai)

Implement a least recently used cache. It should provide set(), get() operations. If not exists, return null (Java), false (C++), -1(Python).

**Solution:**

```java
public class Solution<K, V> {
  // limit is the max capacity of the cache
  static class Node<K,V>{
    K key;
    V value;
    Node<K, V> next;
    Node<K, V> prev;
    Node(K key, V value){
      this.key = key;
      this.value = value;
    }
  }

  private Map<K, Node<K, V>> map = new HashMap<K, Node<K, V>>();
  private int size;
  private int limit;
  private Node<K, V> head;
  private Node<K, V> tail;

  public Solution(int limit) {
    this.size = 0;
    this.limit = limit;
    head = new Node(-1, -1);
    tail = new Node(-1, -1);
    head.next = tail;
    tail.prev = head;
  }
  
  public void set(K key, V value) {
    Node node = map.get(key);
    if (node == null){
      node = new Node(key, value);
      map.put(key, node);
      size = size + 1;
      addToHead(node);
      if (size > limit){
        Node<K,V> tailNode = removeTail();
        map.remove(tailNode.key);
        size = size - 1;
      }
    }else{
      node.value = value;
      moveToHead(node);
    }
  }

  private Node<K, V> removeTail(){
    Node<K, V> tailNode = tail.prev;
    removeNode(tailNode);
    return tailNode;
  }

  private void moveToHead(Node<K, V> node){
    removeNode(node);
    addToHead(node);
  }

  private void removeNode(Node<K, V> node){
    node.prev.next = node.next;
    node.next.prev = node.prev;
  }

  private void addToHead(Node<K, V> node){
    node.prev = head;
    node.next = head.next;
    head.next.prev = node;
    head.next = node;
  }
  
  public V get(K key) {
    Node<K, V> node = map.get(key);
    if (node == null){
      return null;
    }
    moveToHead(node);
    return node.value;
  }
}
```



```java
public class Solution<K, V> {
  // limit is the max capacity of the cache
  static class Node<K, V>{
    K key;
    V value;
    Node<K, V> next;
    Node<K, V> prev;
    Node(K key, V value){
      this.key = key;
      this.value = value;
    }

    void update(K key, V value){
      this.key = key;
      this.value = value;
    }
  }

  // make it final for the pre-defined size limit of the cache.
  private final int limit;
  // record all the time the head and tail of the double linked list.
  private Node<K, V> head;
  private Node<K, V> tail;

  // maintains the relationship of key and its corresponding node
  // in the double linked list
  private Map<K, Node<K, V>> map;

  public Solution(int limit) {
    this.limit = limit;
    this.map = new HashMap<K, Node<K,V>>();
  }
  
  public void set(K key, V value) {
    Node<K, V> node = null;
    // 1. if the key already in the cache, we need to update its value
    // and move it to head(most recent position)
    if (map.containsKey(key)){
      node = map.get(key);
      node.value = value;
    }else if (map.size() < limit){
      node = new Node<K, V>(key, value);
    }else{
      // 3. if the key is not in the cache and we don't have space,
      // we need to evict the tail and reuse the node let it maintain 
      // the new key, value and put it to head.
      node = tail;
      remove(node);
      node.update(key, value);
    }
    append(node);
  }



  private Node<K, V> append(Node<K, V> node){
    map.put(node.key, node);
    if (head == null){
      head = tail = node;
    }else{
      node.next = head;
      head.prev = node;
      head = node;
    }
    return node;
  }
  
  public V get(K key) {
    Node<K, V> node = map.get(key);
    if (node == null){
      return null;
    }
    // even it is a read operating, it is still a write operation to 
    // the double linked list, and we need to move the node to head.
    remove(node);
    append(node);
    return node.value;
  }

  private Node<K, V> remove(Node<K, V> node){
    map.remove(node.key);
    if (node.prev != null){
      node.prev.next = node.next;
    }

    if (node.next != null){
      node.next.prev = node.prev;
    }

    if (node == head){
      head = head.next;
    }

    if (node == tail){
      tail = tail.prev;
    }

    node.next = node.prev = null;
    return node;
  }
}

// Time: O(1) for all operations
```

