package kaavya.datastructures;

public class SingleLinkListOperations {
	class Node{
		int data;
		Node next;
		
		Node(int data){
			this.data = data;
		}
	}
	Node head;
	
	Node addToEnd(int num){//adds and return the last node
		Node newnode = new Node(num);
		newnode.next = null;
		if (head == null) { 
			head = newnode;
			return head;
			
		}
		Node curr = traverseToEnd();
		
		//end of while loop, curr will be one before last node
		curr.next = newnode;
		return newnode;
	}

	private Node traverseToEnd() {
		Node curr = head;
		while(curr.next != null)
			curr = curr.next;
		return curr;
	}
	
	public void printAll(Node headNode) {
		Node curr = headNode;
		System.out.println("\n");
		while(curr.next != null) {
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
	
	
	public static void main(String args[]) {
		SingleLinkListOperations linkedlist = new SingleLinkListOperations();
		Node headNode = linkedlist.addToEnd(0);
		linkedlist.addToEnd(2);
		linkedlist.addToEnd(4);
//		linkedlist.addToEnd(3);
//		linkedlist.addToEnd(4);
//		linkedlist.addToEnd(5);
//		linkedlist.addToEnd(6);
		
		linkedlist.printAll(headNode);
		
//		Node reversehead = linkedlist.reverseList( headNode);
//		linkedlist.printAll(reversehead);
//
//		//back to original
//		headNode = linkedlist.reverseList( reversehead);
//		linkedlist.printAll(headNode);
		
//		reversehead = linkedlist.reverseListInPairs(headNode);
//		linkedlist.printAll(reversehead);
		
		//reverse node between 1 and 4
		System.out.println("\nReverse between nodes:" + headNode.data + ", " + headNode.next.next.data);
		headNode = linkedlist.reverseBetweenNodes(null, headNode, headNode.next.next);
		linkedlist.printAll(headNode);
		
	}
}
