package organisation.employeeDao;

import java.util.*;

import organisation.model.DailyTimeSheet;
import organisation.model.Employee;
import organisation.model.Objective;
import organisation.model.TimeSheet;

public interface EmployeeDao {

	public void register(Employee employee);

	public List<Employee> getAllEmployees();

	public Employee getUserDetails(int id);

	public int deleteUserDetails(int id);

	public int updateEmp(Employee employee);

	public Employee validateUserDao(Employee employee);

	public List<TimeSheet> getAllTimeSheets();
	
	public List<DailyTimeSheet> getAllTimeSheetByDate();
	
	public void addTimesheet(TimeSheet timesheet);
<<<<<<< Updated upstream
=======
	
	public Boolean insertTimeSheet(List<TimeSheet> tms);
	
	public TimeSheet getTimeSheetDetails(int id);
	
	public int deleteTimeSheetDetails(int id);
	
	public Boolean insertObjective(List<Objective> objs);
	
	public int deleteObjective(int id);
	
	public List<Objective> getObjectives();
	
	public int updateObjective(Objective obj);
	
>>>>>>> Stashed changes
	public boolean sendEmail(String[] recepients, String[] bccRecepients, String subject,String message);
}
