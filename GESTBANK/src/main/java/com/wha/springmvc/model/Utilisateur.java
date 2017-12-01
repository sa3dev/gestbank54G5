package com.wha.springmvc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "utilisateur")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "UTILISATEUR_TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class Utilisateur implements Serializable {

	// #region Attributs

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "nom")
	private String nom;
	@Column(name = "prenom")
	private String prenom;
	@Id
	@Column(name = "pseudo", length=75)
	private String pseudo;
	@Column(name = "mdp")
	private String motdepasse;
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
	@Column(name = "dateNaissance")
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	// #endregion

	// #region Constructeurs
	public Utilisateur() {

	}

	public Utilisateur(String nom, String prenom, String pseudo, String motdepasse, String email, String adresse,
			Integer codePostal, String ville, Integer telephone, Date dateNaissance) {
		this.nom = nom;
		this.prenom = prenom;
		this.pseudo = pseudo;
		this.motdepasse = motdepasse;
		this.email = email;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.telephone = telephone;
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

	public String getMotdepasse() {
		return motdepasse;
	}

	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	// #endregion

	// #region Utilitaire

	@Override
	public String toString() {
		return "Utilisateur [nom=" + nom + ", prenom=" + prenom + ", pseudo=" + pseudo + ", motdepasse=" + motdepasse
				+ ", email=" + email + ", adresse=" + adresse + ", codePostal=" + codePostal + ", ville=" + ville
				+ ", telephone=" + telephone + ", dateNaissance=" + dateNaissance + "]";
	}

	// #endregion
}
