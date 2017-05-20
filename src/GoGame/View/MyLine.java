package GoGame.View;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.concurrent.TimeUnit;

import static GoGame.View.Go.createContent;
import static GoGame.View.Go.flag;
import static GoGame.View.Move.setMove;
import static GoGame.View.View.*;

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
