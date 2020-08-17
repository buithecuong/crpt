package organisation.model;

import javax.persistence.CascadeType;
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

@Entity
@Table(name = "employeeobjective", uniqueConstraints = { @UniqueConstraint(columnNames = {"srNo"}) })
public class EmployeeObjective {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "srNo", length = 11, nullable = false, unique = true)
		private int srNo = 1;
		
		
		@ManyToOne(cascade = CascadeType.ALL)
		public Employee getEmployee() {
			return employee;
		}
		
		@ManyToOne
		@JoinColumn(name ="employee_id")
		@OnDelete(action = OnDeleteAction.CASCADE)
		private Employee employee;
		
		
		@Column
		private String name;
		@Column
		private int duration;
		@Column
		private String type;
		@Column
		private String description;
		
		@Column
		private String status;

		public EmployeeObjective() {
		}

		public EmployeeObjective(Employee employee, int srNo, String name, int duration, String type, String description, String status) {
			super();
			this.employee = employee;
			this.srNo = srNo;
			this.name = name;
			this.duration = duration;
			this.type = type;
			this.description = description;
			
			this.status = status;
		}

		public int getSrNo() {
			return srNo;
		}

		public void setSrNo(int srNo) {
			this.srNo = srNo;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getDuration() {
			return duration;
		}

		public void setDuration(int duration) {
			this.duration = duration;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
		
		public void setEmployee(Employee employee) {
			this.employee = employee;
		}
		
		public String getStatus() {
			return status;
		}
		
		public void setStatus(String status) {
			this.status = status;
		}



}
