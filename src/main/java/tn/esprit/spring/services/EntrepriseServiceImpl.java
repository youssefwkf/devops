package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	public int ajouterEntreprise(Entreprise entreprise) {
		entrepriseRepoistory.save(entreprise);
		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		deptRepoistory.save(dep);
		return dep.getId();
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		//Le bout Master de cette relation N:1 est departement  
				//donc il faut rajouter l'entreprise a departement 
				// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
				//Rappel : la classe qui contient mappedBy represente le bout Slave
				//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
				Optional entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId);
				Optional depManagedEntity = deptRepoistory.findById(depId);
				if(depManagedEntity.isPresent() && entrepriseManagedEntity.isPresent()){
					Entreprise entreprise = (Entreprise) entrepriseManagedEntity.get();
					Departement dep = (Departement) depManagedEntity.get();
					dep.setEntreprise(entreprise);
					deptRepoistory.save(dep);	
				}
				
		
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		
		List<String> depNames = new ArrayList<>();
		Optional e =entrepriseRepoistory.findById(entrepriseId);
		if(e.isPresent()){
			Entreprise entrepriseManagedEntity = (Entreprise) e.get();
			for(Departement dep : entrepriseManagedEntity.getDepartements()){
				depNames.add(dep.getName());
			}	
		}
		return depNames;
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		Optional e = entrepriseRepoistory.findById(entrepriseId);
		if(e.isPresent()){
			Entreprise entreprise = (Entreprise) e.get();
			entrepriseRepoistory.delete(entreprise);	
		}
		
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		Optional dep = deptRepoistory.findById(depId);
		if(dep.isPresent()){
			Departement departement = (Departement) dep.get();
			deptRepoistory.delete(departement);
		}
			
	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		Optional e = entrepriseRepoistory.findById(entrepriseId);
		if(e.isPresent()){
			
			return (Entreprise) e.get();
		}
		return null;	
	}

}
