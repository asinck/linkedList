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
     * This inserts an Item into the list.
     * 
     * @param myItem the Item to be inserted
     */
    public void insert (Item myItem) {
	if (list == null) {
	    list = new Node(myItem);
	    numNodes++;
	}
	else {
	    int compare = list.getData().compareTo(myItem);
	    if (compare < 0) {
		Node myNode = new Node(myItem);
		myNode.setNext(list);
		list = myNode;
		numNodes++;
	    }
	    else if (compare < 0) {
		insert(myItem, list);
	    }
	}
    }
    /*
      part two of the insert method; this part uses recursion
      */
    private void insert (Item myItem, Node myNode) {
	//if we've reached the end of the list
	if (myNode.getNext() == null) {
	    Node node = new Node(myItem); // no null end node now
	    myNode.setNext(node);
	    numNodes++;
	}
	else {
	    int compare = myNode.getData().compareTo(myItem);
	    if (compare < 0) {
		compare = myNode.getNext().getData().compareTo(myItem);
		if (compare > 0) {
		    Node node = new Node(myItem);
		    node.setNext(myNode.getNext());
		    myNode.setNext(node);
		    numNodes++;
		}
		else if (compare != 0) {
		    insert(myItem, myNode.getNext());
		}
	    }
	    else if (compare != 0) {
		insert(myItem, myNode.getNext());
	    }
	}
    }

    /**
     * This deletes the given item from the list.
     */
    public void delete (Item myItem) {
	if (list != null) {
	    int compare = list.getData().compareTo(myItem);
	    if (compare == 0) {
		list = list.getNext();
		numNodes--;
	    }
	    else if (compare < 0) {
		list = delete(myItem, list);
	    }
	}
    }
    /*
      part two of the delete method; this part uses recursion
      */
    private Node delete (Item myItem, Node myNode) {
	Node node = null;
	if (myNode != null) {
	    int compare = myNode.getData().compareTo(myItem);
	    //the item is the current item
	    if (compare == 0) {
		node = myNode.getNext();
		numNodes--;
	    }
	    //the item hasn't been found yet
	    else if (compare < 0) {
		node = myNode;
		node.setNext(delete(myItem, myNode.getNext()));
	    }
	    //the item is not in the list
	    else {
		node = myNode;
	    }
	}
	return node;
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
