package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


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

	@Temporal(TemporalType.DATE)
	private Date actmoddate;

	private String actstatus;

	//bi-directional many-to-one association to ChActivity
	@ManyToOne
	@JoinColumn(name="ACTID")
	private ChActivity chActivity;

	//bi-directional many-to-one association to ChApplication
	@ManyToOne
	@JoinColumn(name="APPID")
	private ChApplication chApplication;

	//bi-directional many-to-one association to ChComment
	@OneToMany(mappedBy="chApplicationActivity")
	private List<ChComment> chComments;

	//bi-directional many-to-one association to ChDrugScreen
	@OneToMany(mappedBy="chApplicationActivity")
	private List<ChDrugScreen> chDrugScreens;

	public ChApplicationActivity() {
	}

	public long getAppactid() {
		return this.appactid;
	}

	public void setAppactid(long appactid) {
		this.appactid = appactid;
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

	public ChActivity getChActivity() {
		return this.chActivity;
	}

	public void setChActivity(ChActivity chActivity) {
		this.chActivity = chActivity;
	}

	public ChApplication getChApplication() {
		return this.chApplication;
	}

	public void setChApplication(ChApplication chApplication) {
		this.chApplication = chApplication;
	}

	public List<ChComment> getChComments() {
		return this.chComments;
	}

	public void setChComments(List<ChComment> chComments) {
		this.chComments = chComments;
	}

	public ChComment addChComment(ChComment chComment) {
		getChComments().add(chComment);
		chComment.setChApplicationActivity(this);

		return chComment;
	}

	public ChComment removeChComment(ChComment chComment) {
		getChComments().remove(chComment);
		chComment.setChApplicationActivity(null);

		return chComment;
	}

	public List<ChDrugScreen> getChDrugScreens() {
		return this.chDrugScreens;
	}

	public void setChDrugScreens(List<ChDrugScreen> chDrugScreens) {
		this.chDrugScreens = chDrugScreens;
	}

	public ChDrugScreen addChDrugScreen(ChDrugScreen chDrugScreen) {
		getChDrugScreens().add(chDrugScreen);
		chDrugScreen.setChApplicationActivity(this);

		return chDrugScreen;
	}

	public ChDrugScreen removeChDrugScreen(ChDrugScreen chDrugScreen) {
		getChDrugScreens().remove(chDrugScreen);
		chDrugScreen.setChApplicationActivity(null);

		return chDrugScreen;
	}

}