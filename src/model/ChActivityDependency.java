package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CH_ACTIVITY_DEPENDENCY database table.
 * 
 */
@Entity
@Table(name="CH_ACTIVITY_DEPENDENCY")
@NamedQuery(name="ChActivityDependency.findAll", query="SELECT c FROM ChActivityDependency c")
public class ChActivityDependency implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long actdepid;

	private String actcode;

	private String depactcode;

	private String jobcode;

	public ChActivityDependency() {
	}

	public long getActdepid() {
		return this.actdepid;
	}

	public void setActdepid(long actdepid) {
		this.actdepid = actdepid;
	}

	public String getActcode() {
		return this.actcode;
	}

	public void setActcode(String actcode) {
		this.actcode = actcode;
	}

	public String getDepactcode() {
		return this.depactcode;
	}

	public void setDepactcode(String depactcode) {
		this.depactcode = depactcode;
	}

	public String getJobcode() {
		return this.jobcode;
	}

	public void setJobcode(String jobcode) {
		this.jobcode = jobcode;
	}

}