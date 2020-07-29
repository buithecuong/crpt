package organisation.timesheetDao;

import java.util.List;

import organisation.model.Timesheet;

public interface TimesheetDao {
	
	public List<Timesheet> getTimesheet();

	public int deleteTimeDetails(int id);

	public int updateTime(Timesheet timesheet);
	

}

