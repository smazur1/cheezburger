package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CH_HRROLE database table.
 * 
 */
@Entity
@Table(name="CH_HRROLE")
@NamedQuery(name="ChHrrole.findAll", query="SELECT c FROM ChHrrole c")
public class ChHrrole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="HR_ID")
	private long hrId;

	@Column(name="ROLE_CODE")
	private String roleCode;

	@Column(name="ROLE_DESCRIPTION")
	private String roleDescription;

	//bi-directional many-to-one association to ChRoleActivity
	@OneToMany(mappedBy="chHrrole")
	private List<ChRoleActivity> chRoleActivities;

	//bi-directional many-to-one association to ChUserRole
	@OneToMany(mappedBy="chHrrole")
	private List<ChUserRole> chUserRoles;

	public ChHrrole() {
	}

	public long getHrId() {
		return this.hrId;
	}

	public void setHrId(long hrId) {
		this.hrId = hrId;
	}

	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleDescription() {
		return this.roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public List<ChRoleActivity> getChRoleActivities() {
		return this.chRoleActivities;
	}

	public void setChRoleActivities(List<ChRoleActivity> chRoleActivities) {
		this.chRoleActivities = chRoleActivities;
	}

	public ChRoleActivity addChRoleActivity(ChRoleActivity chRoleActivity) {
		getChRoleActivities().add(chRoleActivity);
		chRoleActivity.setChHrrole(this);

		return chRoleActivity;
	}

	public ChRoleActivity removeChRoleActivity(ChRoleActivity chRoleActivity) {
		getChRoleActivities().remove(chRoleActivity);
		chRoleActivity.setChHrrole(null);

		return chRoleActivity;
	}

	public List<ChUserRole> getChUserRoles() {
		return this.chUserRoles;
	}

	public void setChUserRoles(List<ChUserRole> chUserRoles) {
		this.chUserRoles = chUserRoles;
	}

	public ChUserRole addChUserRole(ChUserRole chUserRole) {
		getChUserRoles().add(chUserRole);
		chUserRole.setChHrrole(this);

		return chUserRole;
	}

	public ChUserRole removeChUserRole(ChUserRole chUserRole) {
		getChUserRoles().remove(chUserRole);
		chUserRole.setChHrrole(null);

		return chUserRole;
	}

}