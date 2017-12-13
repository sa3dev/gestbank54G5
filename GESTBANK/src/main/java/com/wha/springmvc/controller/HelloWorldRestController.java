package com.wha.springmvc.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.wha.springmvc.model.Administrateur;
import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.ClientPotentiel;
import com.wha.springmvc.model.Compte;
import com.wha.springmvc.model.Conseiller;
import com.wha.springmvc.model.DemandeOuverture;
import com.wha.springmvc.model.Notification;
import com.wha.springmvc.model.Requete;
import com.wha.springmvc.model.Transaction;
import com.wha.springmvc.model.User;
import com.wha.springmvc.model.Utilisateur;
import com.wha.springmvc.service.BanqueService;
import com.wha.springmvc.service.UserService;
import com.wha.springmvc.service.UtilisateurService;

@RestController
public class HelloWorldRestController {

	@Autowired
	UserService userService; // Service which will do all data
								// retrieval/manipulation work

	// #region Exemples
	// -------------------Retrieve All
	// Users--------------------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);// You
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// User--------------------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") long id) {
		System.out.println("Fetching User with id " + id);
		User user = userService.findById(id);
		if (user == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// -------------------Create a
	// User--------------------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating User " + user.getUsername());

		if (userService.isUserExist(user)) {
			System.out.println("A User with name " + user.getUsername() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a User
	// --------------------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		System.out.println("Updating User " + id);

		User currentUser = userService.findById(id);

		if (currentUser == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		currentUser.setUsername(user.getUsername());
		currentUser.setAddress(user.getAddress());
		currentUser.setEmail(user.getEmail());

		userService.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	// ------------------- Delete a User
	// --------------------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting User with id " + id);

		User user = userService.findById(id);
		if (user == null) {
			System.out.println("Unable to delete. User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Users
	// --------------------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers() {
		System.out.println("Deleting All Users");

		userService.deleteAllUsers();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	// #endregion

	// #region Utilisateur

	// #region Client

	@Autowired
	BanqueService banqueservice;

	@RequestMapping(value = "/account/{noCompte}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Compte> getCompteByNo(@PathVariable("noCompte") Long noCompte) {
		System.out.println("affichage compte : " + noCompte);
		Compte compte = banqueservice.getCompteByNo(noCompte);

		if (compte == null) {
			System.out.println("Il n'y a pas de compte existant pour le numero suivant " + noCompte);
			return new ResponseEntity<Compte>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Compte>(compte, HttpStatus.OK);
	}

	@RequestMapping(value = "/account/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Compte>> getComptesByClient(@PathVariable("id") Long id) {
		List<Compte> compte = banqueservice.getComptesByClient(id);
		if (compte.isEmpty()) {
			return new ResponseEntity<List<Compte>>(HttpStatus.NO_CONTENT);// You
		}
		return new ResponseEntity<List<Compte>>(compte, HttpStatus.OK);
	}

	@RequestMapping(value = "/account/user/{clientIdentifiant}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> ajoutCompte(@RequestBody Compte compte,
			@RequestBody @PathVariable("clientIdentifiant") Long clientIdentifiant, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating account " + compte.getNoCompte());

		if (banqueservice.isCompteExist(compte)) {
			System.out.println("le numero de compte : " + compte.getNoCompte() + " existe deja");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		banqueservice.ajoutCompte(compte, clientIdentifiant);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				ucBuilder.path("account/user/{clientIdentifiant}").buildAndExpand(compte.getNoCompte()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/user/account/{noCompte}", method = RequestMethod.PUT)
	public ResponseEntity<Compte> modificationCompte(@PathVariable("noCompte") Long noCompte, @RequestBody Compte c) {

		Compte currentCompte = banqueservice.getCompteByNo(noCompte);

		if (currentCompte == null) {
			System.out.println("Account " + currentCompte + " not found");
			return new ResponseEntity<Compte>(HttpStatus.NOT_FOUND);
		}

		currentCompte.setNoCompte(c.getNoCompte());
		currentCompte.setRIB(c.getRIB());
		currentCompte.setSolde(c.getSolde());
		currentCompte.setSeuilRemuneration(c.getSeuilRemuneration());
		currentCompte.setDecouvert(c.getDecouvert());
		currentCompte.setMontantAgios(c.getMontantAgios());
		currentCompte.setMontantRemuneration(c.getMontantRemuneration());

		banqueservice.modificationCompte(currentCompte);
		return new ResponseEntity<Compte>(currentCompte, HttpStatus.OK);
	}

	@RequestMapping(value = "/transaction/{noCompte}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Transaction>> getAllTransactionsByCompte(@PathVariable("noCompte") Long noCompte) {
		List<Transaction> transaction = banqueservice.getAllTransactionsByCompte(noCompte);
		if (transaction.isEmpty()) {
			return new ResponseEntity<List<Transaction>>(HttpStatus.NO_CONTENT);// You
		}
		return new ResponseEntity<List<Transaction>>(transaction, HttpStatus.OK);
	}

	@RequestMapping(value = "/account/{id}/{month}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Transaction>> getThatMonthTransactionsByCompte(@PathVariable("id") long id,
			@PathVariable("month") int month) {
		System.out.println("Fetching Account with id " + id);
		List<Transaction> list = banqueservice.getThatMonthTransactionsByCompte(id, month);
		if (list.isEmpty()) {
			System.out.println("Account with id " + id + " not found");
			return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Transaction>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/account/transaction/{noCompte}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> ajoutTransaction(@RequestBody Transaction transaction,
			@PathVariable("noCompte") Long noCompte, UriComponentsBuilder ucBuilder) {

		banqueservice.ajoutTransaction(transaction, noCompte);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("account/transaction/{noCompte}")
				.buildAndExpand(transaction.getNoTransaction()).toUri());
		return new ResponseEntity<Boolean>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "requete/account/{noCompte}/{matricule}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> envoiRequete(@RequestBody Requete requete,
			@RequestBody @PathVariable("matricule") Long matricule,
			@RequestBody @PathVariable("noCompte") Long noCompte, UriComponentsBuilder ucBuilder) {
		System.out.println("creation requete " + requete.getNumRequete());

		banqueservice.envoiRequete(requete, matricule, noCompte);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				ucBuilder.path("requete/account/{noCompte}").buildAndExpand(requete.getNumRequete()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/notification/{identifiant}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Notification>> getAllNotificationsByClient(
			@PathVariable("identifiant") long identifiant) {
		List<Notification> notification = banqueservice.getAllNotificationsByClient(identifiant);
		if (notification.isEmpty()) {
			return new ResponseEntity<List<Notification>>(HttpStatus.NO_CONTENT);// You
		}
		return new ResponseEntity<List<Notification>>(notification, HttpStatus.OK);
	}

	@Autowired
	UtilisateurService utilisateurservice;

	@RequestMapping(value = "/client/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Client> findById(@PathVariable("id") long identifiant) {
		System.out.println("Fetching Client with id " + identifiant);
		Client client = utilisateurservice.findById(identifiant);
		if (client == null) {
			System.out.println("Client with id " + identifiant + " not found");
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}

	@RequestMapping(value = "account/client/{noCompte}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Client> findByCompte(@PathVariable("noCompte") long noCompte) {

		System.out.println("Fetching Client with id " + noCompte);
		Client client = utilisateurservice.findByCompte(noCompte);

		if (client == null) {
			System.out.println("Client with id " + noCompte + " not found");
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}

	@RequestMapping(value = "/clients/{mattricule}", method = RequestMethod.GET)
	public ResponseEntity<List<Client>> findAllClients(@PathVariable("mattricule") long mle) {
		List<Client> clients = utilisateurservice.findAllClients(mle);
		if (clients.isEmpty()) {
			return new ResponseEntity<List<Client>>(HttpStatus.NO_CONTENT);// You
		}
		return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
	}

	@RequestMapping(value = "/clients/", method = RequestMethod.GET)
	public ResponseEntity<List<Client>> findAllClients() {
		List<Client> clients = utilisateurservice.findAllClients();
		if (clients.isEmpty()) {
			return new ResponseEntity<List<Client>>(HttpStatus.NO_CONTENT);// You
		}
		return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
	}

	@RequestMapping(value = "/requete/conseiller/{matricule}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Requete>> findRequeteByConseiller(@PathVariable("matricule") Long matricule) {
		List<Requete> requete = utilisateurservice.findRequeteByConseiller(matricule);
		if (requete.isEmpty()) {
			return new ResponseEntity<List<Requete>>(HttpStatus.NO_CONTENT);// You
		}
		return new ResponseEntity<List<Requete>>(requete, HttpStatus.OK);
	}

	@RequestMapping(value = "demande/ouverture/{matricule}/{numDemande}", method = RequestMethod.PUT)
	public ResponseEntity<DemandeOuverture> affectionOuverture(@RequestBody DemandeOuverture demandeOuverture,
			@PathVariable("matricule") Long matricule, @PathVariable("numDemande") int numDemande) {

		DemandeOuverture currentDemande = utilisateurservice.getDemandeByNum(numDemande);

		if (currentDemande == null) {
			System.out.println("Account " + currentDemande + " not found");
			return new ResponseEntity<DemandeOuverture>(HttpStatus.NOT_FOUND);
		}

		currentDemande.setConseiller(demandeOuverture.getConseiller());
		currentDemande.setDateAffectation(demandeOuverture.getDateAffectation());

		utilisateurservice.affectionOuverture(currentDemande, matricule);
		return new ResponseEntity<DemandeOuverture>(currentDemande, HttpStatus.OK);
	}

	@RequestMapping(value = "/conseillers/{id}", method = RequestMethod.GET)
	public ResponseEntity<Conseiller> findByMle(@PathVariable("id") Long matricule) {

		Conseiller conseiller = utilisateurservice.findByMle(matricule);

		if (utilisateurservice.isConseillerExist(conseiller)) {
			return new ResponseEntity<Conseiller>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Conseiller>(conseiller, HttpStatus.OK);

	}

	@RequestMapping(value = "/conseiller/", method = RequestMethod.POST)
	public ResponseEntity<Conseiller> saveConseiller(@RequestBody Conseiller conseiller,
			UriComponentsBuilder ucBuilder) {
		System.out.println("Creating consiller " + conseiller.getNom());

		if (conseiller == null) {
			System.out.println("A conseiller with name " + conseiller.getNom() + " already exist");
			return new ResponseEntity<Conseiller>(HttpStatus.CONFLICT);
		}

		utilisateurservice.saveConseiller(conseiller);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/conseiller/{id}").buildAndExpand(conseiller.getMatricule()).toUri());
		return new ResponseEntity<Conseiller>(conseiller, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/conseiller/{mle}", method = RequestMethod.PUT)
	public ResponseEntity<Conseiller> updateConseiller(@PathVariable("mle") Long mle,
			@RequestBody Conseiller conseiller) {

		Conseiller currentConseiller = utilisateurservice.findByMle(mle);

		if (currentConseiller == null) {
			System.out.println("Account " + currentConseiller + " not found");
			return new ResponseEntity<Conseiller>(HttpStatus.NOT_FOUND);
		}

		currentConseiller.setAdresse(conseiller.getAdresse());
		currentConseiller.setCodePostal(conseiller.getCodePostal());
		currentConseiller.setVille(conseiller.getVille());
		currentConseiller.setEmail(conseiller.getEmail());
		currentConseiller.setTelephone(conseiller.getTelephone());
		currentConseiller.setMotdepasse(conseiller.getMotdepasse());
		currentConseiller.setPseudo(conseiller.getPseudo());
		currentConseiller.setDateNaissance(conseiller.getDateNaissance());
		currentConseiller.setNom(conseiller.getNom());
		currentConseiller.setPrenom(conseiller.getPrenom());
		currentConseiller.setMotdepasse(conseiller.getMotdepasse());

		utilisateurservice.updateConseiller(currentConseiller);
		System.out.println("Conseiller Mis a jour ");
		return new ResponseEntity<Conseiller>(currentConseiller, HttpStatus.OK);
	}

	@RequestMapping(value = "/conseillers/", method = RequestMethod.GET)
	public ResponseEntity<List<Conseiller>> findAllConseillers() {
		List<Conseiller> all = utilisateurservice.findAllConseillers();

		if (all.isEmpty()) {
			return new ResponseEntity<List<Conseiller>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Conseiller>>(all, HttpStatus.OK);

	}

	@RequestMapping(value = "/user/pseudo/{pseudo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Utilisateur> getUtilisateurByPseudo(@PathVariable("pseudo") String pseudo) {
		System.out.println("Fetching Clients follow by pseudo " + pseudo);
		Utilisateur u = utilisateurservice.getUtilisateurByPseudo(pseudo);

		if (!utilisateurservice.isPseudoExist(pseudo)) {
			System.out.println("Pseudo " + pseudo + " not found");
			return new ResponseEntity<Utilisateur>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Utilisateur>(u, HttpStatus.OK);
	}

	@RequestMapping(value = "/demands/", method = RequestMethod.GET)
	public ResponseEntity<List<DemandeOuverture>> findAllDemands() {
		List<DemandeOuverture> demands = utilisateurservice.findAllDemandes();
		System.out.println(demands);
		if (demands.isEmpty()) {
			return new ResponseEntity<List<DemandeOuverture>>(HttpStatus.NO_CONTENT);// You
		}
		return new ResponseEntity<List<DemandeOuverture>>(demands, HttpStatus.OK);
	}

	@RequestMapping(value = "/conseillers/{id}/demands", method = RequestMethod.GET)
	public ResponseEntity<List<DemandeOuverture>> findDemandeByConseiller(@PathVariable("id") Long matricule) {

		Conseiller conseiller = utilisateurservice.findByMle(matricule);
		List<DemandeOuverture> demands = utilisateurservice.findDemandeByConseiller(matricule);

		if (utilisateurservice.isConseillerExist(conseiller)) {

			return new ResponseEntity<List<DemandeOuverture>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<DemandeOuverture>>(demands, HttpStatus.OK);

	}

	@RequestMapping(value = "/demand/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DemandeOuverture> getDemandeByNum(@PathVariable("id") int id) {
		System.out.println("Fetching Clients follow by id " + id);
		DemandeOuverture demande = utilisateurservice.getDemandeByNum(id);
		if (demande == null) {
			System.out.println("Request with id " + id + " not found");
			return new ResponseEntity<DemandeOuverture>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<DemandeOuverture>(demande, HttpStatus.OK);
	}

	@RequestMapping(value = "/requete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Requete> getRequeteByNum(@PathVariable("id") int id) {
		System.out.println("Fetching Clients follow by id " + id);
		Requete req = utilisateurservice.getRequeteByNum(id);
		if (req == null) {
			System.out.println("Request with id " + id + " not found");
			return new ResponseEntity<Requete>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Requete>(req, HttpStatus.OK);
	}

	/*
	 * @RequestMapping(value = "/demand/", method = RequestMethod.POST) public
	 * ResponseEntity<Void> DemandeOuverture(@RequestBody ClientPotentiel c,
	 * UriComponentsBuilder ucBuilder) throws ParseException {
	 * System.out.println("Creating DemandeOuverture pour " + c.getNom()); Date
	 * myDate = new SimpleDateFormat("yyyy-MM-dd").parse("2014-02-14");
	 * DemandeOuverture demande = new DemandeOuverture(c, false , myDate, myDate);
	 * utilisateurservice.saveDemande(demande);
	 * 
	 * HttpHeaders headers = new HttpHeaders();
	 * headers.setLocation(ucBuilder.path("/demand/{id}").buildAndExpand(demande.
	 * getNumDemande()).toUri()); return new ResponseEntity<Void>(headers,
	 * HttpStatus.CREATED); }
	 */

	@RequestMapping(value = "/demand/", method = RequestMethod.POST)
	public ResponseEntity<Void> DemandeOuverture(@RequestBody DemandeOuverture dm, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Demand " + dm.getNumDemande());

		utilisateurservice.saveDemande(dm);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/demand/{id}").buildAndExpand(dm.getNumDemande()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/client/", method = RequestMethod.POST)
	public ResponseEntity<Void> createClient(@RequestBody Client cl, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating User " + cl.getIdentifiant());

		if (utilisateurservice.isClientExist(cl)) {
			System.out.println("A User with name " + cl.getIdentifiant() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		utilisateurservice.saveClient(cl);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/client/{id}").buildAndExpand(cl.getIdentifiant()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/client/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Client> updateClient(@PathVariable("id") long id, @RequestBody Client cl) {
		System.out.println("Updating User " + id);

		Client currentClient = utilisateurservice.findById(id);

		if (currentClient == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		}

		currentClient.setEmail(cl.getEmail());
		currentClient.setCodePostal(cl.getCodePostal());
		currentClient.setEmail(cl.getAdresse());
		currentClient.setMotdepasse(cl.getMotdepasse());
		currentClient.setTelephone(cl.getTelephone());
		currentClient.setVille(cl.getVille());

		utilisateurservice.updateClient(currentClient);
		return new ResponseEntity<Client>(currentClient, HttpStatus.OK);
	}

	/*
	 * 
	 * @RequestMapping(value = "/demand/", method = RequestMethod.POST) public
	 * ResponseEntity<Void> DemandeOuverture(@RequestBody DemandeOuverture demande,
	 * UriComponentsBuilder ucBuilder) throws ParseException {
	 * System.out.println("Creating demande " + demande.getNumDemande());
	 * 
	 * 
	 * Date myDate = new SimpleDateFormat("yyyy-MM-dd").parse("2014-02-14");
	 * demande.setDateCreation(myDate); utilisateurservice.saveDemande(demande);
	 * 
	 * HttpHeaders headers = new HttpHeaders();
	 * headers.setLocation(ucBuilder.path("/demand/{id}").buildAndExpand(demande.
	 * getNumDemande()).toUri()); return new ResponseEntity<Void>(headers,
	 * HttpStatus.CREATED); }
	 */

	/*
	 * public void validationDemande(DemandeOuverture demande); public void
	 * envoieMail(String sujet, String corpsMessage, String destinataire, String
	 * cc); public void validationRequete(int numRequete);
	 */
	/*
	 * @RequestMapping(value =
	 * "validation/demande/ouverture/{nbValidation}/{numDemande}", method =
	 * RequestMethod.PUT) public ResponseEntity<DemandeOuverture>
	 * validationDemande(@RequestBody DemandeOuverture
	 * demandeOuverture, @PathVariable("nbValidation") boolean
	 * nbValidation, @PathVariable("numDemande") int numDemande) {
	 * 
	 * 
	 * DemandeOuverture currentDemande =
	 * utilisateurservice.getDemandeByNum(numDemande);
	 * 
	 * if (currentDemande == null) { System.out.println("Account " + currentDemande
	 * + " not found"); return new
	 * ResponseEntity<DemandeOuverture>(HttpStatus.NOT_FOUND); }
	 * 
	 * 
	 * currentDemande.setValide(demandeOuverture.isValide());
	 * 
	 * 
	 * 
	 * utilisateurservice.validationDemande(currentDemande); return new
	 * ResponseEntity<DemandeOuverture>(currentDemande, HttpStatus.OK); }
	 */
	/**
	 * 
	 * @param params: prend 2 valeur params[0]=pseudo params[1]=password
	 * @param ucBuilder
	 * @return
	 * @throws URISyntaxException 
	 */
	
	
	@RequestMapping(value = "/connexion/", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> connexion(@RequestBody List<String> params, UriComponentsBuilder ucBuilder) throws URISyntaxException {
		System.out.println("Connexion User " + params.get(0));
		
		//URI locationAngular = new URI("http://localhost:4200");
		//ucBuilder.path("http://localhost:4200");
			
		//HttpHeaders headers = new HttpHeaders();

		Utilisateur u = utilisateurservice.getUtilisateurByPseudo(params.get(0));
		
		String typeUser = "" ;
		
		
		if (u.getMotdepasse().equals(params.get(1))) {
			//headers.setLocation(locationAngular);
			
			if (u instanceof Administrateur) {
				
				typeUser = "Administrateur";
				
			//	headers.setLocation(ucBuilder.path("/admin/{pseudo}").buildAndExpand(u.getPseudo()).toUri());			

			} else if (u instanceof Conseiller) {
				
				typeUser = "Conseiller";
			//	headers.setLocation(ucBuilder.path("/conseiller/{pseudo}").buildAndExpand(u.getPseudo()).toUri());

			} else if (u instanceof Client) {
				
				typeUser = "Client";
			//	headers.setLocation(ucBuilder.path("/client/{pseudo}").buildAndExpand(u.getPseudo()).toUri());
			}
		}else {
			//headers.setLocation(ucBuilder.path("/").buildAndExpand(u.getPseudo()).toUri());
			typeUser = "erreur";
			return new ResponseEntity<String>(typeUser, HttpStatus.FORBIDDEN);
		}

		return new ResponseEntity<String>(typeUser, HttpStatus.CREATED);
	}
	
	
}
