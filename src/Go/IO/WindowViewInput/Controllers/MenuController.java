package Go.IO.WindowViewInput.Controllers;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import Go.IO.WindowViewInput.Go;

public class MenuController {

    private MainController mainController;
    @FXML
    public void newgame() {
        Pane nowagra = new Go();
        mainController.setScreen(nowagra);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
