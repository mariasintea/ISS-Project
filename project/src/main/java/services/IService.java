package services;

import model.domain.*;
import services.observer.Observable;

import java.util.List;

public interface IService extends Observable {
    String checkUser(String username, String password);

    boolean checkPayPalUser(String username, String password);

    Product findProduct(String name);

    int addOrder(int address, String payment);

    void addProductToOrder(int orderId, int productId, int quantity);

    double getTotalPrice(int orderId);

    int addAddress(String street, int number, String city, String county, String country);

    void addProduct(String name, double price, int quantity);

    void updateProduct(int id, String name, double price, int quantity);

    void deleteProduct(int id, String name, double price, int quantity);

    List<Product> getAllProducts();
}
