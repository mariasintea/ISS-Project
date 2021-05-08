package model.repository;

import model.domain.Order;
import model.domain.ProductsInOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class OrdersRepository {
    SessionFactory sessionFactory;

    public OrdersRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    /**
     * adds order to database
     * @param order - Order to be added
     */
    public int addOrder(Order order){
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(order);
                int id = (Integer) session.createQuery("select max(o.id) from Order o").uniqueResult();
                tx.commit();
                return id;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return 0;
    }

    /**
     * adds products in order to database
     * @param productsInOrder - Product in order to be added
     */
    public void addProductInOrder(ProductsInOrder productsInOrder){
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(productsInOrder);
                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
    }


    public int getTotal(Integer orderId){
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                int total = (Integer) session.createQuery("select sum(p.price * o.quantity) from ProductsInOrder o, Product p where p.id = o.productId and o.orderId = :orderId").setParameter("orderId", orderId).uniqueResult();
                tx.commit();
                System.out.println(total);
                return total;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return 0;
    }
}
