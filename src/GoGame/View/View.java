package App;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.TimeUnit;

import static App.Go.HEIGHT;
import static App.Go.WIDTH;
import static javafx.application.Application.launch;

/**
 * Created by lukasz on 06.05.2017.
 */
public class View {

    public static char[][] array = new char[20][20];
    static boolean visited = false;

    public static void setCurrentView( String view) {
        int index = 0;
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {

            }
        }
    }

    public static void setView( char[][] arra2y, int size) throws InterruptedException {

        HEIGHT = size;
        WIDTH = size;
        array = arra2y;
        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(Go.class);
            }
        }.start();

        System.out.println(12);
        TimeUnit.SECONDS.sleep(2);
        array[0][5] = 1;
        array[3][1] = 2;

        System.out.println(1);
    }
    public static void main(String[] args) throws InterruptedException {
        char[][] tab = new char[19][19];
        tab[0][11] = 1;
        tab[0][2] = 1;
        tab[11][0] = 1;
        setView(tab,12);

    }

}
