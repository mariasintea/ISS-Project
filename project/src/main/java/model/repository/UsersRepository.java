package model.repository;

import model.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class UsersRepository {
    SessionFactory sessionFactory;

    public UsersRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public User findUser(String username, String password){
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                User user = (User) session.createQuery("from User u where u.password = :password and u.username = :username", User.class).setParameter("password", password).setParameter("username", username).uniqueResult();
                tx.commit();
                return user;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }
}
