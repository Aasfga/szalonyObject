package GoGame.View;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static GoGame.View.Go.createContent;
import static GoGame.View.Go.flagOfColour;
import static GoGame.View.Move.setMove;
import static GoGame.View.View.judgeDidHisJob;

public class Tile extends Rectangle {

    public Tile(int x, int y) {
        setWidth(Go.TILE_SIZE);
        setHeight(Go.TILE_SIZE);
        relocate(x * Go.TILE_SIZE, y * Go.TILE_SIZE);
        setFill(Color.valueOf("#ffad33"));
        setOnMouseClicked(e -> thingsToDoWhenClicked(x, y)); // Must check what if doubleclicked
    }

    private void thingsToDoWhenClicked(int x, int y) {
        judgeDidHisJob = false;
        setMove(x, y, flagOfColour ? 2 : 1);
        while (!judgeDidHisJob) {}

        Go.scene.setRoot(createContent());
    }
}