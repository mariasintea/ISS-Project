package views;

import controllers.MainPageSalesmanController;
import controllers.PlaceOrderController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.domain.Product;
import services.IService;
import services.observer.Observer;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class MainPageSalesmanView extends UnicastRemoteObject implements Serializable, Observer{
    @FXML
    TableView<Product> productsTable;
    @FXML
    TableColumn<Product, Integer> idColumn;
    @FXML
    TableColumn<Product, String> nameColumn;
    @FXML
    TableColumn<Product, Double> priceColumn;
    @FXML
    TableColumn<Product, Integer> availableQuantityColumn;
    ObservableList<Product> model = FXCollections.observableArrayList();
    MainPageSalesmanController controller;

    public MainPageSalesmanView() throws RemoteException {
    }

    public void setUp(MainPageSalesmanController controller){
        this.controller = controller;
        IService service = controller.getService();
        service.addObserver(this);
        loadTable();
    }

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        availableQuantityColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("availableQuantity"));
        productsTable.setItems(model);
    }

    /**
     * loads the elements from database into the TableView productsTable
     */
    void loadTable()
    {
        List<Product> productList = controller.getProducts();
        model.setAll(productList);
    }

    /**
     * opens Place Order Page
     */
    public void handlePlaceOrder(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/PlaceOrderPage.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Place Order");
            stage.setScene(scene);

            PlaceOrderView view = loader.getController();
            PlaceOrderController new_controller = new PlaceOrderController();
            new_controller.setUp(controller.getService());
            view.setUp(new_controller);

            stage.show();
        }
        catch (Exception e)
        {

        }
    }

    @Override
    public void update() {
        loadTable();
    }
}

