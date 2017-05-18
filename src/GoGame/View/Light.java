package GoGame.View;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import java.util.concurrent.TimeUnit;

import static GoGame.View.Go.createContent;
import static GoGame.View.Go.flag;
import static GoGame.View.Move.setMove;
import static GoGame.View.View.*;
import static GoGame.View.View.judgeDidHisJob;
import static javafx.scene.paint.Color.GREEN;
import static sun.dc.pr.Rasterizer.TILE_SIZE;

/**
 * Created by lukasz on 18.05.2017.
 */
public class Light extends StackPane {

    public Light(int x, int y) {

        relocate(x* Go.TILE_SIZE, y* Go.TILE_SIZE);
        Ellipse bg = new Ellipse(Go.TILE_SIZE * 0.3125, Go.TILE_SIZE * 0.26);
        bg.setFill(GREEN);
        bg.setTranslateX((Go.TILE_SIZE - Go.TILE_SIZE * 0.3125 * 2) / 2);
        bg.setTranslateY((Go.TILE_SIZE - Go.TILE_SIZE * 0.26 * 2) / 2 + Go.TILE_SIZE * 0.07);
        getChildren().addAll(bg);
        setOnMouseClicked(e -> thingsToDoWhenClicked(x, y)); // Must check what if doubleclicked

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
}