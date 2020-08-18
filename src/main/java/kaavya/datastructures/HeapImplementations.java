package kaavya.datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import kaavya.datastructures.SingleLinkListOperations.Node;

public class HeapImplementations {
	// Comparator for Node class
	static class  NodeComparator implements Comparator<Node>{
		public int compare(Node obj1, Node obj2) {
			return (obj1.data - obj2.data);
		}
	}
	
	public static void main(String[] args) {
		//	find the kth smallest element in the array
		//Use Heap implementation - PriorityQueue when ever we need the max or min element
		int[] arr = {3, 1, -2, 5, 7};
		System.out.println("Kth smallest in the arr{3, 1, -2, 5, 7} using Heap in O(log N) is: ");
		ArrayList<Integer> list = findKthSmallest(arr, 2);
		list.forEach((p)  -> System.out.print(p + ", "));
		
		System.out.println("\nKth smallest in the arr {-2, 1, 6, 3, 4, 10, 2, 1}:");
		int[] arr2 = {-2, 1, 6, 3, 4, 10, 2, 1};
		findKthSmallest(arr2, 5).forEach((p)  -> System.out.print(p + ", "));
		
		
		//Merging K sorted lists
		LinkedList<Node> list1 = new LinkedList<>();LinkedList<Node> list2 = new LinkedList<>();LinkedList<Node> list3 = new LinkedList<>();
		list1.addFirst(new Node(3));list1.addFirst(new Node(4));
		list2.addFirst(new Node(2));list2.addFirst(new Node(5));
		list3.addFirst(new Node(6));
		LinkedList<Node> resultList = mergeKSortedLists(list1, list2, list3);
		System.out.println("\nMerging K sorted lists using heap:");
		resultList.forEach((p) -> System.out.print(p.data + ", "));
		
		System.out.println("\nMerging K sorted lists using heap: (2nd version):");
		Node[] arrNode= new Node[3];
		
		Node list1Node = new Node(2); list1Node.next = new Node(3); list1.add(list1Node);
		
		Node list2Node = new Node(-1); list2Node.next=new Node(4); list2.add(list2Node);
		
		Node list3Node = new Node(1); list3Node.next=new Node(5); list3.add(list3Node);

		arrNode[0] = list1Node;
		arrNode[1] = list2Node;
		arrNode[2] = list3Node;
		
		resultList = mergeKSortedLists2(arrNode);
		resultList.forEach((p) -> System.out.print(p.data + ", "));
		
		
		//finding median
		int[] arr3 = {3,6,2,4,7,1,5,12};//1,2,3,4,5,6,7,12
		System.out.println("\nMedian of Array:" +
		findMedian(arr3));
		
		
		//maxcandies in k minutes. Candies fill one half in each minute
		/* Facebook:
		 * You have N bags of candy. The ith bag contains arr[i] pieces of candy, and each of the bags is magical!
It takes you 1 minute to eat all of the pieces of candy in a bag (irrespective of how many pieces of candy are inside), and as soon as you finish, the bag mysteriously refills. If there were x pieces of candy in the bag at the beginning of the minute, then after you've finished you'll find that floor(x/2) pieces are now inside.
You have k minutes to eat as much candy as possible. How many pieces of candy can you eat?
		 */
		int[] arr4 = {2, 1, 7, 4, 2};
		System.out.println("\nMax candies: " + 
		maxCandies(arr4, 3));
		
	}
	
	static ArrayList<Integer> findKthSmallest(int[] arr, int k) {
		
		//We need the smallest. To remove the largest numbers, use a maxheap.
		//when we use a maxheap of size k, each time the queue exceeds capacity, we can remove the max elements
		PriorityQueue<Integer>  maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());
		
		for(Integer elem : arr) {
			maxHeap.add(elem);
			if (maxHeap.size() > k) maxHeap.poll();
		}
		//end of the for loop we will have the heap of size k with max elements removed
		
		//kth smallest
		ArrayList<Integer> smallList = new ArrayList<Integer>(maxHeap);
		
		return smallList; //index starts from 0
	}
	
	//Merge K sorted linked lists to one single list.
	//Input is array of linked lists
	//output is single merged linked list
	static LinkedList<Node> mergeKSortedLists(LinkedList<Node> list1, LinkedList<Node> list2, LinkedList<Node> list3){
		LinkedList<Node> resultList = new LinkedList<Node>();
		int k = 3; //since we have k lists
		
		PriorityQueue<Node> minHeap = new PriorityQueue<Node>(k, new NodeComparator()); //auxillary intermediate heap
		
		//Iterate through each of the 3 lists and migrate elements from each of the list to the aux Heap and next to the resulting list
		int max = Math.max(Math.max(list1.size(), list2.size()), list3.size());
		for (int i = 0; i <= max; i++) {
			heapnListOperation(list1, resultList, k, minHeap);
			heapnListOperation(list2, resultList, k, minHeap);
			heapnListOperation(list3, resultList, k, minHeap);
		}
		
		//remove the remaining elements from the heap and migrate to the resultArray
		while (!minHeap.isEmpty()) {
			resultList.add(minHeap.poll());
		}
		return resultList;
	}

	private static void heapnListOperation(LinkedList<Node> list, LinkedList<Node> resultList, int k,
			PriorityQueue<Node> minHeap) {
		
		Node cur = list.poll();
		if (cur != null) {
			minHeap.add(cur);
			
			if (minHeap.size() >= k+1) 
				resultList.add(minHeap.poll()); //remove the min from heap and add it to the result list 
		}
	}
	
	
	//consolidating array of lists together
	static LinkedList<Node> mergeKSortedLists2(Node[] lists){
		LinkedList<Node> resultList = new LinkedList<Node>();

		
		PriorityQueue<Node> minHeap = new PriorityQueue<Node>(new NodeComparator()); //auxillary intermediate heap
		
		//Iterate through each of the 3 lists and migrate elements from each of the list to the aux Heap and next to the resulting list
		for (Node node : lists) {
			if (node != null) {
				minHeap.add(node);
			}
		}
		System.out.println("\nSize of minHeap:" + minHeap.size());
		//remove the remaining elements from the heap and migrate to the resultArray
		while (!minHeap.isEmpty()) {
			//resultList.add(
			Node curHead = minHeap.poll();
			resultList.add(curHead);
			if (curHead.next != null) {
				minHeap.add(curHead.next);
			}
		}
		return resultList;
	}
	
	//median of array of numbers
	//create 2 heaps one for big numbers and one fpr small numbers. Median will be the biggest/max number in the big numbers heap
	//we can disregard the numbers in the small numbers heap
	public static int findMedian(int[] arr) {
		PriorityQueue<Integer> smallqueue = new PriorityQueue<>(Collections.reverseOrder());//maxheap
		PriorityQueue<Integer> bigqueue = new PriorityQueue<>();//minheap
		
		for (int i : arr) {
			if (!smallqueue.isEmpty() && i < smallqueue.peek()) 
				smallqueue.add(i);
			else bigqueue.add(i);
			
			//rebalance tree
			rebalanceHeaps(smallqueue, bigqueue);
		}
		
		if(arr.length %2 ==0 )
			return (bigqueue.poll() + bigqueue.poll())/2;
		else
			return bigqueue.poll();
	}
	
	//Our "balanced state" is when either both heaps are equal in size or the upperHalf
   // has 1 more element than the lowerHalf.
	public static void rebalanceHeaps(PriorityQueue<Integer> smallqueue, PriorityQueue<Integer> bigqueue) {
		if (bigqueue.size() > smallqueue.size() + 1) {
			smallqueue.add(bigqueue.poll());
		} else if (smallqueue.size() > bigqueue.size())
			bigqueue.add(smallqueue.poll());
	}
	
	
	 private static int maxCandies(int[] arr, int k) {
	    //construct heap for each element of array
	    ArrayList<PriorityQueue<Integer>> queueList = new ArrayList<PriorityQueue<Integer>>();
	    for (int i=0; i < arr.length ; i++){
	      queueList.add(new PriorityQueue<Integer>(Collections.reverseOrder()));
	      int num = arr[i];
	      PriorityQueue<Integer> curqueue = queueList.get(i);
	      curqueue.add(num);
	      for(int j = 2; j <= k ; j++) {//construct the queue for each element in queueList[i]
	    	  num = num/2;
	    	  curqueue.add(num);
	      }
	    }
	    printAllHeap(queueList);
	    
	    //in k minutes, pick the greatest of all queues
	    int totalcandy = 0;
	    for(int i = 1; i <= k ; i++) {
		    
		    ArrayList<Integer> maxelements = new ArrayList<>();
		    for(int j = 0; j < arr.length ; j++) {
		      maxelements.add(queueList.get(j).peek());
		    }
		      
		      int max = Collections.max(maxelements);
		      //System.out.print("\nMax candy:" + max);
		      int index = maxelements.indexOf(max);
		      totalcandy += queueList.get(index).poll(); 
	    }
	    System.out.print("\ntotalcandy:" + totalcandy);
	    return totalcandy;
	 }
	 
	 private static void printAllHeap(ArrayList<PriorityQueue<Integer>> queueList) {
		 for (PriorityQueue<Integer> queue : queueList) {
			 System.out.print("\nQueue:");
			 queue.forEach((p) -> System.out.print(p + ", "));
		 }
	 }
}
