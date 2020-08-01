package organisation.timesheetDao;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import organisation.model.TimeSheet;

public class TimesheetDaoImpl implements TimesheetDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<TimeSheet> getTimesheet() {

		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<TimeSheet> timesheetList = session.createQuery("from timesheet order by srNo").list();
		session.close();
		return timesheetList;
	}

	@Override
	public int deleteTimeDetails(int id) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		TimeSheet timesheet = (TimeSheet) session.get(TimeSheet.class, id);

		session.delete(timesheet);
		session.flush();
		tx.commit();
		session.close();
		return id;

	}

	public int updateTime(TimeSheet timesheet) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(timesheet);
		session.flush();
		tx.commit();
		session.close();
		return timesheet.getSrNo();
	}
	
	public TimeSheet getTimeSheetDetails(int id) {
		Session session = sessionFactory.openSession();
		TimeSheet ts = session.load(TimeSheet.class, id);
        return ts;
	}

	



}
