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
        return service.getAll().stream().map(p -> p.getName()).collect(Collectors.toList());
    }

    public void addProduct(String productName, Integer quantity){
        Product product = service.findProduct(productName);
        productsList.add(product);
        productsQuantities.add(quantity);
    }

    public boolean checkOrder(){
        for (int i = 0; i < productsList.size(); i++)
            if(!productsList.get(i).extractFromQuantity(productsQuantities.get(i)))
                return false;
            return true;
    }

    public int addOrder(){
        if(!checkOrder())
            return -1;
        int orderId = service.addOrder();
        for (int i = 0; i < productsList.size(); i++)
            service.addProductToOrder(orderId, productsList.get(i).getId(), productsQuantities.get(i));
        return service.getTotalPrice(orderId);
    }
}
