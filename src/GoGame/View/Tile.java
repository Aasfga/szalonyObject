package GoGame.View;

import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.TimeUnit;

import static GoGame.View.Go.createContent;
import static GoGame.View.Go.flag;
import static GoGame.View.Move.setMove;
import static GoGame.View.View.*;

public class Tile extends Rectangle {

    public Tile(int x, int y) {
        setWidth(Go.TILE_SIZE);
        setHeight(Go.TILE_SIZE);
        relocate(x * Go.TILE_SIZE, y * Go.TILE_SIZE);
        setFill(Color.valueOf("#ffad33"));
        setOnMouseClicked(e -> thingsToDoWhenClicked(x, y)); // Must check what if doubleclicked
        setOnMouseEntered(e->thingsToDoWhenClicked2(x,y));
    }

    private void thingsToDoWhenClicked(int x, int y) {
        array[lasty][lastx] = 0;
        lastx = 19;
        lasty = 19;
        setMove(x, y, flag ? 2 : 1);
        Go.scene.setRoot(createContent());
        while (!judgeDidHisJob) {
            Go.scene.setRoot(createContent());
            try
            {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch(InterruptedException e)
            {
            }
        }
        judgeDidHisJob = false;
        Go.scene.setRoot(createContent());
        flag =  !flag;
    }

    private void thingsToDoWhenClicked2(int x, int y) {

        int cos  = 2 ;

        if( ( x != lastx || y != lasty ) && array[y][x] == 0) {
            array[lasty][lastx] = 0;
            array[y][x] = 3;
            lasty = y;
            lastx = x;
            Go.scene.setRoot(createContent());
        }
    }

}
