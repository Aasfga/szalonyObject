package Go.IO.WindowViewInput;

import javafx.application.Application;

import static Go.IO.WindowViewInput.Go.*;

public class View
{

	static final int size = 21;
	static char[][] array = new char[size][size];
	static int lastx = size - 1;
	static int lasty = size - 1;
	public static boolean judgeDidHisJob = false;

	public synchronized void setCurrentView(String viewToSet)
	{
		int index = 0;
		for(int y = 0; y < HEIGHT; y++)
		{
			for(int x = 0; x < WIDTH; x++)
			{
				array[y][x] = viewToSet.charAt(index);
				index++;
			}
		}
		judgeDidHisJob = true;
	}

	private static void startingView(int size)
	{
		HEIGHT = size;
		WIDTH = size;
		new Thread(() -> Application.launch(Go.class)).start();
	}

//    public static void main(String[] args) throws InterruptedException {
//
//        Judge judge = new Judge();
//        judge.setGame(GoGame.getGoGame());
//        Player f = new Player("A", new Move(), judge);
//        Player s = new Player("B", new Move(), judge);
//        Match match = new LocalMatch(f, s, new WindowViewInput(), judge);
//        Match.link(judge, match);
//        new Thread(match).start();
//        startingView(12);
//    }
//
//    public static class LocalMatch extends Match
//    {
//        GameState gameState;
//
//
//        public LocalMatch(Player f, Player s, WindowViewInput v, Judge j)
//        {
//            super(f, s, v);
//            judge = j;
//            gameState = new GameState(judge.getGame().getInitBoard(), players[0]);
//        }
//
//        @Override
//        public GameState getState()
//        {
//            return gameState;
//        }
//
//        @Override
//        public void setState(GameState state)
//        {
//            gameState = state;
//        }
//    }
}
