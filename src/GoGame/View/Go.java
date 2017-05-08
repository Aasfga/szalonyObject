package GoGame.View;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static GoGame.View.View.array;

public class Go extends Application{

    static int  TILE_SIZE = 50;
    static int  WIDTH = 20;
    static int  HEIGHT = 20;
    static Scene scene;
    static boolean flag = false; // false - WHITE, true - BLACK
    private static  Group tileGroup = new Group();
    private static Group lineGroup = new Group();
    private static Group pieceGroup = new Group();

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
                if( array[y][x] == 1) {
                    Piece piece = new Piece(PieceType.WHITE, x, y);
                    pieceGroup.getChildren().add(piece);
                }
                if( array[y][x] == 2) {
                    Piece piece = new Piece(PieceType.BLACK, x, y);
                    pieceGroup.getChildren().add(piece);
                }
            }
        }
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
