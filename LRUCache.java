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
}
