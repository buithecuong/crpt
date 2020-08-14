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

public interface EmployeeService {

	public int register(Employee employee);

	public List<Employee> getList();

	public Employee getUserDetails(int id);

	public int deleteUserDetails(int id);

	public int updateEmployee(Employee employee);

	public ModelAndView validateEmployee(Employee employee, HttpServletRequest request, HttpSession session);

	public ModelAndView checkSession(Employee employee, HttpSession session);

	List<TimeSheet> getTimeSheetList();
	
	public String addTimesheet(TimeSheet timesheet);
	
	public String addEmployeeTimesheet(EmployeeTimeSheet employeetimesheet);
	
	public List<TimeSheet> getListTimesheet();
	
	public List<EmployeeTimeSheet> getListEmployeeTimesheet(Employee employee);
	
	public List<EmployeeTimeSheet> getListEmployeeTimesheetByCurrentDate(Employee employee);
	
	public List<DailyTimeSheet> getListDailyTimesheet();
	
	public List<DailyEmployeeTimeSheet> getListDailyEmployeeTimesheet(Employee employee) ;
	
	public List<SumTimeSheet> getTotalTimesheetHours();
	
	public List<SumEmployeeTimeSheet> getEmployeeTotalTimesheetHours(Employee employee);
	
	public Boolean sendEmail(String[] recepients, String[] bccRecepients, String subject, String message);
	
	public int deleteTimeSheet(int srNo);
	
	public int deleteEmployeeTimeSheet(int srNo);

	public int updateTimeSheet(TimeSheet timesheet);

	public TimeSheet getTimeSheetDetails(int timesheetId);
	
	public Boolean insertTimesheet(List<TimeSheet> tms);
	
	public Boolean insertEmployeeTimesheet(List<EmployeeTimeSheet> tms);
	
	public Boolean insertObjective(List<Objective> objs);

	public int deleteObjective(int id);

	public List<Objective> getObjectives();

	public int updateObjective(Objective obj);
	
	

}
