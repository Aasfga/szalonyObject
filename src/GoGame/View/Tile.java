package GoGame.View;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static GoGame.View.Go.createContent;
import static GoGame.View.Go.flag;
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
        setMove(x, y, flag ? 2 : 1);
        while (!judgeDidHisJob) {
            try
            {
                wait();
            } catch(InterruptedException e)
            {
                break;
            }
        }

        judgeDidHisJob = false;
        Go.scene.setRoot(createContent());
        flag =  !flag;
    }
}