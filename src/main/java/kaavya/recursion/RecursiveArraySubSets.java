package kaavya.recursion;
import java.util.List;
import java.util.ArrayList;

public class RecursiveArraySubSets {

	public static void main(String[] args) {
		int[] inputSet = {1,2,3};
		List<List<Integer>> outputList = new ArrayList<>();
		generate(0, new ArrayList<>(), inputSet, outputList);
		
		for (List<Integer> outputSubList : outputList) {
			System.out.println("\nSublist:" + outputSubList);
		}

	}

	static void generate(int index, List<Integer> partialSubset, int[] inputSet, List<List<Integer>> outputList) {
		
		//base/exit case
		if (index == inputSet.length) {
			outputList.add(new ArrayList<>(partialSubset));
			System.out.println("Added to Output List: " + partialSubset);
			return;
		}
		
		// Recurse WITH the item at 'currentIndex' in the powerset we are working on.
		partialSubset.add(inputSet[index]);
		System.out.println("Added to partial set:" + inputSet[index]);
		generate(index+1, partialSubset, inputSet, outputList);
		
		// When the recursion returns, remove the choice we made
		System.out.println("will be Removed from partial set:" + partialSubset.get(partialSubset.size()-1));
		partialSubset.remove(partialSubset.size()-1);
		
		//Recurse WITHOUT the item at 'currentIndex' in the powerset we are working on.
		generate(index+1, partialSubset, inputSet, outputList);
	}
}
