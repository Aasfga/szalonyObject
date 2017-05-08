package GoGame.View;

import GoGame.View.Go;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static GoGame.View.Go.createContent;
import static GoGame.View.View.array;

public class Tile extends Rectangle {

    public boolean flagmouse =  false;

    public Tile ( int x, int y) {
        setWidth(Go.TILE_SIZE);
        setHeight(Go.TILE_SIZE);
        relocate(x * Go.TILE_SIZE, y * Go.TILE_SIZE);
        setFill(Color.valueOf("#ffad33"));
        if(!flagmouse) {
            flagmouse = true;
            setOnMouseClicked(e -> open(x, y));
        }
    }

    private void open(int a,int b)  {
        if (Go.flag) {
            array[b][a] = 1;
            Go.flag = false;
        } else {
            array[b][a] = 2;
            Go.flag = true;
        }
        Go.scene.setRoot(createContent());
    }
}
