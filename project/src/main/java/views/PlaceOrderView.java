package views;

import controllers.PayPalController;
import controllers.PlaceOrderController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.List;

public class PlaceOrderView extends UnicastRemoteObject implements Serializable {
    @FXML
    ChoiceBox<String> productsList;
    @FXML
    ChoiceBox<String> paymentField;
    @FXML
    TextField quantityField;
    @FXML
    TextField streetField;
    @FXML
    TextField numberField;
    @FXML
    TextField cityField;
    @FXML
    TextField countyField;
    @FXML
    TextField countryField;
    PlaceOrderController controller;

    public PlaceOrderView() throws RemoteException {

    }

    public void setUp(PlaceOrderController controller)
    {
        this.controller = controller;
        setProductsList();
        setPaymentList();
    }

    private void setProductsList()
    {
        List<String> products = controller.getProducts();
        for(String productName: products)
            productsList.getItems().add(productName);
    }

    private void setPaymentList()
    {
        List<String> paymentTypes = Arrays.asList("cash", "paypal");
        for(String paymentType: paymentTypes)
            paymentField.getItems().add(paymentType);
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
        if (streetField.getText().equals("") || numberField.getText().equals("") || cityField.getText().equals("") || countyField.getText().equals("") || countryField.getText().equals("") || paymentField.getValue().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setContentText("All text field must be completed!");
            alert.showAndWait();
            return;
        }
        int addressId = controller.addAddress(streetField.getText(), Integer.valueOf(numberField.getText()), cityField.getText(), countyField.getText(), countryField.getText());

        if(paymentField.getValue().equals("paypal"))
        {
            if(controller.checkOrder()) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/PaypalPage.fxml"));
                try {
                    Scene scene = new Scene(loader.load());
                    Stage stage = new Stage();
                    stage.setTitle("Pay with PayPal");
                    stage.setScene(scene);

                    PayPalView view = loader.getController();
                    PayPalController new_controller = new PayPalController();
                    new_controller.setUp(controller.getService());
                    view.setUp(new_controller);

                    stage.show();
                    stage.setOnHiding(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent event) {
                            double total = controller.addOrder(addressId, paymentField.getValue());
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("PAYPAL PAYMENT ACCEPTED!");
                            alert.setContentText("You just payed " + total + "!");
                            alert.showAndWait();
                        }
                    });
                } catch (Exception e) {

                }
            }
        }
        else
        {
            double total = controller.addOrder(addressId, paymentField.getValue());
            if(total >= 0.0)
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Order Completed!");
                alert.setContentText("Order Total: " + total);
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR!");
                alert.setContentText("Not enough quantity for some products!");
                alert.showAndWait();
            }
        }

        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();
    }
}
