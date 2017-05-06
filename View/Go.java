import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * Created by Kruti on 05.05.2017.
 */
public class Go extends Application{
    public static final int  TILE_SIZE = 50;
    public static final int  WIDTH = 19;
    public static final int  HEIGHT = 19;

    private Tile[][] board = new Tile[WIDTH][HEIGHT];

    private Group tileGroup = new Group();
    private Group lineGroup = new Group();

    //missing everything for pieces



    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(TILE_SIZE * WIDTH, TILE_SIZE * HEIGHT);

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
                board[x][y] = tile;

                tileGroup.getChildren().add(tile);
            }
        }

    root.getChildren().addAll(tileGroup,lineGroup);
        return root;
    }

    public void start(Stage primaryStage) throws Exception{
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("GO");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
