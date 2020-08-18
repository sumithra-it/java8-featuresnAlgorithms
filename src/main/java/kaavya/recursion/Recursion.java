package kaavya.recursion;

public class Recursion {

	static int gcd(int p, int q) {
		if (q == 0) return p;
		else {
			System.out.println("Modulo of " + p + " and " + q + " is " + p%q);
			
			//recursion
			return gcd(q, p%q);
		}
	}
	public static void main(String[] args) {
		int p = 64; int q = 164;
		System.out.println("GCD of " + p + " and " + q + " is " + gcd(p, q));
		
		int n = 8;
		System.out.println("Sum of N=" + n + " numbers is: " + sumofNNumbers(n));
		n=8;
		System.out.println("Sum of N=" + n + " numbers using head Recursion: " + sumofNNumbersHead(n));
		
		int num = 5; n = 3;
		System.out.println(String.format("%d th power of %d is: %d" , n, num,
		findNthpower(num, n)));
		
		n = 7;
		System.out.println("Fibonnaci series for " + n + " th position is:" + 
		fibonnacci(1, 1, n, 2));
		System.out.println("Fibonnaci Simple model for " + n + " th position is:" + 
				fibonnacciSimpleModel(n));
		
	}
	
	//finding nth power of any number
	static int findNthpower(int num, int n) {
		if (n == 1) {
			return num;
		}
		return (num * findNthpower(num, n -1));
		
	}
	
	//Tail Recursion - Sum of integers from n to 0:
	static int sumofNNumbers(int n) {
		if (n ==1) {
			return 1;
		}
		int sum =  (n + sumofNNumbers(n-1)); //For debugging created variable sum. Just return from here.
		return sum;
	}
	
	//Head Recursion - Sum of integers from n to 0:
	static int sumofNNumbersHead(int n) {

		if (n > 1) {
			int sum = n + sumofNNumbersHead(n-1);
			return sum;
		}
		else 
			return 1;
	}

	//start with sum = 1; n is the Nth number in the series; curN is 1 
	static int fibonnacci( int prev, int num, int position, int curN) {
		if (curN == position)
			return num;
		++curN;
		//logic: prev + num
		return fibonnacci(num, prev + num, position, curN);
	}
	
	//model fibonnaci: fibonnacci of n is sum of fibonnacci of previous 2 numbers which is (n-1) and (n-2)
	static int fibonnacciSimpleModel( int n) {
		if (n <= 1)
			return 1;
		return fibonnacciSimpleModel(n-1) + fibonnacciSimpleModel(n-2);
	}
}
