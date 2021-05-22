package controllers;

import model.domain.Product;
import services.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlaceOrderController {
    Service service;
    List<Product> productsList;
    List<Integer> productsQuantities;

    public PlaceOrderController(){

    }

    public void setUp(Service service) {
        productsList = new ArrayList<>();
        productsQuantities = new ArrayList<>();
        this.service = service;
    }

    public List<String> getProducts(){
        return service.getAllProducts().stream().map(p -> p.getName()).collect(Collectors.toList());
    }

    public Service getService() {
        return service;
    }

    public void addProduct(String productName, Integer quantity){
        Product product = service.findProduct(productName);
        productsList.add(product);
        productsQuantities.add(quantity);
    }
    public int addAddress(String street, Integer number, String city, String county, String country){
        int addressId = service.addAddress(street, number, city, county, country);
        return addressId;
    }

    private boolean checkOrder(){
        for (int i = 0; i < productsList.size(); i++)
            if(!productsList.get(i).extractFromQuantity(productsQuantities.get(i)))
                return false;
            return true;
    }

    public double addOrder(int addressId, String payment){
        if(!checkOrder())
            return -1;
        int orderId = service.addOrder(addressId, payment);
        for (int i = 0; i < productsList.size(); i++)
            service.addProductToOrder(orderId, productsList.get(i).getId(), productsQuantities.get(i));
        return service.getTotalPrice(orderId);
    }
}
