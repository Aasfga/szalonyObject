package GoGame.View;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import static GoGame.View.Go.TILE_SIZE;
import static GoGame.View.Go.createContent;
import static GoGame.View.View.array;
import static GoGame.View.View.lastx;
import static GoGame.View.View.lasty;

public class Piece extends StackPane{

    public Piece(PieceType type, int x, int y) {

        relocate(x*TILE_SIZE, y*TILE_SIZE);
        Ellipse bg = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
        bg.setFill(type == PieceType.BLACK ? Color.BLACK : Color.WHITE);
        bg.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
        bg.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2 + TILE_SIZE * 0.07);
        getChildren().addAll(bg);
        setOnMouseEntered(e->thingsToDoWhenClicked2(x,y));

    }
    private void thingsToDoWhenClicked2(int x, int y) {

        if( ( x != lastx || y != lasty ) && array[y][x] == 0) {
            array[lasty][lastx] = 0;
            array[y][x] = 3;
            lasty = y;
            lastx = x;
            Go.scene.setRoot(createContent());
        }
        else if( ( x != lastx || y != lasty ) && array[y][x] != 0) {
            array[lasty][lastx] = 0;
            lasty = 19;
            lastx = 19;
            Go.scene.setRoot(createContent());
        }
    }
}
