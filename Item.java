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
     * This is the constructor for the class.
     */
    public Item(String myString, int myValue) {
        str = myString;
        val = myValue;
    }
    
    /**
     * This compares the values of the two strings.
     */
    public int compareTo(Comparable item) {
        int lenOne = str.length();
        Item myItem = (Item) item;
        String str2 = myItem.getStr();
        int lenTwo = str2.length();
        int returnValue = 0;
        int index = 0;

        while (index < lenOne && index < lenTwo && returnValue == 0) {
            returnValue = str.charAt(index) - str2.charAt(index);
            index += 1;
        }

        if (returnValue == 0) {
            returnValue = val - myItem.getVal();
        }
        return returnValue;
    }

    /**
     * This returns the str of the item.
     * 
     * @return a the str of the item.
     */
    public String getStr() {
        return str;
    }

    /**
     * This returns the val of the item.
     * 
     * @return a the val of the item.
     */
    public int getVal() {
        return val;
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
