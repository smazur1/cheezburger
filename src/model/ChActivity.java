package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CH_ACTIVITY database table.
 * 
 */
@Entity
@Table(name="CH_ACTIVITY")
@NamedQuery(name="ChActivity.findAll", query="SELECT c FROM ChActivity c")
public class ChActivity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long actid;

	private String actcode;

	private String actdescription;

	//bi-directional many-to-one association to ChActivityDependency
	@OneToMany(mappedBy="chActivity1")
	private List<ChActivityDependency> chActivityDependencies1;

	//bi-directional many-to-one association to ChActivityDependency
	@OneToMany(mappedBy="chActivity2")
	private List<ChActivityDependency> chActivityDependencies2;

	//bi-directional many-to-one association to ChApplicationActivity
	@OneToMany(mappedBy="chActivity")
	private List<ChApplicationActivity> chApplicationActivities;

	//bi-directional many-to-one association to ChJobactivity
	@OneToMany(mappedBy="chActivity")
	private List<ChJobactivity> chJobactivities;

	//bi-directional many-to-one association to ChRoleActivity
	@OneToMany(mappedBy="chActivity")
	private List<ChRoleActivity> chRoleActivities;

	public ChActivity() {
	}

	public long getActid() {
		return this.actid;
	}

	public void setActid(long actid) {
		this.actid = actid;
	}

	public String getActcode() {
		return this.actcode;
	}

	public void setActcode(String actcode) {
		this.actcode = actcode;
	}

	public String getActdescription() {
		return this.actdescription;
	}

	public void setActdescription(String actdescription) {
		this.actdescription = actdescription;
	}

	public List<ChActivityDependency> getChActivityDependencies1() {
		return this.chActivityDependencies1;
	}

	public void setChActivityDependencies1(List<ChActivityDependency> chActivityDependencies1) {
		this.chActivityDependencies1 = chActivityDependencies1;
	}

	public ChActivityDependency addChActivityDependencies1(ChActivityDependency chActivityDependencies1) {
		getChActivityDependencies1().add(chActivityDependencies1);
		chActivityDependencies1.setChActivity1(this);

		return chActivityDependencies1;
	}

	public ChActivityDependency removeChActivityDependencies1(ChActivityDependency chActivityDependencies1) {
		getChActivityDependencies1().remove(chActivityDependencies1);
		chActivityDependencies1.setChActivity1(null);

		return chActivityDependencies1;
	}

	public List<ChActivityDependency> getChActivityDependencies2() {
		return this.chActivityDependencies2;
	}

	public void setChActivityDependencies2(List<ChActivityDependency> chActivityDependencies2) {
		this.chActivityDependencies2 = chActivityDependencies2;
	}

	public ChActivityDependency addChActivityDependencies2(ChActivityDependency chActivityDependencies2) {
		getChActivityDependencies2().add(chActivityDependencies2);
		chActivityDependencies2.setChActivity2(this);

		return chActivityDependencies2;
	}

	public ChActivityDependency removeChActivityDependencies2(ChActivityDependency chActivityDependencies2) {
		getChActivityDependencies2().remove(chActivityDependencies2);
		chActivityDependencies2.setChActivity2(null);

		return chActivityDependencies2;
	}

	public List<ChApplicationActivity> getChApplicationActivities() {
		return this.chApplicationActivities;
	}

	public void setChApplicationActivities(List<ChApplicationActivity> chApplicationActivities) {
		this.chApplicationActivities = chApplicationActivities;
	}

	public ChApplicationActivity addChApplicationActivity(ChApplicationActivity chApplicationActivity) {
		getChApplicationActivities().add(chApplicationActivity);
		chApplicationActivity.setChActivity(this);

		return chApplicationActivity;
	}

	public ChApplicationActivity removeChApplicationActivity(ChApplicationActivity chApplicationActivity) {
		getChApplicationActivities().remove(chApplicationActivity);
		chApplicationActivity.setChActivity(null);

		return chApplicationActivity;
	}

	public List<ChJobactivity> getChJobactivities() {
		return this.chJobactivities;
	}

	public void setChJobactivities(List<ChJobactivity> chJobactivities) {
		this.chJobactivities = chJobactivities;
	}

	public ChJobactivity addChJobactivity(ChJobactivity chJobactivity) {
		getChJobactivities().add(chJobactivity);
		chJobactivity.setChActivity(this);

		return chJobactivity;
	}

	public ChJobactivity removeChJobactivity(ChJobactivity chJobactivity) {
		getChJobactivities().remove(chJobactivity);
		chJobactivity.setChActivity(null);

		return chJobactivity;
	}

	public List<ChRoleActivity> getChRoleActivities() {
		return this.chRoleActivities;
	}

	public void setChRoleActivities(List<ChRoleActivity> chRoleActivities) {
		this.chRoleActivities = chRoleActivities;
	}

	public ChRoleActivity addChRoleActivity(ChRoleActivity chRoleActivity) {
		getChRoleActivities().add(chRoleActivity);
		chRoleActivity.setChActivity(this);

		return chRoleActivity;
	}

	public ChRoleActivity removeChRoleActivity(ChRoleActivity chRoleActivity) {
		getChRoleActivities().remove(chRoleActivity);
		chRoleActivity.setChActivity(null);

		return chRoleActivity;
	}

}