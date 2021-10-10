import static org.junit.Assert.assertArrayEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.services.ITimesheetService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TimesheetProjectTest {
	
	private static final Logger l = LogManager.getLogger(TimesheetProjectTest.class);
	
	@Autowired
	ITimesheetService timesheetService;
	
	@Autowired
	MissionRepository missionRepository;
	
	@Autowired
	EmployeRepository employeRepository;
	
	@Test
	public void testAjouterTimesheet() throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date dateDebut = dateFormat.parse("2018/06/24");
		Date dateFin = dateFormat.parse("2018/12/24");
		int missionId = 5;
		int employeId = 2;
		List <Mission> missions= timesheetService.findAllMissionByEmployeJPQL(employeId);
		if(! missionRepository.existsById(missionId)){
			l.info("mission introuvable");
		}
		if(! employeRepository.existsById(employeId)){
			l.info("employe introuvable");
		}
		if(dateDebut.after(dateFin)){
			l.info("vérifier les dates");
		}
		try{
			if(missionRepository.existsById(missionId) && employeRepository.existsById(employeId) && dateDebut.before(dateFin)){
				timesheetService.ajouterTimesheet(missionId, employeId, dateDebut, dateFin);
			}
		}
		catch(Exception e){
			l.info(e);
		}
		
		finally{
			List <Mission> missionsCompare= timesheetService.findAllMissionByEmployeJPQL(employeId);
			assertArrayEquals(missionsCompare.toArray(),missions.toArray());

		}

		
	}

}
