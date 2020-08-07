package kaavya.algorithms;
import java.util.Random;

/* QuickSort - 
 * Given an array and an element k as partition element of the array, put k at its correct
 * position in the sorted array and put smaller elements before it and larger elements after it.
 *  
 * 1. Shuffle the array if its a raw array (not for linked lists)
 * 2. Main idea: Partition the array for some k element, no entry to the left of k should be grater than j
 * 									no entry to the right of k should be less than j
 * 3. Start i pointer at beginning of the array, keep moving it as long as left of it <= a[k]
 * 4. k pointer is at the partition element.
 * 5. j pointer is at the end of array, keep moving it as long as right of it >= a[k] 
 * 6. Swap/exchange the elements of i pointer and j pointer.
 * 7. Partitioning is complete when the pointers have crossed each other.
 * 8. Exchange j with partition element so partition element k is at the correct position.
 * 9. Sort both sides of the partitioned array recursively.
 */

public class QuickSort {	

/* Partition the array for some k element, no entry to the left of k should be grater than j
 * 									no entry to the right of k should be less than j
 */
	private static int partition(int[] arr, int lo, int hi) {
		int i = lo; int j = hi + 1;
		//partition element is first element which is lo
		//DEBUG: System.out.println("Partition element is at position: " + lo + " value: " + arr[lo]);
		 
		while(true) {
			while(arr[++i] <= arr[lo]) if (i == hi) break; //find item to the left of lo to swap
			while(arr[--j] >= arr[lo]) if (j == lo) break;  //find item to the right of lo to swap
			if(i >= j) break; //if the pointers cross then the partition is complete.
			exchange(arr, i, j); //swap the i & j pointer elements
		}
		exchange(arr, lo, j); //swap the partition element
		return j;
	}
	
	//same algorithm with slightly different implementation for partition
	//last element is the pivot element and run the for loop finite number of times
	/*This function takes last element as pivot, places the pivot element at its 
	   correct position in sorted array, and places all smaller (smaller than  
	   pivot) to left of pivot and all greater elements to right of pivot */
	private static int partition2(int[] arr, int lo, int hi) {	
		int pivot = arr[hi];
		int pivotIndex = hi;
		int i = lo - 1; //index of smaller element
		for(int j = lo; j < hi ; j++) {
			if(arr[j] < pivot) {//if current element is smaller than pivot
				++i;
				if (i != j) exchange(arr, i, j);
			}
		}//end of for loop
		//swap the partition element with 
		exchange(arr,pivotIndex, ++i);
		printArray(arr);
		return i;
	}
	
	//partition element as lo
	private static int partition3(int[] arr, int lo, int hi) {	
		int pivot = arr[lo];
		int pivotIndex = lo;
		int i = lo - 1; //index of smaller element
		for(int j = lo; j < hi ; j++) {
			if(arr[j] < pivot) {//if current element is smaller than pivot
				++i;
				if (i != j) exchange(arr, i, j);
			}
		}//end of for loop
		//swap the partition element with 
		exchange(arr,pivotIndex, ++i);
		printArray(arr);
		return i;
	}
	
	private static void sort(int[] arr, int lo, int hi) {
		//recursively sort the left side and right side
		if (hi <= lo) return;
		int j = partition3(arr, lo, hi);
		sort(arr, lo, j-1);
		sort(arr, j+1, hi);
	}
	private static void exchange(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j]=temp;
	}
	private static void printArray(int[] arr) {
		System.out.println("\n");
		for (int i=0; i<= arr.length-1; i++)
			System.out.print(arr[i] + " ,");
	}
	
	public static void main(String[] args) {

		//int[] arr = {33, 11,12,10,14,13,9,7, 4, 8, 15, 1, 3, 6,20, 30, 22, 29, 28}; //
		int[] arr = {5, 4, 6, 1,3,2} ; 
		System.out.println("\nLength of array is: " + arr.length);	

		sort(arr);
	}
	public static void sort(int[] arr) {
		System.out.println("\nOriginal Array:"); printArray(arr);

//		shuffleArr(arr);
//		System.out.println("\nShuffled Array:"); printArray(arr);

		sort(arr, 0, arr.length-1);
		System.out.println("\nSorted Array:"); printArray(arr);
	}
	public static void shuffleArr(int[] arr) {
		Random random = new Random();
		int length = arr.length;
		for (int i=0; i <= length-1; i++) {
			int tempindex = random.nextInt(length);
			
			int element = arr[tempindex];
			arr[tempindex] = arr[i];
			arr[i] = element;
		}
		
	}
	
}
