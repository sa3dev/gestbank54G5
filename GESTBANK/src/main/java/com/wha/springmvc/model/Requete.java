package com.wha.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "requete")
public class Requete {
	// #region Attributs

	@Id
	@Column(name = "numRequete")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int numRequete;
	@Column(name = "type")
	private String type;
	@Column(name = "accepte")
	private boolean accepte;
	@ManyToOne
	@JoinColumn(name = "no_compte", referencedColumnName = "noCompte")
	private Compte compte;
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="id_client",referencedColumnName="identifiant") private
	 * Client client;
	 */

	@Column(name = "message")
	private String message;
	// #endregion

	// #region Constructeurs
	public Requete() {
		this.accepte=false;
	}
	// #endregion

	// #region Accesseurs

	public String getType() {
		return type;
	}

	public int getNumRequete() {
		return numRequete;
	}

	public void setNumRequete(int numRequete) {
		this.numRequete = numRequete;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isAccepte() {
		return accepte;
	}

	public void setAccepte(boolean accepte) {
		this.accepte = accepte;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	/*
	 * public Client getClient() { return client; }
	 * 
	 * public void setClient(Client client) { this.client = client; }
	 */

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	// #endregion

	// #region Utilitaire

	@Override
	public String toString() {
		return "Requete [type=" + type + ", compte=" + compte + ", message=" + message + "]";
	}

	// #endregion
}
