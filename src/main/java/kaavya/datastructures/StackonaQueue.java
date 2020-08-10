package kaavya.datastructures;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
public class StackonaQueue {
	
	
	public static void main(String[] args) {
		Queue<String> queue1 = new LinkedList<String>();
		//linked list is  queue implementation - LIFO
		queue1.add("A");
		queue1.add("B");
		queue1.add("C");
		queue1.add("D");
		
		System.out.println("Elements added to Queue");
		
		queue1.forEach(System.out::print);
		
		LinkedList<String> ll = new LinkedList<String>();
		ll.add("X");
		ll.add("Y");
		ll.add("Z");
		
		System.out.println("\nFirst item in linked list: " + ll.getFirst());
		System.out.println("\nLast item in linked list: " + ll.getLast());
		
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(20);
		stack.push(10);
		stack.push(40);
		stack.push(30);
		
		Stack<Integer> sortedStack = StackonaQueue.sortStackAscending(stack);
		System.out.println("\n Sorted Stack in ascending order: ");
		int size = sortedStack.size();
		for (int i=0; i < size; i++)
			System.out.print(sortedStack.pop() + ", ");
		
//		System.out.println("\n Popping from Stack: ");
//		System.out.print(stack.pop());
//		System.out.print(stack.pop());
//		System.out.print(stack.pop());
//		System.out.print(stack.pop());
		
		//implement a stack using 2 Queues
		LinkedList<String> ll1 = new LinkedList<String>();
		LinkedList<String> ll2 = new LinkedList<String>();
		
		
		StackonaQueue.push("K", ll1, ll2);
		StackonaQueue.push("A", ll1, ll2);
		StackonaQueue.push("A", ll1, ll2);
		StackonaQueue.push("V", ll1, ll2);
		
		System.out.println("\n Popping from StackQueue: ");
		System.out.print(StackonaQueue.pop(ll1, ll2));
		System.out.print(StackonaQueue.pop(ll1, ll2));
		System.out.print(StackonaQueue.pop(ll1, ll2));
		System.out.print(StackonaQueue.pop(ll1, ll2));
		
	}
	
	//Sort in ascending order
	static Stack<Integer> sortStackAscending(Stack<Integer> stack) {
		Stack<Integer> smallerstack = new Stack<>();
		int cur;
		while(!stack.isEmpty()) {
			cur = stack.pop();
			
			while(!smallerstack.isEmpty() && (smallerstack.peek() < cur)) {
				//if the smallerstack has very small int than the cur, we pop it and add the 
				//current to the smallerstack first, to sort in ascending order.
				stack.push(smallerstack.pop());
			}
			smallerstack.push(cur); //cur int is small, just keep adding it to the smallerstack
		}
		//end of while loop, smaller stack will have big elements on the bottom
		return smallerstack;
		
	}
	
	static void push(String s, LinkedList<String> ll1, LinkedList<String> ll2) {
		//add to ll1
		//ll1.add(s);
		
		//copy from queue 1 to another queue 2
		int size = ll1.size();
		for (int i=0; i< size; i++) {
			ll2.add(ll1.remove());
		}
		
		ll1.add(s);
		//copy all elements back to queue 1
		size = ll2.size();
		for (int i=0; i< size; i++) {
			ll1.add(ll2.remove());
		}
		
	}
	
	static String pop(LinkedList<String> ll1, LinkedList<String> ll2) {
		return ll1.remove();
	}

}
