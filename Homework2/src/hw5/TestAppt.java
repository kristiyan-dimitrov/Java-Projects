package hw5;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.time.*;

// CONTENTS
// 1) main class - displays menu to user and calls all other classes
// 2) LoadFromFile class - loads data from appointments.txt if present
// 3) SaveToFile class - saves all created appointments to appointments.txt
// 4) CreateAppointment class - called during main class to create appointments
// 5) printAllAppointmentsOnDate class - called to display all appointments on a specified date

public class TestAppt {

	public static void main(String[] args) throws ParseException {
		
		// Creating an ArrayList to hold all our Appointment objects
		ArrayList<Appointment> appointments = LoadFromFile();
		
		Scanner inp = new Scanner(System.in); // Creating a scanner to read in user input
		int choice = 0;
		int quit = 0;
		
		do 
		{
			// Display menu with options to the user
			System.out.println(" ");
			System.out.println("What would you like to do? Enter 1, 2, 3, or 4:");
			System.out.println("1. Create an appointment");
			System.out.println("2. See all appointments on a given date");
			System.out.println("3. Save all entered appointments");
			System.out.println("4. Quit application (Remember to use option 3 before quitting!");
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
			}

			// Execute different methods based on the user's choice
			// Option 1 refers to Adding an Appointment
			if(choice == 1){
				Appointment newAppointment = CreateAppointment();
				appointments.add(newAppointment);
			}
			// Option 2 refers to printing all appointments on a given Date
			if(choice == 2){
				printAllAppointmentsOnDate(appointments);
				System.out.println(" ");
				System.out.println("----------------------");	
			}
			
			// Option 3 refers to Saving all your created appointments to appointments.txt
			if(choice == 3){
				System.out.println("Your newly created appointments have been saved and added to appointments.txt");
				SaveToFile(appointments);
				System.out.println(" ");
				System.out.println("----------------------");
			}

			if(choice == 4){
				System.out.println("Goodbye!");
				quit = 1;
			}

		}
		while (quit != 1 );	
			
	}
	
	
	// Supporting function to read in appointments file when application starts up
	public static ArrayList<Appointment> LoadFromFile() throws ParseException{
		
		String pathname = "appointments.txt";
		File file = new File(pathname);
		ArrayList<Appointment> arrLst = new ArrayList<Appointment>();

		try (Scanner scanner = new Scanner(file)) { // Try to load from file if it exists i.e. if there are previously saved appointments
			System.out.println("Detected appointments.txt file --> Loaded all appointments from it in the application");
			while (scanner.hasNextLine()) {
				
			String line = scanner.nextLine(); // Take the next line from the .txt file
			String[] el = line.split(","); // Split the line by commas
			
			// Now I need to read in the strings and convert them to Appointments
			if (el[0].equals("Onetime")) {
				Appointment appt = new Onetime(el[1],el[2]); // Create new Onetime appt, taking the description (string after type), and date (next element after description
				arrLst.add(appt);
			}
			
			if (el[0].equals("Daily")) {
				Appointment appt = new Daily(el[1],el[2],el[3]); //el[1] is description, el[2] is startDate, el[3] is endDate 
				arrLst.add(appt);
			}
			
			if (el[0].equals("Monthly")) {
				Appointment appt = new Monthly(el[1], el[2], el[3], el[4]); // 1 - description; 2 - day; 3 - startMMyyyy 4 - endMMyyyy
				arrLst.add(appt);
			}
			// If el[0] is of type Daily then initialize an appointment as new Daily
					
			} 
			
		} catch (FileNotFoundException ex) {
			 // If file doesn't exist, then create an empty appointments ArrayList
			System.out.println("No previous appointments detected from appointments.txt file.");
			System.out.println("Initializing empty appointments ArrayList");
			ArrayList<Appointment> arrLst2 = new ArrayList<Appointment>();
			return arrLst2;
		}
		
		return arrLst;
	}
	
	
	// Supporting function to write to a .txt file in the proper format required by the LoadFromFile function and all the subclass constructors
	private static void SaveToFile(ArrayList<Appointment> appointments) {
	
		String pathnameOutput = "appointments.txt";

		// Creating a String array with the Names & Salaries of the employees
		String[] writeLines = new String[appointments.size()];
		for (int i = 0; i < writeLines.length; i++) {
			
			// Transforming Type, Description, and Date to proper format and writing to file
			if (appointments.get(i).getType().equals("Onetime")) {
				Date date_tmp = appointments.get(i).getDate(); // Take current Date object
				LocalDate localDate = date_tmp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // Convert it to localDate, because Date is deprecated
				String string_tmp = localDate.getMonthValue() + "/" + localDate.getDayOfMonth() + "/" + localDate.getYear(); // Extract relevant fields for the string format needed for the .txt file
				
				writeLines[i] = appointments.get(i).getType() + "," + appointments.get(i).getDescription()+ "," + string_tmp;
			}
			
			// Transforming Type, Description, startDate, and endDate to proper format and writing to file
			if (appointments.get(i).getType().equals("Daily")) {
				Date dateStart_tmp = appointments.get(i).getStartDate(); // Take current startDate object
				Date dateEnd_tmp = appointments.get(i).getEndDate(); // Take current endDate object
				LocalDate localDateStart = dateStart_tmp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // Convert it to localDate, because Date is deprecated
				LocalDate localDateEnd = dateEnd_tmp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // Convert it to localDate, because Date is deprecated
				String stringStart_tmp = localDateStart.getMonthValue() + "/" + localDateStart.getDayOfMonth() + "/" + localDateStart.getYear(); // Extract relevant fields for the string format needed for the .txt file
				String stringEnd_tmp = localDateEnd.getMonthValue() + "/" + localDateEnd.getDayOfMonth() + "/" + localDateEnd.getYear(); // Extract relevant fields for the string format needed for the .txt file
				
				writeLines[i] = appointments.get(i).getType() + "," + appointments.get(i).getDescription() + "," + stringStart_tmp + "," + stringEnd_tmp;
			}
			
			// Transforming Type, Description, Day, startDate, and endDate to proper format and writing to file
			if (appointments.get(i).getType().equals("Monthly")) {
				Date dateStart_tmp = appointments.get(i).getStartDate(); // Take current startDate object
				Date dateEnd_tmp = appointments.get(i).getEndDate(); // Take current endDate object
				LocalDate localDateStart = dateStart_tmp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // Convert it to localDate, because Date is deprecated
				LocalDate localDateEnd = dateEnd_tmp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // Convert it to localDate, because Date is deprecated
				String stringStart_tmp = localDateStart.getMonthValue() + "/" + localDateStart.getDayOfMonth() + "/" + localDateStart.getYear(); // Extract relevant fields for the string format needed for the .txt file
				String stringEnd_tmp = localDateEnd.getMonthValue() + "/" + localDateEnd.getDayOfMonth() + "/" + localDateEnd.getYear(); // Extract relevant fields for the string format needed for the .txt file
				
				
				writeLines[i] = appointments.get(i).getType() + "," + appointments.get(i).getDescription() + "," + appointments.get(i).getDay() + "," + stringStart_tmp + "," + stringEnd_tmp;
			}
			
		}
		
		// Writing the names & salaries to .txt file
		try {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathnameOutput));
			for (String line : writeLines) {
				bufferedWriter.write(line+"\n");
			}
			bufferedWriter.close();
			
		} catch (IOException ex) {
			System.out.println("Failed to save appointments to file!");
		} catch (NullPointerException exe) {
			System.out.println("Nothing saved, because no appointments have been created this session");
		}
		
		
	
	}
	
	// Helper function for setting up an appointment
	public static Appointment CreateAppointment() {
		
		Appointment newAppointment = null;
		int back = 0;
		int choice = 4;
		Scanner inp = new Scanner(System.in); // Creating a scanner to read in user input
		
		// Get the user to input the type of appointment they want to make
		do {
			System.out.println("What kind of appointment would you like to make?");
			System.out.println("1. Onetime");
			System.out.println("2. Daily");
			System.out.println("3. Monthly");
			System.out.println("4. Go back to Main Menu");
			
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
			}
			
			// Execute different methods based on the user's choice
			
			// Create a Onetime appointment
			if(choice == 1){
				inp.nextLine();
				// Get description input from user
				System.out.println("Please enter a description for your appointment below:");
				String description = inp.nextLine();
				System.out.println("Please enter a date for your appointment below");
				System.out.println("You must use format MM/dd/yyyy");
				String date = "";
				try {
					date = inp.next("../../...."); // Get date from user
				} catch (InputMismatchException ex) {
					
					System.out.println("Looks like you didn't enter the date in the correct format");
					System.out.println("You must use format MM/dd/yyyy");
				}
				
				newAppointment = new Onetime(description, date);
				return newAppointment;
			}
			
			// Create a Daily appointment
			if(choice == 2){
				inp.nextLine();
				// Getting Description from user
				System.out.println("Please enter a description for your appointment below:");
				String description = inp.nextLine();
				
				// Getting Start Date from user
				System.out.println("Please enter a Start Date for your Daily appointment below");
				System.out.println("You must use format MM/dd/yyyy");
				String startDate = "";
				try {
					startDate = inp.next("../../...."); // Get date from user
				} catch (InputMismatchException ex) {
					System.out.println("Looks like you didn't enter the date in the correct format");
					System.out.println("You must use format MM/dd/yyyy");
				}
				// Getting End Date from User
				System.out.println("Please enter a End Date for your Daily appointment below");
				System.out.println("You must use format MM/dd/yyyy");
				String endDate = "";
				try {
					endDate = inp.next("../../...."); // Get date from user
				} catch (InputMismatchException ex) {
					
					System.out.println("Looks like you didn't enter the date in the correct format");
					System.out.println("You must use format MM/dd/yyyy");
				}
				newAppointment = new Daily(description, startDate, endDate);
				return newAppointment;	
			}
			// Create a Monthly appointment
			if(choice == 3){
				inp.nextLine();
				// Getting Description from user
				System.out.println("Please enter a description for your appointment below:");
				String description = inp.nextLine();
				
				// Getting Day of the month from user
				System.out.println("Please enter a Day of the month for your Monthly appointment below");
				System.out.println("You must use format dd");
				String day = "";
				try {
					day = inp.next(".."); // Get date from user
				} catch (InputMismatchException ex) {
					System.out.println("Looks like you didn't enter the date in the correct format");
					System.out.println("You must use format dd");
				}
				
				// Getting Start MM/yyyy from user
				System.out.println("Please enter a Start Date for your Monthly appointment below");
				System.out.println("You must use format MM/yyyy");
				String startMMyyyy = "";
				try {
					startMMyyyy = inp.next("../...."); // Get date from user
				} catch (InputMismatchException ex) {
					System.out.println("Looks like you didn't enter the date in the correct format");
					System.out.println("You must use format MM/yyyy");
				}
				
				// Getting End MM/yyyy from User
				System.out.println("Please enter a End Date for your Monthly appointment below");
				System.out.println("You must use format MM/yyyy");
				String endMMyyyy = "";
				try {
					endMMyyyy = inp.next("../...."); // Get date from user
				} catch (InputMismatchException ex) {
					
					System.out.println("Looks like you didn't enter the date in the correct format");
					System.out.println("You must use format MM/yyyy");
				}
				newAppointment = new Monthly(description, day, startMMyyyy, endMMyyyy);
				return newAppointment;	
			}

			if(choice == 4){
				back = 1;
			}

		}  while(back != 1);
			
		return newAppointment;
				
	}
	
	// This function implements option 2 from the menu
	public static void printAllAppointmentsOnDate(ArrayList<Appointment> appointments) {
		
		Scanner inp = new Scanner(System.in); // Creating a scanner to read in user input
		
		System.out.println("Please enter a date you'd like to see the appointments for:");
		System.out.println("You must use format MM/dd/yyyy");
		String date = "";
		try {
			date = inp.next("../../...."); // Get date from user
		} catch (InputMismatchException ex) {
			System.out.println("Looks like you didn't enter the date in the correct format");
			System.out.println("You must use format MM/dd/yyyy");
			return;
		}
		
		System.out.println("Type  -  Description");
		System.out.println("--------------------");
		
		for (int i = 0; i < appointments.size(); i++) {
			
			// Checking if appointment occurs (parsing the MM/dd/yyyy input as integers for the occursOn method
			if (appointments.get(i).occursOn(Integer.parseInt(date.substring(date.length()-4)), Integer.parseInt(date.substring(0, 2)), Integer.parseInt(date.substring(3,5)))) {
				System.out.println(appointments.get(i));
			}
		}
	}
	
	
}
