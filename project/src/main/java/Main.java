import controllers.LogInController;
import controllers.MainPageAdministratorController;
import controllers.MainPageSalesmanController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.domain.PayPalUser;
import model.repository.OrdersRepository;
import model.repository.PayPalUsersRepository;
import model.repository.UsersRepository;
import org.hibernate.SessionFactory;
import model.repository.ProductsRepository;
import services.Service;
import services.utils.HibernateUtils;
import views.LogInView;
import views.MainPageAdministratorView;
import views.MainPageSalesmanView;

import java.io.IOException;

public class Main extends Application {
    HibernateUtils hibernateUtils = new HibernateUtils();

    @Override
    public void start(Stage stage) {
        try {
            hibernateUtils.initialize();
            initView(stage);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        stage.show();
    }

    @Override
    public void stop(){
        hibernateUtils.close();
    }

    public static void main() {
        launch();
    }

    private void initView(Stage primaryStage) throws IOException {
        SessionFactory sessionFactory = hibernateUtils.getSessionFactory();
        ProductsRepository productsRepository = new ProductsRepository(sessionFactory);
        UsersRepository usersRepository = new UsersRepository(sessionFactory);
        OrdersRepository ordersRepository = new OrdersRepository(sessionFactory);
        PayPalUsersRepository paypalRepository = new PayPalUsersRepository(sessionFactory);
        Service service = new Service(productsRepository, usersRepository, ordersRepository, paypalRepository);

        FXMLLoader loaderMainPageAdministrator = new FXMLLoader();
        loaderMainPageAdministrator.setLocation(getClass().getResource("/fxml/MainPageAdministrator.fxml"));
        MainPageAdministratorController controllerAdministrator = new MainPageAdministratorController();
        controllerAdministrator.setUp(service);
        Parent rootAdministrator = loaderMainPageAdministrator.load();
        MainPageAdministratorView viewAdministrator = loaderMainPageAdministrator.getController();
        viewAdministrator.setUp(controllerAdministrator);

        FXMLLoader loaderMainPageSalesman = new FXMLLoader(getClass().getResource("/fxml/MainPageSalesman.fxml"));
        MainPageSalesmanController controllerSalesman = new MainPageSalesmanController();
        controllerSalesman.setUp(service);
        Parent rootSalesman = loaderMainPageSalesman.load();
        MainPageSalesmanView viewSalesman = loaderMainPageSalesman.getController();
        viewSalesman.setUp(controllerSalesman);

        FXMLLoader loaderLogin = new FXMLLoader(getClass().getResource("/fxml/LogInPage.fxml"));
        Parent root = loaderLogin.load();
        LogInView viewLogIn = loaderLogin.getController();
        LogInController controllerLogIn = new LogInController();
        controllerLogIn.setUp(service);
        viewLogIn.setUp(controllerLogIn, rootAdministrator, rootSalesman);

        primaryStage.setTitle("Log In");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}

