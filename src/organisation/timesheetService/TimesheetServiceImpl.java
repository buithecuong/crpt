package organisation.timesheetService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import organisation.timesheetDao.TimesheetDao;
import organisation.model.Timesheet;

public class TimesheetServiceImpl implements TimesheetService {
	
	@Autowired
	TimesheetDao TimesheetDao;

	Timesheet timeSheet;

	@Override
	public List<Timesheet> getList() {
		List<Timesheet> timesheetList = TimesheetDao.getTimesheet();
		return timesheetList;
	}

	@Override
	public int deleteTimesheetDetails(int id) {
		int deletedid = TimesheetDao.deleteTimeDetails(id);
		return deletedid;
	}

	@Override
	public int updatetimesheet(Timesheet timesheet) {
		int updatedId = TimesheetDao.updateTime(timesheet);
		return updatedId;
	}

	@Override
	public Timesheet TimesheetDetails(int timesheetId) {
		Timesheet timesheet = (Timesheet) TimesheetDao.getTimesheet();
		return timesheet;
	}


	
}
