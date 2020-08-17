package organisation.employeeDao;

import java.util.*;

import organisation.model.DailyTimeSheet;
import organisation.model.Employee;
import organisation.model.EmployeeObjective;
import organisation.model.SumTimeSheet;
import organisation.model.TimeSheet;
import organisation.model.EmployeeTimeSheet;
import organisation.model.SumEmployeeTimeSheet;
import organisation.model.DailyEmployeeTimeSheet;
import organisation.model.Objective;
import organisation.model.EmployeeObjective;

public interface EmployeeDao {

	public void register(Employee employee);

	public List<Employee> getAllEmployees();

	public Employee getUserDetails(int id);

	public int deleteUserDetails(int id);

	public int updateEmp(Employee employee);

	public Employee validateUserDao(Employee employee);
	
	
	// Email

	public boolean sendEmail(String[] recepients, String[] bccRecepients, String subject,String message);
	

	//Timesheet

	public List<TimeSheet> getAllTimeSheets();
	
	public List<DailyTimeSheet> getAllTimeSheetByDate();
	public List<SumTimeSheet>  getTotalTimeSheetHours();	
	public void addTimesheet(TimeSheet timesheet);	
	public int deleteTimeSheet(int srNo);
	public int updateTimeSheet(TimeSheet timesheet);	
	public  TimeSheet getTimeSheetDetails(int srNo);	
	public Boolean insertTimeSheet(List<TimeSheet> tms);
	
	
	// Employee Timesheet	
	public void addEmployeeTimesheet(EmployeeTimeSheet employeetimesheet);

	public int deleteTimeSheetDetails(int id);
	public int updateEmployeeTimeSheet(EmployeeTimeSheet emptimesheet);
	public EmployeeTimeSheet getEmployeeTimeSheetDetails(int srNo);	
	public int deleteEmployeeTimeSheet(int srNo);
	
	List<EmployeeTimeSheet> getAllEmployeeTimeSheetByCurrentDate(Employee employee);
	public List<SumEmployeeTimeSheet>  getEmployeeTotalTimeSheetHours(Employee employee);
	public List<DailyEmployeeTimeSheet> getAllEmployeeTimeSheetGroupByDate(Employee employee);
	public List<EmployeeTimeSheet> getAllTimeSheetByEmployee(Employee employee);	
	
	public List<DailyEmployeeTimeSheet> getAllDailyTimeSheetGroupByEmployee();
	
	//public List<DailyEmployeeTimeSheet> getAllDailyTimeSheetGroupByEmployee();
	                                    
	public List<EmployeeTimeSheet> getAllEmployeeTimeSheet();
	

	public Boolean insertEmployeeTimeSheet(List<EmployeeTimeSheet> tms);	
	
	
	
	//Objective
	public Boolean insertObjective(List<Objective> objs);
	public int deleteObjective(int id);
	public List<Objective> getObjectives();

	public int updateObjective(Objective obj);
	
	public Boolean insertEmployeeObjective(List<EmployeeObjective> em_objs);
	public List<EmployeeObjective> getEmployeeObjectives(Employee employee);
	public int updateEmployeeObjective(EmployeeObjective emobj);
	
	public int deleteEmployeeObjective(int id);
	
	public EmployeeObjective getEmployeeObjectiveDetails(int id);

	
}
