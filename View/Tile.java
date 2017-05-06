import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Kruti on 06.05.2017.
 */
public class Tile extends Rectangle {

    public Tile ( int x, int y) {
        setWidth(Go.TILE_SIZE);
        setHeight(Go.TILE_SIZE);

        relocate(x * Go.TILE_SIZE, y * Go.TILE_SIZE);

        setFill(Color.valueOf("#ffad33"));
    }
}
