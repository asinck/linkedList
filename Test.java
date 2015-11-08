/* This program interactively tests the Item and Info classes */
import java.util.*;
import java.io.*;

public class Test {
    public static final Scanner keyboard = new Scanner( System.in );
    public static void main (String [] args) {
        System.out.println("This program will let you test the Item or Info class.");
        Item testItem = null; // this is the item that the user tests
        Info testInfo1 = new Info(); // this is the Info that the user tests
        Info testInfo2 = new Info(); // this is the Info that the user tests
        
        int option = 1;
        boolean opt;
        while (option != 0) {
            printMainMenu();
            System.out.println("Please select an option. (1..12 or 0)  ");
            option = getInt();
            System.out.println("");
            switch (option) {
            case 1: // create an item
                testItem = makeItem();
                break;
            case 2:// display the item
                System.out.println(testItem);
                break;
            case 3:// compare to a new item
                Item compareItem = makeItem();
                int compare = testItem.compareTo(compareItem);
                    
                if (compare < 0) {
                    System.out.println("Your item is less than the new item");
                }
                else if (compare == 0) {
                    System.out.println("Your item is equivalent to the new item");
                }
                else {
                    System.out.println("Your item is greater than the new item");
                }
                    
                break;
    
            case 4:// create an Info
                if (getOpt()) {
                    testInfo1 = new Info();
                    System.out.println("Info 1 created.");
                }
                else {
                    testInfo2 = new Info();
                    System.out.println("Info 2 created.");
                }
                
                break;
            case 5:// insert an item into the list
                if (getOpt()) {
                    testInfo1.insert(makeItem());
                    System.out.println("Item inserted into 1.");
                }
                else {
                    testInfo2.insert(makeItem());
                    System.out.println("Item inserted into 2.");
                }
                
                break;
            case 6:// delete an item from the list
                if (getOpt()) {
                    testInfo1.delete(makeItem());
                    System.out.println("Item deleted (if it was in list 1).");
                }
                else {
                    testInfo2.delete(makeItem());
                    System.out.println("Item deleted (if it was in list 2).");
                }
                
                break;
            case 7:// get an item from the list
                System.out.println("What is the index of the item?");
                int ID = getInt();
                Item myItem;
                opt = getOpt();
                if (opt) myItem = testInfo1.get(ID);
                else myItem = testInfo2.get(ID);

                if (myItem != null) {
                    char l = (opt) ? '1' : '2';
                    System.out.println("From list " + l + ":\n" + myItem);
                }
                else {
                    char l = (opt) ? '1' : '2';
                    System.out.println("Item not found in list " + l);
                }
                break;
            case 8:// get the number of items in the list
                if (getOpt()) {
                    int count = testInfo1.count();
                    System.out.println(count + " nodes in list 1.");
                }
                else {
                    int count = testInfo2.count();
                    System.out.println(count + " nodes in list 2.");
                }
                
                break;
            case 9:// copy the list (Not Yet Implemented)
                System.out.println("What is the start index?");
                int start = getInt();
                System.out.println("What is the end index?");
                int end = getInt();
                if (getOpt())
                    System.out.println("From 1:\n" + testInfo1.copy(start, end));
                else
                    System.out.println("From 2:\n" + testInfo2.copy(start, end));
                break;
            case 10://merge two lists (Not Yet Implemented)
                System.out.println("Merging 1 and 2:");
                System.out.println(testInfo1.merge(testInfo2));
                break;
            case 11://get the index of an item
                if (getOpt()) {
                    int index = testInfo1.indexOf(makeItem());
                    System.out.println("Index in list 1: " + index);
                }
                else {
                    int index = testInfo2.indexOf(makeItem());
                    System.out.println("Index in list 2: " + index);
                }
                
                break;
            case 12://output the list
                opt = getOpt();
                char l = (opt) ? '1' : '2';
                Info outputInfo = (opt) ? testInfo1 : testInfo2;
                System.out.println("List " + l + ":\n\n" + outputInfo);
                break;
            default:
                if (option != 0) {
                    System.out.println("Invalid option.");
                }
                break;
            }
            if (option != 0) {
                System.out.println("Enter anything to continue.  ");
                String s = keyboard.next();
                System.out.println("");
            }
        }
    }
        
    public static void printMainMenu () {
        System.out.println("______________________________________________________________________");
        System.out.println("Main Menu _/");
        System.out.println("---------/");
        System.out.println("   ");
        System.out.println("1  - create an item");
        System.out.println("2  - display the item");
        System.out.println("3  - compare to a new item");
        System.out.println("   ");
        System.out.println("4  - create an Info");
        System.out.println("5  - insert an item into the list");
        System.out.println("6  - delete an item from the list");
        System.out.println("7  - get an item from the list");
        System.out.println("8  - get the number of items in the list");
        System.out.println("9  - copy the list");
        System.out.println("10 - merge two lists");
        System.out.println("11 - get the index of an item");
        System.out.println("12 - output the list");
        System.out.println("   ");
        System.out.println("0  - quit");
    }
    
    public static Item makeItem () {
        System.out.println("What is the ID of the item?");
        int ID = getInt();
        System.out.println("What is the string of the item?");
        String myString = keyboard.next();
        Item myItem = new Item(myString, ID);
        return myItem;
    }
    public static boolean getOpt() {
        System.out.println("List 1 or 2?");
        int item = getInt();
        return (item == 1);
    }
    public static int getInt() {
        String choice = keyboard.next();
        int output = -1;
        boolean done = false;
        while (!done) {
            try {
                output = Integer.parseInt(choice);
                done = true;
            }
            catch (Exception e) {
                System.out.println("Enter a number.  ");
                choice = keyboard.next();
            }
        }

        return output;
    }
}
