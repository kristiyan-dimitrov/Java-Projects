package hw6;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveToFile {
	
	public static void Write (String pathnameOutput, ArrayList<String> outputData) {
		
		// Writing the names & salaries to .txt file
		try {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathnameOutput));
			for (String line : outputData) {
				bufferedWriter.write(line + "\n" + "\n");
			}
			bufferedWriter.close();
			
		} catch (IOException ex) {
			System.out.println("Failed to write to output file!");
		}

	}
		
}
