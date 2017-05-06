package App;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static App.View.array;

/**
 * Created by Kruti on 05.05.2017.
 */
public class Go extends Application{
    public static int  TILE_SIZE = 50;
    public static int  WIDTH = 20;
    public static int  HEIGHT = 20;
    public static Piece[][] board = new Piece[WIDTH][HEIGHT];
    public static Scene scene;
    public static boolean flag = false; // false - WHITE, true - BLACK


    private static  Group tileGroup = new Group();
    private static Group lineGroup = new Group();
    private static Group pieceGroup = new Group();

    //missing everything for pieces

    public static Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(TILE_SIZE * WIDTH, TILE_SIZE * HEIGHT);
        root.getChildren().addAll(tileGroup,lineGroup,pieceGroup);

        for(int y = 0; y < HEIGHT; y++) {
            MyLine linia = new MyLine(0,y,WIDTH-1,y);
            lineGroup.getChildren().add(linia);
        }

        for(int x = 0; x < WIDTH; x++) {
            MyLine linia = new MyLine(x,0,x,HEIGHT - 1);
            lineGroup.getChildren().add(linia);
        }

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile(x, y);
                tileGroup.getChildren().add(tile);
            }
        }

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {

                Piece piece = null;
                if( array[y][x] == 1) {
                    piece = new Piece(PieceType.WHITE, x, y);
                    pieceGroup.getChildren().add(piece);
                }
                if( array[y][x] == 2) {
                    piece = new Piece(PieceType.BLACK, x, y);
                    pieceGroup.getChildren().add(piece);
                }
                board[y][x] = piece;
            }
        }

        return root;
    }

    public void start(Stage primaryStage) throws Exception{
        scene = new Scene(createContent());
        primaryStage.setTitle("GO");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
