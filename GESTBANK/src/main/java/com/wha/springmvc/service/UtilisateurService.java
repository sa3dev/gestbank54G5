package com.wha.springmvc.service;

import java.util.List;

import com.wha.springmvc.model.Client;

import com.wha.springmvc.model.Conseiller;
import com.wha.springmvc.model.DemandeOuverture;

import com.wha.springmvc.model.Requete;
import com.wha.springmvc.model.Utilisateur;

public interface UtilisateurService {
	
	//#region client

	Client findById(Long identifiant);

	Client findByCompte(Long noCompte);

	void saveClient(Client client);

	void updateClient(Client client);

	List<Client> findAllClients(Long mle);

	public boolean isClientExist(Client client);

	// void deleteAllClients();

	// void deleteClientById(String identifiant);
	
	//#endregion
	
	//#region Conseiller
	Conseiller findByMle(Long matricule);

	void saveConseiller(Conseiller conseiller);

	void updateConseiller(Conseiller conseiller);

	List<Conseiller> findAllConseillers();

	public boolean isConseillerExist(Conseiller conseiller);
	
	//#endregion 
	
	//#region Utilisateur
	public boolean isPseudoExist(String peudo);
	
	public Utilisateur getUtilisateurByPseudo(String pseudo);
	
	//#endregion

	//#region demande
	
	public List<DemandeOuverture> findAllDemandes();
	
	public List<DemandeOuverture> findDemandeByConseiller(Long matricule);
	
	public void saveDemande(DemandeOuverture demandeOuverture);
	
	public void affectionOuverture (DemandeOuverture demandeOuverture, Long matricule);
	
	public boolean isDemandeExist(DemandeOuverture ouverture);
	
	public DemandeOuverture getDemandeByNum(int numDemande);

	public void validationDemande(DemandeOuverture demande);
	
	
	//#endregion
	
	// #region Autre
	
	public void envoieMail(String sujet, String corpsMessage, String destinataire, String cc);

	public List<Requete> findRequeteByConseiller(Long matricule);

	public Requete getRequeteByNum(int numRequete);

	public void validationRequete(int numRequete);

	List<Client> findAllClients();
	
	// #endregion
}
