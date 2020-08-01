package organisation.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import organisation.timesheetService.TimesheetService;
import organisation.model.TimeSheet;

public class TimesheetController {
	
	@Autowired
	private TimesheetService timeService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getList() {

		List<TimeSheet> timesheetList = timeService.getList();
		return new ModelAndView("list", "timesheetList", timesheetList);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView getTimesheetDetails(HttpServletRequest request, HttpSession session, ModelMap userModel,
			@ModelAttribute("timesheet") TimeSheet timesheet) {
		int timesheetId = Integer.parseInt(request.getParameter("id"));
		session = request.getSession(true);
		timesheet = timeService.TimesheetDetails(timesheetId);
		userModel.addAttribute("timesheet", timesheet);
		return new ModelAndView("edit");
	}

	@RequestMapping(value = "/updateSave", method = RequestMethod.POST)
	public ModelAndView updatetimesheet(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			@ModelAttribute("timesheet") TimeSheet timesheet) {
		timeService.updatetimesheet(timesheet);
		return new ModelAndView("preTimesheet");
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteUser(HttpServletRequest request, ModelMap userModel) {
		int timesheetId = Integer.parseInt(request.getParameter("id"));
		int resp = timeService.deleteTimesheetDetails(timesheetId);
		userModel.addAttribute("TimesheetDetails", timeService.getList());
		if (resp > 0) {
			userModel.addAttribute("msg", "Data with id : " + timesheetId + " deleted successfully.");
		} else {
			userModel.addAttribute("msg", "Data with id : " + timesheetId + " deletion failed.");
		}
		return new ModelAndView("preTimesheet");
	}


}
