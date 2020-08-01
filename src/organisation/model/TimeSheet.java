package organisation.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "timesheet", uniqueConstraints = { @UniqueConstraint(columnNames = { "srNo" }) })
public class TimeSheet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "srNo", length = 11, nullable = false, unique = true)
	private int srNo;
	@Column
	private String jobTitle;
	@Column
	private int hours;
	@Column
	private String status;
	@Column
	private Date date;

	public TimeSheet() {
	}

	public TimeSheet(int srNo, String jobTitle, int hours, String status, Date date) {
		super();
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
}
