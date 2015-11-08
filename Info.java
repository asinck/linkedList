//package info;

/**
 * @author John Gardiner and Adam Sinck
 * 
 * This program will implement a singly linked list. 
 *
 * This program has two classes: Info and Node. The Info class is a
 * singly-linked list, using Nodes. The Node class has an Item (an
 * external class), and the next Node.
 */

public class Info {
    
    /* Inner Class */
    /**
     * @author John Gardiner and Adam Sinck
     * This is the Node inner class used by the Info class.
     */
    private class Node {

        Item data;
        Node next;

        /**
         * This is the constructor for the Node class.
         */
        public Node (Item newItem) {
            data = new Item(newItem);
            next = null;
        }

        /**
         * This returns a readable representation of the Node.
         * @return a readable string containing the data.
         */    
        @Override public String toString() {
            return "[ " + data + " ]";
        }
    }
    /* End of Inner Class */

    /* Data Members */
    private Node list;
    private int numNodes;

    /**
     * This is the constructor for the class.
     */
    public Info () {
        list = null;
        numNodes = 0;
    }
    
    /**
     * This inserts an Item into the list.
     * @param itemToInsert the Item to be inserted
     */
    public void insert (Item itemToInsert) {
        Info insertList = new Info();
        insertList.list = new Node(itemToInsert);
        insertList.merge(insertList);
        Info newList = merge(insertList);
        list = newList.list;
        numNodes = newList.count();
    }

    /**
     * This deletes the given Item from the list.
     */
    public void delete (Item itemToDelete) {
    	Info deleteList = new Info();
    	deleteList.insert(itemToDelete);
    	delete(deleteList);
    }
    public void delete(Info itemsToRemove) {
    	Node currentNode = list;
    	Node previousNode = null;
    	Node deleteNode = itemsToRemove.list;
    	
    	while (currentNode != null && deleteNode != null) {
            if (previousNode == null &&
                currentNode.data.compareTo(deleteNode.data) == 0) {
                //Item to be deleted is root node, and must be handled accordingly
                list = list.next;
                numNodes--;
                currentNode = list;
                deleteNode = deleteNode.next;
            }
            else if (currentNode.data.compareTo(deleteNode.data) == 0) {
                previousNode.next = currentNode.next;
                numNodes--;
                previousNode = currentNode;
                currentNode = currentNode.next;
                deleteNode = deleteNode.next;
            }
            //Else no match, so select smaller next node, and set
            //appropriate reference
            else if (currentNode.data.compareTo(deleteNode.data) < 0) {
                previousNode = currentNode;
                currentNode = currentNode.next;
            }
            else {
                deleteNode = deleteNode.next;
            }
    	}
    }
    
    /**
     * This returns the Item associated with the given key.
     * @return the Item associated with the given key, or null if
     *     not found.
     */
    public Item get (int targetIndex) {
        Node currentNode = null;
        int index = 0;      
    
        if (targetIndex >= index) {
            currentNode = list;
        }
        
        while (currentNode != null && index < targetIndex) {
            currentNode = currentNode.next;
            index++;
        }
    
        Item result = (currentNode != null) ? currentNode.data : null; 
        return result;
    }

    /**
     * This returns the number of Nodes in the list.
     * @return numNodes, the number of Nodes in the list.
     */
    public int count () {
        return numNodes;
    }

    /**
     * This returns the index of the given Item.
     * @return index, the index of the given item.
     */
    public int indexOf (Item targetItem) {
        final int NOT_FOUND = -1;
        int index = NOT_FOUND;
        boolean found = false;
        Node currentNode = list;
    
        while (currentNode != null && !found) {
            Item currentItem = currentNode.data;
            if (currentItem.compareTo(targetItem) == 0) {
                found = true;
            }
            else {
                currentNode = currentNode.next;
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
     * @param targetStart first position to copy
     * @param targetEnd last position to copy
     * @return new Info object
     */
    Info copy (int targetStart, int targetEnd) {
        Node currentNode = list;
        int index = 0, copyStart = -1, copyEnd = -1;
        
        //Find node to start copy at
        while (currentNode != null && index < targetStart) {
            currentNode = currentNode.next;
            index++;
        }
        copyStart = index;
        
        Info listCopy = new Info();
        //Insert first item into copied list
        if(currentNode != null) {
            listCopy.insert(currentNode.data);
            currentNode = currentNode.next;
            index++;
        }
        
        //Insert remaining items into copied list
    	Node copyNode = listCopy.list;
        while(currentNode != null && index <= targetEnd) {
            copyNode.next = new Node(currentNode.data);
            copyNode = copyNode.next;
            currentNode = currentNode.next;
            listCopy.numNodes++;
            index++;
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
     * @param list Info object to merge with the current Info object
     * @return new Info object with merged values
     */
    Info merge (Info inputList) {
        Info mergedList = new Info();
        
        Node thisCurrentNode = list;
        Node inputCurrentNode = inputList.list;
        
        if (
            thisCurrentNode != null &&
            (inputCurrentNode == null
             || thisCurrentNode.data.compareTo(inputCurrentNode.data) < 1
             )
            ) {
            mergedList.list = new Node(thisCurrentNode.data);
            //if the two nodes are equal, update currentNodeB as well
            //so node is not reinserted
            if (inputCurrentNode != null && thisCurrentNode.data.compareTo(inputCurrentNode.data) == 0 ) {
                inputCurrentNode = inputCurrentNode.next;
            }
            thisCurrentNode = thisCurrentNode.next;
            mergedList.numNodes++;
        }
        else if (inputCurrentNode != null){
            mergedList.list = new Node(inputCurrentNode.data);
            inputCurrentNode = inputCurrentNode.next;
            mergedList.numNodes++;
        }
        
        Node mergedCurrentNode = mergedList.list;
        while (thisCurrentNode != null && inputCurrentNode != null) {
            if (thisCurrentNode.data.compareTo(inputCurrentNode.data) < 1 ) {
            	mergedCurrentNode.next = new Node(thisCurrentNode.data);
            	//if the two nodes are equal, update currentNodeB as
            	//well so node is not reinserted
            	if (thisCurrentNode.data.compareTo(inputCurrentNode.data) == 0 ) {
                    inputCurrentNode = inputCurrentNode.next;
            	}
            	thisCurrentNode = thisCurrentNode.next;
            }
            else {
            	mergedCurrentNode.next = new Node(inputCurrentNode.data);
            	inputCurrentNode = inputCurrentNode.next;
            }
            mergedCurrentNode = mergedCurrentNode.next;
            mergedList.numNodes++;
        }
        
        //add any remaining nodes to the new list
        while (thisCurrentNode != null) {
            mergedCurrentNode.next = new Node(thisCurrentNode.data);
            thisCurrentNode = thisCurrentNode.next;
            mergedCurrentNode = mergedCurrentNode.next;
            mergedList.numNodes++;
        }
        
        while (inputCurrentNode != null) {
            mergedCurrentNode.next = new Node(inputCurrentNode.data);
            inputCurrentNode = inputCurrentNode.next;
            mergedCurrentNode = mergedCurrentNode.next;
            mergedList.numNodes++;
        }
       
        return mergedList;
    }

    /**
     * This returns a readable representation of the Info.
     * @return a readable string containing the Nodes.
     */
    @Override public String toString() {
        Node currentNode = list;
        String outputString = "";
        while (currentNode != null) {
            String value = currentNode.toString();
            outputString += value;
            currentNode = currentNode.next;
            if (currentNode != null) {
                outputString += "\n";
            }
        }
        return outputString;
    }
}
