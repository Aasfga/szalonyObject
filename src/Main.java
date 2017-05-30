import Go.IO.ConsoleViewInput.ConsoleInput;
import Go.IO.ConsoleViewInput.ConsoleView;
import Go.Logic.Board;
import Go.Logic.Game;
import Go.Match;
import Go.Player;

public class Main
{
	public static void main(String[] args)
	{
		Player f = new Player("Dominik", new ConsoleInput());
		Player s = new Player("kinimoD", new ConsoleInput());
		Board board = Game.get().getInitBoard(5);
		Match match = new Match.LocalMatch(new ConsoleView(), board, f, s);
		match.startGame();
	}
}
