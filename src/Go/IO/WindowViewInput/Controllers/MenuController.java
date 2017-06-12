package Go.IO.WindowViewInput.Controllers;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import Go.IO.WindowViewInput.Go;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.File;

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
        root.getChildren().add(go);
        mainController.setScreen(root);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
