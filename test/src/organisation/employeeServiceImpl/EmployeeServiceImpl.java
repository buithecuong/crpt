package organisation.employeeServiceImpl;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import organisation.employeeDao.EmployeeDao;
import organisation.employeeService.EmployeeService;
import organisation.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;

	Employee employee;

	@Transactional
	@Override
	public int register(Employee employee) {
		employeeDao.register(employee);
		return employee.getId();
	}

	@Transactional
	@Override
	public List<Employee> getList() {
		return employeeDao.getAllEmployees();
	}

	@Transactional
	@Override
	public Employee getUserDetails(int id) {
		return employeeDao.getUserDetails(id);
	}

	@Transactional
	@Override
	public int deleteUserDetails(int id) {
		return employeeDao.deleteUserDetails(id);
	}

	@Transactional
	@Override
	public int updateEmployee(Employee employee) {
		return employeeDao.updateEmp(employee);
	}

	@Override
	public ModelAndView validateEmployee(Employee employee,HttpServletRequest request, HttpSession session) {
		
		
		Employee emp = employeeDao.validateUserDao(employee);
		
		
		ModelAndView mav = null;
	     //Employee is User or Admin
		if (null != emp) {
			if (emp.getStatus().equals("admin")) {
				mav = new ModelAndView("adminIntro");
				mav.addObject("employee", emp);
				mav.addObject("firstname", emp.getName());
				session.setAttribute("employee", emp);
			} else {
				mav = new ModelAndView("intro");
				mav.addObject("employee", emp);
				mav.addObject("firstname", emp.getName());
				session.setAttribute("employee", emp);
			}

		} else {
			mav = new ModelAndView("login");
			mav.addObject("message", "Username or Password is wrong!!");
		}

		return mav;
	}

	@Override
	public ModelAndView checkSession(Employee employee, HttpSession session) {
		ModelAndView mav = null;

		if (session.getAttribute("employee") == null) {
			mav = new ModelAndView("login");
			mav.addObject("employee", new Employee());
		}

		else {
			employee = (Employee) session.getAttribute("employee");
			if (employee.getStatus().equals("admin")) {
				mav = new ModelAndView("adminIntro");
				mav.addObject("employee", employee);
				mav.addObject("firstname", employee.getName());

			} else {
				mav = new ModelAndView("intro");
				mav.addObject("employee", employee);
				mav.addObject("firstname", employee.getName());
			}

		}

		return mav;
	}

}

