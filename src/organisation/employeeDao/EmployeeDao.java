package organisation.employeeDao;

import java.util.*;

import organisation.model.DailyTimeSheet;
import organisation.model.Employee;
import organisation.model.SumTimeSheet;
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
	public List<SumTimeSheet>  getTotalTimeSheetHours();
	
	public void addTimesheet(TimeSheet timesheet);
	public boolean sendEmail(String[] recepients, String[] bccRecepients, String subject,String message);
	
	
	public int deleteTimeSheet(int srNo);

	public int updateTimeSheet(TimeSheet timesheet);
	
	public  TimeSheet getTimeSheetDetails(int srNo);
}
