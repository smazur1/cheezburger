package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the CH_APPLICATION_ACTIVITY database table.
 * 
 */
@Entity
@Table(name="CH_APPLICATION_ACTIVITY")
@NamedQuery(name="ChApplicationActivity.findAll", query="SELECT c FROM ChApplicationActivity c")
public class ChApplicationActivity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long appactid;

	private String actcode;

	@Temporal(TemporalType.DATE)
	private Date actmoddate;

	private String actstatus;

	//bi-directional many-to-one association to ChApplication
	@ManyToOne
	@JoinColumn(name="APPID")
	private ChApplication chApplication;

	public ChApplicationActivity() {
	}

	public long getAppactid() {
		return this.appactid;
	}

	public void setAppactid(long appactid) {
		this.appactid = appactid;
	}

	public String getActcode() {
		return this.actcode;
	}

	public void setActcode(String actcode) {
		this.actcode = actcode;
	}

	public Date getActmoddate() {
		return this.actmoddate;
	}

	public void setActmoddate(Date actmoddate) {
		this.actmoddate = actmoddate;
	}

	public String getActstatus() {
		return this.actstatus;
	}

	public void setActstatus(String actstatus) {
		this.actstatus = actstatus;
	}

	public ChApplication getChApplication() {
		return this.chApplication;
	}

	public void setChApplication(ChApplication chApplication) {
		this.chApplication = chApplication;
	}

}