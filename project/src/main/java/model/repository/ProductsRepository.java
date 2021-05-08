package model.repository;

import model.domain.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ProductsRepository{
    SessionFactory sessionFactory;

    public ProductsRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public Product findProduct(String name){
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Product product = (Product) session.createQuery("from Product p where p.name = :name", Product.class).setParameter("name", name).uniqueResult();
                tx.commit();
                return product;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }

    public Product findProduct(Integer id){
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Product product = (Product) session.createQuery("from Product p where p.id = :id", Product.class).setParameter("id", id).uniqueResult();
                tx.commit();
                return product;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }

    /**
     * adds product to database
     * @param product - Product to be added
     */
    public void add(Product product){
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(product);
                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    /**
     * updates product in database
     * @param product - Product to be updated
     */
    public void update(Product product){
        try(Session session = sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx = session.beginTransaction();
                session.update(product);
                tx.commit();
            } catch(RuntimeException ex){
                if (tx!=null)
                    tx.rollback();
            }
        }
    }

    /**
     * deletes product from database
     * @param product - Product to be deleted
     */
    public void delete(Product product){
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.delete(product);
                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    /**
     * selects all products from database
     * @return list of existing products
     */
    public List<Product> getAll(){
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<Product> products = session.createQuery("from Product", Product.class).list();
                tx.commit();
                return products;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }
}
