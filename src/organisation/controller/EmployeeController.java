package organisation.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import organisation.employeeService.EmployeeService;
import organisation.model.Employee;


@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	

	@RequestMapping("/")
	public ModelAndView welcome(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		    return new ModelAndView("welcome");
	}

	@RequestMapping(value = "welcome")
	public ModelAndView welcomeBack() {
		return new ModelAndView("welcome");
	}

	@RequestMapping(value = "welcomeAdmin")
	public ModelAndView welcomeAdmin() {
		ModelAndView mav = new ModelAndView("adminIntro");
		mav.addObject("employee", new Employee());
		return mav;
	}

	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String displayLogin(Model model) {
		model.addAttribute("employee", new Employee());
		return "register";
	}

	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST) //validating new employee
	public ModelAndView addUser(HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("employee") Employee employee,
			BindingResult result, SessionStatus status) {

		boolean error = false;
		String s = String.valueOf(employee.getId());
		if (!s.matches("[0-9]+") && employee.getId() == 0) {
			result.rejectValue("id", "user.id.empty");
			error = true;
		}

		if (employee.getName().isEmpty()) {
			result.rejectValue("name", "user.name.empty");
			error = true;
		}

		if (employee.getUsername().isEmpty()) {
			result.rejectValue("username", "user.username.empty");
			error = true;
		}

		if (employee.getPassword().isEmpty()) {
			result.rejectValue("password", "user.password.empty");
			error = true;
		}

		if (employee.getTeam().isEmpty()) {
			result.rejectValue("team", "user.team.empty");
			error = true;
		}
		
		if (employee.getStatus().isEmpty()) {
			result.rejectValue("status", "user.status.empty");
			error = true;
		}
		
		ModelAndView mav = null;

		if (error) {

			mav = new ModelAndView("register");
			mav.addObject("message", "Enter Complete and Valid Information");
			mav.addObject("employee", new Employee());
			return mav;
		}

		empService.register(employee);

		status.setComplete();
		return new ModelAndView("intro", "name", employee.getName());
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)  // Session Check
	public ModelAndView userProfile(HttpServletRequest request,
			HttpSession session, HttpServletResponse response, Employee employee) {
		session = request.getSession(false);
		return empService.checkSession(employee,session);
		 

	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@ModelAttribute("employee") Employee employee) {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("employee", new Employee());
		return mav;
	}

	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)  // Existing employee validation
	public ModelAndView loginProcess(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@ModelAttribute("employee") Employee employee) {
		            session = request.getSession();
		return empService.validateEmployee(employee,request,session);
		
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getList() {

		List<Employee> employeeList = empService.getList();
		return new ModelAndView("list", "employeeList", employeeList);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView getUserDetails(HttpServletRequest request,HttpSession session,
			ModelMap userModel,@ModelAttribute("employee") Employee employee) {
		int employeeId = Integer.parseInt(request.getParameter("id"));
		session = request.getSession(true);
		employee =empService.getUserDetails(employeeId);
		userModel.addAttribute("employee",employee);
		return new ModelAndView("edit");
	}

	@RequestMapping(value = "/updateSave", method = RequestMethod.POST)
	public ModelAndView updateUser(HttpServletRequest request,HttpSession session,
			HttpServletResponse response,
			@ModelAttribute("employee") Employee employee) {
			empService.updateEmployee(employee);
		    return new ModelAndView("adminIntro");
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteUser(HttpServletRequest request,
			ModelMap userModel) {
		int employeeId = Integer.parseInt(request.getParameter("id"));
		int resp = empService.deleteUserDetails(employeeId);
		userModel.addAttribute("userDetails", empService.getList());
		if (resp > 0) {
			userModel.addAttribute("msg", "User with id : " + employeeId
					+ " deleted successfully.");
		} else {
			userModel.addAttribute("msg", "User with id : " + employeeId
					+ " deletion failed.");
		}
		return new ModelAndView("adminIntro");
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}

}

