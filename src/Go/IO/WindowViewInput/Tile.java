package Go.IO.WindowViewInput;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.nio.file.Paths;
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
        File file = new File("img.png");
        Image img = new Image(file.toURI().toString());
        ImagePattern image = new ImagePattern(img);
        setFill(image);
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
