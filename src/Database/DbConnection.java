package Database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import Model.Employee;
import Model.Member;


public class DbConnection {
	
private static SessionFactory factory = null;
    
    public static SessionFactory getSession(){
    
        if(factory == null){
        
//        	StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
//    		
//    		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
//    		
//    		factory = meta.getSessionFactoryBuilder().build();
        	
        	 factory=new Configuration().configure().addAnnotatedClass(Model.Person.class).addAnnotatedClass(Model.Employee.class).addAnnotatedClass(Model.Member.class).addAnnotatedClass(Model.Book.class).addAnnotatedClass(Model.Issue.class).buildSessionFactory();
        	
    	
    }
        
       return factory;
    }
	
	

}
