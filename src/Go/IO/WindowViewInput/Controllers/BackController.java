package Go.IO.WindowViewInput.Controllers;


import javafx.fxml.FXML;

import static Go.IO.WindowViewInput.Tile.thingsToDoWhenClicked;

/**
 * Created by Kruti on 13.06.2017.
 */
public class BackController {

    private MainController mainController;



    @FXML
    public void back() {
        mainController.setMenu();
    }

    @FXML
    public void save() {
        System.out.println("XD");
    }

    @FXML
    public void pas() {
        thingsToDoWhenClicked(-1,1);
    }


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


}
