/**
 * @author John Gardiner and Adam Sinck
 *
 * The ItemTest class contains a series of JUnit test methods that
 * operate on methods of the Item class. Output for both correct and
 * incorrect inputs are tested, in addition to other aspects of the
 * specifications.
 */

package info;

import static org.junit.Assert.*;

import org.junit.Test;

public class ItemTest {
    /**
     * Class constructor should initialize object with the two values
     * passed to the constructor.
     */
    @Test
    public void testItem() {        
        Item item = new Item("A", 1);
        //test object array is initialized and count set to 0
        assertEquals("constructor failed. ", "A" + Item.DELIMITER + "1",
                     item.toString());
    }
        
    /**
     * Class constructor should initialize object using values from
     * another Item object.
     */
    @Test
    public void testItemCopy() {    
        Item item = new Item("A", 1);
        Item itemCopy = new Item(item);
        //test object array is initialized and count set to 0
        assertEquals("constructor failed. ", "A" + Item.DELIMITER + "1",
                     itemCopy.toString());
    }
        
    /**
     * compareTo should compare two Item objects, then return 0 if
     * this = other, -1 if this < other, 1 if this > other.
     */
    @Test
    public void testCompareTo() {   
        Item itemA = new Item("ABC", 1);
        Item itemB = new Item("CBA", 1);
        Item itemD = new Item("ABC", 1);
        Item itemE = new Item("abc", 1);
                        
        assertTrue("failed to compare to equal items. ",
                   itemA.compareTo(itemD) == 0);
        assertTrue("failed to compare to equal items (ignore case). ",
                   itemA.compareTo(itemE) == 0);
        assertTrue("failed to compare items. this < other",
                   itemA.compareTo(itemB) < 0);
        assertTrue("failed to compare items. this > other",
                   itemB.compareTo(itemA) > 0);
    }
}
