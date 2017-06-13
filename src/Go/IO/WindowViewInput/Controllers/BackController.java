package Go.IO.WindowViewInput.Controllers;


import Go.Match;
import Go.State;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import static Go.IO.WindowViewInput.Go.scoreblack;
import static Go.IO.WindowViewInput.Go.scorewhite;
import static Go.IO.WindowViewInput.Move.setMove;
import static Go.IO.WindowViewInput.Tile.thingsToDoWhenClicked;
import static Go.IO.WindowViewInput.WindowView.array;
import static Go.IO.WindowViewInput.WindowView.finalSize;

/**
 * Created by Kruti on 13.06.2017.
 */
public class BackController
{

	private MainController mainController;
	Match match;

	@FXML
	public void back()
	{
		mainController.setMenu();

		for(int i = 0; i < finalSize; i++)
		{
			for(int j = 0; j < finalSize; j++)
			{
				array[i][j] = 0;
			}
		}
		scorewhite = 0;
		scoreblack = 0;
		setMove(-2, -2);
	}

	@FXML
	public void save()
	{
		State.Container container = new State.Container(match.getState(), match.getPlayers());

		FileOutputStream fileOut = null;
		try
		{   FileChooser fileChooser1 = new FileChooser();
            fileChooser1.setTitle("Save Image");
            File file = fileChooser1.showSaveDialog(new Stage());
			fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(container);
			out.close();
			fileOut.close();
		} catch(Exception e)
		{
		}
	}

	@FXML
	public void pas()
	{
		thingsToDoWhenClicked(-1, -1);
	}


	public void setMainController(MainController mainController)
	{
		this.mainController = mainController;
	}


}
