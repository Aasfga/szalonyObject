import Go.IO.ConsoleViewInput.ConsoleInput;
import Go.IO.ConsoleViewInput.ConsoleView;
import Go.IO.WindowViewInput.Move;
import Go.IO.WindowViewInput.WindowView;
import Go.Logic.Board;
import Go.Logic.Game;
import Go.Match;
import Go.Player;

import java.sql.Time;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main
{

	public static void main(String[] args) {
		Player f = new Player("Dominik", new Move());
		Player s = new Player("kinimoD", new Move());
		Board board = Game.get().getInitBoard(13);
		WindowView view = new WindowView();
		WindowView.startingView(13);
		Match match = new Match.LocalMatch(view, board, f, s);
		match.startGame();
	}
}
