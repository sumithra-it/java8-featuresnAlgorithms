package kaavya.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringCombinations2 {

	public static void main(String[] args) {
		StringCombinations2 sc = new StringCombinations2();
		sc.letterCombinations(0, new StringBuilder(), "23", sc.output);
		sc.output.forEach(System.out::println);
	}
	  Map<String, String> phone = new HashMap<String, String>(){
		  {
		    put("2", "abc");
		    put("3", "def");
		    
		    put("4", "ghi");
		    put("5", "jkl");
		    put("6", "mno");
		    put("7", "pqrs");
		    put("8", "tuv");
		    put("9", "wxyz");
		  }
	 };
	 List<String> output = new ArrayList<String>();
/* 2 sets of strings - "abc" and "def"
 * 1. Start call for "a"
 * 2. Put or append to the working String.
 * 3. Recursive call to String in the 2nd group "def" started.
 * 4.  3 calls - a->d -> return from base/exit clause for recursion, backtrack and remove the last char that was appended
 * 5. 			 a->e -> return, backtrack, remove
 * 6. 			 a->f -> return, backtrack, remove
 * 7.  End of 3 recursive calls
 * 8. Resume/return to the recursion call on Original String "abc". Two more recursion calls for "b" and "c" are left.
 * 9. Whole thing repeats again.
 */
	private void letterCombinations(int currentDigit, StringBuilder partial, String digits, List<String> output) {
		
		if (currentDigit == digits.length()) {
			output.add(partial.toString());
			return;
		}

		String digitStr = Character.toString(digits.charAt(currentDigit));		
		String letters = phone.get(digitStr);
		
		for (char c : letters.toCharArray()) {
			partial.append(c);
			System.out.println(String.format("currentDigit, partial, digits: %s %s %s" , currentDigit , partial, digits));
			letterCombinations(currentDigit+1, partial, digits, output);
			partial.deleteCharAt(partial.length() - 1);
		}
	}
}
