package Database;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.awt.print.Book;
import java.util.List;

public class BookDao {

    private SessionFactory factory;
    private static BookDao empDAO = null;

    public static BookDao getBookDao() {
        if (empDAO == null) {
            empDAO = new BookDao();
        }
        return empDAO;
    }

    private BookDao() {
        factory = DbConnection.getSession();
    }

    public void add(Model.Book book) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        // save the object
        System.out.println("Saving Book ...");
        System.out.println(book);

        session.save(book);
        // commit the transaction
        session.getTransaction().commit();
        System.out.println("Done !...");
    }


    public Model.Book get(String id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //retrieve student based on id : primary key
        System.out.println("Getting Book with id " + id);
        Model.Book book=null;
        try {
        	book = session.get(Model.Book.class, id);
            System.out.println("Get Complete " + book);
            session.getTransaction().commit();
        }
        catch(Exception e)
        {
            session.getTransaction().rollback();
            System.out.println("Rolled Back");
        }
        return book;
    }




    public List<Model.Book> getAll() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<Model.Book> Books = session.createQuery("from Book").list();
        session.getTransaction().commit();

        return Books;
    }

    public void update(Model.Book e) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Hibernate.initialize(e);
        session.update(e);
        session.getTransaction().commit();

    }

    public void delete(String id) {
        Session session = factory.getCurrentSession();
        Book book = (Book) session.load(Book.class, id);
        if (null != book) {
            session.delete(book);
        }
    }


}
