package Go.Logic;

import Go.Common.StoneColour;
import Go.State;
import Go.Logic.Board.*;

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
		return x >= 0 && y < board.size;
	}

	private boolean isSurrounded(int y, int x, Board board, StoneColour colour)
	{
		if(!insideBoard(y, x, board))
			return true;
		if(board.array[y][x].colour == StoneColour.Empty)
			return false;
		if(board.array[y][x].colour != colour)
			return true;
		if(board.array[y][x].visited)
			return true;

		board.array[y][x].visited = true;
		if(!isSurrounded(y, x - 1, board, colour))
		{
			board.array[y][x].visited = false;
			return false;
		}
		if(!isSurrounded(y, x + 1, board, colour))
		{
			board.array[y][x].visited = false;
			return false;

		}
		if(!isSurrounded(y - 1, x, board, colour))
		{
			board.array[y][x].visited = false;
			return false;

		}
		if(!isSurrounded(y + 1, x, board, colour))
		{
			board.array[y][x].visited = false;
			return false;
		}

		return true;
	}


	private void kill(int y, int x, Board board, StoneColour colour)
	{
		if(!insideBoard(y, x, board))
			return;
		if(board.array[y][x].colour != colour)
			return;

		board.array[y][x].colour = StoneColour.Empty;

		kill(y - 1, x, board, colour);
		kill(y + 1, x, board, colour);
		kill(y, x - 1, board, colour);
		kill(y, x + 1, board, colour);
	}

	public boolean isCorrect(State state, State.Move move)
	{
		int x = move.x;
		int y = move.y;
		int v = move.player.getColour().getValue();
		Board board = state.getBoard();

		if(state.getPlayer() == move.player)
			return false;

		return board.array[y][x].colour.equals(StoneColour.Empty);
	}

	private boolean enemyColour(StoneColour first, StoneColour second)
	{
		return second != StoneColour.Empty && second != first;
	}

	public State postMoveActions(State state, State.Move move)
	{
		int y = move.y;
		int x = move.x;
		Board board = state.getBoard();

		y = move.y - 1;
		x = move.x;
		StoneColour colour = board.array[y][x].colour;
		if(isSurrounded(y, x, board, colour))
			kill(y, x, board, colour);
		y = move.y + 1;
		x = move.x;
		colour = board.array[y][x].colour;
		if(isSurrounded(y, x, board, colour))
			kill(y, x, board, colour);
		y = move.y;
		x = move.x - 1;
		colour = board.array[y][x].colour;
		if(isSurrounded(y, x, board, colour))
			kill(y, x, board, colour);
		y = move.y;
		x = move.x + 1;
		colour = board.array[y][x].colour;
		if(isSurrounded(y, x, board, colour))
			kill(y, x, board, colour);

		return new State(state.getPlayer(), board);
	}

	public State addMove(State state, State.Move move)
	{
		Board board = state.getBoard();
		board.array[move.y][move.x].setColour(move.player.getColour());
		return new State(move.player, board);
	}

}
