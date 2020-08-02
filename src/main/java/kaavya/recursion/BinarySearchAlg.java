package kaavya.recursion;

public class BinarySearchAlg {

	public static void main(String[] args) {
		int[] arr = {2,4,5,8,14,23,31,35,36};
		int key = 4;
		BinarySearchAlg alg = new BinarySearchAlg();
		int position = alg.findIndexOfNumber2(arr, 0, arr.length -1, key);
		//System.out.println("Position of number is: " + position);
		
		//find position of a number in a sorted rotated array
		int[] arr2 = {8,14,23,31,35,36,2,4,5};
		key = 8;
		//position = alg.findIndexOfNumber3(arr2, 0, arr2.length -1, key);
		//System.out.println("Position of number in rotataed sorted array is: " + position);
		
		System.out.println("******Min number in rotated array is:" + alg.findMinOfRotatedArr(arr2));
	}

	//recursion
	int findIndexOfNumber(int[] arr, int start, int end, int key){
		System.out.println("Value of start is:" + start + " end is:" + end);
		if(end >= start) {
			//find mid-point
			int mid = start + (end - start) / 2;
			if (arr[mid] == key) return mid;
			if (arr[mid] > key) 
				end = mid - 1;
			else	
				start = mid + 1;
			return findIndexOfNumber(arr, start, end, key);
		} else return -1;
	}
	
	//no recursion
	int findIndexOfNumber2(int[] arr, int start, int end, int key){
		System.out.println("Value of start is:" + start + " end is:" + end);
		int mid = -1;
		while(end >= start) {
			//find mid-point
			mid = start + (end - start) / 2;
			if (arr[mid] == key) return mid;
			if (arr[mid] > key) 
				end = mid - 1;
			else	
				start = mid + 1;
		}
		return -1;
	}
	
	//given array is sorted and rotated. 
	//to solve it, we need to find which part(left/right) is sorted 
	int findIndexOfNumber3(int[] arr, int start, int end, int key){
		int mid = -1;
		while(end >= start) {
			//find mid-point
			mid = start + (end - start) / 2;
			if (arr[mid] == key) return mid;
			
			if (arr[mid] <= arr[end] ) {	
				//right part is sorted			
				if ((arr[mid] > key) && (key <= arr[end])) 
					start = mid + 1;
				else
					end = mid -1;
			} else { //code comes here when arr[mid] > arr[end] as the array is rotated
				//left is sorted
				if ((arr[mid] > key) && (key >= arr[start]))
					start = mid + 1;
				else
					end = mid - 1;
			}
		}
		return -1;
		
	}
	
	//TBD: need to troubleshoot 
	int findMinOfRotatedArr(int[] arr) {
		int start = 0; int end = arr.length -1; int mid;
		while (start < end) {
			mid = start + (end - start) / 2;
			if (arr[mid] > arr[end])
				//min is in the right portion of rotated arr. No changes to end index. Change the start index to focus on right portion of arr.
				start = mid + 1;
			else
				//min is in the left portion of the rotated arr. no changes to start index. Change the end index to focus on left portion
				end = mid;
		System.out.println("Value of start is:" + start + " end is:" + end);	
		}
		return arr[start];
	}
}
