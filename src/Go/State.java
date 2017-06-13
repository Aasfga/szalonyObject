package Go;

import Go.Logic.Board;
import Go.Player;

import java.util.ArrayList;

public class State
{
	Player player;
	Board board;
	ArrayList<Board> history;
	public static int whiteCaptured = 0;
	public static int blackCaptured = 0;

	public State(Player p, Board b, ArrayList<Board> h, int wc, int bc)
	{
		player = p;
		board = b;
		history = h;
		whiteCaptured = wc;
		blackCaptured = bc;
	}

	public Board getBoard()
	{
		return board;
	}

	public Player getPlayer()
	{
		return player;
	}

	public ArrayList<Board> getHistory()
	{
		return history;
	}

	public int getWhiteCaptured()
	{
		return whiteCaptured;
	}

	public int getBlackCaptured()
	{
		return blackCaptured;
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