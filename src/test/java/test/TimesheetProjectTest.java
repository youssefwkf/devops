package test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.ITimesheetService;

@SpringBootTest(classes={TimesheetProjectTest.class})
@RunWith(SpringRunner.class)
@ComponentScan(basePackages ={"tn.esprit.spring"})
public class TimesheetProjectTest {
	
private static final Logger l = LogManager.getLogger(TimesheetProjectTest.class);
	
	@Autowired
	ITimesheetService timesheetService;
	
	@Autowired
	TimesheetRepository timesheetRepository;
	
	@Autowired
	MissionRepository missionRepository;
	
	@Autowired
	IEmployeService employeService;
	
	@Autowired
	EmployeRepository employeRepository;
	
	@Test
	public void testAjouterTimesheet() throws ParseException{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date dateDebut = dateFormat.parse("2018/06/24");
		Date dateFin = dateFormat.parse("2018/12/24");
		
		try{
			Mission mission = new Mission("mission_devops","stages_test");
			timesheetService.ajouterMission(mission);
			l.info("mission_ajouté");
			
			Employe employe = new Employe("youssef","wakaf","youssef@esprit.tn",true,Role.INGENIEUR);
			employeService.ajouterEmploye(employe);
			l.info("employe_ajouté");
			List <Mission> missions= timesheetService.findAllMissionByEmployeJPQL(employe.getId());

			Timesheet timesheet = timesheetService.ajouterTimesheet(mission.getId(), employe.getId(), dateDebut, dateFin);
			l.info("timesheet.....ok");
			List <Mission> missionsCompare= timesheetService.findAllMissionByEmployeJPQL(employe.getId());
			assertEquals(missionsCompare.size(),missions.size()+1);
			l.info("Equals.....ok");
			timesheetRepository.delete(timesheet);
			missionRepository.delete(mission);
			employeRepository.delete(employe);
		}
		catch(Exception e){
			l.info(e);
		}
		
		finally{
			l.info("END_TEST.....ok");
		}

		
	}

}
