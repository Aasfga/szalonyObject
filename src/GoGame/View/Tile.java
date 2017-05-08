package GoGame.View;

import GoGame.View.Go;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static GoGame.View.Go.createContent;
import static GoGame.View.Go.flag;
import static GoGame.View.Move.setMove;
import static GoGame.View.View.array;

public class Tile extends Rectangle {

    public boolean flagmouse =  false;

    public Tile ( int x, int y) {
        setWidth(Go.TILE_SIZE);
        setHeight(Go.TILE_SIZE);
        relocate(x * Go.TILE_SIZE, y * Go.TILE_SIZE);
        setFill(Color.valueOf("#ffad33"));
        setOnMouseClicked(e -> setMove(x,y,flag));
    }

    private void open(int a,int b)  {
        if( array[b][a] == 0) {

            Go.scene.setRoot(createContent());
        }
    }
}
