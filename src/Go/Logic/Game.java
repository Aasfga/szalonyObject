package Go.Logic;

import Go.Common.StoneColour;
import Go.State;
import Go.Logic.Board.*;

import java.security.acl.Group;
import java.util.ArrayList;

public class Game
{
	static Game game;

	private Game()
	{}

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

	public State postMoveActions(State state, State.Move move)
	{
		Board board = state.getBoard();
		int x = move.x;
		int y = move.y;
		Stone stone = board.array[y][x];

		if(y - 1 >= 0 && board.array[y - 1][x].colour.equals(stone.colour))
			board.array[y - 1][x].group.concatGroups(stone.group);
		if(x + 1 < board.size && board.array[y][x + 1].colour.equals(stone.colour))
			board.array[y][x + 1].group.concatGroups(stone.group);
		if(y + 1 < board.size && board.array[y+1][x].colour.equals(stone.colour))
			board.array[y + 1][x].group.concatGroups(stone.group);
		if(x - 1 >= 0 && board.array[y][x - 1].colour.equals(stone.colour))
			board.array[y][x - 1].group.concatGroups(stone.group);

		if(y - 1 >= 0 && !board.array[y - 1][x].colour.equals(stone.colour))
			board.array[y - 1][x].group.tryToKill(board);
		if(x + 1 < board.size && !board.array[y][x + 1].colour.equals(stone.colour))
			board.array[y][x + 1].group.tryToKill(board);
		if(y + 1 < board.size && !board.array[y+1][x].colour.equals(stone.colour))
			board.array[y + 1][x].group.tryToKill(board);
		if(x - 1 >= 0 && !board.array[y][x - 1].colour.equals(stone.colour))
			board.array[y][x - 1].group.tryToKill(board);


		stone.group.tryToKill(board);
		ArrayList<StoneGroup> newGroups = new ArrayList<>();
		for(StoneGroup g : board.groups)
		{
			if(!g.stones.isEmpty())
				newGroups.add(g);
		}
		board.groups = newGroups;


		return new State(move.player, board);
	}

	public State addMove(State state, State.Move move)
	{
		Board board = state.getBoard();
		board.array[move.y][move.x] = new Stone(move.y, move.x, move.player.getColour());
		return new State(move.player, board);
	}

	public static class StoneGroup
	{
		ArrayList<Stone> stones = new ArrayList<>();

		StoneGroup(Stone s)
		{
			stones.add(s);
		}

		StoneGroup()
		{}

		boolean isDead(Board board)
		{
			for(Stone s : stones)
			{
				if(s.hasFreeSpace(board))
					return false;
			}

			return true;
		}

		void kill()
		{
			for(Stone s : stones)
			{
				s.colour = StoneColour.Empty;
				s.group = null;
			}

			stones.clear();
		}


		void concatGroups(StoneGroup group)
		{
			StoneGroup newGroup = new StoneGroup();

			for(Stone x : stones)
			{
				x.group = newGroup;
				newGroup.stones.add(x);
			}

			for(Stone y : group.stones)
			{
				y.group = newGroup;
				newGroup.stones.add(y);
			}

			stones.clear();
			group.stones.clear();
		}

		public void tryToKill(Board board)
		{
			if(isDead(board))
				kill();
		}
	}
}
