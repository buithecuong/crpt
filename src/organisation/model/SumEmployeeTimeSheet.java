package organisation.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

public class SumEmployeeTimeSheet {

	private long hours;
	
	private Employee employee;
	
	public SumEmployeeTimeSheet() {
	}

	public SumEmployeeTimeSheet(long hours, Employee employee) {
		super();
		this.hours = hours;
		this.employee = employee;
	}

	public long getHours() {
		return hours;
	}

	public void setHours(long hours) {
		this.hours = hours;
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setDate(Employee employee) {
		this.employee = employee;
	}

	
}
