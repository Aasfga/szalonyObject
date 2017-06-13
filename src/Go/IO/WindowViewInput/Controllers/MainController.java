package Go.IO.WindowViewInput.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.awt.*;
import java.io.IOException;

public class MainController {
    @FXML
    private StackPane mainStackPane;
    private Pane menu;

    @FXML
    public void initialize() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml_files/MenuScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        menu = pane;
        MenuController menuController = loader.getController();
        menuController.setMainController(this);
        setScreen(pane);
    }

    public void setScreen(Pane pane){
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(pane);
    }

    public void setMenu(){
        setScreen(menu);
    }

}
