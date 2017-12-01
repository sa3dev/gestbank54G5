package com.wha.springmvc.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "conseiller")
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("Conseiller")
public class Conseiller extends Utilisateur {
	// #region Attributs

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mle")
	private Long matricule;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "conseiller")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Set<DemandeOuverture> listeDemandesOuverture;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Requete> listeDemandesClient;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "conseiller")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Set<Client> listeClients;
	// #endregion

	// #region Constructeurs
	public Conseiller() {
		listeClients = new HashSet<Client>();
		listeDemandesClient = new HashSet<Requete>();
		listeDemandesOuverture = new HashSet<DemandeOuverture>();
	}

	public Conseiller(Long matricule, String nom, String prenom, String pseudo, String motdepasse, String email,
			String adresse, Integer codePostal, String ville, Integer telephone, Date dateNaissance) {
		super(nom, prenom, pseudo, motdepasse, email, adresse, codePostal, ville, telephone, dateNaissance);
		this.matricule = matricule;
		listeClients = new HashSet<Client>();
		listeDemandesClient = new HashSet<Requete>();
		listeDemandesOuverture = new HashSet<DemandeOuverture>();
	}

	// #endregion

	// #region Accesseurs
	public Long getMatricule() {
		return matricule;
	}

	public void setMatricule(Long matricule) {
		this.matricule = matricule;
	}

	public Set<DemandeOuverture> getListeDemandesOuverture() {
		return listeDemandesOuverture;
	}

	public void setListeDemandesOuverture(Set<DemandeOuverture> listeDemandesOuverture) {
		this.listeDemandesOuverture = listeDemandesOuverture;
	}

	public Set<Requete> getListeDemandesClient() {
		return listeDemandesClient;
	}

	public void setListeDemandesClient(Set<Requete> listeDemandesClient) {
		this.listeDemandesClient = listeDemandesClient;
	}

	public Set<Client> getListeClients() {
		return listeClients;
	}

	public void setListeClients(Set<Client> listeClients) {
		this.listeClients = listeClients;
	}

	// #endregion

	// #region Utilitaire

	@Override
	public String toString() {
		return "Conseiller [matricule=" + matricule + ", getNom()=" + getNom() + ", getPrenom()=" + getPrenom()
				+ ", getEmail()=" + getEmail() + ", getAdresse()=" + getAdresse() + ", getCodePostal()="
				+ getCodePostal() + ", getVille()=" + getVille() + ", getTelephone()=" + getTelephone()
				+ ", getPseudo()=" + getPseudo() + ", getDateNaissance()=" + getDateNaissance() + "]";
	}

	public void ajoutClient(Client client) {
		Set<Client> l = this.getListeClients();
		l.add(client);
		this.setListeClients(l);
	}

	// #endregion

}
