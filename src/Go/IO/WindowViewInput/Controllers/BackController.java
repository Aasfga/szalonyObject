package Go.IO.WindowViewInput.Controllers;


import javafx.fxml.FXML;

/**
 * Created by Kruti on 13.06.2017.
 */
public class BackController {

    private MainController mainController;



    @FXML
    public void back() {
        System.out.println("XD");
        mainController.setMenu();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


}
