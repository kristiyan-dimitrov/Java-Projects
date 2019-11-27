package msiaThomasKristiyanProj2;

import java.util.*;

public class MyDataFrame {
	/*
	 * Comment to be filled in later this is a TODO so we remember!!!!
	 */
	ArrayList<Object> rows = new ArrayList<>();
	ArrayList<Object> headers = new ArrayList<>();
	ArrayList<Object> columns = new ArrayList<>();
	
	//constructor for the MyDataFrame object
	//Note: in MyPandas, we read in as rows, but I think it could be good to fill in a dataframe as columns too
	//Would be easier for some of the functions
	public MyDataFrame(ArrayList<Object> rows, ArrayList<Object> headers, ArrayList<Object> columns) {
		this.rows = rows;
		this.headers = headers;
		this.columns = columns;
	}
	
	public static MyDataFrame createDF(ArrayList<Object> rows, ArrayList<Object> headers) {
		/*
		 * A function to create a dataframe objects.
		 * Given some of the methods that need to be implemented work row-wise & others are column-wise,
		 * I think it would be good to fill in both columns & headers... can remove if it's too complicated?
		 */
		ArrayList<Object> df_rows = rows;
		ArrayList<Object> df_headers = headers;
		
		ArrayList<Object> df_columns = new ArrayList<>();
		//create array lists for each of the headers, which will be the columns
		for (int i = 0; i < df_headers.size(); i++){
			ArrayList<Object> column_i = new ArrayList<Object>();
			df_columns.add(column_i);	
		}
		
		//Iterate through each row and add the elements to their respect columns
		for (Object row:df_rows) {
			int i = 0;
			for(Object element : (ArrayList<Object>) row) {
				ArrayList<Object> entry = (ArrayList<Object>) df_columns.get(i);
				entry.add(element);
				df_columns.set(i, entry);
				i++;
			}
		}		
		return new MyDataFrame(df_rows,df_headers,df_columns);
	}
	
	public MyDataFrame head(int n) {
		/*
		 * Returns the first n ROWS of data
		 */
		ArrayList<Object> head = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			head.add(rows.get(i));
		}
		return createDF(head,headers);
	}
	
	public MyDataFrame tail(int n) {
		/*
		 * Returns the last n ROWS of data
		 */
		ArrayList<Object> tail = new ArrayList<>();
		int startIdx = rows.size() - n;
		int endIdx = rows.size();
		
		for (int i = startIdx; i < endIdx; i++) {
			tail.add(rows.get(i));
		}
		return createDF(tail,headers);
	}
	
	public String dType(int index) {
		/*
		 * Returns the type of the elements in the column specified by column index
		 */
		
		
		// Getting the column specified by the index
		ArrayList<Object> column = (ArrayList<Object>) columns.get(index);

		// Go through all elements of the column and compare the type of the current element with the type of the previous element
		// Will return "String" if the types are not uniform
		for (int i = 1; i < column.size(); i++) {
			
			if (column.get(i).getClass() != column.get(i-1).getClass()) {
				return "String";
			} 
		}
		
		return column.get(0).getClass().getSimpleName();
	
	}

	public String dType(String name) {
		/*
		 * Returns the type of the elements in the column specified by column name
		 */
		
		int nameIndex;
		
		// Go through headers and look for columnName
		for (int i = 0; i < headers.size(); i++) {
			if(headers.get(i).equals(name)) {
				nameIndex = i;
				return dType(nameIndex);
			}
		}
		
		return "No such column name found";
		
	}	
	
	public MyDataFrame slice(int index) {
		/*
		 * Returns the column with the specified column index
		 */
		
		// Get header at specified index
		ArrayList<Object> header = new ArrayList<Object>();
		header.add(headers.get(index));
		
		// Getting the column specified by the index
		ArrayList<Object> column = (ArrayList<Object>) columns.get(index);
		
		// Initializing rows for new dataframe
		ArrayList<Object> df_rows = new ArrayList<Object>();
		
		// Filling up rows with corresponding element from column
		for (int i = 0; i < column.size(); i++) {
			ArrayList<Object> row = new ArrayList<Object>();
			row.add(column.get(i));
			df_rows.add(row);
		}
		
		return MyDataFrame.createDF(df_rows, header);
	}
	
	public MyDataFrame slice(String name) {
		/*
		 * Returns the column with the specified column name
		 */	
		
		int nameIndex;

		// Go through headers and look for columnName
		for (int i = 0; i < headers.size(); i++) {
			if(headers.get(i).equals(name)) {
				nameIndex = i;
				return slice(nameIndex);
			}
		}
	
		return MyDataFrame.createDF(rows, headers);
	}
//
	public MyDataFrame slice(int[] indexArr) {
		/*
		 * Returns DataFrame with all the columns at the specified indices
		 * i.e. filtering my columns indices
		 */	
		
		// Initializing header & rows for new dataframe
		ArrayList<Object> header = new ArrayList<Object>();
		ArrayList<Object> df_rows = new ArrayList<Object>();
		
		for (int index : indexArr) {
			// Get header items at specified indices
			header.add(headers.get(index));
		}
		
		// Retrieve one column so we can use it's size/length
		ArrayList<Object> column = (ArrayList<Object>) columns.get(indexArr[0]);
		
		// Go through all the rows in the DataFrame
		for (int i = 0; i < column.size(); i++) {
			
			// For each row, retrieve all the elements at the corresponding indices of the corresponding row
			ArrayList<Object> row = new ArrayList<Object>();
			
			for (int index2 : indexArr) {
				ArrayList<Object> sub_row = (ArrayList<Object>) rows.get(i);
				row.add(sub_row.get(index2));
			}
			
		df_rows.add(row);

		}
		
		return MyDataFrame.createDF(df_rows, header);
	}
	
	public MyDataFrame slice(String[] nameArr) {
		
		int[] indexArr = new int[nameArr.length];
//		System.out.println(indexArr);
		// Go through all names in nameArr
		for (int i = 0; i < nameArr.length; i++) {
//			System.out.println(i);
			// For each name in nameArr, check if it equals any of the headers
			for (int j = 0; j < headers.size(); j++) {
				if(headers.get(j).equals(nameArr[i])) {
					indexArr[i] = j;
				}
			}
			
//			System.out.println(i);
//			System.out.println(headers.get(i));
//			System.out.println(nameArr[i]);

		}
		return slice(indexArr);
		
	}
	
	public MyDataFrame createDFfromColumns(ArrayList<Object> columns, ArrayList<Object> headers) {
		/*
		 * Create a dataframe from the Columns instead of the rows
		 * Note: I didn't know how to Slice because it's column-wise, so I am creating this method lol
		 */
		ArrayList<Object> df_columns = columns;
		ArrayList<Object> df_headers = headers;
		ArrayList<Object> temporarycounter = (ArrayList<Object>) columns.get(0);
		
		//initialize the rows
		ArrayList<Object> df_rows = new ArrayList<>();
		for (int i = 0; i < temporarycounter.size(); i++) {
			ArrayList<Object> row = new ArrayList<Object>();
			df_rows.add(row);
		}
		
		//fill in the rows
		for (int i = 0; i < df_headers.size(); i++) {
			Object column = df_columns.get(i);
			int j = 0;
			for (Object element : (ArrayList<Object>) column) {
				ArrayList<Object> entry = (ArrayList<Object>) df_rows.get(j);
				entry.add(element);
				df_rows.set(j, entry);
				j++;
			}
		}
		return new MyDataFrame(df_rows,df_headers,df_columns);
	}
	
	public MyDataFrame loc(int index) {
		/*
		 * A function that returns the row referenced by the index
		 */
		ArrayList<Object> loc = new ArrayList<>();
		loc.add(rows.get(index));
		return createDF(loc,headers);
	}
	
	public MyDataFrame loc(String label) {
		/*
		 * A function that returns the row referenced by a string label
		 * All our indices in our dataframe are numbers, so this turns it into a and search.
		 */
		int index = Integer.valueOf(label);
		
		ArrayList<Object> loc = new ArrayList<>();
		loc.add(rows.get(index));
		return createDF(loc,headers);
	}
	
	public MyDataFrame loc(int from, int to) {
		/*
		 * A function that returns the rows referenced by a range of numerical indices inclusive!
		 * e.g. (0,3) should return 4 rows ==> indices 0,1,2,3
		 */
		ArrayList<Object> loc = new ArrayList<>();
		for (int idx = from; idx <= to; idx++) {
			loc.add(rows.get(idx));
		}
		return createDF(loc,headers);
	}
	
	public MyDataFrame loc(String from, String to) {
		/*
		 * A function that returns the rows referenced by a range of String indices inclusive!
		 * e.g. ("0","3") should return 4 rows ==> indices 0,1,2,3
		 */
		ArrayList<Object> loc = new ArrayList<>();
		for (int idx = Integer.valueOf(from); idx <= Integer.valueOf(to); idx++) {
			loc.add(rows.get(idx));
		}
		return createDF(loc,headers);
	}
	
	
	public MyDataFrame filter(String col, String op, Object o) {
		/*
		 * A function that filters a column and returns a dataframe of all rows associated with the filter
		 * TODO: Implemented = because it is easy; need to continue 
		 */
		ArrayList<Object> filteredCol = new ArrayList<>();
		ArrayList<Integer> indices = new ArrayList<Integer>(); //indices related to filtered columns
		int headerIdx = headers.indexOf(col);
		Boolean checkint = o instanceof Integer;
		Boolean checkstr = o instanceof String;
		
		ArrayList<Object> column = (ArrayList<Object>) columns.get(headerIdx);
		
		//check what the operator is; add the indices to the indices ArrayList if they match
		if (op.equals("=")) {
			for (int i = 0; i < column.size(); i++) {
				Object item = column.get(i);
				if (item.equals(o)) {
					indices.add(i);
				}
			}
		}
		//greater than operator
		else if (op.equals(">")) {
			if (checkint) {
				int obj = (Integer) o;
				for (int i = 0; i < column.size(); i++) {
					Integer item = (Integer) column.get(i);
					if (item > obj) {
						indices.add(i);
					}
				}
			}
			else if (checkstr) {
				String obj = (String) o;
				for (int i = 0; i < column.size(); i++) {
					String item = (String) column.get(i);
					if (item.compareTo(obj) > 0) {
						indices.add(i);
					}
				}
			}
		}
		//greater than or equal to operator
		else if (op.equals(">=")) {
			if (checkint) {
				int obj = (Integer) o;
				for (int i = 0; i < column.size(); i++) {
					Integer item = (Integer) column.get(i);
					if (item >= obj) {
						indices.add(i);
					}
				}
			}
			else if (checkstr) {
				String obj = (String) o;
				for (int i = 0; i < column.size(); i++) {
					String item = (String) column.get(i);
					if (item.compareTo(obj) >= 0) {
						indices.add(i);
					}
				}
			}
		}
		//less than operator
		else if (op.equals("<")) {
			if (checkint) {
				int obj = (Integer) o;
				for (int i = 0; i < column.size(); i++) {
					Integer item = (Integer) column.get(i);
					if (item < obj) {
						indices.add(i);
					}
				}
			}
			else if (checkstr) {
				String obj = (String) o;
				for (int i = 0; i < column.size(); i++) {
					String item = (String) column.get(i);
					if (item.compareTo(obj) < 0) {
						indices.add(i);
					}
				}
			}
		}
		//less than or equal to operator
		else if (op.equals("<=")) {
			if (checkint) {
				int obj = (Integer) o;
				for (int i = 0; i < column.size(); i++) {
					Integer item = (Integer) column.get(i);
					if (item < obj) {
						indices.add(i);
					}
				}
			}
			else if (checkstr) {
				String obj = (String) o;
				for (int i = 0; i < column.size(); i++) {
					String item = (String) column.get(i);
					if (item.compareTo(obj) <= 0) {
						indices.add(i);
					}
				}
			}
		}
		//bad operator
		else {
			System.out.println("You did not choose an operator");
		}
		//Create the new dataframe from the indices Array List
		//For each dataframe column, iterate through and grab the values indicated by indices ArrayList
		for (Object cl : columns) {
			ArrayList<Object> final_columns = new ArrayList<Object>();
			ArrayList<Object> current_columns = (ArrayList<Object>) cl;
			
			for (int index:indices) {
				final_columns.add(current_columns.get(index));
			}
			filteredCol.add(final_columns);
		}
		
		return createDFfromColumns(filteredCol,headers);
	}
	
	public MyDataFrame sort(int index) {
		
		int swaps;
		
		do {
			swaps = 0;
			for (int i = 1; i < rows.size(); i++) {
				ArrayList<Object> this_row =  (ArrayList<Object>) rows.get(i);
				ArrayList<Object> previous_row = (ArrayList<Object>) rows.get(i-1);
				if (this_row.get(index) instanceof String) {
					if( ((String) this_row.get(index)).compareTo((String) previous_row.get(index)) < 0){
						Collections.swap(rows, i, i-1);
						swaps++;
					}
				}
					
				if (this_row.get(index) instanceof Integer) {
					if( ((Integer) this_row.get(index)).compareTo((Integer) previous_row.get(index)) < 0){
						Collections.swap(rows, i, i-1);
						swaps++;
					}
				}
					
			}			
			
		} while (swaps != 0);
	
		return createDF(rows, headers);		
	}
	
	public MyDataFrame sort(String name) {
		
		int nameIndex;

		// Go through headers and look for columnName
		for (int i = 0; i < headers.size(); i++) {
			if(headers.get(i).equals(name)) {
				nameIndex = i;
				return sort(nameIndex);
			}
		}
	
		return MyDataFrame.createDF(rows, headers);
	}
	
	public Object getMin(int index) {
		/*
		 * a function to get the minimum value of a column based on an index
		 */
		String datatype = new MyDataFrame(rows,headers,columns).dType(index);
		if (datatype.contentEquals("Integer")) {
			ArrayList<Integer> mycol = (ArrayList<Integer>) columns.get(index);
			
			//traverse through the column of ints to get the minimum value
			int min = mycol.get(0);
			for (int num:mycol) {
				if (min > num) {
					min = num;
				}
			}
			return min;
		}
		else {
			ArrayList<String> mycol = (ArrayList<String>) columns.get(index);
			
			//iterate through the column of str to get min value
			String min = mycol.get(0);
			for (String str:mycol) {
				if (min.compareTo(str) > 0) {
					min = str;
				}
			}
			return min;
		}
	}
	
	public Object getMin(String label) {
		/*
		 * a function to get the minimum value of a column based on a header label
		 */
		String datatype = new MyDataFrame(rows,headers,columns).dType(label);
		int index = headers.indexOf(label);
		
		if (datatype.contentEquals("Integer")) {
			ArrayList<Integer> mycol = (ArrayList<Integer>) columns.get(index);
			
			//traverse through the column of ints to get the minimum value
			int min = mycol.get(0);
			for (int num:mycol) {
				if (min > num) {
					min = num;
				}
			}
			return min;
		}
		else {
			ArrayList<String> mycol = (ArrayList<String>) columns.get(index);
			
			//iterate through the column of str to get min value
			String min = mycol.get(0);
			for (String str:mycol) {
				if (min.compareTo(str) > 0) {
					min = str;
				}
			}
			return min;
		}
	}
	
	public Object getMax(int index) {
		/*
		 * a function to get the maximum value of a column based on an index
		 */
		String datatype = new MyDataFrame(rows,headers,columns).dType(index);
		if (datatype.contentEquals("Integer")) {
			ArrayList<Integer> mycol = (ArrayList<Integer>) columns.get(index);
			
			//traverse through the column of ints to get the maximum value
			int max = mycol.get(0);
			for (int num:mycol) {
				if (max < num) {
					max = num;
				}
			}
			return max;
		}
		else {
			ArrayList<String> mycol = (ArrayList<String>) columns.get(index);
			
			//iterate through the column of str to get max value
			String max = mycol.get(0);
			for (String str:mycol) {
				if (max.compareTo(str) < 0) {
					max = str;
				}
			}
			return max;
		}
	}
	
	public Object getMax(String label) {
		/*
		 * a function to get the minimum value of a column based on a header label
		 */
		String datatype = new MyDataFrame(rows,headers,columns).dType(label);
		int index = headers.indexOf(label);
		
		if (datatype.contentEquals("Integer")) {
			ArrayList<Integer> mycol = (ArrayList<Integer>) columns.get(index);
			
			//traverse through the column of ints to get the maximum value
			int max = mycol.get(0);
			for (int num:mycol) {
				if (max < num) {
					max = num;
				}
			}
			return max;
		}
		else {
			ArrayList<String> mycol = (ArrayList<String>) columns.get(index);
			
			//iterate through the column of str to get max value
			String max = mycol.get(0);
			for (String str:mycol) {
				if (max.compareTo(str) < 0) {
					max = str;
				}
			}
			return max;
		}
	}
	
}






// GRAVEYARD
// Getting the first element of that column
//Object firstElement = column.get(0).getClass();
