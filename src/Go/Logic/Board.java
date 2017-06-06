package Go.Logic;

import Go.Common.StoneColour;
import sun.invoke.empty.Empty;

import java.util.ArrayList;

public class Board
{
	final int size;
	Stone array[][];
	Board(int s)
	{
		size = s;
		array = new Stone[size][size];
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
				array[i][j] = new Stone();
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
		StoneColour colour = StoneColour.Empty;
		boolean visited = false;

		public void setColour(StoneColour c)
		{
			colour = c;
		}
	}
}
