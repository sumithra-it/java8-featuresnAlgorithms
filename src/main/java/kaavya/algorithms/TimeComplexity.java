package kaavya.algorithms;

public class TimeComplexity {
/*
 * It is O(log n) when we do divide and conquer type of algorithms e.g binary search. 
 * Another example is quick sort where each time we divide the array into two parts and each time it takes O(N) time to find 
 * a pivot element. Hence it is N O(log N)
 */
	public static void main(String[] args) {
		
		System.out.println("Log to the base 2:");
		logN(2);
		
		System.out.println("\nLog to the base 3:");
		logN(3);
		
		System.out.println("\nLog to the base 10:");
		logN(10);
		
		System.out.println("\nLinear N:");
		linearN(10);
		
		System.out.println("\nNLogN:");
		NlogN(16);
		
		System.out.println("\nN*N or N squared:");
		NtimesN(16);
	}
	
	public static void logN(int n) {
		for(int i = 1; i <= 200; i = i * n)
			  System.out.print(i + ", ");
	}

	public static void linearN(int n) {
		for(int i = 1; i <= n; i = i+1)
			  System.out.print(i + ", ");
	}
	
	public static void NlogN(int n) {
		for(int i = 0; i < n; i++) {
			System.out.println("\n");
		    for(int j = 1; j < n; j = j * 2)
				System.out.print(j + ", ");  
		}
	}
	
	public static void NtimesN(int n) {
		for(int i = 0; i < n; i++) {
			System.out.println("\n");
		    for(int j = 1; j < n; j++)
				System.out.print(j + ", ");  
		}
	}
}
