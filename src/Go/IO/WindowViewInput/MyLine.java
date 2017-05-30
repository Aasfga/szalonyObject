package Go.IO.WindowViewInput;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import static Go.IO.WindowViewInput.Tile.thingsToDoWhenClicked;
import static Go.IO.WindowViewInput.Tile.thingsToDoWhenEntered;

public class MyLine extends Line{
    public MyLine ( int x, int y, boolean row, boolean part1, boolean part2){

        if( row ) {
            if (part1) {
                setStartX(x * Go.TILE_SIZE );
            } else {
                setStartX(x * Go.TILE_SIZE + Go.TILE_SIZE / 2);
            }

            if (part2) {
                setEndX((x+1) * Go.TILE_SIZE-1);
            } else {
                setEndX(x * Go.TILE_SIZE + Go.TILE_SIZE/2);
            }

            setStartY(y * Go.TILE_SIZE + Go.TILE_SIZE/2);
            setEndY(y * Go.TILE_SIZE + Go.TILE_SIZE/2);
        }
        else {
            setStartX(x * Go.TILE_SIZE + Go.TILE_SIZE/2);
            setEndX(x * Go.TILE_SIZE + Go.TILE_SIZE/2);

            if (part1) {
                setStartY(y * Go.TILE_SIZE );
            } else {
                setStartY(y * Go.TILE_SIZE + Go.TILE_SIZE / 2);
            }

            if (part2) {
                setEndY((y+1) * Go.TILE_SIZE-1);
            } else {
                setEndY(y * Go.TILE_SIZE + Go.TILE_SIZE/2);
            }
        }

        setStroke(Color.BLACK);
        setOnMouseClicked(e -> thingsToDoWhenClicked(x, y)); // Must check what if doubleclicked
        setOnMouseEntered(e->thingsToDoWhenEntered(x,y));
    }

}
