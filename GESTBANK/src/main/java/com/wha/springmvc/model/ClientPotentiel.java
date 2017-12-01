package com.wha.springmvc.model;

import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "clientPotentiel")
public class ClientPotentiel {
	// #region Attributs
	@Id
	@Column(name = "pseudo", length=75)
	private String pseudo;
	@Column(name = "nom")
	private String nom;
	@Column(name = "prenom")
	private String prenom;
	@Column(name = "email")
	private String email;
	@Column(name = "adresse")
	private String adresse;
	@Column(name = "codePostal")
	private Integer codePostal;
	@Column(name = "ville")
	private String ville;
	@Column(name = "telephone")
	private Integer telephone;
	@Column(name = "revenuMens")
	private Integer revenuMens;
	@Column(name = "dateNaissance")
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	@Transient
	private Set<File> piecesJutificatives;
	// #endregion

	// #region Constructeurs
	public ClientPotentiel() {
		this.piecesJutificatives = new HashSet<File>();
	}

	public ClientPotentiel(String nom, String prenom, String pseudo, String email, String adresse, Integer codePostal,
			String ville, Integer telephone, Integer revenuMens, Date dateNaissance) {
		this();
		this.nom = nom;
		this.prenom = prenom;
		this.pseudo = pseudo;
		this.email = email;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.telephone = telephone;
		this.revenuMens = revenuMens;
		this.dateNaissance = dateNaissance;
	}

	// #endregion

	// #region Accesseurs
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Integer getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(Integer codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public Integer getTelephone() {
		return telephone;
	}

	public void setTelephone(Integer telephone) {
		this.telephone = telephone;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public Integer getRevenuMens() {
		return revenuMens;
	}

	public void setRevenuMens(Integer revenuMens) {
		this.revenuMens = revenuMens;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Set<File> getPiecesJutificatives() {
		return piecesJutificatives;
	}

	public void setPiecesJutificatives(Set<File> piecesJutificatives) {
		this.piecesJutificatives = piecesJutificatives;
	}
	// #endregion

	// #region Utilitaire

	@Override
	public String toString() {
		return "ClientPotentiel [pseudo=" + pseudo + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email
				+ ", adresse=" + adresse + ", codePostal=" + codePostal + ", ville=" + ville + ", telephone="
				+ telephone + ", revenuMens=" + revenuMens + ", dateNaissance=" + dateNaissance + "]";
	}

	// #endregion
}
