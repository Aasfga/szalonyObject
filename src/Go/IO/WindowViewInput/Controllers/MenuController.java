package Go.IO.WindowViewInput.Controllers;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import Go.IO.WindowViewInput.Go;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.IOException;

public class MenuController {

    private MainController mainController;

    @FXML
    public void newgame() {

        Pane root = new Pane();
        root.setPrefSize(1000, 800);
        Pane go = new Go();

//
        File file = new File("tło.jpg");
        Image img = new Image(file.toURI().toString());
        ImagePattern image = new ImagePattern(img);

        Rectangle r = new Rectangle(1000,800);
        r.setFill(image);
        root.getChildren().add(r);
//

        Rectangle whiteplayer = new Rectangle(40,40);
        whiteplayer.setFill(Color.WHITE);
        whiteplayer.relocate(800,200);

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml_files/Back.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BackController backController = loader.getController();
        backController.setMainController(mainController);

        root.getChildren().add(go);
        root.getChildren().add(whiteplayer);
        root.getChildren().add(pane);
        mainController.setScreen(root);
    }


    public void loadgame() {
        System.out.println("YEAAAH"); //TODO (czyt. 'todo' )
    }

    public void exit() {
        Platform.exit();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
