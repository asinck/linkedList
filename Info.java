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
     *
     * This is the Node inner class used by the Info class.
     */
    private class Node {
        Item data;
        Node next;

        /**
         * This is the constructor for the Node class.
         *
         * @param item, an Item object to be stored in node
         */
        public Node (Item newItem) {
            data = new Item(newItem);
            next = null;
        }
    }
    
    /* Data Members */
    private Node list;
    private int numNodes;

    /* Public Methods */
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
     * @param itemToInsert the Item to be inserted
     */
    public void insert (Item itemToInsert) {
        //make a list of one item
        Info insertList = new Info();
        insertList.list = new Node(itemToInsert);
        
        //make a new list that merges the current list and insertList
        Info newList = merge(insertList);
        
        //set list to newList
        list = newList.list;

        //take care of the length of list
        numNodes = newList.count();
    }
    
    /**
     * This deletes the given Item from the list.
     *
     * @param itemToDelete Item to be removed from this Info object
     */
    public void delete (Item itemToDelete) {
        Info deleteList = new Info();
        deleteList.insert(itemToDelete);
        
        delete(deleteList);
    }
    
    /**
     * Remove a list of items from this info list.  The result is that
     *     items common to this Info object and the input Info object
     *     are removed from this Info object.
     *
     * @param itemsToDelete Info object to containing items to remove
     *        from this Info object
     */
    public void delete (Info itemsToDelete) {
        Node currentNode = list;
        Node previousNode = null;
        Node deleteNode = itemsToDelete.list;
        
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
     * 
     * @return the Item associated with the given key, or null if
     *         not found.
     */
    public Item get (int targetIndex) {
        Node currentNode = null;
        int index = 0;      
    
        if (targetIndex >= index && targetIndex <= numNodes - 1){
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
     * 
     * @return numNodes, the number of Nodes in the list.
     */
    public int count () {
        return numNodes;
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
        Node currentNode = null;
        int index = 0;
        Info listCopy = null;
        Node copyNode = null;
        
        //Perform search IFF bounds are valid
        if (targetStart <= targetEnd &&
            targetStart >= index && targetEnd <= numNodes - 1) { 
            currentNode = list;
            listCopy = new Info();
            copyNode = listCopy.list;
        }
        
        //Find node to start copy at
        while (currentNode != null && index < targetStart) {
            currentNode = currentNode.next;
            index++;
        }
        
        //Copy nodes into listCopy
        while (currentNode != null && index <= targetEnd) {
            if (copyNode == null) {
                listCopy.list = new Node(currentNode.data);
                copyNode = listCopy.list;
            }
            else {
                copyNode.next = new Node(currentNode.data);
                copyNode = copyNode.next;
            }
            currentNode = currentNode.next;
            listCopy.numNodes++;
            index++;
        }
        
        return listCopy;
    }
        
    /**
     * Merge the current Info object with the parameter Info object.
     *     The results should be a new Info object in ascending order
     *     with no duplicates.
     *
     * @param inputList Info object to merge with the current Info object
     *
     * @return new Info object with merged values
     */
    Info merge (Info inputList) {
        Info mergedList = new Info();
        
        Node thisCurrentNode = list;
        Node inputCurrentNode = inputList.list;
        
        //Insert nodes into new list
        Node mergedCurrentNode = mergedList.list;
        while (thisCurrentNode != null || inputCurrentNode != null) {
            if (
                inputCurrentNode == null ||
                (thisCurrentNode != null &&
                 thisCurrentNode.data.compareTo(inputCurrentNode.data) < 1 )) {
                if (mergedCurrentNode == null) {
                    mergedList.list = new Node(thisCurrentNode.data);
                    mergedCurrentNode = mergedList.list;
                }
                else {
                    mergedCurrentNode.next = new Node(thisCurrentNode.data);
                    mergedCurrentNode = mergedCurrentNode.next;
                }
                //If the two nodes are equal, update currentNodeB as
                //well so node is not reinserted
                if (inputCurrentNode != null &&
                    thisCurrentNode.data.compareTo(inputCurrentNode.data) == 0 ) {
                    inputCurrentNode = inputCurrentNode.next;
                }       
                thisCurrentNode = thisCurrentNode.next;
            }
            else {
                if (mergedCurrentNode == null) {
                    mergedList.list = new Node(inputCurrentNode.data);
                    mergedCurrentNode = mergedList.list;
                }
                else {
                    mergedCurrentNode.next = new Node(inputCurrentNode.data);
                    mergedCurrentNode = mergedCurrentNode.next;
                }               
                inputCurrentNode = inputCurrentNode.next;
            }
            mergedList.numNodes++;
        }
      
        return mergedList;
    }

    /**
     * This returns the index of the given Item.
     *
     * @return index, the index of the given item.
     */
    public int indexOf (Item targetItem) {
        final int NOT_FOUND = -1;
        int index = 0;
        int result = NOT_FOUND;
        Node currentNode = list;
    
        while (currentNode != null && result == NOT_FOUND) {
            Item currentItem = currentNode.data;
            if (currentItem.compareTo(targetItem) == 0) {
                result = index;
            }
            else {
                currentNode = currentNode.next;
                index++;
            }
        } 
     
        return result;
    }

    /**
     * This returns a readable representation of the Info.
     *
     * @return a readable string containing the Nodes.
     */
    @Override 
    public String toString() {
        Node currentNode = list;
        StringBuilder output = new StringBuilder();
        
        while (currentNode != null) {
            output.append(currentNode.data.toString() + "\n");
            currentNode = currentNode.next;
        }
        
        return output.toString();
    }
}
