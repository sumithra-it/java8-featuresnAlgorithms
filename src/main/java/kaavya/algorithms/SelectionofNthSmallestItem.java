package kaavya.algorithms;

import java.util.Random;

/* Main idea: To find kth smallest element in an array
 * Partition for an element and return the partition index j
 * if partition index j is left of index k, then change the partition end pointer to partition the left side of the array 
 * if j is right of k, then change the beginning of the index to point to the right side half 
 * partition repeatedly to find the new index
 * 
 */
public class SelectionofNthSmallestItem {

	 int[] numbers;
	 int length;
	
    public SelectionofNthSmallestItem(int[] numbers) {
		super();
		this.numbers = numbers;
	}

    
    public static int selectNthElement(int[] arr, int k) {
    	shuffleArr(arr);
    	int lo = 0, hi=arr.length - 1;
    	while (hi > lo) {
    		int j = partition(arr, lo, hi);
    		if (j < k) lo = j + 1; //search for the right side and move the lo pointer
    		if (j > k) hi = j - 1; //search for the left side and move the hi pointer to make the array smaller
    		else return arr[k];
    	}
    	return arr[k]; //found the element
    }
    
    /* Partition the array for some k element, no entry to the left of k should be grater than j
     * 									no entry to the right of k should be less than j
     * Assign 2 pointers to each end of the array and do the partition
     */
	private static int partition(int[] arr, int lo, int hi) {
		int i = lo; int j = hi + 1;
		//partition element is first element which is lo
		while(true) {
			while(arr[++i] <= arr[lo]) if (i == hi) break; //find item to the left of lo to swap
			while(arr[--j] >= arr[lo]) if (j == lo) break;  //find item to the right of lo to swap
			if(i >= j) break; //if the pointers cross then the partition is complete.
			exchangeIndex(arr, i, j); //swap the i & j pointer elements
		}
		exchangeIndex(arr, lo, j); //swap the partition element
		return j;
	}
	
	private static void exchangeIndex(int[] numbers, int minIndex, int i) {
		int temp;
		//exchange numbers[i] with smallest element
		temp = numbers[i];
		numbers[i] = numbers[minIndex];
		numbers[minIndex] = temp;
	}
	
	public static void main(String[] args) {
		int[] arr = {17 ,9 ,10 ,1 ,22 ,3 ,14 ,13};
		System.out.println("\nOriginal Array is: ");
		for (int i=0; i<= arr.length-1; i++)
			System.out.print(arr[i] + " ,");
		
		System.out.println("\nFinding the 2rd smallest element in the array is: ");
		System.out.print(selectNthElement(arr, 2));	
		
		System.out.println("\nFinal Array is: ");
		for (int i=0; i<= arr.length-1; i++)
			System.out.print(arr[i] + " ,");
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
