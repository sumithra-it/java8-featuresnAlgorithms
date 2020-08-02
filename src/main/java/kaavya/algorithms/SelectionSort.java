package kaavya.algorithms;

public class SelectionSort {

	 int[] numbers;
	 int length;
	
public SelectionSort(int[] numbers) {
		super();
		this.numbers = numbers;
	}
	
	private void sort() {
		int minIndex;
		for (int i=0; i<= numbers.length-1; i++) {

			minIndex = findSmallest(i);
			
			exchangewithMin(minIndex, i);
			
		}	
	}
	private void exchangewithMin(int minIndex, int i) {
		int temp;
		//exchange numbers[i] with smallest element
		temp = numbers[i];
		numbers[i] = numbers[minIndex];
		numbers[minIndex] = temp;
	}
	private int findSmallest(int i) {
		//find the smallest element
		int minIndex = i; //min is the temp min variable which holds the least value
		for (int j = i; j <= numbers.length-1; j++) { //go through the loop and update the min value if you find one 
													//in the rest of the elements starting from index j
			if (numbers[j] < numbers[minIndex])  
				minIndex = j; 
		}//end of the above loop we will have the minIndex element in variable 'numbers[minIndex]'
		return minIndex;
	}
	
	public static void main(String[] args) {

		int[] arr = {33, 11,12,10,14,13,9,7};
		SelectionSort alg = new SelectionSort(arr);
		
		alg.sort();
		
		System.out.println("Sorted array is: ");
		for (int num : arr)
			System.out.print(num + " ,");
		
//		for (int i=0; i<= arr.length-1; i++)
//			System.out.print(arr[i] + " ,");
		
	}

}
