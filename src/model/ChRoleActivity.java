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

	private String actcode;

	private String raaccess;

	private String rolecode;

	public ChRoleActivity() {
	}

	public long getRoleactid() {
		return this.roleactid;
	}

	public void setRoleactid(long roleactid) {
		this.roleactid = roleactid;
	}

	public String getActcode() {
		return this.actcode;
	}

	public void setActcode(String actcode) {
		this.actcode = actcode;
	}

	public String getRaaccess() {
		return this.raaccess;
	}

	public void setRaaccess(String raaccess) {
		this.raaccess = raaccess;
	}

	public String getRolecode() {
		return this.rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

}