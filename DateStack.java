package application;

import java.util.Date;

public class DateStack {
	
	private Date dateOfDeth  = new Date();
	
	private Stack stack = new Stack();

	public DateStack() {
		super();
	}

	public DateStack(Date dateOfDeth) {
		super();
		this.dateOfDeth = dateOfDeth;
	}

	public DateStack(Date dateOfDeth, Stack stack) {
		super();
		this.dateOfDeth = dateOfDeth;
		this.stack = stack;
	}

	public Date getDateOfDeth() {
		return dateOfDeth;
	}

	public void setDateOfDeth(Date dateOfDeth) {
		this.dateOfDeth = dateOfDeth;
	}

	public Stack getStack() {
		return stack;
	}

	public void setStack(Stack stack) {
		this.stack = stack;
	}

	
	
 //  compareTo method that compare between martyr by date of death
	
	public int compareTo(DateStack o) {
		return  (this.getDateOfDeth().compareTo(o.getDateOfDeth()));
			
	}

	@Override
	public String toString() {
		return " " + dateOfDeth + "";
	}

	
	
	
	

}
