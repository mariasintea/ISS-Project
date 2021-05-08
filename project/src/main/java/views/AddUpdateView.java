package views;

import controllers.AddUpdateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.domain.Product;

public class AddUpdateView {
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
    AddUpdateController controller;

    /**
     * makes the set up for controller
     * @param controller - current controller
     * @param operation -> add/update
     */
    public void setUp(String operation, AddUpdateController controller){
        this.controller = controller;
        switch (operation){
            case "add": {
                sendButton.setText("Add");
                title.setText("Add Product");
            }break;
            case "update": {
                sendButton.setText("Update");
                title.setText("Update Product");
                setSelectedProduct();
            }break;
        }
    }

    /**
     * sets fills fields with selected Product's info
     */
    public void setSelectedProduct(){
        Product product = controller.getSelectedProduct();
        nameField.setText(product.getName());
        priceField.setText(String.valueOf(product.getPrice()));
        quantityField.setText(String.valueOf(product.getAvailableQuantity()));
    }

    /**
     * creates Product object and adds/updates it in the database
     * closes window if successful
     * shows error message if not successful
     * @param event - ActionEvent
     */
    public void handleSendResponse(ActionEvent event) {
        if(nameField.getText().equals("") || priceField.getText().equals("") || quantityField.getText().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setContentText("All text field must be completed!");
            alert.showAndWait();
            return;
        }

        controller.handleSendResponse(nameField.getText(), priceField.getText(), quantityField.getText());

        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();
    }
}
