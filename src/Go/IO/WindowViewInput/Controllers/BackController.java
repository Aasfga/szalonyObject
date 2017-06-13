package Go.IO.WindowViewInput.Controllers;


import javafx.fxml.FXML;

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
        System.out.println("XD");
    }


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


}
