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
  questions/notes:

  * Info.copy(int indexA, int indexB) returns a linked list from the
  *     element with ID indexA to the element with ID indexB, or null if
  *     a parameter is out of bounds.


  * Item.compareTo() -- compare just the strings, or everything? I'm
  *     assuming that the val is an ID for the Item, and that only the
  *     strings need to be compared.


  */


public class Info {
    
    /**
     * @author John Gardiner and Adam Sinck
     *
     * This is the Node class used by the Info class.
     */
    class Node {

        private Item data;
        private Node next;

        /**
         * This is the constructor for the Node class.
         */
        public Node (Item newItem) {
            data = newItem;
            next = null;
        }
    
        /**
         * This sets the `data` field of the Node.
         */
        public void setData (Item newData) {
            data = newData;
        }
    
        /**
         * This sets the `next` field of the Node.
         */
        public void setNext (Node nextNode) {
            next = nextNode;
        }

        /**
         * This gets the `data` field of the Node.
         * 
         * @return the data field of the Node.
         */
        public Item getData () {
            return data;
        }

        /**
         * This gets the `next` field of the Node.
         * 
         * @return the address of the next Node.
         */
        public Node getNext () {
            return next;
        }

        /**
         * This returns a readable representation of the Node.
         * 
         * @return a readable string containing the data.
         */    
        public String toString() {
            return "[ " + data + " ]";
        }
    }
    
    //data members of the Info class
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
    public void insert (Item newItem) {
        if (list == null) {
            list = new Node(newItem);
            numNodes++;
        }
        else {
            int result = list.getData().compareTo(newItem);
            if (result > 0) {
                Node myNode = new Node(newItem);
                myNode.setNext(list);
                list = myNode;
                numNodes++;
            }
            else if (result < 0) {
                insert(newItem, list);
            }
        }
    }

    /*
      part two of the insert method; this part uses recursion
    */
    private void insert (Item newItem, Node currentNode) {
        //if we've reached the end of the list
        if (currentNode.getNext() == null) {
            Node insertNode = new Node(newItem); // no null end node now
            currentNode.setNext(insertNode);
            numNodes++;
        }
        else {
            int result = currentNode.getData().compareTo(newItem);
            if (result < 0) {
                result = currentNode.getNext().getData().compareTo(newItem);
                if (result > 0) {
                    Node insertNode = new Node(newItem);
                    insertNode.setNext(currentNode.getNext());
                    currentNode.setNext(insertNode);
                    numNodes++;
                }
                else if (result != 0) {
                    insert(newItem, currentNode.getNext());
                }
            }
            else if (result != 0) {
                insert(newItem, currentNode.getNext());
            }
        }
    }

    /**
     * This deletes the given Item from the list.
     */
    public void delete (Item itemToDelete) {
        if (list != null) {
            int result = list.getData().compareTo(itemToDelete);
            if (result == 0) {
                list = list.getNext();
                numNodes--;
            }
            else if (result < 0) {
                list = delete(itemToDelete, list);
            }
        }
    }

    /*
      part two of the delete method; this part uses recursion
    */
    private Node delete (Item itemToDelete, Node currentNode) {
        Node outputNode = null;
        if (currentNode != null) {
            int result = currentNode.getData().compareTo(itemToDelete);
            //the item is the current item
            if (result == 0) {
                outputNode = currentNode.getNext();
                numNodes--;
            }
            //the item hasn't been found yet
            else if (result < 0) {
                outputNode = currentNode;
		Node nextNode = currentNode.getNext();
                outputNode.setNext(delete(itemToDelete, nextNode));
            }
            //the item is not in the list
            else {
                outputNode = currentNode;
            }
        }
        return outputNode;
    }

    /**
     * This deletes all the Items in the given list from the list.
     */
    public void delete (Info deleteList) {
	Node currentNode = deleteList.getList();
	while (currentNode != null) {
	    Item deleteItem = currentNode.getData();
	    delete(deleteItem);
	    currentNode.setNext(currentNode.getNext());
	}
    }

    /**
     * This returns the Item associated with the given key.
     * 
     * @return the Item associated with the given key, or null if
     *         not found.
     */
    public Item get(int targetIndex) {
        Node currentNode = null;
        int index = 0;      
    
        if (targetIndex >= index) {
            currentNode = list;
        }
        
        while (currentNode != null && index < targetIndex) {
            currentNode = currentNode.getNext();
            index++;
        }
    
        Item result = (currentNode != null) ? currentNode.getData() : null; 
        return result;
    }

    /**
     * This returns the first Node in the list
     * 
     * @return list, the first Node in the list
     */
    public Node getList() {
	return list;
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
    public int indexOf (Item targetItem) {
        final int NOT_FOUND = -1;
        int index = NOT_FOUND;
        boolean found = false;
        Node currentNode = list;
    
        while (currentNode != null && !found) {
            Item currentItem = currentNode.getData();
            if (currentItem.compareTo(targetItem) == 0) {
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
     * Return an Info object that is a copy of the contents from the
     *     first parameter position to the last parameter position.
     *     If the bounds given are out of bounds, return null.
     * 
     * @param targetStart first position to copy
     * @param targetEnd last position to copy
     * 
     * @return new Info object
     */
    Info copy (int targetStart, int targetEnd) {
        Node currentNode = list;
        int index = 0, copyStart = -1, copyEnd = -1;
        
        //Find node to start copy at
        while (currentNode != null && index < targetStart) {
            currentNode = currentNode.getNext();
            index += 1;
        }
        copyStart = index;
        
        Info listCopy = new Info();
        while (currentNode != null && index <= targetEnd) {
	    //Insert takes care of making a deep copy
            listCopy.insert(currentNode.getData()); 
            currentNode = currentNode.getNext();
            index += 1;
        }
        copyEnd = index - 1;
        
        if (
	    targetStart > targetEnd
	    || targetStart != copyStart
	    || targetEnd != copyEnd
	    ) { //Search went out of bounds!
            listCopy = null;
        }
	
        return listCopy;
    }
    
    /**
     * Merge the current Info object with the parameter Info object.
     *     The results should be a new Info object in ascending order
     *     with no duplicates.
     * 
     * @param list Info object to merge with the current Info object
     * 
     * @return new Info object with merged values
     */
    Info merge (Info listToBeMerged) {
        Info mergedList = new Info();
        
        Node currentNode = list;
        while(currentNode != null) {
            mergedList.insert(currentNode.getData());
            currentNode = currentNode.getNext();
        }
        
        currentNode = listToBeMerged.getList();
	
        while(currentNode != null) {
            mergedList.insert(currentNode.getData());
            currentNode = currentNode.getNext();
        }
        
        return mergedList;
    }

    /**
     * This returns a readable representation of the Info.
     * 
     * @return a readable string containing the Nodes.
     */
    public String toString() {
        final int PADDING = 3;
        Node currentNode = list;
        String myString = "";
        int index = 0;
        while (currentNode != null) {
            String value = currentNode.toString();
            myString += value;
            currentNode = currentNode.getNext();
        
            if (currentNode != null) {
                myString += "--.\n.";
        
                //this displays as follows:
                // [value]--.
                // .-------'
                // `-[value]--.
                // .---------'
                // `-[value]
                int dashes = value.length() + PADDING;
                if (index == 0) {
                    dashes -= 2;
                }
                for (
                     int iterator = 0;
                     iterator < dashes;
                     iterator++
                     ) {
                    myString += "-";
                }
                myString += "'\n`-";
                index++;
            }
        }
        return myString;
    }

    
}


