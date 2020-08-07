package kaavya.datastructures;

import kaavya.datastructures.DoublyLinkList.Node;

public class DoublyLinkList {
	private Node head;
	class Node {
		int data;
		Node prev;
		Node next;
		
		Node(int elem){
			data = elem;
		}
		
		public int getData() {
			return data;
		}
	}
	
	public void add(int elem){
		//add it to the first element and transfer the head pointer
		Node newNode = new Node(elem);
		newNode.next = head;
		newNode.prev = null;
		
		if (head != null) head.prev = newNode;
		
		head = newNode;
	}
	
	public void insertAfter(Node prevNode, int data) {
		if (prevNode == null) return;
		
		Node newNode = new Node(data);
		
//		Node targetNode = prevNode.next;
//		newNode.next = targetNode;
//		targetNode.prev = newNode;
		//same logic with no temp node
		
		newNode.next = prevNode.next;
		newNode.next.prev = newNode;
		
		prevNode.next = newNode;
		newNode.prev = prevNode;		
	}
	
	public void append(int data) {
		//add the new node to the end.
		//traverse from head to end, and add it there
		Node newNode = new Node(data);
		
		Node currentNode = head;
		if (head == null) {
			head = newNode;
			head.prev = null;
		}
		
		while (currentNode.next != null) {
			currentNode = currentNode.next;
		}
		//end of the while loop, currentNode will be last node
		
		
		currentNode.next = newNode;
		newNode.prev = currentNode;
		
		newNode.next = null;
		
	}
	
	public void insertBefore(Node prevNode, int data) {
		if (prevNode == null) return;
		
		//construct the new node
		Node newNode  = new Node(data);
		
		newNode.next = prevNode;
		prevNode.prev.next = newNode;
		newNode.prev = prevNode.prev;
		prevNode.prev = newNode;
	}
	
	public void deleteNode(Node deletionNode) {
		deletionNode.prev.next = deletionNode.next;
		deletionNode.next.prev = deletionNode.prev;
	}
	public int count() {
		Node currentNode = head; int count = 0;
		while (currentNode.next != null) {
			count++;
			currentNode = currentNode.next;
		}
		return count+1;
	}
	public Node getLastNode() {
		Node currentNode = head;
		while (currentNode.next != null) {
			currentNode = currentNode.next;
		}
		return currentNode;
	}
	public Node getHeadNode() {
		return head;
	}
	
	public Node printList() {
		
		//start from head node and traverse and print
		Node currentNode = head;
		System.out.println("\nForward direction:");
		while (currentNode.next != null) {
			System.out.print(currentNode.data + " -> ");
			currentNode = currentNode.next;
		}
		//print the last node
		System.out.print(currentNode.data);
		return currentNode;
	}
	
	public void reversePrintList() {
		Node lastNode = printList();
		
		//print in reverse direction
		System.out.println("\nReverse direction:");
		while(lastNode.prev != null) {
			System.out.print(lastNode.data + " -> ");
			lastNode = lastNode.prev;
		}
		//print the first node
		System.out.print(lastNode.data);
	}
	public void swap( Node left, Node right) {
//		left.next = right.next; //1
//		left.prev.next = right; //2
//		
//		right.next.prev = left;
//		right.next = left;
//
//		right.prev = left.prev;
//		left.prev = right;
		
		int tempdata = left.data;
		left.data = right.data;
		right.data = tempdata;
		
	}
	public static void main(String[] args) {
		DoublyLinkList dll = new DoublyLinkList();
		dll.add(1);
		dll.append(2);
		dll.append(3);
		dll.insertAfter(dll.head.next, 5);
		dll.insertBefore(dll.head.next, 4);
		dll.printList();

		
		System.out.println("\nSwapping 2 node and last to previous nodes");
		dll.swap(dll.getHeadNode().next, dll.getLastNode().prev);
		dll.printList();

		dll.reversePrintList();
		
		//test deletion
		dll.deleteNode(dll.head.next);
		dll.printList();
		System.out.println("\nTotal elements:" + dll.count());
	}

}
