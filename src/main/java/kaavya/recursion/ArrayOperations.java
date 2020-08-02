package kaavya.recursion;

import java.util.Arrays;

public class ArrayOperations {

	public static void main(String[] args) {
		int[] arr = {15, 10, 13, 12, 14, 16, 9};

		System.out.println("Largest is: " + findLargestNumber(arr));
		System.out.println("Smallest is: " + findSmallestNumber(arr));
		System.out.println("Missing number is: " + findMissingNumber(arr));
		
		findClosestPair(arr2);
		
		threewayPartition(arr3);
	}
	
	public static int findLargestNumber(int[] arr){
		int largest = arr[0];
		for(int i = 0; i < arr.length; i++) {
			if (arr[i] > largest) largest = arr[i];
		}
		return largest;
	}
	
	public static int findSmallestNumber(int[] arr){
		int num = arr[0];
		for(int i = 0; i < arr.length; i++) {
			if (arr[i] < num) num = arr[i];
		}
		return num;
	}
	
	//find missing number in a sequential array
	public static Integer findMissingNumber(int[] arr){
		int min = findSmallestNumber(arr);
		int max = findLargestNumber(arr); //int missing;
		for(int value = min; value < max; value++) {
			if (!valueFoundInArray(value, arr)) return value;
		}
		return null;
	}
	
	public static boolean valueFoundInArray(int value, int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			if (arr[i] == value) return true;
		}
		return false;
	}
	
	static int[] arr2 = {-9, -15, -2, 8, 23, 6, 13, 20};
	//find a pair which can add closest to 0 in a array
	public static void findClosestPair(int[] arr) {
		//sort the array
		Arrays.parallelSort(arr);
		printArr(arr);
		
		//have 2 counters and move them inside until you find a good matching pair that is closest to 0
		int low = 0, high = arr.length-1;
		int anslow = arr[low], anshigh = arr[high];
		while (low < high) {
			if(sum(arr[low], arr[high]) < 0) { 
				if(Math.abs(sum(arr[low], arr[high])) < Math.abs(sum(anslow, anshigh))) {
					anslow = arr[low];
					anshigh = arr[high];
				}
				++low;
			}else {
				if(sum(arr[low], arr[high]) < sum(low, high)) {
					anslow = arr[low];
					anshigh = arr[high];
				}	
				--high;
			}
		}
		System.out.println("\nFound pair whose sum is closest to 0: " + anslow + ", " + anshigh);
	}

	static int sum(int i, int j) {
		return (i+j);
	}
	
	//3 way partitioning algorithm
	//sort an array of 0, 1 and 2
	static int[] arr3 = {0,1,0,1,2,0,0,0,1,1,2,2};
	
	public static void threewayPartition(int[] arr) {
		int low = 0, mid = 0; int high = arr.length-1;
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[mid] ==2) {
				swap(mid, high, arr);
				--high;
			} else if(arr[mid] ==0) {
				swap(mid,low, arr);
				low++;
				mid++;
			} else {
				++mid;
			}
			//printArr(arr); debugging only
		}
		printArr(arr);
	}
	
	static void swap(int mid, int high, int[] arr){
		int temp = arr[mid];
		arr[mid] = arr[high];
		arr[high] = temp;
	}
	static void printArr(int[] arr) {
		System.out.println("\n");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + ", ");
		}
	}

}
