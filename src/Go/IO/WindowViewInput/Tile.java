package Go.IO.WindowViewInput;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.concurrent.TimeUnit;
import static Go.IO.WindowViewInput.Go.createContent;
import static Go.IO.WindowViewInput.Go.flag;
import static Go.IO.WindowViewInput.Move.setMove;
import static Go.IO.WindowViewInput.WindowView.*;

public class Tile extends Rectangle {

    public Tile(int x, int y) {
        setWidth(Go.TILE_SIZE);
        setHeight(Go.TILE_SIZE);
        relocate(x * Go.TILE_SIZE, y * Go.TILE_SIZE);
        setFill(Color.valueOf("#ffad33"));
        setOnMouseClicked(e -> thingsToDoWhenClicked(x, y)); // Must check what if doubleclicked
        setOnMouseEntered(e->thingsToDoWhenEntered(x,y));
    }

    static void thingsToDoWhenClicked(int x, int y) {
        array[lasty][lastx] = 0;
        lastx = size-1;
        lasty = size-1;
        setMove(x, y);
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

    static void thingsToDoWhenEntered(int x, int y) {

        if( ( x != lastx || y != lasty ) && array[y][x] == 0) {
            array[lasty][lastx] = 0;
            array[y][x] = 3;
            lasty = y;
            lastx = x;
            Go.scene.setRoot(createContent());
        }
        else if( ( x != lastx || y != lasty ) && array[y][x] != 0) {
            array[lasty][lastx] = 0;
            lasty = size-1;
            lastx = size-1;
            Go.scene.setRoot(createContent());
        }
    }

}
