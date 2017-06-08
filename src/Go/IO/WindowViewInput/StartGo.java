package Go.IO.WindowViewInput;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;

public class StartGo extends Application{

    static int  TILE_SIZE = 50;
    static int  WIDTH = 20;
    static int  HEIGHT = 20;
    static int PANEL_SIZE = 200;
    static Scene scene;
    static Pane go;

    public static Parent createContent() {

        Pane root = new Pane();
        root.setPrefSize(1000, 800);
        go = new Go();

//
        File file = new File("board.jpg");
        Image img = new Image(file.toURI().toString());
        ImagePattern image = new ImagePattern(img);

        Rectangle r = new Rectangle(1000,800);
        r.setFill(image);
        root.getChildren().add(r);
//
        root.getChildren().add(go);
        return root;
    }

    public void start(Stage primaryStage) {
        scene = new Scene(createContent());
        primaryStage.setTitle("GO");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}