package problem3;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList; // Need the below to create an ArrayList
import java.time.LocalDate; // Need this to get today's date

public class dataAnalysis {

	static ArrayList<String> names = new ArrayList<String>();
	static ArrayList<String> dob = new ArrayList<>();
	static ArrayList<String> salaries = new ArrayList<>();

	public static void main(String[] args) throws IOException{
	
		String pathname = "employees.txt";
		File file = new File(pathname);

		// Read in data into 3 String ArrayLists
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] el = line.split(","); // Then split.

				names.add(el[0]);
				dob.add(el[1]);
				salaries.add(el[2]);
				
			}
		}

		// Question 1 - How many Employees exist in the file?
		System.out.println("----------------------");
		System.out.println("Question 1 - How many Employees exist in the file?");
		System.out.println(names.size() + "\n");

		// Question 2 - Who has the highest salary?
		System.out.println("----------------------");
		System.out.println("Question 2 - Who has the highest salary?");
		// Retrieving the name at the index where the highest salary is located 
		System.out.println(names.get(indexHighest(salaries)) + "\n"); // See below for definition of indexHighest()

		// Question 3 - What is the average salary?
		float avgSalary = averageSalary(salaries);
		System.out.println("----------------------");
		System.out.println("Question 3 - What is the average salary?");
		System.out.println(avgSalary + "\n");

		// Question 4 - How many employees make above the average?
		System.out.println("----------------------");
		System.out.println("Question 4 - How many employees make above the average?");
		System.out.println(overAverageSalary(salaries,avgSalary) + "\n");

		//Question 5 - What is the average age of employees?
		// First, I create an empty arrayList to store the ages
		ArrayList<Integer> ages = new ArrayList<Integer>();

		// Then, take each Date of Birth, convert to Age and add to ArrayList of ages
		for (int i = 0; i < dob.size(); i++){
			ages.add(convertDOBtoAge(dob.get(i))); // See below for convertDOBtoAge() definition
		}

		System.out.println("----------------------");
		System.out.println("Question 5 - What is the average age of employees?");
		System.out.println(averageAge(ages) + "\n"); // See below for averageAge() definition

		// FINAL TASK - Writing Output file the employee names sorted according to their salary
		// First, we need to put all the employees into an array and sort it
		
		// Defining ArrayList of Employee objects
		ArrayList<Employee> employees = new ArrayList<Employee>();
		
		// Adding all the employees to the above list
		for (int i = 0; i < names.size(); i++) {
			Employee next_employee = new Employee(names.get(i),Float.parseFloat(salaries.get(i)));
			employees.add(next_employee);
		}
		
		// Checking the current, unsorted list of employees
		for (int i = 0; i < employees.size(); i++) {
			System.out.println(employees.get(i));
		}
		System.out.println(" ");
		// Sorting our list of Employee objects
		employees.sort(null);
		
		// Verifying our sort worked
		for (int i = 0; i < employees.size(); i++) {
			System.out.println(employees.get(i));
		}
		
		// Now that the employees are sorted, we need to write them all to a file
		String pathnameOutput = "employeesSorted.csv";
		String header = "NAME,SALARY";

		// Creating a String array with the Names & Salaries of the employees
		String[] writeLines = new String[employees.size()];
		for (int i = 0; i < writeLines.length; i++) {
			writeLines[i] = employees.get(i).getName() + "," + employees.get(i).getSalary();
		}
		
		// Writing the names & salaries to .csv file
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathnameOutput));
		bufferedWriter.write(header+"\n");
		for (String line : writeLines) {
			bufferedWriter.write(line+"\n");
		}
		bufferedWriter.close();
	}
	
// A few supporting private methods
	// Function for Question 2 - finding index of element with highest value	
	private static int indexHighest(ArrayList<String> lst){
		float max = Float.parseFloat(lst.get(0));
		int index = 0; 

		for (int i = 1; i < lst.size(); i++){
			if (Float.parseFloat(lst.get(i)) > max){
				max = Float.parseFloat(lst.get(i));
				index = i;
			}
		}
		return index;
	}

	// Function for Question 3 - calculating averageSalary
	private static float averageSalary(ArrayList<String> salaries){
		float sum = 0;

		for (int i = 0; i < salaries.size(); i++){
			sum = sum + Float.parseFloat(salaries.get(i));
		}
		return sum / salaries.size();
	}

	// Function for Question 4 - Number of Employees making above avg. Salary
	private static int overAverageSalary(ArrayList<String> salaries, float avgSalary){
		
		int number = 0;

		for (int i = 0; i < salaries.size(); i++){
			if (Float.parseFloat(salaries.get(i)) > avgSalary){
				number++;
			}
		}
		return number;
	}

	// Function for Question 5
	private static int convertDOBtoAge(String dob){
		
		int age;
		// Convert dob to age
		int year_dob = Integer.parseInt(dob.split("/")[2]);
		LocalDate today = LocalDate.now(); // Today's date
		int year_today = today.getYear();
		
		
		// LocalDate birthday = LocalDate.of(year);
		// Period p = Period.between(birthday, today); // Finding the period between dob & today

		age = year_today - year_dob; // Extracting the # of years from the period as the age of the person
		return age;
	}

	// Function for Question 5 - calculating averageAge
	private static float averageAge(ArrayList<Integer> ages){
		float sum = 0;

		for (int i = 0; i < ages.size(); i++){
			sum = sum + ages.get(i);
		}
		return sum / ages.size();
	}

}
