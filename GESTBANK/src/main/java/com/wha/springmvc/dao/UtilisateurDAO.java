package com.wha.springmvc.dao;

import java.util.List;

import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Compte;
import com.wha.springmvc.model.Conseiller;
import com.wha.springmvc.model.DemandeOuverture;
import com.wha.springmvc.model.Requete;
import com.wha.springmvc.model.Utilisateur;

public interface UtilisateurDAO {
	Client findById(Long identifiant);

	Client findByCompte(Long noCompte);

	List<Client> findAllClients(Long mle);

	void saveClient(Client client);

	void updateClient(Client client);

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
	
	public void updateDemande(DemandeOuverture demandeOuverture);
	
	public void affectationOuverture (DemandeOuverture demandeOuverture, Long matricule);
	
	public boolean isDemandeExist(DemandeOuverture ouverture);

	List<Utilisateur> findAllUtilisateurs();

	DemandeOuverture getDemandeByNum(int numDemande);

	Long getMaxIdentifiant();
	
	Long getMaxNoCompte();

	Integer getMaxNoTransact();

	List<Requete> findRequeteByConseiller(Long matricule);

	Requete getRequeteByNum(int numRequete);
	
	List<Client> findAllClients();

	//void validationRequete(Compte compte);

	//#endregion
}
