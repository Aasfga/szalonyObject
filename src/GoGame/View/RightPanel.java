package GoGame.View;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import static GoGame.View.Go.*;

public class RightPanel extends StackPane {

    //    private Score scoreWhite;
//    private Score scoreBlack;
    private Rectangle border = new Rectangle(Go.PANEL_SIZE, HEIGHT * TILE_SIZE);
    private Text name1; //TODO
    private Text name2; //TODO

    //       RightPanel(String nameWhite, String nameBlack,Score scoreWhite,Score scoreBlack ){
    RightPanel(String nameWhite, String nameBlack){//Score scoreWhite,Score scoreBlack ){
//        this.scoreWhite = scoreWhite;
//        this.scoreBlack = scoreBlack;

        relocate(WIDTH * TILE_SIZE,0);

//        scoreBlack.relocate(100,100);
        border.setFill(Color.YELLOW);
        getChildren().addAll(border);
    }
}