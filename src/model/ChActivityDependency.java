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

	//bi-directional many-to-one association to ChActivity
	@ManyToOne
	@JoinColumn(name="DEPACTID")
	private ChActivity chActivity1;

	//bi-directional many-to-one association to ChActivity
	@ManyToOne
	@JoinColumn(name="ACTID")
	private ChActivity chActivity2;

	//bi-directional many-to-one association to ChJobtype
	@ManyToOne
	@JoinColumn(name="JOB_ID")
	private ChJobtype chJobtype;

	public ChActivityDependency() {
	}

	public long getActdepid() {
		return this.actdepid;
	}

	public void setActdepid(long actdepid) {
		this.actdepid = actdepid;
	}

	public ChActivity getChActivity1() {
		return this.chActivity1;
	}

	public void setChActivity1(ChActivity chActivity1) {
		this.chActivity1 = chActivity1;
	}

	public ChActivity getChActivity2() {
		return this.chActivity2;
	}

	public void setChActivity2(ChActivity chActivity2) {
		this.chActivity2 = chActivity2;
	}

	public ChJobtype getChJobtype() {
		return this.chJobtype;
	}

	public void setChJobtype(ChJobtype chJobtype) {
		this.chJobtype = chJobtype;
	}

}