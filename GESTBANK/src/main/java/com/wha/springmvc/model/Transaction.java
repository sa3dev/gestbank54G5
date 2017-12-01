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
@Table(name = "transaction")
public class Transaction {
	// #region Attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "noTransaction")
	private Integer noTransaction;
	@Column(name = "montant")
	private Integer montant;
	@Column(name = "libelle")
	private String libelle;
	@Column(name = "dateTransaction")
	@Temporal(TemporalType.DATE)
	private Date date;
	@Column(name = "typeTransaction")
	private String typeTransaction;
	// #endregion

	// #region Constructeurs
	public Transaction() {

	}

	public Transaction(Integer montant, String libelle, Date date, String typeTransaction) {
		this.montant = montant;
		this.libelle = libelle;
		this.date = date;
		this.typeTransaction = typeTransaction;
	}

	// #endregion

	// #region Accesseurs
	public Integer getMontant() {
		return montant;
	}

	public void setMontant(Integer montant) {
		this.montant = montant;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getNoTransaction() {
		return noTransaction;
	}

	public void setNoTransaction(Integer noTransaction) {
		this.noTransaction = noTransaction;
	}

	// #endregion

	// #region Utilitaire

	public String getTypeTransaction() {
		return typeTransaction;
	}

	public void setTypeTransaction(String typeTransaction) {
		this.typeTransaction = typeTransaction;
	}

	@Override
	public String toString() {
		return "Transaction [noTransaction=" + noTransaction + ", montant=" + montant + ", libelle=" + libelle
				+ ", date=" + date + "]";
	}

	// #endregion
}
