package com.wha.springmvc.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "demandeOuverture")
public class DemandeOuverture {
	// #region Attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "numDemande")
	private int numDemande;
	@OneToOne(cascade = CascadeType.ALL)
	private ClientPotentiel cp;
	@Column(name = "Status")
	private boolean valide;

	@ManyToOne
	private Conseiller conseiller;

	@Column(name = "dateCreation")
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	@Column(name = "dateAffectation")
	@Temporal(TemporalType.DATE)
	private Date dateAffectation;
	// #endregion

	// #region Constructeurs
	public DemandeOuverture() {
		this.dateCreation = new Date();
	}

	public DemandeOuverture(ClientPotentiel cp, boolean valide, Date dateCreation,
			Date dateAffectation) {
		super();
		this.cp = cp;
		this.valide = valide;
		// this.conseiller = conseiller;
		this.dateCreation = dateCreation;
		this.dateAffectation = dateAffectation;
	}

	// #endregion

	// #region Accesseurs
	public ClientPotentiel getCp() {
		return cp;
	}

	public void setCp(ClientPotentiel cp) {
		this.cp = cp;
	}

	public boolean isValide() {
		return valide;
	}

	public void setValide(boolean valide) {
		this.valide = valide;
	}

	public Conseiller getConseiller() {
		return conseiller;
	}

	public void setConseiller(Conseiller conseiller) {
		this.conseiller = conseiller;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateAffectation() {
		return dateAffectation;
	}

	public void setDateAffectation(Date dateAffectation) {
		this.dateAffectation = dateAffectation;
	}

	public int getNumDemande() {
		return numDemande;
	}

	public void setNumDemande(int numDemande) {
		this.numDemande = numDemande;
	}
	// #endregion

	// #region Utilitaire

	@Override
	public String toString() {
		return "DemandeOuverture [numDemande=" + numDemande + ", cp=" + cp + ", valide=" + valide + ", dateCreation="
				+ dateCreation + ", dateAffectation=" + dateAffectation + "]";
	}

	// #endregion
}
