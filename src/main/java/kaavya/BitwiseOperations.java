package kaavya;

public class BitwiseOperations {

	public static void main(String[] args) {
		int i = 2, j = 5;
		
		System.out.println("Left shift 2 by 1: 2 << 1 = " + (i << 1));
		
		System.out.println("Left shift 2 by 2: 2 << 2 = " + (i << 2));
		
		System.out.println("Left shift 2 by 3: 2 << 3 = " + (i << 3));
		
		System.out.println("Right shift 5 by 1: 5 >> 1 = " + (j >> 1));
		
		System.out.println("Right shift 5 by 1: 5 >> 2 = " + (j >> 2));
		
		System.out.println("Right shift 5 by 1: 5 >> 3 = " + (j >> 3));
		
		i = -10;
		System.out.println("Left shift -10 by 1: -10 << 1 = " + (i << 1));
		System.out.println("Left shift -10 by 2: -10 << 2 = " + (i << 2));
		
		i = 10;
		System.out.println("10 & 5 = " + (i & 5));
		System.out.println("10 | 5 = " + (i | 5));
		
		System.out.println("XOR: 10 ^ 20 = " + (10 ^ 20));
		System.out.println("Inverse: ~10 = " + (~i));
		System.out.println("Inverse: ~5 = " + (~5));
		
		// Find all possible subsets of a set
		//Input: nums = [1,2,3]
		/*Output:
			[
			[3],
			[1],
			[2],
			[1,2,3],
			[1,3],
			[2,3],
			[1,2],
			[]
			]*/
		int arr[] = {1,2,3};
		possibleSubSets(arr);
	}

	static void possibleSubSets(int[] arr){
		int len = arr.length;
		
		//for a binary iterator i, execute the loop 2 to the power of n = 8 times
		//to get 2 power of n, we can shift 1 n times
		for (int i = 0; i < (1 << len)  ; i++) {
			System.out.print("{ ");
			
			for (int j=0; j < len ; j++) { //execute j loop n times
				if ((i & (1 << j)) > 0)  //i Bitwise & shift j to left once. If you get a non-zero result, its the binary number between 1 and 2*n
					System.out.print(arr[j] + " " );
			}
			System.out.println(" }");
		}
	}
}
