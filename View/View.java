package App;


/**
 * Created by lukasz on 06.05.2017.
 */
public class View {

    public static void setView( char[][] array, int size) {
        Go go = new Go();
        go.HEIGHT = size;
        go.WIDTH = size;
        go.array = array;
        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(Go.class);
            }
        }.start();
    }
    public static void main(String[] args) {
        char[][] tab = new char[19][19];
        tab[0][1] = 1;
        tab[0][2] = 1;
        tab[3][1] = 2;
        setView(tab,13);
    }


}