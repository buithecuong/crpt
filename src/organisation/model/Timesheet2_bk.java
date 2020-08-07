package organisation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "timesheet", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })

public class Timesheet2_bk {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 11, nullable = false, unique = true)	
	private int id;

	@Column
	private String jobTitle;
	@Column
	private int hours;
	@Column
	private String statusCheck;
	@Column
	private String date;
	

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

	public String getStatusCheck() {
		return statusCheck;
	}

	public void setStatusCheck(String statusCheck) {
		this.statusCheck = statusCheck;
	}

	public String getDate() {
		return date;
	}

	public void setDatee(String date) {
		this.date = date;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	

}
