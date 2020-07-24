package kaavya.algorithms;

/*
 *  Merge the 2 halves recursively.
 * To merge: copy all the array to aux array. have 2 pointers the beginning of 2 halves.
 * compare and find the lowest one and move it to the final array. Increment the corresponding pointer.
 */

public class MergeSort2Recursive {	

/* Everything to left is sorted.
 * Scroll through the list on the right and insert the element to the correct sorted position on the left
 */
	private static void recursivesort(int[] arr, int lo, int hi) {
		if (hi <= lo) return;
		int mid = lo + (hi - lo) / 2;

		recursivesort(arr, lo, mid);
		//System.out.println("First half Completed...");printArray(arr);
		
		recursivesort(arr, mid+1, hi);
		//System.out.println("Second half Completed...");printArray(arr);
		
		System.out.println("Calling merge with lo:" + lo + " mid:" + mid + " hi:" + hi);
		merge(arr, lo, mid, hi);
	}
	private static void sort(int[] arr) {
		recursivesort(arr, 0, arr.length - 1);
	}

	private static void printArray(int[] arr) {
		System.out.println("\n Array is: "); 
		for (int i=0; i<= arr.length-1; i++)
			System.out.print(arr[i] + " ,");
	}
	
	public static void main(String[] args) {

		int[] arr = {33, 11,12,10,14,13,9,7, 4, 8, 15, 1, 3, 6,20, 30, 22, 29, 28};
	//	int[] arr = { 5, 4, 6, 1,3,5};
		System.out.println("Length of array is: " + arr.length);	
		
		sort(arr);
		
		System.out.println("Final sorted array is:");
		printArray(arr);
	}
	
	private static void merge(int[] arr, int lo, int mid, int hi) {
		//copy to an temp aux array
		int[] auxarr= new int[arr.length];
		for (int num=0 ; num < arr.length ; num++) auxarr[num] = arr[num];
		
		//lo, mid, hi are indexes.
		//2 pointer to the beginning of 2 halves
		
		int i = lo; int j = mid+1;
		//pointer k is for inserting to the final sorted array
		for (int k=lo; k<= hi; k++) {
			if(i > mid) arr[k] = auxarr[j++]; //if left array index is already completely processed, simply copy over the right array and keep incrementing the j pointer
			else if(j > hi) arr[k] = auxarr[i++]; //if right array index is already completely processed, simply copy over the left array and keep incrementing the i pointer
			else if (auxarr[i] <= auxarr[j]) arr[k] = auxarr[i++]; //left array element with i index is the smallest, copy the smallest element to the final arr array & advance the i pointer
			else arr[k] = auxarr[j++]; //right array element with j index is the smallest, copy the smallest element to the final arr array & advance the j pointer					

		}//end arr k index loop
	}



}
