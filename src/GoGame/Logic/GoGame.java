package GoGame.Logic;

import TurnBasedGameLogic.Controller.Board;
import TurnBasedGameLogic.Controller.GameState;
import TurnBasedGameLogic.Module.Game;

public class GoGame implements Game
{
	private static GoGame game = null;

	private GoGame(){};

	public static GoGame getGoGame()
	{
		if(game == null)
		{
			game = new GoGame();
		}
		return game;
	}

	@Override
	public boolean isGood(GameState oldState, GameState newState)
	{
		GoBoard oldBoard = (GoBoard) oldState.getBoard();
		GoBoard newBoard = (GoBoard) newState.getBoard();

		int oldFree = 0;
		int oldWhite = 0;
		int oldBlack = 0;

		int newFree = 0;
		int newWhite = 0;
		int newBlack = 0;

		for(int i = 0; i < oldBoard.size; i++)
		{
			for(int j = 0; j < oldBoard.size; j++)
			{
				switch(oldBoard.array[i][j])
				{
					case 0: oldFree++; break;
					case 1: oldWhite++; break;
					default: oldBlack++;
				}

				switch(newBoard.array[i][j])
				{
					case 0: newFree++; break;
					case 1: newWhite++; break;
					default: newBlack++; break;
				}
			}
		}

		return oldFree - newFree == 1 && (newWhite == oldWhite || newBlack == oldBlack);
	}

	@Override
	public boolean isEnd(GameState state)
	{
		return false;
	}

	@Override
	public Board getInitBoard()
	{
		return new GoBoard(12);
	}

	@Override
	public Board createBoard(String data)
	{
		return new GoBoard(data);
	}

	@Override
	public GameState makeActions(GameState state)
	{
		return state;
	}

	@Override
	public String getString(GameState state)
	{
		StringBuilder builder = new StringBuilder();
		GoBoard board = (GoBoard) state.getBoard();

		for(int i = 0; i < board.size; i++)
		{
			for(int j = 0; j < board.size; j++)
				builder.append(board.array[i][j]);
		}

		return builder.toString();
	}
}
