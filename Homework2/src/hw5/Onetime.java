package hw5;

import java.text.ParseException;
import java.util.Date;

public class Onetime extends Appointment{

	// The date variable, which uniquely identifies this OneTime appointment 
	Date date;
	
	
	// CONSTRUCTOR
	public Onetime(String description, String date) {
		// Setting description as per superclass constructor
		super(description);
		
		// Setting the type as per the superclass.
		this.type = "Onetime";
		
		try {
			this.date = format.parse(date); // Take the string input and parse to a Date object based on the format defined in the superclass Appointment	
		} catch (ParseException ex) {
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
		
		return d.equals(date);
	}

	@Override
	public String toString() {
		return this.type + " - " + this.description;
	}
	
	public Date getDate() {
		return this.date;
	}


	@Override
	Date getStartDate() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	Date getEndDate() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	String getDay() {
		// TODO Auto-generated method stub
		return null;
	}


//	public void setDate(Date date) {
//		this.date = date;
//	}	
	
	
}
