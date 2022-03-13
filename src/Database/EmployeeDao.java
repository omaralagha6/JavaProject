package Database;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Model.Employee;
import Model.Person;

public class EmployeeDao implements Dao{

	private SessionFactory factory;

	public EmployeeDao() {
		factory =DbConnection.getSession();
	}

	public void add(Person person) {
		Session session =factory.getCurrentSession();
		session.beginTransaction();

		// save the object
		System.out.println("Saving Student ...");
		System.out.println(person);
		Employee employee=(Employee)person;
		session.save(employee);
		// commit the transaction
		session.getTransaction().commit();
		System.out.println("Done !...");
	}
	
	
	public Person get(String id)
	{
		Session session=factory.getCurrentSession();
		session.beginTransaction();
		//retrieve student based on id : primary key
		System.out.println("Getting student with id "+id);
		Employee emp=session.get(Employee.class,id);
		System.out.println("Get Complete "+emp);
		session.getTransaction().commit();
		return emp;
	}
	
	
	public List<Person>getAll() {
		Session session=factory.getCurrentSession();
		session.beginTransaction();
		List<Person> employees=session.createQuery("from Employee").list();
		session.getTransaction().commit();
		
		return employees;
	}
	
	public void update(Person e)
	{
		Session session=factory.getCurrentSession();
		session.beginTransaction();
		Employee emp=(Employee)e;
		Hibernate.initialize(emp);
		session.update(emp);
		session.getTransaction().commit();
		
	}
	
	public void delete(String id)
	{
		Session session = factory.getCurrentSession();
		Employee p = (Employee) session.load(Employee.class, id);
		if (null != p) {
			session.delete(p);
		}
	}

}
