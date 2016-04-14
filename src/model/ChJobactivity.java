package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CH_JOBACTIVITY database table.
 * 
 */
@Entity
@Table(name="CH_JOBACTIVITY")
@NamedQuery(name="ChJobactivity.findAll", query="SELECT c FROM ChJobactivity c")
public class ChJobactivity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="JOBACT_ID")
	private long jobactId;

	@Column(name="ACT_CODE")
	private String actCode;

	@Column(name="JOB_CODE")
	private String jobCode;

	public ChJobactivity() {
	}

	public long getJobactId() {
		return this.jobactId;
	}

	public void setJobactId(long jobactId) {
		this.jobactId = jobactId;
	}

	public String getActCode() {
		return this.actCode;
	}

	public void setActCode(String actCode) {
		this.actCode = actCode;
	}

	public String getJobCode() {
		return this.jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

}