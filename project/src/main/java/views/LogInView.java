package views;

import controllers.LogInController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LogInView extends UnicastRemoteObject implements Serializable {
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    LogInController controller;
    Parent parentAdministratorView;
    Parent parentSalesmanView;

    public LogInView() throws RemoteException {
    }

    public void setUp(LogInController controller, Parent parentAdministratorView, Parent parentSalesmanView) {
        this.controller = controller;
        this.parentAdministratorView = parentAdministratorView;
        this.parentSalesmanView = parentSalesmanView;
    }

    public void handleLogIn(ActionEvent event) {
        if(username.getText().equals("") || password.getText().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setContentText("Username and password must not be empty!");
            alert.showAndWait();
        }
        else {
            try {
                String role = controller.handleLogIn(username.getText(), password.getText());

                if(role.equals("nonexistent")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR!");
                    alert.setContentText("Username and password do not match!");
                    alert.showAndWait();
                    return;
                }

                Stage stage = new Stage();
                stage.setTitle("Sales Company -  logged as " + username.getText() + ", " + role);
                if(role.equals("salesman"))
                    stage.setScene(new Scene(parentSalesmanView));
                else
                    stage.setScene(new Scene(parentAdministratorView));

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        System.exit(0);
                    }
                });

                stage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR!");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }
}
