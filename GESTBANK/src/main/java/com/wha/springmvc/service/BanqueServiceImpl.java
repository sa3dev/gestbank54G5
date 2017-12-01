package com.wha.springmvc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wha.springmvc.dao.BanqueDAO;
import com.wha.springmvc.dao.UtilisateurDAO;
import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Compte;
import com.wha.springmvc.model.Notification;
import com.wha.springmvc.model.Requete;
import com.wha.springmvc.model.Transaction;
import com.wha.springmvc.model.Utilisateur;

@Service("banqueService")
@Transactional
public class BanqueServiceImpl implements BanqueService {

	/*
	 * // #region Compte private static List<Compte> comptes;
	 * 
	 * private static void getComptes() { if (comptes == null) {
	 * DummyBDD.CreateBDD(); comptes = DummyBDD.getComptes(); } }
	 */

	@Autowired
	private BanqueDAO dao;
	
	@Autowired
	private UtilisateurDAO userDao;

	@Override
	public Compte getCompteByNo(Long noCompte) {
		/*
		 * getComptes(); for (Compte compte : comptes) { if
		 * (compte.getNoCompte() == noCompte) return compte; } return null;
		 */
		return dao.getCompteByNo(noCompte);
	}

	@Override
	public List<Compte> getComptesByClient(Long clientIdentifiant) {
		/*
		 * getComptes(); Client c = null; for (Client client :
		 * DummyBDD.getClients()) { if (client.getIdentifiant() ==
		 * clientIdentifiant) { c = client; break; } } if (c != null) return
		 * c.getListeComptes(); return new ArrayList<Compte>();
		 */
		return dao.getComptesByClient(clientIdentifiant);
	}

	@Override
	public void ajoutCompte(Compte compte, Long clientIdentifiant) {
		/*
		 * getComptes(); Client c = null; for (Client client :
		 * DummyBDD.getClients()) { if (client.getIdentifiant() ==
		 * clientIdentifiant) { c = client; break; } } if (c != null) { Compte
		 * compteComplet = DummyBDD.ajoutCompte(compte); comptes =
		 * DummyBDD.getComptes(); c.ajoutCompte(compteComplet); }
		 */
		//recupération d'un utilisateur 
	//	dao.ajoutCompte(compte, clientIdentifiant);
		
		Client u = userDao.findById(clientIdentifiant);
		u.getListeComptes().add(compte);
		userDao.saveClient(u);
		
		
		
	}

	@Override
	public void modificationCompte(Compte compte) {
		/*
		 * getComptes(); int index = comptes.indexOf(compte); comptes.set(index,
		 * compte); DummyBDD.setComptes(comptes);
		 */
		Compte entity = dao.getCompteByNo(compte.getNoCompte());
		if (entity != null) {
			entity.setSolde(compte.getSolde());
			if (!entity.getDecouvert().equals(compte.getDecouvert())) {
				entity.setDecouvert(compte.getDecouvert());
				Notification notification = new Notification();
				notification.setDate(new Date());
				notification.setMessage(
						"Le Decouvert du compte " + entity.getNoCompte() + " est passé à " + entity.getDecouvert());
				notification.setLu(false);
				entity.ajoutNotification(notification);
			}
			if (!entity.getSeuilRemuneration().equals(compte.getSeuilRemuneration())) {
				entity.setSeuilRemuneration(compte.getSeuilRemuneration());
				Notification notification = new Notification();
				notification.setDate(new Date());
				notification.setMessage("Le seuil de remuneration du compte " + entity.getNoCompte() + " est passé à "
						+ entity.getSeuilRemuneration());
				notification.setLu(false);
				entity.ajoutNotification(notification);
			}
			entity.setMontantAgios(compte.getMontantAgios());
			entity.setSeuilRemuneration(compte.getSeuilRemuneration());
			entity.setRIB(compte.getRIB());
		}
	}

	@Override
	public boolean isCompteExist(Compte compte) {
		/*
		 * for (Compte c : comptes) { if (c.equals(compte)) return true; }
		 * return false;
		 */
		return dao.isCompteExist(compte);

	}

	// #endregion

	// #region Transaction

	/*
	 * private static List<Transaction> transactions;
	 * 
	 * private static void getTransactions() { if (transactions == null) {
	 * DummyBDD.CreateBDD(); transactions = DummyBDD.getTransactions(); } }
	 */

	@Override
	public List<Transaction> getAllTransactionsByCompte(Long noCompte) {
		/*
		 * getComptes(); List<List<Transaction>> liste = new ArrayList<>();
		 * Compte c = null; for (Compte compte : comptes) { if
		 * (compte.getNoCompte() == noCompte) { c = compte; } } if (c != null) {
		 * List<Transaction> debits = new ArrayList<Transaction>();
		 * List<Transaction> credits = new ArrayList<Transaction>(); for
		 * (Transaction t : c.getListeTransactions()) { if (t instanceof Credit)
		 * { credits.add(t); } else { debits.add(t); } } liste.add(credits);
		 * liste.add(debits); } return liste;
		 */
		return dao.getAllTransactionsByCompte(noCompte);
	}

	@Override
	public List<Transaction> getThatMonthTransactionsByCompte(Long noCompte, int thatMonth) {
		/*
		 * getComptes(); List<List<Transaction>> liste = new ArrayList<>();
		 * Compte c = getCompteByNo(noCompte); List<Transaction> debits = new
		 * ArrayList<Transaction>(); List<Transaction> credits = new
		 * ArrayList<Transaction>(); for (Transaction t :
		 * c.getListeTransactions()) { LocalDate ld =
		 * t.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		 * if (ld.getMonthValue() == thatMonth) { if (t instanceof Credit) {
		 * credits.add(t); } else { debits.add(t); } } } liste.add(credits);
		 * liste.add(debits); return liste;
		 */
		return dao.getThatMonthTransactionsByCompte(noCompte, thatMonth);
	}

	@Override
	public List<Notification> getAllNotificationsByClient(Long Identifiant) {
		List<Compte> lc = dao.getComptesByClient(Identifiant);
		List<Notification> ln = new ArrayList<>();
		for (Compte compte : lc) {
			ln.addAll(dao.getAllNotificationsByCompte(compte.getNoCompte()));
		}
		return ln;
	}

	@Override
	public boolean ajoutTransaction(Transaction transaction, Long noCompte) {
		if (dao.ajoutTransaction(transaction, noCompte)) {
			Notification notification = new Notification();
			if (transaction.getTypeTransaction().equals("debit")) {
				notification.setDate(new Date());
				notification.setMessage("Transaction n°" + transaction.getNoTransaction() + " : Un débit de $"
						+ transaction.getMontant() + " a été effectué à partir du compte n°" + noCompte);
				notification.setLu(false);
			} else {
				notification.setDate(new Date());
				notification.setMessage("Transaction n°" + transaction.getNoTransaction() + " : Votre compte "
						+ noCompte + " a été crédité de $" + transaction.getMontant());
				notification.setLu(false);
			}
			System.out.println(notification);
			dao.getCompteByNo(noCompte).ajoutNotification(notification);
			return true;
		}
		return false;
	}

	@Override
	public void envoiRequete(Requete requete, Long matricule, Long noCompte) {
		dao.envoiRequete(requete, matricule, noCompte);
	}

	// #endregion
}