package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CH_USER database table.
 * 
 */
@Entity
@Table(name="CH_USER")
@NamedQuery(name="ChUser.findAll", query="SELECT c FROM ChUser c")
public class ChUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long userid;

	private String password;

	private String username;

	//bi-directional many-to-one association to ChUserRole
	@OneToMany(mappedBy="chUser")
	private List<ChUserRole> chUserRoles;

	public ChUser() {
	}

	public long getUserid() {
		return this.userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<ChUserRole> getChUserRoles() {
		return this.chUserRoles;
	}

	public void setChUserRoles(List<ChUserRole> chUserRoles) {
		this.chUserRoles = chUserRoles;
	}

	public ChUserRole addChUserRole(ChUserRole chUserRole) {
		getChUserRoles().add(chUserRole);
		chUserRole.setChUser(this);

		return chUserRole;
	}

	public ChUserRole removeChUserRole(ChUserRole chUserRole) {
		getChUserRoles().remove(chUserRole);
		chUserRole.setChUser(null);

		return chUserRole;
	}

}