//package info;

/**
 * @author John Gardiner and Adam Sinck
 * 
 * This program will implement one class (the item class), which will
 * be used by the Info class. It implements the Comparable interface.
 *
 */

public class Item implements Comparable {
    
    String str;
    int val;

    /**
     * This is one of the constructors for the class.
     * It accepts a string and an int.
     */
    public Item(String myString, int myValue) {
        str = myString;
        val = myValue;
    }

    /**
     * This is the other constructor for the class.
     * It accepts an Item.
     */
    public Item(Item newItem) {
        str = newItem.str;
        val = newItem.val;
    }

    /**
     * This compares the values of the two strings.
     */
    public int compareTo(Comparable item) {
        int lenOne = str.length();
        Item myItem = (Item) item;
        String str2 = myItem.str;
        int lenTwo = str2.length();
        int returnValue = 0;
        int index = 0;

        while (index < lenOne && index < lenTwo && returnValue == 0) {
            returnValue = str.charAt(index) - str2.charAt(index);
            index += 1;
        }
	
        return returnValue;
    }

    /**
     * This returns a readable representation of the item.
     * 
     * @return a readable string containing the str and the val.
     */
    public String toString() {
        return "String: " + str + " . Value: " + val;
    }
}

/**
 * @author John Gardiner and Adam Sinck
 * 
 * This interface is for the Item class.
 */
interface Comparable {
    int compareTo(Comparable item);
}
