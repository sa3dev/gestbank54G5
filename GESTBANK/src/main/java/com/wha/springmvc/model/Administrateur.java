package com.wha.springmvc.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "administrateur")
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("Administrateur")
public class Administrateur extends Utilisateur {

	// #region Attribut
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Conseiller> listeConseillers;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<DemandeOuverture> listeDemandes;
	// #endregion

	// #region Constructeurs
	public Administrateur() {
		this.listeConseillers = new HashSet<Conseiller>();
		this.listeDemandes = new HashSet<DemandeOuverture>();
	}
	// #endregion

	// #region Accesseurs
	public Set<Conseiller> getListeConseillers() {
		return listeConseillers;
	}

	public void setListeConseillers(Set<Conseiller> listeConseillers) {
		this.listeConseillers = listeConseillers;
	}

	public Set<DemandeOuverture> getListeDemandes() {
		return listeDemandes;
	}

	public void setListeDemandes(Set<DemandeOuverture> listeDemandes) {
		this.listeDemandes = listeDemandes;
	}
	// #endregion

	// #region Utilitaire

	@Override
	public String toString() {
		return "Administrateur []";
	}

	// #endregion
}
