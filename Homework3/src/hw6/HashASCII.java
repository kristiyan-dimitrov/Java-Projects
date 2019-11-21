package hw6;

import java.util.ArrayList;

public class HashASCII {

	public static ArrayList<Integer> Hash(ArrayList<String> arr, int ArraySize){
		
		ArrayList<Integer> arrHashed = new ArrayList<Integer>();

		for (String el : arr) { // Go through each word in the array
			int sum = 0;
			for (int i = 0; i < el.length(); i++) { // Go through each character in the word
				char c = el.charAt(i); // Retrieve the character
				sum += (int) c; // Cast the character as int, which gives the ASCII value and add that to sum
			}
			
			
			arrHashed.add(sum%ArraySize);
		}
				
		return arrHashed;
	}
	
	
}
