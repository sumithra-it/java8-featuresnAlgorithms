package kaavya.algorithms;

/*
 * Same as insertion sort, but more efficient as we skip h and sort every h element
 * Ex:  Sort every 13th element, starting from 13 and go upwards to end of array
 * Next sort every 4th element, starting from 4 & go upwards to 5,6,7
 *  Last iteration is insertion sort where each elements is sorted with its own adjacent element one at a time
 *  
 *  result is a much faster and efficient insertion sort where the number of iterations are drastically reduced for a pure insertion sort 
 */
public class ShellSort {

	 int[] numbers;
	 int length;
	
    public ShellSort(int[] numbers) {
		super();
		this.numbers = numbers;
	}

/* Everything to left is sorted.
 * Scroll through the list on the right by skipping n and insert the element to the correct sorted position on the left
 */
	private void sort() {
		int N = numbers.length;
		int h = 1;
		while (h < N/3) h = 3 * h + 1; //1, 4, 13, 40, 121, 364 ...
		System.out.println("Starting value of h is: " + h);
		
		while (h >= 1) { //outer loop to scroll thru right of the list
			System.out.println("h sorting the array - value of h is: " + h);
			for (int i = h; i < N; i++) {
				System.out.println("Outer loop: i and h reset to: " + i);
				for (int j = i; j >= h; j -= h) { //inner loop to scroll thru the left of the list				
					System.out.println("Inner loop: comparing j :" + j + " and j-h  at:" + (j - h));	
					if (numbers[j] < numbers[j-h])
						exchangeIndex(j, j-1); //exchange with the lower number
				} // end inner for loop
			} //end outer for
			h = h/3;
			System.out.println("Next batch for h is: " + h);
		}	
	}
	private void exchangeIndex(int minIndex, int i) {
		int temp;
		//exchange numbers[i] with smallest element
		temp = numbers[i];
		numbers[i] = numbers[minIndex];
		numbers[minIndex] = temp;
	}

	
	public static void main(String[] args) {

		int[] arr = {33, 11,12,10,14,13,9,7, 33, 11,12,10,14,13,9,7};
		ShellSort alg = new ShellSort(arr);
		
		alg.sort();
		
		System.out.println("Sorted array is: ");
		for (int i=0; i<= arr.length-1; i++)
			System.out.print(arr[i] + " ,");
		
	}

}
