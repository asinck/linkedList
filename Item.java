/**
 * @author John Gardiner and Adam Sinck
 * 
 * This program will implement one class (the item class), which will
 * be used by the Info class. It implements the Comparable interface.
 */

package info;

public class Item implements Comparable<Item> {
    public final static String DELIMITER = " ";
    String str;
    int val;

    /**
     * This is the constructor for the class.
     * 
     * @param myString, the value for str
     * @param myValue, the value for val
     */
    public Item (String myString, int myValue) {
        str = myString;
        val = myValue;
    }
    
    /**
     * This is the copy constructor for the class.
     * 
     * @param newItem, the item to be made
     */
    public Item (Item newItem) {
        str = newItem.str;
        val = newItem.val;
    }

    /**
     * This compares the values of the two strings.
     *
     * @param inputItem, the item to compare to
     * 
     * @return the result of the comparison
     */
    @Override public int compareTo(Item inputItem) {
        Item otherItem = (Item) inputItem;
        int compareResult = str.compareToIgnoreCase(otherItem.str);
        return compareResult;
    }

    /**
     * This returns a readable representation of the item.
     * 
     * @return a readable string containing the str and the val.
     */
    @Override public String toString() {
        return str + DELIMITER + val;
    }
}
