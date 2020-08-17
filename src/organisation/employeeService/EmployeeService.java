package organisation.employeeService;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;


import organisation.model.Employee;
import organisation.model.TimeSheet;
import organisation.model.DailyEmployeeTimeSheet;
import organisation.model.DailyTimeSheet;
import organisation.model.SumTimeSheet;
import organisation.model.EmployeeTimeSheet;
import organisation.model.SumEmployeeTimeSheet;
import organisation.model.Objective;
import organisation.model.EmployeeObjective;

public interface EmployeeService {

	public int register(Employee employee);

	public List<Employee> getList();

	public Employee getUserDetails(int id);

	public int deleteUserDetails(int id);

	public int updateEmployee(Employee employee);

	public ModelAndView validateEmployee(Employee employee, HttpServletRequest request, HttpSession session);

	public ModelAndView checkSession(Employee employee, HttpSession session);
	
	
	// Email
	public Boolean sendEmail(String[] recepients, String[] bccRecepients, String subject, String message);
	
	
	//Timesheet

	List<TimeSheet> getTimeSheetList();
	
	public String addTimesheet(TimeSheet timesheet);
	
	public List<TimeSheet> getListTimesheet();
	
	public List<DailyTimeSheet> getListDailyTimesheet();
	
	public List<SumTimeSheet> getTotalTimesheetHours();
	
	public int deleteTimeSheet(int srNo);

	public int updateTimeSheet(TimeSheet timesheet);

	public TimeSheet getTimeSheetDetails(int timesheetId);
	public Boolean insertTimesheet(List<TimeSheet> tms);
	
	
	// Employee Timesheet
	
	public EmployeeTimeSheet getEmployeeTimeSheetDetails(int srNo);
	public int updateEmployeeTimeSheet(EmployeeTimeSheet emptimesheet);
	public Boolean insertEmployeeTimesheet(List<EmployeeTimeSheet> tms);
	
	public String addEmployeeTimesheet(EmployeeTimeSheet employeetimesheet);
	public List<EmployeeTimeSheet> getListEmployeeTimesheet(Employee employee);
	
	public List<EmployeeTimeSheet> getAllEmployeeTimesheet();
	
	public int deleteEmployeeTimeSheet(int srNo);
	
	public List<DailyEmployeeTimeSheet> getListDailyEmployeeTimesheet(Employee employee) ;
	public List<SumEmployeeTimeSheet> getEmployeeTotalTimesheetHours(Employee employee);

	public List<DailyEmployeeTimeSheet> getAllDailyEmployeeTimesheet() ;
	
	
	public List<EmployeeTimeSheet> getListEmployeeTimesheetByCurrentDate(Employee employee);
	
	
	// Objective
	public Boolean insertObjective(List<Objective> objs);

	public int deleteObjective(int id);

	public List<Objective> getObjectives();

	public int updateObjective(Objective obj);
	
	
	// Employee Objective
	
	public Boolean insertEmployeeObjective(List<EmployeeObjective> emp_objs);

	public int deleteEmployeeObjective(int id);

	public List<EmployeeObjective> getEmployeeObjectives(Employee employee);

	public int updateEmployeeObjective(EmployeeObjective empObj);
	
	public EmployeeObjective getEmployeeObjectiveDetails(int empObjId);
	
	

}
