package Go.IO.WindowViewInput.Controllers;


import javafx.fxml.FXML;

import static Go.IO.WindowViewInput.Go.scoreblack;
import static Go.IO.WindowViewInput.Go.scorewhite;
import static Go.IO.WindowViewInput.Tile.thingsToDoWhenClicked;
import static Go.IO.WindowViewInput.WindowView.array;
import static Go.IO.WindowViewInput.WindowView.finalSize;

/**
 * Created by Kruti on 13.06.2017.
 */
public class BackController {

    private MainController mainController;

    @FXML
    public void back() {
        mainController.setMenu();

        for (int i = 0; i < finalSize; i++) {
            for (int j = 0; j < finalSize; j++) {
                array[i][j] = 0;
            }
        }
        scorewhite = 0;
        scoreblack = 0;
    }

    @FXML
    public void save() {
        System.out.println("XD");
    }

    @FXML
    public void pas() {
        thingsToDoWhenClicked(-1,-1);
    }


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


}
