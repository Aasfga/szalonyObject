package Go.IO.ConsoleViewInput;


import Go.Common.StoneColour;
import Go.IO.View;
import Go.Logic.Board;
import Go.Logic.Game;
import Go.State;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class ConsoleView implements View
{


	@Override
	public void setCurrentView(State state)
	{
		int size = 5;
		int array[][] = state.getBoard().toArray();
		PrintStream stream = null;
		try
		{
			stream = new PrintStream(System.out, true, "UTF-8");
		} catch(UnsupportedEncodingException e)
		{
			return;
		}

		Random random = new Random();

		printLine(size);
		for(int i = 0; i < size; i++)
		{
			stream.print("|");
			for(int j = 0; j < size; j++)
			{
				if(array[i][j] == 0)
					stream.print(" ");
				else if(array[i][j] == 1)
					stream.print("\u25CB");
				else
					stream.print("\u25CF");
				stream.print("|");
			}
			stream.println();
			printLine(size);
		}

	}

	@Override
	public void setError(Game.Result res)
	{}

	static void printLine(int size)
	{
		for(int i = 0; i < 2*size + 1; i++)
		{
			System.out.print('-');
		}
		System.out.println();
	}

}
