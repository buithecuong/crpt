package organisation.timesheetDao;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import organisation.model.Timesheet;
public class TimesheetDaoImpl implements TimesheetDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public List<Timesheet> getTimeshett() {

		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<Timesheet> timesheetList = session.createQuery("from timesheet order by id").list();
		session.close();
		return timesheetList;
	}

	@Override
	public int deleteTimeDetails(int id) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Timesheet timesheet = (Timesheet) session.get(Timesheet.class, id);

		session.delete(timesheet);
		session.flush();
		tx.commit();
		session.close();
		return id;

	}

	public int updateTime(Timesheet timesheet) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(timesheet);
		session.flush();
		tx.commit();
		session.close();
		return timesheet.getId();
	}
	
	public Timesheet getTimeSheetDetails(int id) {
		Session session = sessionFactory.openSession();
		Timesheet ts = session.load(Timesheet.class, id);
        return ts;
	}

	@Override
	public List<Timesheet> getTimesheet() {
		// TODO Auto-generated method stub
		return null;
	}

	



}
