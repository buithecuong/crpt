package organisation.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;

import organisation.employeeService.EmployeeService;
import organisation.timesheetService.TimesheetService;
import organisation.model.Employee;
import organisation.model.TimeSheet;
import organisation.model.TimeSheetForm;

import organisation.model.Contact;
import organisation.model.ContactForm;


@Controller
public class EmployeeController {

	private static List<TimeSheet> TSrecords = new ArrayList<TimeSheet>();
	
	@Autowired
	private EmployeeService empService;

	@RequestMapping("/")
	public ModelAndView welcome(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return new ModelAndView("login");
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

	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST) // validating new employee
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("employee") Employee employee, BindingResult result, SessionStatus status) {

		boolean error = false;
		ModelAndView mav = null;

		if (error) {

			mav = new ModelAndView("register");
			mav.addObject("employee", new Employee());
			return mav;
		}

		empService.register(employee);

		status.setComplete();
		return new ModelAndView("intro", "name", employee.getName());
	}
	
	@RequestMapping(value = "contact")
	public ModelAndView contact() {
		return new ModelAndView("contact");
	}
	@RequestMapping(value = "sendEmail", method = RequestMethod.POST) // validating new employee
	public ModelAndView sendEmail(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("employee") Employee employee, BindingResult result, SessionStatus status) {

		boolean error = false;
		ModelAndView mav = null;

		if (error) {

			mav = new ModelAndView("register");
			mav.addObject("employee", new Employee());
			return mav;
		}

		String recepient = request.getParameter("toEmail");
		String sender = request.getParameter("fromEmail");
		String subject = request.getParameter("subject");
		String phone_number = request.getParameter("telephone");
		String message =request.getParameter("message") + "\n" + request.getParameter("name")+ "\n Phone Number:" + phone_number;
	
		String [] recepients =recepient.split(",");
		String [] bccRecepients =new String[]{sender};
		String msg = "Please try again! ";
		if (empService.sendEmail(recepients, bccRecepients, subject, message))
			msg = "Email is sent ";
		
		return new ModelAndView("intro", "msg", msg);
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET) // Session Check
	public ModelAndView userProfile(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			Employee employee) {
		session = request.getSession(false);
		return empService.checkSession(employee, session);

	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("employee") Employee employee) {
		session = request.getSession(false);
		return empService.checkSession(employee, session);
	}

	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST) // Existing employee validation
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("employee") Employee employee) {
		session = request.getSession();
		return empService.validateEmployee(employee, request, session);

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getList() {

		List<Employee> employeeList = empService.getList();
		return new ModelAndView("list", "employeeList", employeeList);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView getUserDetails(HttpServletRequest request, HttpSession session, ModelMap userModel,
			@ModelAttribute("employee") Employee employee) {
		int employeeId = Integer.parseInt(request.getParameter("id"));
		session = request.getSession(true);
		employee = empService.getUserDetails(employeeId);
		userModel.addAttribute("employee", employee);
		return new ModelAndView("edit");
	}

	@RequestMapping(value = "/updateSave", method = RequestMethod.POST)
	public ModelAndView updateUser(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			@ModelAttribute("employee") Employee employee) {
		empService.updateEmployee(employee);
		return new ModelAndView("adminIntro");
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteUser(HttpServletRequest request, ModelMap userModel) {
		int employeeId = Integer.parseInt(request.getParameter("id"));
		int resp = empService.deleteUserDetails(employeeId);
		userModel.addAttribute("userDetails", empService.getList());
		if (resp > 0) {
			userModel.addAttribute("msg", "User with id : " + employeeId + " deleted successfully.");
		} else {
			userModel.addAttribute("msg", "User with id : " + employeeId + " deletion failed.");
		}
		return new ModelAndView("adminIntro");
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}

	@RequestMapping(value = "/datepicker", method = RequestMethod.GET)
	public ModelAndView datepicker(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			 Employee employee) {

		return new ModelAndView("DatePicker");
	}
	
	@RequestMapping(value = "/dailyTimesheet", method = RequestMethod.GET)
	public ModelAndView dailytimesheet(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			 Employee employee) {

		return new ModelAndView("timesheet");
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("timeSheetForm") TimeSheetForm TSForm) {
		System.out.println(TSForm);
		System.out.println(TSForm.getTimesheetRecords());
		List<TimeSheet> records = TSForm.getTimesheetRecords();
		
		if(null != records && records.size() > 0) {
			EmployeeController.TSrecords = records;
			for (TimeSheet record : TSrecords) {
				System.out.printf("%s \t %s \n", record.getJobTitle(), record.getHours(),record.getStatus());
				empService.addTimesheet(record);
			}
		}
		
		List<TimeSheet> timeSheetList = empService.getTimeSheetList();
		
		return new ModelAndView("showContact", "timeSheetList", timeSheetList);
	
	}
	
	@RequestMapping(value = "/datepickerRow", method = RequestMethod.GET)
	public ModelAndView datepickerRow(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			 Employee employee) {

		return new ModelAndView("DatePickerRow");
	}
	
	@RequestMapping(value = "/dailyTimesheetRow", method = RequestMethod.GET)
	public ModelAndView dailytimesheet_single(Model model,HttpServletRequest request, HttpServletResponse response, HttpSession session,
			 Employee employee) {
		model.addAttribute("timesheet", new TimeSheet());
		return new ModelAndView("timesheetRow");
	}

	@RequestMapping(value = "/addTimesheetRow", method = RequestMethod.POST) // validating new employee
	public ModelAndView addTimesheet(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("timesheet") TimeSheet timesheet, BindingResult result, SessionStatus status) {

		boolean error = false;
		ModelAndView mav = null;

		if (error) {

			mav = new ModelAndView("dailyTimesheet");
			mav.addObject("timesheet", new TimeSheet());
			return mav;
		}

		empService.addTimesheet(timesheet);

		status.setComplete();
		List<TimeSheet> timesheetList = empService.getListTimesheet();
		for (TimeSheet record : timesheetList) {
			System.out.printf("%s \t %s \n", record.getJobTitle(), record.getHours(),record.getStatus());
		}
		return new ModelAndView("showTimesheet","timesheetList", timesheetList);
	}
	
	
	@RequestMapping(value = "/viewTimesheetList", method = RequestMethod.GET) // 
	public ModelAndView getTimesheetList() {

		List<TimeSheet> timesheetList = empService.getListTimesheet();
		for (TimeSheet record : timesheetList) {
			System.out.printf("%s \t %s \n", record.getJobTitle(), record.getHours(),record.getStatus());
		}
		return new ModelAndView("showTimesheet","timesheetList", timesheetList);
	}
	
	
	
	
	private static List<Contact> contacts = new ArrayList<Contact>();

	static {
		contacts.add(new Contact("Barack", "Obama", "barack.o@whitehouse.com", "147-852-965"));
		contacts.add(new Contact("George", "Bush", "george.b@whitehouse.com", "785-985-652"));
		contacts.add(new Contact("Bill", "Clinton", "bill.c@whitehouse.com", "236-587-412"));
		contacts.add(new Contact("Ronald", "Reagan", "ronald.r@whitehouse.com", "369-852-452"));
	}
	
	@RequestMapping(value = "/getContact", method = RequestMethod.GET)
	public ModelAndView get() {
		
		ContactForm contactForm = new ContactForm();
		contactForm.setContacts(contacts);
		
		return new ModelAndView("add_contact" , "contactForm", contactForm);
	}
	
	@RequestMapping(value = "/saveContact", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("contactForm") ContactForm contactForm) {
		System.out.println(contactForm);
		System.out.println(contactForm.getContacts());
		List<Contact> contacts = contactForm.getContacts();
		
		if(null != contacts && contacts.size() > 0) {
			EmployeeController.contacts = contacts;
			for (Contact contact : contacts) {
				System.out.printf("%s \t %s \n", contact.getFirstname(), contact.getLastname());
			}
		}
		
		return new ModelAndView("show_contact", "contactForm", contactForm);
	}

}