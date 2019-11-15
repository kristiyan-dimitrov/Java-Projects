package hw5;

import java.text.ParseException;
import java.util.Date;

public class Daily extends Appointment {

	// The two variables that uniquely define a Daily appointment
	Date startDate;
	Date endDate;
	
	
	
	// CONSTRUCTOR
	public Daily(String description, String startDate, String endDate){
		// Setting description as per superclass constructor
		super(description);
		
		// Setting the type as per the superclass.
		this.type = "Daily";
		
		// Setting startDate from provided string
		try {
			this.startDate = format.parse(startDate);
		} catch(ParseException ex) {
			System.out.println("You entered an invalid date format.");
			System.out.println("Please, enter a valid date in the MM/dd/yyyy format.");
		}
		
		// Setting endDate from provided string 
		try {
			this.endDate = format.parse(endDate);
		} catch(ParseException ex) {
			System.out.println("You entered an invalid date format. Please, enter a valid date in the MM/dd/yyyy format.");
		}
			
	}
	
	// Implementing required abstract method from superclass Appointment
		@Override
		boolean occursOn(int year, int month, int day) {
			String MMddyyyy = String.valueOf(month) + "/" + String.valueOf(day) + "/" + String.valueOf(year);
			Date d = null;
			try {
				d = format.parse(MMddyyyy);
			} catch (ParseException ex) {
			}
			
			if (d.before(endDate) && d.after(startDate)) {
				return true;
			}
			
			if(d.equals(endDate) || d.equals(startDate)) {
				return true;
			}
			return false;
		}

		@Override
		public String toString() {
			return this.type + " - " + this.description;
		}
		
		@Override
		Date getStartDate() {
			return startDate;
		}



		@Override
		Date getEndDate() {
			return endDate;
		}



		@Override
		String getDay() {
			// Ignore this method
			return null;
		}
		
		@Override
		Date getDate() {
			// Ignore this method
			return null;
		}
}
