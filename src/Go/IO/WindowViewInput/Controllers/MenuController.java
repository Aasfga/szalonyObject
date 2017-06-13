package Go.IO.WindowViewInput.Controllers;
import Go.IO.WindowViewInput.Move;
import Go.IO.WindowViewInput.Tile;
import Go.IO.WindowViewInput.WindowView;
import Go.Logic.Board;
import Go.Logic.Game;
import Go.Player;
import Go.Match;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import Go.IO.WindowViewInput.Go;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;

import static Go.IO.WindowViewInput.Go.scoreblack;
import static Go.IO.WindowViewInput.Go.scorewhite;

public class MenuController {

    private MainController mainController;


    @FXML
    public void newgame() {

        Player f = new Player("Dominik", new Move());
        Player s = new Player("kinimoD", new Move());
        WindowView view = new WindowView();
        Board board = Game.get().getInitBoard(13);
        Match match = new  Match.LocalMatch(view, board, f, s);
        match.startGame();
        refresh();
    }

    public void refresh() {
        Tile.setMenu(this);
        Pane root = new Pane();
        root.setPrefSize(1000, 800);
        Pane go = new Go();

//
        File file = new File("src/Go/IO/WindowViewInput/images/tło.jpg");
        Image img = new Image(file.toURI().toString());
        ImagePattern image = new ImagePattern(img);

        Rectangle r = new Rectangle(1000,800);
        r.setFill(image);
        root.getChildren().add(r);
//


        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml_files/Back.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BackController backController = loader.getController();
        backController.setMainController(mainController);

        //
        Pane panelBlack = new Pane();
        panelBlack.relocate(760,200);
        Rectangle scoreBlack = new Rectangle(218,85);
        scoreBlack.setArcHeight(50);
        scoreBlack.setArcWidth(50);
        scoreBlack.setStrokeWidth(3);
        scoreBlack.setStroke(Color.WHITE);

        Text pointsB = new Text(Integer.toString(scorewhite));
        pointsB.setFill(Color.WHITE);
        pointsB.relocate(87,45);
        pointsB.setFont(Font.font(null, FontWeight.BOLD, 45));
        panelBlack.getChildren().add(scoreBlack);
        panelBlack.getChildren().add(pointsB);


        Pane panelWhite = new Pane();
        panelWhite.relocate(760,350);
        Rectangle scoreWhite = new Rectangle(218,85);
        scoreWhite.setFill(Color.WHITE);
        scoreWhite.setArcHeight(50);
        scoreWhite.setArcWidth(50);
        scoreWhite.setStrokeWidth(3);
        scoreWhite.setStroke(Color.BLACK);

        Text pointsW = new Text(Integer.toString(scoreblack));
        pointsW.relocate(87,45);
        pointsW.setFont(Font.font(null, FontWeight.BOLD, 45));
        panelWhite.getChildren().add(scoreWhite);
        panelWhite.getChildren().add(pointsW);

        root.getChildren().add(go);
        root.getChildren().add(pane);
        root.getChildren().add(panelBlack);
        root.getChildren().add(panelWhite);
        mainController.setScreen(root);
    }

    public void loadgame() {
        System.out.println("YEAAAH"); //TODO (czyt. 'todo' )
    }

    public void exit() {
        Platform.exit();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
