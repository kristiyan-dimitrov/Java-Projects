package hw6;

import java.util.ArrayList;
import java.util.Collections;

public class Rescale {

	public static ArrayList<Integer> RescaleData(ArrayList<Double> arr, int ArraySize){
		
		ArrayList<Integer> arrScaled = new ArrayList<Integer>();
		Double arrMin = Collections.min(arr);
		for (Double el : arr) {
			int elScaled = (int) Math.round((el - arrMin)%ArraySize);
			arrScaled.add(elScaled);
		}
		
		return arrScaled;
	}

	public static ArrayList<Integer> RescaleASCII(ArrayList<Integer> arr, int ArraySize){
		
		ArrayList<Integer> arrScaled = new ArrayList<Integer>();
		Integer arrMin = Collections.min(arr);
		for (Integer el : arr) {
			int elScaled = (int) Math.round((el - arrMin)%ArraySize);
			arrScaled.add(elScaled);
		}
		
		return arrScaled;
	}
	
	
}
