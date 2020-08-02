package kaavya.datastructures;

public class Stack {
	private class Node {
		String item;
		Node next;
	}
	private Node first = null;
	
	public boolean isEmpty() {
	   return (first == null);
	}
	
	public void push(String item) {
		//store reference to the first item in the stack in temp variable
		Node oldfirst = first;
		
		//create new node that needs to be inserted
	    first = new Node();
		first.item = item;
		
		//point the new item to first item of the stack
		first.next = oldfirst;
		
	}
	
	public String pop() {
		String popped = first.item;
		first = first.next;
		
		return popped;
	}
	
	public static void main (String args[]) {
		Stack stack = new Stack();
		
		//push to the stack
		String[] arr = {"S", "U", "M", "I"};
		
		System.out.println("Pushing to stack: ");
		for(int i=0; i<= arr.length-1; i++) {
			stack.push(arr[i]);
			System.out.print(arr[i]);
		}
		
		System.out.println("\nPopping from stack: ");
		for(int i=0; i<= arr.length-1; i++) {
			System.out.print(stack.pop());
		}
	}
	
}
