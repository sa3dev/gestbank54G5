package user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
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
import com.wha.springmvc.model.Compte;
import com.wha.springmvc.model.Conseiller;
import com.wha.springmvc.model.DemandeOuverture;
import com.wha.springmvc.model.Requete;
import com.wha.springmvc.model.Transaction;
import com.wha.springmvc.service.BanqueService;
import com.wha.springmvc.service.UtilisateurService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={HelloWorldConfiguration.class, JpaConfiguration.class})

public class BanqueServiceTest {
	
	@Autowired
	BanqueService bservice;
	
	@Autowired
	UtilisateurService uservice;
	
	@Transactional
	@Rollback(true)
	@Test
	public void ajoutCompteTest(){
		Client c = uservice.findById(1L);
		int nbcompte = c.getListeComptes().size();
		assertEquals(5, nbcompte);
		
		Compte cpt = new Compte(21L,0,0);
		bservice.ajoutCompte(cpt, c.getIdentifiant());
		
		nbcompte = c.getListeComptes().size();
		assertEquals(6, nbcompte);
		
				
//		List<DemandeOuverture> demandes = uservice.findAllDemandes();
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void ajoutTransactionTest(){
		
		Compte cp = bservice.getCompteByNo(22L);
		int nbTransaction = cp.getListeTransactions().size();
		assertEquals(12, nbTransaction);
		
		Transaction ts = new Transaction(20, "credit de 20", null,"credit" );
		bservice.ajoutTransaction(ts, cp.getNoCompte());
		
		nbTransaction = cp.getListeTransactions().size();
		assertEquals(13, nbTransaction);
		
		//ajoutTransaction(Transaction transaction, Long noCompte);
		//Transaction t = new Transaction(Integer montant, String libelle, Date date, String typeTransaction);
	}
	
	/*
	@Transactional
	@Test
	@Rollback(true)
	public void modificationCompteTest() {
	
		Compte currentCompte = bservice.getCompteByNo(22L);
		String rib = "01234";
				
		currentCompte.setRIB(rib);
		bservice.modificationCompte(currentCompte);	
		
		
		assertEquals(rib,  currentCompte.getRIB());
	//void modificationCompte(Compte compte); 
	}*/
	
	
	
}
