

import static org.junit.Assert.assertEquals;

import java.util.List;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IEntrepriseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntrepriseServiceImplTest {

	@Autowired
	IEntrepriseService es;
	 int entId;
	public void TestAjouterEntreprise (){
		List<Entreprise> entreprises = es.getAllEntreprises();
		int expected = entreprises.size();
		System.out.printf("Expected number of Entreprises is:",+ expected);
		Entreprise e = new Entreprise();
		e.setName("EntrepriseTest");
		e.setRaisonSocial("RaisonSocialeTest");
		
		Entreprise savedEntreprise = es.ajouterEntreprise(e);
		System.out.printf("Expected number of Entreprises is:",+ expected+1);
		System.out.printf("Expected number of Entreprises is:",+ es.getAllEntreprises().size());
		assertEquals(expected+1, es.getAllEntreprises().size());
		es.deleteEntrepriseById(savedEntreprise.getId());
	}
	
	/*public void TestGetEntrpriseById (){
		try{es.getEntrepriseById(entId);}
		catch{TimeoutException = 5000}
		
	}*/
}
