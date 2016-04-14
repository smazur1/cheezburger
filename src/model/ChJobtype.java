package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CH_JOBTYPE database table.
 * 
 */
@Entity
@Table(name="CH_JOBTYPE")
@NamedQuery(name="ChJobtype.findAll", query="SELECT c FROM ChJobtype c")
public class ChJobtype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="JOB_ID")
	private long jobId;

	@Column(name="JOB_CODE")
	private String jobCode;

	@Column(name="JOB_DESCRIPTION")
	private String jobDescription;

	public ChJobtype() {
	}

	public long getJobId() {
		return this.jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public String getJobCode() {
		return this.jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getJobDescription() {
		return this.jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

}