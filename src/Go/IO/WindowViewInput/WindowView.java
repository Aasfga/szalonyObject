package Go.IO.WindowViewInput;

import Go.IO.View;
import Go.Logic.Game;
import javafx.application.Application;

import Go.State;

import static Go.IO.WindowViewInput.Go.*;

public class WindowView implements View {
	static final int size = 13;
	static char[][] array = new char[21][21];
	static int lastx = size - 1;
	static int lasty = size - 1;
	public static volatile boolean judgeDidHisJob = false;


	public static void startingView(int size) {
		HEIGHT = size;
		WIDTH = size;
		new Thread(() -> Application.launch(MainApp.class)).start();
	}

	@Override
	public synchronized void setCurrentView(State state) {
		int intArray[][] = state.getBoard().toArray();

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (intArray[i][j] == 0) array[i][j] = 0;
				else if (intArray[i][j] == 1) array[i][j] = 1;
				else array[i][j] = 2;
			}
		}
		judgeDidHisJob = true;
	}

	@Override
	public void setError(Game.Result res) {

	}
}