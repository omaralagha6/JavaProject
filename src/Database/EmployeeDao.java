package Database;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Model.Employee;
import Model.Person;

public class EmployeeDao {

    private SessionFactory factory;
    private static EmployeeDao empDAO = null;

    public static EmployeeDao getEmployeeDao() {
        if (empDAO == null) {
            empDAO = new EmployeeDao();
        }
        return empDAO;
    }

    private EmployeeDao() {
        factory = DbConnection.getSession();
    }

    public void add(Employee person) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        // save the object
        System.out.println("Saving Employee ...");
        System.out.println(person);

        session.save(person);
        // commit the transaction
        session.getTransaction().commit();
        System.out.println("Done !...");
    }


    public Employee getEmployee(String id, String pass) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //retrieve student based on id : primary key
        System.out.println("Getting Employee with id " + id);
        Employee emp =null;
        if(pass.equals(""))
        {
            try {
                emp = session.get(Employee.class, id);
                System.out.println("Get Complete " + emp);
                session.getTransaction().commit();
            }
            catch(Exception e)
            {
                session.getTransaction().rollback();
                System.out.println("Rolled Back");
            }
        }
        else {
            try {
                emp = (Employee) session.createQuery("from Employee where Id = '" + id + "' and password = '" + pass + "'").getSingleResult();
                System.out.println("Get Complete " + emp);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                System.out.println("Rolled Back");
            }
        }
        return emp;
    }


    public List<Employee> getAll() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<Employee> employees = session.createQuery("from Employee").list();
        session.getTransaction().commit();

        return employees;
    }

    public void update(Employee e) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Hibernate.initialize(e);
        session.update(e);
        session.getTransaction().commit();

    }

    public void delete(String id) {
        Session session = factory.getCurrentSession();
        Employee p = (Employee) session.load(Employee.class, id);
        if (null != p) {
            session.delete(p);
        }
    }

}
