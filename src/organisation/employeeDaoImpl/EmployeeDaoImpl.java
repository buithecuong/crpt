package organisation.employeeDaoImpl;


import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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
		List<TimeSheet> timeSheetList = session.createQuery("from TimeSheet order by srNO").list();
		
		session.close();
		return timeSheetList;
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
	
}

