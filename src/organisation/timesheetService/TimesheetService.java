package organisation.timesheetService;

import java.util.List;

import organisation.model.Timesheet;

public interface TimesheetService {
	
	public List<Timesheet> getList();

	public int deleteTimesheetDetails(int id);

	public int updatetimesheet(Timesheet timesheet);

	public Timesheet TimesheetDetails(int timesheetId);

}
