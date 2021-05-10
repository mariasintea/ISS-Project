package views;

import controllers.PlaceOrderController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class PlaceOrderView {
    @FXML
    ChoiceBox<String> productsList;
    @FXML
    TextField quantityField;
    PlaceOrderController controller;

    public PlaceOrderView(){

    }

    public void setUp(PlaceOrderController controller)
    {
        this.controller = controller;
        setProductsList();
    }

    private void setProductsList()
    {
        List<String> products = controller.getProducts();
        for(String productName: products)
            productsList.getItems().add(productName);
    }

    public void handleAddProduct(){
        String name = productsList.getValue();
        if (name == null && quantityField.getText().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setContentText("All text field must be completed!");
            alert.showAndWait();
            return;
        }
        controller.addProduct(name, Integer.valueOf(quantityField.getText()));
        productsList.getItems().remove(name);
        quantityField.setText("");
    }

    public void handlePlaceOrder(ActionEvent event){
        double total = controller.addOrder();
        if (total >= 0.0)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Order Completed!");
            alert.setContentText("Order Total: " + total);
            alert.showAndWait();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setContentText("Not enough quantity for some products!");
            alert.showAndWait();
        }

        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();
    }
}
