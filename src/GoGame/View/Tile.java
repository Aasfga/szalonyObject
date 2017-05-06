package App;

import App.Go;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static App.Go.createContent;
import static App.View.array;
import static com.sun.jmx.snmp.EnumRowStatus.active;

/**
 * Created by Kruti on 06.05.2017.
 */
public class Tile extends Rectangle {

    public boolean flagmouse =  false;
    int xmouse = -1 , ymouse = -1;

    public Tile ( int x, int y) {
        setWidth(Go.TILE_SIZE);
        setHeight(Go.TILE_SIZE);
        relocate(x * Go.TILE_SIZE, y * Go.TILE_SIZE);

        setFill(Color.valueOf("#ffad33"));

        setOnMouseClicked(e -> open(x,y));

    }

    private void open(int a,int b)  {
        if(flagmouse == false  ) {
            flagmouse = true;
            System.out.println(a + " " + b);
            if (Go.flag == true) {
                array[b][a] = 1;
                Go.flag = false;
            } else {
                array[b][a] = 2;
                Go.flag = true;
            }

            // View.array = aktualnystangry(b,a);
            Go.scene.setRoot(createContent());
            flagmouse = false;
        }
    }


}
