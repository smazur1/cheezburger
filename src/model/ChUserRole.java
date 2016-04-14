package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CH_USER_ROLE database table.
 * 
 */
@Entity
@Table(name="CH_USER_ROLE")
@NamedQuery(name="ChUserRole.findAll", query="SELECT c FROM ChUserRole c")
public class ChUserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long userroleid;

	private String rolecode;

	//bi-directional many-to-one association to ChUser
	@ManyToOne
	@JoinColumn(name="USERID")
	private ChUser chUser;

	public ChUserRole() {
	}

	public long getUserroleid() {
		return this.userroleid;
	}

	public void setUserroleid(long userroleid) {
		this.userroleid = userroleid;
	}

	public String getRolecode() {
		return this.rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	public ChUser getChUser() {
		return this.chUser;
	}

	public void setChUser(ChUser chUser) {
		this.chUser = chUser;
	}

}