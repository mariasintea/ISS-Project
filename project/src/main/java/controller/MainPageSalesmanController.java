package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Product;
import services.ProductsService;
import services.observer.Observer;

import java.util.List;

public class MainPageSalesmanController implements Observer{
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
    ProductsService service;

    public MainPageSalesmanController() {
    }

    public void setUp(ProductsService service){
        this.service = service;
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

    void loadTable()
    {
        service.initialize();
        List<Product> productList = service.getAll();
        model.setAll(productList);
        service.close();
    }

    public void handlePlaceOrder(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/PlaceOrderPage.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Place Order");
            stage.setScene(scene);

            PlaceOrderController controller = loader.getController();
            controller.setUp(service);

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
