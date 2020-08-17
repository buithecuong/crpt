package organisation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "objective", uniqueConstraints = { @UniqueConstraint(columnNames = {"srNo"}) })
public class Objective {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "srNo", length = 11, nullable = false, unique = true)
		private int srNo = 1;
		@Column
		private String name;
		@Column
		private int duration;
		@Column
		private String type;
		@Column
		private String description;

		public Objective() {
		}

		public Objective(int srNo, String name, int duration, String type, String description) {
			super();
			this.srNo = srNo;
			this.name = name;
			this.duration = duration;
			this.type = type;
			this.description = description;
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


}
