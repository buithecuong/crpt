package organisation.employeeDaoImpl;


import java.text.SimpleDateFormat;
import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import organisation.employeeDao.EmployeeDao;
import organisation.model.Employee;
import organisation.model.TimeSheet;
import organisation.model.EmployeeTimeSheet;
import organisation.model.DailyTimeSheet;
import organisation.model.SumTimeSheet;
import organisation.model.SumEmployeeTimeSheet;
import organisation.model.DailyEmployeeTimeSheet;
import organisation.model.Objective;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void register(Employee emp) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(emp);
		session.flush();
		tx.commit();
		session.close();
	}

	@Override
	public Employee validateUserDao(Employee emp) {         // Employee Exists or Not
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		Query<Employee> query = session
				.createQuery("from Employee where username= :uname and password = :pass");
		query.setParameter("uname", emp.getUsername());
		query.setParameter("pass", emp.getPassword());
		List<Employee> emps = query.list();
		session.close();
	    return emps.isEmpty() ?  null : emps.get(0) ;   
	   		
	}

	@Override
	public List<Employee> getAllEmployees() {
		Session session = sessionFactory.openSession();
		
		@SuppressWarnings("unchecked")
		List<Employee> employeeList = session.createQuery("from Employee order by id").list();
		
		session.close();
		return employeeList;
	}
	
	
	@Override
	public List<TimeSheet> getAllTimeSheets() {
		Session session = sessionFactory.openSession();
		
		@SuppressWarnings("unchecked")
		
		List<TimeSheet> timeSheetList = session.createQuery("from TimeSheet order by date, srNo").list();
		session.close();
		return timeSheetList;
	}
	
	@Override
	public List<EmployeeTimeSheet> getAllTimeSheetByEmployee(Employee employee) {
		Session session = sessionFactory.openSession();
		
		@SuppressWarnings("unchecked")
		
		List<EmployeeTimeSheet> employeetimeSheetList = session.createQuery("from EmployeeTimeSheet where employee_id= " + employee.getId() + " order by date, srNo").list();
		session.close();
		return employeetimeSheetList;
	}
	
	@Override
	public List<EmployeeTimeSheet> getAllEmployeeTimeSheetByCurrentDate(Employee employee) {
		Session session = sessionFactory.openSession();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDateStr = formatter.format(date).split(" ")[0];
		@SuppressWarnings("unchecked")
		
		List<EmployeeTimeSheet> employeetimeSheetList = session.createQuery("from EmployeeTimeSheet where employee_id=" + employee.getId() + " and date=\'" + currentDateStr + "\' order by srNo").list();
		session.close();
		return employeetimeSheetList;
	}
	
	@Override
	public List<DailyEmployeeTimeSheet> getAllEmployeeTimeSheetGroupByDate(Employee employee) {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")		
		Query query = session.createQuery("SELECT new organisation.model.DailyEmployeeTimeSheet(sum(hours), date, employee) "
		  + "from EmployeeTimeSheet where employee_id= " + employee.getId() + " group by date");
	    List<DailyEmployeeTimeSheet> dailyEmployeeTimeSheetList = query.list();
		session.close();
		return dailyEmployeeTimeSheetList;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public List<DailyTimeSheet> getAllTimeSheetByDate() {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")		
		Query query = session.createQuery("SELECT new organisation.model.DailyTimeSheet(sum(hours), date) "
		  + "from TimeSheet group by date");
	    List<DailyTimeSheet> dailyTimeSheetList = query.list();
		session.close();
		return dailyTimeSheetList;
	}
	
	@Override
	public List<SumEmployeeTimeSheet>  getEmployeeTotalTimeSheetHours(Employee employee) {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")		
		Query query = session.createQuery("SELECT new organisation.model.SumEmployeeTimeSheet(sum(hours), employee) "
		  + "from EmployeeTimeSheet where employee_id= " + employee.getId());
		List<SumEmployeeTimeSheet> sumEmployeeTimeSheet = query.list();
		session.close();
		return sumEmployeeTimeSheet;
	}
	
	@Override
	public List<SumTimeSheet>  getTotalTimeSheetHours() {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")		
		Query query = session.createQuery("SELECT new organisation.model.SumTimeSheet(sum(hours)) "
		  + "from EmployeeTimeSheet");
		List<SumTimeSheet> sumTimeSheet = query.list();
		session.close();
		return sumTimeSheet;
	}
	
	@Override
	public void addTimesheet(TimeSheet timesheet) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(timesheet);
		session.flush();
		tx.commit();
		session.close();
	}
	
	@Override
	public void addEmployeeTimesheet(EmployeeTimeSheet employeetimesheet) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(employeetimesheet);
		session.flush();
		tx.commit();
		session.close();
	}

	@Override
	public int deleteTimeSheet(int srNo) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		TimeSheet timesheet = (TimeSheet) session.get(TimeSheet.class, srNo);

		session.delete(timesheet);
		session.flush();
		tx.commit();
		session.close();
		return srNo;

	}
	
	@Override
	public int deleteEmployeeTimeSheet(int srNo) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		EmployeeTimeSheet employeetimesheet = (EmployeeTimeSheet) session.get(EmployeeTimeSheet.class, srNo);

		session.delete(employeetimesheet);
		session.flush();
		tx.commit();
		session.close();
		return srNo;

	}

	public int updateTimeSheet(TimeSheet timesheet) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(timesheet);
		session.flush();
		tx.commit();
		session.close();
		return timesheet.getSrNo();
	}
	
	
	

	@Override
	public Employee getUserDetails(int id) {
		Session session = sessionFactory.openSession();
		Employee employee = session.load(Employee.class, id);
        return employee;
	}

	@Override
	public int deleteUserDetails(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Employee emp=(Employee)session.get(Employee.class,id);
        
		session.delete(emp);
		session.flush();
		tx.commit();
		session.close();
		return id;
	}

	@Override
	public int updateEmp(Employee employee) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(employee);
		session.flush();
		tx.commit();
		session.close();
		return employee.getId();
	}
	
	private String SMTP_HOST ="smtp.gmail.com";
	private String FROM_ADDRESS ="xxx@gmail.com";
	private String PASSWORD ="xxxx";
	private String FROM_NAME="CRPT ADMIN";
	
	@Override	               
	public boolean sendEmail(String[] recepients, String[] bccRecepients, String subject,String message)
	{
		
		try{
			Properties props =new Properties();
			props.put("mail.smtp.host",SMTP_HOST );
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "false");
			props.put("mail.smtp.ssl.enable", "true");
			
			
			
			javax.mail.Session session = javax.mail.Session.getInstance(props, new javax.mail.Authenticator(){
				protected PasswordAuthentication getPasswordAuthentication()
				{
					return new PasswordAuthentication(FROM_ADDRESS,PASSWORD);
				}
			});
			
			Message msg = new MimeMessage(session);
			InternetAddress from = new InternetAddress(FROM_ADDRESS, FROM_NAME);
			msg.setFrom(from);	
			//To Recipients
			InternetAddress[] toAddresses = new InternetAddress[recepients.length];
			for (int i =0;i<recepients.length; i++)
			{
				toAddresses[i] = new InternetAddress(recepients[i]);	
			}
			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			//BCC Recipients
			InternetAddress[] bccAddresses = new InternetAddress[bccRecepients.length];
			for (int i =0;i<bccRecepients.length; i++)
			{
				bccAddresses[i] = new InternetAddress(bccRecepients[i]);		
			}	
			msg.setRecipients(Message.RecipientType.BCC, bccAddresses);	
			msg.setSubject(subject);
			msg.setContent(message,"text/plain");
			Transport.send(msg);	
			return true;	
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
		
	}
    
	public class SMTPAuthenticator extends javax.mail.Authenticator
    {
        public PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(FROM_ADDRESS, PASSWORD);
        }
    }
	
	public Boolean insertTimeSheet(List<TimeSheet> tms) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		for(TimeSheet t : tms)
		{
			session.save(t);
			 if (tms.indexOf(t) % 2 == 0 ) { //20, same as the JDBC batch size
			        //flush a batch of inserts and release memory:
			        session.flush();
			        session.clear();
			    }
		}
		session.flush();
        session.clear();
		tx.commit();
		session.close();
		return true;
	}
	
	public TimeSheet getTimeSheetDetails(int srNo) {
		Session session = sessionFactory.openSession();
		TimeSheet ts = session.load(TimeSheet.class, srNo);
        return ts;
	}


	@Override
	public int deleteTimeSheetDetails(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		TimeSheet ts =(TimeSheet)session.get(TimeSheet.class,id);
        
		session.delete(ts);
		session.flush();
		tx.commit();
		session.close();
		return id;
	}
	
	
	public Boolean insertEmployeeTimeSheet(List<EmployeeTimeSheet> tms) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		for(EmployeeTimeSheet t : tms)
		{
			session.save(t);
			 if (tms.indexOf(t) % 2 == 0 ) { //20, same as the JDBC batch size
			        //flush a batch of inserts and release memory:
			        session.flush();
			        session.clear();
			    }
		}
		session.flush();
        session.clear();
		tx.commit();
		session.close();
		return true;
	}
	
	@Override
	public int deleteObjective(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Objective obj =(Objective)session.get(Objective.class,id);

		session.delete(obj);
		session.flush();
		tx.commit();
		session.close();
		return id;
	}

	public Boolean insertObjective(List<Objective> objs) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		for(Objective obj : objs)
		{
			session.save(obj);
			 if (objs.indexOf(obj) % 2 == 0 ) { //20, same as the JDBC batch size
			        //flush a batch of inserts and release memory:
			        session.flush();
			        session.clear();
			    }
		}
		session.flush();
        session.clear();
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public List<Objective> getObjectives() {
		Session session = sessionFactory.openSession();

		@SuppressWarnings("unchecked")

		List<Objective> objectiveList = session.createQuery("from Objective order by id").list();
		session.close();
		return objectiveList;
	}

	@Override
	public int updateObjective(Objective obj) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(obj);
		session.flush();
		tx.commit();
		session.close();
		return obj.getSrNo();
	}
	
}

