package kaavya.algorithms;

public class InsertionSort {

	 int[] numbers;
	 int length;
	
    public InsertionSort(int[] numbers) {
		super();
		this.numbers = numbers;
	}

/* Everything to left is sorted.
 * Scroll through the list on the right and insert the element to the correct sorted position on the left
 */
	private void sort() {
		for (int i=1; i<= numbers.length-1; i++) { //outer loop to scroll thru right of the list
			for (int j=i; j > 0; j--) { //inner loop to scroll thru the left of the list
				if (numbers[j] < numbers[j-1]) {
					exchangeIndex(j, j-1); //exchange with the lower number
				}
			}
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

		int[] arr = {33, 11,12,10,14,13,9,7};
		InsertionSort alg = new InsertionSort(arr);
		
		alg.sort();
		
		System.out.println("Sorted array is: ");
		for (int i=0; i<= arr.length-1; i++)
			System.out.print(arr[i] + " ,");
		
	}

}
