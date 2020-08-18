package kaavya.recursion;

public class StringEnumeration {
	public static void main (String args[]) {
		
		String s = "S,U,M,I";
		
//		String[] arr = s.split(",");
		
//		System.out.println("\n Array is: "); 
//		for (int i=0; i<= arr.length-1; i++)
//			System.out.println(arr[i]);
		
		/*
		0,1,2,3
		1,2,3,0
		2,3,0,1
		3,0,1,2
		*/
//  for (int k = 1; k <= arr.length; k++) {
//		
//		String output; 
//		for (int j = 0; j <= arr.length; j++) {
//			output = new String();
//			int count = 0;
//			int i = j;
//			while(true) {
//				output.concat(arr[i]);
//				System.out.print(arr[i]);
//				i = i + k;
//				count++;
//				if (i > 3) i= 0; 
//				if (count > 3) break;
//			} //end while loop
//			System.out.println("\n");
//		}//end j loop
//		System.out.println("\n k is " + k);
//	}// end k loop
//  
  
	s = "ABC";
	System.out.println("Recursive String Enumeration:");
	printEnumerations("", s);
  
	}
	
	static void printEnumerations(String fixed, String unfixed) {
		
		if (unfixed.length() == 0) {
			System.out.println(fixed);
			return;
		}
		//move the char from unfixed to fixed
		for(int i = 0; i<=unfixed.length()-1; i++) {
			
			String newfixed = fixed + unfixed.charAt(i); //add the char to fixed
			
			String newunfixed = unfixed.substring(0,i)+ unfixed.substring(i+1);//remove the char from unfixed
			
			System.out.println("newfixed:" + newfixed + " newunfixed:" + newunfixed);
			
			//copy the left out unfixed to unfixed to fixed to print the new string
			
			
			printEnumerations(newfixed, newunfixed);
		}
		
	}

	
}
