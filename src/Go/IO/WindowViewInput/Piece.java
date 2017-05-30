package Go.IO.WindowViewInput;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import static Go.IO.WindowViewInput.Go.TILE_SIZE;
import static Go.IO.WindowViewInput.Tile.thingsToDoWhenEntered;

public class Piece extends StackPane{

    public Piece(PieceType type, int x, int y) {

        relocate(x*TILE_SIZE, y*TILE_SIZE);
        Ellipse bg = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
        bg.setFill(type == PieceType.BLACK ? Color.BLACK : Color.WHITE);
        bg.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
        bg.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2 + TILE_SIZE * 0.07);
        getChildren().addAll(bg);
        setOnMouseEntered(e->thingsToDoWhenEntered(x,y));
    }

}
