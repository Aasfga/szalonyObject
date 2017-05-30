import Go.IO.ConsoleViewInput.ConsoleInput;
import Go.IO.ConsoleViewInput.ConsoleView;
import Go.IO.WindowViewInput.Move;
import Go.IO.WindowViewInput.WindowView;
import Go.Logic.Board;
import Go.Logic.Game;
import Go.Match;
import Go.Player;

public class Main
{
	public static void maina(String[] args)
	{
		Player f = new Player("Dominik", new ConsoleInput());
		Player s = new Player("kinimoD", new ConsoleInput());
		Board board = Game.get().getInitBoard(5);
		Match match = new Match.LocalMatch(new ConsoleView(), board, f, s);
		match.startGame();
	}

	public static void main(String[] args)
	{
		Player f = new Player("Dominik", new Move());
		Player s = new Player("kinimoD", new Move());
		Board board = Game.get().getInitBoard(9);
		WindowView view = new WindowView();
		WindowView.startingView(9);
		Match match = new Match.LocalMatch(view, board, f, s);
		match.startGame();
	}
	//        Judge judge = new Judge();
//        judge.setGame(GoGame.getGoGame());
//        Player f = new Player("A", new Move(), judge);
//        Player s = new Player("B", new Move(), judge);
//        Match match = new LocalMatch(f, s, new WindowViewInput(), judge);
//        Match.link(judge, match);
//        new Thread(match).start();
//        startingView(12);
}
