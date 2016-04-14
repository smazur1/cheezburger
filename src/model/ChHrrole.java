package model;

import java.io.Serializable;
import javax.persistence.*;


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

}