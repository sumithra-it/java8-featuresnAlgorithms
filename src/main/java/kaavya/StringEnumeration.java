package kaavya;

public class StringEnumeration {
	public static void main (String args[]) {
		
		String s = "S,U,M,I";
		
		String[] arr = s.split(",");
		
//		System.out.println("\n Array is: "); 
//		for (int i=0; i<= arr.length-1; i++)
//			System.out.println(arr[i]);
		
		/*
		0,1,2,3
		1,2,3,0
		2,3,0,1
		3,0,1,2
		*/
  for (int k = 1; k <= arr.length; k++) {
		
		String output; 
		for (int j = 0; j <= arr.length; j++) {
			output = new String();
			int count = 0;
			int i = j;
			while(true) {
				output.concat(arr[i]);
				System.out.print(arr[i]);
				i = i + k;
				count++;
				if (i > 3) i= 0; 
				if (count > 3) break;
			} //end while loop
			System.out.println("\n");
		}//end j loop
		System.out.println("\n k is " + k);
	}// end k loop
	}
}
