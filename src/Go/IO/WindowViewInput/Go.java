package Go.IO.WindowViewInput;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;

import static Go.IO.WindowViewInput.WindowView.array;

public class Go extends Application{

    static int  TILE_SIZE = 50;
    static int  WIDTH = 20;
    static int  HEIGHT = 20;
    static int PANEL_SIZE = 200;
    static Scene scene;
    static boolean flag = false; // false - WHITE, true - BLACK
    private static  Group tileGroup = new Group();
    private static Group lineGroup = new Group();
    private static Group pieceGroup = new Group();
    private static Group backlight = new Group();
    private static Group scoreGroup = new Group();

    public static Parent createContent() {

        tileGroup.getChildren().clear();
        lineGroup.getChildren().clear();
        pieceGroup.getChildren().clear();
        backlight.getChildren().clear();
        scoreGroup.getChildren().clear();

        Pane root = new Pane();
        RightPanel panel = new RightPanel("cos","cos");//,score1,score2);
        root.getChildren().add(panel);
        root.setPrefSize(TILE_SIZE * WIDTH + PANEL_SIZE, TILE_SIZE * HEIGHT);
        root.getChildren().addAll(tileGroup,lineGroup,pieceGroup,backlight,scoreGroup);

        // Row lines
        for(int x = 0; x < WIDTH; x++) {
            for(int y = 0; y < HEIGHT; y++) {
                MyLine linia = null;
                if( x == 0)
                    linia = new MyLine(x, y,true, false, true);
                if( x == WIDTH - 1 )
                    linia = new MyLine(x, y,true, true, false);
                if( x > 0 && x < WIDTH -1)
                    linia = new MyLine(x, y,true, true, true);
                lineGroup.getChildren().add(linia);
            }
        }
        //Column lines
        for(int x = 0; x < WIDTH; x++) {
            for(int y = 0; y < HEIGHT; y++) {
                MyLine linia = null;
                if( y == 0)
                    linia = new MyLine(x, y,false, false, true);
                else
                if( y == HEIGHT - 1)
                    linia = new MyLine(x, y, false, true, false);
                else
                    linia = new MyLine(x, y, false, true, true);


                lineGroup.getChildren().add(linia);
            }
        }



        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile(x, y);
                tileGroup.getChildren().add(tile);
            }
        }

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if( array[y][x] == 1) {
                    Piece piece = new Piece(PieceType.WHITE, x, y);
                    pieceGroup.getChildren().add(piece);
                }
                if( array[y][x] == 2) {
                    Piece piece = new Piece(PieceType.BLACK, x, y);
                    pieceGroup.getChildren().add(piece);
                }
                if( array[y][x] == 3) {
                    Light light = new Light(x,y);
                    backlight.getChildren().add(light);
                }
            }
        }

        Score score1 = new Score(true,5);
        Score score2 = new Score(false,5);
        scoreGroup.getChildren().addAll(score1,score2);
        return root;
    }

    public void start(Stage primaryStage) {
        scene = new Scene(createContent());
        primaryStage.setTitle("GO");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}