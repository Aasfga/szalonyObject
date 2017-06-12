package Go.IO.WindowViewInput;

import Go.IO.View;
import Go.Logic.Game;
import javafx.application.Application;

import Go.State;

import static Go.IO.WindowViewInput.Go.*;

public class WindowView implements View
{
	static final int size = 11;
	static char[][] array = new char[size][size];
	static int lastx = size - 1;
	static int lasty = size - 1;
	public static volatile boolean judgeDidHisJob = false;


	public static void startingView(int size)
	{
		HEIGHT = size;
		WIDTH = size;
		new Thread(() -> Application.launch(StartGo.class)).start();
	}

	@Override
	public synchronized void setCurrentView(State state)
	{
		int intArray[][] = state.getBoard().toArray();

		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				if(intArray[i][j] == 0) array[i][j] = 0;
				else if(intArray[i][j] == 1) array[i][j] = 1;
				else array[i][j] = 2;
			}
		}
		judgeDidHisJob = true;
	}

	@Override
	public void setError(Game.Result res)
	{

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
