package services;

import model.domain.Order;
import model.domain.Product;
import model.domain.ProductsInOrder;
import model.domain.User;
import model.repository.OrdersRepository;
import model.repository.ProductsRepository;
import model.repository.UsersRepository;
import services.observer.Observable;
import services.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class Service implements Observable {
    List<Observer> observers;
    ProductsRepository productRepository;
    UsersRepository userRepository;
    OrdersRepository orderRepository;

    public Service(ProductsRepository productRepository, UsersRepository userRepository, OrdersRepository orderRepository) {
        observers = new ArrayList<>();
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    public String checkUser(String username, String password){
        User user = userRepository.findUser(username, password);
        if (user == null)
            return "nonexistent";
        return user.getRole();
    }

    public Product findProduct(String name){
        return productRepository.findProduct(name);
    }

    public int addOrder(){
        Order order = new Order();
        return orderRepository.addOrder(order);
    }

    public void addProductToOrder(int orderId, int productId, int quantity){
        ProductsInOrder productsInOrder = new ProductsInOrder(0, orderId, productId, quantity);
        orderRepository.addProductInOrder(productsInOrder);
        Product product = productRepository.findProduct(productId);
        product.extractFromQuantity(quantity);
        productRepository.update(product);
        notifyObservers();
    }

    public double getTotalPrice(int orderId){
        return orderRepository.getTotal(orderId);
    }

    public void addProduct(String name, double price, int quantity){
        Product newProduct = new Product(0, name, price, quantity);
        productRepository.add(newProduct);
        notifyObservers();
    }

    public void updateProduct(int id, String name, double price, int quantity){
        Product newProduct = new Product(id, name, price, quantity);
        productRepository.update(newProduct);
        notifyObservers();
    }

    public void deleteProduct(int id, String name, double price, int quantity){
        Product newProduct = new Product(id, name, price, quantity);
        productRepository.delete(newProduct);
        notifyObservers();
    }

    /**
     * selects all products from database
     * @return list of existing products
     */
    public List<Product> getAllProducts(){
        List<Product> products = productRepository.getAll();
        return products;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        observers.stream().forEach(x->x.update());
    }
}
