package com.wha.springmvc.dao;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.wha.springmvc.dao.AbstractDAO;
import com.wha.springmvc.dao.BanqueDAO;
import com.wha.springmvc.model.Compte;
import com.wha.springmvc.model.Conseiller;
import com.wha.springmvc.model.Notification;
import com.wha.springmvc.model.Requete;
import com.wha.springmvc.model.Transaction;

@Repository("BanqueDAO")
public class BanqueDAOImpl extends AbstractDAO<Long, Compte> implements BanqueDAO {

	@Override
	public Compte getCompteByNo(Long noCompte) {
		Compte compte = getByKey(noCompte);
		return compte;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Compte> getComptesByClient(Long idClient) {
		// List<Compte> comptes=getByKey(idClient);
		List<Compte> comptes = getEntityManager()
				.createQuery("SELECT c.listeComptes FROM Client c WHERE identifiant= :idClient")
				.setParameter("idClient", idClient).getResultList();
		return comptes;
	}

	@Override
	public void ajoutCompte(Compte compte, Long idClient) {
		persist(compte);
		// List<Compte> comptes=(List<Compte>) getByKey(idClient);
		
		getComptesByClient(idClient).add(compte);
		
	}

	@Override
	public void modificationCompte(Compte compte) {
		Compte compteup = getByKey(compte.getNoCompte());

		update(compteup);
	}

	@Override
	public List<Transaction> getAllTransactionsByCompte(Long noCompte) {
		// Compte compte=getByKey(noCompte);
		// List<Transaction> lt=getCompteByNo(noCompte).getListeTransactions();
		List<Transaction> lt = getEntityManager()
				.createQuery("SELECT c.listeTransactions FROM Compte c where c.noCompte= :noCompte")
				.setParameter("noCompte", noCompte).getResultList();
		return lt;
	}

	@Override
	public List<Transaction> getThatMonthTransactionsByCompte(Long noCompte, int thatMonth) {
		// Compte compte=getByKey(noCompte);
		List<Transaction> ltm = new ArrayList<>();
		for (Transaction t : getCompteByNo(noCompte).getListeTransactions()) {
			if (t.getDate().getMonth() == thatMonth) {
				ltm.add(t);
			}
		}
		return ltm;
	}

	@Override
	public Transaction getTransaction(Integer noTransaction) {
		Transaction transaction = getEntityManager()
				.createQuery("SELECT t FROM Transaction t WHERE t.noTransaction LIKE :noTransaction", Transaction.class)
				.setParameter("noTransaction", noTransaction).getSingleResult();
		return transaction;
	}

	@Override
	public boolean ajoutTransaction(Transaction transaction, Long noCompte) {
		Compte compte = getByKey(noCompte);
		if (compte.ajoutTransaction(transaction)) {
			persist(compte);
			return true;
		}
		return false;
	}

	@Override
	public boolean isCompteExist(Compte compte) {
		for (Compte c : getAllComptes()) {
			if (c.equals(compte))
				return true;
		}
		return false;
	}

	@Override
	public List<Compte> getAllComptes() {
		List<Compte> comptes = getEntityManager().createQuery("SELECT com FROM Compte com").getResultList();
		return comptes;
	}

	@Override
	public List<Notification> getAllNotificationsByCompte(Long noCompte) {
		List<Notification> lt = getEntityManager()
				.createQuery("SELECT c.listeNotification FROM Compte c WHERE c.noCompte LIKE :noCompte")
				.setParameter("noCompte", noCompte).getResultList();
		return lt;
	}

	@Override
	public void envoiRequete(Requete requete,Long matricule, Long noCompte){
		Compte compte = getCompteByNo(noCompte);
		requete.setCompte(compte);
		Conseiller conseiller=(Conseiller)getEntityManager().createQuery("SELECT cons FROM Conseiller cons where cons.matricule=:matricule")
				.setParameter("matricule", matricule).getSingleResult();
		Set<Requete> requeteConseiller=conseiller.getListeDemandesClient();

		requeteConseiller.add(requete);
	}

}
