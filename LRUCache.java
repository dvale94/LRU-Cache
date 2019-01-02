import java.util.HashMap;

/**
 * A Least Recently Used Cache implemented with the use of a HashMap and a 
 * Doubly Linked List. The HashMap will hold the keys and address of the Nodes 
 * of the Doubly Linked List. The Doubly Linked List implements a queue that
 * will hold the values of the keys. Whenever any entry is accessed, it will 
 * be moved to the top of the queue. When the cache is full and a entry needs 
 * to be added, the entry at the end of the queue is removed and the new entry 
 * is inserted at the beginning of the queue.
 * 
 * Daniel Valencia
 **/

/* 
 * Class for the nodes of the doubly linked list.
 */
class Node {
	int value;
	int key;
	Node left;
	Node right;
}

public class LRUCache {

    HashMap<Integer, Node> map;
    Node head;
    Node tail;
    final int LRU_SIZE = 4;
    
    /* Constructor for the LRUCache. For the implementation, the LRU cache size  
     * is 4, but it can be made dynamic.
     */
    public LRUCache() {
        map = new HashMap<Integer, Node>();
    }
    
    
    /* 
     * Sets a node as the most recently accessed
     */
    public void addToHead(Node node) {
        node.right = head;
	node.left = null;
	if (head != null) {
            head.left = node;
        }
        head = node;
	if (tail == null) {
            tail = head;
        }    
    }
    
    /* Removes a node from the queue and sets the values for previous node 
     * and next node of the surrounding nodes accordingly 
     */
    public void removeNode(Node node) {
        if (node.left != null) {
            node.left.right = node.right;
	} 
        else {
            head = node.right;
	}

	if (node.right != null) {
            node.right.left = node.left;
	} 
        else {
            tail = node.left;
	}
    }  
    
    /* 
     * Inserts the given entry into the queue.
     * If the key exist, update the value and move it to head of queue
     * If max queue size is reached, make room for the new entry.
     */
    public void putEntry(int key, int value) {
	if (map.containsKey(key)) {
            Node entry = map.get(key);
            entry.value = value;
            removeNode(entry);
            addToHead(entry);
	} 
        else {
            Node newEntry = new Node();
            newEntry.left = null;
            newEntry.right = null;
            newEntry.value = value;
            newEntry.key = key;
            
            if (map.size() < LRU_SIZE) {
                addToHead(newEntry);
            } 
            else {
		map.remove(tail.key);
		removeNode(tail);				
		addToHead(newEntry);
            }

            map.put(key, newEntry);
	}
}
    
    /* 
     * References hasmap to return the value of the node with the given key.
     * Sets the returned node as the most recently accessed.
     * If the node does not eist, returns -1.
     */
    public int get(int key) {
        if (map.containsKey(key)) {
            Node entry = map.get(key);
            removeNode(entry);
            addToHead(entry);
            return entry.value;
	}
	return -1;
    }
    
    /* Returns a HashMap of the current keys and nodes in the
     * cache
     */
	public HashMap list() {
            HashMap<Integer, Node> listMap = new HashMap<>();
            Node curr = head;
		
            while(curr != null) {
		listMap.put(curr.key, curr);
                curr = curr.right;
            }
		
            return listMap;
	}
}
