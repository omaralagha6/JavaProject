package Database;


import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class IssueDao {
	 private SessionFactory factory;
	    private static IssueDao issDAO = null;

	    public static IssueDao getIssueDao() {
	        if (issDAO == null) {
	            issDAO = new IssueDao();
	        }
	        return issDAO;
	    }

	    private IssueDao() {
	        factory = DbConnection.getSession();
	    }

	    public void add(Model.Issue issue) {
	        Session session = factory.getCurrentSession();
	        session.beginTransaction();

	        // save the object
	        System.out.println("Saving Book ...");
	        System.out.println(issue);

	        session.save(issue);
	        // commit the transaction
	        session.getTransaction().commit();
	        System.out.println("Done !...");
	    }


	    public Model.Issue get(String id) {
	        Session session = factory.getCurrentSession();
	        session.beginTransaction();
	        //retrieve student based on id : primary key
	        System.out.println("Getting Book with id " + id);
	       Model.Issue iss=null;
	        try {
	        	iss = session.get(Model.Issue.class,id);
	            System.out.println("Get Complete " + iss);
	            session.getTransaction().commit();
	        }
	        catch(Exception e)
	        {
	            session.getTransaction().rollback();
	            System.out.println("Rolled Back");
	        }
	        return iss;
	    }




	    public List<Model.Issue> getAll() {
	        Session session = factory.getCurrentSession();
	        session.beginTransaction();
	        List<Model.Issue> Issues = session.createQuery("from Issue").list();
	        session.getTransaction().commit();

	        return Issues;
	    }
	    
	    public List<Model.Issue> getAllDistinct() {
	        Session session = factory.getCurrentSession();
	        session.beginTransaction();
	        List<Model.Issue> Issues = session.createQuery("from Issue where").list();
	        session.getTransaction().commit();

	        return Issues;
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
