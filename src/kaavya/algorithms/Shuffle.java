package kaavya.algorithms;
import java.util.Arrays;
import java.util.Random;
import java.util.List;

/*
 * One way - Generate a random number for each element and sort based on the random number as key.
 * Since Sorting is an expensive operation, we should avoid it and this method is not efficient.
 * 
 * Efficient way - Move from left to right and generate a random integer between the index and swap the 
 * random integer position and the index you are working with. Move to the end of the array.  
 */
public class Shuffle {

	 Integer[] numbers;
	 int length;
	
    public Shuffle(Integer[] numbers) {
		super();
		this.numbers = numbers;
	}

/* Everything to left is sorted.
 * Scroll through the list and generate random integer index and swap the element
 */
	private void sort() {
		Random random = new Random();
		int indexr;
		for (int j = 1; j <= numbers.length-1; j++) { // loop to scroll 
			indexr =random.ints(0, j).findFirst().getAsInt(); //generate the random integer within the range of the index
			exchangeIndex(j, indexr); //exchange with the random int index 
		}
	}
	private void exchangeIndex(int minIndex, int i) {
		int temp;
		//exchange numbers[i] with smallest element
		temp = numbers[i];
		numbers[i] = numbers[minIndex];
		numbers[minIndex] = temp;
	}

	
	public static void main(String[] args) {

		Integer[] sortedarr = {7 ,9 ,10 ,11 ,12 ,13 ,14 ,33};
		Shuffle alg = new Shuffle(sortedarr);
//		Random random = new Random();
//		System.out.println("Random num is: " + random.ints(1, 2).findFirst().getAsInt());
		alg.sort();
		
		System.out.println("Shuffled array is: ");
		//java 8
		List<Integer> list = Arrays.asList(sortedarr);
		list.forEach(System.out :: println);
		
		list.forEach((i) -> System.out.print(i + " "));
	}

}
