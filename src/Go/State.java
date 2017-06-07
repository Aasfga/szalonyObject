package Go;

import Go.Logic.Board;
import Go.Player;

import java.util.ArrayList;

public class State
{
	Player player;
	Board board;

	public State(Player p, Board b)
	{
		player = p;
		board = b;
	}

	public Board getBoard()
	{
		return board;
	}

	public Player getPlayer()
	{
		return player;
	}


	public static class Move
	{
		public int x;
		public int y;
		public Player player;

		public Move(int y, int x)
		{
			this.y = y;
			this.x = x;
		}
	}
}
