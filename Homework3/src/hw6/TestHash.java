package hw6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;


public class TestHash {

	public static void main(String[] args) {
		Scanner inp = new Scanner(System.in); // Creating a scanner to read in user input
		int ArraySize;
		while(true) { // Asking user for size of Array (100 or 200)
			try {
				System.out.println("What size array would you like, 100 or 200? Press 1 or 2");
				System.out.println("1. Array Size = 100");
				System.out.println("2. Array Size = 200");
				int userInput = inp.nextInt();
				if (userInput != 1 && userInput != 2) {
					System.out.println("Please enter a valid option: 1 or 2");
				} else {
					ArraySize = userInput * 100;
					break;
				}
				
			} catch(InputMismatchException ex) {
				System.out.println("Please enter a valid option: 1 or 2");
			}
		}
		inp.close(); // Closing Scanner object is good practice
		// Read in input.txt
		ArrayList<String> arrInput = Input.LoadInput();

//		HASH FUNCTION 1
		ArrayList<Integer> hashCode1Values = HashASCII.Hash(arrInput, ArraySize);
		
		// Rescaling hashed data to fit between 0 and ArraySize (100 or 200)
		ArrayList<Integer> hashCode1Scaled = Rescale.RescaleASCII(hashCode1Values, ArraySize);
		
		// Creating output1 data
		ArrayList<String> output1Data = new ArrayList<String>(Collections.nCopies(ArraySize, "EMPTY LINE..."));
		for (int i = 0; i < ArraySize; i++) {	
			// If the element is EMPTY LINE then replace with i:Name
			if(output1Data.get(hashCode1Scaled.get(i)) == "EMPTY LINE...") {
				output1Data.set(hashCode1Scaled.get(i), String.valueOf(hashCode1Scaled.get(i)) + ":" + arrInput.get(i));
			} else { // Else, just append to the String value the name
				String line = output1Data.get(hashCode1Scaled.get(i));
				output1Data.set(hashCode1Scaled.get(i), line + ", " + arrInput.get(i));
			}
			
		}
		
		System.out.println("VERIFYING RESULTS FOR HASH FUNCTION 1");
		for (String el : output1Data) {
			System.out.println(el);
		}
		// Write to output1 file
		SaveToFile.Write("output1.txt", output1Data);
				
		
//		HASH FUNCTION 2
		
		// Store hashed values from X.hashCode()
		ArrayList<Double> hashCode2Values = new ArrayList<Double>();
		for (String el : arrInput) {
			hashCode2Values.add((double) el.hashCode());
		}
		
		// Rescaling hashed data to fit between 0 and ArraySize (100 or 200)
		ArrayList<Integer> hashCode2Scaled = Rescale.RescaleData(hashCode2Values, ArraySize);
		
		// Creating output2 data
		ArrayList<String> output2Data = new ArrayList<String>(Collections.nCopies(ArraySize, "EMPTY LINE..."));
	
		for (int i = 0; i < ArraySize; i++) {	
			// If the element is EMPTY LINE then replace with i:Name
			if(output2Data.get(hashCode2Scaled.get(i)) == "EMPTY LINE...") {
				output2Data.set(hashCode2Scaled.get(i), String.valueOf(hashCode2Scaled.get(i)) + ":" + arrInput.get(i));
			} else { // Else, just append to the String value the name
				String line = output2Data.get(hashCode2Scaled.get(i));
				output2Data.set(hashCode2Scaled.get(i), line + ", " + arrInput.get(i));
			}
			
		}
		
		System.out.println("VERIFYING RESULTS FOR HASH FUNCTION 2");
		for (String el : output2Data) {
			System.out.println(el);
		}
		// Write to output2 file
		SaveToFile.Write("output2.txt", output2Data);
		
//		HASH FUNCTION 3
		ArrayList<Integer> hashCode3Values = HashMine.Hash(arrInput, ArraySize);
		
		// Rescaling hashed data to fit between 0 and ArraySize (100 or 200)
		ArrayList<Integer> hashCode3Scaled = Rescale.RescaleASCII(hashCode3Values, ArraySize);
		
		// Creating output3 data
		ArrayList<String> output3Data = new ArrayList<String>(Collections.nCopies(ArraySize, "EMPTY LINE..."));
		for (int i = 0; i < ArraySize; i++) {	
			// If the element is EMPTY LINE then replace with i:Name
			if(output3Data.get(hashCode3Scaled.get(i)) == "EMPTY LINE...") {
				output3Data.set(hashCode3Scaled.get(i), String.valueOf(hashCode3Scaled.get(i)) + ":" + arrInput.get(i));
			} else { // Else, just append to the String value the name
				String line = output3Data.get(hashCode3Scaled.get(i));
				output3Data.set(hashCode3Scaled.get(i), line + ", " + arrInput.get(i));
			}
			
		}
		
		System.out.println("VERIFYING RESULTS FOR HASH FUNCTION 3");
		for (String el : output3Data) {
			System.out.println(el);
		}
		// Write to output3 file
		SaveToFile.Write("output3.txt", output3Data);
		
//		ALGORITHM EVALUATION
		System.out.println("--------------------------");
		System.out.println("The data for the three hashing algorithms");
		System.out.println("is printed above and saved to three .txt files");
		System.out.println("--------------------------");
		System.out.println("---ALGORITHM EVALUATION---");
		System.out.println("--------------------------");
		System.out.println("Metrics for ASCII Hashing");
		HashingMetrics.Evaluate(output1Data, ArraySize);
		System.out.println("--------------------------");
		System.out.println("Metrics for Java in-built Hashing");
		HashingMetrics.Evaluate(output2Data, ArraySize);
		System.out.println("--------------------------");
		System.out.println("Metrics for my Hashing");
		HashingMetrics.Evaluate(output3Data, ArraySize);
		System.out.println("--------------------------");
		System.out.println("Two observations:");
		System.out.println("1) The in-built hashing method gives the fewest empty lines and the best distribution for the data for both sizes of array");
		System.out.println("2) My hashing algorithm gives fewer empty lines than the ASCII hashing for both ArraySizes,");
		System.out.println("but my distribution is better only for ArraySize = 200");
		
		
	}

}

// GRAVEYARD
// System.out.println(-5%3);

