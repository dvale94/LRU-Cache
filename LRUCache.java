package lrucache;
import java.util.HashMap;

/**
 *
 * @author Daniel Valencia
 */

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
     * Inserts the given element into the queue.
     * If the key exist, update the value and move it to head of queue
     * If max queue size is reached, make room for new element.
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
            
            if (map.size() > LRU_SIZE) {
		map.remove(tail.key);
		removeNode(tail);				
		addToHead(newEntry);
            } 
            else {
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
}
