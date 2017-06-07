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
		return x >= 0 && x < board.array.length && y >= 0 && y < board.array.length;
	}

	private boolean isSurrounded(int y, int x, Board board, StoneColour colour)
	{
		if(!insideBoard(y, x, board))
			return true;
		if(board.array[y][x].colour == StoneColour.Empty)
			return false;
		if(board.array[y][x].colour != StoneColour.Empty && board.array[y][x].colour != colour)
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

		board.array[y][x].visited = false;

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


	private boolean isSelfKiller(State.Move move, Board board, StoneColour colour)
	{
		board.array[move.y][move.x].setColour(colour);

		int y;
		int x;
		if(isSurrounded(move.y, move.x, board, colour))
		{
			y = move.y - 1;
			x = move.x;
			if(insideBoard(y, x, board))
			{
				colour = board.array[y][x].colour;

				if(colour != StoneColour.Empty && isSurrounded(y, x, board, colour))
				{
					board.array[move.y][move.x].setColour(StoneColour.Empty);
					return false;
				}

			}
			y = move.y + 1;
			x = move.x;
			if(insideBoard(y, x, board))
			{
				colour = board.array[y][x].colour;

				if(colour != StoneColour.Empty && isSurrounded(y, x, board, colour))
				{
					board.array[move.y][move.x].setColour(StoneColour.Empty);
					return false;
				}

			}
			y = move.y;
			x = move.x - 1;
			if(insideBoard(y, x, board))
			{
				colour = board.array[y][x].colour;

				if(colour != StoneColour.Empty && isSurrounded(y, x, board, colour))
				{
					board.array[move.y][move.x].setColour(StoneColour.Empty);
					return false;
				}

			}
			y = move.y;
			x = move.x + 1;
			if(insideBoard(y, x, board))
			{
				colour = board.array[y][x].colour;

				if(colour != StoneColour.Empty && isSurrounded(y, x, board, colour))
				{
					board.array[move.y][move.x].setColour(StoneColour.Empty);
					return false;
				}

			}
			board.array[move.y][move.x].setColour(StoneColour.Empty);
			return true;
		}

		board.array[move.y][move.x].setColour(StoneColour.Empty);
		return false;
	}


	public boolean isCorrect(State state, State.Move move)
	{
		int x = move.x;
		int y = move.y;
		StoneColour colour = move.player.getColour();
		Board board = state.getBoard();



		if(state.getPlayer() == move.player)
			return false;

		return board.array[y][x].colour.equals(StoneColour.Empty) && !isSelfKiller(move, board, colour);
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
		StoneColour colour;
		y = move.y - 1;
		x = move.x;
		if(insideBoard(y, x, board))
		{
			colour = board.array[y][x].colour;

			if(colour != StoneColour.Empty && isSurrounded(y, x, board, colour))
				kill(y, x, board, colour);

		}
		y = move.y + 1;
		x = move.x;
		if(insideBoard(y, x, board))
		{
			colour = board.array[y][x].colour;
			if(colour != StoneColour.Empty && isSurrounded(y, x, board, colour))
				kill(y, x, board, colour);

		}
		y = move.y;
		x = move.x - 1;
		if(insideBoard(y, x, board))
		{
			colour = board.array[y][x].colour;
			if(colour != StoneColour.Empty && isSurrounded(y, x, board, colour))
				kill(y, x, board, colour);

		}
		y = move.y;
		x = move.x + 1;
		if(insideBoard(y, x, board))
		{
			colour = board.array[y][x].colour;
			if(colour != StoneColour.Empty && isSurrounded(y, x, board, colour))
				kill(y, x, board, colour);

		}

		return new State(state.getPlayer(), board);
	}

	public State addMove(State state, State.Move move)
	{
		Board board = state.getBoard();
		board.array[move.y][move.x].setColour(move.player.getColour());
		return new State(move.player, board);
	}

}
