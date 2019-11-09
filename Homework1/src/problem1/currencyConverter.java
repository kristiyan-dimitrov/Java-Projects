package problem1;

import java.util.Scanner; // Need to import this in order to create a Scanner object and receive input into Java
import java.util.InputMismatchException;

public class currencyConverter {

	// This variable needs to be declared outside of the main method
	// so it can be used by all other methods in the class
	static float rate = 10;

	// Define a Scanner object that can be used by all methods in the class 
	// to read-in user input
	static Scanner inp = new Scanner(System.in);

	public static void main(String[] args){	
		
		float convertedAmount;
		int choice = 0;
		int quit = 0;
		
		do 
		{
			// Display menu with options to the user
			System.out.println(" ");
			System.out.println("The current exchange rage is " + rate + " yen to one usd");
			System.out.println(" ");
			System.out.println("What would you like to do? Enter 1, 2, 3, or 4:");
			System.out.println("1. Change the exchange rate");
			System.out.println("2. Convert yen to usd");
			System.out.println("3. Convert usd to yen");
			System.out.println("4. Quit application");
			System.out.println(" ");

			try {
				choice = inp.nextInt();
				if (choice != 1 && choice != 2 && choice != 3 && choice != 4 ){
				System.out.println("----------------------");
				System.out.println("ERROR! You entered an invalid command");
				System.out.println("Please enter a single number 1, 2, 3, or 4");
				}
				System.out.println(" ");
				System.out.println("Your choice is: " + choice);
				System.out.println(" ");	
			} catch(InputMismatchException ex) {
				inp.next();
				System.out.println("----------------------");
				System.out.println("ERROR! You entered an invalid command");
				System.out.println("Please enter a single number 1, 2, 3, or 4");
				// choice = inp.nextInt();	
			}

			// Execute different methods based on the user's choice
			if(choice == 1){
				System.out.println("Input New Exchange Rate (# of yen for 1 dollar):");
				rate = changeExchangeRate();
			}

			if(choice == 2){
					System.out.println("Enter the amount of yen you want to convert:");
					convertedAmount = convertYen();
					System.out.println(" ");
					System.out.println("----------------------");
					System.out.println("The number of yen you entered is equivalent to " + convertedAmount + " usd");	
			}

			if(choice == 3){
					System.out.println("Enter the amount of usd you want to convert:");
					convertedAmount = convertUsd();
					System.out.println(" ");
					System.out.println("----------------------");
					System.out.println("The number of usd you entered is equivalent to " + convertedAmount + " yen");
			}

			if(choice == 4){
				quit = 1;
			}

		}
		while (quit != 1 );
	}
	
	// Function for choice #1
	private static float changeExchangeRate(){
	
		float newRate;

		if (inp.hasNextFloat()){
			newRate = inp.nextFloat();
			return newRate;
		} else {
			System.out.println("ERROR! You probably entered an invalid rate");
			System.out.println("Please input a proper float rate value");
			inp.next(); // This forces the scanner to forget the wrong input of the user
			newRate = changeExchangeRate(); //Trying this recursively
			return newRate;
		}	
	}	

	// Function for choice #2
	private static float convertYen(){
		
		float converted;

		if (inp.hasNextFloat()){
			float amount = inp.nextFloat();
			converted = amount/rate;
			return converted;
		} else {
			System.out.println("ERROR! You probably entered an invalid amount");
			System.out.println("Please input a proper float amount value");
			inp.next();
			converted = convertYen();
			return converted;
		}
	}

	// Function for choice #3
	private static float convertUsd(){
		
		float converted;
		if (inp.hasNextFloat()){
			float amount = inp.nextFloat();
			converted = amount*rate;
			return converted;
		} else {
			System.out.println("ERROR! You probably entered an invalid amount");
			System.out.println("Please input a proper float amount value");
			inp.next();
			converted = convertUsd();
			return converted;
		}
	}
}