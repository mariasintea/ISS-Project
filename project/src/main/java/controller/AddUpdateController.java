package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Product;
import services.ProductsService;

public class AddUpdateController {
    @FXML
    Button sendButton;
    @FXML
    Label title;
    @FXML
    TextField nameField;
    @FXML
    TextField priceField;
    @FXML
    TextField quantityField;
    ProductsService service;
    Product selectedProduct;

    public AddUpdateController() {
    }

    public void setUp(String operation, ProductsService service){
        this.service = service;
        switch (operation){
            case "add": {
                sendButton.setText("Add");
                title.setText("Add Product");
            }break;
            case "update": {
                sendButton.setText("Update");
                title.setText("Update Product");
            }break;
        }
    }

    public void setSelectedProduct(Product product){
        this.selectedProduct = product;
        nameField.setText(product.getName());
        priceField.setText(String.valueOf(product.getPrice()));
        quantityField.setText(String.valueOf(product.getAvailableQuantity()));
    }

    public void handleSendResponse(ActionEvent event) {
        if(nameField.getText().equals("") || priceField.getText().equals("") || quantityField.getText().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setContentText("All text field must be completed!");
            alert.showAndWait();
            return;
        }

        service.initialize();
        if(sendButton.getText().equals("Update")) {
            int id = selectedProduct.getId();
            Product newProduct = new Product(id, nameField.getText(), Double.parseDouble(priceField.getText()), Integer.parseInt(quantityField.getText()));
            service.update(newProduct);
        }
        else{
            int id = service.getNextId();
            Product newProduct = new Product(id, nameField.getText(), Double.parseDouble(priceField.getText()), Integer.parseInt(quantityField.getText()));
            service.add(newProduct);
        }
        service.close();

        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();
    }
}
