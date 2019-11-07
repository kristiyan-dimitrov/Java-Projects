// NOTES:
// - Look at Sample2 java on how you cn define your own internal functions that can then be called from main functions
// 	- more specifically, this will probably be 2 functions, one for yen and one for usd that are called from within main


import java.io.IOException; // This makes error messages more verbose
import java.util.Scanner; // Need to import this in order to create a Scanner object and receive input into Java
import java.util.InputMismatchException;

public class currencyConverter {

	// This variable needs to be declared outside of the main method
	// so it can be used by all other methods in the class
	static float rate = 10;

	// Define a Scanner object that can be used by all methods in the class 
	// to read-in user input
	static Scanner inp = new Scanner(System.in);

	public static void main(String[] args) /*throws IOException */ {	
		
		float convertedAmount;
		int choice;
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

			// Get the choice of the user
			choice = inp.nextInt();

			System.out.println(" ");
			System.out.println("Your choice is: " + choice);
			System.out.println(" ");

			if (choice != 1 && choice != 2 && choice != 3 && choice != 4 ){
				System.out.println("ERROR! You entered an invalid command");
				System.out.println("Please enter a single number 1, 2, 3, or 4");
			}
			// Execute different methods based on the user's choice
			if(choice == 1){
				try {
				System.out.println("Input New Exchange Rate (# of yen for 1 dollar):");
				rate = changeExchangeRate();
				} catch (Exception e){
					System.out.println("ERROR! You probably entered an invalid rate");
					System.out.println("Please input a proper float rate value");
					// try {
					// rate = changeExchangeRate();
					// } catch (Exception e2){
					// 	System.out.println("You entered an invalid value again");
					// 	System.out.println("Returning you to main menu");
					// }
				}
			}

			if(choice == 2){
				try {
					System.out.println("Enter the amount of yen you want to convert:");
					convertedAmount = convertYen();
					System.out.println(" ");
					System.out.println("----------------------");
					System.out.println("The number of yen you entered is equivalent to " + convertedAmount + " usd");	
				} catch (Exception e){
					System.out.println("ERROR! You probably entered an invalid amount");
					System.out.println("Please input a proper float amount value");
					
				}
				
			}

			if(choice == 3){
				try {
					System.out.println("Enter the amount of usd you want to convert:");
					convertedAmount = convertUsd();
					System.out.println(" ");
					System.out.println("----------------------");
					System.out.println("The number of usd you entered is equivalent to " + convertedAmount + " yen");
					} catch (Exception e){
						System.out.println("ERROR! You probably entered an invalid amount");
						System.out.println("Please input a proper float amount value");
					}
				}

			if(choice == 4){
				quit = 1;
			}

		}
		while (quit != 1 );
	}
	

	private static float changeExchangeRate(){
		float newRate = inp.nextFloat();
		return newRate;
	}	

	private static float convertYen(){
		float amount = inp.nextFloat();
		float converted = amount/rate;
		return converted;
	}

	private static float convertUsd(){
		float amount = inp.nextFloat();
		float converted = amount*rate;
		return converted;
	}
}

// -----------------
