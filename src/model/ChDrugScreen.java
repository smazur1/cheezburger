package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the CH_DRUG_SCREEN database table.
 * 
 */
@Entity
@Table(name="CH_DRUG_SCREEN")
@NamedQuery(name="ChDrugScreen.findAll", query="SELECT c FROM ChDrugScreen c")
public class ChDrugScreen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long dsid;

	@Temporal(TemporalType.DATE)
	private Date moddate;

	private String results;

	private String testtype;

	//bi-directional many-to-one association to ChApplicationActivity
	@ManyToOne
	@JoinColumn(name="APPACTID")
	private ChApplicationActivity chApplicationActivity;

	public ChDrugScreen() {
	}

	public long getDsid() {
		return this.dsid;
	}

	public void setDsid(long dsid) {
		this.dsid = dsid;
	}

	public Date getModdate() {
		return this.moddate;
	}

	public void setModdate(Date moddate) {
		this.moddate = moddate;
	}

	public String getResults() {
		return this.results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public String getTesttype() {
		return this.testtype;
	}

	public void setTesttype(String testtype) {
		this.testtype = testtype;
	}

	public ChApplicationActivity getChApplicationActivity() {
		return this.chApplicationActivity;
	}

	public void setChApplicationActivity(ChApplicationActivity chApplicationActivity) {
		this.chApplicationActivity = chApplicationActivity;
	}

}