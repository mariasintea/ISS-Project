package services.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {
    private static SessionFactory sessionFactory;

    public HibernateUtils(){

    }

    /**
     * initializes session
     */
    public static void initialize() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    public SessionFactory getSessionFactory(){
        initialize();
        return sessionFactory;
    }

    /**
     * closes session
     */
    public static void close(){
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }

    }
}
