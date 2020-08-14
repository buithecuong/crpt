package organisation.employeeDao;

import java.util.*;

import organisation.model.DailyTimeSheet;
import organisation.model.Employee;
import organisation.model.SumTimeSheet;
import organisation.model.TimeSheet;
import organisation.model.EmployeeTimeSheet;
import organisation.model.SumEmployeeTimeSheet;
import organisation.model.DailyEmployeeTimeSheet;
import organisation.model.Objective;

public interface EmployeeDao {

	public void register(Employee employee);

	public List<Employee> getAllEmployees();

	public Employee getUserDetails(int id);

	public int deleteUserDetails(int id);

	public int updateEmp(Employee employee);

	public Employee validateUserDao(Employee employee);

	public List<TimeSheet> getAllTimeSheets();
	
	public List<EmployeeTimeSheet> getAllTimeSheetByEmployee(Employee employee);
	
	List<EmployeeTimeSheet> getAllEmployeeTimeSheetByCurrentDate(Employee employee);
	
	public List<DailyTimeSheet> getAllTimeSheetByDate();
	public List<DailyEmployeeTimeSheet> getAllEmployeeTimeSheetGroupByDate(Employee employee);
	public List<SumTimeSheet>  getTotalTimeSheetHours();
	
	public List<SumEmployeeTimeSheet>  getEmployeeTotalTimeSheetHours(Employee employee);
	
	public void addTimesheet(TimeSheet timesheet);
	public void addEmployeeTimesheet(EmployeeTimeSheet employeetimesheet);
	public boolean sendEmail(String[] recepients, String[] bccRecepients, String subject,String message);
	
	
	public int deleteTimeSheet(int srNo);
	
	public int deleteEmployeeTimeSheet(int srNo);

	public int updateTimeSheet(TimeSheet timesheet);
	
	public  TimeSheet getTimeSheetDetails(int srNo);
	
	public Boolean insertTimeSheet(List<TimeSheet> tms);
	
	public Boolean insertEmployeeTimeSheet(List<EmployeeTimeSheet> tms);
	
	public int deleteTimeSheetDetails(int id);
	
	public Boolean insertObjective(List<Objective> objs);

	public int deleteObjective(int id);

	public List<Objective> getObjectives();

	public int updateObjective(Objective obj);
}
