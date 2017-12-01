package com.wha.springmvc.dao;

import java.util.List;

import com.wha.springmvc.model.Compte;
import com.wha.springmvc.model.Notification;
import com.wha.springmvc.model.Requete;
import com.wha.springmvc.model.Transaction;

public interface BanqueDAO {
	Compte getCompteByNo(Long noCompte);
	List<Compte> getComptesByClient(Long idClient);
	void ajoutCompte(Compte compte,Long idClient);
	void modificationCompte(Compte compte);
	boolean isCompteExist(Compte compte);
	
	List<Transaction> getAllTransactionsByCompte(Long noCompte);
	List<Transaction> getThatMonthTransactionsByCompte(Long noCompte, int thatMonth);
	Transaction getTransaction(Integer noTransaction);
	boolean ajoutTransaction(Transaction transaction, Long noCompte);
	List<Compte> getAllComptes();

	public List<Notification> getAllNotificationsByCompte(Long noCompte);
	void envoiRequete(Requete requete, Long matricule,Long noCompte);


}
