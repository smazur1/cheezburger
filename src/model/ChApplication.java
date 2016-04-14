package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the CH_APPLICATION database table.
 * 
 */
@Entity
@Table(name="CH_APPLICATION")
@NamedQuery(name="ChApplication.findAll", query="SELECT c FROM ChApplication c")
public class ChApplication implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long appid;

	private String address;

	private String appref;

	private String appstatus;

	private String birthday;

	private String citizen;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private String druguse;

	private String education;

	private String jobcode;

	private String jobhistory;

	@Temporal(TemporalType.DATE)
	private Date moddate;

	private String name;

	private String veteran;

	//bi-directional many-to-one association to ChApplicationActivity
	@OneToMany(mappedBy="chApplication")
	private List<ChApplicationActivity> chApplicationActivities;

	public ChApplication() {
	}

	public long getAppid() {
		return this.appid;
	}

	public void setAppid(long appid) {
		this.appid = appid;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAppref() {
		return this.appref;
	}

	public void setAppref(String appref) {
		this.appref = appref;
	}

	public String getAppstatus() {
		return this.appstatus;
	}

	public void setAppstatus(String appstatus) {
		this.appstatus = appstatus;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCitizen() {
		return this.citizen;
	}

	public void setCitizen(String citizen) {
		this.citizen = citizen;
	}

	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getDruguse() {
		return this.druguse;
	}

	public void setDruguse(String druguse) {
		this.druguse = druguse;
	}

	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getJobcode() {
		return this.jobcode;
	}

	public void setJobcode(String jobcode) {
		this.jobcode = jobcode;
	}

	public String getJobhistory() {
		return this.jobhistory;
	}

	public void setJobhistory(String jobhistory) {
		this.jobhistory = jobhistory;
	}

	public Date getModdate() {
		return this.moddate;
	}

	public void setModdate(Date moddate) {
		this.moddate = moddate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVeteran() {
		return this.veteran;
	}

	public void setVeteran(String veteran) {
		this.veteran = veteran;
	}

	public List<ChApplicationActivity> getChApplicationActivities() {
		return this.chApplicationActivities;
	}

	public void setChApplicationActivities(List<ChApplicationActivity> chApplicationActivities) {
		this.chApplicationActivities = chApplicationActivities;
	}

	public ChApplicationActivity addChApplicationActivity(ChApplicationActivity chApplicationActivity) {
		getChApplicationActivities().add(chApplicationActivity);
		chApplicationActivity.setChApplication(this);

		return chApplicationActivity;
	}

	public ChApplicationActivity removeChApplicationActivity(ChApplicationActivity chApplicationActivity) {
		getChApplicationActivities().remove(chApplicationActivity);
		chApplicationActivity.setChApplication(null);

		return chApplicationActivity;
	}

}