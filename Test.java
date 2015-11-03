/* This program interactively tests the Item and Info classes */
import java.util.*;
import java.io.*;

public class Test {
    public static final Scanner keyboard = new Scanner( System.in );
    public static void main (String [] args) {
        System.out.println("This program will let you test the Item or Info class.");
        Item testItem = null; // this is the item that the user tests
        Info testInfo = new Info(); // this is the Info that the user tests
        
        int option = 1;
        while (option != 0) {
            printMainMenu();
            System.out.println("Please select an option. (1..12 or 0)  ");
            option = keyboard.nextInt();
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
                testInfo = new Info();
                System.out.println("Info created.");
                break;
            case 5:// insert an item into the list
                testInfo.insert(makeItem());
                System.out.println("Item inserted.");
                break;
            case 6:// delete an item from the list
                testInfo.delete(makeItem());
                System.out.println("Item deleted (if it was in list).");
                break;
            case 7:// get an item from the list
                System.out.println("What is the position of the item?");
                int ID = keyboard.nextInt();
                Item myItem = testInfo.get(ID);
                if (myItem != null) {
                    System.out.println(myItem);
                }
                else {
                    System.out.println("Item not found.");
                }
                break;
            case 8:// get the number of items in the list
                System.out.println(testInfo.count() + " nodes in the list.");
                break;
            case 9:// copy the list (Not Yet Implemented)
                System.out.println("Not Yet Implemented.");
                break;
            case 10://merge two lists (Not Yet Implemented)
                System.out.println("Not Yet Implemented.");
                break;
            case 11://get the index of an item
                System.out.println(testInfo.indexOf(makeItem()));
                break;
            case 12://output the list
                System.out.println(testInfo);
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
        System.out.println("9  - copy the list (Not Yet Implemented)");
        System.out.println("10 - merge two lists (Not Yet Implemented)");
        System.out.println("11 - get the index of an item");
        System.out.println("12 - output the list");
        System.out.println("   ");
        System.out.println("0  - quit");
    }
    
    public static Item makeItem () {
        System.out.println("What is the ID of the item?");
        int ID = keyboard.nextInt();
        System.out.println("What is the string of the item?");
        String myString = keyboard.next();
        Item myItem = new Item(myString, ID);
        return myItem;
    }
}
