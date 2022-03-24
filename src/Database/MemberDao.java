package Database;

import java.lang.reflect.Member;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Model.Employee;
import Model.Person;

public class MemberDao{

	
	private SessionFactory factory;
	private static MemberDao memDAO=null;
	public static MemberDao getMemDAO(){
		if(memDAO==null)
		{
			memDAO=new MemberDao();
		}
		return memDAO;
	}

	private MemberDao() {
		factory =DbConnection.getSession();
	}

	public void add(Model.Member person) {
		Session session =factory.getCurrentSession();
		session.beginTransaction();

		// save the object
		System.out.println("Saving Member ...");
		System.out.println(person);
		session.save(person);
		// commit the transaction
		session.getTransaction().commit();
		System.out.println("Done !...");
	}
	
	
	public Model.Member get(String id)
	{
		Session session=factory.getCurrentSession();
		session.beginTransaction();
		//retrieve student based on id : primary key
		System.out.println("Getting member with id "+id);
		Model.Member mem= session.get(Model.Member.class,id);
		System.out.println("Get Complete "+mem);
		session.getTransaction().commit();
		return mem;
	}
	public Model.Member getPerson(String id,String phonenumber)
	{
		Session session=factory.getCurrentSession();
		session.beginTransaction();
		//retrieve student based on id : primary key
		System.out.println("Getting student with id "+id);
		Model.Member emp=(Model.Member) session.createQuery("from Member where Id = "+id+" and PhoneNumber = "+phonenumber);
		System.out.println("Get Complete "+emp);
		session.getTransaction().commit();
		return emp;
	}
	
	
	public List<Model.Member>getAll() {
		Session session=factory.getCurrentSession();
		session.beginTransaction();
		List<Model.Member> members=session.createQuery("from Member").list();
		session.getTransaction().commit();
		
		return members;
	}
	
	public void update(Model.Member e)
	{
		Session session=factory.getCurrentSession();
		session.beginTransaction();
		Hibernate.initialize(e);
		session.update(e);
		session.getTransaction().commit();
		
	}
	
	public void delete(String id)
	{
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Model.Member p = (Model.Member) session.load(Model.Member.class, id);
		if (null != p) {
			session.delete(p);
		}
		session.getTransaction().commit();
	}



}
