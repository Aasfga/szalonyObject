package Go.IO.WindowViewInput;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("fxml_files/MainScreen.fxml"));
        StackPane stackPane = loader.load();
        primaryStage.setTitle("GO - game");
        primaryStage.setScene(new Scene(stackPane, 985, 775));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
