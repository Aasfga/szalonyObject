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
	public static void main(String[] args)
	{
		Player f = new Player("Dominik", new ConsoleInput());
		Player s = new Player("kinimoD", new ConsoleInput());
		Board board = Game.get().getInitBoard(5);
		Match match = new Match.LocalMatch(new ConsoleView(), board, f, s);
		match.startGame();
	}

	public static void maina(String[] args)
	{
		Player f = new Player("Dominik", new Move());
		Player s = new Player("kinimoD", new Move());
		Board board = Game.get().getInitBoard(11);
		WindowView view = new WindowView();
		WindowView.startingView(11);
		Match match = new Match.LocalMatch(view, board, f, s);
		match.startGame();


//		Scanner scanner = new Scanner(System.in);
//		while(true)
//		{
//			if(scanner.nextInt() == 0)
//			{
//
//				try
//				{
//					TimeUnit.SECONDS.sleep(1);
//				} catch(InterruptedException e)
//				{
//					e.printStackTrace();
//				}
//
//			}
//			else
//				break;
//		}
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
