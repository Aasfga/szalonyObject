package GoGame.Logic;

import TurnBasedGameLogic.Controller.Board;
import TurnBasedGameLogic.Controller.PlayerId;
import TurnBasedGameLogic.Module.Game;

import java.util.Arrays;

public class GoBoard implements Board
{
	public static enum Colour implements PlayerId
	{
		Black(0),
		White(1),
		Free(2);

		char mark;

		Colour(int m)
		{
			mark = (char) m;
		}

		public int getId()
		{
			return mark;
		}

	}


	int size;
	char array[][];
	Game game = GoGame.getGoGame();

	@Override
	public int getSize()
	{
		return size;
	}

	@Override
	public Game getGame()
	{
		return null;
	}

	public GoBoard(int size)
	{
		this.size = size;
		array = new char[size][size];

		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
				array[i][j] = '0';

		}
	}

	public GoBoard(String data)
	{
		size = (int) Math.sqrt(data.length());
		array = new char[size][size];
		int j = 0;
		for(int i = 0; i < data.length(); i++)
		{
			if(i != 0 && data.length() % i == 0)
			{
				j++;
			}
			array[j][i % size] = (char) (data.charAt(i) - '0');
		}
	}

	public GoBoard(GoBoard board)
	{
		size = board.size;
		game = board.game;
		for(int i = 0; i < size; i++)
		{
			array[i] = Arrays.copyOf(board.array[i], size);
		}
	}

	public void setField(Colour c, int y, int x)
	{
		array[y][x] = (char) c.getId();
	}

	public char getField(int y, int x)
	{
		return array[y][x];
	}
}
