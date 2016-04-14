package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


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

	//bi-directional many-to-one association to ChHrrole
	@ManyToOne
	@JoinColumn(name="HR_ID")
	private ChHrrole chHrrole;

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

	public ChHrrole getChHrrole() {
		return this.chHrrole;
	}

	public void setChHrrole(ChHrrole chHrrole) {
		this.chHrrole = chHrrole;
	}

	public ChUser getChUser() {
		return this.chUser;
	}

	public void setChUser(ChUser chUser) {
		this.chUser = chUser;
	}

}