package Database;

import java.lang.reflect.Member;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Model.Employee;
import Model.Person;

public class MemberDao implements Dao{

	
	private SessionFactory factory;

	public MemberDao() {
		factory =DbConnection.getSession();
	}

	public void add(Person person) {
		Session session =factory.getCurrentSession();
		session.beginTransaction();

		// save the object
		System.out.println("Saving Student ...");
		System.out.println(person);
		session.save((Member)person);
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
		Person mem= (Person) session.get(Member.class,id);
		System.out.println("Get Complete "+mem);
		session.getTransaction().commit();
		return mem;
	}
	
	
	public List<Person>getAll() {
		Session session=factory.getCurrentSession();
		session.beginTransaction();
		List<Person> members=session.createQuery("from Member").list();
		session.getTransaction().commit();
		
		return members;
	}
	
	public void update(Person e)
	{
		Session session=factory.getCurrentSession();
		session.beginTransaction();
		Hibernate.initialize((Member)e);
		session.update((Member)e);
		session.getTransaction().commit();
		
	}
	
	public void delete(String id)
	{
		Session session = factory.getCurrentSession();
		Member p = (Member) session.load(Member.class, id);
		if (null != p) {
			session.delete(p);
		}
	}
	
	
}
