package kaavya.recursion;

public class RecursionPractice {
	static int i = 0; static int j= 0; static int counter = 0;
	public static void main(String[] args) {
		RecursionPractice p = new RecursionPractice();
		//p.callme();
		p.callme2(0);
		System.out.println("Factorial of 3 is:" + p.factorial(5));
		System.out.println("Sum of numbers between 3 and 8 is:" + p.rangeAdd(10, 13));
		i = 0; j = 0;
		System.out.println("Fibnoacci Series of 10 numbers is:");  p.fibonacci(0,1);
	}

	void callme() {
		 if (i <= 5) {
			 i++;
			System.out.println("Mathod Call:" + i);
			callme();
		 }
	}
	
	void callme2(int j){
		if (j <= 5) {
			j++;
			System.out.println("Mathod Call:" + j);
			callme2(j);
		}
			
	}
	
	//For recursion, focus on the break condition
	public int factorial(int num){
		//break when num is equal to 1
		if (num == 1) return num;
		
		System.out.println("num is: " + num);
		return num * factorial(num-1);
	}
	
	//To add numbers between 2 numbers
	//Recursion ends when the end number is greater than start
	public int rangeAdd(int st, int en) {
		if (st > en) return 0;
		return (st + rangeAdd(st + 1, en));
	}
	
	private void fibonacci(int st, int next){
		System.out.print(st + ",");
		++counter;
		if (counter >= 20)  return; //to break the recursion
		
		int temp = next;
		next = st + next; //find next in series by Adding 2 adjacent numbers
		st = temp;
		
		fibonacci(st, next);
		
	}
}
