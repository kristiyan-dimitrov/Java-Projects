package hw6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Input {

	public static ArrayList<String> LoadInput(){
		String pathname = "input.txt";
		File file = new File(pathname);
		ArrayList<String> arr = new ArrayList<String>();

		try (Scanner scanner = new Scanner(file)) { // Try to load from input.txt
			System.out.println("Detected input.txt file --> Loaded into this application");
			
			while (scanner.hasNextLine()) {	
			
				String line = scanner.nextLine(); // Take the next line from the input.txt file
				arr.add(line);
			}
			
		} catch (FileNotFoundException ex) {
			 // If file doesn't exist, then create an empty appointments ArrayList
			System.out.println("You don't have input.txt in the folder with the source code!");
			
		}
			
		return arr;
	}
	
}
