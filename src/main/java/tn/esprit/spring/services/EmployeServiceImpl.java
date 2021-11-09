package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class EmployeServiceImpl implements IEmployeService {

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;

	public long ajouterEmploye(Employe employe) {
		employeRepository.save(employe);
		return employe.getId();
	}

	public void mettreAjourEmailByEmployeId(String email, long employeId) {
		
		Optional e = employeRepository.findById(employeId);
		if (e.isPresent()){
			Employe employe = (Employe) e.get();
			employe.setEmail(email);
			employeRepository.save(employe);
		}
		

	}

	@Transactional	
	public void affecterEmployeADepartement(long employeId, int depId) {
		Optional depManagedEntityO = deptRepoistory.findById(depId);
		Optional employeManagedEntity0 = employeRepository.findById(employeId);
		if(employeManagedEntity0.isPresent()&& depManagedEntityO.isPresent()){
			Employe employeManagedEntity = (Employe) employeManagedEntity0.get();
			Departement depManagedEntity = (Departement) depManagedEntityO.get();
		if(depManagedEntity.getEmployes() == null){
			List<Employe> employes = new ArrayList<>();
			employes.add(employeManagedEntity);
			depManagedEntity.setEmployes(employes);
		}else{
			depManagedEntity.getEmployes().add(employeManagedEntity);

		}
		}

	}
	@Transactional
	public void desaffecterEmployeDuDepartement(long employeId, int depId)
	{
		Optional departement = deptRepoistory.findById(depId);
		if(departement.isPresent()){
			Departement dep = (Departement) departement.get();
			int employeNb = dep.getEmployes().size();
			for(int index = 0; index < employeNb; index++){
				if(dep.getEmployes().get(index).getId() == employeId){
					dep.getEmployes().remove(index);
					break;//a revoir
				}
			}
		}
		
	}

	public int ajouterContrat(Contrat contrat) {
		contratRepoistory.save(contrat);
		return contrat.getReference();
	}

	public void affecterContratAEmploye(int contratId, long employeId) {
		Optional contratManagedEntityO = contratRepoistory.findById(contratId);
		Optional employeManagedEntityO = employeRepository.findById(employeId);
		if(contratManagedEntityO.isPresent() && employeManagedEntityO.isPresent()){
			Employe employeManagedEntity = (Employe) employeManagedEntityO.get();
			Contrat contratManagedEntity = (Contrat) contratManagedEntityO.get();
			contratManagedEntity.setEmploye(employeManagedEntity);
			contratRepoistory.save(contratManagedEntity);
		}
		
		
	}

	public String getEmployePrenomById(long employeId) {
		Optional emp = employeRepository.findById(employeId);
		if(emp.isPresent()){
			Employe employeManagedEntity = (Employe) emp.get();
			return employeManagedEntity.getPrenom();
		}
		return null;
	}
	public void deleteEmployeById(long employeId)
	{
		Optional emp = employeRepository.findById(employeId);

		//Desaffecter l'employe de tous les departements
		//c'est le bout master qui permet de mettre a jour
		//la table d'association
		if(emp.isPresent()){
			Employe employe = (Employe) emp.get();
			for(Departement dep : employe.getDepartements()){
				dep.getEmployes().remove(employe);
			}

			employeRepository.delete(employe);	
		}
		
	}

	public void deleteContratById(int contratId) {
		Optional contratManagedEntity = contratRepoistory.findById(contratId);
		if(contratManagedEntity.isPresent()){
			Contrat c = (Contrat) contratManagedEntity.get();
			contratRepoistory.delete(c);
		}
		

	}

	public int getNombreEmployeJPQL() {
		return employeRepository.countemp();
	}
	
	public List<String> getAllEmployeNamesJPQL() {
		return employeRepository.employeNames();

	}
	
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, long employeId) {
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}
	public void deleteAllContratJPQL() {
         employeRepository.deleteAllContratJPQL();
	}
	
	public float getSalaireByEmployeIdJPQL(long employeId) {
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}
	
	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}

	public List<Employe> getAllEmployes() {
				return (List<Employe>) employeRepository.findAll();
	}

}
