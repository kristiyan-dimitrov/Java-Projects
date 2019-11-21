package hw6;

import java.util.ArrayList;

public class HashingMetrics {

	public static void Evaluate(ArrayList<String> outputData, int ArraySize) {
		
		// Will calculate 2 metrics:
		// 1) the number of EMPTY LINES...
		// 2) the ArraySize/2 and the "center of distribution" i.e. the average of the indices weighted by the number of elements at the index
		
		
		// Metric 1
		int numberEmptyLines = 0;
		
		for (int i = 0; i < outputData.size(); i++) {
			if (outputData.get(i) == "EMPTY LINE...") {
				numberEmptyLines++ ;
			}	
		}

		System.out.println("Number of 'EMPTY LINE...': " + numberEmptyLines);
		
		// Metric 2
		int centerDistribution = 0;
		
		for (int i = 0; i < outputData.size(); i++) {
			
			if (outputData.get(i) == "EMPTY LINE...") {
				continue;
			} else {
				centerDistribution += i* (outputData.get(i).split(",").length); // Index * Number of elements
				
			}
		}
		
		System.out.println("The Distance b/w ArraySize/2 and Center of the distribution: " + Math.abs(centerDistribution/ArraySize - ArraySize/2));
		
	}		
}
