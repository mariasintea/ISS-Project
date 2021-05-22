package model.repository;

import model.domain.PayPalUser;
import model.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class PayPalUsersRepository {
    SessionFactory sessionFactory;

    public PayPalUsersRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public PayPalUser findUser(String username, String password){
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                PayPalUser user = (PayPalUser) session.createQuery("from PayPalUser u where u.password = :password and u.username = :username", PayPalUser.class).setParameter("password", password).setParameter("username", username).uniqueResult();
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
