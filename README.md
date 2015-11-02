This is an assignment for my CSE360 class. I doubt anyone will find it useful.

Item contains a String and an integer. It has the following methods:
- item, the constructor,
- compareTo, compares the current instance to the given item,
- getStr and getVal, accessors for the string and integer, and 
- toString, which you can guess the reason for.
Item implements the Comparable interface.


Info is a linked list of Nodes. Both Info and Node are in the Info file. Info has two data members: a list (type Node), and the number of nodes (type integer). Info has the following public methods:
- Info, the constructor,
- insert, inserts the given Item,
- delete, deletes the given Item,
- get, gets an Item from the list, by ID,
- count, returns the number of nodes in the list,
- copy, returns a copy of the nodes from the first given ID to the second given ID,
- merge, combines two lists,
- indexOf, returns the index of the given Item in the list, and
- toString, returns a string representation of the list.

Node has an Item and a reference to the next node, and the following methods:
- Node, the constructor,
- setData and setNext, set methods,
- getData and getNext, get methods, and 
- toString, returns a string representation of the Node.


Test is a side program that allows interactive testing of the programs. Besides that, it happens to be 123 lines long.