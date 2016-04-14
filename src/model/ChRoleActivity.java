package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CH_ROLE_ACTIVITY database table.
 * 
 */
@Entity
@Table(name="CH_ROLE_ACTIVITY")
@NamedQuery(name="ChRoleActivity.findAll", query="SELECT c FROM ChRoleActivity c")
public class ChRoleActivity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long roleactid;

	private String raaccess;

	//bi-directional many-to-one association to ChActivity
	@ManyToOne
	@JoinColumn(name="ACTID")
	private ChActivity chActivity;

	//bi-directional many-to-one association to ChHrrole
	@ManyToOne
	@JoinColumn(name="HR_ID")
	private ChHrrole chHrrole;

	public ChRoleActivity() {
	}

	public long getRoleactid() {
		return this.roleactid;
	}

	public void setRoleactid(long roleactid) {
		this.roleactid = roleactid;
	}

	public String getRaaccess() {
		return this.raaccess;
	}

	public void setRaaccess(String raaccess) {
		this.raaccess = raaccess;
	}

	public ChActivity getChActivity() {
		return this.chActivity;
	}

	public void setChActivity(ChActivity chActivity) {
		this.chActivity = chActivity;
	}

	public ChHrrole getChHrrole() {
		return this.chHrrole;
	}

	public void setChHrrole(ChHrrole chHrrole) {
		this.chHrrole = chHrrole;
	}

}