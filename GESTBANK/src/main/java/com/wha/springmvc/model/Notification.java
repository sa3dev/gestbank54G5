package com.wha.springmvc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "notification")
public class Notification {
	// #region Attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "numNotif")
	private int numNotif;
	@Column(name = "dateAffectation")
	@Temporal(TemporalType.DATE)
	private Date date;
	@Column(name = "message")
	private String message;
	@Column(name = "message_lu")
	private boolean lu;
	// #endregion

	// #region Constructeurs
	public Notification() {

	}
	// #endregion

	// #region Accesseurs
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isLu() {
		return lu;
	}

	public void setLu(boolean lu) {
		this.lu = lu;
	}

	public int getNumNotif() {
		return numNotif;
	}

	public void setNumNotif(int numNotif) {
		this.numNotif = numNotif;
	}

	// #endregion

	// #region Utilitaire

	@Override
	public String toString() {
		return "Notification [numNotif=" + numNotif + ", date=" + date + ", message=" + message + ", lu=" + lu + "]";
	}

	// #endregion
}
