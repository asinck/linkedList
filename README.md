This is an assignment for my CSE360 class. I doubt anyone will find it useful.

Item contains a String and an integer. It has the following methods:
- item, the constructor,
- compareTo, compares the current instance to the given item,
- getStr and getVal, accessors for the string and integer, and 
- toString, which you can guess the reason for.

Item implements the Comparable interface.

Info is a linked list of Nodes. Node is a private class in Info. Info has two data members: a list (type Node), and the number of nodes (type integer). Info has the following public methods:
- Info, the constructor,
- insert, inserts the given Item,
- delete(Item), deletes the given Item,
- delete(Info), deletes all items from the current list that are in the input list, 
- get, gets an Item from the list, by index,
- count, returns the number of nodes in the list,
- copy, returns a copy of the nodes from the first given index to the second given index,
- merge, combines two lists,
- indexOf, returns the index of the given Item in the list, and
- toString, returns a string representation of the list.

Node has an Item and a reference to the next node, and the following methods:
- Node, the constructor, and
- toString, returns a string representation of the Node.

InfoTest and ItemTest are junit test files.

Test is a side program that allows interactive testing of the programs. 
