package com.wha.springmvc.dummy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.ClientPotentiel;
import com.wha.springmvc.model.Compte;
import com.wha.springmvc.model.Conseiller;
import com.wha.springmvc.model.DemandeOuverture;
import com.wha.springmvc.model.Transaction;
import com.wha.springmvc.model.Utilisateur;

public class DummyBDD {

	private static final AtomicLong counterClient = new AtomicLong();
	private static final AtomicLong counterConseiller = new AtomicLong();
	private static final AtomicLong counterCompte = new AtomicLong();

	private static List<Conseiller> conseillers;
	private static List<Client> clients;
	private static List<Compte> comptes;
	private static List<Utilisateur> utilisateurs;
	private static List<Transaction> transactions;
	private static List<DemandeOuverture> demandes;

	public static void CreateBDD() {
		if (clients == null || conseillers == null || comptes == null || transactions == null || demandes == null) {
			setClients(populateDummyClients());
			setConseillers(populateDummyConseillers());
			setComptes(populateDummyComptes());
			setTransactions(populateDummyTransaction());
			setDemandes(populateDummyOuverture());
			populateUtilisateurs();
		//affectationConseiller();
			affectationCompte();
			transactionsCompte();
		}

	}

	private static List<Conseiller> populateDummyConseillers() {
		List<Conseiller> liste = new ArrayList<Conseiller>();
		liste.add(new Conseiller(counterConseiller.incrementAndGet(), "Michel", "Jeanne", "JMichel", "JMichel",
				"jmichel@gest-bank.ky", null, null, null, null, null));
		liste.add(new Conseiller(counterConseiller.incrementAndGet(), "Rober", "Frank", "FRober", "FRober",
				"frober@gest-bank.ky", null, null, null, null, null));
		liste.add(new Conseiller(counterConseiller.incrementAndGet(), "Durant", "Jacques", "JDurant", "JDurant",
				"jdurant@gest-bank.ky", null, null, null, null, null));
		return liste;
	}

	private static List<Client> populateDummyClients() {
		List<Client> liste = new ArrayList<Client>();
		liste.add(new Client(counterClient.incrementAndGet(), "Davis", "Mark", "MDavis", "MDavis",
				"mark.davis@b-mail.ky", null, null, null, null, null));
		liste.add(new Client(counterClient.incrementAndGet(), "Davis", "Helen", "HDavis", "HDavis",
				"helen.davis@b-mail.ky", null, null, null, null, null));
		liste.add(new Client(counterClient.incrementAndGet(), "Damon", "Barn", "BDamon", "BDamon",
				"barn.damon@b-mail.ky", null, null, null, null, null));
		liste.add(new Client(counterClient.incrementAndGet(), "Thomas", "William", "WThomas", "WThomas",
				"will.thoms@a-mail.ky", null, null, null, null, null));
		liste.add(new Client(counterClient.incrementAndGet(), "Wong", "Mickael", "MWong", "MWong",
				"mickael.wong2@c-mail.ky", null, null, null, null, null));
		liste.add(new Client(counterClient.incrementAndGet(), "Davis", "Chris", "CDavis", "CDavis",
				"chris.davis@y-mail.ky", null, null, null, null, null));
		liste.add(new Client(counterClient.incrementAndGet(), "Gibbs", "Anthony", "AGibbs", "AGibbs",
				"antonio.gibbs@n-mail.ky", null, null, null, null, null));
		return liste;
	}

	private static List<Compte> populateDummyComptes() {
		List<Compte> liste = new ArrayList<Compte>();
		liste.add(new Compte(counterCompte.incrementAndGet(), 0, 0));
		liste.add(new Compte(counterCompte.incrementAndGet(), 500, 0));
		liste.add(new Compte(counterCompte.incrementAndGet(), 200, 0));
		liste.add(new Compte(counterCompte.incrementAndGet(), 100, 10000));
		liste.add(new Compte(counterCompte.incrementAndGet(), 0, 5000));
		liste.add(new Compte(counterCompte.incrementAndGet(), 0, 0));
		liste.add(new Compte(counterCompte.incrementAndGet(), 500, 5000));
		liste.add(new Compte(counterCompte.incrementAndGet(), 0, 0));
		liste.add(new Compte(counterCompte.incrementAndGet(), 600, 7000));
		liste.add(new Compte(counterCompte.incrementAndGet(), 0, 0));
		return liste;
	}

	private static List<Transaction> populateDummyTransaction() {
		List<Transaction> liste = new ArrayList<Transaction>();
		Random rand = new Random();
		/*for (int i = 0; i < 2000; i++) {
			Date date = new Date();
			date.setDate(date.getDate() - rand.nextInt(365));
			if (rand.nextBoolean()) {
				int montant = rand.nextInt(1200);
				String libelle = "Debit d'un montant de : " + montant;
				liste.add(new Debit(montant, libelle, date));
			} else {
				int montant = rand.nextInt(1200);
				String libelle = "Credit d'un montant de : " + montant;
				liste.add(new Credit(montant, libelle, date));
			}
		}*/
		return liste;
	}

	private static List<DemandeOuverture> populateDummyOuverture() {
		List<DemandeOuverture> liste = new ArrayList<DemandeOuverture>();
		Random rand = new Random();
		Date date = new Date();
		date.setDate(date.getDate() - rand.nextInt(15));
		/*liste.add(new DemandeOuverture(new ClientPotentiel("Sheppard", "John", "JSheppard", "john.sheppard@a-mail.com",
				null, null, null, null, null, null), false, null, date, null));
		date = new Date();
		date.setDate(date.getDate() - rand.nextInt(15));
		liste.add(new DemandeOuverture(new ClientPotentiel("Halliwell", "Andy", "AHalliwell",
				"andy.halliwell@w-mail.com", null, null, null, null, null, null), false, null, date, null));
		date = new Date();
		date.setDate(date.getDate() - rand.nextInt(15));
		liste.add(new DemandeOuverture(new ClientPotentiel("Beckett", "Martha", "MBeckett", "martha.beckett@c-mail.com",
				null, null, null, null, null, null), false, null, date, null));
		date = new Date();
		date.setDate(date.getDate() - rand.nextInt(15));
		liste.add(new DemandeOuverture(new ClientPotentiel("Hogden", "William", "WHogden", "will.hogden@m-mail.com",
				null, null, null, null, null, null), false, null, date, null));*/
		return liste;
	}

	/*private static void affectationConseiller() {
		int nbCons = conseillers.size();

		for (int i = 0; i < nbCons; i++) {
			for (int j = i; j < clients.size(); j = j + nbCons) {
				clients.get(j).setConseiller(conseillers.get(i));
				//conseillers.get(i).ajoutClient(clients.get(j));
			}
		}

	}*/

	/*
	 * private static void affectationCompte() { int nbClient = clients.size();
	 * 
	 * for(int i=0; i< nbClient; i++){ for(int j=i; j< comptes.size();
	 * j=j+nbClient){ if (comptes.get(j)!=null){
	 * clients.get(i).ajoutCompte(comptes.get(j)); } } }
	 * 
	 * }
	 */

	private static void affectationCompte() {
		int nbClient = clients.size();
		List<Compte> copyComptes = new ArrayList<Compte>(comptes);
		Random rand = new Random();
		int n_a;
		for (int i = 0; i < nbClient; i++) {
			for (int j = i; j < comptes.size(); j = j + nbClient) {
				n_a = rand.nextInt(copyComptes.size());
				clients.get(i).ajoutCompte(copyComptes.remove(n_a));
			}
		}
	}

	private static void transactionsCompte() {
		int nbCompte = comptes.size();
		List<Transaction> copyTransactions = new ArrayList<Transaction>(transactions);
		Random rand = new Random();
		int n_a;
		Transaction t;
		for (int i = 0; i < nbCompte; i++) {
			for (int j = i; j < transactions.size(); j = j + nbCompte) {
				n_a = rand.nextInt(copyTransactions.size());
				t = copyTransactions.get(n_a);
				if (comptes.get(i).ajoutTransaction(t)) {
					copyTransactions.remove(n_a);
				}
			}
		}
	}

	private static void populateUtilisateurs() {
		utilisateurs = new ArrayList<Utilisateur>();
		for (Client client : clients) {
			utilisateurs.add(client);
		}
		for (Conseiller conseiller : conseillers) {
			utilisateurs.add(conseiller);
		}
	}

	public static List<Conseiller> getConseillers() {
		return conseillers;
	}

	public static void setConseillers(List<Conseiller> conseillers) {
		DummyBDD.conseillers = conseillers;
	}

	public static Conseiller ajoutConseiller(Conseiller conseiller) {
		conseiller.setMatricule(counterConseiller.incrementAndGet());
		DummyBDD.conseillers.add(conseiller);
		DummyBDD.utilisateurs.add(conseiller);
		return conseiller;
	}

	public static List<Client> getClients() {
		return clients;
	}

	public static void setClients(List<Client> clients) {
		DummyBDD.clients = clients;
	}

	public static Client ajoutClient(Client client) {
		client.setIdentifiant(counterClient.incrementAndGet());
		DummyBDD.clients.add(client);
		DummyBDD.utilisateurs.add(client);
		return client;
	}

	public static List<Compte> getComptes() {
		return comptes;
	}

	public static void setComptes(List<Compte> comptes) {
		DummyBDD.comptes = comptes;
	}

	public static Compte ajoutCompte(Compte compte) {
		compte.setNoCompte(counterCompte.incrementAndGet());
		DummyBDD.comptes.add(compte);
		return compte;
	}

	public static List<Transaction> getTransactions() {
		return transactions;
	}

	public static void setTransactions(List<Transaction> transactions) {
		DummyBDD.transactions = transactions;
	}

	public static Transaction ajoutTransaction(Transaction transaction) {
		DummyBDD.transactions.add(transaction);
		return transaction;
	}

	public static List<DemandeOuverture> getDemandes() {
		return demandes;
	}

	public static void setDemandes(List<DemandeOuverture> demandes) {
		DummyBDD.demandes = demandes;
	}
	
	public static void ajoutDemande(DemandeOuverture demande){
		demande.setDateCreation(new Date());
		DummyBDD.demandes.add(demande);
	}

	public static List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public static void setUtilisateurs(List<Utilisateur> utilisateurs) {
		DummyBDD.utilisateurs = utilisateurs;
	}

}
