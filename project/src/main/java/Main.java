import controller.MainPageAdministratorController;
import controller.MainPageSalesmanController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ProductsService;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            initView(stage);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void initView(Stage primaryStage) throws IOException {

        ProductsService productsService = new ProductsService();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/MainPageAdministratorPage.fxml"));
        //loader.setLocation(getClass().getResource("/fxml/MainPageSalesmanPage.fxml"));
        AnchorPane pageLayout = loader.load();
        primaryStage.setScene(new Scene(pageLayout));

        MainPageAdministratorController controller = loader.getController();
        //MainPageSalesmanController controller = loader.getController();
        controller.setUp(productsService);
    }
}

