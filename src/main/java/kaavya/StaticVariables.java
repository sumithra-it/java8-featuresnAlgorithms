package kaavya;

import java.util.ArrayList;
import java.util.Collections;

public class StaticVariables {

		   public static void main(String args[]){
		     Student s1 = new Student();
		     s1.showData();
		     Student s2 = new Student();
		     s2.showData();
		     Student.b++;
		     s1.showData();
		     
		     ArrayList<Integer> maxelements = new ArrayList<>();
		      maxelements.add(2);maxelements.add(1);maxelements.add(7);maxelements.add(4);maxelements.add(2);
		      System.out.print("\nmaxelements:");
		      maxelements.forEach((p)-> System.out.print(p));
		      
		      int max = Collections.max(maxelements);
		      System.out.print("\nMax candy:" + max);
		  }
		}

		class Student {
		int a; //initialized to zero
		static int b; //initialized to zero only when class is loaded not for each object created.

		  Student(){
		   //Constructor incrementing static variable b
		   b++;
		  }

		   public void showData(){
		      System.out.println("Value of a = "+a);
		      System.out.println("Value of b = "+b);
		   }
		   
		public static void increment(){
		b++;
		}


		}
		
