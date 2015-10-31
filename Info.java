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
	Node myNode = new Node(myItem);
	
	if (count() == 0) {
	    list = myItem;
	    myNode.setNext(null)
	}
	
	else {
	    Node currentNode = list;
	    Item currentItem = currentNode.getData();
	    Node nextNode = null;
	    Item nextItem = null;
	    boolean hasNext = (currentNode.getNext() != null);
	    
	    if (hasNext) {
		nextNode = currentNode.getNext();
		nextItem = nextNode.getData();
	    }
	    
	    boolean inList = currentItem.compareTo(nextItem) == 0;
	    boolean spotFound = false;
	    
	    //continue while
	    // - The spot to insert the item has not been found
	    // - The current Node has a next Node
	    while (!spotFound && hasNext) {
		compare = currentNode.compareTo(nextItem);
		
		if (compare < 1) {
		    currentNode = nextNode;
		    currentItem = nextItem;
		    nextNode = nextNode.getNext();
		    nextItem = nextNode.getData();
		}
		else {
		    spotFound = true;
		    inList = (compare == 0);
		}
	    }

	    if (!inList) {
		myNode.setNext(currentNode.getNext());
		currentNode.setNext(myNode);
	    }
	}
	numNodes += 1;
    }


    public void delete(Item myItem) {
	Node currentNode = list;
	boolean found = false;

	if (currentNode != null) {
	    //also need to track the previous node for pointers
	    currentItem = currentNode.getData();
	    
	    while (currentNode != null && !found) {
		if (currentItem.compareTo(myItem) == 0) {
		    found = true;
		    currentNode.setNext( 
		}
	    }
	}
    }


    /**
     * This returns the Item associated with the given key.
     * 
     * @return the Item associated with the given key, or null if
     *         not found.
     */
    public Item get(int key) {
	const int NOT_FOUND = null;
	Item foundItem = NOT_FOUND;
	    //return NOT_FOUND;
	if (list == null) {
	    Node currentNode = list;
	    Item currentItem = currentNode.getData();
	    boolean found = false;
	    while (currentNode.getNext() != null && !found) {
		currentNode = currentNode.getNext();
		currentItem = currentNode.getData();
		if (currentItem.getVal() == key) {
		    foundItem = currentItem;
		    found = true;
		}
	    }
	}
	return foundItem;
    }

    public int indexOf (Item myItem) {
	const final int NOT_FOUND = -1;
	boolean found = false;
	Node currentNode = list;
	int index = NOT_FOUND;
	
	while (currentNode != null && !found) {
	    currentItem = currentNode.getData();
	    if (currentItem.compareTo(myItem) == 0) {
		found = true;
	    }
	    if (!found) {
		currentNode = currentNode.next();
	    }
	    index++;
	}

	return index;
    }

    /**
     * This returns the number of Nodes in the list.
     * 
     * @return numNodes, the number of Nodes in the list.
     */
    public void count () {
	return numNodes;
    }
    
}

private class Node {

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

}
