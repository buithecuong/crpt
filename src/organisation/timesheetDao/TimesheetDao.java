package organisation.timesheetDao;

import java.util.List;

import organisation.model.TimeSheet;

public interface TimesheetDao {
	
	public List<TimeSheet> getTimesheet();

	public int deleteTimeDetails(int id);

	public int updateTime(TimeSheet timesheet);
	

}

