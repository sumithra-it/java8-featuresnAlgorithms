package kaavya.datastructures;
import kaavya.datastructures.DoublyLinkList.Node;
 
public class DoublyLinkListQuickSort {
	//assign a pivot and partition
	//sort from both left and right ends recursively
	
	public void sort(DoublyLinkList dll, Node left, Node right) {

		Node pivot = partition(dll, left, right);
		
//		sort(dll, left, pivot );
//		sort(dll, pivot.next, right);
		
	}
	
	public Node partition(DoublyLinkList dll, Node left, Node right) {
		Node pivot = dll.getHeadNode();
		int counter = 1;
		
		while( true) {
			++counter;
			System.out.println("counter: " + counter);
			while(left.data < pivot.data)
				left = left.next;
			System.out.println("Left pointer: " + left.data);
			
			while (right.data > pivot.data)
				right = right.prev;
			System.out.println("Right pointer: " + right.data);
			
			if(left.data == right.prev.data) break;
			//swap the left and right nodes
			dll.swap(left, right);
		}	
		//swap the partition element
		dll.swap(pivot, right);
		return pivot;
	}
	
	public static void main(String[] args) {
		
		DoublyLinkList dll = new DoublyLinkList();
		dll.add(1);
		dll.append(2);
		dll.append(3);
		dll.insertAfter(dll.getHeadNode().next, 5);
		dll.insertBefore(dll.getHeadNode().next, 4);
		dll.printList();
		System.out.println("");

		
		DoublyLinkListQuickSort dllsort = new DoublyLinkListQuickSort();
		Node head = dll.getHeadNode();
		Node right = dll.getLastNode();
		
		dllsort.sort(dll, head, right);
		dll.printList();
	}

}
