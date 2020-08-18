package kaavya.datastructures;

public class SingleLinkListOperations {
	static class Node{
		int data;
		Node next;
		
		Node(int data){
			this.data = data;
		}
	}
	static Node head;
	
	Node addToEnd(int num){//adds and return the last node
		Node newnode = new Node(num);
		newnode.next = null;
		if (head == null) { 
			head = newnode;
			return head;
			
		}
		Node curr = traverseToEnd();
		 System.out.println("Adding to node: " + curr.data + " newnode:" + newnode.data);
		
		//end of while loop, curr will be one before last node
		curr.next = newnode;
		return newnode;
	}

	
	Node addToEnd(Node newnode){//adds and return the last node
		newnode.next = null;
		if (head == null) { 
			head = newnode;
			return head;
			
		}
		Node curr = traverseToEnd();
		System.out.println("Adding to node: " + curr.data + " newnode:" + newnode.data);
		
		curr.next = newnode;
		return newnode;
	}
	
	public static Node addToHead(Node newNode){//adds to head
		newNode.next = head;
		head = newNode;
		return head;
	}
	private Node traverseToEnd() {
		Node curr = head;
		while(curr.next != null)
			curr = curr.next;
		return curr;
	}
	
	public static void printAll(Node headNode) {
		Node curr = headNode;
		System.out.println("\n");
		while((curr.next != null) ) {
			System.out.print(curr.data + " -> ");
			curr = curr.next;
		}
		System.out.print(curr.data );//print last node
	}
	
	public Node reverseList(Node headNode) {
		//initailze prev, current and next pointers snd keep moving the prev and curr by 1
		Node prev = null; //for the first node
		Node curr = headNode;
		Node next;
		
		while(curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		//head = prev;
		return prev; //return the reversed head as head node
	}
	
	public Node reverseListInPairs(Node headNode) { //reverse pairs
		Node temp = null;
		Node cur = headNode;
		Node newHead = null;
		while ((cur != null) && (cur.next != null)) {
			if (temp != null)  temp.next.next = cur.next;
			temp = cur.next;
			cur.next = temp.next;
			temp.next = cur;
			
			
			cur = cur.next;
			if (newHead == null) 
				newHead = temp;
		}
		
		return newHead;
	}
	
	public Node reverseBetweenNodes(Node boundaryNode, Node headNode, Node lastNode) { //reverse 2 or more
//		System.out.println("\nprevNode: " + boundaryNode.data + ", headNode: " + headNode.data +
//							", lastNode: " + lastNode.data);
		Node cur = headNode.next;
		Node next; Node prev = headNode;
		while (prev != lastNode) {
			next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		headNode.next = cur;
		if (boundaryNode == null) {boundaryNode = lastNode;}
		else boundaryNode.next = lastNode;
		
		return boundaryNode; //return new boundary node/start node. It might change if started with very first node
	}
	
	public Node midPoint(Node head) {
		//initialize 2 pointers and skip 1 by 2 count and another by 1 count.
		Node shortPointer=head; Node longPointer = head;
		
		while ((longPointer != null ) && (longPointer.next != null)) {
			
			longPointer = longPointer.next.next;
			shortPointer = shortPointer.next;
		}
		return shortPointer;
	}
	
	public Node findNthElementfromEnd(Node head, int n) {
		Node cur=head; 
		
		for (int i = 0; i <= n; i++) {
			cur = cur.next;
		}
		Node nthElement = head;
			
		while (cur != null) {
			cur = cur.next;
			nthElement = nthElement.next;
		}
		
		return nthElement;
	}
	
	public void findnRemoveLoop(Node head) {
		//initialize slow and fast pointers and if they meet, there is a loop
		Node shortPointer=head; Node longPointer = head; int counter = 0;
		
		while (shortPointer != null && longPointer != null && longPointer.next != null) {
			
			longPointer = longPointer.next.next;
			shortPointer = shortPointer.next;
			System.out.println("shortPointer: " + shortPointer.data + " longPointer: " + longPointer.data);
			++counter;
			if (longPointer == shortPointer) {
				System.out.println("Loop found at the execution:" + counter + " loopnode is: " + longPointer.data);
				removeLoop(longPointer, head);
				return;
			}
		}
	}
	
	public void removeLoop(Node loopNode, Node head){
		//count the number of nodes inside/that form the loop
		int counter = 1;
		//check how many nodes we need to traverse for 2 pointers (cur and loopNode) from the loop node to meet
		Node cur = loopNode; 
		while (cur.next != loopNode) {
			++counter;
			cur = cur.next;
		}
		System.out.println("Loop is between " + counter + " nodes");
		
		//traverse K nodes from the head and mark pointer1. Other pointer2 to traverse from beginning of the list.
		//When the pointers meet, it's the starting point of the loop.
		cur = head;
		for (int i = 0; i < counter; i++) {
			cur = cur.next;
		}//end of loop, cur pointer would have traversed K nodes

		Node slowp = head;
		System.out.println("Slowp is at: " + slowp.data + " cur is at: " + cur.data);
		while (slowp != cur) {
			slowp = slowp.next;
			cur = cur.next;
		}//slowp and cur met at the starting point of the loop
		System.out.println("Loop starting point is:" + cur.data);
		
		
		//to find the end of the loop,
		while(cur.next != slowp) {
			cur = cur.next;
		}//end of the loop cur will be at end of the loop
		System.out.println("End of the loop is at: " + cur.data);
		cur.next = null; //remove the loop and re-point the end of the loop node to null. 
	}
	
	public static void main(String args[]) {
		SingleLinkListOperations linkedlist = new SingleLinkListOperations();
	//	Node loopNode  = linkedlist.new Node(3);

		addToHead(new Node(0));
		addToHead(new Node(6));
		addToHead(new Node(2));
		addToHead(new Node(1));
		addToHead(new Node(3));
		addToHead(new Node(4));
		addToHead(new Node(5));
		linkedlist.printAll(head);
		
		//create a loop between 3 and 5
		head.next.next.next.next.next.next.next = head.next.next.next.next;
		
		System.out.println("\nLoop exists: " );
		linkedlist.findnRemoveLoop(head);
		
		linkedlist.printAll(head);
		
		linkedlist.addToEnd(4);
		linkedlist.addToEnd(7);
		linkedlist.addToEnd(8);
		linkedlist.addToEnd(6);
		
		linkedlist.printAll(head);
		//linkedlist.printAll(headNode);
		
		System.out.println("\nMid point is: " + linkedlist.midPoint(head).data);
		
		System.out.println("\nNth element from the end is: " + linkedlist.findNthElementfromEnd(head, 1).data);

		
		Node reversehead = linkedlist.reverseList( head);
		linkedlist.printAll(reversehead);

		//back to original
		head = linkedlist.reverseList( reversehead);
		linkedlist.printAll(head);
		
		reversehead = linkedlist.reverseListInPairs(head);
		linkedlist.printAll(reversehead);
		
		//reverse node between 1 and 4
		System.out.println("\nReverse between nodes:" + head.data + ", " + head.next.next.data);
		head = linkedlist.reverseBetweenNodes(null, head, head.next.next);
		linkedlist.printAll(head);
		
	}
}
