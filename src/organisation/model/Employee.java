package organisation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length=11, nullable=false, unique=true)
    private Integer studentId;
 
    @Column(name = "name", length=20, nullable=true)
    private String studentName;
 
    @Column(name="age", length=5, nullable=true)
    private Integer studentAge;*/

@Entity
@Table(name = "employee", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 11, nullable = false, unique = true)
	private int id;

	@Column
	private String name;
	@Column
	private String team;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		return "Name: '" + this.name + "', User Name: '" + this.username + "', Team: '" + this.team + "'";
	}

}
