package kaavya.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ListIterator;

public class ArrayOperations {

	public static void main(String[] args) {
		int[] arr = {15, 10, 13, 12, 14, 16, 9};

		System.out.println("Largest is: " + findLargestNumber(arr));
		System.out.println("Smallest is: " + findSmallestNumber(arr));
		System.out.println("Missing number is: " + findMissingNumber(arr));
		
		findClosestPair(arr2);
		
		threewayPartition(arr3);
		
		maxSumOfContigousSubArray(arr4);
		
		System.out.println("Printing subarrays with sum 9:");
		printSubArraysWithGivenSum(9, arr5);
		
		printLeadersInArray(arr6);
		
		findfirstrepeatingnumber(arr7);
		
		forLoop1();
		
		permuationsofArray();
		
		longestCommonPreFix(strArr);
		
		searchIn2DArray(45);
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

	static int[] arr4 = {-2, 1, -3, 4, -1, 2, 1, -5, 4 };
	//Max sum of contiguous sub-array
	static int maxSumOfContigousSubArray(int[] arr) {
		//contiguous sub-array can be 1 element or n elements long, but needs to be contiguous in index and cannot break in between.
		System.out.println("\n\n");
		int maxAtIndex = arr[0], maxsofar = arr[0], currentMax = arr[0];
		for(int i = 1; i < arr.length; i++) {
			
			//check if sum at the current index is the best.
			//			or
			//the contiguous sum from the previous step with this index is the best
			maxAtIndex =  Math.max(arr[i], maxAtIndex + arr[i]);
			maxsofar = Math.max(maxsofar, maxAtIndex);

			//DEBUG: System.out.println("Max at index " + i + " is:" + maxAtIndex + " maxsofar is:" + maxsofar);
		}
		System.out.println("\n Max sum of the contigous subarray is:" + maxsofar);
		return maxsofar;
	}
	
	//find contiguous sub-arrays with given sum in an array.
	//Given an Array of non negative Integers and a number. 
	//You need to print all the starting and ending indices of Subarrays having their sum equal to the given integer.
	static int[] arr5 = {2, 3, 6, 4, 9, 0, 9};
	static void printSubArraysWithGivenSum(int key, int[] arr) {
		
		int st = 0, end = 0; //initialize 2 pointers
		int sum = 0;
		
		while ((st <= arr.length-1) && (end <= arr.length)) {
			if (sum == key) {
				System.out.println("Starting index: " + st + " Ending index: " + (end-1));
				
				if(end <= arr.length-1) sum = sum + arr[end];
				
				++end;
				
			} else if (sum < key) {
				sum = sum + arr[end];
				++end;
			} else if (sum > key){//sum is > key, reduce the sum by removing an element and increment the start pointer
				sum = sum - arr[st];
				++st;
			}
			//DEBUG: System.out.println("Current sum is: " + sum);//Debugging only
		}
		
	}
	
	static int[] arr6 ={14, 12, 70, 75, 99, 65, 21, 90};
	static void printLeadersInArray(int[] arr) {
		int leader = arr[0];
		System.out.println("Leaders of the array are: ");
		for(int i = 1; i<=arr.length-1; i++) {
			if (arr[i]  > leader) {
				leader = arr[i]; //elect new leader
				//System.out.print(arr[i] + ",");
			} else {
				System.out.print(arr[i-1] + ",");
				leader = arr[i];//reset the new leader
			}
				
		}
		System.out.println(leader);//print last leader
	}
	
	static int[] arr7 = {10, 7, 8, 1, 8, 7, 6};
	static void findfirstrepeatingnumber(int[] arr) {
		
		//create a hash set to keep track of which element exists how many times
		HashSet<Integer> set = new HashSet<>();
		int repeatingIndex = arr.length;
		for(int i = arr.length-1; i>=0; i--) {
			//DEBUG: System.out.println("Value of i: " + i + " arr[i]: " + arr[i]);
			if (set.contains(arr[i])) {
				if(i < repeatingIndex) repeatingIndex = i;
					System.out.println("repeatingIndex is:" + repeatingIndex);
			} else {
				set.add(arr[i]);
			}

		}
		System.out.println("\n\nFirst Repeating element is: " + arr[repeatingIndex]);
	}
	
	
	static void forLoop1() {
		System.out.println("\nPrinting for loop reverse1:");
		int[] arr = {1,2,3,4,5,6,7};
		for(int i = arr.length-1; i>=0; i--) {
			System.out.print(arr[i] + ",");
		}
	}
	
	//TODO
	static void permuationsofArray() {
		int[] arr = {1,2,3};
		ArrayList<Integer> fixed = new ArrayList<Integer>();
		ArrayList<Integer> unfixed = new ArrayList<Integer>();
		
		//add all elements to unfixed array initially
		for (int i : arr) {
			unfixed.add(i);
		}
		System.out.println("\nRaw Array:");
		unfixed.forEach(System.out :: print);
		
		System.out.println("\n\nPrinting Permutations: ");
		permutate(unfixed, fixed);

		permutate("ABC", "");
		
	}
	
	static void permutate(ArrayList<Integer> unfixed, ArrayList<Integer> fixed){

		if (unfixed.size() == 0) {
			System.out.println("");
			fixed.forEach(e -> System.out.print(e));
			return;
		}
		
		for(int index =0; index< unfixed.size(); index++) {

			//move one element from unfixed to fixed.
			int current = unfixed.get(index); //i is the index position
		    //System.out.println("Moving element to fixed: " + current);
			ArrayList<Integer> newfixed = new ArrayList<>();
			newfixed.addAll(fixed);
			newfixed.add(current); //add the current with the existing fixed elements
			//fixed.add(current); //gives wrong output. Create a new fixed each time.
			
			//remove the element from unfixed
			//unfixed.remove(current); //cannot alter and remove fro ArrayList whch you are currently iteratnig.
			//Create a new ArrayList.
			ArrayList<Integer> newunfixed = new ArrayList<>();
			for (int uf : unfixed) {
				if (uf != current)
					newunfixed.add(uf);
			}
			
			//recursively permuatate the remaining unfixed elements
			permutate(newunfixed, newfixed);
		
		}
	}
	

	//Permutate String
	static void permutate(String unfixed, String fixed){

		if (unfixed.length() == 0) {
			System.out.println("\nPermutation: " );
			System.out.print(fixed);
			return;
		}
		
		for(int index =0; index< unfixed.length(); index++) {

			//move one element from unfixed to fixed.
			String newfixed = fixed + unfixed.substring(index, index+1); //unfixed.charAt(index);//i is the index position
			
			//remove the element from unfixed
			String newunfixed = unfixed.substring(0, index) + unfixed.substring(index+1);
			
			//recursively permuatate the remaining unfixed elements
			permutate(newunfixed, newfixed);
		
		}
	}
	
	// longest common prefix in array of Strings.
	static String[] strArr={"javaog","javaworld","java2bean","java2temp"};
	static void longestCommonPreFix(String[] arr){
		//find shortest string so we dint get into ArrayIndexOutOfBouds exception
		int minlen = strArr[0].length(); String minEntry = strArr[0];
		for (String str : strArr) {
			if (str.length() < minlen) {
				minlen = str.length();
				minEntry = str;
			}		
		}
		System.out.println("\nminlen is: " + minlen);
		System.out.println("minEntry is: " + minEntry);
		
		//iterate the strArr and find the common prefix
		String longestPrefix = "";char ch;
				
		longestPrefix = ""; String str = strArr[0];
		
			
		//find the longestPrefx
		for (int index=0; index< minlen; index++) {
			ch = str.charAt(index);
			//System.out.println("Value of ch is:" + ch + ", value from minEntry is:" + minEntry.charAt(index));
			if (ch == minEntry.charAt(index)) {
				longestPrefix = longestPrefix.concat(Character.toString(ch));
			
			} else
				break; 
		}
		
		minEntry = longestPrefix;
		minlen = minEntry.length();
		System.out.println("Longest Common Prefix is1: " + longestPrefix);
			
			
		//loop through rest of the elements and adjust the longestPrefix if needed
		for (int j=1; j <= strArr.length-1 ; j++) {
			str = strArr[j];
			
			for (int index=0; index< minlen; index++) {
				ch = str.charAt(index);
				if (ch != minEntry.charAt(index)) {
					
					//reduce the longestPrefix by the non-common char
					if (longestPrefix.charAt(index) == minEntry.charAt(index)) {
						longestPrefix = longestPrefix.substring(0, index);
						minlen = longestPrefix.length();
					}
					break;
				}
			}
		}//end outer for
		System.out.println("\nLongest Common Prefix is2: " + longestPrefix);
	}
	
	//row wise and column wise sorted matrix ,we need to search element with minimum time complexity
	static int[][] sortedMatrix = 
		 { 
		 { 1, 6, 10, 12, 20 }, 
		 { 4, 8, 15, 22, 25 }, 
		 { 5, 20, 35, 37, 40 },
		 { 10, 28, 38, 45, 55 } 
		 };
	static void searchIn2DArray(int key){
		int rows =  sortedMatrix.length;
		int columns = sortedMatrix[0].length;
		
		int r = 0, c = columns-1;
		
		while ((r <= rows-1) && (c <= columns -1))
		if (sortedMatrix[r][c] < key ) 
			++r;
		else if (sortedMatrix[r][c] > key)
			--c;
		else {
			System.out.println("Key found at: (" + r + ", " + c + ")");
			break;
		}
		
		if ((r == rows) || (c == columns)) 
			System.out.println("Key not found in the 2D matrix");
	}

	
}
