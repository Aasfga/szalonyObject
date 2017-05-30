package Go.IO.WindowViewInput;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import static Go.IO.WindowViewInput.Go.HEIGHT;
import static Go.IO.WindowViewInput.Go.TILE_SIZE;


class Score extends StackPane {

    private Rectangle border = new Rectangle(135, 100);
    private Text text = new Text();

    Score(boolean white, int amount ){

        text.setFont(Font.font(40));
        text.setText("50");
        text.setVisible(true);

        if(white)
            relocate(HEIGHT * TILE_SIZE + 25,TILE_SIZE);
        else
            relocate(HEIGHT * TILE_SIZE + 25,TILE_SIZE *2 + 100 );

        border.setFill(Color.AZURE);
        getChildren().addAll(border, text);
    }
}
