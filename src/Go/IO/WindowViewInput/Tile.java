package Go.IO.WindowViewInput;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.TimeUnit;

import static Go.IO.WindowViewInput.Go.flag;
import static Go.IO.WindowViewInput.Move.setMove;
import static Go.IO.WindowViewInput.WindowView.*;

public class Tile extends Rectangle {

    public static Go paneThatImOn;

    public Tile(int x, int y, Go pane) {
        paneThatImOn = pane;
        setWidth(Go.TILE_SIZE);
        setHeight(Go.TILE_SIZE);
        relocate(x * Go.TILE_SIZE, y * Go.TILE_SIZE);
        setFill(Color.valueOf("#ffad33"));
        setOnMouseClicked(e -> thingsToDoWhenClicked(x, y)); // Must check what if doubleclicked
        setOnMouseEntered(e->thingsToDoWhenEntered(x,y));
    }

    static synchronized void thingsToDoWhenClicked(int x, int y) {
        array[lasty][lastx] = 0;
        lastx = size-1;
        lasty = size-1;
        array[y][x] = 0;
        setMove(x, y);
        while (!judgeDidHisJob) {
            try
            {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch(InterruptedException e)
            {}
        }
        judgeDidHisJob = false;
        paneThatImOn.setGoPane();
        flag =  !flag;
    }

    static void thingsToDoWhenEntered(int x, int y) {

        if( ( x != lastx || y != lasty ) && array[y][x] == 0) {
            array[lasty][lastx] = 0;
            array[y][x] = 3;
            lasty = y;
            lastx = x;
            paneThatImOn.setGoPane();
//            Go.scene.setRoot(createContent()); // TODO trzeba odświeżyć
        }
        else if( ( x != lastx || y != lasty ) && array[y][x] != 0) {
            array[lasty][lastx] = 0;
            lasty = size-1;
            lastx = size-1;
            paneThatImOn.setGoPane();
        }
    }

}
