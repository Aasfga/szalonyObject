package GoGame.View;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static GoGame.View.Go.HEIGHT;
import static GoGame.View.Go.TILE_SIZE;

/**
 * Created by kruti on 21.05.17.
 */

class Score extends StackPane {

    private Rectangle border = new Rectangle(135, 100);
    private Text text = new Text();

    Score(boolean white, int amount ){

        text.setFont(Font.font(40));
        text.setText("OK");
        text.setVisible(true);

        if(white)
            relocate(HEIGHT * TILE_SIZE + 25,TILE_SIZE);
        else
            relocate(HEIGHT * TILE_SIZE + 25,TILE_SIZE *2 + 100 );

        border.setFill(Color.AZURE);
        getChildren().addAll(border, text);
    }
}

