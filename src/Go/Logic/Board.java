package Go.Logic;

import Go.Common.StoneColour;
import sun.invoke.empty.Empty;

import java.util.ArrayList;

public class Board
{
	final int size;
	Stone array[][];
	ArrayList<Game.StoneGroup> groups = new ArrayList<>();
	Board(int s)
	{
		size = s;
		array = new Stone[size][size];
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
				array[i][j] = new Stone(i, j);
		}
	}

	public int[][] toArray()
	{
		int array[][] = new int[size][size];

		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
				array[i][j] = this.array[i][j].colour.getValue();
		}
		return array;
	}

	public static class Stone
	{
		int x;
		int y;
		StoneColour colour;
		Game.StoneGroup group = new Game.StoneGroup(this);


		Stone(int y, int x)
		{
			this.x = x;
			this.y = y;
			colour = StoneColour.Empty;
		}

		public Stone(int y, int x, StoneColour c)
		{
			this(y, x);
			colour = c;
		}

		boolean hasFreeSpace(Board board)
		{
			if(y - 1 >= 0 && board.array[y - 1][x].colour.equals(StoneColour.Empty))
				return true;
			if(x + 1 < board.size && board.array[y][x + 1].colour.equals(StoneColour.Empty))
				return true;
			if(y + 1 < board.size && board.array[y+1][x].colour.equals(StoneColour.Empty))
				return true;
			if(x - 1 >= 0 && board.array[y][x - 1].colour.equals(StoneColour.Empty))
				return true;

			return false;
		}
	}
}
