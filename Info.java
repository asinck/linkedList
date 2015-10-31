/**
 * @author John Gardiner and Adam Sinck
 * 
 * This program will implement a singly linked list. 
 *
 * This program has two classes: Info and Node. The Info class is a
 * singly-linked list, using Nodes. The Node class has an Item (an
 * external class), and the next Node.
 */

/*
questions:

* Info.copy(int indexA, int indexB) returns a linked list from the
*     element with ID indexA to the element with ID indexB, right?

* Item.compareTo() -- compare just the strings, or everything?

* Node.next: is this set directly by the Info class?

*/

public class Info {
    
    //data members
    private Node list;
    private int numNodes;

    //methods

    /**
     * This is the constructor for the class.
     */
    public Info () {
	list = null;
	numNodes = 0;
    }

    /**
     * This returns the Item associated with the given key.
     * 
     * @return the Item associated with the given key, or null if
     *         not found.
     */
    public Item get(int key) {
	Item foundItem = null;
	Node currentNode = list;
	boolean found = false;
	
	while (currentNode != null && !found) {
	    Item currentItem = currentNode.getData();
	    if (currentItem.getVal() == key) {
		foundItem = currentItem;
		found = true;
	    }
	    else {
		currentNode = currentNode.getNext();
	    }
	}
	return foundItem;
    }

    /**
     * This returns the number of Nodes in the list.
     * 
     * @return numNodes, the number of Nodes in the list.
     */
    public int count () {
	return numNodes;
    }

    /**
     * This returns the index of the given Item.
     * 
     * @return index, the index of the given item.
     */
    public int indexOf (Item myItem) {
	final int NOT_FOUND = -1;
	int index = NOT_FOUND;
	boolean found = false;
	Node currentNode = list;
	
	while (currentNode != null && !found) {
	    Item currentItem = currentNode.getData();
	    if (currentItem.compareTo(myItem) == 0) {
		found = true;
	    }
	    else {
		currentNode = currentNode.getNext();
	    }
	    index++;
	}
	
	if (!found) {
	    index = NOT_FOUND;
	}
	return index;
    }

    /**
     * This returns a readable representation of the Info.
     * 
     * @return a readable string containing the Nodes.
     */
    public String toString() {
	Node currentNode = list;
	String myString = "";
	final int PADDING = 5;
	while (currentNode != null) {
	    String value = currentNode.toString();
	    myString += value;
	    currentNode = currentNode.getNext();
	    
	    if (currentNode != null) {
		myString += "--.\n.";
		
		//this displays as follows:
		// [value]--.
		// .-------/
		// \-[value]--.
		// .---------/
		// \-[value]
		for (
		     int iterator = 0;
		     iterator < value.length() + PADDING;
		     iterator++
		     ) {
		    myString += "-";
		}
		myString += "/\n\\-";
	    }
	}
	return myString;
    }
}

/**
 * @author John Gardiner and Adam Sinck
 *
 * This is the Node class used by the Info class.
 */
class Node {

    private Item data;
    private Node next;

    //constructor for the class
    public Node (Item myItem) {
	data = myItem;
	next = null;
    }

    public void setData (Item myData) {
	data = myData;
    }
    public void setNext (Node nextNode) {
	next = nextNode;
    }

    public Item getData () {
	return data;
    }

    public Node getNext () {
	return next;
    }
    
    public String toString() {
	return "[ " + data + " ]";
    }
}
