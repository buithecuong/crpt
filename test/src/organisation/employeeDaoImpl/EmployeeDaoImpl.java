package organisation.employeeDaoImpl;


import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;




import organisation.employeeDao.EmployeeDao;
import organisation.model.Employee;

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

}

