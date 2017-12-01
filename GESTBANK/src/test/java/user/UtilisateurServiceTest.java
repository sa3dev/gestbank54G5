package user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;







import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.wha.springmvc.configuration.HelloWorldConfiguration;
import com.wha.springmvc.configuration.JpaConfiguration;
import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.ClientPotentiel;
import com.wha.springmvc.model.Conseiller;
import com.wha.springmvc.model.DemandeOuverture;
import com.wha.springmvc.service.UtilisateurService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={HelloWorldConfiguration.class, JpaConfiguration.class})

public class UtilisateurServiceTest {
	
	@Autowired
	UtilisateurService uservice;
	
	@Test
	public void findAllDemandesTest(){
		List<DemandeOuverture> demandes = uservice.findAllDemandes();
		assertEquals(11, demandes.size());
	}
	
	@Test
	public void rechercheUsers(){
		Client c = uservice.findById(2L);
		assertNotNull(c);
		
	}
	
	@Test
	public void findByComptetest(){
		long rib = new Long(22);
		String pseudo = "MDavis";
		
		Client cl=uservice.findByCompte(rib);
		assertEquals(pseudo, cl.getPseudo());
	}

	@Transactional
	@Rollback(true)
	@Test
	public void saveDemandeTest(){
		ClientPotentiel cp = new ClientPotentiel("test", "test", "test", "zizou@gmail.com", "98 rue de la win", 59119, "waziers", 0662611167, 50000, null);
		DemandeOuverture demande = new DemandeOuverture(cp, false, null, null);
		uservice.saveDemande(demande);
		int nbClientpo = uservice.findAllDemandes().size();  
		assertEquals(12, nbClientpo);
		//public void saveDemande(DemandeOuverture demandeOuverture);
	}
	
	@Test
	public void findByMleTest(){
		long mle = new Long(2);
		String pseudo = "FRober";
		
		Conseiller cl = uservice.findByMle(mle);
		assertEquals(pseudo, cl.getPseudo());
				
		//Conseiller findByMle(Long matricule);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void affectationOuvertureTest(){
		DemandeOuverture demande = uservice.getDemandeByNum(10);
		long mle = new Long(2);
		Conseiller cl = uservice.findByMle(2L);
		uservice.affectionOuverture(demande, mle);
		assertEquals(cl, demande.getConseiller());
		//public void affectationOuverture(DemandeOuverture demandeOuverture, Long matricule);
	}
	
	@Test
	public void findAllClientsTest(){
		int nbClient = uservice.findAllClients().size();
		assertEquals(8, nbClient);
		System.out.println(uservice.findAllClients());
		//List<Client>> findClients() ;
	}
	
	@Transactional
	@Test
	@Rollback(true)
	public void updateClientTest(){
		
		String adr = "10 rue de la paix";
		String mail = "greg@gmail.com";
		Client cl = uservice.findById(8L);
		
		cl.setAdresse(adr);
		cl.setEmail(mail);
		
		uservice.updateClient(cl);
		
		assertEquals(mail, cl.getEmail());
		assertEquals(adr, cl.getAdresse());
		//void updateClient(Client client); 
	}
	
	@Transactional
	@Test
	@Rollback(true)
	public void updateConseillerTest() {
		String old_adr = "qq part";
		String adr = "new adress";
		
		Conseiller c = uservice.findByMle(4L);
		assertEquals(old_adr,  c.getAdresse());
		
		c.setAdresse(adr);
		uservice.updateConseiller(c);
		c = uservice.findByMle(4L);
		assertEquals(adr,  c.getAdresse());
	}

	@Transactional
	@Test
	@Rollback(true)
	public void saveConseillerTest() {
		Conseiller cons = new Conseiller(5L, "test", "test", "test", "test", "test@test", "10 test", 59119, "waziers", 0662611167, null);
		uservice.saveConseiller(cons);
		int nbCons = uservice.findAllConseillers().size();
		assertEquals(5, nbCons);
	}
	
}
