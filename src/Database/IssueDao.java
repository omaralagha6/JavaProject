package Database;


import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class IssueDao {
	 private SessionFactory factory;
	    private static IssueDao empDAO = null;

	    public static IssueDao getIssueDao() {
	        if (empDAO == null) {
	            empDAO = new IssueDao();
	        }
	        return empDAO;
	    }

	    private IssueDao() {
	        factory = DbConnection.getSession();
	    }

	    public void add(Model.Issue person) {
	        Session session = factory.getCurrentSession();
	        session.beginTransaction();

	        // save the object
	        System.out.println("Saving Book ...");
	        System.out.println(person);

	        session.save(person);
	        // commit the transaction
	        session.getTransaction().commit();
	        System.out.println("Done !...");
	    }


	    public Model.Issue get(String id) {
	        Session session = factory.getCurrentSession();
	        session.beginTransaction();
	        //retrieve student based on id : primary key
	        System.out.println("Getting Book with id " + id);
	        List<Model.Issue> iss=null;
	        try {
	        	iss = session.createQuery("from Issue where book_isbn = " + id).list();
	            System.out.println("Get Complete " + iss);
	            session.getTransaction().commit();
	        }
	        catch(Exception e)
	        {
	            session.getTransaction().rollback();
	            System.out.println("Rolled Back");
	        }
	        return iss.get(0);
	    }




	    public List<Model.Issue> getAll() {
	        Session session = factory.getCurrentSession();
	        session.beginTransaction();
	        List<Model.Issue> Books = session.createQuery("from Issue").list();
	        session.getTransaction().commit();

	        return Books;
	    }
	    
	    public List<Model.Issue> getAllDistinct() {
	        Session session = factory.getCurrentSession();
	        session.beginTransaction();
	        List<Model.Issue> Books = session.createQuery("from Issue where").list();
	        session.getTransaction().commit();

	        return Books;
	    }

	    public void update(Model.Issue e) {
	        Session session = factory.getCurrentSession();
	        session.beginTransaction();

	        Hibernate.initialize(e);
	        session.update(e);
	        session.getTransaction().commit();

	    }

	    public void delete(String id) {
	        Session session = factory.getCurrentSession();
	        Model.Issue p = (Model.Issue) session.load(Model.Issue.class, id);
	        if (null != p) {
	            session.delete(p);
	        }
	    }
}
