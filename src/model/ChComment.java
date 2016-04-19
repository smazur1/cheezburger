package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the CH_COMMENT database table.
 * 
 */
@Entity
@Table(name="CH_COMMENT")
@NamedQuery(name="ChComment.findAll", query="SELECT c FROM ChComment c")
public class ChComment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long comid;

	private String comments;

	@Temporal(TemporalType.DATE)
	private Date moddate;

	//bi-directional many-to-one association to ChApplicationActivity
	@ManyToOne
	@JoinColumn(name="APPACTID")
	private ChApplicationActivity chApplicationActivity;

	public ChComment() {
	}

	public long getComid() {
		return this.comid;
	}

	public void setComid(long comid) {
		this.comid = comid;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getModdate() {
		return this.moddate;
	}

	public void setModdate(Date moddate) {
		this.moddate = moddate;
	}

	public ChApplicationActivity getChApplicationActivity() {
		return this.chApplicationActivity;
	}

	public void setChApplicationActivity(ChApplicationActivity chApplicationActivity) {
		this.chApplicationActivity = chApplicationActivity;
	}

}