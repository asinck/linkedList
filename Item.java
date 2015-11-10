//package info;

/**
 * @author John Gardiner and Adam Sinck
 * 
 * This program will implement one class (the item class), which will
 * be used by the Info class. It implements the Comparable interface.
 *
 */

public class Item implements Comparable<Item> {
    public final static String DELIMITER = ", ";
    String str;
    int val;

    /**
     * This is the constructor for the class.
     */
    public Item (String myString, int myValue) {
        str = myString;
        val = myValue;
    }
    
    /**
     * This is the copy constructor for the class.
     */
    public Item (Item newItem) {
        str = newItem.str;
        val = newItem.val;
    }

    /**
     * This compares the values of the two strings.
     */
    @Override public int compareTo(Comparable inputItem) {
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
