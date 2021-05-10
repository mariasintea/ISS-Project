package controllers;

import model.domain.Product;
import services.Service;

public class AddUpdateController {
    Service service;
    String operation;
    Product selectedProduct;

    public AddUpdateController() {
    }

    public void setUp(String operation, Service service){
        this.service = service;
        this.operation = operation;
    }

    public void setSelectedProduct(Product product) {
        this.selectedProduct = product;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void handleSendResponse(String name, String price, String quantity) {
        if(operation.equals("update")) {
            service.updateProduct(selectedProduct.getId(), name, Double.parseDouble(price), Integer.parseInt(quantity));
        }
        else{
            service.addProduct(name, Double.parseDouble(price), Integer.parseInt(quantity));
        }
    }
}
