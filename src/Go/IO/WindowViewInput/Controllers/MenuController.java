package Go.IO.WindowViewInput.Controllers;
import Go.Common.StoneColour;
import Go.IO.WindowViewInput.*;
import Go.Logic.Board;
import Go.Logic.Game;
import Go.Player;
import Go.Match;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import Go.State;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Timer;
import java.util.TimerTask;

import static Go.IO.WindowViewInput.Go.scoreblack;
import static Go.IO.WindowViewInput.Go.scorewhite;

import Server.Client;

public class MenuController {

    private MainController mainController;
    Match match;


    @FXML
    public void newgame() {

        Player f = new Player("Dominik", new Move());
        Player s = new Player("kinimoD", new Move());
        WindowView view = new WindowView();
        Board board = Game.get().getInitBoard(13);
        match = new  Match.LocalMatch(view, board, f, s);
        match.startGame();
        refresh();
    }

    public void refresh() {
        Tile.setMenu(this);
        Pane root = new Pane();
        root.setPrefSize(1000, 800);
        Pane go = new Go();

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
        backController.match = this.match;

        //
        Pane panelBlack = new Pane();
        panelBlack.relocate(760,200);
        Rectangle scoreBlack = new Rectangle(218,85);
        scoreBlack.setArcHeight(50);
        scoreBlack.setArcWidth(50);
        scoreBlack.setStrokeWidth(3);
        scoreBlack.setStroke(Color.WHITE);

        Text pointsB = new Text(Integer.toString(scoreblack));
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

        Text pointsW = new Text(Integer.toString(scorewhite));
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
        WindowView view = new WindowView();

        State.Container container;
        try
        {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Open File");
            File file = chooser.showOpenDialog(new Stage());
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            container = (State.Container) in.readObject();
            in.close();
            fileIn.close();
        }
        catch(Exception e)
        {
        	throw new NullPointerException("trolololo nie ma zapisu");
        }

        Player player[] = container.getPlayers();
        player[0].setInput(new Move());
        player[1].setInput(new Move());
        match = new Match.LocalMatch(view, container.getState(), player);
        match.startGame();
        refresh();
    }

    public void exit() {
        Platform.exit();
    }

    public void multiplayer() {
        Client c = new Client();
        String uuid = c.login();
        System.out.println(uuid);
        c.entrygame(uuid);
        while(!c.prompt(uuid)) {
            try {
                Thread.sleep(1000);
            }
            catch(Exception e) {}
        }

        Player f = new Player(uuid, new Move());
        Player s = new Player("opponent", new Move()); // Move to RemoteMove
        WindowView view = new WindowView();
        Board board = Game.get().getInitBoard(13);
        if( c.getColor().equals("White") ) {
            f.setColour(StoneColour.White);
            s.setColour(StoneColour.Black);
            match = new  Match.RemoteMatch(c, view, board, f, f, s);
        }
        else {
            f.setColour(StoneColour.Black);
            s.setColour(StoneColour.White);
            match = new  Match.RemoteMatch(c, view, board, f, s, f);
        }
        match.startGame();
        refresh();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
