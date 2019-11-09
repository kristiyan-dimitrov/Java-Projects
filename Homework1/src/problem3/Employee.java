package problem3;

public class Employee implements Comparable<Employee>{

	private String name;
	private float salary;

	// Constructor method
	public Employee(String name, float salary){
		this.name = name;
		this.salary = salary;
	}

	// Overriding the compareTo method so we can compare two employees
	
	public int compareTo(Employee anotherEmployee){
		
		int result;
		if (Float.compare(this.salary, anotherEmployee.salary) == 0){
			result = 0;
		} else if (Float.compare(this.salary, anotherEmployee.salary) > 0){
			result = 1;
		} else {
			result = -1;
		}
		
		return result;
	}

	public String toString() {
		return this.name + "---" + this.salary;
	}

	// Overriding the equals method so we can establish equality b/w two employees based on salary
	
	public boolean equals(Employee anotherEmployee) {
		return this.salary == anotherEmployee.salary;
	}

	// Define two get methods - for name & salary
	public String getName(){
		return name;
	}

	public float getSalary(){
		return salary;
	}

}