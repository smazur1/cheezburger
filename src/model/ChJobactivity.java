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

	//bi-directional many-to-one association to ChActivity
	@ManyToOne
	@JoinColumn(name="ACTID")
	private ChActivity chActivity;

	//bi-directional many-to-one association to ChJobtype
	@ManyToOne
	@JoinColumn(name="JOB_ID")
	private ChJobtype chJobtype;

	public ChJobactivity() {
	}

	public long getJobactId() {
		return this.jobactId;
	}

	public void setJobactId(long jobactId) {
		this.jobactId = jobactId;
	}

	public ChActivity getChActivity() {
		return this.chActivity;
	}

	public void setChActivity(ChActivity chActivity) {
		this.chActivity = chActivity;
	}

	public ChJobtype getChJobtype() {
		return this.chJobtype;
	}

	public void setChJobtype(ChJobtype chJobtype) {
		this.chJobtype = chJobtype;
	}

}