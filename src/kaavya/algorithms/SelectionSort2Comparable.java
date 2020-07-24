package kaavya.algorithms;
import java.util.ArrayList;
import java.util.Comparator;

//TODO: Refactor code to point to Comparable class type instead of primitive int types
public class SelectionSort2Comparable {
	
	private static void sort(Object[] stdArr, Comparator<Student> c) {
		int minIndex;
		for (int i=0; i<= stdArr.length-1; i++) {
			
			minIndex = findSmallest(stdArr, i, c);
			
			exchangewithMin(stdArr, minIndex, i);
			
		}	
	}
	private static void exchangewithMin(Object[] stdArr, int minIndex, int i) {
		Object temp;
		//exchange numbers[i] with smallest element
		temp = stdArr[i];
		stdArr[i] = stdArr[minIndex];
		stdArr[minIndex] =  temp;
	}
	private static int findSmallest(Object[] sArr, int i, Comparator<Student> comparator) {
		//find the smallest element
		int minIndex = i; //min is the temp min variable which holds the least value
		for (int j = i; j <= sArr.length-1; j++) { //go through the loop and update the min value if you find one 
													//in the rest of the elements starting from index j
			if (comparator.compare((Student)sArr[j], (Student) sArr[minIndex]) < 0)  
				minIndex = j; 
		}//end of the above loop we will have the minIndex element in variable 'numbers[minIndex]'
		return minIndex;
	}
	
	public static void main(String[] args) {

		ArrayList<Student> arr = new ArrayList<Student>();
		SelectionSort2Comparable selectionSort = new SelectionSort2Comparable();
		arr.add(selectionSort.new Student(33, "Sumi33")); 
		arr.add(selectionSort.new Student(31, "Sumi31"));arr.add(selectionSort.new Student(22, "Sumi22"));
		arr.add(selectionSort.new Student(10, "Sumi10")); arr.add(selectionSort.new Student(25, "Sumi25"));
		arr.add(selectionSort.new Student(40, "Sumi40"));
		
		Comparator<Student> ageC = (Student st1, Student st2) -> st1.age - st2.age;
		
		//StudentAgeComparator ageC = selectionSort.new StudentAgeComparator();
		SelectionSort2Comparable.sort(arr.toArray(), ageC);
		
		printArray(arr);
		
		//for name comparator
		Comparator<Student> nameC = (Student st1, Student st2) -> st1.name.compareToIgnoreCase(st2.name);
		SelectionSort2Comparable.sort(arr.toArray(), ageC);
		
		printArray(arr);
	} //end main
	private static void printArray(ArrayList<Student> arr) {
		System.out.println("\nSorted array is: ");
		for (int i=0; i<= arr.size()-1; i++)
			System.out.print(arr.get(i) + " ,");
	}
	
	class Student {
		int age;
		String name;
		Student(int age, String name){
			this.age = age; this.name = name;
		}
		@Override
		public String toString(){
			return "Student: Age-" + this.age + " Name-" + this.name;
		}
	}
	
	
//	class StudentAgeComparator implements Comparator<Student>{
//		@Override
//		public int compare(Student st1, Student st2) {
//			return (st1.age - st2.age);
//		}	
//	}
//	class StudentNameComparator implements Comparator<Student>{
//		@Override
//		public int compare(Student st1, Student st2) {
//			 return st1.name.compareToIgnoreCase(st2.name);
//		}	
//	}	
}
