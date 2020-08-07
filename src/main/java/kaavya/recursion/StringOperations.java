package kaavya.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class StringOperations {

	public static void main(String[] args) {
		String s = "ABC";
		StringOperations mystr = new StringOperations();
		//System.out.print("C".substring(0)); 
		//System.out.print("Kabvya".substring(5));
		
		
		printPermutation("", s);
		//System.out.println("Reverse of " + "sumithra is:" +  mystr.stringReverse("sumithra"));
				
		//recursion
		//System.out.println("Reverse of " + "sumithra is:" + mystr.stringReverseRecursion("sumithra"));
		
	    //System.out.println("Anagrams:" + mystr.anagramStrings("Suui", "Smui"));
		
		//System.out.println("Anagrams:" + mystr.anagramusingSort("abcd", "dcbb"));
		
	    //System.out.println("All unique: " + mystr.allUnique3("abcdez"));
	    
	    //System.out.println("Kth non repeating char is: " + mystr.findNonRepeatedChar2("kaavya", 3));
	    
	    //mystr.findSubstrings("Kaavya");
		

	}
	/* Start with the entire string as unfixed. Move the first char to fixed part and permuatate the remaining char
	 * in the unfixed part. 
	 */
	static void printPermutation(String fixed, String unfixed) {
		if (unfixed.length() == 0) {
			System.out.println(fixed);
			return;
			//Exit criteria for the loop is when all the char have been moved to the fixed part and unfixed is empty
		}
		
		for (int i = 0; i < unfixed.length() ; i++) {
			String newfixed = fixed + unfixed.charAt(i); //add first char to the already present fixed part
			String newunfixed = unfixed.substring(0, i) + unfixed.substring(i+1); // compute the unfixed by copying the remaining letters in front and back of the char which just got moved to the fixed part 
			printPermutation(newfixed, newunfixed); //recursively call the permutations to move more unfixed to fixed
		}
	}
	
	String stringReverse(String given) {
		String reversed = new String();
		for(int i = given.length()-1; i >=0 ; i-- ) {
			reversed = reversed + given.charAt(i);
		}
		return reversed;
	}
	
	//static String reversedStr = new String();
	
	public String stringReverseRecursion(String given) {
		int len = given.length() ;
		if ( len ==1 ) return given;
		
		//strip off the last char and call recursively
		return given.charAt(len-1) + stringReverseRecursion(given.substring(0,len-1)); //last letter is excluded by substring function
	}

	
	public boolean anagram(String str1, String str2) {
		//inefficient as we time complexity is 2 times O(n) as we are traversing the list twice 
		return (anagramStrings(str1, str2) && anagramStrings(str2,str1));
	}
	
	public boolean anagramStrings(String str1, String str2) {
	//check if each character of str2 is found in str1
		if (str1.length() != str2.length()) return false;

		for (int i = 0; i < str1.length() ; i++) {
			
			//if found, remove it from the str2 to avoid duplicate/repetitive char matches	
			int indexinStr2 = str2.indexOf(str1.charAt(i));
			if (indexinStr2 < 0) return false;
			str2 = str2.substring(0, indexinStr2) + str2.substring(indexinStr2+1, str2.length());
			
			//check if the str1 contains each character
//			if(!findifFound(str1, str2.charAt(i))) {
//				return false;
//			}
		}	
		return true;
	}
	boolean findifFound(String str1, char c){
		return str1.contains(Character.toString(c));
	}
	
	boolean anagramusingSort(String str1, String str2){
		
		//sort both strings
		char[] str1Arr = str1.toLowerCase().toCharArray();
		Arrays.sort(str1Arr);
		char[] str2Arr = str2.toLowerCase().toCharArray();
		Arrays.sort(str2Arr);
		
		str1 = Arrays.toString(str1Arr);
		str2 = Arrays.toString(str2Arr);
		
		//compare if they are same
		return str1.compareTo(str2) == 0;
	}
	
	//Check if String has all unique characters
	boolean allUnique(String str) {
		//create new HAshSet and add method of hasSet checks if the set already contains the element before inserting it
		//time complexity is O(n)
		HashSet<Character> hashset = new HashSet<Character>(str.length());
		for (int i = 0; i < str.length() ; i++) {
			if (hashset.add(str.charAt(i)) == false) return false; 
		}
		return true;
	}
	
	//using String methods
	boolean allUnique2(String str) {
		for (int i = 0; i < str.length() ; i++) {
		//	if (str.contains(Character.toString(str.charAt(i)))) return false;
		//if we use contains as above, the the first occurance will come back as a match.
			char c = str.charAt(i);
			if (str.indexOf(c) != str.lastIndexOf(c)) return false;
		}
		return true;
	}
	
	//using ascii code
	boolean allUnique3(String str2) {
		boolean[] asciiArr = new boolean[26];
		String str = str2.toUpperCase();
		for (int i = 0; i < str.length() ; i++) {
			//find ascii number
			int index = (int) str.charAt(i) - 65;
			System.out.println(index);
			if (asciiArr[index] == true) return false; //this means the char was already found in the ascii array
			else {
				asciiArr[index] = true;
			}
		}
		return true;
	}
	
	//Linked HashMap - used to produce a copy of a doubly linked list version of HAshMap that has the same order as the original
	//Find first non-repeated or kth non-repeated character in a String
	Character findNonRepeatedChar(String str, int k){
		LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
		
		for (int i = 0; i < str.length() ; i++) {
			Character c = str.charAt(i);
			//if found in map, increment it's count
			if(map.containsKey(c))
				map.put(c, map.get(c) + 1);
			else				
				//otherwise insert it with count1
				map.put(c, 1);
		}
		
		//iterate through the map to find the kth non-repetitive char
		 int counter=1;
		for (Character key : map.keySet()) {
			Integer value = map.get(key);
			if (value == 1) {
				if (counter == k)	return key;				
				counter++;
			}
		}
		return null;
	}
	
	Character findNonRepeatedChar2(String str, int k) {
		//if first index snd last index is same, that means the char is found only once.
		int counter = 1;
		for (int i = 0; i < str.length() ; i++) {
			char c = str.charAt(i);
			if (str.indexOf(c) == str.lastIndexOf(c)) {
				if (counter == k) return c;
				else counter++;
			}		
		}
		return null;
	}
	
	//finding all sub-strings
	ArrayList<String> findSubstrings(String str) {
		int len = str.length();
		ArrayList<String> arr = new ArrayList<>();
		for (int index = 0; index < len ; index++) {
			for (int width = index+1; width <= len ; width++) {
				arr.add(str.substring(index, width));
			}
		}
		
		//print all the Substrings
		arr.forEach(System.out :: println);
		return arr;
	}	
}
