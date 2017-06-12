package Go.IO.WindowViewInput;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;

import java.io.File;

import static Go.IO.WindowViewInput.Go.movecolour;
import static Go.IO.WindowViewInput.Tile.thingsToDoWhenClicked;
import static javafx.scene.paint.Color.GREEN;

public class Light extends StackPane {

    public Light(int x, int y) {

        relocate(x* Go.TILE_SIZE, y* Go.TILE_SIZE);
        Ellipse bg = new Ellipse(Go.TILE_SIZE * 0.3125, Go.TILE_SIZE * 0.26);
        if(movecolour) {
            File file = new File("blight.png");
            Image img = new Image(file.toURI().toString());
            ImagePattern image = new ImagePattern(img);
            bg.setFill(image);
        }
        else {
            File file = new File("wlight.png");
            Image img = new Image(file.toURI().toString());
            ImagePattern image = new ImagePattern(img);
            bg.setFill(image);
        }
        bg.setTranslateX((Go.TILE_SIZE - Go.TILE_SIZE * 0.3125 * 2) / 2);
        bg.setTranslateY((Go.TILE_SIZE - Go.TILE_SIZE * 0.26 * 2) / 2 + Go.TILE_SIZE * 0.07);
        getChildren().addAll(bg);
        setOnMouseClicked(e -> thingsToDoWhenClicked(x, y)); // Must check what if doubleclicked
    }
}