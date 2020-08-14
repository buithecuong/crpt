package organisation.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

public class DailyEmployeeTimeSheet {

	private long hours;
	private Date date;
	
	private Employee employee;

	public DailyEmployeeTimeSheet() {
	}

	public DailyEmployeeTimeSheet(long hours, Date date, Employee employee) {
		super();
		this.hours = hours;
		this.date = date;
		this.employee = employee;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
