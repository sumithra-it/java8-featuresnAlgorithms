package kaavya.algorithms;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SortingAlgorithmTest {
	
	@BeforeEach
	void setUp() throws Exception {
		
	}
	
	@Test
	public void TestQuickSort() {
		int[] arr = {33, 11,12,10,14,13,9,7};
		QuickSort.sort(arr);
		
		//convert primitive array to List<Integer>
		List<Integer> sortedList = Arrays.stream(arr).boxed().collect(Collectors.toList());
		
		List<Integer> testList = Arrays.asList(7,9,10,11,12,13,14,33);

		Assertions.assertTrue(sortedList.equals(testList), () -> "Array is not sorted correctly");
	}
}
