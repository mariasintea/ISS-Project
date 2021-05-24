package views;

import controllers.PayPalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PayPalView extends UnicastRemoteObject implements Serializable {
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    PayPalController controller;

    public PayPalView() throws RemoteException {
    }

    public void setUp(PayPalController controller) {
        this.controller = controller;
    }

    public void handleConfirmPayment(ActionEvent event) {
        if(username.getText().equals("") || password.getText().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setContentText("Username and password must not be empty!");
            alert.showAndWait();
            return;
        }
        boolean ok = controller.handleCheckUser(username.getText(), password.getText());
        if(!ok)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setContentText("Username or password incorrect!");
            alert.showAndWait();
        }
        else
        {
            Node node = (Node) event.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.hide();
        }
    }
}
