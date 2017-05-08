package GoGame.View;

import javafx.application.Application;

import static GoGame.View.Go.HEIGHT;
import static GoGame.View.Go.WIDTH;

public class View {

    static char[][] array = new char[20][20];

    public static void setCurrentView( String view) {
        int index = 0;
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                array[y][x] = view.charAt(index);
                index++;
            }
        }
    }

    private static void setView(int size) {
        HEIGHT = size;
        WIDTH = size;
        new Thread(() -> Application.launch(Go.class)).start();
    }

    public static void main(String[] args) throws InterruptedException {
        setView(12);
    }

}
