package organisation.model;

//import java.sql.Date;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import organisation.model.Employee;

@Entity
@Table(name = "employeetimesheet", uniqueConstraints = { @UniqueConstraint(columnNames = { "srNo" }) })
public class EmployeeTimeSheet {

	

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "srNo", length = 11, nullable = false, unique = true)
	private int srNo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	public Employee getEmployee() {
		return employee;
	}
	
	@ManyToOne
	@JoinColumn(name ="employee_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Employee employee;
	
	@Column
	private String jobTitle;
	@Column
	private int hours;
	@Column
	private String status;
	@Column
	private Date date;

	public EmployeeTimeSheet() {
	}

	public EmployeeTimeSheet(Employee employee, int srNo, String jobTitle, int hours, String status, Date date) {
		super();
		this.employee = employee;
		this.srNo = srNo;
		this.jobTitle = jobTitle;
		this.hours = hours;
		this.status = status;
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
