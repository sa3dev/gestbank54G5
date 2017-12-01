package com.wha.springmvc.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;
import org.springframework.stereotype.Repository;

import com.wha.springmvc.dao.AbstractDAO;
import com.wha.springmvc.dao.UtilisateurDAO;
import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Compte;
import com.wha.springmvc.model.Conseiller;
import com.wha.springmvc.model.DemandeOuverture;
import com.wha.springmvc.model.Requete;
import com.wha.springmvc.model.Utilisateur;

@Repository("UtilisateurDao")
public class UtilisateurDAOImpl extends AbstractDAO<Long, Utilisateur> implements UtilisateurDAO {

	// ---------------ClientDAO------------------------------------
	@Override
	public Client findById(Long identifiant) {
		Client client = getEntityManager()
				.createQuery("SELECT c FROM Client c WHERE c.identifiant LIKE :identifiant", Client.class)
				.setParameter("identifiant", identifiant).getSingleResult();
		return client;
	}
	
	public Client findByCompte(Long noCompte){
		Client cli = getEntityManager()
				.createQuery("SELECT c FROM Client c WHERE "
						+ " (SELECT cpt FROM Compte cpt WHERE cpt.noCompte = :noCompte) "
						+ "MEMBER OF c.listeComptes", Client.class)
				.setParameter("noCompte", noCompte).getSingleResult();
		return cli;
		
	
		
		
		/*Client client = getEntityManager()
				.createQuery("SELECT c FROM Client c WHERE "
						+ " (SELECT cpt FROM Compte cpt WHERE cpt.noCompte LIKE :noCompte) "
						+ "MEMBER OF c.listeComptes", Client.class)
				.setParameter("noCompte", noCompte)
				.getSingleResult();
		return client;*/
	}

	public List<Client> findAllClients() {
		List<Client> clients = getEntityManager().createQuery("SELECT c FROM Client c", Client.class).getResultList();
		return clients;
	}

	@Override
	public List<Client> findAllClients(Long mle) {
		List<Client> clients = getEntityManager()
				.createQuery("SELECT c.listeClients FROM Conseiller c WHERE c.matricule LIKE :mle")
				.setParameter("mle", mle).getResultList();
		return clients;
	}

	@Override
	public void saveClient(Client client) {
		persist(client);
	}

	@Override
	public void updateClient(Client client) {
		update(client);
	}

	@Override
	public boolean isClientExist(Client client) {
		for (Client c : findAllClients()) {
			if (c.equals(client))
				return true;
		}
		return false;
	}

	// ---------------ConseillerDAO------------------------------------

	@Override
	public Conseiller findByMle(Long matricule) {
		Conseiller conseiller = getEntityManager()
				.createQuery("SELECT c FROM Conseiller c WHERE c.matricule LIKE :matricule", Conseiller.class)
				.setParameter("matricule", matricule).getSingleResult();

		return conseiller;
	}

	@Override
	public void saveConseiller(Conseiller conseiller) {
		persist(conseiller);
	}

	@Override
	public void updateConseiller(Conseiller conseiller) {
		update(conseiller);
	}

	@Override
	public List<Conseiller> findAllConseillers() {
		List<Conseiller> Conseillers = getEntityManager().createQuery("SELECT c FROM Conseiller c", Conseiller.class)
				.getResultList();

		return Conseillers;
	}

	@Override
	public boolean isConseillerExist(Conseiller conseiller) {
		for (Conseiller c : findAllConseillers()) {
			if (c.equals(conseiller))
				return true;
		}
		return false;
	}

	// ---------------UtilisateurDAO------------------------------------

	@Override
	public List<Utilisateur> findAllUtilisateurs() {
		List<Utilisateur> Utilisateurs = getEntityManager().createQuery("SELECT u FROM Utilisateur u").getResultList();
		return Utilisateurs;
	}

	public boolean isUtilisateurExist(Utilisateur utilisateur) {
		for (Utilisateur u : findAllUtilisateurs()) {
			if (u.equals(utilisateur))
				return true;
		}
		return false;
	}

	@Override
	public boolean isPseudoExist(String pseudo) {
		for (Utilisateur u : findAllUtilisateurs()) {
			if (u.getPseudo().equals(pseudo))
				return true;
		}
		return false;
	}

	@Override
	public Utilisateur getUtilisateurByPseudo(String pseudo) {
		System.out.println("pseudo: " + pseudo);
		try {
			Utilisateur utilisateur = (Utilisateur) getEntityManager()
					.createQuery("SELECT u FROM Utilisateur u WHERE u.pseudo LIKE :pseudo")
					.setParameter("pseudo", pseudo).getSingleResult();
			return utilisateur;
		} catch (NoResultException ex) {
			return null;
		}
	}

	// ---------------Demandes------------------------------------

	@Override
	public List<DemandeOuverture> findAllDemandes() {
		List<DemandeOuverture> demandeOuverture = getEntityManager().createQuery("SELECT do FROM DemandeOuverture do")
				.getResultList();
		return demandeOuverture;
	}

	@Override
	public List<DemandeOuverture> findDemandeByConseiller(Long matricule) {
		List<DemandeOuverture> DemandeOuvertures = getEntityManager()
				.createQuery("SELECT d FROM DemandeOuverture d WHERE d.conseiller.matricule LIKE :matricule")
				.setParameter("matricule", matricule).getResultList();
		return DemandeOuvertures;
	}

	@Override
	public void saveDemande(DemandeOuverture demandeOuverture) {
		entityManager.persist(demandeOuverture);
	}

	@Override
	public void updateDemande(DemandeOuverture demandeOuverture) {
		entityManager.merge(demandeOuverture);
	}

	@Override
	public void affectationOuverture(DemandeOuverture demandeOuverture, Long matricule) {
		Conseiller curentConseiller = findByMle(matricule);
		Set<DemandeOuverture> ls = curentConseiller.getListeDemandesOuverture();
		ls.add(demandeOuverture);
		demandeOuverture.setConseiller(curentConseiller);
		update(curentConseiller);
	}

	@Override
	public boolean isDemandeExist(DemandeOuverture ouverture) {
		// for (DemandeOuverture u : findAllDemandes()) {
		// if (u.equals(ouverture))
		// return true;
		// }
		// return false;
		DemandeOuverture dem = getEntityManager()
				.createQuery("SELECT d FROM DemandeOuverture d WHERE d.numDemande LIKE :numDemande",
						DemandeOuverture.class)
				.setParameter("numDemande", ouverture.getNumDemande()).getSingleResult();
		if (dem == null) {
			return false;
		}
		return true;
	}

	@Override
	public DemandeOuverture getDemandeByNum(int numDemande) {
		DemandeOuverture dem = null;
		dem = getEntityManager().createQuery("SELECT d FROM DemandeOuverture d WHERE d.numDemande LIKE :numDemande",
				DemandeOuverture.class).setParameter("numDemande", numDemande).getSingleResult();
		return dem;
	}

	// Autre --------------------------------------------------------------

	@Override
	public Long getMaxIdentifiant() {

		Long max = (Long) getEntityManager().createQuery("SELECT max(c.identifiant) FROM Client c").getSingleResult();
		if (max == null)
			max = 0L;
		return max;
	}

	@Override
	public Long getMaxNoCompte() {

		Long max;
		max = (Long) getEntityManager().createQuery("SELECT max(c.noCompte) FROM Compte c").getSingleResult();

		if (max == null)
			max = 0L;
		return max;
	}

	@Override
	public Integer getMaxNoTransact() {

		Integer max;
		max = (Integer) getEntityManager().createQuery("SELECT max(t.noTransaction) FROM Transaction t")
				.getSingleResult();
		if (max == null)
			max = 0;
		return max;
	}

	@Override
	public List<Requete> findRequeteByConseiller(Long matricule) {
		List<Requete> requetes = getEntityManager()
				.createQuery("SELECT c.listeDemandesClient FROM Conseiller c WHERE c.matricule=:matricule")
				.setParameter("matricule", matricule).getResultList();
		return requetes;
	}

	@Override
	public Requete getRequeteByNum(int numRequete) {
		Requete requete=(Requete) getEntityManager().createQuery("SELECT r FROM Requete r WHERE r.numRequete=:numRequete")
				.setParameter("numRequete", numRequete).getSingleResult();
		return requete;
	}

}