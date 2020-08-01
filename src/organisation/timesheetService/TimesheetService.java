package organisation.timesheetService;

import java.util.List;

import organisation.model.TimeSheet;

public interface TimesheetService {
	
	public List<TimeSheet> getList();

	public int deleteTimesheetDetails(int id);

	public int updatetimesheet(TimeSheet timesheet);

	public TimeSheet TimesheetDetails(int timesheetId);

}
