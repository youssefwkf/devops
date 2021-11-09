package tn.esprit.spring.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class TimesheetServiceImpl implements ITimesheetService {
	

	@Autowired
	MissionRepository missionRepository ;
	
	@Autowired
	DepartementRepository deptRepoistory;
	
	@Autowired
	TimesheetRepository timesheetRepository;
	
	@Autowired
	EmployeRepository employeRepository;
	
	private static final Logger l = LogManager.getLogger(TimesheetServiceImpl.class);
	
	public int ajouterMission(Mission mission) {
		missionRepository.save(mission);
		return mission.getId();
	}
    
	public void affecterMissionADepartement(int missionId, int depId) {
		Optional miss = missionRepository.findById(missionId);
		Optional dep = deptRepoistory.findById(depId);
		if(miss.isPresent() && dep.isPresent()){
			Mission mission = (Mission) miss.get();
			Departement departement = (Departement) dep.get();
			mission.setDepartement(departement);
			missionRepository.save(mission);
		}
		
		
	}

	public Timesheet ajouterTimesheet(int missionId, long employeId, Date dateDebut, Date dateFin) {
		TimesheetPK timesheetPK = new TimesheetPK();
		timesheetPK.setDateDebut(dateDebut);
		timesheetPK.setDateFin(dateFin);
		timesheetPK.setIdEmploye(employeId);
		timesheetPK.setIdMission(missionId);
		
		Timesheet timesheet = new Timesheet();
		
		timesheet.setTimesheetPK(timesheetPK);
		timesheet.setValide(false); //par defaut non valide
		return timesheetRepository.save(timesheet);
		
	}

	
	public void validerTimesheet(int missionId, long employeId, Date dateDebut, Date dateFin, long validateurId) {
		l.info("In valider Timesheet");
		Optional validateurO = employeRepository.findById(validateurId);
		Optional missionO = missionRepository.findById(missionId);
		//verifier s'il est un chef de departement (interet des enum)
		if (validateurO.isPresent() && missionO.isPresent()){
			Employe validateur = (Employe) validateurO.get();
			Mission mission = (Mission) missionO.get();
			if(!validateur.getRole().equals(Role.CHEF_DEPARTEMENT)){
				l.info("l'employe doit etre chef de departement pour valider une feuille de temps !");
				return;
			}	
		//verifier s'il est le chef de departement de la mission en question
		boolean chefDeLaMission = false;
		for(Departement dep : validateur.getDepartements()){
			if(dep.getId() == mission.getDepartement().getId()){
				chefDeLaMission = true;
				break;
			}
		}
		if(!chefDeLaMission){
			l.info("l'employe doit etre chef de departement de la mission en question");
			return;
		}
//
		TimesheetPK timesheetPK = new TimesheetPK(missionId, employeId, dateDebut, dateFin);
		Timesheet timesheet =timesheetRepository.findBytimesheetPK(timesheetPK);
		timesheet.setValide(true);
		
		//Comment Lire une date de la base de données
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		l.info("dateDebut : " + dateFormat.format(timesheet.getTimesheetPK().getDateDebut()));
		}
		
	}

	
	public List<Mission> findAllMissionByEmployeJPQL(long employeId) {
		return timesheetRepository.findAllMissionByEmployeJPQL(employeId);
	}

	
	public List<Employe> getAllEmployeByMission(int missionId) {
		return timesheetRepository.getAllEmployeByMission(missionId);
	}

	

}
