package hw5;

import java.text.ParseException;
import java.util.Date;

public class Monthly extends Appointment {

	// I decided that the Monthly appointment will be uniquely defined by:
	// Starting date consisting of date MM/dd/yyyy
	Date startDate;
	// Ending date consisting of date MM/dd/yyyy (Probably not in this format actually; the toString method for Date gives back way too much detail)
	Date endDate;
	// The day of the month
	String day;
	
	// CONSTRUCTOR
	public Monthly(String description, String day, String startMMyyyy, String endMMyyyy) {
		// Setting description as per superclass constructor
		super(description);
		
		// Setting the day values
		this.day = day;
		
		// Setting the type as per the superclass.
		this.type = "Monthly";
		
		// Setting startDate from provided string
		try {
			String startDate = startMMyyyy.substring(0, 3) + day + startMMyyyy.substring(startMMyyyy.length()-5); // MM/ + day + /yyyy
			this.startDate = format.parse(startDate);
		} catch(ParseException ex) {
			System.out.println("You entered an invalid date format. Please, enter a valid date in the MM/dd/yyyy format.");
		}
		
		// Setting endDate from provided string 
		try {
			String endDate = endMMyyyy.substring(0, 3) + day + endMMyyyy.substring(endMMyyyy.length()-5);
			this.endDate = format.parse(endDate);
		} catch(ParseException ex) {
			System.out.println("You entered an invalid date format. Please, enter a valid date in the MM/dd/yyyy format.");
		}
					
	}
	
	// Implementing required abstract method from superclass Appointment
	@Override
	boolean occursOn(int year, int month, int day) {
		if (Integer.parseInt(this.day) == day) {
			
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
		}
		return false;
	}

	@Override
	public String toString() {
		return this.type + " - " + this.description;
	}
	
	@Override
	Date getDate() {
		return null; // Don't need this, was forced to put it due to Onetime class method
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
		return day;
	}
}
