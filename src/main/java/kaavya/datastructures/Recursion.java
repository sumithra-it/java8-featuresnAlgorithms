package kaavya.datastructures;

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
	}

}
