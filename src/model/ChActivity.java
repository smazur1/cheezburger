package model;

import java.io.Serializable;
import javax.persistence.*;


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

}