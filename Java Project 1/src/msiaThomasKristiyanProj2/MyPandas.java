package msiaThomasKristiyanProj2;

import java.util.*;
import java.io.*;

public class MyPandas {
	
	public static MyDataFrame readCSV(String filepath) throws IOException{
		ArrayList<Object> df_rows = new ArrayList <Object>();
		ArrayList<Object> my_headers = new ArrayList<Object>();
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath));
		
		//first line of the file is headers
		String[] header_input = bufferedReader.readLine().split(",");
		for (int i = 0; i < header_input.length; i++) {
			String head_name = header_input[i];
			my_headers.add(head_name);
		}
		
		/*
		 * Parse through each line of the mybabies.csv and add it to an ArrayList "df_row"
		 * Append "df_row" to an ArrayList "df_rows", where each index of "df_rows" is a row
		 */
		while(bufferedReader.ready()) {
			String[] row_input = bufferedReader.readLine().split(",");
			ArrayList<Object> df_row = new ArrayList<Object>();
			
			for (int i = 0; i<row_input.length; i++){
				if (checkInt(row_input[i])) {
					int age = Integer.parseInt(row_input[i]); 
					df_row.add(age);
				} else {
					String str = row_input[i];
					df_row.add(str);
				}
            }
			df_rows.add(df_row);
		}
		
		bufferedReader.close();
		return MyDataFrame.createDF(df_rows,my_headers);
	}
	
	public static boolean checkInt(String input){
		/*
		 * A function that checks whether a specific row input is an int
		 * Let's use ParseInt so we can calculate directly from the dataframe without casting
		 */
	    try{
	        Integer.valueOf(input);
	        return true;
	    }catch(NumberFormatException e){
	        return false;
	    }
	}
	
	public static MyDataFrame concat(MyDataFrame df1, MyDataFrame df2) {
		/*
		 * A Function that adds the rows of one data frame to the other data frame
		 * Returns the original dataframe with rows added
		 */
		ArrayList<Object> df1_rows = df1.rows;
		for (Object df2_row : df2.rows) {
			df1_rows.add(df2_row);
		}
		
		return MyDataFrame.createDF(df1_rows, df1.headers);
	}
	
	public static void writeCSV(MyDataFrame dataframe, String filepath) throws IOException{
		/*
		 * A function that turns the dataframe header & rows into a string and writes it into the filepath
		 */
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath));
		
		//write header
		String writeHeaders = "";
		for (Object df_header : (ArrayList<Object>) dataframe.headers) {
			writeHeaders = writeHeaders + df_header + ",";	
		}
		bufferedWriter.write(writeHeaders);
		bufferedWriter.newLine();
		
		//write rows
		for (Object df_row : (ArrayList<Object>) dataframe.rows) {
			String writeRows = "";
			for (Object element : (ArrayList<Object>) df_row) {
				writeRows = writeRows + element + ",";
			}
			bufferedWriter.write(writeRows);
			bufferedWriter.newLine();
		}
		bufferedWriter.close();
	}

}
