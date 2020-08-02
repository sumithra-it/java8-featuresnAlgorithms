package kaavya.algorithms;

/*start with splitting the array into 2 halves and sort them separately
 * Next: Merge the 2 halves together.
 * To merge: copy all the array to aux array. have 2 pointers the beginning of 2 halves.
 * compare and find the lowest one and move it to the final array. Increment the corresponding pointer.
 */

public class MergeSort2 {	

/* Everything to left is sorted.
 * Scroll through the list on the right and insert the element to the correct sorted position on the left
 */
	private static void sort(int[] numbers, int lo, int high) {
		for (int i=lo; i<= high; i++) { //outer loop to scroll thru right of the list
			for (int j=i; j > lo; j--) { //inner loop to scroll thru the left of the list
				if (numbers[j] < numbers[j-1]) {
					exchangeIndex(numbers, j, j-1); //exchange with the lower number
				}
			}
		}	
	}
	private static void exchangeIndex(int[] numbers, int minIndex, int i) {
		int temp;
		//exchange numbers[i] with smallest element
		temp = numbers[i];
		numbers[i] = numbers[minIndex];
		numbers[minIndex] = temp;
	}
	private static void printArray(int[] arr) {
		System.out.println("\n Array is: "); 
		for (int i=0; i<= arr.length-1; i++)
			System.out.print(arr[i] + " ,");
	}
	
	public static void main(String[] args) {

		//int[] arr = {33, 11,12,10,14,13,9,7, 4, 8, 15, 1, 3, 6,20, 30, 22, 29, 28};
		int[] arr = { 4, 3, 2, 1,6,5};
		System.out.println("Length of array is: " + arr.length);

		//sort each halves
		MergeSort2.sort(arr, 0, arr.length/2 - 1);
		MergeSort2.sort(arr, arr.length/2 , arr.length-1);	
		
		//copy to an temp aux array
		int[] aux= new int[arr.length];
		for (int num=0 ; num < arr.length ; num++) aux[num] = arr[num];
		MergeSort2.merge(arr, aux, 0, arr.length - 1, arr.length/2 - 1);
		
		printArray(arr);
	}
	
	private static void merge(int[] arr, int[] auxarr, int lo, int hi, int mid) {
		//lo, mid, hi are indexes.
		//2 pointers to the beginning of 2 halves
		
		int i = lo; int j = mid+1;
		//pointer k is for inserting to the final sorted array
		for (int k=lo; k<= hi; k++) {
			if(i > mid) arr[k] = auxarr[j++]; //if left array index is already completely processed, simply copy over the right array and keep incrementing the j pointer
			else if(j > hi) arr[k] = auxarr[i++]; //if right array index is already completely processed, simply copy over the left array and keep incrementing the i pointer
			else if (auxarr[i] <= auxarr[j]) arr[k] = auxarr[i++]; //left array element with i index is the smallest, copy the smallest element to the final arr array & advance the i pointer
			else arr[k] = auxarr[j++]; //right array element with j index is the smallest, copy the smallest element to the final arr array & advance the j pointer					

		}//end arr k index loop
	}

 	private static void splitArray(int[] arr) {
		//split the array into 2 halves
		int llength= arr.length/2; int rlength = llength;
		
		boolean iseven = true;
		if ((arr.length) % 2 == 0) {
			iseven = true; System.out.println("Its a even number");
		} else {
			System.out.println("Its a odd number");
			iseven = false;
			rlength = llength +1;
		}
		
		int[] aleft = new int[llength];
		int[] aright = new int[rlength]; 
		for (int i=0; i < llength ; i++) 
			aleft[i] = arr[i]; //split first half
		
		int copyindex = 0;
		for(int i = llength ; i < arr.length; i++) {
			aright[copyindex] = arr[i]; //split second half 
			copyindex++;
		}
		System.out.println("Left Array is: ");printArray(aleft);
		System.out.println("Right Array is: ");printArray(aleft);
	}

}
