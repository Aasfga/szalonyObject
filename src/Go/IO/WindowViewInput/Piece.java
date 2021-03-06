package Go.IO.WindowViewInput;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;

import java.io.File;
import java.nio.file.Paths;

import static Go.IO.WindowViewInput.Go.TILE_SIZE;
import static Go.IO.WindowViewInput.Tile.thingsToDoWhenEntered;

public class Piece extends StackPane{

    public Piece(PieceType type, int x, int y) {

        relocate(x*TILE_SIZE, y*TILE_SIZE);
        Ellipse bg = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);

        if( type == PieceType.BLACK ) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml_files/Back.fxml"));

            File file = new File("src/Go/IO/WindowViewInput/images/b.png");
            Image img = new Image(file.toURI().toString());
            ImagePattern image = new ImagePattern(img);
            bg.setFill(image);
        }
        else {
            File file = new File("src/Go/IO/WindowViewInput/images/w.png");
            Image img = new Image(file.toURI().toString());
            ImagePattern image = new ImagePattern(img);
            bg.setFill(image);
        }
        bg.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
        bg.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2 + TILE_SIZE * 0.07);
        getChildren().addAll(bg);
        setOnMouseEntered(e->thingsToDoWhenEntered(x,y));
    }

}
