//This is an unedited copy of the Junit tests I wrote for assignment 1.

package info;

import static org.junit.Assert.*;

import org.junit.Test;


public class InfoTest {

    //This will test to make sure that the constructor is working
    @Test
    public void testInfo() {
        Info info;
        assertNull(info);
        info = new Info();
        assertNotNull(info);
    }
    
    //this will test the insert method by running a bunch of
    //insert test methods
    @Test
    public void testInsert() {
        testInsertAndGetOne();
        testInsertAndGetTwo();
        testInsertAndGetThree();
        testInsertAndGetFour();
        testInsertAndGetFive();
    }

    //the tests for get and insert are the same, except that inserting
    //needs to check the size also.
    @Test
    public void testGet() {
        testInsertAndGetOne();
        testInsertAndGetTwo();
        testInsertAndGetThree();
        testInsertAndGetFour();
        testInsertAndGetFive();
    }
    
    @Test
    public void testInsertAndGetOne() {
        Info info = new Info();
        info.insert("1");
        assertEquals("1", info.get(1));
    }

    @Test
    public void testInsertAndGetTwo() {
        Info info = new Info();
        info.insert("1");
        info.insert("2");
        info.insert("3");
        info.insert("4");
        info.insert("5");
        assertEquals("1", info.get(1));
        assertEquals("2", info.get(2));
        assertEquals("3", info.get(3));
        assertEquals("4", info.get(4));
        assertEquals("5", info.get(5));
    }
    
    @Test
    public void testInsertAndGetThree() {
        Info info = new Info();
        info.insert("1");
        info.insert("4");
        info.insert("2");
        info.insert("6");
        info.insert("-1");
        assertEquals("-1", info.get(1));
        assertEquals("1", info.get(2));
        assertEquals("2", info.get(3));
        assertEquals("4", info.get(4));
        assertEquals("6", info.get(5));
    }

    @Test
    public void testInsertAndGetFour() {
        Info info = new Info();
        info.insert("1");
        info.insert("1");
        info.insert("3");
        info.insert("5");
        info.insert("3");
        assertEquals("1", info.get(1));
        assertEquals("3", info.get(2));
        assertEquals("5", info.get(3));
        assertNull(info.get(4));
        assertEquals(3, info.count());
    }

    @Test
    public void testInsertAndGetFive() {
        Info info = new Info();
        info.insert("1");
        info.insert("0");
        info.insert("-1");
        assertEquals("-1", info.get(1));
        assertEquals("0", info.get(2));
        assertEquals("1", info.get(3));
        assertNull(info.get(4));
        assertEquals(3, info.count());
    }

    //this will test the delete method by running a bunch of delete
    //test methods
    @Test
    public void testDelete() {
        testDeleteOne();
        testDeleteTwo();
        testDeleteThree();
    }
    
    @Test
    public void testDeleteOne() {
        Info info = new Info();
        assertEquals(0, info.count());
        info.delete("1");
        assertEquals(0, info.count());
        
        info.insert("1");
        assertEquals(1, info.count());
        info.delete("1");
        assertEquals(0, info.count());
    }

    @Test
    public void testDeleteTwo() {
        Info info = new Info();
        info.insert("1");
        info.insert("3");
        info.insert("8");
        info.insert("2");
        info.insert("0");
        
        info.delete("4");
        assertEquals(5, info.count());
        info.delete("8");
        assertEquals(4, info.count());
        info.delete("1");
        assertEquals(3, info.count());
        info.delete("0");
        assertEquals(2, info.count());
        info.delete("2");
        assertEquals(1, info.count());
        info.delete("2");
        assertEquals(1, info.count());
        info.delete("3");
        assertEquals(0, info.count());
    }

    @Test
    public void testDeleteThree() {
        Info info = new Info();
        for (int i = 0; i < 10; i++) {
            info.insert("" + i);
        }
        for (int i = 0; i < 10; i++) {
            info.delete("" + i);
        }
        assertEquals(0, info.count());
    }

    //this will test the count method by running a bunch of count test
    //methods
    @Test
    public void testCount() {
        testCountOne();
        testCountTwo();
        testCountThree();
    }
    
    @Test
    public void testCountOne() {
        Info info = new Info();
        assertEquals(0, info.count());
        info.insert("1");
        assertEquals(1, info.count());
        info.delete("1");
        assertEquals(0, info.count());
    }

    @Test
    public void testCountTwo() {
        Info info = new Info();
        info.insert("1");
        assertEquals(1, info.count());
        info.insert("2");
        assertEquals(2, info.count());
        info.insert("3");
        assertEquals(3, info.count());
        info.insert("4");
        assertEquals(4, info.count());
        info.insert("5");
        assertEquals(5, info.count());

        info.delete("1");
        assertEquals(4, info.count());
        info.delete("2");
        assertEquals(3, info.count());
        info.delete("3");
        assertEquals(2, info.count());
        info.delete("6");
        assertEquals(2, info.count());
        info.delete("4");
        assertEquals(1, info.count());
        info.delete("5");
        assertEquals(0, info.count());
        info.delete("6");
        assertEquals(0, info.count());
    }

    @Test
    public void testCountThree() {
        Info info = new Info();
        info.insert("1");
        assertEquals(1, info.count()); //1
        info.insert("6");
        assertEquals(2, info.count());//1, 6
        info.insert("-50");
        assertEquals(3, info.count());//-50, 1, 6
        info.delete("6");
        assertEquals(2, info.count());//-50, 1
        info.insert("10");
        assertEquals(3, info.count());//-50, 1, 10
        info.delete("1");
        assertEquals(2, info.count());//-50, 10
        info.delete("6");
        assertEquals(2, info.count());//-50, 10
        info.delete("-50");
        assertEquals(1, info.count());//10
        info.delete("10");
        assertEquals(0, info.count());//
        info.delete("0");
        assertEquals(0, info.count());//
        
    }

    //this will test the copy method by running a bunch of copy test methods
    @Test
    public void testCopy() {
        testCopyOne();
        testCopyTwo();
        testCopyThree();
        testCopyFour();
        testCopyFive();
    }
    
    @Test
    public void testCopyOne() {
        Info info1 = new Info();
        Info info2 = new Info();
        
        info2 = info1.copy(0, info1.count());
        
        assertEquals(info1, info2);
    }
    
    @Test
    public void testCopyTwo() {
        Info info1 = new Info();
        Info info2 = new Info();
        info1.insert("1");
        info1.insert("2");
        info1.insert("8");
        info1.insert("-1");
        info1.insert("4");
        
        info2 = info1.copy(1, info1.count());
        
        assertEquals(info1, info2);
    }

    @Test
    public void testCopyThree() {
        Info info1 = new Info();
        Info info2 = new Info();
        info1.insert("4");
        info1.insert("8");
        info1.insert("1");
        info1.insert("4");
        info1.insert("0");
        
        info2 = info1.copy(2, info1.count());
        
        info1.delete("0");
        
        assertEquals(info1, info2);
    }

    @Test
    public void testCopyFour() {
        Info info1 = new Info();
        Info info2 = new Info();
        info1.insert("-50");
        info1.insert("4");
        info1.insert("9");
        info1.insert("3");
        info1.insert("42");
        
        info2 = info1.copy(2, info1.count()-1);
        
        info1.delete("-50");
        info1.delete("42");
        
        assertEquals(info1, info2);
    }

    @Test
    public void testCopyFive() {
        Info info1 = new Info();
        Info info2 = new Info();
        info1.insert("1");
        info1.insert("3");
        info1.insert("5");
        info1.insert("7");
        info1.insert("9");
        
        info2 = info1.copy(2, info1.count());
        
        assertNotEquals(info1, info2);
    }
    //this will test the merge method by running a bunch of merge test methods
    @Test
    public void testMerge() {
        testMergeOne();
        testMergeTwo();
        testMergeThree();
    }
    @Test
    public void testMergeOne() {
        Info info1 = new Info();
        Info info2 = new Info();
        Info info3 = new Info();
        Info info4 = new Info();
        
        info3 = info1.merge(info2);
        assertEquals(0, info3.count());
        assertEquals(info3, info4);
    }

    @Test
    public void testMergeTwo() {
        Info info1 = new Info();
        Info info2 = new Info();
        Info info3 = new Info();
        Info info4 = new Info();
        
        info1.insert("1");
        info1.insert("3");
        info1.insert("7");
        info1.insert("-1");
        
        info2.insert("2");
        info2.insert("7");
        info2.insert("9");
        info2.insert("0");
        
        info3 = info1.merge(info2);
        
        info4.insert("1");
        info4.insert("3");
        info4.insert("7");
        info4.insert("-1");
        info4.insert("2");
        info4.insert("9");
        info4.insert("0");
        
        assertEquals(7, info3.count());
        assertEquals(info3, info4);
    }

    @Test
    public void testMergeThree() {
        Info info1 = new Info();
        Info info2 = new Info();
        Info info3 = new Info();
        Info info4 = new Info();
        
        info1.insert("1");
        info1.insert("3");
        info1.insert("5");
        info1.insert("8");
        
        info3 = info1.merge(info2);
        
        info4.insert("1");
        info4.insert("3");
        info4.insert("5");
        info4.insert("8");

        assertEquals(4, info3.count());
        assertEquals(info3, info4);
    }

    @Test
    public void testMergeFour() {
        Info info1 = new Info();
        Info info2 = new Info();
        Info info3 = new Info();
        Info info4 = new Info();
        
        info2.insert("1");
        info2.insert("7");
        info2.insert("2");
        info2.insert("0");
        
        info3 = info1.merge(info2);
        
        info4.insert("1");
        info4.insert("7");
        info4.insert("2");
        info4.insert("0");

        assertEquals(4, info3.count());
        assertEquals(info3, info4);
    }
  
    //this will test the indexOf method by running a bunch of indexOf
    //test methods
    @Test
    public void testIndexOf() {
        testIndexOfOne();
        testIndexOfTwo();
        testIndexOfThree();
    }
    
    @Test
    public void testIndexOfOne() {
        Info info1 = new Info();
        assertEquals(-1, info.indexOf("1"));
        
        info.insert("1");
        assertEquals(1, info.indexOf("1"));
        
        info.delete("1");
        assertEquals(-1, info.indexOf("1"));
    }

    @Test
    public void testIndexOfTwo() {
        Info info = new Info();
        
        info.insert("3");
        info.insert("1");
        info.insert("5");
        info.insert("4");
        info.insert("2");
        
        assertEquals(1, info.indexOf("1"));
        assertEquals(2, info.indexOf("2"));
        assertEquals(3, info.indexOf("3"));
        assertEquals(4, info.indexOf("4"));
        assertEquals(5, info.indexOf("5"));
        
        info.delete("4");
        info.delete("1");
        
        assertEquals(2, info.indexOf("1"));
        assertEquals(3, info.indexOf("2"));
        assertEquals(5, info.indexOf("3"));
    }
    
    @Test
    public void testIndexOfThree() {
        Info info = new Info();

        info.insert("-5");
        info.insert("1");
        assertEquals(2, info.indexOf("1"));
        info.delete("1");
        assertEquals(-1, info.indexOf("1"));
        assertEquals(1, info.indexOf("5"));
        
        info.insert("6");
        info.insert("2");
        info.insert("90");
        info.insert("-61");

        info.delete("5");
        info.delete("8");
        info.delete("90");
        
        assertEquals(1, info.indexOf("-61"));
        assertEquals(2, info.indexOf("-5"));
        assertEquals(3, info.indexOf("2"));
        assertEquals(4, info.indexOf("6"));
        assertEquals(-1, info.indexOf("90"));
    }

    //this will test the toArray method by running a bunch of toArray
    //test methods
    @Test
    public void testToArray() {
        testToArrayOne();
        testToArrayTwo();
        testToArrayThree();
        testToArrayFour();
    }

    @Test
    public void testToArrayOne() {
        Info info = new Info();
        String[] string = info.toArray();

        assertEquals(0, string.length);
    }

    @Test
    public void testToArrayTwo() {
        Info info = new Info();

        info.insert("1");

        String[] string = info.toArray();
        
        assertEquals(1, string.length);
        assertEquals(info.get(1), string[0]);
    }

    @Test
    public void testToArrayThree() {
        Info info = new Info();

        info.insert("1");
        info.insert("7");
        info.insert("12");
        info.insert("0");
        info.insert("-52");

        String[] string = info.toArray();

        assertEquals(5, string.length);
        assertEquals(info.get(1), string[0]);
        assertEquals(info.get(2), string[1]);
        assertEquals(info.get(3), string[2]);
        assertEquals(info.get(4), string[3]);
        assertEquals(info.get(5), string[4]);
    }

    @Test
    public void testToArrayFour() {
        Info info = new Info();

        info.insert("1");
        String[] string = info.toArray();
        assertEquals(1, string.length);
        assertEquals(info.get(1), string[0]);
        
        info.insert("6");
        string = info.toArray();
        assertEquals(2, string.length);
        assertEquals(info.get(1), string[0]);
        assertEquals(info.get(2), string[1]);
        
        info.insert("1");
        string = info.toArray();
        assertEquals(2, string.length);
        assertEquals(info.get(1), string[0]);
        assertEquals(info.get(2), string[1]);
        
        info.insert("7");
        string = info.toArray();
        assertEquals(3, string.length);
        assertEquals(info.get(1), string[0]);
        assertEquals(info.get(2), string[1]);
        assertEquals(info.get(3), string[2]);
        
        info.insert("-10");
        string = info.toArray();
        assertEquals(4, string.length);
        assertEquals(info.get(1), string[0]);
        assertEquals(info.get(2), string[1]);
        assertEquals(info.get(3), string[2]);
        assertEquals(info.get(4), string[3]);
    }
}
