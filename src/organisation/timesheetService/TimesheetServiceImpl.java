package organisation.timesheetService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import organisation.timesheetDao.TimesheetDao;
import organisation.model.TimeSheet;

public class TimesheetServiceImpl implements TimesheetService {
	
	@Autowired
	TimesheetDao TimesheetDao;

	TimeSheet timeSheet;

	@Override
	public List<TimeSheet> getList() {
		List<TimeSheet> timesheetList = TimesheetDao.getTimesheet();
		return timesheetList;
	}

	@Override
	public int deleteTimesheetDetails(int id) {
		int deletedid = TimesheetDao.deleteTimeDetails(id);
		return deletedid;
	}

	@Override
	public int updatetimesheet(TimeSheet timesheet) {
		int updatedId = TimesheetDao.updateTime(timesheet);
		return updatedId;
	}

	@Override
	public TimeSheet TimesheetDetails(int timesheetId) {
		TimeSheet timesheet = (TimeSheet) TimesheetDao.getTimesheet();
		return timesheet;
	}


	
}
