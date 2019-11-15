package hw5;

import java.text.SimpleDateFormat;
import java.util.Date;

abstract public class Appointment {

	// Defining the format for the Date variable which will be used by all subclasses
	SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	
	// All subclasses should have a description
	String description;

	// All subclasses will also need a type so we can correctly parse them into the ArrayList at applciation launch 
	String type;
	
	// CONSTRUCTOR for the superclass
	public Appointment(String description) {
		this.description = description;
	}
	
	// Declaring ABSTRACT method, which will have to be defined in the subclasses
	abstract boolean occursOn(int year, int month, int day);
	abstract Date getDate(); // for the Onetime 
	abstract Date getStartDate(); // For Daily & Monthly
	abstract Date getEndDate(); // For Daily & Monthly
	abstract String getDay(); // For Monthly
	
	public String getDescription() {
		return description;
	}

//	public void setDescription(String description) {
//		this.description = description;
//	}

	public String getType() {
		return type;
	}


//	public void setType(String type) {
//		this.type = type;
//	}
	
	

}
