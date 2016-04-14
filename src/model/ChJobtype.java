package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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

	//bi-directional many-to-one association to ChActivityDependency
	@OneToMany(mappedBy="chJobtype")
	private List<ChActivityDependency> chActivityDependencies;

	//bi-directional many-to-one association to ChApplication
	@OneToMany(mappedBy="chJobtype")
	private List<ChApplication> chApplications;

	//bi-directional many-to-one association to ChJobactivity
	@OneToMany(mappedBy="chJobtype")
	private List<ChJobactivity> chJobactivities;

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

	public List<ChActivityDependency> getChActivityDependencies() {
		return this.chActivityDependencies;
	}

	public void setChActivityDependencies(List<ChActivityDependency> chActivityDependencies) {
		this.chActivityDependencies = chActivityDependencies;
	}

	public ChActivityDependency addChActivityDependency(ChActivityDependency chActivityDependency) {
		getChActivityDependencies().add(chActivityDependency);
		chActivityDependency.setChJobtype(this);

		return chActivityDependency;
	}

	public ChActivityDependency removeChActivityDependency(ChActivityDependency chActivityDependency) {
		getChActivityDependencies().remove(chActivityDependency);
		chActivityDependency.setChJobtype(null);

		return chActivityDependency;
	}

	public List<ChApplication> getChApplications() {
		return this.chApplications;
	}

	public void setChApplications(List<ChApplication> chApplications) {
		this.chApplications = chApplications;
	}

	public ChApplication addChApplication(ChApplication chApplication) {
		getChApplications().add(chApplication);
		chApplication.setChJobtype(this);

		return chApplication;
	}

	public ChApplication removeChApplication(ChApplication chApplication) {
		getChApplications().remove(chApplication);
		chApplication.setChJobtype(null);

		return chApplication;
	}

	public List<ChJobactivity> getChJobactivities() {
		return this.chJobactivities;
	}

	public void setChJobactivities(List<ChJobactivity> chJobactivities) {
		this.chJobactivities = chJobactivities;
	}

	public ChJobactivity addChJobactivity(ChJobactivity chJobactivity) {
		getChJobactivities().add(chJobactivity);
		chJobactivity.setChJobtype(this);

		return chJobactivity;
	}

	public ChJobactivity removeChJobactivity(ChJobactivity chJobactivity) {
		getChJobactivities().remove(chJobactivity);
		chJobactivity.setChJobtype(null);

		return chJobactivity;
	}

}