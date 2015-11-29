/**
 * @author John Gardiner and Adam Sinck
 *
 * The InfoTest class contains a series of JUnit test methods that
 * operate on methods of the Info class. Output for both correct and
 * incorrect inputs are tested, in addition to other aspects of the
 * specifications.
 */

package info;

import static org.junit.Assert.*;

import org.junit.Test;

public class InfoTest {
    /**
     * Class constructor should initialize a new array and set item
     * count to 0. Furthermore, a Info object should be able to change
     * size to hold a large number of items. The constant N determines
     * how many test values are loaded into the array.
     */
    @Test
    public void testInfo() {       
        Info info = new Info();

        //test info is not null
        assertNotNull("info object not correctly initialized", info);
        
        //test object array is initialized and count set to 0
        assertEquals("Class not correctly initiliazed", 0, info.count());
        
    }

    /**
     * Items are inserted into the array in random order. Then the
     * array is tested to verify the items are sorted in ascending
     * order. Next, items already in the array are inserted, and count
     * is tested to verify that they were NOT added. Edge cases
     * tested.
     */
    @Test
    public void testInsert() {
        Item i1 = new Item("A", 1);
        Item i2 = new Item("B", 2);
        Item i3 = new Item("C", 3);
        Item i4 = new Item("D", 4);
                
        Info info = new Info();
        
        //insert some values in somewhat random order
        info.insert(i4);
        info.insert(i2);
        info.insert(i3);
        info.insert(i1);
        
        //we should now have four items in our array
        assertEquals("incorrect number of items in array", 4, info.count());
        
        //test ascending for order
        assertEquals("array out of order", "A" + Item.DELIMITER + "1",
                     info.get(0).toString());
        assertEquals("array out of order", "B" + Item.DELIMITER + "2",
                     info.get(1).toString());
        assertEquals("array out of order", "C" + Item.DELIMITER + "3",
                     info.get(2).toString());
        assertEquals("array out of order", "D" + Item.DELIMITER + "4",
                     info.get(3).toString());
        
        //insert an item already in the array (should fail)
        info.insert(i1);
        assertEquals("invalid item added to start of array", 4, info.count());
        info.insert(i3);
        assertEquals("invalid item added to middle of array", 4, info.count());
        info.insert(i4);
        assertEquals("invalid item added to end of array", 4, info.count());
    }

    /**
     * Items are inserted into the array, then they are deleted.
     * Delete is verified using indexOf method to determine if the
     * removed item is still in the array. After every item is
     * removed, one more item is inserted back into the array to
     * verify that the array can be refilled once emptied.
     */
    @Test
    public void testDelete() {
        Item i1 = new Item("A", 1);
        Item i2 = new Item("B", 2);
        Item i3 = new Item("C", 3);
        Item i4 = new Item("D", 4);
                
        Info info = new Info();
        
        //insert values into the array
        info.insert(i1);
        info.insert(i2);
        info.insert(i3);
        info.insert(i4);
        
        //delete items in array
        info.delete(i1);
        assertEquals("failed to delete first item in array", -1,
                     info.indexOf(i1));
        info.delete(i2);
        assertEquals("failed to delete middle item in array", -1,
                     info.indexOf(i2));
        info.delete(i3);
        assertEquals("failed to delete middle item in array", -1,
                     info.indexOf(i3));
        info.delete(i4);
        assertEquals("failed to delete last item in array", -1,
                     info.indexOf(i4));
        
        //test that values can be stored into a previously emptied
        //array (ensure index is maintained after deletes)
        info.insert(i1);
        assertEquals("failed to add item back into emptied array", 1,
                     info.count());
    }

    /**
     * Items are inserted into the array, then those same items are
     * retrieved using get(). The result is verified to ensure the
     * correct item is returned for the appropriate index. Both upper
     * and lower bounds are checked to verify the correct value is
     * returned for an incorrect input.
     */
    @Test
    public void testGet() {
        Item i1 = new Item("A", 1);
        Item i2 = new Item("B", 2);
        Item i3 = new Item("C", 3);
        Item i4 = new Item("D", 4);
                
        Info info = new Info();
        
        //insert values into the collection
        info.insert(i1);
        info.insert(i2);
        info.insert(i3);
        info.insert(i4);
        
        //get first item in array
        assertEquals("failed to get first item from array", "A" +
                     Item.DELIMITER + "1", info.get(0).toString());
        
        //get middle item in array
        assertEquals("failed to get middle item from array", "C" +
                     Item.DELIMITER + "3", info.get(2).toString());
        
        //get last item in array
        assertEquals("failed to get last item from array", "D" +
                     Item.DELIMITER + "4", info.get(3).toString());
        
        //get items out bounds (should return null)
        assertNull("incorrect value returned for index < 0", info.get(-1));
        
        //get items out bounds (should return null)
        assertNull("incorrect value returned for index >= than array size",
                   info.get(4));
    }

    /**
     * Items are inserted into an array, then the count is verified at
     * count = 0, count = 1, count = 2. Next, the items are removed,
     * and the count is verified once more to ensure that count is
     * reset properly after the array has been emptied
     */
    @Test
    public void testCount() {
        Item i1 = new Item("A", 1);
        Item i2 = new Item("B", 2);
        
        Info info = new Info();

        //call count when with no items
        assertEquals("count initiliazed incorrectly", 0, info.count());
        
        //insert values into the array
        info.insert(i1);
        
        //call count with one item
        assertEquals("invalid count when size = 1", 1, info.count());
        
        //call count with > 1 items
        info.insert(i2);
        assertEquals("invalid count when size = 2", 2, info.count());
        
        //remove items, then call count (should return 0)
        info.delete(i1);
        info.delete(i2);
        assertEquals("invalid count when size = 0", 0, info.count());           
    }

    /**
     * Insert items into array, then the functionality of copy() is
     * verified by copying various subsets of the array and testing
     * the values with-in those arrays. Finally three out of bounds
     * conditions are tested, in such a way that all possible out of
     * bounds conditions are tested.
     */
    @Test
    public void testCopy() {
        Item i1 = new Item("A", 1);
        Item i2 = new Item("B", 2);
        Item i3 = new Item("C", 3);
        Item i4 = new Item("D", 4);
                
        Info info = new Info();
        
        //insert values into the collection
        info.insert(i1);
        info.insert(i2);
        info.insert(i3);
        info.insert(i4);
        
        //copy first item
        Info i = info.copy(1,1);
        assertNotNull("failed to initialize new array", i);
        assertEquals("failed to copy first item", 1, i.count());
        assertEquals("failed to copy first item", "B" + Item.DELIMITER + 2,
                     i.get(0).toString());
        
        //copy middle two items
        i = info.copy(1, 2);
        assertEquals("failed to copy middle items", 2, i.count());
        assertEquals("failed to copy middle items", "B" + Item.DELIMITER + "2",
                     i.get(0).toString());
        assertEquals("failed to copy middle items", "C" + Item.DELIMITER + "3",
                     i.get(1).toString());
        
        //copy last item
        i = info.copy(3, 3);
        assertEquals("failed to copy last item", 1, i.count());
        assertEquals("failed to copy last item", "D" + Item.DELIMITER + "4",
                     i.get(0).toString());
        
        //copy entire array
        i = info.copy(0, 3);
        assertEquals("failed to copy entire array", 4, i.count());
        assertEquals("failed to copy entire array", "A" + Item.DELIMITER + "1",
                     i.get(0).toString());
        assertEquals("failed to copy entire array", "B" + Item.DELIMITER + "2",
                     i.get(1).toString());
        assertEquals("failed to copy entire array", "C" + Item.DELIMITER + "3",
                     i.get(2).toString());
        assertEquals("failed to copy entire array", "D" + Item.DELIMITER + "4",
                     i.get(3).toString());
        
        //test out of bounds
        //x < 0
        i = info.copy(-1, 0);
        assertNull("wrong value returned for index < 0", i);
        
        //y >= size
        assertNull("wrong value returned for index >= array size", i);
        i = info.copy(0, 4);
        
        // x > y
        i = info.copy(3, 2);
        assertNull("wrong value returned when bounds are switched", i);
    }

    /**
     * Two Info objects are created and filled with values in such a
     * way that one array in larger than the other. Next, two more
     * values are added to each array so that the intersection of the
     * two arrays is not empty. Finally, merge() is called and the
     * returned array is checked to verify all items from the original
     * two arrays (except duplicates) are present and in ascending
     * order
     */
    @Test
    public void testMerge() {
        Info listA = new Info(), listB = new Info();
        Item i1 = new Item("A", 1);
        Item i2 = new Item("B", 2);
        Item i3 = new Item("C", 3);
        Item i4 = new Item("D", 4);
                
        listA.insert(i4);
        listA.insert(i3);
                
        listB.insert(i1);
        listB.insert(i2);
        listB.insert(i3);

        Info mergedList = listA.merge(listB);
        assertEquals("failed to correctly sort merged list", "A" + Item.DELIMITER +
                     "1", mergedList.get(0).toString());
        assertEquals("failed to correctly sort merged list", "B" + Item.DELIMITER +
                     "2", mergedList.get(1).toString());
        assertEquals("failed to correctly sort merged list", "C" + Item.DELIMITER +
                     "3", mergedList.get(2).toString());
    }

    /**
     * An Info object is created and filled, then indexOf() is called
     * to retrieve specific values. The results are tested to verify
     * that the correct item is returned. Finally, indexOf() is called
     * with incorrect values to verify the correct value is returned.
     */
    @Test
    public void testIndexOf() {
        Item i0 = new Item("z", 0);
        Item i1 = new Item("A", 1);
        Item i2 = new Item("B", 2);
        Item i3 = new Item("C", 3);
        Item i4 = new Item("D", 4);
        Item i5 = new Item("E", 5);
                
        Info info = new Info();
        
        //insert values into the collection
        info.insert(i1);
        info.insert(i2);
        info.insert(i3);
        info.insert(i4);
        
        //verify indexOf functionality
        assertEquals("incorrect index returned", 0, info.indexOf(i1));
        assertEquals("incorrect index returned", 1, info.indexOf(i2));
        assertEquals("incorrect index returned", 3, info.indexOf(i4));
        
        //verify correct value (-1) for invalid input
        assertEquals("incorrect value returned for invalid input", -1,
                     info.indexOf(i0));
        assertEquals("incorrect value returned for invalid input", -1,
                     info.indexOf(i5));
    }
       
    /**
     * Create two Info objects, fill them and test the functionality
     * of deleteInfoList. Every occurrence of an Item object from list
     * 2 that common to list 1 should be removed from list 1.
     */
    @Test
    public void testDeleteInfoList() {
        Item i0 = new Item("A", 0);
        Item i1 = new Item("B", 1);
        Item i2 = new Item("C", 2);
        Item i3 = new Item("D", 3);
        Item i4 = new Item("E", 4);
        Item i5 = new Item("F", 5);
                
        Info info = new Info();

        //insert values into the collection
        info.insert(i0);
        info.insert(i1);
        info.insert(i2);
        info.insert(i3);
        info.insert(i4);
        info.insert(i5);
                
        Info infoB = new Info();
        infoB.insert(i0);
        infoB.insert(i3);
        infoB.insert(i5);
                
        info.delete(infoB);
        
        //verify delete functionality
        assertEquals("failed to correctly delete info list", 3, info.count());
        assertNotEquals("failed to correctly delete info list", "A" +
                        Item.DELIMITER + "0", info.get(0).toString());
        assertEquals("failed to correctly delete info list", "B" +
                     Item.DELIMITER + "1", info.get(0).toString());
        assertEquals("failed to correctly delete info list", "C" +
                     Item.DELIMITER + "2", info.get(1).toString());
        assertEquals("failed to correctly delete info list", "E" +
                     Item.DELIMITER + "4", info.get(2).toString());
                
        //refill values into each list so they are the same
        info.insert(i0);
        info.insert(i3);                
        info.insert(i5);
        assertEquals("failed to re-insert item into list after delete info list",
                     "A" + Item.DELIMITER + "0", info.get(0).toString());
        infoB.insert(i1);
        infoB.insert(i2);
        infoB.insert(i4);
                
        //delete entire list
        info.delete(infoB);
        
        //verify delete functionality
        assertEquals("failed to correctly delete info list", 0, info.count());
                
        //insert values into the collection
        info.insert(i0);
        info.insert(i1);
        info.insert(i2);
        info.insert(i3);
        info.insert(i4);
        info.insert(i5);
                
        infoB.insert(i0);
        infoB.insert(i3);
        infoB.insert(i5);
                
        //delete entire list
        infoB.delete(info);
        
        //verify delete functionality
        assertEquals("failed to correctly delete info list", 0, infoB.count());
    }

    /**
     * toString should output the items in a readable format.
     */
    @Test
    public void testToString() {   
        Item item1  = new Item("Alice", 22);
        Item item2  = new Item("Bob", 25);
        Item item3  = new Item("Chad", 36);
        Item item4  = new Item("Derek", 3);
        Item item5  = new Item("Emily", 11);
        
        Info infoA = new Info();
        assertEquals("toString failed.", "", infoA.toString());
        
        infoA.insert(item1);
        String equalString = "Alice" + Item.DELIMITER + "22\n";
        assertEquals("toString failed.", equalString, infoA.toString());
        
        infoA.insert(item2);
        equalString += "Bob" + Item.DELIMITER + "25\n";
        assertEquals("toString failed.", equalString, infoA.toString());
        
        infoA.insert(item3);
        equalString += "Chad" + Item.DELIMITER + "36\n";
        assertEquals("toString failed.", equalString, infoA.toString());
        
        infoA.insert(item4);
        equalString += "Derek" + Item.DELIMITER + "3\n";
        assertEquals("toString failed.", equalString, infoA.toString());
        
        infoA.insert(item5);
        equalString += "Emily" + Item.DELIMITER + "11\n";
        assertEquals("toString failed.", equalString, infoA.toString());

    }
}
