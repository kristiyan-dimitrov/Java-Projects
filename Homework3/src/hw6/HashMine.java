package hw6;

import java.util.ArrayList;

public class HashMine {

	public static ArrayList<Integer> Hash(ArrayList<String> arr, int ArraySize){
		
		// My Hashing Algorigthm is ((k*x)mod p)mod ArraySize
		// k is a small prime number I choose
		// p is a large (>200) prime number I choose
		// x is the sum of the ASCII codes of the letters
		
		ArrayList<Integer> arrHashed = new ArrayList<Integer>();
		int k = 5; // A constant for my hashing algorithm
		int p = 233; // A constant for my hashing algorithm
		
		for (String el : arr) { // Go through each word in the array
			int sum = 0;
			for (int i = 0; i < el.length(); i++) { // Go through each character in the word
				char c = el.charAt(i); // Retrieve the character
				sum += (int) c; // Cast the character as int, which gives the ASCII value and add that to sum
			}
			
			arrHashed.add(((k*sum)%p)%ArraySize);
		}
				
		return arrHashed;
	}
	
	
}