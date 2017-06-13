package Go.Logic;

import Go.Common.StoneColour;
import Go.State;
import Go.Logic.Board.*;

import java.util.ArrayList;

public class Game
{
	static Game game;

	private Game()
	{
	}

	public static Game get()
	{
		if(game == null)
			game = new Game();

		return game;
	}

	public Board getInitBoard(int size)
	{
		return new Board(size);
	}

	public boolean isEnd(State state)
	{
		return false;
	}

	private boolean insideBoard(int y, int x, Board board)
	{
		return x >= 0 && x < board.array.length && y >= 0 && y < board.array.length;
	}

	private boolean isSurrounded(Cords cords, Board board, StoneColour colour)
	{
		if(!cords.areInside())
			return true;
		if(board.getStone(cords).equals(StoneColour.Empty))
			return false;
		if(enemyColour(colour, board.getStone(cords)))
			return true;
		if(board.isVisited(cords))
			return true;

		board.setVisited(cords);

		boolean ans = isSurrounded(cords.up(), board, colour) &&
					  isSurrounded(cords.down(), board, colour) &&
					  isSurrounded(cords.left(), board, colour) &&
				      isSurrounded(cords.right(), board, colour);


		board.leave(cords);
		return ans;
	}

	private void kill(Cords cords, Board board, StoneColour colour)
	{
		if(!cords.areInside())
			return;
		if(!board.getStone(cords).equals(colour))
			return;

		board.setStone(cords, StoneColour.Empty);

		kill(cords.up(), board, colour);
		kill(cords.right(), board, colour);
		kill(cords.left(), board, colour);
		kill(cords.down(), board, colour);
	}

	private boolean canBeKilled(Cords cords, Board board, StoneColour colour)
	{
		if(!cords.areInside())
			return false;
		return enemyColour(cords, colour, board) && isSurrounded(cords, board, board.getStone(cords));
	}

	private boolean isSelfKiller(State.Move move, Board board)
	{
		Cords moveCords = board.new Cords(move.y, move.x);
		StoneColour moveColour = move.player.getColour();

		board.setStone(moveCords, moveColour);

		if(!isSurrounded(moveCords, board, moveColour))
		{
			board.setStone(moveCords, StoneColour.Empty);
			return false;
		}

		boolean ans = canBeKilled(moveCords.up(), board, moveColour) ||
					  canBeKilled(moveCords.left(), board, moveColour) ||
					  canBeKilled(moveCords.right(), board, moveColour) ||
					  canBeKilled(moveCords.down(), board, moveColour);

		board.setStone(moveCords, StoneColour.Empty);
		return !ans;
	}


	private boolean koMove(State state, State.Move move)
	{
		ArrayList<Board> history = state.getHistory();
		Board board = new Board(postMoveActions(addMove(state, move), move).getBoard());
		board.setStone(board.new Cords(move.y, move.x), move.player.getColour());
		Board last;

		try
		{
			last = history.get(history.size() - 1);
		}
		catch(Exception e)
		{
			return false;
		}

		for(int i = 0; i < last.array.length; i++)
		{
			for(int j = 0; j < last.array.length; j++)
			{
				if(!last.array[i][j].equals(board.array[i][j]))
					return false;
			}
		}


		return true;
	}

	public Result isCorrect(State state, State.Move move)
	{
		int x = move.x;
		int y = move.y;
		StoneColour colour = move.player.getColour();
		Board board = state.getBoard();


		if(!board.array[y][x].colour.equals(StoneColour.Empty))
			return Result.WrongMove;
		if(state.getPlayer() == move.player)
			return Result.WrongPlayer;
		if(isSelfKiller(move, board))
			return Result.Suicide;
		if(koMove(state, move))
			return Result.Ko;

		return Result.Success;
	}

	private boolean enemyColour(Cords first, Cords second, Board board)
	{
		StoneColour f = board.getStone(first);
		StoneColour s = board.getStone(second);

		return enemyColour(f, s);
	}

	private boolean enemyColour(Cords first, StoneColour second, Board board)
	{
		return enemyColour(board.getStone(first), second);
	}

	private boolean enemyColour(StoneColour first, Cords second, Board board)
	{
		return enemyColour(second, first, board);
	}

	private boolean enemyColour(StoneColour first, StoneColour second)
	{
		return !second.equals(StoneColour.Empty) && !first.equals(second);
	}

	public State postMoveActions(State state, State.Move move)
	{
		Board board = state.getBoard();
		Cords moveCords = board.new Cords(move.y, move.x);
		StoneColour moveColour = move.player.getColour();

		if(canBeKilled(moveCords.up(), board, moveColour))
			kill(moveCords.up(), board, moveColour.other());
		if(canBeKilled(moveCords.right(), board, moveColour))
			kill(moveCords.right(), board, moveColour.other());
		if(canBeKilled(moveCords.left(), board, moveColour))
			kill(moveCords.left(), board, moveColour.other());
		if(canBeKilled(moveCords.down(), board, moveColour))
			kill(moveCords.down(), board, moveColour.other());

		return new State(state.getPlayer(), board, state.getHistory());
	}

	public State addMove(State state, State.Move move)
	{

		Board board = new Board(state.getBoard());
		board.array[move.y][move.x].setColour(move.player.getColour());
		ArrayList<Board> history = (ArrayList<Board>) state.getHistory().clone();
		history.add(state.getBoard());
		return new State(move.player, board, history);
	}


	public enum Result
	{
		Success,
		Ko,
		Suicide,
		WrongMove,
		WrongPlayer
	}
}
