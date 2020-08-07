package organisation.employeeDao;

import java.util.*;

import organisation.model.DailyTimeSheet;
import organisation.model.Employee;
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
	public boolean sendEmail(String[] recepients, String[] bccRecepients, String subject,String message);
}
