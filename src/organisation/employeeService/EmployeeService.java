package organisation.employeeService;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import organisation.model.Employee;

public interface EmployeeService {

	public int register(Employee employee);

	public List<Employee> getList();

	public Employee getUserDetails(int id);

	public int deleteUserDetails(int id);

	public int updateEmployee(Employee employee);

	public ModelAndView validateEmployee(Employee employee, HttpServletRequest request, HttpSession session);

	public ModelAndView checkSession(Employee employee, HttpSession session);

}
