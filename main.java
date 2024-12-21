import javafx.application.Application;
import javafx.stage.Stage;

public class InventoryManagerApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        InventorySystem inventorySystem = new InventorySystem();
        LoginScreen loginScreen = new LoginScreen(inventorySystem);
        loginScreen.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
