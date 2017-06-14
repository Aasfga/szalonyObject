package Go;

import Go.Logic.Board;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

import static Go.IO.WindowViewInput.Go.scoreblack;
import static Go.IO.WindowViewInput.Go.scorewhite;

public class State implements Serializable
{
	Player player;
	Board board;
	ArrayList<Board> history;
	int whiteCaptured = 0;
	int blackCaptured = 0;

	public State(Player p, Board b, ArrayList<Board> h, int wc, int bc)
	{
		player = p;
		board = b;
		history = h;
		whiteCaptured = wc;
		blackCaptured = bc;
		scorewhite = bc;
		scoreblack = wc;
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

		public String toJSON() {
			return (new Gson()).toJson(this);
		}
	}

	public static class Container implements Serializable
	{
		State state;
		Player players[];

		public Container(State s, Player p[])
		{
			state = s;
			players = p;
		}

		public State getState()
		{
			return state;
		}

		public Player[] getPlayers()
		{
			return players;
		}
	}
}