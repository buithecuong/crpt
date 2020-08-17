package organisation.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import organisation.model.Employee;
import organisation.model.EmployeeObjective;
import organisation.model.SumTimeSheet;
import organisation.model.SumEmployeeTimeSheet;
import organisation.model.TimeSheet;
import organisation.model.DailyTimeSheet;
import organisation.model.TimeSheetForm;
import organisation.model.EmployeeTimeSheet;
import organisation.model.DailyEmployeeTimeSheet;
import organisation.model.Objective;

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
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "contact")
	public ModelAndView contact() {
		return new ModelAndView("contact");
	}
	
	@RequestMapping(value = "sendEmail", method = RequestMethod.POST) // validating new employee
	public String sendEmail(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("employee") Employee employee, BindingResult result, SessionStatus status) {

		boolean error = false;
		ModelAndView mav = null;

		if (error) {

			
			return "redirect:/contact";
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
		
		return "redirect:/contact";
	}
	
	@RequestMapping(value = "/profilebk", method = RequestMethod.GET) // Session Check
	public ModelAndView userProfilebk(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			Employee employee) {
		session = request.getSession(false);
		return empService.checkSession(employee, session);

	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET) // Session Check
	public ModelAndView userProfile(HttpServletRequest request, HttpSession session) {
		
		Employee employee = (Employee) session.getAttribute("employee");
		ModelAndView mav = new ModelAndView("profile");
		mav.addObject("employee", employee);
		
		return mav;

	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("employee") Employee employee) {
		session = request.getSession(false);
		return empService.checkSession(employee, session);
	}

	@RequestMapping(value = "/loginProcessbk", method = RequestMethod.POST) // Existing employee validation
	public ModelAndView loginProcessbk(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("employee") Employee employee) {
		session = request.getSession();

		ModelAndView mav = empService.validateEmployee(employee, request, session);
		
		employee = (Employee) session.getAttribute("employee");
		mav.addObject("employee", employee);
		
		List<Employee> employeeList = empService.getList();
		mav.addObject("employeeList", employeeList);
		
		List<EmployeeObjective> employeeObjectiveList = empService.getEmployeeObjectives(employee);
		mav.addObject("employeeObjectiveList", employeeObjectiveList);
		
		List<EmployeeTimeSheet> employeetimesheetList = empService.getListEmployeeTimesheet(employee);
		mav.addObject("employeetimesheetList", employeetimesheetList);
		
		
		List<EmployeeTimeSheet> currentdateemployeetimesheetList= empService.getListEmployeeTimesheetByCurrentDate(employee);
		mav.addObject("currentdateemployeetimesheetList", currentdateemployeetimesheetList);
		
		
		List<DailyEmployeeTimeSheet>  dailyemployeetimesheetList =  empService.getListDailyEmployeeTimesheet(employee);
		mav.addObject("dataPoints", dailyemployeetimesheetList);
		
		long totalHours = 0;
		if (dailyemployeetimesheetList.size() > 0) {
			
			if (employee.getStatus().equals("manager")) {
				List<SumTimeSheet>  totalTimeSheetHours = empService.getTotalTimesheetHours();
				totalHours = totalTimeSheetHours.get(0).getHours();
			}
			else
			{
				List<SumEmployeeTimeSheet>  employeeTotalTimeSheetHours = empService.getEmployeeTotalTimesheetHours(employee);
				totalHours = employeeTotalTimeSheetHours.get(0).getHours();
			}
		}
		
		mav.addObject("totalTimeSheetHours",totalHours );
		System.out.printf("Total timesheet hours: \t %s \n", totalHours);
		return mav;

	}
	
	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST) // Existing employee validation
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("employee") Employee employee) {
		session = request.getSession();

		ModelAndView mav = empService.validateEmployee(employee, request, session);
		
		employee = (Employee) session.getAttribute("employee");
		mav.addObject("employee", employee);
		
		List<Employee> employeeList = empService.getList();
		mav.addObject("employeeList", employeeList);
		
		List<EmployeeObjective> employeeObjectiveList = empService.getEmployeeObjectives(employee);
		mav.addObject("employeeObjectiveList", employeeObjectiveList);
		
		List<EmployeeTimeSheet> employeetimesheetList = empService.getListEmployeeTimesheet(employee);
		mav.addObject("employeetimesheetList", employeetimesheetList);
		
		
		List<EmployeeTimeSheet> currentdateemployeetimesheetList= empService.getListEmployeeTimesheetByCurrentDate(employee);
		mav.addObject("currentdateemployeetimesheetList", currentdateemployeetimesheetList);
		
		
		List<DailyEmployeeTimeSheet>  dailyemployeetimesheetList =  empService.getListDailyEmployeeTimesheet(employee);
		mav.addObject("dataPoints", dailyemployeetimesheetList);
		
		long totalHours = 0;
		if (dailyemployeetimesheetList.size() > 0) {
			
			if (employee.getStatus().equals("manager")) {
				List<SumTimeSheet>  totalTimeSheetHours = empService.getTotalTimesheetHours();
				totalHours = totalTimeSheetHours.get(0).getHours();
			}
			else
			{
				List<SumEmployeeTimeSheet>  employeeTotalTimeSheetHours = empService.getEmployeeTotalTimesheetHours(employee);
				totalHours = employeeTotalTimeSheetHours.get(0).getHours();
			}
		}
		
		mav.addObject("totalTimeSheetHours",totalHours );
		System.out.printf("Total timesheet hours: \t %s \n", totalHours);
		return mav;

	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView goHome(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		// Employee is User or Admin
		Employee employee = (Employee) session.getAttribute("employee");
		if (employee.getStatus().equals("admin")) {
			mav = new ModelAndView("adminDashboard");
		}
		else if (employee.getStatus().equals("manager")) {
			mav = new ModelAndView("managerDashboard");				
		}
		else {
			mav = new ModelAndView("userDashboard");
			
		}
		//session = request.getSession();
		
		
		mav.addObject("employee", employee);
		
		List<Employee> employeeList = empService.getList();
		mav.addObject("employeeList", employeeList);
		
		List<EmployeeObjective> employeeObjectiveList = empService.getEmployeeObjectives(employee);
		mav.addObject("employeeObjectiveList", employeeObjectiveList);
		
		
		List<EmployeeTimeSheet> employeetimesheetList = empService.getListEmployeeTimesheet(employee);
		mav.addObject("employeetimesheetList", employeetimesheetList);
		
		
		List<EmployeeTimeSheet> currentdateemployeetimesheetList= empService.getListEmployeeTimesheetByCurrentDate(employee);
		mav.addObject("currentdateemployeetimesheetList", currentdateemployeetimesheetList);
		
		
		List<DailyEmployeeTimeSheet>  dailyemployeetimesheetList =  empService.getListDailyEmployeeTimesheet(employee);
		mav.addObject("dataPoints", dailyemployeetimesheetList);
		
		long totalHours = 0;
		if (dailyemployeetimesheetList.size() > 0) {
			
			if (employee.getStatus().equals("manager")) {
				List<SumTimeSheet>  totalTimeSheetHours = empService.getTotalTimesheetHours();
				totalHours = totalTimeSheetHours.get(0).getHours();
			}
			else
			{
				List<SumEmployeeTimeSheet>  employeeTotalTimeSheetHours = empService.getEmployeeTotalTimesheetHours(employee);
				totalHours = employeeTotalTimeSheetHours.get(0).getHours();
			}
		}
		
		mav.addObject("totalTimeSheetHours",totalHours );
		System.out.printf("Total timesheet hours: \t %s \n", totalHours);
		return mav;
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
	public String updateUser(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			@ModelAttribute("employee") Employee employee) {
		empService.updateEmployee(employee);
		return "redirect:/home";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteUser(HttpServletRequest request, ModelMap userModel) {
		int employeeId = Integer.parseInt(request.getParameter("id"));
		int resp = empService.deleteUserDetails(employeeId);
		userModel.addAttribute("userDetails", empService.getList());
		if (resp > 0) {
			userModel.addAttribute("msg", "User with id : " + employeeId + " deleted successfully.");
		} else {
			userModel.addAttribute("msg", "User with id : " + employeeId + " deletion failed.");
		}
		return "redirect:/home";
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
	
	@RequestMapping(value = "/save1", method = RequestMethod.POST)
	public ModelAndView save1(@ModelAttribute("timeSheetForm") TimeSheetForm TSForm) {
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
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Employee employee, @ModelAttribute("timeSheetForm") TimeSheetForm TSForm) throws ParseException {

		String str = request.getParameter("TSstr");
		String day = request.getParameter("date");

		ArrayList<TimeSheet> tms = new ArrayList<>();

		String[] timesheets = str.split(",");
		int tokenCount = timesheets.length;

		for (int j = 0; j < tokenCount; j++) {
			System.out.println("Split Output: " + timesheets[j]);

			String[] values = timesheets[j].split(":");
			if (values.length > 2) {
				TimeSheet t = new TimeSheet();
				t.setSrNo(1);
				t.setJobTitle(values[1]);
				t.setHours(Integer.valueOf(values[2]));
				t.setStatus(values[3]);
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(day);
				t.setDate(date);
				tms.add(t);
			}
		}

		empService.insertTimesheet(tms);
		System.out.println("TimeSheets Inserted Successfully");

		List<TimeSheet> timesheetList = empService.getListTimesheet();
		for (TimeSheet record : timesheetList) {
			System.out.printf("%s \t %s \n", record.getJobTitle(), record.getHours(), record.getStatus());
		}
		return new ModelAndView("showTimesheet", "timesheetList", timesheetList);
	}
	
	@RequestMapping(value = "/datepickerRow", method = RequestMethod.GET)
	public ModelAndView datepickerRow(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			 Employee employee) {

		return new ModelAndView("DatePickerRow");
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
	
	
	@RequestMapping(value = "/viewDailyTimesheetList", method = RequestMethod.GET) // 
	public ModelAndView getDailyTimesheetList() {
		
		ModelAndView mav = new ModelAndView("showDailyTimesheet");
		List<TimeSheet> timesheetList = empService.getListTimesheet();
		if (timesheetList.size()>0) {
			List<DailyTimeSheet>  dailytimesheetList =  empService.getListDailyTimesheet();
			for (DailyTimeSheet record : dailytimesheetList) {
				System.out.printf("%s \t %s \n", record.getDate(), record.getHours());
			}
			mav.addObject("dailytimesheetList", dailytimesheetList);
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/editTimeSheet", method = RequestMethod.GET)
	public ModelAndView getTimeSheetDetails(HttpServletRequest request, HttpSession session, ModelMap timesheetModel,
			@ModelAttribute("timesheet") TimeSheet timesheet) {
		int srNo = Integer.parseInt(request.getParameter("id"));
		session = request.getSession(true);
		timesheet = empService.getTimeSheetDetails(srNo);
		timesheetModel.addAttribute("timesheet", timesheet);
		return new ModelAndView("editTimeSheet");
	}

	@RequestMapping(value = "/updateTimeSheet", method = RequestMethod.POST)
	public ModelAndView updateUser(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			@ModelAttribute("timesheet") TimeSheet timesheet) {
		System.out.printf("test update timesheet");
		empService.updateTimeSheet(timesheet);
		return new ModelAndView("showTimesheet");
	}	

	@RequestMapping(value = "/deleteTimeSheet", method = RequestMethod.GET)
	public ModelAndView deleteTimeSheet(HttpServletRequest request, ModelMap userModel) {
		int srNo = Integer.parseInt(request.getParameter("id"));
		int resp = empService.deleteTimeSheet(srNo);
		userModel.addAttribute("TimeSheetDetails", empService.getList());
		if (resp > 0) {
			userModel.addAttribute("msg", "TimeSheet with srNo : " + srNo + " deleted successfully.");
		} else {
			userModel.addAttribute("msg", "TimeSheet with srNo : " + srNo + " deletion failed.");
		}
		return new ModelAndView("adminIntro");
	}	
	
	@RequestMapping(value = "/viewDailyTimesheetChart", method = RequestMethod.GET) // 
	public ModelAndView getDailyTimesheetChart() {

		ModelAndView mav = new ModelAndView("showDailyTimeSheetChart");
		List<TimeSheet> timesheetList = empService.getListTimesheet();
		if (timesheetList.size()>0) {
			List<DailyTimeSheet>  dailytimesheetList =  empService.getListDailyTimesheet();
			for (DailyTimeSheet record : dailytimesheetList) {
				System.out.printf("%s \t %s \n", record.getDate(), record.getHours());
			}
			mav.addObject("dataPoints", dailytimesheetList);
		}
		return mav;
	}
	
	
	
	


	
	@RequestMapping(value = "/employeeTimeSheet", method = RequestMethod.GET)
	public ModelAndView dailytimesheet(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			 Employee employee) {

		return new ModelAndView("employeeTimeSheet");
	}
	
	@RequestMapping(value = "/saveEmployeeTimeSheet", method = RequestMethod.POST)
	public String saveEmployeeTimeSheet(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Employee employee, @ModelAttribute("timeSheetForm") TimeSheetForm TSForm) throws ParseException {
		
		employee = (Employee) session.getAttribute("employee");
		String str = request.getParameter("TSstr");
		String day = request.getParameter("date");

		ArrayList<EmployeeTimeSheet> tms = new ArrayList<>();

		String[] employeetimesheets = str.split(",");
		int tokenCount = employeetimesheets.length;

		for (int j = 0; j < tokenCount; j++) {
			System.out.println("Split Output: " + employeetimesheets[j]);

			String[] values = employeetimesheets[j].split(":");
			if (values.length > 2) {
				EmployeeTimeSheet et = new EmployeeTimeSheet();
				et.setJobTitle(values[1]);
				et.setHours(Integer.valueOf(values[2]));
				et.setStatus(values[3]);
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(day);
				et.setDate(date);
				et.setEmployee(employee);
				tms.add(et);
			}
		}

		empService.insertEmployeeTimesheet(tms);
		System.out.println("EmployeeTimeSheets Inserted Successfully");

		return "redirect:/viewEmployeeTimesheetList";
	}

	
	@RequestMapping(value = "/employeeTimeSheetRow", method = RequestMethod.GET)
	public ModelAndView employeetimesheet_single(Model model,HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("employee") Employee employee) {
		ModelAndView mav = new ModelAndView("employeeTimeSheetRow");
		employee = (Employee) session.getAttribute("employee");
		System.out.printf("Name: %s \n", employee.getName());
		mav.addObject("employee", employee);
		
		EmployeeTimeSheet employeeTimeSheet = new EmployeeTimeSheet();
		employeeTimeSheet.setEmployee(employee);
		model.addAttribute("employeetimesheet", employeeTimeSheet);
		
		return mav;
	}

	
	@RequestMapping(value = "/addEmployeeTimesheetRow", method = RequestMethod.POST) // validating new employee
	public ModelAndView addEmployeeTimesheet(HttpServletRequest request, HttpServletResponse response,  HttpSession session,
			@ModelAttribute("employeetimesheet") EmployeeTimeSheet employeetimesheet, BindingResult result, SessionStatus status) {

		boolean error = false;
		ModelAndView mav = null;
		Employee employee = (Employee) session.getAttribute("employee");
		if (error) {

			mav = new ModelAndView("dailyTimesheet");
			mav.addObject("timesheet", new TimeSheet());
			return mav;
		}
		employeetimesheet.setEmployee(employee);
		empService.addEmployeeTimesheet(employeetimesheet);

		status.setComplete();
		List<EmployeeTimeSheet> employeetimesheetList = empService.getListEmployeeTimesheet(employee);
		for (EmployeeTimeSheet record : employeetimesheetList) {
			System.out.printf("test %s \t %s \n",  record.getJobTitle(), record.getHours(),record.getStatus());
		}
		
		mav = new ModelAndView("showEmployeeTimeSheet", "employeetimesheetList",employeetimesheetList );
		return mav;
	}  
	


	
	@RequestMapping(value = "/viewEmployeeTimesheetList", method = RequestMethod.GET) // 
	public ModelAndView getEmployeeTimesheetList(HttpServletRequest request,  HttpSession session) {
		
		Employee employee = (Employee) session.getAttribute("employee");
		List<EmployeeTimeSheet> employeetimesheetList = empService.getListEmployeeTimesheet(employee);
		for (EmployeeTimeSheet record : employeetimesheetList) {
			System.out.printf("%s \t %s \n", record.getJobTitle(), record.getHours(),record.getStatus());
		}
		return new ModelAndView("showEmployeeTimeSheet","employeetimesheetList", employeetimesheetList);
	}
	
	
	@RequestMapping(value = "/viewAllEmployeeTimesheet", method = RequestMethod.GET) // 
	public ModelAndView getAllEmployeeTimesheet(HttpServletRequest request,  HttpSession session) {
		
		List<EmployeeTimeSheet> employeetimesheetList = empService.getAllEmployeeTimesheet();
		for (EmployeeTimeSheet record : employeetimesheetList) {
			System.out.printf("%s \t %s \n", record.getJobTitle(), record.getHours(),record.getStatus());
		}
		return new ModelAndView("showEmployeeTimeSheet","employeetimesheetList", employeetimesheetList);
	}
	
	@RequestMapping(value = "/viewDailyEmployeeTimesheetList", method = RequestMethod.GET) // 
	public ModelAndView getDailyEmployeeTimesheetList(HttpServletRequest request,  HttpSession session) {
		
		ModelAndView mav = new ModelAndView("showDailyEmployeeTimeSheet");
		                                     
		Employee employee = (Employee) session.getAttribute("employee");
		List<EmployeeTimeSheet> employeetimesheetList = empService.getListEmployeeTimesheet(employee);
		List<DailyEmployeeTimeSheet>  dailyemployeetimesheetList = null;
		if (employeetimesheetList.size()>0) {
			dailyemployeetimesheetList =  empService.getListDailyEmployeeTimesheet(employee);
			for (DailyEmployeeTimeSheet record : dailyemployeetimesheetList) {
				System.out.printf("%s \t %s \n", record.getDate(), record.getHours());
			}
			mav.addObject("dailyemployeetimesheetList", dailyemployeetimesheetList);
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/viewAllDailyEmployeeTimesheetList", method = RequestMethod.GET) // 
	public ModelAndView getAllDailyEmployeeTimesheetList(HttpServletRequest request,  HttpSession session) {
		
		ModelAndView mav = new ModelAndView("showDailyEmployeeTimeSheet");
		                                     
		Employee employee = (Employee) session.getAttribute("employee");
		List<EmployeeTimeSheet> employeetimesheetList = empService.getAllEmployeeTimesheet();
		List<DailyEmployeeTimeSheet>  dailyemployeetimesheetList = null;
		if (employeetimesheetList.size()>0) {
			dailyemployeetimesheetList =  empService.getAllDailyEmployeeTimesheet();
			for (DailyEmployeeTimeSheet record : dailyemployeetimesheetList) {
				System.out.printf("%s \t %s \n", record.getDate(), record.getHours());
			}
			mav.addObject("dailyemployeetimesheetList", dailyemployeetimesheetList);
		}
		return mav;
	}
	
	@RequestMapping(value = "/viewEmployeeDailyTimesheetChart", method = RequestMethod.GET) // 
	public ModelAndView getEmployeeDailyTimesheetChart(HttpServletRequest request,  HttpSession session) {

		List<DailyTimeSheet>  dailytimesheetList =  empService.getListDailyTimesheet();
		for (DailyTimeSheet record : dailytimesheetList) {
			System.out.printf("%s \t %s \n", record.getDate(), record.getHours());
		}
		
		
		ModelAndView mav = new ModelAndView("showDailyTimeSheetChart");
		Employee employee = (Employee) session.getAttribute("employee");
		List<DailyEmployeeTimeSheet>  dailyemployeetimesheetList = null;
		List<EmployeeTimeSheet> employeetimesheetList = empService.getListEmployeeTimesheet(employee);
		if (employeetimesheetList.size()>0) {
			dailyemployeetimesheetList =  empService.getListDailyEmployeeTimesheet(employee);
			for (DailyEmployeeTimeSheet record : dailyemployeetimesheetList) {
				System.out.printf("%s \t %s \n", record.getDate(), record.getHours());
			}
			mav.addObject("dataPoints", dailyemployeetimesheetList);
		}
		return mav;
	}
	
	@RequestMapping(value = "/editEmployeeTimeSheet", method = RequestMethod.GET)
	public ModelAndView getEmployeeTimeSheetDetails(HttpServletRequest request, HttpSession session, ModelMap employeeTimesheetModel,
			@ModelAttribute("employeetimesheet") EmployeeTimeSheet employeetimesheet) {
		int srNo = Integer.parseInt(request.getParameter("id"));
		session = request.getSession(true);
		employeetimesheet = empService.getEmployeeTimeSheetDetails(srNo);
		employeeTimesheetModel.addAttribute("employeetimesheet", employeetimesheet);
		return new ModelAndView("editEmployeeTimeSheet");
	}

	@RequestMapping(value = "/updateEmployeeTimeSheet", method = RequestMethod.POST)
	public String updateUser(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			@ModelAttribute("employeetimesheet") EmployeeTimeSheet employeetimesheet) {
		System.out.printf("test update timesheet");
		Employee employee = (Employee) session.getAttribute("employee");
		employeetimesheet.setEmployee(employee);
		empService.updateEmployeeTimeSheet(employeetimesheet);
		return "redirect:/viewEmployeeTimesheetList";
	}
	
	@RequestMapping(value = "/deleteEmployeeTimeSheet", method = RequestMethod.GET)
	public String deleteEmployeeTimeSheet(HttpServletRequest request, HttpSession session, ModelMap userModel) {
		int srNo = Integer.parseInt(request.getParameter("id"));
		int resp = empService.deleteEmployeeTimeSheet(srNo);
		userModel.addAttribute("TimeSheetDetails", empService.getList());
		if (resp > 0) {
			userModel.addAttribute("msg", "TimeSheet with srNo : " + srNo + " deleted successfully.");
		} else {
			userModel.addAttribute("msg", "TimeSheet with srNo : " + srNo + " deletion failed.");
		}
		
		return "redirect:/viewEmployeeTimesheetList";
	}
	
	
	
	@RequestMapping(value = "/objectives", method = RequestMethod.GET)
	public ModelAndView objectives(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Employee employee) {

		return new ModelAndView("objectives");
	}

	@RequestMapping(value = "/saveObj", method = RequestMethod.POST)
	public ModelAndView saveObjective(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Employee employee) throws ParseException {

		String str = request.getParameter("Objstr");

		ArrayList<Objective> objs = new ArrayList<>();

		String[] objectives = str.split(",");
		int tokenCount = objectives.length;

		for (int j = 0; j < tokenCount; j++) {
			System.out.println("Split Output: " + objectives[j]);

			String[] values = objectives[j].split(":");
			if (values.length > 2) {
				Objective t = new Objective();
				t.setSrNo(1);
				t.setName(values[1]);
				t.setDuration(Integer.valueOf(values[2]));
				t.setType(values[3]);
				t.setDescription(values[4]);
				objs.add(t);
			}
		}

		empService.insertObjective(objs);

		List<Objective> objectiveList = empService.getObjectives();
		return new ModelAndView("showObjective", "objectiveList", objectiveList);
	}

	@RequestMapping(value = "/viewObjectiveList", method = RequestMethod.GET) //
	public ModelAndView getObjectiveList() {
		List<Objective> objectiveList = empService.getObjectives();
		return new ModelAndView("showObjective", "objectiveList", objectiveList);
	}

	@RequestMapping(value = "/deleteobj", method = RequestMethod.GET)
	public ModelAndView deleteObjective(HttpServletRequest request, ModelMap userModel) {
		int objId = Integer.parseInt(request.getParameter("id"));
		int resp = empService.deleteObjective(objId);
		userModel.addAttribute("userDetails", empService.getListTimesheet());
		if (resp > 0) {
			userModel.addAttribute("msg", "User with id : " + objId + " deleted successfully.");
		} else {
			userModel.addAttribute("msg", "User with id : " + objId + " deletion failed.");
		}
		return new ModelAndView("adminIntro");
	}
	
	
	
	
	@RequestMapping(value = "/employeeObjectives", method = RequestMethod.GET)
	public ModelAndView employeeObjectives(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Employee employee) {

		return new ModelAndView("employeeObjectives");
	}

	@RequestMapping(value = "/saveEmployeeObj", method = RequestMethod.POST)
	public String saveEmployeeObjective(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Employee employee) throws ParseException {

		String str = request.getParameter("Objstr");
		employee = (Employee) session.getAttribute("employee");
		ArrayList<EmployeeObjective> empObjs = new ArrayList<>();

		String[] empObjectives = str.split(",");
		int tokenCount = empObjectives.length;
		
		for (int j = 0; j < tokenCount; j++) {
			System.out.println("Split Output: " + empObjectives[j]);

			String[] values = empObjectives[j].split(":");
			if (values.length > 2) {
				EmployeeObjective t = new EmployeeObjective();
				t.setSrNo(1);
				t.setName(values[1]);
				t.setDuration(Integer.valueOf(values[2]));
				t.setType(values[3]);
				t.setDescription(values[4]);
				t.setStatus(values[5]);
				t.setEmployee(employee);
				empObjs.add(t);
			}
		}

		empService.insertEmployeeObjective(empObjs);
		return "redirect:/viewEmployeeObjectiveList";
	}

	@RequestMapping(value = "/viewEmployeeObjectiveList", method = RequestMethod.GET) //
	public ModelAndView getEmployeeObjectiveList(HttpSession session) {
		Employee employee = (Employee) session.getAttribute("employee");
		List<EmployeeObjective> employeeObjectiveList = empService.getEmployeeObjectives(employee);
		return new ModelAndView("showEmployeeObjective", "employeeObjectiveList", employeeObjectiveList);
	}

	@RequestMapping(value = "/deleteEmployeeObj", method = RequestMethod.GET)
	public String deleteEmployeeObjective(HttpServletRequest request, ModelMap userModel) {
		int objId = Integer.parseInt(request.getParameter("id"));
		int resp = empService.deleteEmployeeObjective(objId);
		userModel.addAttribute("userDetails", empService.getListTimesheet());
		if (resp > 0) {
			userModel.addAttribute("msg", "User with id : " + objId + " deleted successfully.");
		} else {
			userModel.addAttribute("msg", "User with id : " + objId + " deletion failed.");
		}
		return "redirect:/viewEmployeeObjectiveList";
	}
	
	@RequestMapping(value = "/editEmployeeObj", method = RequestMethod.GET)
	public ModelAndView getEmployeObjective(HttpServletRequest request, HttpSession session, ModelMap empObjModel,
			@ModelAttribute("employeeobjective") EmployeeObjective employeeobjective) {
		int employObjId = Integer.parseInt(request.getParameter("id"));
		session = request.getSession(true);
		employeeobjective = empService.getEmployeeObjectiveDetails(employObjId);
		empObjModel.addAttribute("employeeobjective", employeeobjective);
		return new ModelAndView("editEmployeeObjective");
	}
	
	
	@RequestMapping(value = "/updateEmployeeObj", method = RequestMethod.POST)
	public String updateEmployeeObjective(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			@ModelAttribute("employeeobjective") EmployeeObjective employeeobjective) {
		Employee employee = (Employee) session.getAttribute("employee");
		employeeobjective.setEmployee(employee);
		empService.updateEmployeeObjective(employeeobjective);
		return "redirect:/viewEmployeeObjectiveList";
	}
}