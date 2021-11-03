package springTest;



import java.text.ParseException;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;




@SpringBootTest(classes={TimesheetProjectTest.class})
@RunWith(SpringRunner.class)
public class TimesheetProjectTest {
	
	private static final Logger l = LogManager.getLogger(TimesheetProjectTest.class);
	
	//@Autowired(required=true)
	//EmployeServiceImpl timesheetService;
	
	//@Autowired(required=false)
//	MissionRepository missionRepository;
	
	//@Autowired(required=false)
	//EmployeRepository employeRepository;
	
	@Test
	public void testAjouterTimesheet() throws ParseException{
		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		//Date dateDebut = dateFormat.parse("2018/06/24");
		//Date dateFin = dateFormat.parse("2018/12/24");
		//int missionId = 5;
		//int employeId = 2;
	//	Employe emp = new Employe();
	//	timesheetService.ajouterEmploye(emp);
		//List <Mission> missions= timesheetService.findAllMissionByEmployeJPQL(employeId);
		//System.out.println(missions);
		//if(! missionRepository.existsById(missionId)){
			//l.info("mission introuvable");
		//}
		//if(! employeRepository.existsById(employeId)){
			//l.info("employe introuvable");
		//}
		//if(dateDebut.after(dateFin)){
			//l.info("vérifier les dates");
		//}
		System.out.println("*********");
		//try{
			//if(missionRepository.existsById(missionId) && employeRepository.existsById(employeId) && dateDebut.before(dateFin)){
				//timesheetService.ajouterTimesheet(missionId, employeId, dateDebut, dateFin);
			//	System.out.println("*********");
			//}
		//}
		//catch(Exception e){
			//l.info(e);
		//}
		
		/*finally{
			List <Mission> missionsCompare= timesheetService.findAllMissionByEmployeJPQL(employeId);
			assertArrayEquals(missionsCompare.toArray(),missions.toArray());

		}*/

		
	}
public void testAjouterTimesheet() throws ParseException{
		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		//Date dateDebut = dateFormat.parse("2018/06/24");
		//Date dateFin = dateFormat.parse("2018/12/24");
		//int missionId = 5;
		//int employeId = 2;
	//	Employe emp = new Employe();
	//	timesheetService.ajouterEmploye(emp);
		//List <Mission> missions= timesheetService.findAllMissionByEmployeJPQL(employeId);
		//System.out.println(missions);
		//if(! missionRepository.existsById(missionId)){
			//l.info("mission introuvable");
		//}
		//if(! employeRepository.existsById(employeId)){
			//l.info("employe introuvable");
		//}
		//if(dateDebut.after(dateFin)){
			//l.info("vérifier les dates");
		//}
		System.out.println("*********");
		//try{
			//if(missionRepository.existsById(missionId) && employeRepository.existsById(employeId) && dateDebut.before(dateFin)){
				//timesheetService.ajouterTimesheet(missionId, employeId, dateDebut, dateFin);
			//	System.out.println("*********");
			//}
		//}
		//catch(Exception e){
			//l.info(e);
		//}
		
		/*finally{
			List <Mission> missionsCompare= timesheetService.findAllMissionByEmployeJPQL(employeId);
			assertArrayEquals(missionsCompare.toArray(),missions.toArray());

		}*/

		
	}

}