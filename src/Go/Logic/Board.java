package Go.Logic;

import Go.Common.StoneColour;

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

	Board(Board b)
	{
		size = b.size;
		array = new Stone[size][size];

		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
					array[i][j] = new Stone(b.array[i][j]);
		}
	}

	void setVisited(Cords cords)
	{
		array[cords.y][cords.x].visited = true;
	}

	void leave(Cords cords)
	{
		array[cords.y][cords.x].visited = false;
	}

	boolean isVisited(Cords cords)
	{
		return array[cords.y][cords.x].visited;
	}


	void setStone(Cords cords, StoneColour colour)
	{
		if(cords.areInside())
			array[cords.y][cords.x].colour = colour;
		else
			throw new IllegalArgumentException();
	}

	StoneColour getStone(Cords cords)
	{
		return array[cords.y][cords.x].colour;
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

		public Stone()
		{}

		public Stone(Stone stone)
		{
			colour = stone.colour;
		}

		public void setColour(StoneColour c)
		{
			colour = c;
		}

		@Override
		public boolean equals(Object o)
		{
			if(o == null)
				return false;
			else if(!(o instanceof Stone))
				return false;

			Stone other = (Stone) o;

			return colour.equals(other.colour);
		}
	}

	class Cords
	{
		int x;
		int y;


		Cords(int y, int x)
		{
			this.x = x;
			this.y = y;
		}

		boolean areInside()
		{
			return x >= 0 && x < array.length && y >= 0 && y < array.length;
		}

		Cords up()
		{
			return new Cords(y + 1, x);
		}

		Cords down()
		{
			return new Cords(y - 1, x);
		}

		Cords left()
		{
			return new Cords(y, x - 1);
		}

		Cords right()
		{
			return new Cords(y, x + 1);
		}
	}
}
