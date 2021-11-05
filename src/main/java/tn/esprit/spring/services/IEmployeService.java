package tn.esprit.spring.services;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;


public interface IEmployeService {
	
	public long ajouterEmploye(Employe employe);
	public void mettreAjourEmailByEmployeId(String email, long employeId);
	public void affecterEmployeADepartement(long employeId, int depId);
	public void desaffecterEmployeDuDepartement(long employeId, int depId);
	public int ajouterContrat(Contrat contrat);
	public void affecterContratAEmploye(int contratId, long employeId);
	public String getEmployePrenomById(long employeId);
	public void deleteEmployeById(long employeId);
	public void deleteContratById(int contratId);
	public int getNombreEmployeJPQL();
	public List<String> getAllEmployeNamesJPQL();
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise);
	public void mettreAjourEmailByEmployeIdJPQL(String email, long employeId);
	public void deleteAllContratJPQL();
	public float getSalaireByEmployeIdJPQL(long employeId);
	public Double getSalaireMoyenByDepartementId(int departementId);
	public List<Employe> getAllEmployes();
	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, 
	Date dateDebut, Date dateFin);
	
	
	

	
}
